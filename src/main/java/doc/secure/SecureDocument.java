/******************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 2/19/14.
 * This is the definition of class SecureDocument which implement the interface Document. It represents
 * any document that is saved in this repository.
 ******************************************************************************************************/
package doc.secure;

import java.util.Date;

public class SecureDocument implements Documents{
    private String docCode;
    private Date createDate;
    private Date modifiedDate;
    private String subject;
    private String author;
    private String information;
    private Boolean accessControl;
    private int size;

    public SecureDocument(String docCode,String author, Boolean accessControl) {
        this.docCode = docCode;
        this.author = author;
        this.accessControl = accessControl;

    }
    /* DocCode getter
    * @param
    */
    public String getDocCode() {return docCode;}
    /* DocCode setter
    * @param  Document code
    */
    public void setDocCode(String docCode) {this.docCode=docCode;}
    /* Creation Date getter
    * @param
    */
    public Date getCreateDate() {
        return createDate;
    }
    /* Creation Date setter
    * @param    Date of creation of the Doc.
    */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    /* Author getter
    * @param
    */
    public String getAuthor() {return author;}
    /* Author setter
    * @param  Author of the doc.
    */
    public void setAuthor(String author) { this.author = author;}
    /* Modification Date getter
    * @param
    */
    public Date getModifiedDate() {
        return modifiedDate;
    }
    /* Modification Date setter
    * @param    Last Date of Modification of the doc
    */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    /* Subject getter
    * @param
    */
    public String getSubject() {
        return subject;
    }
    /* Subject setter
    * @param    Subject of the doc.
    */
    public void setSubject(String Subject) {
        this.subject = Subject;
    }
    /* Access control code getter
    * @param
    */
    public Boolean getAccessControl() {
        return accessControl;
    }
    /* Access Control code setter
    * @param    Access Control Code.
    */
    public void setAccessControl(Boolean accessControl) {
        this.accessControl = accessControl;
    }
    /* Information getter
    * @param
    */
    public String getInformation(){return information; }
    /* information setter
    * @param    Information of the doc.
    */
    public void setInformation(String information){this.information = information;}
    /* Size getter
    * @param
    */
    public int getSize() {return size;}
    /* Size setter
    * @param    Size of the file on disk.
    */
    public void setSize(int size) {this.size = size;}

}
