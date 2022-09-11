package TableFactory;

public class DepartmentsFactory implements TableFactory{

    @Override
    public Table createTable() {
        return new DepartmentsTable();
    }
}
