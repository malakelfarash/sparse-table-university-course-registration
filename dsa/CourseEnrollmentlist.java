package dsa;

public class CourseEnrollmentlist {

    int courseid;
    CourseEnrollmentlist next;

    public CourseEnrollmentlist(int courseid) {
        this.courseid = courseid;
        this.next = null;
    }

    public CourseEnrollmentlist(int courseid, CourseEnrollmentlist next) {
        this.courseid = courseid;
        this.next = next;
    }
}
