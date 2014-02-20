package test.java;
import main.java.SecureDocument;
import main.java.XMLParse;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
/**
 * Created by Administrator on 2/20/14.
 */


public class TestAccessControl {

    public void AccessControlTest() throws ParseException, FileNotFoundException {

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

        SecureDocument e = new SecureDocument();
        String creationDatee = "December 12, 2013";
        Date createDatee = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(creationDatee);
        e.setCreateDate(createDatee);
        String modificationDatee = "December 12, 2013";
        Date modifyDatee = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(modificationDatee);
        e.setModifiedDate(modifyDatee);
        e.setUsername("nschell");
        e.setSubject("Confidential Info");
        e.setInformation("Jack Bauer on his way to England, thwarting a terrorist attack for 24 Live Another Day");
        e.setAccessControl(true);

        SecureDocument f = new SecureDocument();
        String creationDatef = "December 12, 2013";
        Date createDatef = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(creationDatef);
        f.setCreateDate(createDatef);
        String modificationDatef = "December 12, 2013";
        Date modifyDatef = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(modificationDatef);
        f.setModifiedDate(modifyDate);
        f.setUsername("nschell");
        f.setSubject("Confidential Info");
        f.setInformation("Jack Bauer on his way to England, thwarting a terrorist attack for 24 Live Another Day");
        f.setAccessControl(true);

        SecureDocument g = new SecureDocument();
        String creationDateg = "December 12, 2013";
        Date createDateg = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(creationDateg);
        g.setCreateDate(createDateg);
        String modificationDateg = "December 12, 2013";
        Date modifyDateg = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(modificationDateg);
        g.setModifiedDate(modifyDate);
        g.setUsername("nschell");
        g.setSubject("Confidential Info");
        g.setInformation("Jack Bauer on his way to England, thwarting a terrorist attack for 24 Live Another Day");
        g.setAccessControl(true);

        XMLParse.objectToXML(d, "c:\\test\\test1.xml");
        XMLParse.objectToXML(e, "c:\\test\\test2.xml");
        XMLParse.objectToXML(f, "c:\\test\\test3.xml");
        XMLParse.objectToXML(g, "c:\\test\\test4.xml");

    }

}
