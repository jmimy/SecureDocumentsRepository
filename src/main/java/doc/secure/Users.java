/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 2/28/14.
 * This is the class Users definition  that represent all users of the application.
 * All the getters and setters are defined.
 *********************************************************************************************************/
package doc.secure;

import doc.secure.swing.MessageFrame;
import org.hsqldb.jdbc.JDBCDriver;
import java.sql.*;

public class Users implements Profile {
    private String username;
    private String password;
    private String name;
    private String homePath;
    private int active;
    private String createdBy;
    private String type;
    String sql;
    ResultSet rs = null;
    Statement statement = null;
    Connection connection = null;
    String URL;
    String dbusr = "SA";
    String dbpassword = "SA";

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /* Username getter
   * @param
   */
    public String getUsername(){return username;}
    /* Username setter
   * @param  username
   */
    public void setUsername(String username) {this.username = username;}
    /* Password getter
   * @param
   */
    public String getPassword() {return password;}
    /* Password setter
   * @param  password
   */
    public void setPassword(String password) {this.password = password;}
    /* Name getter
   * @param  Name of user
   */
    public String getName() {return name;}
    /* Name setter
   * @param  Name of the user.
   */
    public void setName(String name) {this.name = name;}
    /* Home path getter
   * @param
   */
    public  String getHomePath() {return homePath;}
    /* Author setter
   * @param  Author of the doc.
   */
    public  void setHomePath(String homepath) {this.homePath = homePath;}
    /* Active code getter
   * @param
   */
    public int getActive() {return active;}
    /* Active code setter
   * @param  active code
   */
    public void setActive(int active) {this.active = active;}
    /* Created by getter
   * @param
   */
    public String getCreatedBy() {return createdBy;}
    /* Createby setter
   * @param  user that creates the record
   */
    public  void setCreatedBy( String createdBy) {this.createdBy = createdBy;}
    /* Tyoe getter
   * @param
   */
    public String getType() {return type;}
    /* Type of user setter
   * @param  user type
   */
    public void setType(String type) {this.type = type;}

    public Boolean findByUsername() throws SQLException
    {

        Boolean result = false;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        }
        catch (ClassNotFoundException c)
        {
            MessageFrame message = new MessageFrame( "#5" + c.getMessage());
        }

        try
        {
            URL = "jdbc:hsqldb:file:lib/data/testdb;ifexists=true";
            connection = DriverManager.getConnection(URL, dbusr, dbpassword);
            sql = "select * from users where username= '" + username + "' and password = '" + password + "'";
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            if (rs.next())
            {
                result = true;
            }

        }
        catch (SQLException e)
        {
            //Handle CommunicationsException
            MessageFrame messsage = new MessageFrame(e.getMessage());

        }

        return result;
    }
}
