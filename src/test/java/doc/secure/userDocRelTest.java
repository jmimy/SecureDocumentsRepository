package doc.secure;

        import org.junit.Before;
        import org.junit.Test;

        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertFalse;
        import static org.junit.Assert.assertNotEquals;
/**
 * Created by Jeromy on 3/1/14.
 */
public class userDocRelTest {
    private String username;
    private String docCode;
    private char rights;
   // private char rightstest;

    @Before
    public void setup() {
    username = "jmimy";
    docCode = "10001";
    rights = 'x';
    }

   @Test
   public void testUserDocRelConstruction() {
       UserDocRel userDocRel = new UserDocRel(username,docCode);

       assertEquals("Check username", userDocRel.getUsername(),username);
       assertEquals("Check docCode", userDocRel.getDocCode(),docCode);

   }

    @Test
    public void testUserDocRelConstructionFalse() {
        UserDocRel userDocRel = new UserDocRel(username, docCode);

        String usernametest = "jpierre";
        String docCodetest = "10002";
        userDocRel.setUsername(usernametest);
        userDocRel.setDocCode(docCodetest);

        assertNotEquals("Incorrect user", userDocRel.getUsername(), username);
        assertNotEquals("Incorrect docCode", userDocRel.getDocCode(),docCode);
    }

    @Test
    public void testMethodSetGet() {
        UserDocRel userDocRel = new UserDocRel(username, docCode);

        userDocRel.setRights(rights);
        assertEquals("Check rights", userDocRel.getRights(), rights);
    }

    @Test
    public void testMethodSetGetFalse() {
        UserDocRel userDocRel = new UserDocRel(username, docCode);

        userDocRel.setRights(rights);
        rights = 'o';
        assertNotEquals("Check rights", userDocRel.getRights(), rights);
    }


}

