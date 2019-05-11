package us.jordan.week9.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

@Service
public class TeamService {
    static int teamNum = 4;

    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();

        final String DB_URL = "jdbc:derby:CoffeeDB";
        Statement stmt = null;
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT Team, Location FROM Teams";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                //Retrieve by column name
                String id  = rs.getString("Team");
                String description = rs.getString("Location");

                teams.add(new Team(id, description));
            }

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return teams;
    }

    public Team getTeam(String id) {
        final String DB_URL = "jdbc:derby:CoffeeDB";
        Connection conn = null;
        Statement getSTMT = null;
        Team getTeam = new Team();

        try{
            conn = DriverManager.getConnection(DB_URL);
            getSTMT = conn.createStatement();

            String get = "SELECT Team, Location FROM Teams WHERE Team=" + id;
            ResultSet rs = getSTMT.executeQuery(get);

            String team = "", location = "";

            if(rs.next()) {
                team = rs.getString(1);
                location = rs.getString(3);
            }

            getTeam = new Team(team, location);

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(getSTMT!=null)
                    getSTMT.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return getTeam;
    }

    public void addTeam(Team team) {
        final String DB_URL = "jdbc:derby:CoffeeDB";
        String SQL = "INSERT INTO Teams VALUES (?, ?, ?)";
        Connection conn = null;
        Statement getSTMT = null;
        PreparedStatement setSTMT;
        String name = team.getName(), location = team.getLocation();

        try{
            conn = DriverManager.getConnection(DB_URL);
            setSTMT = conn.prepareStatement(SQL);

            setSTMT.setString(1, name);
            setSTMT.setInt(2, teamNum);
            setSTMT.setString(3, location);
            teamNum++;

            setSTMT.executeUpdate();
            //Clean-up environment
            setSTMT.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}
