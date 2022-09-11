package DAO;

import DTO.Employee;
import Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImp implements EmployeeDAO {

    Employee employee = null;

    @Override
    public Employee get(int id) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "select * from Employees where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            int emp_id = rs.getInt("id");
            int boss_id = rs.getInt("boss_id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");

            employee = new Employee(emp_id, boss_id, first_name, last_name);
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() throws SQLException {

        List<Employee> employeeList = new ArrayList<>();
        Connection con = Database.getConnection();
        String sql = "select * from Employees";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int boss_id = rs.getInt("boss_id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            employee = new Employee(id, boss_id, first_name, last_name);
            employeeList.add(employee);
        }

        return employeeList;
    }

    @Override
    public int insert(Employee expression) throws SQLException {
        return 0;
    }

    @Override
    public int update(Employee expression) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Employee expression) throws SQLException {

        Connection con = Database.getConnection();
        String sql = "delete from Employees where id = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, expression.getId());
        ps.executeUpdate();

        return 0;
    }
}
