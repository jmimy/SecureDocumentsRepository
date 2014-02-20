package main.java;

import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;

/**
 * Created by Administrator on 2/19/14.
 */
public class XMLParse {

    public XMLParse() {

    }

    public static void objectToXML(SecureDocument s, String filePath) throws FileNotFoundException {
        try {
            JAXBContext context = JAXBContext.newInstance(SecureDocument.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            QName qName = new QName("main.java", "s");
            JAXBElement<SecureDocument> root = new JAXBElement<SecureDocument>(qName, SecureDocument.class, s);
            m.marshal(s, new FileOutputStream(filePath));

        } catch (JAXBException e) {
            e.printStackTrace();

        }
    }

    public static SecureDocument XMLToObject(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(SecureDocument.class);
            Unmarshaller un = context.createUnmarshaller();
            SecureDocument s = (SecureDocument) un.unmarshal(new File(filePath));
            return s;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
