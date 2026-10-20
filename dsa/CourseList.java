package dsa;

public class CourseList {

    public class CourseNode {

        int id;
        StudentEnrollmentList headStudent;
        CourseNode next;
        CourseNode prev;

        public CourseNode(int id) {
            this.id = id;
            this.headStudent = null;
            this.next = null;
            this.prev = null;
        }

        public CourseNode(int id, CourseNode next, CourseNode prev) {
            this.id = id;
            this.headStudent = null;
            this.next = next;
            this.prev = prev;
        }

        public boolean removeStudentFromCourse(int studentID) {
            StudentEnrollmentList current = headStudent;
            StudentEnrollmentList prev = null;

            while (current != null) {
                if (current.studentID == studentID) {
                    if (prev == null) {
                        headStudent = current.next;
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

    private CourseNode head;
    private CourseNode tail;

    public CourseList() {
        this.head = null;
        this.tail = null;
    }

    public void addcourse(int id) {
        CourseNode current = head;
        while (current != null) {
            if (current.id == id) {
                System.out.println("Course with id " + id + " already exists. Cannot be added again.");
                return;
            }
            current = current.next;
        }

        CourseNode newcourse = new CourseNode(id);
        if (head == null) {
            head = tail = newcourse;
        } else {
            tail.next = newcourse;
            newcourse.prev = tail;
            tail = newcourse;
        }
        System.out.println("Course with id " + id + " has been added successfully.");
    }

    public void removecourse(int id) {
        CourseNode current = head;
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (current.id == id) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            System.out.println("Course with id " + id + " has been removed from the system.");
            return;
        }

        while (current != null && current.id != id) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Cannot remove Course with id: " + id + ", course was not found on the system.");
            return;
        }
        if (current == tail) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
            System.out.println("Course with id " + id + " has been removed from the system.");
            return;
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;
        System.out.println("Course with id " + id + " has been removed from the system.");
    }

    // get the first course (Used in system manager to remove student from courses)
    public CourseNode getfirstcourse() {
        return head;
    }

    public void printlist() {
        CourseNode current = head;
        while (current != null) {
            System.out.println("Course ID: " + current.id);
            current = current.next;
        }
    }

    public CourseNode findCourse(int id) {
        CourseNode current = head;
        while (current != null) {
            if (current.id == id) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void sortStudentsByID(int courseID) {
        CourseNode HeadCourse = head;

        // find required course by id
        while (HeadCourse != null && HeadCourse.id != courseID) {
            HeadCourse = HeadCourse.next;
        }

        if (HeadCourse == null) {
            System.out.println("Course not found.");
            return;
        }

        // sorting students
        StudentEnrollmentList headStudent = HeadCourse.headStudent;

        if (headStudent == null || headStudent.next == null) {
            System.out.println("Not enough students to sort in course " + courseID);
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            StudentEnrollmentList temp = headStudent;

            while (temp.next != null) {
                if (temp.studentID > temp.next.studentID) {
                    // swap
                    int id = temp.studentID;
                    temp.studentID = temp.next.studentID;
                    temp.next.studentID = id;
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);

        System.out.println("Students in course " + courseID + " sorted by ID:");
        StudentEnrollmentList current = HeadCourse.headStudent;
        while (current != null) {
            System.out.print(current.studentID + " ");
            current = current.next;
        }
        System.out.println();
    }
}
