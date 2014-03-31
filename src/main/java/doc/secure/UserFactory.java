/*******************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: on 3/7/14
 * This class defines a method factory to instantiate User object.
 * @param
 ******************************************************************************************/
package doc.secure;

public class UserFactory {
    public static Users CreateUser(String username, String password) {
            return new Users(username,password);
    }
}
