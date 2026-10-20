package dsa;

import java.util.Stack;

public class SystemManager {

    StudentList studentList;
    CourseList courseList;
    int lastStudentID;
    int lastCourseID;

    Stack<Action> undoStack = new Stack<>();
    Stack<Action> redoStack = new Stack<>();

    public SystemManager() {
        this.studentList = new StudentList();
        this.courseList = new CourseList();
    }

    public void addStudent(int id) {
        studentList.addStudent(id);
        undoStack.push(new Action("addStudent", id, 0));
        lastStudentID = id;
    }

    public void removeStudent(int id) {
        StudentList.StudentNode student = studentList.findStudent(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        // Remove this student from all courses they're enrolled in
        CourseEnrollmentlist currentCourse = student.headCourse;
        while (currentCourse != null) {
            CourseList.CourseNode courseNode = courseList.findCourse(currentCourse.courseid);
            if (courseNode != null) {
                courseNode.removeStudentFromCourse(id);
            }
            currentCourse = currentCourse.next;
        }
        studentList.removestudent(id);
        undoStack.push(new Action("removeStudent", id, 0));
    }

    public int getLastStudentAdded() {
        if (lastStudentID == 0) {
            System.out.println("No Student has been added yet.");
        }
        return lastStudentID;
    }

    public void printStudentList() {
        studentList.printlist();
    }

    public void addCourse(int id) {
        courseList.addcourse(id);
        undoStack.push(new Action("addCourse", 0, id));
        lastCourseID = id;
    }

    public void removeCourse(int id) {
        courseList.removecourse(id);
        undoStack.push(new Action("removeCourse", 0, id));
    }

    public int getLastCourseAdded() {
        if (lastCourseID == 0) {
            System.out.println("No Course has been added yet.");
        }
        return lastCourseID;
    }

    public void printCourseList() {
        courseList.printlist();
    }

    public void enrollStudent(int studentID, int courseID) {
        // Find the student
        StudentList.StudentNode student = studentList.findStudent(studentID);
        if (student == null) {
            System.out.println("Student with ID " + studentID + " does not exist.");
            return;
        }

        // Find the course
        CourseList.CourseNode course = courseList.findCourse(courseID);
        if (course == null) {
            System.out.println("Course with ID " + courseID + " does not exist.");
            return;
        }

        // Check if student already enrolled in this course
        CourseEnrollmentlist ce = student.headCourse;
        while (ce != null) {
            if (ce.courseid == courseID) {
                System.out.println("Student " + studentID + " is already enrolled in course " + courseID);
                return;
            }
            ce = ce.next;
        }

        // Check if student is already enrolled in 7 courses
        int courseCount = 0;
        ce = student.headCourse;
        while (ce != null) {
            courseCount++;
            ce = ce.next;
        }
        if (courseCount > 7) {
            System.out.println("Student " + studentID + " cannot be enrolled in more than 7 courses.");
            return;
        }

        // Check if course already has 30 students
        int studentCount = 0;
        StudentEnrollmentList se = course.headStudent;
        while (se != null) {
            studentCount++;
            se = se.next;
        }
        if (studentCount >= 30) {
            System.out.println("Course " + courseID + " cannot have more than 30 students.");
            return;
        }

        // Add the course to the student's course list
        CourseEnrollmentlist newCourseEnrollment = new CourseEnrollmentlist(courseID);
        newCourseEnrollment.next = student.headCourse;
        student.headCourse = newCourseEnrollment;

        // Add the student to the course's student list
        StudentEnrollmentList newStudentEnrollment = new StudentEnrollmentList(studentID);
        newStudentEnrollment.next = course.headStudent;
        course.headStudent = newStudentEnrollment;

        System.out.println("Student " + studentID + " has been enrolled in course " + courseID + " successfully.");

        // Save in undo stack
        undoStack.push(new Action("enroll", studentID, courseID));
    }

    public void removeEnrollment(int studentID, int courseID) {

        // Find the student
        StudentList.StudentNode student = studentList.findStudent(studentID);
        if (student == null) {
            System.out.println("Student with ID " + studentID + " not found.");
            return;
        }
        // Find the course
        CourseList.CourseNode course = courseList.findCourse(courseID);
        if (course == null) {
            System.out.println("Course with ID " + courseID + " not found.");
            return;
        }

        // Remove student from coourse
        boolean studentRemovedFromCourse = course.removeStudentFromCourse(studentID);

        // Remove course from student course list
        boolean courseRemovedFromStudent = student.removeCourseFromStudent(courseID);

        if (studentRemovedFromCourse && courseRemovedFromStudent) {
            System.out.println("Student with ID " + studentID + " successfully removed from course " + courseID + ".");
            undoStack.push(new Action("addEnroll", studentID, courseID));
        } else {
            System.out.println("Removal failed. Student is not enrolled in this course.");
        }
    }

    // listCourse By student id
    public void listCourseByStudent(int studentId) {
        StudentList.StudentNode student = studentList.findStudent(studentId);
        if (student == null) {
            System.out.println("Student not found");
            return;
        }
        CourseEnrollmentlist current = student.headCourse;
        if (current == null) {
            System.out.println("There`s no courses enrolled by the student with id: " + studentId);
            return;
        }
        System.out.println("Courses enrolled by student with id(" + studentId + ") :");
        while (current != null) {
            System.out.println("Course id:" + current.courseid);
            current = current.next;
        }
    }

    // listStudent By course id
    public void listStudentsByCourse(int courseID) {
        CourseList.CourseNode course = courseList.findCourse(courseID);

        if (course == null) {
            System.out.println("Course with ID " + courseID + " was not found.");
            return;
        }

        StudentEnrollmentList currentStudent = course.headStudent;

        if (currentStudent == null) {
            System.out.println("No students are registered in course with ID " + courseID);
            return;
        }

        System.out.println("Students enrolled in course " + courseID + ":");
        while (currentStudent != null) {
            System.out.println("Student ID: " + currentStudent.studentID);
            currentStudent = currentStudent.next;
        }
    }

    // is the course full
    public boolean isFullCourse(int courseid) {
        CourseList.CourseNode course = courseList.findCourse(courseid);
        if (course == null) {
            System.out.println("Course not found");
            return false;
        }
        StudentEnrollmentList current = course.headStudent;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        if (count < 30) {
            System.out.println("Course isn`t full");
            return false;
        } else {
            System.out.println("course is full");
            return true;
        }
    }

    // is student normal
    public boolean isNormalStudent(int studentID) {
        StudentList.StudentNode student = studentList.findStudent(studentID);
        if (student == null) {
            System.out.println("Student not found");
            return false;
        }

        int courseCount = 0;
        CourseEnrollmentlist current = student.headCourse;

        while (current != null) {
            courseCount++;
            current = current.next;
        }
        if (courseCount < 2 || courseCount > 7) {
            System.out.println("Student isn`t normal");
            return false;
        } else {
            System.out.println("Student is normal");
            return true;
        }
    }

    public void sortStudentsByID(int cid) {
        courseList.sortStudentsByID(cid);
    }

    public void sortCoursesByID(int sid) {
        studentList.sortCoursesByID(sid);
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("nothing to undo");
            return;
        }
        Action action = undoStack.pop();

        switch (action.type) {
            case "addStudent":
                studentList.removestudent(action.studentid);
                redoStack.push(new Action("removeStudent", action.studentid, 0));
                break;

            case "removeStudent":
                studentList.addStudent(action.studentid);
                redoStack.push(new Action("addStudent", action.studentid, 0));
                break;

            case "addCourse":
                courseList.removecourse(action.courseid);
                redoStack.push(new Action("removeCourse", 0, action.courseid));
                break;

            case "removeCourse":
                courseList.addcourse(action.courseid);
                redoStack.push(new Action("addCourse", 0, action.courseid));
                break;

            case "enroll":
                removeEnrollment(action.studentid, action.courseid);
                redoStack.push(new Action("removeEnroll", action.studentid, action.courseid));
                break;

            case "removeEenroll":
                enrollStudent(action.studentid, action.courseid);
                redoStack.push(new Action("enroll", action.studentid, action.courseid));
                break;

            default:
                System.out.println("Unknown action type: " + action.type);
        }
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("nothing to redo");
            return;
        }

        Action action = redoStack.pop();

        switch (action.type) {
            case "removeStudent":
                studentList.addStudent(action.studentid);
                undoStack.push(new Action("addStudent", action.studentid, 0));
                break;

            case "addStudent":
                studentList.removestudent(action.studentid);
                undoStack.push(new Action("removeStudent", action.studentid, 0));
                break;

            case "removeCourse":
                courseList.addcourse(action.courseid);
                undoStack.push(new Action("addCourse", 0, action.courseid));
                break;

            case "addCourse":
                courseList.removecourse(action.courseid);
                undoStack.push(new Action("removeCourse", 0, action.courseid));
                break;

            case "removeEnroll":
                enrollStudent(action.studentid, action.courseid);
                undoStack.push(new Action("enroll", action.studentid, action.courseid));
                break;

            case "enroll":
                removeEnrollment(action.studentid, action.courseid);
                undoStack.push(new Action("removeEnroll", action.studentid, action.courseid));
                break;

            default:
                System.out.println("Unknown action type: " + action.type);
        }
    }
}
