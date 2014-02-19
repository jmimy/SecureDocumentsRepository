package main.java;

import java.util.Date;

/**
 * Created by Administrator on 2/19/14.
 */
public class SecureDocument {
    private Date createDate;
    private String username;
    private Date modifiedDate;
    private String Subject;
    private String Information;
    private Boolean accessControl;

     public SecureDocument(Date createDate, String username, Date modifiedDate, String Subject, String Information, Boolean accessControl) {
        this.createDate = createDate;
        this.username = username;
        this.modifiedDate = modifiedDate;
        this.Subject = Subject;
        this.Information = Information;
        this.accessControl = accessControl;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String Information) {
        this.Information = Information;
    }

    public Boolean getAccessControl() {
        return accessControl;
    }

    public void setAccessControl(Boolean accessControl) {
        this.accessControl = accessControl;
    }

}
