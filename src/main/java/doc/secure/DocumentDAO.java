/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 3/14/14.
 * This is the class definition all Document database access. .
 * All the methods necessary for an instance of SecureDocument to read, find by document code, find document
 * list, update or create or delete document are defined.
 *********************************************************************************************************/
package doc.secure;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DocumentDAO {
    private static DocumentDAO instance = new DocumentDAO();

    public static DocumentDAO getInstance() {
        return instance;
    }

    /*Method to set an object secureDocument fields from a record of table SecureDocument. It accepts a resultset and sets the field values
    * a secureDocument object. The resultset has the following column labels:docCode, subject, author, information, createdate, accessControl. */
    private SecureDocument read(ResultSet rs) throws SQLException
    {
        String docCode = rs.getString("docCode");
        String subject = rs.getString("subject");
        String author = rs.getString("author");
        String information = rs.getString("information");
        Date createDate = rs.getDate("createDate");
        Boolean accessControl = rs.getBoolean("accessControl");
        int size = rs.getInt("size") ;
        SecureDocument secureDocument = DocumentFactory.createDocuments(docCode, author, accessControl);
        secureDocument.setDocCode(docCode);
        secureDocument.setSubject(subject);
        secureDocument.setAuthor(author);
        secureDocument.setInformation(information);
        secureDocument.setCreateDate(createDate);
        return secureDocument;
    }

    private UserDocRel relRead(ResultSet resultSet) throws SQLException
    {
        String docCode = resultSet.getString("docCode");
        String username = resultSet.getString("username");
        String right = resultSet.getString("right");
        UserDocRel userDocRel = new UserDocRel(username,docCode);
        userDocRel.setDocCode(docCode);
        userDocRel.setUsername(username);
        userDocRel.setRights(right);
        return userDocRel;

    }

    /* Method to retrieve document from the table securedoctbl */
    public SecureDocument findByDocCode(String docCode)
    {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection connection = null;
        String URL;
        try
        {
            URL = "jdbc:mysql://localhost:3306/securedocsrep";
            connection = DriverManager.getConnection(URL, "mysql", "mysql123");
            String sql = "select * from securedoctbl where docCode= '" + docCode + "'";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            if (!rs.next())
            {
                return null;
            }
            return read(rs);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    /* Method to retrieve document record from securedocstbl table */
    public List<SecureDocument> findAll()
    {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection connection = null;
        String URL;
        LinkedList<SecureDocument> documents = new LinkedList<SecureDocument>();

        try
        {
            URL = "jdbc:mysql://localhost:3306/securedocsrep";
            connection = DriverManager.getConnection(URL, "mysql", "mysql123");
            String sql = "select * from securedoctbl order by docCode";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next())
            {
                SecureDocument secureDocument = read(rs);
                documents.add(secureDocument);
            }
            return documents;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {

        }
    }


    /* Method to retrieve document records from securedocstbl table for a given user */
    public List<SecureDocument> findUserDocs(Users users)
    {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection connection = null;
        String URL;
        LinkedList<SecureDocument> documents = new LinkedList<SecureDocument>();

        try
        {
            URL = "jdbc:mysql://localhost:3306/securedocsrep";
            connection = DriverManager.getConnection(URL, "mysql", "mysql123");
            String sql = "select * from securedoctbl where doccode in (select doccode from userDocRel where username = '" + users.getUsername() + "')" ;
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next())
            {
                SecureDocument secureDocument = read(rs);
                documents.add(secureDocument);
            }
           // return documents;
        }
        catch (SQLException e)
        {
            //Handle CommunicationsException
            if (e.getSQLState() == "08001"){
                Message messsage = new Message("Your database server is not running.");
            }
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException econ) {
                Message messsage = new Message(econ.getMessage());
            }
        }
     return documents;
    }

    /* Method to update a document record in securedocstbl table  */
    public void update(SecureDocument secureDocument)
    {
        PreparedStatement statement = null;
        Connection connection = null;
        String URL;
        try
        {
            URL = "jdbc:mysql://localhost:3306/securedocsrep";
            connection = DriverManager.getConnection(URL, "mysql", "mysql123");
            String sql = "update securedoctbl set subject='" +secureDocument.getSubject() + "' where docCode= '" + secureDocument.getDocCode() + "'";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            //Handle CommunicationsException
            if (e.getSQLState() == "08001"){
                Message messsage = new Message("Your database server is not running.");
            }
        } finally
        {
            try {
                connection.close();
            }
            catch (SQLException econ) {
                Message messsage = new Message(econ.getMessage());
            }
        }
    }

    /* Method to create a new document record in securedocstbl table from an existing object secureDocument */
    public void create(SecureDocument secureDocument)
    {
        PreparedStatement statement = null;
        Connection connection = null;
        String URL;
        String d1;
        String d2;
        int a;

        try
        {
            URL = "jdbc:mysql://localhost:3306/securedocsrep";
            d1 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(secureDocument.getCreateDate());   //Date type conversion
            d2 = new  SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(secureDocument.getModifiedDate()); //Date type conversion
            Boolean accessControl = secureDocument.getAccessControl();
            a = ( accessControl == true) ? 1: 0;                                                       // Boolean type convert to int(true=1,false=0)
            connection = DriverManager.getConnection(URL, "mysql", "mysql123");
            String sql = "insert into securedoctbl " + "(docCode, createDate, modifiedDate, subject, author, information, accessControl, size ) "
                    + "values ('" + secureDocument.getDocCode() +"','" + d1 + "','" + d2 + "','" + secureDocument.getSubject() + "','"
                    + secureDocument.getAuthor() + "','" + secureDocument.getInformation() + "','"+ a +  "',"
                    + secureDocument.getSize() + ")";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            //Handle CommunicationsException
            if (e.getSQLState() == "08001"){
                Message messsage = new Message("Your database server is not running.");
            }
        }

        finally {
            try {
                connection.close();
            }
            catch (SQLException econ) {
                Message messsage = new Message(econ.getMessage());
            }

        }
    }

    /* Method to delete a document */
    public void delete(SecureDocument secureDocument)
    {
        PreparedStatement statement = null;
        Connection connection = null;
        String URL;

        try
        {
            URL = "jdbc:mysql://localhost:3306/securedocsrep";
            connection = DriverManager.getConnection(URL, "mysql", "mysql123");
            String sql = "delete from securedoctbl where docCode='" + secureDocument.getDocCode() + "'";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            //Handle CommunicationsException
            if (e.getSQLState() == "08001"){
                Message messsage = new Message("Your database server is not running.");
            }
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException econ) {
                Message messsage = new Message(econ.getMessage());
            }

        }
    }
}

