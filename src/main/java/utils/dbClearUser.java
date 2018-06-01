package utils;

import java.sql.*;

import static utils.PropertiesCache.getProperty;

public class dbClearUser {
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String url = getProperty("db.url");
    private static final String user = getProperty("db.user");
    private static final String password = getProperty("db.pass");

    public static void getClean () {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected database successfully...");
            System.out.println("Executing a query...");
            stmt = conn.createStatement();

            String sessionDelete = "delete from users_sessions where user_id in " +
                    "(select id from users where email='"+getProperty("new.user.email")+"')";
            String checkSessionDelete = "select count(id) as result from users_sessions where user_id in " +
                    "(select id from users where email='"+getProperty("new.user.email")+"')";
            String usersDelete = "delete from users where email='"+getProperty("new.user.email")+"'";
            String checkUserDelete = "select count(id) as result from users where email='"+getProperty("new.user.email")+"'";
            String iviteDelete = "delete from invites where email='"+getProperty("user.gmail")+"'";

            stmt.executeUpdate(sessionDelete);
            stmt.executeUpdate(checkSessionDelete);
            stmt.executeUpdate(usersDelete);
            stmt.executeUpdate(checkUserDelete);
            stmt.executeUpdate(iviteDelete);

            ResultSet sessionDel = stmt.executeQuery(sessionDelete);
            ResultSet resultSessionDelete = stmt.executeQuery(checkSessionDelete);
            ResultSet userDel = stmt.executeQuery(usersDelete);
            ResultSet rresultUserDelete = stmt.executeQuery(checkUserDelete);
            ResultSet deleteIvite = stmt.executeQuery(iviteDelete);

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Current USER was DELTED! Goodbye!");
    }

/*    public static void main(String[] args) {
        getClean();
    }*/
}
