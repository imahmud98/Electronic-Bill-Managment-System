/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Database_Connector;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


public class PasswordConfirmationController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    /**
     * Initializes the controller class.
     */
    
    ResetPasswordController rpc = new ResetPasswordController();  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ChangePasswordConfirm(ActionEvent event) {
         
        String pass = rpc.UpdatedPassword();
        try {
            // TODO
            Database_Connector obj=new Database_Connector();
            Connection connection=obj.getConnection();
            PreparedStatement pst=null;
           
            String query= "UPDATE `login` SET `pass`=? where login_id='Admin'";

            pst=connection.prepareStatement(query);
            pst.setString(1, pass);
            pst.execute();


            obj.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        success_notification();
        Stage st = (Stage) anchorpane.getScene().getWindow();;
        st.close();
    }

    @FXML
    private void CancelPasswordChange(ActionEvent event) {
        Stage st = (Stage) anchorpane.getScene().getWindow();;
        st.close();
    }
    
    
    public void success_notification(){
        String title = "Passward Updated";
        String message = "New Password Has Been Updated";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1200));
    }
    
}
