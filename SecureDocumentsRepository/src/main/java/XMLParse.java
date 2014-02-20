package main.java;

import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;

import javax.xml.bind.*;
import java.io.File;

/**
 * Created by Administrator on 2/19/14.
 */
public class XMLParse {

    public XMLParse() {

    }

    public static void objectToXML(SecureDocument s, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(SecureDocument.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(s, new File(filePath));
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
