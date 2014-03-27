/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 2/28/14.
 * This is the definition of the class that establishes the relationship between SecureDocument and Users
 * objects.
 * All the getters and setters are defined.
 *********************************************************************************************************/
package doc.secure;

public class UserDocRel {
    private String username;
    private String docCode;
    private String rights;

    public UserDocRel(String username, String docCode){
        this.username = username;
        this.docCode = docCode;
    }

    public String getUsername(){return username;}

    public void setUsername(String username){this.username=username;}

    public String getDocCode(){return docCode;}

    public void setDocCode(String docCode){this.docCode=docCode;}

    public String getRights(){return rights;}

    public void setRights(String rights){this.rights = rights;}

}
