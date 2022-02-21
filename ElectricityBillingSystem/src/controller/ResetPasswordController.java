/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Database_Connector;


public class ResetPasswordController implements Initializable {
    
    @FXML
    private JFXTextField current_password;
    @FXML
    private JFXTextField new_password;
    @FXML
    private JFXTextField confirm_new_password;
    
    public static String password = "";
    String mainpass = "";
    
    @FXML
    private Label error_label;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ResetPassword(ActionEvent event) {
        
        String current_password_value = current_password.getText();
        String new_password_value = new_password.getText();
        String new_password_confirm_value = confirm_new_password.getText();
        try {
            // TODO
            Database_Connector obj=new Database_Connector();
            Connection connection=obj.getConnection();
            PreparedStatement pst=null;

            ResultSet rs=null;

           
            String query= "Select pass from login where login_id='Admin'";

            pst=connection.prepareStatement(query);
            rs=pst.executeQuery();

            
            while(rs.next()){
                mainpass = rs.getString("pass");
                System.out.println(mainpass);
                
            }

            

            obj.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(current_password_value.trim().equals(mainpass)){
            if(new_password_value.trim().equals(new_password_confirm_value)){
                Parent root1;      
                password = new_password_value;
                FXMLLoader fxmlLoader1= new FXMLLoader(getClass().getResource("/view/PasswordConfirmation.fxml"));
                try {
                    root1 = (Parent) fxmlLoader1.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1)); 
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                resetValue();
            }
            else{             
                error_label.setText("Passwords don't match");
            }    
        }
        else{
            error_label.setText("Current password doestn't match");
        }
    }
    
    public String UpdatedPassword(){

        return password;
    }
    
    public void resetValue(){
        current_password.clear();
        new_password.clear();
        confirm_new_password.clear();
    }
    
}
