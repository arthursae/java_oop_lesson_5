package lesson4.view;

import lesson4.controllers.UserController;
import lesson4.models.Student;

import java.util.List;

public class StudentView implements UserView {

    private final UserController<Student> controller;

    public StudentView(UserController<Student> controller) {
        this.controller = controller;
    }

    public void sendOnConsole() {
        sendOnConsole(SortType.NONE);
    }

    @Override
    public void sendOnConsole(SortType sortType) {

        List<Student> students = switch (sortType) {
            case NONE -> controller.getAll();
            case NAME -> controller.getAllSortByFullName();
            case ID -> controller.getAllSortById();
        };

        if (students == null || students.size() == 0) {
            System.out.println("Отсутствуют студенты для вывода");
            return;
        }

        System.out.println("===================================");
        System.out.println("Для вывода использована " + sortType);
        students.forEach(System.out::println);
        System.out.println("===================================");
    }

    @Override
    public void create(String fullName, Integer age, String phoneNumber, String groupTitle) {
        controller.create(fullName, age, phoneNumber, groupTitle);
    }

    @Override
    public void removeUser(String fullName) {
        int removeCount = controller.remove(fullName);
        System.out.println("Удалено студентов: " + removeCount);
    }
}
