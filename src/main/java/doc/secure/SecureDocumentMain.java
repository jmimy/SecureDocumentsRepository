/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 2/28/14.
 * This class defined the main window or form for the application. A table contains all the documents the
 * current user has access.Text fields and buttons are available to manipulate records.
 *********************************************************************************************************/
package doc.secure;

import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableCellRenderer;


public class SecureDocumentMain extends JFrame {
            private static String username;
            private static String date = new SimpleDateFormat("MM/dd/YYYY").format(Calendar.getInstance().getTime());;
            private static String timeLog = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());;

            public SecureDocumentMain(String username) {
                this.username = username;
                initComponents();
            }

            private void initComponents()
            {
                /* Instantiate all components needed for the Main window or frame */
                JScrollPane jScrollPane = new JScrollPane();
                JTable docTable = new JTable();
                JPanel jPanel = new JPanel();
                JButton addButton = new JButton("Add");
                JButton updateButton = new JButton("Update");
                JButton openButton = new JButton("Open");
                JButton deleteButton = new JButton("Delete");
                final JTextField codeTextField = new JTextField();
                final JTextField subjectTextField = new JTextField();
                final JTextField infoTextField = new JTextField();
                final JTextField authTextField = new JTextField();
                final JTextField cDateTextField = new JTextField(date);
                final JTextField mDateTextField = new JTextField(date);
                final JTextField sizeTextField = new JTextField();
                JLabel codeJLabel = new JLabel("Code");
                JLabel subjectJLabel = new JLabel("Subject");
                JLabel infoJLabel = new JLabel("information");
                JLabel authJLabel = new JLabel("Author");
                JLabel cDateJLabel = new JLabel("Date created");
                JLabel mDateJLabel = new JLabel("Date modified");
                JLabel sizeJLabel = new JLabel("Size");

                /* */
                cDateTextField.setBackground(new Color(224,224,224));
                mDateTextField.setBackground(new Color(224,224,224));
                cDateTextField.setEditable(false);
                mDateTextField.setEditable(false);

                ResultSet rs = null;
                PreparedStatement statement = null;
                Connection connection = null;
                String URL;
                int columns = 7;
                String sql_rel;
                Vector columns_name = new Vector(columns);
                Vector data_row;
                DefaultTableModel dtm = new DefaultTableModel();

                //Columns Vector Elements
                columns_name.addElement("Code");
                columns_name.addElement("Subject");
                columns_name.addElement("Author");
                columns_name.addElement("information");
                columns_name.addElement("Date Created");
                columns_name.addElement("Date Modified");
                columns_name.addElement("Size");
                dtm.setColumnIdentifiers(columns_name);

                try
                {
                    URL = "jdbc:mysql://localhost:3306/securedocsrep";
                    connection = DriverManager.getConnection(URL, "mysql", "mysql123");
                    sql_rel = "select docCode , subject , author," +
                            " information , createDate , modifiedDate, " +
                            "size from securedoctbl where doccode in (select distinct doccode from userdocrel " +
                            "where username = '" + username +"')" ;
                    statement = connection.prepareStatement(sql_rel);
                    rs = statement.executeQuery();


                    //Rows Vector Elements
                    while (rs.next()) {
                        data_row = new Vector(columns);
                        for (int j=1; j<=columns; j++)
                        {
                           data_row.addElement(rs.getString(j));
                        }
                        dtm.addRow(data_row);
                    }

                    //Pass the Table Object Model Structure
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

                addButton.addActionListener(new ActionListener() {
                    String docCode;
                    String subject;
                    String information;
                    String author;
                    Date cDate;
                    Date mDate;
                    Boolean aControl;
                    int size;
                    public void actionPerformed(ActionEvent evt) {
                        docCode = codeTextField.getText();
                        subject = subjectTextField.getText();
                        information = infoTextField.getText();
                        author = authTextField.getText();
                        cDate  = Calendar.getInstance().getTime();
                        mDate  = Calendar.getInstance().getTime();

                        aControl = true;
                        SecureDocument secureDocument = new SecureDocument(docCode,author,aControl);
                        secureDocument.setSubject(subject);
                        secureDocument.setInformation(information);
                        secureDocument.setCreateDate(cDate);
                        secureDocument.setModifiedDate(mDate);

                        DocumentDAO.create(secureDocument);

                    }
                });

                updateButton.addActionListener(new ActionListener() {
                    String docCode;
                    String subject;
                    String information;
                    String author;
                    Date cDate;
                    Date mDate;
                    Boolean aControl;
                    int size;
                    public void actionPerformed(ActionEvent evt) {
                        docCode = codeTextField.getText();
                        subject = subjectTextField.getText();
                        information = infoTextField.getText();
                        author = authTextField.getText();
                        cDate  = Calendar.getInstance().getTime();
                        mDate  = Calendar.getInstance().getTime();
                        aControl = true;
                        SecureDocument secureDocument = new SecureDocument(docCode,author,aControl);
                        secureDocument.setSubject(subject);
                        secureDocument.setInformation(information);
                        secureDocument.setCreateDate(cDate);
                        secureDocument.setModifiedDate(mDate);
                        DocumentDAO.update(secureDocument);

                    }
                });

                openButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {


                    }
                });

                deleteButton.addActionListener(new ActionListener() {
                    String docCode;
                    String subject;
                    String information;
                    String author;
                    Date cDate;
                    Date mDate;
                    Boolean aControl;
                    int size;
                    public void actionPerformed(ActionEvent evt) {
                        docCode = codeTextField.getText();
                        subject = subjectTextField.getText();
                        information = infoTextField.getText();
                        author = authTextField.getText();
                        cDate  = Calendar.getInstance().getTime();
                        mDate  = Calendar.getInstance().getTime();
                        aControl = true;
                        SecureDocument secureDocument = new SecureDocument(docCode,author,aControl);
                        secureDocument.setSubject(subject);
                        secureDocument.setInformation(information);
                        secureDocument.setCreateDate(cDate);
                        secureDocument.setModifiedDate(mDate);

                        DocumentDAO.delete(secureDocument);

                    }
                });

         /* Setup the appearance of the window: panel, table, buttons and data fields needed. Adds to the Panel.add(scrollPane, BorderLayout.CENTER). */
                GroupLayout jPanel1Layout = new GroupLayout(jPanel);
                jPanel.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                                .addComponent(jScrollPane)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                                                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                                                                                        .addComponent(infoTextField, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
                                                                                                        .addComponent(infoJLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                                                                                        .addComponent(subjectJLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                        .addComponent(codeJLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                                .addGap(41, 41, 41)
                                                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(codeTextField, GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(subjectTextField, GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                        .addComponent(authJLabel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(18, 18, 18)
                                                                                        .addComponent(authTextField)))
                                                                        .addGap(74, 74, 74)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                        .addComponent(cDateJLabel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(18, 18, 18)
                                                                                        .addComponent(cDateTextField, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                        .addComponent(mDateJLabel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                                        .addComponent(mDateTextField))
                                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                        .addComponent(sizeJLabel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                                        .addComponent(sizeTextField))))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(openButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(0, 412, Short.MAX_VALUE)))
                                        .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(codeJLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cDateJLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cDateTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(codeTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(subjectJLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(subjectTextField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(mDateJLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(mDateTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(infoJLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(sizeJLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(sizeTextField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(infoTextField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(4, 4, 4)))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(authJLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(authTextField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                                        .addGap(71, 71, 71)
                                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(openButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(87, Short.MAX_VALUE))
                );

                GroupLayout layout = new GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();

                setTitle("Secure Document Repository");
                setSize(900,650);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setVisible(true);

            }// <end of constructor>
}