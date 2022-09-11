package TableFactory;

public class EmployeesFactory implements TableFactory {

    @Override
    public Table createTable() {
        return new EmployeesTable();
    }
}
