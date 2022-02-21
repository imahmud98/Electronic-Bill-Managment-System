/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import model.Database_Connector;
import model.GenerateBillData;

public class CustomerDetailsController implements Initializable {

    ObservableList<String> action = FXCollections.observableArrayList("Select Month", "January", "February","March","April","May","Jun","July","August","September","October","November","December");
    @FXML
    private JFXTextField UserEmail;
    @FXML
    private Label name;
    @FXML
    private Label address;
    @FXML
    private Label gender;
    @FXML
    private Label totalunit;
    @FXML
    private Label pin;
    @FXML
    private JFXTextField newUnit;
    @FXML
    private ChoiceBox monthselectBox;
    @FXML
    private Label contact;
    @FXML
    private Label meter;
    @FXML
    private Label id;

   
    
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        monthselectBox.setValue("Select Month");
        monthselectBox.setItems(action);
       
    }    

    @FXML
    private void GenerateInfo(ActionEvent event) {
        
        String name1,id1,contact1,pin1,meter1;
        Double month1;
        
        try {
            // TODO
            Database_Connector obj=new Database_Connector();
            Connection connection=obj.getConnection();
            PreparedStatement pst=null;
            
            ResultSet rs=null;

            
  
            String cash= "Select * from customer where email=?";

            pst=connection.prepareStatement(cash);
            pst.setString(1,UserEmail.getText() );
            rs=pst.executeQuery();

            while(rs.next()){
                name.setText("NAME: "+rs.getString("name"));
                id.setText("CUSTOMER ID: "+rs.getString("id"));
                contact.setText("MOBILE NO: "+rs.getString("contact"));
                pin.setText("PIN NO: "+rs.getString("pin"));
                meter.setText("METER NO: "+rs.getString("meter")); 
                address.setText("ADDRESS: "+rs.getString("address"));
                gender.setText("GENDER: "+rs.getString("gender"));
                totalunit.setText("TOTAL UNIT: "+Double.toString(rs.getDouble("jan") + rs.getDouble("feb") + rs.getDouble("mar") + rs.getDouble("apr") + rs.getDouble("may") + rs.getDouble("june") + rs.getDouble("aug") + rs.getDouble("sep") + rs.getDouble("oct") + rs.getDouble("nov") + rs.getDouble("dece") + rs.getDouble("july") ));
                
            }
            
            
            obj.disconnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(GenerateBillController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void UpdateUnitMethod(ActionEvent event) {
         try {
            // TODO
            Database_Connector obj=new Database_Connector();
            Connection connection=obj.getConnection();
            PreparedStatement pst=null;
            
            
                
                if(monthselectBox.getValue().toString().equals("January")){
                    String query= "UPDATE `customer` SET `jan`=? where email=?";

                    pst=connection.prepareStatement(query);
                    pst.setDouble(1, Double.parseDouble(newUnit.getText()));
                    pst.setString(2,UserEmail.getText() );
                    pst.execute();
                }
                else if(monthselectBox.getValue().toString().equals("February")){ 
                    String query= "UPDATE `customer` SET `feb`=? where email=?";

                    pst=connection.prepareStatement(query);
                    pst.setDouble(1, Double.parseDouble(newUnit.getText()));
                    pst.setString(2,UserEmail.getText() );
                    pst.execute();
//                    month=Double.parseDouble(rs.getString("feb"));
                }
                else if(monthselectBox.getValue().toString().equals("March")){
                    String query= "UPDATE `customer` SET `mar`=? where email=?";

                    pst=connection.prepareStatement(query);
                    pst.setDouble(1, Double.parseDouble(newUnit.getText()));
                    pst.setString(2,UserEmail.getText() );
                    pst.execute();
//                    month=Double.parseDouble(rs.getString("mar"));
                }
                else if(monthselectBox.getValue().toString().equals("April")){ 
                    String query= "UPDATE `customer` SET `apr`=? where email=?";

                    pst=connection.prepareStatement(query);
                    pst.setDouble(1, Double.parseDouble(newUnit.getText()));
                    pst.setString(2,UserEmail.getText() );
                    pst.execute();
//                    month=Double.parseDouble(rs.getString("apr"));
                }
                else if(monthselectBox.getValue().toString().equals("May")){  
                    String query= "UPDATE `customer` SET `may`=? where email=?";

                    pst=connection.prepareStatement(query);
                    pst.setDouble(1, Double.parseDouble(newUnit.getText()));
                    pst.setString(2,UserEmail.getText() );
                    pst.execute();
//                    month=Double.parseDouble(rs.getString("may"));
                }
                else if(monthselectBox.getValue().toString().equals("June")){
                    String query= "UPDATE `customer` SET `june`=? where email=?";

                    pst=connection.prepareStatement(query);
                    pst.setDouble(1, Double.parseDouble(newUnit.getText()));
                    pst.setString(2,UserEmail.getText() );
                    pst.execute();
//                    month=Double.parseDouble(rs.getString("june"));
                }
                else if(monthselectBox.getValue().toString().equals("July")){   
                    String query= "UPDATE `customer` SET `july`=? where email=?";

                    pst=connection.prepareStatement(query);
                    pst.setDouble(1, Double.parseDouble(newUnit.getText()));
                    pst.setString(2,UserEmail.getText() );
                    pst.execute();
//                    month=Double.parseDouble(rs.getString("july"));
                }
                else if(monthselectBox.getValue().toString().equals("August")){ 
                    String query= "UPDATE `customer` SET `aug`=? where email=?";

                    pst=connection.prepareStatement(query);
                    pst.setDouble(1, Double.parseDouble(newUnit.getText()));
                    pst.setString(2,UserEmail.getText() );
                    pst.execute();
//                    month=Double.parseDouble(rs.getString("aug"));
                }
                else if(monthselectBox.getValue().toString().equals("September")){ 
                    String query= "UPDATE `customer` SET `sep`=? where email=?";

                    pst=connection.prepareStatement(query);
                    pst.setDouble(1, Double.parseDouble(newUnit.getText()));
                    pst.setString(2,UserEmail.getText() );
                    pst.execute();
//                    month=Double.parseDouble(rs.getString("sep"));
                }
                else if(monthselectBox.getValue().toString().equals("October")){ 
                    String query= "UPDATE `customer` SET `oct`=? where email=?";

                    pst=connection.prepareStatement(query);
                    pst.setDouble(1, Double.parseDouble(newUnit.getText()));
                    pst.setString(2,UserEmail.getText() );
                    pst.execute();
//                    month=Double.parseDouble(rs.getString("oct"));
                }
                else if(monthselectBox.getValue().toString().equals("November")){ 
                    String query= "UPDATE `customer` SET `nov`=? where email=?";

                    pst=connection.prepareStatement(query);
                    pst.setDouble(1, Double.parseDouble(newUnit.getText()));
                    pst.setString(2,UserEmail.getText() );
                    pst.execute();
//                    month=Double.parseDouble(rs.getString("nov"));
                }
                else if(monthselectBox.getValue().toString().equals("December")){ 
                    String query= "UPDATE `customer` SET `dece`=? where email=?";

                    pst=connection.prepareStatement(query);
                    pst.setDouble(1, Double.parseDouble(newUnit.getText()));
                    pst.setString(2,UserEmail.getText() );
                    pst.execute();
//                    month=Double.parseDouble(rs.getString("dece"));
                }
          
           
            obj.disconnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(GenerateBillController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
