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

public class PositionDAOImp implements PositionDAO{

    Position position = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    @Override
    public Position get(int id) throws SQLException {
        try {
            con = Database.getInstance().getConnection();
            sql = "select * from Positions where id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int pos_id = rs.getInt("id");
                String pos_name = rs.getString("position_name");
                int salary = rs.getInt("salary");

                position = new Position(pos_id, pos_name, salary);
            }
            return position;
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
    public List<Position> getAll() throws SQLException {
        List<Position> positionList;
        try {
            positionList = new ArrayList<>();
            con = Database.getInstance().getConnection();
            sql = "select * from Positions";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String position_name = rs.getString("position_name");
                int salary = rs.getInt("salary");
                position = new Position(id, position_name, salary);
                positionList.add(position);
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
    public int insert(Position expression) throws SQLException {
        try{
            con = Database.getInstance().getConnection();
            sql = "insert into Positions (id, position_name, salary) values (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, expression.getId());
            ps.setString(2, expression.getPosition_name());
            ps.setInt(3, expression.getSalary());
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
    public int update(Position expression, int oldId) throws SQLException {
        try {
            con = Database.getInstance().getConnection();
            sql = "update Positions set id = ?, position_name = ?, salary = ? where id = ?;";
            ps = con.prepareStatement(sql);
            ps.setInt(1, expression.getId());
            ps.setString(2, expression.getPosition_name());
            ps.setInt(3, expression.getSalary());
            ps.setInt(4, oldId);
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
    public int delete(Position expression) throws SQLException {
        try{
            con = Database.getInstance().getConnection();
            sql = "delete from Positions where id = ?;";
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
    public int getSalary(String position) throws SQLException {
        try{
            con = Database.getInstance().getConnection();
            sql = "select p.salary from Positions p where p.position_name = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, position);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt("salary");
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
    public int getIdByName(String name) throws SQLException {
        try{
            con = Database.getInstance().getConnection();
            sql = "select id from Positions where position_name = ?";
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
        }
    }

    @Override
    public int getMaxId() throws SQLException {
        try{
            con = Database.getInstance().getConnection();
            sql = "select max(id) from Positions;";
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
