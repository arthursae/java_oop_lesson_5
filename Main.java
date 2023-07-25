package lesson4;

import lesson4.controllers.GroupController;
import lesson4.controllers.StudentController;
import lesson4.controllers.TeacherController;
import lesson4.repositories.StudentRepository;
import lesson4.repositories.TeacherRepository;
import lesson4.services.GroupService;
import lesson4.services.StudentService;
import lesson4.services.TeacherService;
import lesson4.view.GroupView;
import lesson4.view.StudentView;
import lesson4.view.TeacherView;

public class Main {

    private static StudentRepository studentRepository;
    private static TeacherRepository teacherRepository;
    private static StudentService studentService;
    private static TeacherService teacherService;
    private static StudentController studentController;
    private static TeacherController teacherController;

    public static void main(String[] args) {
        StudentView student = getStudentController();
        TeacherView teacher = getTeacherController();
        GroupView groupView = getGroupView();
//        -------------------------------------------------------------------------

        student.create("Ivan Morozov", 18, "02", "11Б");
        student.create("Ivan Morozov", 18, "02", "11Б");
        student.create("Petr Vorobev", 19, "03", "10А");
        student.create("Sidor Sidorov", 20, "112", "10А");
        student.create("Elena Ivanova", 19, "911", "10А");
        student.create("Anna Morozova", 17, "01", "11А");
        teacher.create("Konstantin Konstantinopolskiy", 42, "108", "11А");
        teacher.create("Maria Ivanovna", 33, "234", "10А");

        groupView.printAllFromGroup("11А");
        groupView.printAllFromGroup("10А");
        //student.sendOnConsole();
        //student.sendOnConsole(SortType.NAME);
        //student.sendOnConsole(SortType.ID);

        //student.removeUser("Ivan Morozov");

        //student.sendOnConsole(SortType.NAME);
        //teacher.sendOnConsole(SortType.NAME);
    }

    private static StudentView getStudentController() {
        studentRepository = new StudentRepository();
        studentService = new StudentService(studentRepository);
        studentController = new StudentController(studentService);
        return new StudentView(studentController);
    }

    private static TeacherView getTeacherController() {
        teacherRepository = new TeacherRepository();
        teacherService = new TeacherService(teacherRepository);
        teacherController = new TeacherController(teacherService);
        return new TeacherView(teacherController);
    }

    private static GroupView getGroupView() {
        GroupService groupService = new GroupService(studentService, teacherService);
        GroupController groupController = new GroupController(groupService);
        return new GroupView(groupController);
    }
}
