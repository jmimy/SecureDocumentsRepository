/*******************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: on 3/7/14
 * This class is used for method factory to instantiate SecureDocument object.
 *
 ******************************************************************************************/
package doc.secure;

public class DocumentFactory {
    public static SecureDocument createDocuments(String docCode,String author,Boolean accessCode) {
        return new SecureDocument(docCode, author, accessCode);
    }
}
