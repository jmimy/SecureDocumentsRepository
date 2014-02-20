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

    @Test
    public void XMLParseTest() throws ParseException {


        /*Set Values*/
        SecureDocument d = new SecureDocument();
        String creationDate = "December 12, 2013";
        Date createDate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(creationDate);
        d.setCreateDate(createDate);
        String modificationDate = "December 12, 2013";
        Date modifyDate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(modificationDate);
        d.setModifiedDate(modifyDate);
        d.setUsername("nschell");
        d.setSubject("Confidential Info");
        d.setInformation("Jack Bauer on his way to England, thwarting a terrorist attack for 24 Live Another Day");
        d.setAccessControl(false);


        /*Expected values */
        String creationDateExpected = "December 12, 2013";
        Date createDateExpected = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(creationDateExpected);
        String modificationDateExpected = "December 12, 2013";
        Date modifyDateExpected = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(modificationDateExpected);
        String usernameExpected = "nschell";
        String subjectExpected = "Confidential Info";
        String documentExpected = "Jack Bauer on his way to England, thwarting a terrorist attack for 24 Live Another Day";
        Boolean accesscontrolExpected = false;

        XMLParse.objectToXML(d, "C:\\test\\test.xml");
        SecureDocument testDoc = XMLParse.XMLToObject("C:\\test\\test.xml");

        assertEquals(createDateExpected, testDoc.getCreateDate());
        assertEquals(modifyDateExpected, testDoc.getCreateDate());
        assertEquals(usernameExpected, testDoc.getUsername());
        assertEquals(subjectExpected, testDoc.getSubject());
        assertEquals(documentExpected, testDoc.getInformation());
        assertEquals(accesscontrolExpected, testDoc.getAccessControl());
    }
}
