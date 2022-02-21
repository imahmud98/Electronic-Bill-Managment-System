/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class DashboardController implements Initializable {

    @FXML
    private BorderPane borderpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadUI("GenerateBill");
    }
    
    
    @FXML
    private void GenerateBillButton(ActionEvent event) {
        LoadUI("GenerateBill");
    }

    @FXML
    private void NewCustomerButton(ActionEvent event) {
        LoadUI("NewCustomer");
    }
    
    @FXML
    private void bKashButton(ActionEvent event) {
        LoadUI("BKash");
    }

    @FXML
    private void ExitButton(ActionEvent event) {
        
        Parent root;      
//        SocialMediaController.login=false; 
        System.gc();
        try {
            root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Stage stage = (Stage) borderpane.getScene().getWindow();
                Scene scene = new Scene(root);
                
                stage.setScene(scene);
                //stage.initStyle(StageStyle.UNDECORATED);  /* creates problem in maitaining screen ratio */
                stage.centerOnScreen();
                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PasswordResetButton(ActionEvent event) {
        LoadUI("ResetPassword");
    }


    @FXML
    private void CalculatorButton(ActionEvent event) throws IOException {
        Runtime.getRuntime().exec("calc");
    }
   

    @FXML
    private void CustomerDetailsButton(ActionEvent event) {
        LoadUI("CustomerDetails");
    }    
    
    public void LoadUI(String ui){
        Parent root = null;
        try {
           root = FXMLLoader.load(getClass().getResource("/view/"+ui+".fxml"));
//           System.out.println(root);
        } catch (IOException ex) {
            System.out.println(root);
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println(root);
        borderpane.setCenter(root);
    }


    
}
