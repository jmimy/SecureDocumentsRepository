/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 3/4/14.
 * This is the class definition all Users database access. .
 * All the methods necessary for an instance of Users to read, find by user, find user list, update or create
 * or delete user are defined.
 *********************************************************************************************************/
package doc.secure;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class UsersDAO {
    private static UsersDAO instance = new UsersDAO();

    public static UsersDAO getInstance() {
        return instance;
    }


    /*Method to set an object user fields from a record of table Users. It accepts a resultset and sets the field values
     * an user object. The resultset has the following columns:username, password, type, name, active. */
    private Users read(ResultSet rs) throws SQLException
    {
        String username = rs.getString("username");
        String password = rs.getString("password");
        String type = rs.getString("type");
        String name = rs.getString("name");
        int active = rs.getInt("active");
        Users user = UserFactory.CreateUser(username,password);
        user.setUsername(username);
        user.setPassword(password);
        user.setType(type);
        user.setName(name);
        user.setActive(active);
        return user;
    }

    /* Method to retrieve a user from the table User */
    public Boolean findByUsername(Users users) throws SQLException
    {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection connection = null;
        String URL;
        String sql;
        Boolean result = true;
        try
        {
            URL = "jdbc:mysql://localhost:3306/securedocsrep";
            connection = DriverManager.getConnection(URL, "mysql", "mysql123");
            sql = "select * from users where username=? and password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, users.getUsername());
            statement.setString(2, users.getPassword());
            rs = statement.executeQuery();
            if (!rs.next())
            {
                result = false;
            }
            //result = true;
        }
        catch (SQLException e)
        {
            //Handle CommunicationsException
            if (e.getSQLState() == "08001"){
                Message messsage = new Message("Your database server is not running.");
            }
            try {
                String timeLog = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                String err = connection.toString();
                sql = "insert into logstbl(logCode, errMessage,user,createdate) values('10001','" + err + "', 'jmimy','" + timeLog + "')" ;
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.execute(sql);
            }
            catch (SQLException elog)
            {
                Message message = new Message(elog.getMessage());
            }
            finally
            {
                connection.close();
            }

        }
        return result;
    }



    /* Method to retrieve of users record from Users table */
    public List<Users> findAll() throws SQLException
    {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection connection = null;
        String URL;
        LinkedList<Users> user = new LinkedList<Users>();
        String sql;

        try
        {
            URL = "jdbc:mysql://localhost:3306/securedocsrep";
            connection = DriverManager.getConnection(URL, "mysql", "mysql123");
            sql = "select * from user order by username";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next())
            {
                Users users = read(rs);
                user.add(users);
            }

        }
        catch (SQLException e)
        {
            //Handle CommunicationsException
            if (e.getSQLState() == "08001"){
                Message messsage = new Message("Your database server is not running.");
            }
            try {
                String timeLog = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                String err = connection.toString();
                sql = "insert into logstbl(logCode, errMessage,user,createdate) values('10001','" + err + "', 'jmimy','" + timeLog + "')" ;
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.execute(sql);
            }
            catch (SQLException elog)
            {
                Message message = new Message(elog.getMessage());
            }
        }
        finally
        {
            connection.close();
        }
    return user;
    }

    /* Method to update a user record in Users table  */
    public void update(Users user) throws SQLException
    {
        PreparedStatement statement = null;
        Connection connection = null;
        String URL;
        try
        {
            URL = "jdbc:mysql://localhost:3306/securedocsrep";
            connection = DriverManager.getConnection(URL, "mysql", "mysql123");
            String sql = "update user set password=? and active = ?  where username=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getPassword());
            statement.setInt(2, user.getActive());
            statement.setString(3, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            connection.close();
        }
    }

    /* Method to create a new user record in Users table from an existing object user */
    public void create(Users user) throws SQLException
    {
        PreparedStatement statement = null;
        Connection connection = null;
        String URL;
        try
        {
            URL = "jdbc:mysql://localhost:3306/securedocsrep";
            connection = DriverManager.getConnection(URL, "mysql", "mysql123");
            String sql = "insert into user " + "(username, password) "
                    + "values ('" + user.getUsername() +"','" + user.getPassword() + "','" + user.getType() + "','" + user.getCreatedBy() + "')";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            connection.close();
        }
    }

    /* Method to delete a user */
    public void delete(Users user)
    {
        PreparedStatement statement = null;
        Connection connection = null;
        String URL;

        try
        {
            URL = "jdbc:mysql://localhost:3306/securedocsrep";
            connection = DriverManager.getConnection(URL, "mysql", "mysql123");
            String sql = "delete from user where username='" + user.getUsername() + "'";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}