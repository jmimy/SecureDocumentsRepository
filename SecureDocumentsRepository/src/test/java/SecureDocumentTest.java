package test.java;

import main.java.SecureDocument;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
/**
 * Created by Jeromy on 3/1/14.
 */
public class SecureDocumentTest {
    private String docCode;
    private Date createDate;
    private String subject;
    private String author;
    private String information;
    private Boolean accessControl;
    private int size;


    @Before
    public void setup() {
        docCode = "10001";
        createDate = Calendar.getInstance().getTime();
        subject = "The collectibles";
        author = "jmimy";
        accessControl = false;


    }

    @Test
    public void testSecureDocumentConstruction() {
        SecureDocument secureDocument = new SecureDocument(createDate,docCode,subject,author,accessControl);

        assertEquals("Check createdate",secureDocument.getCreateDate(),createDate);
        assertEquals("Check doccode", secureDocument.getDocCode(),docCode);
        assertEquals("Check subject",secureDocument.getSubject(),subject);
        assertEquals("Check author",secureDocument.getAuthor(),author);
        assertEquals("Check accesscontrol",secureDocument.getAccessControl(),accessControl);
    }

    @Test
    public void testSecureDocumentConstructionFalse() {
        SecureDocument secureDocument = new SecureDocument(createDate,docCode,subject,author,accessControl);

        docCode = "10002";
        subject = "Illustrated Sports";
        assertNotEquals("doccode not equal", secureDocument.getDocCode(),docCode);
        assertNotEquals("subject not equal", secureDocument.getSubject(),subject);
    }
}
