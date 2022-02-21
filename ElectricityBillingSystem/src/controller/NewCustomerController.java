/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import model.Database_Connector;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


public class NewCustomerController implements Initializable {
    
    ObservableList<String> action = FXCollections.observableArrayList("Select Gender", "Male", "Female","Others");
    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField Address;
    @FXML
    private JFXTextField COntact;
    @FXML
    private JFXTextField MeterNo;
    @FXML
    private JFXTextField Email;
    @FXML
    private ChoiceBox GenderBox;
    @FXML
    private JFXTextField PinCode;
    @FXML
    private JFXTextField Age;
    @FXML
    private Label CustomerID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GenderBox.setValue("Select Gender");
        GenderBox.setItems(action);
         try {
            // TODO
            Database_Connector obj=new Database_Connector();
            Connection connection=obj.getConnection();
            PreparedStatement pst=null;
            ResultSet rs=null;
           
            String query= "SELECT `id` FROM `customer` ORDER BY `customer`.`id` DESC";
            
            pst=connection.prepareStatement(query);
           
            rs=pst.executeQuery();
            int count = 0;
            
            while(rs.next()){
                ++count;
                if(count == 1){
                    int c=Integer.parseInt(rs.getString("id"))+1;
                    CustomerID.setText(Integer.toString(c));
                }
               
            }
            if (count==0){
                CustomerID.setText("1001");
            }
            
   
            obj.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(NewCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void NewUser(ActionEvent event) {
        connect_database();
    }
    
    
    private void connect_database(){
        
        
        Database_Connector obj=new Database_Connector();

     try {
            obj.getConnection();
            PreparedStatement pst=null;
            pst = obj.connection.prepareStatement("INSERT INTO `customer`(`name`, `email`, `address`, `gender`, `contact`, `age`, `pin`, `meter`,`id`) VALUES (?,?,?,?,?,?,?,?,?)");
            
            if(GenderBox.getValue().toString().equals("Male")){                
                pst.setString(4, "Male");
            }
            else if(GenderBox.getValue().toString().equals("Female")){
                pst.setString(4, "Female");
            }
            else if(GenderBox.getValue().toString().equals("Others")){
                pst.setString(4, "Others");
            }
                
            pst.setString(1, Name.getText());
                
            
            pst.setString(2, Email.getText());
            pst.setString(3, Address.getText());
            pst.setInt(5, Integer.parseInt(COntact.getText()));
            pst.setInt(6, Integer.parseInt(Age.getText()));
            pst.setInt(7, Integer.parseInt(PinCode.getText()));
            pst.setInt(8, Integer.parseInt(MeterNo.getText()));
            pst.setInt(9, Integer.parseInt(CustomerID.getText()));
            pst.executeUpdate();

            
            obj.disconnect();
            
            
            Name.clear();
            Email.clear();
            Address.clear();
            COntact.clear();
            Age.clear();
            PinCode.clear();
            MeterNo.clear();
            
            
            successful_update_notification();
        
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
            
        }
    }
    private void successful_update_notification(){
    String title = "User Added";
        String message = "New user has been added";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1200));
    }
    
}
