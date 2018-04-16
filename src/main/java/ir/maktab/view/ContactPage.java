package ir.maktab.view;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    String serverIp ;
    String token;
    String role ;
    public ContactPage(String serverIp,String token,String role) {
        this.serverIp = serverIp;
        this.role=role;
        this.token = token;
        initComponents();
        refreshTable();
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

        if(role.equals("User")){
            deleteBtn.setEnabled(false);
        }
        else if(role.equals("Guest")){
            addBtn.setEnabled(false);
            editBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }
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
        JFrame addFrame = new JFrame();
        addFrame.add(new AddPage(serverIp,token));
        addFrame.setVisible(true);
        addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addFrame.setSize(380,250);
    }

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int row = table.getSelectedRow();
        System.out.println(row);
        int column =0;
        System.out.println(table.getModel().getValueAt(row,column));
        System.out.println(table.getModel().getValueAt(row,1));

        int id ;
        id = (int) table.getModel().getValueAt(row,column);
        Client client = Client.create();
        WebResource webResource = client.resource(String.format("http://%s:8080/api/Contact/delete/%d",serverIp,id));
        ClientResponse response = webResource.type("application/json").header("Authorization",token).get(ClientResponse.class);
        if(response.getStatus()==200){
            JOptionPane.showMessageDialog(this, "Contact Deleted",
                    "Deleting Message",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(response.getStatus()==201){
            JOptionPane.showMessageDialog(this, "Request Send but not Deleted!",
                    "Deleting Message",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this, String.format("ERROR Code: %d",response.getStatus()),
                    "Deleting Message",JOptionPane.ERROR_MESSAGE);
        }

    }

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int row = table.getSelectedRow();
        Vector rowContent = (Vector) ((DefaultTableModel)table.getModel()).getDataVector().elementAt(row);
        Contact contact = new Contact((String)rowContent.get(1),(String)rowContent.get(2),
                (String)rowContent.get(3),(String)rowContent.get(4),(String)rowContent.get(5));
        contact.setId((int) rowContent.get(0));

        Client client = Client.create();
        WebResource webResource = client.resource(String.format("http://%s:8080/api/Contact/update",serverIp));

        ObjectMapper mapper = new ObjectMapper();
        String json ;

        try{
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(contact);
            ClientResponse response = webResource.type("application/json").header("Authorization",token).post(ClientResponse.class,json);
            if(response.getStatus()==200){
                JOptionPane.showMessageDialog(this, "Contact Updated",
                        "Editing Message",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(response.getStatus()==201){
                JOptionPane.showMessageDialog(this, "Request Send but not Updated!",
                        "Editing Message",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, "Bad Request or internal server Error",
                        "Editing Message",JOptionPane.ERROR_MESSAGE);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {
        refreshTable();
    }

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void refreshTable(){
        List contacts = null;
        Client client = Client.create();
        WebResource resource = client.resource(String.format("http://%s:8080/api/Contact/getAll" , "localhost"));
        ClientResponse response = resource.accept("application/json").header("Authorization",token).post(ClientResponse.class);
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
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("ID",ides);
        dtm.addColumn("FirstName",firstNames);
        dtm.addColumn("LastName",lastNames);
        dtm.addColumn("Email",emails);
        dtm.addColumn("Mobile",mobiles);
        dtm.addColumn("Home",homes);
        table.setModel(dtm);

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

