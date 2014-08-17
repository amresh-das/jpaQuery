package com.huskycode.jpaquery.types.db;

public class ColumnImpl implements Column {
    private final String name;
    private final Class<?> type;

    public ColumnImpl(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColumnImpl column = (ColumnImpl) o;

        if (name != null ? !name.equals(column.name) : column.name != null) return false;
        if (type != null ? !type.equals(column.type) : column.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
