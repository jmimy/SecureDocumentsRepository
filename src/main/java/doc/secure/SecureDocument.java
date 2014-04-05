/******************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 2/19/14.
 * This is the definition of class SecureDocument which implement the interface Document. It represents
 * any document that is saved in this repository.
 ******************************************************************************************************/
package doc.secure;

import doc.secure.swing.MessageFrame;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;

public class SecureDocument implements Documents{
    private String docCode;
    private Date createDate;
    private Date modifiedDate;
    private String subject;
    private String author;
    private String information;
    private Boolean accessControl;
    private int size;
    private static Connection connection = null;
    private static ResultSet rs = null;
    private static Statement statement = null;
    private static String username = "SA";
    private static String password = "SA";
    private static String URL = "jdbc:hsqldb:file:lib/data/testdb;ifexists=true";
    private static String sql;

    public SecureDocument(String docCode,String author, Boolean accessControl) {
        this.docCode = docCode;
        this.author = author;
        this.accessControl = accessControl;

    }
    /* DocCode getter
    * @param
    */
    public String getDocCode() {return docCode;}
    /* DocCode setter
    * @param  Document code
    */
    public void setDocCode(String docCode) {this.docCode=docCode;}
    /* Creation Date getter
    * @param
    */
    public Date getCreateDate() {
        return createDate;
    }
    /* Creation Date setter
    * @param    Date of creation of the Doc.
    */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    /* Author getter
    * @param
    */
    public String getAuthor() {return author;}
    /* Author setter
    * @param  Author of the doc.
    */
    public void setAuthor(String author) { this.author = author;}
    /* Modification Date getter
    * @param
    */
    public Date getModifiedDate() {
        return modifiedDate;
    }
    /* Modification Date setter
    * @param    Last Date of Modification of the doc
    */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    /* Subject getter
    * @param
    */
    public String getSubject() {
        return subject;
    }
    /* Subject setter
    * @param    Subject of the doc.
    */
    public void setSubject(String Subject) {
        this.subject = Subject;
    }
    /* Access control code getter
    * @param
    */
    public Boolean getAccessControl() {
        return accessControl;
    }
    /* Access Control code setter
    * @param    Access Control Code.
    */
    public void setAccessControl(Boolean accessControl) {
        this.accessControl = accessControl;
    }
    /* Information getter
    * @param
    */
    public String getInformation(){return information; }
    /* information setter
    * @param    Information of the doc.
    */
    public void setInformation(String information){this.information = information;}
    /* Size getter
    * @param
    */
    public int getSize() {return size;}
    /* Size setter
    * @param    Size of the file on disk.
    */
    public void setSize(int size) {this.size = size;}



    /* This method updates a document record.
    * @param  a SecureDocument instance
    * @throws SQLException if connection failed.
    */
    public static void update(SecureDocument secureDocument)
    {
        try
        {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        }
        catch (ClassNotFoundException c)
        {
            MessageFrame message = new MessageFrame( "#5" + c.getMessage());
        }

        try
        {
            String sql = "update securedoctbl set subject='" +secureDocument.getSubject() + "' where docCode= '" + secureDocument.getDocCode() + "'";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
            MessageFrame message = new MessageFrame("Document updated successfully.");
        }
        catch (SQLException e)
        {
            //Handle CommunicationsException
            if (e.getSQLState() == "08001"){
                MessageFrame messsage = new MessageFrame("Your database server is not running.");
            }
        } finally
        {
            try {
                connection.close();
            }
            catch (SQLException econ) {
                MessageFrame messsage = new MessageFrame(econ.getMessage());
            }
        }
    }

    /* Method to insert a new document record in securedocstbl table from an instance of SecureDocument field values.
    * @param a instance of a SecureDocument
    * @throws SQLException if connection failed
    */
    public static void add(SecureDocument secureDocument)
    {
        String dateFormat1;
        String dateFormat2;
        int aBoolToInt;
        try
        {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        }
        catch (ClassNotFoundException c)
        {
            MessageFrame message = new MessageFrame( "#5" + c.getMessage());
        }

        try
        {
            dateFormat1 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(secureDocument.getCreateDate());   //Date type conversion
            dateFormat2 = new  SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(secureDocument.getModifiedDate()); //Date type conversion
            Boolean accessControl = secureDocument.getAccessControl();
            aBoolToInt = ( accessControl == true) ? 1: 0; // Boolean type convert to int(true=1,false=0)

            connection = DriverManager.getConnection(URL, username, password);

            sql = "insert into securedoctbl " + "(docCode, createDate, modifiedDate, subject, author, information, accessControl, size ) "
                    + "values ('" + secureDocument.getDocCode() +"','" + dateFormat1 + "','" + dateFormat2 + "','" + secureDocument.getSubject() + "','"
                    + secureDocument.getAuthor() + "','" + secureDocument.getInformation() + "','"+ aBoolToInt +  "',"
                    + secureDocument.getSize() + ")";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
            MessageFrame message = new MessageFrame("Document created successfully.");
        }
        catch (SQLException e)
        {
            //Handle CommunicationsException
            if (e.getSQLState() == "08001"){
                MessageFrame messsage = new MessageFrame("Your database server is not running.");
            }
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException econ) {
                MessageFrame messsage = new MessageFrame(econ.getMessage());
            }

        }
    }

    /* Method to delete an existing document record in securedocstbl table.
    * @param   an instance of a SecureDocument
    * @throws  SQLException if connection failed
    */
    public static void delete(SecureDocument secureDocument)
    {
        try
        {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        }
        catch (ClassNotFoundException c)
        {
            MessageFrame message = new MessageFrame( "#5" + c.getMessage());
        }

        try
        {
            if (connection.isClosed())
            {connection = DriverManager.getConnection(URL, username, password);}

            sql = "delete from securedoctbl where docCode='" + secureDocument.getDocCode() + "'";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
            MessageFrame message = new MessageFrame("Document deleted successfully.");
        } catch (SQLException e)
        {
            //Handle CommunicationsException
            if (e.getSQLState() == "08001"){
                MessageFrame messsage = new MessageFrame("Your database server is not running.");
            }
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException econ) {
                MessageFrame messsage = new MessageFrame(econ.getMessage());
            }

        }
    }


}
