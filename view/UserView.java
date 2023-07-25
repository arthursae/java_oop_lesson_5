package lesson4.view;

public interface UserView {
    void sendOnConsole(); //GET

    void sendOnConsole(SortType sortType); //GET

    void create(String fullName, Integer age, String phoneNumber, String groupTitle);//POST

    int removeUser(String fullName); //DELETE

    void updateUserGroupById(int id, String groupTitle); //UPDATE
}
