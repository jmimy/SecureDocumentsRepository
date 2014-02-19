package test.java;

import main.java.SecureDocument;
import main.java.XMLParse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2/19/14.
 */
public class TestXMLParse {
    String creationDate = "December 12, 2013";
    Date createDate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(creationDate);

    String modificationDate = "December 12, 2013";
    Date modifyDate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(modificationDate);

    SecureDocument secDoc = new SecureDocument(createDate, "nschell", modifyDate, "Confidential Info", "Jack Bauer on his way to England, thwarting a terrorist attack for 24 Live Another Day", false);
    XMLParse.objectToXML(secDoc, "C:\\test\test.xml");
    SecureDocument testDoc = XMLParse.XMLToObject("C:\\test\test.xml");



}
