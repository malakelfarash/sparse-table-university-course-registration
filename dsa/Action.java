package dsa;

public class Action {

    String type;       // نوع العملية (مثلاً: "addStudent")
    int studentid;
    int courseid;

    public Action(String type, int studentid, int courseid) {
        this.type = type;
        this.studentid = studentid;
        this.courseid = courseid;
    }
}
