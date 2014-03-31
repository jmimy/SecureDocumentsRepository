/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 03/14/14.
 * This is the class for control Access. A signon form require user authentication. It validate username and
 * password pair against the database and load the main screen, if credentials are valid.
 *********************************************************************************************************/

package doc.secure;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Message extends JFrame {
        String message;

        public Message(String message) {
            this.message = message;

            JPanel panel = new JPanel();
            getContentPane().add(panel);

            panel.setLayout(null);

            JButton okButton = new JButton("OK");
            okButton.setBounds(80, 125, 90, 30);

            JLabel messageLabel = new JLabel(message);
            messageLabel.setBounds(10,30,250,50);

            panel.add(okButton);
            panel.add(messageLabel);

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    }
                }
            );

            setTitle("Sign On");
            setSize(300,200);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        }

}