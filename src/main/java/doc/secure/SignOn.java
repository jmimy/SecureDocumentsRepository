/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 03/14/14.
 * This is the class for Authentication and control Access. A signon form require user credentials. It validate username and
 * password pair against the database(users table) and load the main screen, if the credentials are valid or display to
 * new credentials.
 *********************************************************************************************************/
package doc.secure;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;


public class SignOn extends JFrame {

    public SignOn() {
        /* Instantiate all components needed for the Sign on window or frame */
        JPanel panel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        JLabel userLabel = new JLabel("User ID");
        JLabel passwordLabel = new JLabel("Password");
        final JTextField userText = new JTextField(10);
        final JTextField passwordText = new JTextField(8);

        getContentPane().add(panel);
        panel.setLayout(null);

        /* Configure all the window components: set the sizes of the buttons and fields */
        okButton.setBounds(40, 125, 90, 30);
        cancelButton.setBounds(160,125, 90,30);
        userLabel.setBounds(43,30,80,25);
        passwordLabel.setBounds(43,70,80,25);
        userText.setBounds(115,30, 130, 25);
        passwordText.setBounds(115, 70, 130,25);

        /* Adding all the buttons and text fields to the Panel */
        panel.add(okButton);
        panel.add(cancelButton);
        panel.add(userLabel);
        panel.add(passwordLabel);
        panel.add(userText);
        panel.add(passwordText);

        //Add Action listener to cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /* Add Action listener to Ok button. It will load the main application window if the credentials are valid or
        display a message requesting a new pair of username and password if the credentials are not valid. */
        okButton.addActionListener(new ActionListener() {
            String username;
            String password;
            Users users;

            @Override
            public void actionPerformed(ActionEvent e) {
                username = userText.getText();
                password = passwordText.getText();
                users = new Users(username,password);
                Boolean test = false;
                UsersDAO usersDAO = new UsersDAO();
                Users user = new Users(username, password);
                try {
                    test=usersDAO.findByUsername(user);
                }
                catch (SQLException esign)
                {
                    Message message = new Message(esign.getMessage());
                }

                if (test)
                {
                    Message message = new Message(" Invalid account.Enter an actual user and password." );
                }
                    else
                {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            SecureDocumentMain secureDocumentMain = new SecureDocumentMain(users.getUsername());

                        }
                    });
                }
            }
        });

        /* Set window or frame configuration: Title, size, visible, exit*/
        setTitle("Sign On");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /* Load the Sign on window: an instance of SignOn window is created to allow user to sign on and  get access to the
     * Documents Repository application */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SignOn sigOn = new SignOn();
            }
        });
    }
}
