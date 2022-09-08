package DTO;

public class Position {

    private int id;
    private String position_name;
    private int salary;

    public Position(int id, String position_name, int salary) {
        this.id = id;
        this.position_name = position_name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", position_name='" + position_name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
