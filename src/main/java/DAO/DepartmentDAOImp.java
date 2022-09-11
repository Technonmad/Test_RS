package DAO;

import DTO.Department;
import DTO.Employee;
import DTO.Position;
import Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImp implements DepartmentDAO{

    Department department = null;

    @Override
    public Department get(int id) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "select * from Departments where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            int dep_id = rs.getInt("id");
            String dep_name = rs.getString("dep_name");
            String email = rs.getString("email");
            String phone = rs.getString("phone");

            department = new Department(dep_id, dep_name, email, phone);
        }
        return department;
    }

    @Override
    public List<Department> getAll() throws SQLException {
        List<Department> departmentsList = new ArrayList<>();
        Connection con = Database.getConnection();
        String sql = "select * from Departments";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String dep_name = rs.getString("dep_name");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            department = new Department(id, dep_name, email, phone);
            departmentsList.add(department);
        }

        return departmentsList;
    }

    @Override
    public int insert(Department expression) throws SQLException {
        return 0;
    }

    @Override
    public int update(Department expression) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Department expression) throws SQLException {

        Connection con = Database.getConnection();
        String sql = "delete from Departments where id = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, expression.getId());
        ps.executeUpdate();

        return 0;
    }
}
