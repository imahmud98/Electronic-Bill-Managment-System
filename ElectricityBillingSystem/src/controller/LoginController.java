/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javafx.scene.input.KeyEvent;
import model.Database_Connector;


public class LoginController implements Initializable {

    @FXML
    private TextField signin_email;
    @FXML
    private PasswordField signin_password;
    
    String mail,pass;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    } 
    
    
    @FXML
    private void signin_button(ActionEvent event) {
        signin_method();
    }
    
    
    private void signin_method(){
    String email = signin_email.getText();
    String password = signin_password.getText();
    try {
            // TODO
            Database_Connector obj=new Database_Connector();
            Connection connection=obj.getConnection();
            PreparedStatement pst=null;

            ResultSet rs=null;

           
            String query= "Select login_id, pass from login where login_id=? and pass=?";

            pst=connection.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            rs=pst.executeQuery();

            
            while(rs.next()){
                mail = rs.getString("login_id");
                pass = rs.getString("pass");
                
            }

            

            obj.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    if(email.trim().equals(mail) && pass.trim().equals(password)){
            
            Parent root;      
            try {
                
               
                root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
                
                Stage stage = (Stage) signin_password.getScene().getWindow();
                stage.close(); // This to stop seeing the black screen as the screen gets bigger
                Scene scene = new Scene(root);
                stage.setScene(scene);
                //stage.initStyle(StageStyle.UNDECORATED);  /* creates problem in maitaining screen ratio */
                stage.centerOnScreen();
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
   
        } else {
            error_notification();
            System.out.println("Admin Login Failed");
        } 
    }
    
    
    public void error_notification(){
        String title = "Access Denied";
        String message = "Please Enter Valid Email Id and Password";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(Duration.millis(1200));
    } 

}
