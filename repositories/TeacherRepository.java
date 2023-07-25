package lesson4.repositories;

import lesson4.models.Teacher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherRepository implements UserRepository<Teacher> {

    private final List<Teacher> students;

    public TeacherRepository() {
        this.students = new ArrayList<>();
    }

    @Override
    public void create(Teacher teacher) {
        teacher.setId(getMaxId() + 1);
        students.add(teacher);
    }

    @Override
    public List<Teacher> getAll() {
        return students;
    }

    @Override
    public int remove(String fullName) {
        int removeCount = 0;

        Iterator<Teacher> iterator = students.iterator();
        while (iterator.hasNext()) {
            Teacher teacher = iterator.next();
            if (teacher.getFullName().equals(fullName)) {
                iterator.remove();
                removeCount++;
            }
        }
        return removeCount;
    }

    @Override
    public List<Teacher> getAllByGroupTitle(String groupTitle) {
        return students.stream()
                .filter(student -> student.getGroupTitle().equals(groupTitle))
                .collect(Collectors.toList());
    }

    private Long getMaxId() {
        Long maxId = 0L;
        for (Teacher student : students) {
            if (student.getId() > maxId) {
                maxId = student.getId();
            }
        }
        return maxId;
    }
}
