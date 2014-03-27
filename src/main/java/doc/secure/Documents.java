/***************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 3/6/14.
 * This is an interface for class SecureDocument
 * All the getters and setters are defined for the fields to be defined in Class SecureDocument
 *************************************************************************************************/
package doc.secure;

import java.util.Date;

public interface Documents {

    public String getDocCode();

    public void setDocCode(String docCode);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public String getAuthor();

    public void setAuthor(String author);

    public Date getModifiedDate();

    public void setModifiedDate(Date modifiedDate);

    public String getSubject();

    public void setSubject(String Subject);

    public Boolean getAccessControl();

    public void setAccessControl(Boolean accessControl);

    public String getInformation();

    public void setInformation(String information);

    public int getSize();

    public void setSize(int size);
}