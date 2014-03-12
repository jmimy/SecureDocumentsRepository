package main.java;

/**
 * Created by Jeromy on 3/7/14.
 */
public class DocumentFactory {
    public static Documents CreateDocuments(String docCode,String author,Boolean accessCode) {
        return new SecureDocument(docCode, author, accessCode);
    }
}
