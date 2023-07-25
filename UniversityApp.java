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
import lesson4.view.SortType;

import java.util.Scanner;

public class UniversityApp {

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
//      -------------------------------------------------------------------------

        student.create("Ivan Morozov", 18, "02", "11Б");
        student.create("Ivan Morozov", 18, "02", "11Б");
        student.create("Petr Vorobev", 19, "03", "10А");
        student.create("Sidor Sidorov", 20, "112", "10А");
        student.create("Elena Ivanova", 19, "911", "10А");
        student.create("Anna Morozova", 17, "01", "11А");
        teacher.create("Konstantin Sidorov", 42, "108", "11А");
        teacher.create("Maria Pavlova", 33, "234", "10А");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите запрос: ");
            String input = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(input)) break;

            if (!input.isEmpty()) {
                String[] commands = input.split("\\s+");
                int commandslength = commands.length;

                if ("/get-student".equalsIgnoreCase(commands[0])) {

                    if (commandslength == 2) {
                        if ("name".equalsIgnoreCase(commands[1])) {
                            student.sendOnConsole(SortType.NAME);
                        } else if ("id".equalsIgnoreCase(commands[1])) {
                            student.sendOnConsole(SortType.ID);
                        } else {
                            System.out.println("Неправильный параметр сортировки: " + commands[1]);
                        }
                    } else {
                        student.sendOnConsole(SortType.NONE);
                    }
                } else if ("/get-group".equalsIgnoreCase(commands[0])) {

                    if (commandslength == 2) {
                        groupView.printAllFromGroup(commands[1]);
                    } else {
                        System.out.println("Не указано название группы");
                    }
                } else if ("/create-student".equalsIgnoreCase(commands[0])) {

                    if (commandslength == 5) {
                        String studentName = commands[1];
                        Integer studentAge = null;
                        String studentPhone = commands[3];
                        String studentGroup = commands[4];

                        try {
                            studentAge = Integer.parseInt(commands[2]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Указан неправильный возраст студента");
                        }

                        if (studentAge != null) {
                            student.create(studentName.replace("_", " "), studentAge, studentPhone, studentGroup);
                            System.out.println("Студент '" + studentName + "' добавлен");
                        } else {
                            System.out.println("Не удалось добавить студента");
                        }
                    } else {
                        System.out.println("Не указаны все данные студента: имя_фамилия возраст телефон группа");
                    }

                } else if ("/delete-teacher".equalsIgnoreCase(commands[0])) {
                    if (commandslength == 2) {
                        String teacherName = commands[1].replace("_", " ");
                        int counter = teacher.removeUser(teacherName);
                        if (counter > 0) {
                            System.out.println("Учитель удален: " + counter);
                        } else {
                            System.out.println("Учитель с именем " + teacherName + " отсутствует в списке");
                        }
                    } else {
                        System.out.println("Не указано имя учителя: имя_фамилия");
                    }

                } else if ("/set-group-student-by-id".equalsIgnoreCase(commands[0])) {
                    if (commandslength == 3) {
                        int id = -1;

                        try {
                            id = Integer.parseInt(commands[1]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Указан неправильный формат айди");
                        }

                        if (id > 0) {
                            student.updateUserGroupById(id, commands[2]);
                        } else {
                            System.out.println("Не удалось обновить данные с id = " + id);
                        }

                    } else {
                        System.out.println("Не указаны все данные: айди и название группы");
                    }
                } else {
                    System.out.println("Неправильная команда");
                }
            }
        }
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
