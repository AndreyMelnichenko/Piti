package utils;

import java.sql.*;

import static utils.PropertiesCache.getProperty;

public class dbClearUser {
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String url = getProperty("db.url");
    private static final String user = getProperty("db.user");
    private static final String password = getProperty("db.pass");

    private void getClean(String query) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected database successfully...");
            System.out.println("Executing a query...");
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException ignored) {}
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Query executed! Goodbye!");
    }

    private void userSessiondelete(){
        String sessionDelete = "delete from users_sessions where user_id in (select id from users where email='"+getProperty("new.user.email")+"')";
        getClean(sessionDelete);
    }

    private void checkSessionDelete(){
        String checkSessionDelete = "select count(id) as result from users_sessions where user_id in (select id from users where email='"+getProperty("new.user.email")+"')";
        getClean(checkSessionDelete);
    }

    private void userDelete(){
        String usersDelete = "delete from users where email='"+getProperty("new.user.email")+"'";
        getClean(usersDelete);
    }

    private void checkUserDelete(){
        String checkUserDelete = "select count(id) as result from users where email='"+getProperty("new.user.email")+"'";
        getClean(checkUserDelete);
    }

    private void inviteDelete(){
        String inviteDelete = "delete from invites where email='"+getProperty("user.gmail")+"'";
        getClean(inviteDelete);
    }

    public static void uncheckDevices(){
        String uncheckDevice = "update user_device set is_checked=0 where user_id=79";
        dbClearUser db = new dbClearUser();
        db.getClean(uncheckDevice);
    }

    public static void setTimeZone(){
        String setTimeZone = "update users set time_zone=2 where  id=79";
        dbClearUser db = new dbClearUser();
        db.getClean(setTimeZone);
    }

    public static void clearData() {
        dbClearUser db = new dbClearUser();
        db.userSessiondelete();
        db.checkSessionDelete();
        db.userDelete();
        db.checkUserDelete();
        db.inviteDelete();
    }
}
