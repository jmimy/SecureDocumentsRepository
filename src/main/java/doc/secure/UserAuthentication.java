package doc.secure;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;

/**
 * Created by Jeromy on 3/6/14.
 */
public class UserAuthentication {
    private JTextField username;
    private JTextField password;
    private JLabel title;
    private JButton cancelButton;
    private JButton OKButton;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.username.setColumns(10);
        this.password.setColumns(10);

    }
}
