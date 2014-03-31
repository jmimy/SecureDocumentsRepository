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
    /* username getter
    * @param
    */
    public String getUsername(){return username;}
    /* username setter
    * @param   username
    */
    public void setUsername(String username){this.username=username;}
    /* DocCode getter
    * @param
    */
    public String getDocCode(){return docCode;}
    /* DocCode setter
    * @param  a document code
    */
    public void setDocCode(String docCode){this.docCode=docCode;}
    /* Rights setter
    * @param  user rights for the doc
    */
    public String getRights(){return rights;}
    /* Rights setter
    * @param  user right code for the doc
    */
    public void setRights(String rights){this.rights = rights;}

}
