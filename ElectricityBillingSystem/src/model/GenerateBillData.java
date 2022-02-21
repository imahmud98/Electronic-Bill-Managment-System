/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class GenerateBillData {
    String name;
    String id;
    String generatebill;
    
    public GenerateBillData(String name, String id, String generatebill){
        this.name = name;
        this.id = id;
        this.generatebill = generatebill;
    }
    
    
    public void setName(String s){
        this.name = s;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setId(String a){
        this.id = a;
    }
    public String getId(){
        return this.id;
    }
    public void setGeneratebill(String a){
        this.generatebill = a;
    }
    public String getGeneratebill(){
        return this.generatebill;
    }
}
