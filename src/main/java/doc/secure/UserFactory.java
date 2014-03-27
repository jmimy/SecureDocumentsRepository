/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 2/28/14.
 * This the class that defines the method factory that is used to instantiate Users class.
 *
 *********************************************************************************************************/
package doc.secure;

public class UserFactory {
    public static Users CreateUser(String username, String password) {
            return new Users(username,password);
    }
}
