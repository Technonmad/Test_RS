package DTO;

public class Employee {

    private int id;
    private int boss_id;
    private String first_name;
    private String last_name;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", boss_id=" + boss_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }

    public Employee(int id, int boss_id, String first_name, String last_name) {
        this.id = id;
        this.boss_id = boss_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoss_id() {
        return boss_id;
    }

    public void setBoss_id(int boss_id) {
        this.boss_id = boss_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
