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

    public String getUsername(){return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public  String getHomePath() {return homePath;}

    public  void setHomePath(String homepath) {this.homePath = homePath;}

    public int getActive() {return active;}

    public void setActive(int active) {this.active = active;}

    public String getCreatedBy() {return createdBy;}

    public  void setCreatedBy( String createdBy) {this.createdBy = createdBy;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

}
