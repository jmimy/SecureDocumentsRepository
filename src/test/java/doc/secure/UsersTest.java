/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 3/4/14.
 * This is the Junit test class for Users class.
 * Class construction and all methods are tested .
 *********************************************************************************************************/
package doc.secure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;


public class UsersTest {

    private String username;
    private String password;
    private String name;
    private String homePath;
    private int active;
    private String createdBy;
    private String type;

    @Before
    public void setup() {
        username = "jmimy";
        password = "abcd1234";
        name = "Jean Mimy";
        homePath = "Personal//data";
        active = 1;
        createdBy = "admin";
        type = "user";
    }

    @Test
    public void testUsersConstruction(){
            Users users = new Users(username, password);

            assertEquals("Check username", users.getUsername(),username);
            assertEquals("Check password", users.getPassword(),password);
    }

    @Test
    public void testUsersConstructionFalse() {
        Users users = new Users(username, password);

        username = "jpierre";
        password = "dcba4321";
        assertNotEquals("Incorrect user", users.getUsername(), username);
        assertNotEquals("Incorrect password", users.getPassword(), password);
    }

    @Test
    public void testSetter() {
        Users users = new Users(username,password);

        users.setName(name);
        users.setActive(active);
        users.setCreatedBy(createdBy);
        users.setHomePath(homePath);
        users.setType(type);

        assertEquals("Checking Name",users.getName(),name);
        assertEquals("Checking Active",users.getActive(),active);
        assertEquals("Checking Created by",users.getCreatedBy(),createdBy);
        assertEquals("Checking Type",users.getType(),type);
    }

    @Test
    public void testSetterFalse() {
        Users users = new Users(username,password);

        users.setName(name);
        users.setActive(active);
        users.setCreatedBy(createdBy);
        users.setHomePath(homePath);
        users.setType(type);

        name = "Jean Pierre";
        homePath = "Personal//test";
        active = 0;
        createdBy = "user";
        type = "owner";

        assertNotEquals("Not the same name", users.getName(), name);
        assertNotEquals("Not the same homepath", users.getHomePath(), homePath);
        assertNotEquals("Not he same active",users.getActive(),active);
        assertNotEquals("Not the same type",users.getType(),type);
        assertNotEquals("Not the same created ny", users.getCreatedBy(), createdBy);

    }
}
