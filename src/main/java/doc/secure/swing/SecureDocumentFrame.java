/**********************************************************************************************************
 * Created by: Jean-Robert Mimy
 * Date: 2/28/14.
 * This class defined the main window or form for the application. A table contains all the documents the
 * current user has access.Text fields and buttons are available to manipulate records.
 *********************************************************************************************************/
package doc.secure.swing;

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
import javax.swing.table.DefaultTableModel;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.util.Vector;

import doc.secure.SecureDocument;
import doc.secure.swing.MessageFrame;


public class SecureDocumentFrame extends JFrame {
            private String username;
            private static String date = new SimpleDateFormat("MM/dd/YYYY").format(Calendar.getInstance().getTime());;
            private static String timeLog = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());;

            public SecureDocumentFrame(String username) {
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
                Vector<String> columns_name = new Vector<String>(columns);
                Vector<String> data_row;
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

                try {
                    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse (new File("lib/securedocuments.xml"));

                    NodeList listOfDocuments = doc.getElementsByTagName("document");
                    int totalUsers = listOfDocuments.getLength();
                    System.out.println("Total nbr of users : " + totalUsers);

                    for(int s=0; s<listOfDocuments.getLength() ; s++){
                        data_row = new Vector<String>(7);

                        Node firstDocumentNode = listOfDocuments.item(s);
                        if(firstDocumentNode.getNodeType() == Node.ELEMENT_NODE){


                            Element firstDocumentElement = (Element)firstDocumentNode;

                            NodeList doccodeList = firstDocumentElement.getElementsByTagName("doccode");
                            Element doccodeElement = (Element)doccodeList.item(0);
                            NodeList textDCList = doccodeElement.getChildNodes();

                            NodeList subjectList = firstDocumentElement.getElementsByTagName("subject");
                            Element subjectElement = (Element)subjectList.item(0);
                            NodeList textSJList = subjectElement.getChildNodes();

                            NodeList informationList = firstDocumentElement.getElementsByTagName("information");
                            Element informationElement = (Element)informationList.item(0);
                            NodeList textIFList = informationElement.getChildNodes();

                            NodeList authorList = firstDocumentElement.getElementsByTagName("author");
                            Element authorElement = (Element)authorList.item(0);
                            NodeList textATList = authorElement.getChildNodes();

                            NodeList createddateList = firstDocumentElement.getElementsByTagName("createdate");
                            Element createddateElement = (Element)createddateList.item(0);
                            NodeList textCDList = doccodeElement.getChildNodes();

                            NodeList modifieddateList = firstDocumentElement.getElementsByTagName("modifieddate");
                            Element modifieddateElement = (Element)modifieddateList.item(0);
                            NodeList textMDList = modifieddateElement.getChildNodes();

                            NodeList sizeList = firstDocumentElement.getElementsByTagName("size");
                            Element sizeElement = (Element)sizeList.item(0);
                            NodeList textSZList = sizeElement.getChildNodes();

                            data_row.addElement(((Node)textDCList.item(0)).getNodeValue().toString());
                            data_row.addElement(((Node)textSJList.item(0)).getNodeValue().toString());
                            data_row.addElement(((Node)textIFList.item(0)).getNodeValue().toString());
                            data_row.addElement(((Node)textATList.item(0)).getNodeValue().toString());
                            data_row.addElement(((Node)textCDList.item(0)).getNodeValue().toString());
                            data_row.addElement(((Node)textMDList.item(0)).getNodeValue().toString());
                            data_row.addElement(((Node)textSZList.item(0)).getNodeValue().toString());
                        }//end of if clause
                        dtm.addRow(data_row);
                    }//end of for loop with s var

                    //Pass the Table Object Model Structure
                    docTable.setModel(dtm);

                    jScrollPane.setViewportView(docTable);
                }
                catch (SAXParseException err)
                {
                    MessageFrame messageFrame = new MessageFrame("Parsing error" + ", line "
                            + err.getLineNumber () + ", uri " + err.getSystemId ());
                }
                catch (SAXException e)
                {
                    Exception x = e.getException ();
                    MessageFrame messageFrame = new MessageFrame(((x == null) ? e : x).getMessage());

                }
                catch (Throwable t)
                {
                    MessageFrame messageFrame = new MessageFrame(t.getMessage());
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

                        secureDocument.add(secureDocument);

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
                        secureDocument.update(secureDocument);

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

                        secureDocument.delete(secureDocument);

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