/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 4/02/14.
 * This the Junit Test for class DocumentFactory.
 * It test class factory. No constructor for this class.
 *********************************************************************************************************/
package doc.secure;

import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DocumentFactoryTest {
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
        author = "jmimy";
        accessControl = false;
    }

    @Test
    public void testDocumentFactoryConstruction() {

        Documents secureDocument = DocumentFactory.createDocuments(docCode, author, accessControl);

        assertEquals("Check doccode", secureDocument.getDocCode(),docCode);
        assertEquals("Check author", secureDocument.getAuthor(), author);
        assertEquals("Check accesscontrol",secureDocument.getAccessControl(),accessControl);
    }

    @Test
    public void testDocumentFactoryConstructionFalse() {
        Documents secureDocument = DocumentFactory.createDocuments(docCode, author, accessControl);

        docCode = "10002";
        author = "admin";
        accessControl = true;
        assertNotEquals("doccode not equal", secureDocument.getDocCode(),docCode);
        assertNotEquals("author not equal", secureDocument.getAuthor(),author);
        assertNotEquals("accesscontrol not equal",secureDocument.getAccessControl(),accessControl);
    }
}
