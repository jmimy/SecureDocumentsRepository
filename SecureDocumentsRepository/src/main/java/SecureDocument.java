package main.java;


import javax.swing.text.Document;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Administrator on 2/19/14.
 */


@XmlRootElement
public class SecureDocument{

    private String docCode;
    private Date createDate;
    private Date modifiedDate;
    private String subject;
    private String path;
    private  String author;
    private Boolean accessControl;
    private int size;

    private SecureDocument(String docCode, Date createDate, String subject,String author, Boolean accessControl, int size) {
        this.docCode =docCode;
        this.createDate = createDate;
        this.subject = subject;
        this.author = author;
        this.accessControl = accessControl;
        this.size = size;
    }

    public String getDocCode () { return docCode;}

    public void setDocCode(String docCode) {this.docCode = docCode; }

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

}
