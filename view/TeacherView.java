package lesson4.view;

import lesson4.controllers.UserController;
import lesson4.models.Teacher;

import java.util.List;

public class TeacherView implements UserView {

    private final UserController<Teacher> controller;

    public TeacherView(UserController<Teacher> controller) {
        this.controller = controller;
    }

    public void sendOnConsole() {
        sendOnConsole(SortType.NONE);
    }

    @Override
    public void sendOnConsole(SortType sortType) {

        List<Teacher> teachers = switch (sortType) {
            case NONE -> controller.getAll();
            case NAME -> controller.getAllSortByFullName();
            case ID -> controller.getAllSortById();
        };

        if (teachers == null || teachers.size() == 0) {
            System.out.println("Отсутствуют учителя для вывода");
            return;
        }

        System.out.println("===================================");
        System.out.println("Для вывода использована " + sortType);
        teachers.forEach(System.out::println);
        System.out.println("===================================");
    }

    @Override
    public void create(String fullName, Integer age, String phoneNumber, String groupTitle) {
        controller.create(fullName, age, phoneNumber, groupTitle);
    }

    @Override
    public int removeUser(String fullName) {
        return controller.remove(fullName);
    }

    @Override
    public void updateUserGroupById(int id, String groupTitle) {
        controller.updateGroupTitleById(id, groupTitle);
    }
}
