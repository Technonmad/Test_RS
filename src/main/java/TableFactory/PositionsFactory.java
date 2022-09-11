package TableFactory;

public class PositionsFactory implements TableFactory{

    @Override
    public Table createTable() {
        return new PositionsTable();
    }
}
