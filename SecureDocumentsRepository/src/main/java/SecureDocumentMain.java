package main.java;

/**
 * Created by Jeromy on 2/28/14.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import  java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SecureDocumentMain {
    public static void main(String[] args) throws SQLException{

    try{
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        System.out.println("Where is MySQL JDBC Drive");
        e.printStackTrace();
        return;
    }
        Connection conn;
        String str;
        String err;
        String timeLog;

    try {
        String URL = "jdbc:mysql://localhost:3306/securedocsrep";
        conn = DriverManager.getConnection(URL, "mysql", "mysql123");

    }
    catch (SQLException e) {
         System.out.println();
         e.printStackTrace();
         return;
    }

    if(conn != null) {
        timeLog = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        err = conn.toString();
        str = "insert into logstbl(logCode, errMessage,user,createdate) values('10001','" + err + "', 'jmimy','" + timeLog + "')" ;
        PreparedStatement pst = conn.prepareStatement(str);
        pst.execute(str);
    }
        else
            {System.out.println("Failed to make connection" + SQLException.class.toString());}
    }

}