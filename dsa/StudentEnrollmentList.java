package dsa;

public class StudentEnrollmentList {

    int studentID;
    StudentEnrollmentList next;

    public StudentEnrollmentList(int studentID) {
        this.studentID = studentID;
        this.next = null;
    }

    public StudentEnrollmentList(int studentID, StudentEnrollmentList next) {
        this.studentID = studentID;
        this.next = next;
    }
}
