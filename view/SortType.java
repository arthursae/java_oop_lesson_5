package lesson4.view;

public enum SortType {
    NONE("сортировка по умолчанию"),
    NAME("сортировка по имени"),
    ID("сортировка по айди");

    private final String sortName;

    @Override
    public String toString() {
        return sortName;
    }

    SortType(String sortName) {
        this.sortName = sortName;
    }
}
