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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Database_Connector;
import model.GenerateBillData;


public class GenerateBillController implements Initializable {
    
    ObservableList<String> action = FXCollections.observableArrayList("Select Month", "January", "February","March","April","May","Jun","July","August","September","October","November","December");
    @FXML
    private JFXTextField customerID;
    @FXML
    private ChoiceBox monthBox;
    @FXML
    private TableView<GenerateBillData> generate_bill_info;
    @FXML
    private TableColumn<GenerateBillData, String> col_name;
    @FXML
    private TableColumn<GenerateBillData, String> col_id;
    @FXML
    private TableColumn<GenerateBillData, String> col_generatebill;
    String name,id,contact,pin,meter;
    Double month;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        monthBox.setValue("Select Month");
        monthBox.setItems(action);
        initTable();
    }    
    
    ObservableList<GenerateBillData> table_data = FXCollections.observableArrayList();
    
    private void initTable(){
        
        initCols();
    }
    private void initCols(){
    col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
    col_id.setCellValueFactory(new PropertyValueFactory<>("id")); 
    col_generatebill.setCellValueFactory(new PropertyValueFactory<>("generatebill")); 
    }

    @FXML
    private void GenerateBill(ActionEvent event) {
        try {
            // TODO
            Database_Connector obj=new Database_Connector();
            Connection connection=obj.getConnection();
            PreparedStatement pst=null;
            
            ResultSet rs=null;

            
  
            String cash= "Select * from customer where id=?";

            pst=connection.prepareStatement(cash);
            pst.setString(1,customerID.getText() );
            rs=pst.executeQuery();

            while(rs.next()){
                name=rs.getString("name");
                id=rs.getString("id");
                contact=rs.getString("contact");
                pin=rs.getString("pin");
                meter=rs.getString("meter");
                
                if(monthBox.getValue().toString().equals("January")){                
                    month=Double.parseDouble(rs.getString("jan"));
                }
                else if(monthBox.getValue().toString().equals("February")){                
                    month=Double.parseDouble(rs.getString("feb"));
                }
                else if(monthBox.getValue().toString().equals("March")){                
                    month=Double.parseDouble(rs.getString("mar"));
                }
                else if(monthBox.getValue().toString().equals("April")){                
                    month=Double.parseDouble(rs.getString("apr"));
                }
                else if(monthBox.getValue().toString().equals("May")){                
                    month=Double.parseDouble(rs.getString("may"));
                }
                else if(monthBox.getValue().toString().equals("June")){                
                    month=Double.parseDouble(rs.getString("june"));
                }
                else if(monthBox.getValue().toString().equals("July")){                
                    month=Double.parseDouble(rs.getString("july"));
                }
                else if(monthBox.getValue().toString().equals("August")){                
                    month=Double.parseDouble(rs.getString("aug"));
                }
                else if(monthBox.getValue().toString().equals("September")){                
                    month=Double.parseDouble(rs.getString("sep"));
                }
                else if(monthBox.getValue().toString().equals("October")){                
                    month=Double.parseDouble(rs.getString("oct"));
                }
                else if(monthBox.getValue().toString().equals("November")){                
                    month=Double.parseDouble(rs.getString("nov"));
                }
                else if(monthBox.getValue().toString().equals("December")){                
                    month=Double.parseDouble(rs.getString("dece"));
                }
                
                
            }
            
             table_data.add(new GenerateBillData("","","CUSTOMER INFO"));
            generate_bill_info.setItems(table_data);
            
            table_data.add(new GenerateBillData(name,id,"Name: "+name));
            generate_bill_info.setItems(table_data);
            
            table_data.add(new GenerateBillData("","","ID: "+id));
            generate_bill_info.setItems(table_data);
            
            table_data.add(new GenerateBillData("","","Pin No: "+pin));
            generate_bill_info.setItems(table_data);
            
            table_data.add(new GenerateBillData("","","Contact:"+contact));
            generate_bill_info.setItems(table_data);
            
            table_data.add(new GenerateBillData("","",""));
            generate_bill_info.setItems(table_data);
            
            table_data.add(new GenerateBillData("","","BILL INFO"));
            generate_bill_info.setItems(table_data);
            
            table_data.add(new GenerateBillData("","","Meter No: "+meter));
            generate_bill_info.setItems(table_data);
            
            table_data.add(new GenerateBillData("","","Current Month Unit: "+month));
            generate_bill_info.setItems(table_data);
            
            //vat calclation and table update
            double PresentMonthCost = 0.0;
            
            if(month<=200){
                PresentMonthCost = month*5.72; 
            }
            else{
                PresentMonthCost = month*9.94;
            }
            
            double tax = PresentMonthCost + 30;
            
            
            table_data.add(new GenerateBillData("","","Cost Without Tax: "+PresentMonthCost));
            generate_bill_info.setItems(table_data);
            
            table_data.add(new GenerateBillData("","","Cost With Tax: "+tax));
            generate_bill_info.setItems(table_data);
           
            obj.disconnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(GenerateBillController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
    
}
