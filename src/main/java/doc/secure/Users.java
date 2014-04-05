/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 2/28/14.
 * This is the class Users definition  that represent all users of the application.
 * All the getters and setters are defined.
 *********************************************************************************************************/
package doc.secure;

import doc.secure.swing.MessageFrame;
import org.hsqldb.jdbc.JDBCDriver;
//xml file
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
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
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("lib/users.xml"));
            NodeList listOfUsers = doc.getElementsByTagName("user");

            for(int s=0; s<listOfUsers.getLength() ; s++){

                Node firstUserNode = listOfUsers.item(s);
                if(firstUserNode.getNodeType() == Node.ELEMENT_NODE){

                    Element firstUserElement = (Element)firstUserNode;

                    NodeList usernameList = firstUserElement.getElementsByTagName("username");
                    Element usernameElement = (Element)usernameList.item(0);
                    NodeList textUList = usernameElement.getChildNodes();

                    NodeList passwordList = firstUserElement.getElementsByTagName("password");
                    Element passwordElement = (Element)passwordList.item(0);
                    NodeList textPList = passwordElement.getChildNodes();

                    if (((Node)textUList.item(0)).getNodeValue().toString().contentEquals(username) && ((Node)textPList.item(0)).getNodeValue().contentEquals(password))
                    {
                        result = true;
                        break;
                    }

                }
            }//end of for loop with s var
        }
        catch (SAXParseException err)
        {
            MessageFrame messageFrame = new MessageFrame("Parsing error" + ", line "
                    + err.getLineNumber () + ", uri " + err.getSystemId ());
        }
        catch (SAXException e)
        {
            Exception x = e.getException ();
            MessageFrame messageFrame = new MessageFrame(((x == null) ? e : x).getMessage());

        }
        catch (Throwable t)
        {
           MessageFrame messageFrame = new MessageFrame(t.getMessage());
        }
        //retrurn boolean value
        return result;
    }//end of findByUsername
}
