package doc.secure;

/******************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: on 3/6/14.
 * This is an interface for class Users
 * All the getters and setters are declared for the methods to be defined in Users class
 *****************************************************************************************/
public interface Profile {

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);

    public String getName();

    public void setName(String name);

    public  String getHomePath();

    public  void setHomePath(String homepath);

    public int getActive();

    public void setActive(int active);

    public String getCreatedBy();

    public  void setCreatedBy( String createdBy);

    public String getType();

    public void setType(String type);
}
