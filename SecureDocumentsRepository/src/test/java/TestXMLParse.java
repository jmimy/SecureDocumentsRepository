package test.java;

import main.java.SecureDocument;
import main.java.XMLParse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Administrator on 2/19/14.
 */


public class TestXMLParse {

    private Date createDate;
    private Date modifyDate;
    private String username;
    private String subject;
    private String document;
    private Boolean accesscontrol;

    @Before
    public void setup() throws ParseException {

        String creationDate = "December 12, 2013";
        Date createDate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(creationDate);
        String modificationDate = "December 12, 2013";
        Date modifyDate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(modificationDate);
        String username = "nschell";
        String subject = "Confidential Info";
        String document = "Jack Bauer on his way to England, thwarting a terrorist attack for 24 Live Another Day";
        Boolean accesscontrol = false;
    }

    @Test
    public void XMLParseTest() {

    SecureDocument secDoc = new SecureDocument(createDate, username, modifyDate, subject, document, accesscontrol);
    XMLParse.objectToXML(secDoc, "C:\\test\\test.xml");
    SecureDocument testDoc = XMLParse.XMLToObject("C:\\test\\test.xml");

        assertEquals(createDate, testDoc.getCreateDate());
        assertEquals(modifyDate, testDoc.getCreateDate());
        assertEquals(username, testDoc.getUsername());
        assertEquals(subject, testDoc.getSubject());
        assertEquals(document, testDoc.getInformation());
        assertEquals(accesscontrol, testDoc.getAccessControl());
    }
}
