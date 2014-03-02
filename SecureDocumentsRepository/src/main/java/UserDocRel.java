package main.java;

/**
 * Created by Jeromy on 2/26/14.
 */
public class UserDocRel {
    private String username;
    private String docCode;
    private char rights;

    public UserDocRel(String username, String docCode, char rights){
        this.username = username;
        this.docCode = docCode;
        this.rights = rights;
    }

    public String getUsername(){return username;}

    public void setUsername(String username){this.username=username;}

    public String getDocCode(){return docCode;}

    public void setDocCode(String docCode){this.username = username;}

    public char getRights(){return rights;}

    public void setRights(char rights){this.rights = rights;}

}
