/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 3/14/14.
 * This is the class that defines Document database access methods.
 * Methods necessary to read, find by document code, list, update or create or delete a document.
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
    private Connection connection = null;
    private static String username = "mysql";
    private static String password = "mysql123";
    private static String URL = "jdbc:mysql://localhost:3306/securedocsrep";
    private static DocumentDAO instance = new DocumentDAO();

    public static DocumentDAO getInstance() {
        return instance;
    }

   /*This method reads a recordset, create an instance of SecureDocument class and set its field values.
    * @param  a resultset retrieved from table that contains  Document record
    * @throws SQLException if resultset reading failed.
    */
    private SecureDocument read(ResultSet rs)
    {
        try
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
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    /* This method search for a document record in table securedoctbl knowing its code key value.
    * @param code of the document
    * @throws SQLException if connection failed.
    */
    public SecureDocument findByDocCode(String docCode)
    {
        ResultSet rs = null;
        PreparedStatement statement = null;

        try
        {
            if (connection.isClosed())
                {connection = DriverManager.getConnection(URL, username, password);}

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
        finally
        {
            try {
                connection.close();
            }
            catch (SQLException econ) {
                Message messsage = new Message(econ.getMessage());
            }
        }

    }

    /* This method return all documents a logon user has access to according to records in userRelDoc table.
    * @param  user instance
    * @throws SQLException if connection failed.
    */
    public List<SecureDocument> findUserDocs(Users users)
    {
        ResultSet rs = null;
        PreparedStatement statement = null;
        LinkedList<SecureDocument> documents = new LinkedList<SecureDocument>();

        try
        {
            if (connection.isClosed())
                {connection = DriverManager.getConnection(URL, username, password);}

            String sql = "select * from securedoctbl where doccode in (select doccode from userDocRel where username = '" + users.getUsername() + "')" ;
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next())
            {
                SecureDocument secureDocument = read(rs);
                documents.add(secureDocument);
            }
            rs.close();
            connection.close();
           return documents;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            try {
                connection.close();
            }
            catch (SQLException econ) {
                Message messsage = new Message(econ.getMessage());
            }
        }

    }

    /* This method updates a document record.
    * @param  a SecureDocument instance
    * @throws SQLException if connection failed.
    */
    public static void update(SecureDocument secureDocument)
    {
        PreparedStatement statement = null;
        Connection connection = null;

        try
        {


            connection = DriverManager.getConnection(URL, username, password);
            String sql = "update securedoctbl set subject='" +secureDocument.getSubject() + "' where docCode= '" + secureDocument.getDocCode() + "'";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.close();
            Message message = new Message("Document updated successfully.");
        }
        catch (SQLException e)
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

    /* Method to insert a new document record in securedocstbl table from an instance of SecureDocument field values.
    * @param a instance of a SecureDocument
    * @throws SQLException if connection failed
    */
    public static void create(SecureDocument secureDocument)
    {
        PreparedStatement statement = null;
        Connection connection = null;
        String dateFormat1;
        String dateFormat2;
        int aBoolToInt;

        try
        {
            dateFormat1 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(secureDocument.getCreateDate());   //Date type conversion
            dateFormat2 = new  SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(secureDocument.getModifiedDate()); //Date type conversion
            Boolean accessControl = secureDocument.getAccessControl();
            aBoolToInt = ( accessControl == true) ? 1: 0; // Boolean type convert to int(true=1,false=0)


            connection = DriverManager.getConnection(URL, username, password);

            String sql = "insert into securedoctbl " + "(docCode, createDate, modifiedDate, subject, author, information, accessControl, size ) "
                    + "values ('" + secureDocument.getDocCode() +"','" + dateFormat1 + "','" + dateFormat2 + "','" + secureDocument.getSubject() + "','"
                    + secureDocument.getAuthor() + "','" + secureDocument.getInformation() + "','"+ aBoolToInt +  "',"
                    + secureDocument.getSize() + ")";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.close();
            Message message = new Message("Document created successfully.");
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


    /* Method to delete an existing document record in securedocstbl table.
    * @param   an instance of a SecureDocument
    * @throws  SQLException if connection failed
    */
    public static void delete(SecureDocument secureDocument)
    {
        PreparedStatement statement = null;
        Connection connection = null;

        try
        {
            if (connection.isClosed())
                {connection = DriverManager.getConnection(URL, username, password);}

            String sql = "delete from securedoctbl where docCode='" + secureDocument.getDocCode() + "'";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.close();
            Message message = new Message("Document deleted successfully.");
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

