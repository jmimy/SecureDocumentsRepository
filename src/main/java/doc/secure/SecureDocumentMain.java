/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 2/28/14.
 * This the main class of the application.
 * It verifies the connection. Read configuration file and load the Main Window.
 *********************************************************************************************************/
package doc.secure;

import java.awt.*;
import java.awt.print.PrinterException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.naming.CommunicationException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingUtilities;

public class SecureDocumentMain extends JFrame {
            private static String username;

            public SecureDocumentMain(String username) {
                this.username = username;
                initComponents();
            }

            private void initComponents()
            {
                /* Instantiate all components needed for the Main window or frame */
                JPanel jPanel = new JPanel();
                JButton addButton = new JButton("Add");
                JButton updateButton = new JButton("Cancel");
                JButton deleteButton = new JButton("Delete");
                JButton printButton = new JButton("Print");
                JTable docTable = new JTable();
                JScrollPane jScrollPane = new JScrollPane();

                /* */
                ResultSet rs = null;
                PreparedStatement statement = null;
                Connection connection = null;
                String URL;
                ResultSetMetaData rsmetadata;
                int columns;
                String sql_rel;

                try
                {
                    URL = "jdbc:mysql://localhost:3306/securedocsrep";
                    connection = DriverManager.getConnection(URL, "mysql", "mysql123");
                    sql_rel = "select docCode , subject , author," +
                            " information , createDate , modifiedDate, " +
                            "size from securedoctbl where doccode in (select doccode from userdocrel " +
                            "where username = '" + username +"')" ;
                    statement = connection.prepareStatement(sql_rel);
                    rs = statement.executeQuery();
                    rsmetadata = rs.getMetaData();

                    //NUmber of columns for the resultset
                    columns = rsmetadata.getColumnCount();

                    DefaultTableModel dtm = new DefaultTableModel();

                    Vector columns_name = new Vector();
                    Vector data_row = new Vector();

                    for (int i=1; i<=columns; i++)
                    {
                        columns_name.addElement(rsmetadata.getColumnName(i));
                    }

                    dtm.setColumnIdentifiers(columns_name);
                    while (rs.next()) {
                        for (int j=1; j<=columns; j++)
                        {
                           data_row.addElement(rs.getString(j));
                        }
                        dtm.addRow(data_row);
                    }
                    //pass Default Table Object
                    docTable.setModel(dtm);

                    jScrollPane.setViewportView(docTable);
                }//End of Try
                catch (SQLException e)
                {
                    //Handle CommunicationsException
                    if (e.getSQLState() == "08001"){
                        Message messsage = new Message("Your database server is not running.");
                    }
                    try {
                        String timeLog = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                        String err = connection.toString();
                        sql_rel = "insert into logstbl(logCode, errMessage,user,createdate) values('10001','" + err + "', 'jmimy','" + timeLog + "')" ;
                        PreparedStatement pst = connection.prepareStatement(sql_rel);
                        pst.execute(sql_rel);
                    }
                    catch (SQLException elog)
                    {
                         Message message = new Message(elog.getMessage());
                    }
                }
                finally
                {
                    try {
                        connection.close();
                    }
                    catch (SQLException econ) {
                        Message message = new Message(econ.getMessage());
                    }

                }
         /* Setup the appearance of the window: panel, table, buttons and data fields needed. Adds to the Panel.add(scrollPane, BorderLayout.CENTER). */
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(addButton)
                    .addGap(28, 28, 28)
                    .addComponent(updateButton)
                    .addGap(26, 26, 26)
                    .addComponent(deleteButton)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(52, 52, 52)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addButton)
                            .addComponent(updateButton)
                            .addComponent(deleteButton))
                    .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(122, Short.MAX_VALUE))
        );

        pack();

            setTitle("Secure Document Repository");
            setSize(900,650);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        }

        public static void invokeMain(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                     SecureDocumentMain secureDocumentMain = new SecureDocumentMain(username);
                }
            });
        }
}