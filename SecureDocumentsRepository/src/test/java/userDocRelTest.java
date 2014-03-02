package test.java;

        import main.java.UserDocRel;
        import main.java.Users;
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

    @Before
    public void setup() {
    username = "jmimy";
    docCode = "10001";
    rights = 'x';
    }

   @Test
   public void testUserDocRelConstruction() {
       UserDocRel userDocRel = new UserDocRel(username,docCode, rights);
       assertEquals("Check username", userDocRel.getUsername(),username);
       assertEquals("Check docCode", userDocRel.getDocCode(),docCode);
       assertEquals("Check rights",userDocRel.getRights(),rights);
   }

    @Test
    public void testUserDocRelConstructionFalse() {
        UserDocRel userDocRel = new UserDocRel(username, docCode,rights);

        username = "jpierre";
        docCode = "10002";
        rights = 'o';
        assertNotEquals("Incorrect user", userDocRel.getUsername(), username);
        assertNotEquals("Incorrect docCode", userDocRel.getDocCode(),docCode);
        assertNotEquals("Incorrect rights", userDocRel.getRights(), rights);
    }
}
