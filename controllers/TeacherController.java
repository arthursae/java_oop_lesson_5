package lesson4.controllers;

import lesson4.models.Teacher;
import lesson4.services.UserService;

import java.util.List;

public class TeacherController implements UserController<Teacher> {

    private final UserService<Teacher> userService;

    public TeacherController(UserService<Teacher> userService) {
        this.userService = userService;
    }

    //@PostMethod("/teachers")
    @Override
    public void create(String fullName, Integer age, String phoneNumber, String groupTitle) {
        userService.create(fullName, age, phoneNumber, groupTitle);
    }

    @Override
    public void updateGroupTitleById(int id, String groupTitle) {
        userService.updateGroupTitleById(id, groupTitle);
    }

    //@GetMethod("/teachers")
    @Override
    public List<Teacher> getAll() {
        return userService.getAll();
    }

    //@DeleteMethod("/teachers")
    @Override
    public int remove(String fullName) {
        return userService.remove(fullName);
    }

    @Override
    public List<Teacher> getAllSortByFullName() {
        return userService.getAllSortByFullName();
    }

    @Override
    public List<Teacher> getAllSortById() {
        return userService.getAllSortById();
    }
}
