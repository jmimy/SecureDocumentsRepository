/*******************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: on 3/7/14
 * This class defines a method factory to instantiate SecureDocument object.
 * @param
 ******************************************************************************************/
package doc.secure;

public class DocumentFactory {
    public static SecureDocument createDocuments(String docCode,String author,Boolean accessCode) {
        return new SecureDocument(docCode, author, accessCode);
    }
}
