package DAO;

import DTO.Department;
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
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    @Override
    public Department get(int id) throws SQLException {
        try{
            con = Database.getInstance().getConnection();;
            sql = "select * from Departments where id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                int dep_id = rs.getInt("id");
                String dep_name = rs.getString("dep_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                department = new Department(dep_id, dep_name, email, phone);
            }
            return department;
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        finally {
            if (con != null){
                con.close();
            }
            if (ps != null){
                ps.close();
            }
            if (rs != null){
                rs.close();
            }
        }
    }

    @Override
    public List<Department> getAll() throws SQLException {
        List<Department> departmentsList;
        try{
            departmentsList = new ArrayList<>();
            con = Database.getInstance().getConnection();
            sql = "select * from Departments";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

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
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        finally {
            if (con != null){
                con.close();
            }
            if (ps != null){
                ps.close();
            }
            if (rs != null){
                rs.close();
            }
        }
    }

    @Override
    public int insert(Department expression) throws SQLException {
        try {
            con = Database.getInstance().getConnection();
            sql = "insert into Departments (id, dep_name, email, phone) values (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, expression.getId());
            ps.setString(2, expression.getDep_name());
            ps.setString(3, expression.getEmail());
            ps.setString(4, expression.getPhone());
            ps.executeUpdate();
            return 0;
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        finally {
            if (con != null){
                con.close();
            }
            if (ps != null){
                ps.close();
            }
        }
    }

    @Override
    public int update(Department expression, int oldId) throws SQLException {
        try{
            con = Database.getInstance().getConnection();
            sql = "update Departments set id = ?, dep_name = ?, email = ?, phone = ? where id = ?;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, expression.getId());
            ps.setString(2, expression.getDep_name());
            ps.setString(3, expression.getEmail());
            ps.setString(4, expression.getPhone());
            ps.setInt(5, oldId);
            ps.executeUpdate();
            return 0;
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        finally {
            if (con != null){
                con.close();
            }
            if (ps != null){
                ps.close();
            }
        }
    }

    @Override
    public int delete(Department expression) throws SQLException {
        try{
            con = Database.getInstance().getConnection();
            sql = "delete from Departments where id = ?;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, expression.getId());
            ps.executeUpdate();
            return 0;
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        finally {
            if (con != null){
                con.close();
            }
            if (ps != null){
                ps.close();
            }
        }
    }

    @Override
    public int getBossId(String department) throws SQLException {
        try{
            con = Database.getInstance().getConnection();
            sql =
                    "select e.id from employees e" + "\n" +
                    "join department_employees de on e.id = de.employee_id" + "\n" +
                    "join departments d on d.id = de.department_id" + "\n" +
                    "where e.id = e.boss_id and d.dep_name = ?;";
            ps = con.prepareStatement(sql);
            ps.setString(1, department);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        finally {
            if (con != null){
                con.close();
            }
            if (ps != null){
                ps.close();
            }
        }
    }

    @Override
    public List<String> getPositions(String department) throws SQLException {
        List<String> positionList;
        try{
            positionList = new ArrayList<>();
            con = Database.getInstance().getConnection();
            sql =
                    "select p.position_name from positions p\n" +
                    "join departments_positions dp on p.id = dp.position_id\n" +
                    "join departments d on d.id = dp.department_id\n" +
                    "where d.dep_name = ?;";
            ps = con.prepareStatement(sql);
            ps.setString(1,department);
            rs = ps.executeQuery();

            while (rs.next()) {
                String positionName = rs.getString("position_name");
                positionList.add(positionName);
            }

            return positionList;
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        finally {
            if (con != null){
                con.close();
            }
            if (ps != null){
                ps.close();
            }
            if (rs != null){
                rs.close();
            }
        }
    }

    @Override
    public List<String> getDepartmentsName() throws SQLException {
        List<String> departmentsList;
        try{
            departmentsList = new ArrayList<>();
            con = Database.getInstance().getConnection();
            sql = "select dep_name from Departments";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String dep_name = rs.getString("dep_name");
                departmentsList.add(dep_name);
            }

            return departmentsList;
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        finally {
            if (con != null){
                con.close();
            }
            if (ps != null){
                ps.close();
            }
            if (rs != null){
                rs.close();
            }
        }
    }

    @Override
    public int getIdByName(String name) throws SQLException {
        try{
            con = Database.getInstance().getConnection();
            sql = "select id from Departments where dep_name = ?;";
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        finally {
            if (con != null){
                con.close();
            }
            if (ps != null){
                ps.close();
            }
            if (rs != null){
                rs.close();
            }
        }
    }

    @Override
    public int getMaxId() throws SQLException {
        try{
            con = Database.getInstance().getConnection();
            sql = "select max(id) from Departments;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt("max");
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        finally {
            if (con != null){
                con.close();
            }
            if (ps != null){
                ps.close();
            }
        }
    }
}
