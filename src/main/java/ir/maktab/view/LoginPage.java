package ir.maktab.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import ir.maktab.api.user.dto.UserAuthDTO;

import javax.swing.*;


/**
 * Created by Hamed-Abbaszadeh on 2/21/2018.
 */

public class LoginPage extends javax.swing.JPanel {


    public LoginPage() {
        initComponents();
    }


    @SuppressWarnings("unchecked")

    private void initComponents() {

        lbl1 = new javax.swing.JLabel();
        userNameTxt = new javax.swing.JTextField();
        lbl3 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        serverIp = new javax.swing.JTextField();
        passwordTxt = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        lbl1.setText("Server Ip:");
        userNameTxt.setText("Hamed");
        lbl3.setText("Password :");
        lbl2.setText("UserName :");
        serverIp.setText("127.0.0.1");
        passwordTxt.setText("passwordTxt");
        loginBtn.setText("Login");

        loginBtn.addActionListener(evt -> loginBtnActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(serverIp, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(userNameTxt)
                                                                .addComponent(passwordTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(serverIp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(userNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(passwordTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                        .addComponent(lbl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 28, Short.MAX_VALUE)
                                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(38, Short.MAX_VALUE))
        );
    }


    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        UserAuthDTO userAuthDTO = new UserAuthDTO(userNameTxt.getText() , passwordTxt.getText());
        System.out.println(userAuthDTO.getUsername());
        System.out.println(userAuthDTO.getPassword());
        String ip = serverIp.getText();
        Client client = Client.create();
        WebResource webResource =  client.resource("http://localhost:8080/api/login/authenticate" );
        ObjectMapper mapper = new ObjectMapper();
        String json= null;
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userAuthDTO);
            System.out.println(json);
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class,json);
            System.out.println(response.getStatus());
            if (response.getStatus() != 200)
                JOptionPane.showMessageDialog(this,String.format("ERROR Code: %d", response.getStatus()));

            else {
                JFrame contactFrame = new JFrame("Contacts Page");
                contactFrame.setSize(460 , 390);
                contactFrame.setResizable(false);
                contactFrame.add(new ContactPage());
                contactFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                contactFrame.setVisible(true);
            }
        } catch (JsonProcessingException | NullPointerException e) {
            e.printStackTrace();
        }

    }

    // Variables declaration - do not modify
    private javax.swing.JTextField serverIp;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JTextField userNameTxt;
    // End of variables declaration

    public static void main(String[] args) {
        JFrame loginFrame = new JFrame("LoginPage");
        loginFrame.setSize(370 , 350);
        loginFrame.setResizable(false);
        loginFrame.add(new LoginPage());
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
