package dsa;

import java.util.Scanner;

public class DSA {

    public static void main(String[] args) {
        // إنشاء SystemManager
        SystemManager systemManager = new SystemManager();

        // إنشاء كائن Scanner لقراءة المدخلات من المستخدم
        Scanner scanner = new Scanner(System.in);

        // عرض القائمة للمستخدم
        while (true) {
            System.out.println("\n-- System Menu --");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Add course");
            System.out.println("4. Remove course");
            System.out.println("5. Enroll Student in Course");
            System.out.println("6. Remove Student from Course");
            System.out.println("7. Undo");
            System.out.println("8. Redo");
            System.out.println("9. Show student list");
            System.out.println("10. Show course list");
            System.out.println("11. check if the course is full or not by ID");
            System.out.println("12. check if the student is normal or not by ID ");
            System.out.println("13. sort students in a course by course ID");
            System.out.println("14. sort courses enrolled by a students by student ID");
            System.out.println("-1. Exit");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID to add: ");
                    int studentIdToAdd = scanner.nextInt();
                    systemManager.addStudent(studentIdToAdd);  // إضافة طالب
                    break;

                case 2:
                    System.out.print("Enter Student ID to remove: ");
                    int studentIdToRemove = scanner.nextInt();
                    systemManager.removeStudent(studentIdToRemove);  // إزالة طالب
                    break;

                case 3:
                    System.out.print("Enter Course Id to add: ");
                    int courseIdToAdd = scanner.nextInt();
                    systemManager.addCourse(courseIdToAdd);
                    break;

                case 4:
                    System.out.print("Enter Course Id to remove: ");
                    int courseIdToDelete = scanner.nextInt();
                    systemManager.removeCourse(courseIdToDelete);
                    break;

                case 5:
                    System.out.print("Enter Student ID to enroll: ");
                    int studentIdEnroll = scanner.nextInt();
                    System.out.print("Enter Course ID to enroll: ");
                    int courseIdEnroll = scanner.nextInt();
                    systemManager.enrollStudent(studentIdEnroll, courseIdEnroll);  // تسجيل طالب في كورس
                    break;

                case 6:
                    System.out.print("Enter Student ID to remove from course: ");
                    int studentIdRemove = scanner.nextInt();
                    System.out.print("Enter Course ID to remove from: ");
                    int courseIdRemove = scanner.nextInt();
                    systemManager.removeEnrollment(studentIdRemove, courseIdRemove);  // إزالة طالب من كورس
                    break;

                case 7:
                    systemManager.undo();  // تنفيذ Undo
                    break;

                case 8:
                    systemManager.redo();  // تنفيذ Redo
                    break;

                case 9:
                    systemManager.printStudentList();
                    break;

                case 10:
                    systemManager.printCourseList();
                    break;
                case 11:
                    System.out.print("Enter Course ID: ");
                    int courseidtocheck = scanner.nextInt();
                    systemManager.isFullCourse(courseidtocheck);
                    break;
                case 12:
                    System.out.print("Enter Student ID: ");
                    int studentidtocheck = scanner.nextInt();
                    systemManager.isNormalStudent(studentidtocheck);
                    break;
                case 13:
                    System.out.print("Enter Course ID: ");
                    int courseidtosort = scanner.nextInt();
                    systemManager.sortStudentsByID(courseidtosort);
                    break;
                case 14:
                    System.out.print("Enter Student ID: ");
                    int studentidtosort = scanner.nextInt();
                    systemManager.sortCoursesByID(studentidtosort);
                    break;

                case -1:
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
