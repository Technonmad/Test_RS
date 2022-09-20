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
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    @Override
    public Employee get(int id) throws SQLException {
        try {
            con = Database.getInstance().getConnection();
            sql =
                    "select e.id, e.boss_id, e.first_name, e.last_name, d.dep_name, p.position_name, p.salary\n" +
                    "from Employees e\n" +
                    "join Department_Employees de on e.id = de.employee_id\n" +
                    "join Departments d on d.id = de.department_id\n" +
                    "join Employees_Positions ep on e.id = ep.employee_id\n" +
                    "join Positions p on p.id = ep.position_id\n" +
                    "where e.id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                int emp_id = rs.getInt("id");
                int boss_id = rs.getInt("boss_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String dep_name = rs.getString("dep_name");
                String position = rs.getString("position_name");
                int salary = rs.getInt("salary");

                employee = new Employee(emp_id, boss_id, first_name, last_name, dep_name, position, salary);
            }
            return employee;
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
    public List<Employee> getAll() throws SQLException {

        List<Employee> employeeList;
        try {
            employeeList = new ArrayList<>();
            con = Database.getInstance().getConnection();
            sql = "select e.id, e.boss_id, e.first_name, e.last_name, d.dep_name, p.position_name, p.salary\n" +
                    "from Employees e\n" +
                    "join Department_Employees de on e.id = de.employee_id\n" +
                    "join Departments d on d.id = de.department_id\n" +
                    "join Employees_Positions ep on e.id = ep.employee_id\n" +
                    "join Positions p on p.id = ep.position_id;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int boss_id = rs.getInt("boss_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String dep_name = rs.getString("dep_name");
                String position = rs.getString("position_name");
                int salary = rs.getInt("salary");
                employee = new Employee(id, boss_id, first_name, last_name, dep_name, position, salary);
                employeeList.add(employee);
            }
            return employeeList;
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
    public int insert(Employee expression) throws SQLException {
        DepartmentDAO departmentDAO = new DepartmentDAOImp();
        PositionDAO positionDAO = new PositionDAOImp();
        try{
            con = Database.getInstance().getConnection();
            sql = "insert into Employees (id, boss_id, first_name, last_name) values (?, ?, ?, ?);";
            ps = con.prepareStatement(sql);
            ps.setInt(1, expression.getId());
            ps.setInt(2, expression.getBoss_id());
            ps.setString(3, expression.getFirst_name());
            ps.setString(4, expression.getLast_name());
            ps.executeUpdate();

            sql = "insert into Department_Employees(employee_id, department_id) values (?, ?);";
            ps = con.prepareStatement(sql);
            ps.setInt(1, expression.getId());
            ps.setInt(2, departmentDAO.getIdByName(expression.getDep_name()));
            ps.executeUpdate();

            sql = "insert into Employees_Positions(employee_id, position_id) values (?, ?);";
            ps = con.prepareStatement(sql);
            ps.setInt(1, expression.getId());
            ps.setInt(2, positionDAO.getIdByName(expression.getPosition()));
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
    public int update(Employee expression, int id) throws SQLException {
        DepartmentDAO departmentDAO = new DepartmentDAOImp();
        PositionDAO positionDAO = new PositionDAOImp();
        try{
            con = Database.getInstance().getConnection();
            sql = "update Employees set first_name = ?, last_name = ?, boss_id = ? where id = ?;";
            ps = con.prepareStatement(sql);
            ps.setString(1, expression.getFirst_name());
            ps.setString(2, expression.getLast_name());
            ps.setInt(3, expression.getBoss_id());
            ps.setInt(4, id);
            ps.executeUpdate();

            sql = "update Department_Employees set department_id = ? where employee_id = ?;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, departmentDAO.getIdByName(expression.getDep_name()));
            ps.setInt(2, id);
            ps.executeUpdate();

            sql = "update Employees_Positions set position_id = ? where employee_id = ?;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, positionDAO.getIdByName(expression.getPosition()));
            ps.setInt(2, id);
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
    public int delete(Employee expression) throws SQLException {
        try{
            con = Database.getInstance().getConnection();
            sql = "delete from Employees where id = ?;";
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
    public int getMaxId() throws SQLException {
        try{
            con = Database.getInstance().getConnection();
            sql = "select max(id) from Employees;";
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
