/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 03/14/14.
 * This class defined a window or frame to display Error and Warnings message for the user.
 *********************************************************************************************************/

package doc.secure.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageFrame extends JFrame {
        String message;

        public MessageFrame(String message) {
            this.message = message;

            JPanel panel = new JPanel();
            getContentPane().add(panel);

            panel.setLayout(null);

            JButton okButton = new JButton("OK");
            okButton.setBounds(80, 125, 90, 30);

            JLabel messageLabel = new JLabel(message);
            messageLabel.setBounds(10,30,490,50);

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
            setSize(500,200);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        }

}
