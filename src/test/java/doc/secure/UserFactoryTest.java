/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 4/4/14.
 * This is the Junit test class for UserFactory class.
 * Factory method is tested. No constructor for this class.
 *********************************************************************************************************/
package doc.secure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;


public class UserFactoryTest {

    private String username;
    private String password;

    @Before
    public void setup() {
        username = "jmimy";
        password = "abcd1234";
    }

    @Test
    public void testUserFactoryConstruction(){
        Profile users = UserFactory.CreateUser(username, password);

        assertEquals("Check username", users.getUsername(),username);
        assertEquals("Check password", users.getPassword(),password);
    }

    @Test
    public void testUserFactoryConstructionFalse() {
        Profile users = UserFactory.CreateUser(username, password);

        username = "jpierre";
        password = "dcba4321";
        assertNotEquals("Incorrect user", users.getUsername(), username);
        assertNotEquals("Incorrect password", users.getPassword(), password);
    }
}