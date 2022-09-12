package InsertFactory;

import UpdateFactory.UpdateEmployee;

public class InsertEmployeeFactory implements InsertFactory{

    @Override
    public Insert createFrame() {
        return new InsertEmployee();
    }

}
