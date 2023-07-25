package lesson4.services;

import lesson4.models.Student;
import lesson4.models.User;
import lesson4.repositories.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentService implements UserService<Student> {

    private final UserRepository<Student> userRepository;

    public StudentService(UserRepository<Student> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(String fullName, Integer age, String phoneNumber, String groupTitle) {
        userRepository.create(new Student(fullName, age, phoneNumber, groupTitle));
    }

    @Override
    public void updateGroupTitleById(int id, String groupTitle) {
        var students = userRepository.getAll();

        for (Student student : students) {
            if (student.getId().intValue() == id) {
                userRepository.updateGroupTitleById(id, groupTitle);
            }
        }
    }

    @Override
    public List<Student> getAll() {
        var students = userRepository.getAll();
        Collections.sort(students);
        return students;
    }

    public List<Student> getAllSortByFullName() {
        var students = userRepository.getAll();
        students.sort(Comparator.comparing(User::getFullName));
        return students;
    }

    public List<Student> getAllSortById() {
        var students = userRepository.getAll();
        students.sort(Comparator.comparing(User::getId));
        return students;
    }

    @Override
    public int remove(String fullName) {
        return userRepository.remove(fullName);
    }

    @Override
    public List<Student> getAllByTitile(String groupTitle) {
        return userRepository.getAllByGroupTitle(groupTitle);
    }
}
