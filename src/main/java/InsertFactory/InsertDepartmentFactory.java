package InsertFactory;

public class InsertDepartmentFactory implements InsertFactory{

    @Override
    public Insert createFrame() {
        return new InsertDepartment();
    }
}
