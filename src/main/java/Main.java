import DAO.EmployeeDAO;
import DAO.EmployeeDAOImp;
import DTO.Employee;
import Database.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        GUI gui = new GUI();
        gui.setVisible(true);

        /*Connection con = Database.getConnection();
        if (con != null)
            System.out.println("KPACUBO");*/
    }

}
