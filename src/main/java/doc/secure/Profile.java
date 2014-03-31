/******************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: on 3/6/14.
 * This is an interface for class Users
 * All the getters and setters are declared for the methods to be defined in Users class
 *****************************************************************************************/
package doc.secure;

public interface Profile {
    /* username getter
    * @param
    */
    public String getUsername();
    /* username setter
    * @param username
    */
    public void setUsername(String username);
    /* Password getter
    * @param
    */
    public String getPassword();
    /* Password setter
    * @param  password related to the username
    */
    public void setPassword(String password);
    /* Name getter
    * @param
    */
    public String getName();
    /* Name getter
    * @param  Name of user
    */
    public void setName(String name);
    /* Homepath getter
    * @param
    */
    public  String getHomePath();
    /* Home path setter
    * @param  home path of user
    */
    public  void setHomePath(String homepath);
    /* Active code getter
    * @param
    */
    public int getActive();
    /* Active code setter
    * @param  active code value
    */
    public void setActive(int active);
    /* createdBy getter
    * @param
    */
    public String getCreatedBy();
    /* Createdby setter
    * @param  user that creates this record
    */
    public  void setCreatedBy( String createdBy);
    /* Type getter
    * @param
    */
    public String getType();
    /* Type setter
    * @param  user type
    */
    public void setType(String type);
}
