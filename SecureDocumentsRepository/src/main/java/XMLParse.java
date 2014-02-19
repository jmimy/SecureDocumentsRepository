package main.java;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Administrator on 2/19/14.
 */
public class XMLParse {

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
