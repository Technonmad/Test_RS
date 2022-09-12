package InsertFactory;

public class InsertPositionFactory implements InsertFactory{

    @Override
    public Insert createFrame() {
        return new InsertPosition();
    }

}
