package main.java;

/**
 * Created by Jeromy on 2/24/14.
 */

public class Users {
    private String username;
    private String password;
    private String name;
    private String homePath;
    private Boolean active;
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

    public Boolean getActive() {return active;}

    public void setActive(Boolean active) {this.active = active;}

    public String getCreatedBy() {return createdBy;}

    public  void setCreatedBy( String createdBy) {this.createdBy = createdBy;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

}
