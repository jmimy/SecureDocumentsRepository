/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 2/28/14.
 * This is the class Users definition  that represent all users of the application.
 * All the getters and setters are defined.
 *********************************************************************************************************/
package doc.secure;

public class Users implements Profile {
    private String username;
    private String password;
    private String name;
    private String homePath;
    private int active;
    private String createdBy;
    private String type;

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
}
