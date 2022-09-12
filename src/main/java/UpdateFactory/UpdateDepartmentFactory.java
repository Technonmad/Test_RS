package UpdateFactory;

public class UpdateDepartmentFactory implements UpdateFactory {

    @Override
    public Update createFrame() {
        return new UpdateDepartment();
    }
}
