package test.java;

import main.java.DocumentFactory;
import main.java.Documents;
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
    private  Date modifiedDate;


    @Before
    public void setup() {
        docCode = "10001";
        createDate = Calendar.getInstance().getTime();
        subject = "The collectibles";
        author = "jmimy";
        accessControl = false;
        modifiedDate = Calendar.getInstance().getTime();
        information = "Backpack School";
        size = 8;
    }

    @Test
    public void testSecureDocumentConstruction() {
        Documents secureDocument;
        secureDocument = new SecureDocument(docCode,author,accessControl);

        assertEquals("Check doccode", secureDocument.getDocCode(),docCode);
        assertEquals("Check author", secureDocument.getAuthor(), author);
        assertEquals("Check accesscontrol",secureDocument.getAccessControl(),accessControl);
    }

    @Test
    public void testSecureDocumentConstructionFalse() {
        SecureDocument secureDocument = new SecureDocument(docCode,author,accessControl);

        docCode = "10002";
        author = "admin";
        accessControl = true;
        assertNotEquals("doccode not equal", secureDocument.getDocCode(),docCode);
        assertNotEquals("author not equal", secureDocument.getAuthor(),author);
        assertNotEquals("accesscontrol not equal",secureDocument.getAccessControl(),accessControl);
    }

    @Test
    public void testMethodSetGet() {
        SecureDocument secureDocument = new SecureDocument(docCode,author,accessControl);

        secureDocument.setCreateDate(createDate);
        secureDocument.setSubject(subject);
        secureDocument.setInformation(information);
        secureDocument.setSize(size);
        secureDocument.setModifiedDate(modifiedDate);

        assertEquals("Check createdate", secureDocument.getCreateDate(), createDate);
        assertEquals("Check subject",secureDocument.getSubject(),subject);
        assertEquals("Check information",secureDocument.getInformation(),information);
        assertEquals("Check size",secureDocument.getSize(),size);
        assertEquals("Check modified date", secureDocument.getModifiedDate(),modifiedDate);
    }

    @Test
    public void testMedthodSetGetFalse() {
        SecureDocument secureDocument = new SecureDocument(docCode,author,accessControl);
        Date testDate;

        secureDocument.setCreateDate(createDate);
        secureDocument.setSubject(subject);
        secureDocument.setInformation(information);
        secureDocument.setSize(size);
        secureDocument.setModifiedDate(modifiedDate);

        Calendar now = Calendar.getInstance();
        now.set(Calendar.YEAR, 2013);
        now.set(Calendar.MONTH, 10);
        now.set(Calendar.DAY_OF_MONTH, 1);
        testDate = now.getTime();

        createDate= testDate;
        modifiedDate = testDate;
        subject="The reptiles";
        information = "Pack College Collection";
        size = 0;

        assertNotEquals("Not the createdate",secureDocument.getCreateDate(),createDate);
        assertNotEquals("Not the modifieddate",secureDocument.getModifiedDate(),modifiedDate);
        assertNotEquals("Not the same subject",secureDocument.getSubject(),subject);
        assertNotEquals("Not the same information",secureDocument.getInformation(),information);
        assertNotEquals("Not the same size",secureDocument.getSize(),size);

    }
}
