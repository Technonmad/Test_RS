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

    @Override
    public Position get(int id) throws SQLException {
        Connection con = Database.getConnection();
        String sql = "select * from Positions where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
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

    @Override
    public List<Position> getAll() throws SQLException {
        List<Position> positionList = new ArrayList<>();
        Connection con = Database.getConnection();
        String sql = "select * from Positions";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String position_name = rs.getString("position_name");
            int salary = rs.getInt("salary");
            position = new Position(id, position_name, salary);
            positionList.add(position);
        }

        return positionList;
    }

    @Override
    public int insert(Position expression) throws SQLException {
        return 0;
    }

    @Override
    public int update(Position expression) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Position expression) throws SQLException {

        Connection con = Database.getConnection();
        String sql = "delete from Positions where id = ?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, expression.getId());
        ps.executeUpdate();

        return 0;
    }
}
