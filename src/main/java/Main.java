import Database.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        //GUI gui = new GUI();
        //gui.setVisible(true);

            Connection con = Database.getConnection();
            if (con != null)
                System.out.println("KPACUBO");
    }

}
