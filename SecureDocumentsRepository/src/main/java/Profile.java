package main.java;

/**
 * Created by Jeromy on 3/6/14.
 */
public interface Profile {

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);

    public String getName();

    public void setName(String name);

    public  String getHomePath();

    public  void setHomePath(String homepath);

    public Boolean getActive();

    public void setActive(Boolean active);

    public String getCreatedBy();

    public  void setCreatedBy( String createdBy);

    public String getType();

    public void setType(String type);
}
