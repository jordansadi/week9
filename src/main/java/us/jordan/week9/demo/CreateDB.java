package us.jordan.week9.demo;

import java.sql.*;

public class CreateDB {
    CreateDB() {
        try
        {
            final String DB_URL = "jdbc:derby:CoffeeDB;create=true";

            Connection conn = DriverManager.getConnection(DB_URL);

            dropTable(conn);
            buildTable(conn);
            conn.close();
        } catch (Exception e)
        {
            System.out.println("Error Creating the Table");
            System.out.println(e.getMessage());
        }
    }

    private static void dropTable(Connection conn)
    {
        try
        {
            Statement stmt = conn.createStatement();

            try
            {
                stmt.execute("DROP TABLE Teams");
            } catch (SQLException ex)
            {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex)
        {
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void buildTable(Connection conn)
    {
        try
        {
            Statement stmt = conn.createStatement();

            stmt.execute("CREATE TABLE Teams (" +
                    "Team VARCHAR(25), " +
                    "TeamNum INT NOT NULL PRIMARY KEY, " +
                    "Location VARCHAR(25) " +
                    ")");

            stmt.execute("INSERT INTO Teams VALUES ( " +
                    "'Birds of Prey', " +
                    "1, " +
                    "'Gotham' )");

            stmt.execute("INSERT INTO Teams VALUES ( " +
                    "'Justice League', " +
                    "2, " +
                    "'Washington DC' )");

            stmt.execute("INSERT INTO Teams VALUES ( " +
                    "'Teen Titans', " +
                    "3, " +
                    "'Jump City' )");

        } catch (SQLException ex)
        {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
