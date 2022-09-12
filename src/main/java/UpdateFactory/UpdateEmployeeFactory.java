package UpdateFactory;

public class UpdateEmployeeFactory implements UpdateFactory {

    @Override
    public Update createFrame() {
        return new UpdateEmployee();
    }
}
