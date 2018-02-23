package ir.maktab.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import ir.maktab.model.contact.Contact;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by Hamed-Abbaszadeh on 2/23/2018.
 */

public class ContactPage extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    public ContactPage() {
        initialTable();
        initComponents();
        table.setModel(dtm);
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {
        addBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        searchBtn = new javax.swing.JButton();
        addBtn.setText("Add");
        addBtn.setToolTipText("addContact");
        addBtn.addActionListener(evt -> addBtnActionPerformed(evt));

        deleteBtn.setText("Delete");
        deleteBtn.setToolTipText("deleteSelectedContact");
        deleteBtn.addActionListener(evt -> deleteBtnActionPerformed(evt));

        editBtn.setText("Edit");
        editBtn.setToolTipText("editSelectedContact");
        editBtn.addActionListener(evt -> editBtnActionPerformed(evt));

        refreshBtn.setText("Refresh");
        refreshBtn.setToolTipText("RefreshTable");
        refreshBtn.addActionListener(evt -> refreshBtnActionPerformed(evt));

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null}
                },
                new String [] {
                        "ID", "FirstName", "LastName", "Email", "Mobile", "Home"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        searchBtn.setText("Search");
        searchBtn.setToolTipText("SearchAContact");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
    }// </editor-fold>

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void initialTable(){
        List contacts = null;
        Client client = Client.create();
        WebResource resource = client.resource(String.format("http://%s:8080/api/Contact/getAll" , "localhost"));
        ClientResponse response = resource.accept("application/json").post(ClientResponse.class);
        if (response.getStatus() != 200) {
            JOptionPane.showMessageDialog(this , "Failed : HTTP error code : "
                    + response.getStatus());
        }
        //converting json to List of contacts
        String json = response.getEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            contacts = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Contact.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(contacts.size());
        System.out.println(contacts.get(0));
        Iterator iterator = contacts.iterator();
        Vector ides = new Vector();
        Vector firstNames = new Vector();
        Vector lastNames =new Vector();
        Vector emails = new Vector();
        Vector mobiles = new Vector();
        Vector homes = new Vector();

        Contact contact;
        while (iterator.hasNext()){
            contact = (Contact) iterator.next();
            ides.add(contact.getId());
            firstNames.add(contact.getFirstName());
            lastNames.add(contact.getLastName());
            emails.add(contact.getEmail());
            mobiles.add(contact.getMobile());
            homes.add(contact.getHome());
        }
        dtm.addColumn("ID",ides);
        dtm.addColumn("FirstName",firstNames);
        dtm.addColumn("LastName",lastNames);
        dtm.addColumn("Email",emails);
        dtm.addColumn("Mobile",mobiles);
        dtm.addColumn("Home",homes);

    }

    // Variables declaration - do not modify
    private javax.swing.JButton addBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton searchBtn;
    // End of variables declaration
}

