package dsa;

public class StudentList {

    public class StudentNode {

        int id;
        CourseEnrollmentlist headCourse;
        StudentNode next;
        StudentNode prev;

        public StudentNode(int id) {
            this.id = id;
            this.headCourse = null;
            this.next = null;
            this.prev = null;
        }

        public StudentNode(int id, StudentNode next, StudentNode prev) {
            this.id = id;
            this.headCourse = null;
            this.next = next;
            this.prev = prev;
        }

        public boolean removeCourseFromStudent(int courseID) {
            CourseEnrollmentlist current = headCourse;
            CourseEnrollmentlist prev = null;

            while (current != null) {
                if (current.courseid == courseID) {
                    if (prev == null) {
                        headCourse = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    return true;
                }
                prev = current;
                current = current.next;
            }
            return false;
        }
    }

    private StudentNode head;
    private StudentNode tail;

    public StudentList() {
        head = null;
        tail = null;
    }

    public boolean StudentExist(int id) {
        StudentNode current = head;
        while (current != null) {
            if (current.id == id) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean addStudent(int id) {
        if (StudentExist(id)) {
            System.out.println("Student with ID " + id + " already exists, can't be added again");
            return false;
        }

        StudentNode newstudent = new StudentNode(id);
        if (head == null) {
            head = tail = newstudent;
        } else {
            tail.next = newstudent;
            newstudent.prev = tail;
            tail = newstudent;
        }
        System.out.println("Student with ID " + id + " has been added successfully.");
        return true;
    }

    public void removestudent(int id) {
        StudentNode current = head;

        while (current != null) {
            if (current.id == id) {
                if (current == head && current == tail) {
                    head = tail = null;
                } else if (current == head) {
                    head = head.next;
                    head.prev = null;
                } else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Student with id: " + id + " has been removed from the system.");
                return;
            }
            current = current.next;
        }
        System.out.println("Cannot remove student with id: " + id + ", student was not found on the system.");
    }

    public void printlist() {
        StudentNode current = head;
        while (current != null) {
            System.out.println("Student ID: " + current.id);
            current = current.next;
        }
    }

    public StudentNode findStudent(int id) {
        StudentNode current = head;
        while (current != null) {
            if (current.id == id) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void sortCoursesByID(int studentID) {
        StudentNode HeadStudent = head; //HeadStudent : pointer for first student

        // finding required students by id
        while (HeadStudent != null && HeadStudent.id != studentID) {
            HeadStudent = HeadStudent.next;
        }

        if (HeadStudent == null) {
            System.out.println("Students not found.");
            return;
        }

        // sorting students
        CourseEnrollmentlist headCourse = HeadStudent.headCourse;

        if (headCourse == null || headCourse.next == null) {
            System.out.println("Not enough courses to sort for student " + studentID);
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            CourseEnrollmentlist temp = headCourse; //temp : pointer for first course

            while (temp.next != null) {
                if (temp.courseid > temp.next.courseid) {
                    // swap
                    int id = temp.courseid;
                    temp.courseid = temp.next.courseid;
                    temp.next.courseid = id;
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);

        System.out.println("Courses for student " + studentID + " sorted by ID:");
        CourseEnrollmentlist current = HeadStudent.headCourse;
        while (current != null) {
            System.out.print(current.courseid + " ");
            current = current.next;
        }
        System.out.println();
    }
}
