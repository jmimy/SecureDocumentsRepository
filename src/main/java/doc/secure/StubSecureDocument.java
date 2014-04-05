package doc.secure;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jeromy on 4/4/14.
 */
public class StubSecureDocument {
    private String docCode;
    private Date createDate;
    private Date modifiedDate;
    private String subject;
    private String author;
    private String information;
    private Boolean accessControl;
    private int size;

    public StubSecureDocument()
    {
        this.docCode = "1001";
        this.createDate = Calendar.getInstance().getTime();
        this.modifiedDate = Calendar.getInstance().getTime();
        this.subject = "The reptiles";
        this.information = "The Wild Animals";
        this.author="jmimy";
        this.accessControl=true;
        this.size = 12;
    }
}
