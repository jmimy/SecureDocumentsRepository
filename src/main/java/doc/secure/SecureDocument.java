package doc.secure;


import javax.swing.text.Document;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Administrator on 2/19/14.
 */


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
        this.createDate = createDate;
        this.subject = subject;
        this.author = author;
        this.accessControl = accessControl;

    }
    public String getDocCode() {return docCode;}

    public void setDocCode(String docCode) {this.docCode=docCode;}

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAuthor() {return author;}

    public void setAuthor(String author) { this.author = author;}

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String Subject) {
        this.subject = Subject;
    }

    public Boolean getAccessControl() {
        return accessControl;
    }

    public void setAccessControl(Boolean accessControl) {
        this.accessControl = accessControl;
    }

    public String getInformation(){return information; }

    public void setInformation(String information){this.information = information;}

    public int getSize() {return size;}

    public void setSize(int size) {this.size = size;}

}
