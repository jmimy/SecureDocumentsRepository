/***************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 3/6/14.
 * This is an interface for SecureDocument class
 * All the getters and setters are defined for the fields to be defined in SecureDocument Class
 *************************************************************************************************/
package doc.secure;

import java.util.Date;

public interface Documents {
    /* DocCode getter
    * @param
    */
    public String getDocCode();
    /* DocCode setter
    * @param  a document code
    */
    public void setDocCode(String docCode);
    /* Creation Date getter
    * @param
    */
    public Date getCreateDate();
    /* Creation getter
    * @param  Creation date
    */
    public void setCreateDate(Date createDate);
    /* Author getter
    * @param
    */
    public String getAuthor();
    /* Author setter
    * @param   author
    */
    public void setAuthor(String author);
    /* Modification Date getter
    * @param
    */
    public Date getModifiedDate();
    /* Modification Date setter
    * @param   Modification Date
    */
    public void setModifiedDate(Date modifiedDate);
    /* Subject getter
    * @param
    */
    public String getSubject();
    /* Subject setter
   * @param   Subject
   */
    public void setSubject(String Subject);
    /* Access code getter
   * @param
   */
    public Boolean getAccessControl();
     /* Access Code setter
    * @param   access control code
    */
    public void setAccessControl(Boolean accessControl);
     /* Document information getter
    * @param
    */
    public String getInformation();
    /* Subject setter
   * @param   Information string about the document
   */
    public void setInformation(String information);
    /* File size getter
   * @param
   */
    public int getSize();
     /* File size setter
    * @param  Size of the file on disk
    */
    public void setSize(int size);
}