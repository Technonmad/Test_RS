package UpdateFactory;

public class UpdatePositionsFactory implements UpdateFactory {

    @Override
    public Update createFrame() {
        return new UpdatePositions();
    }
}
