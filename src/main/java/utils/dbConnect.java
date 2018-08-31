package utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.PropertiesCache.getProperty;

public class dbConnect {
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

    private static String getDataValue(String email, String field, String table){
        PreparedStatement ps = null;
        Connection conn = null;
        String SQL = "select "+field+" from "+table+" where email='"+email+"'";
        String result = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            //result=null;
            while (rs.next()) {
                result=rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(dbConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(dbConnect.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(dbConnect.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    private static String getDataValue(String email, String field, String table, String notNull) throws SQLException {
        String result = null;
        try(Connection conn = DriverManager.getConnection(url, user, password);
        Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery("select "+field+" from "+table+" where user_id in (select id from users where email='"+email+"') and "+notNull+" is not null;");
            while (resultSet.next()) {
                result=resultSet.getString(1);
            }
            return result;
        }
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
        dbConnect db = new dbConnect();
        db.getClean(uncheckDevice);
    }

    public static void uncheckDevices(String userName){
        String uncheckDevice = "update user_device set is_checked=0 where user_id in (select id from users where email='"
                +userName+"')";
        dbConnect db = new dbConnect();
        db.getClean(uncheckDevice);
    }

    public static void setLang(int i, String email){
        String uncheckDevice = "update users set lang="+i+" where email='"+email+"'";
        dbConnect db = new dbConnect();
        db.getClean(uncheckDevice);
    }

    public static void setTimeZone(){
        String setTimeZone = "update users set time_zone=2 where  id=79";
        dbConnect db = new dbConnect();
        db.getClean(setTimeZone);
    }

    public static void setTimeZone(String email){
        String setTimeZone = "update users set time_zone=2 where  email='"+email+"'";
        dbConnect db = new dbConnect();
        db.getClean(setTimeZone);
    }

    public static void emailReset(String email, String id){
        String query = "update users set email='"+email+"' where id="+id;
        dbConnect db = new dbConnect();
        db.getClean(query);
    }

    public static void clearData() {
        dbConnect db = new dbConnect();
        db.userSessiondelete();
        db.checkSessionDelete();
        db.userDelete();
        db.checkUserDelete();
        db.inviteDelete();
    }

    public static String getInviteToken(String email){
        return getDataValue(email,"token","invites");
    }

    public static String getRepareToken(String email){
        return getDataValue(email, "recovery_token", "users");
    }

    public static String getUserId(String email){
        return getDataValue(email, "id", "users");
    }

    public static String getUserToken(String email){
        return getDataValue(email, "token", "users");
    }

    public static String getFireBaseToken(String email) {
        String result = null;
        try {
            result = getDataValue(email, "firebase_token", "users_sessions", "firebase_token");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        clearData();
    }
}
