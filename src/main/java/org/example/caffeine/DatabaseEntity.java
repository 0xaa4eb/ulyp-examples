package org.example.caffeine;

class DatabaseEntity {

    private final int id;
    private final String field1;
    private final String field2;

    DatabaseEntity(int id, String field1, String field2) {
        this.id = id;
        this.field1 = field1;
        this.field2 = field2;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                '}';
    }
}
