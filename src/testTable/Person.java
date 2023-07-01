/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import residence.ConnectionDB;

/**
 *
 * @author farid
 */
public class Person {
        
        private int Id;
        private String Name;
        private byte[] image;

    public Person(int Id, String Name, byte[] image) {
        this.Id = Id;
        this.Name = Name;
        this.image = image;
    }

    public Person() {
    }
    
    

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public ArrayList <Person> getData(){
    
        ConnectionDB cnx=new ConnectionDB();
        String Query ="SELECT ID_Rsident,Name_Resident,imageStd FROM Resident_Gl ";
        Statement stm;
        ResultSet res;
        cnx.Connecting();
        System.out.println("passe connecting....");
        ArrayList<Person> listData=new ArrayList<>();
        
        try {
            System.out.println("");
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            System.out.println("Pass ExecuteQuery ...");
            res.next();
            for (int i = 0; i < 20; i++) {
                
                Person p=new Person(res.getInt("ID_Rsident"), res.getString("Name_Resident"), res.getBytes("imageStd"));
                System.out.println(p.getId() +" "+ p.getName()+ " " );
                
                listData.add(p);
            res.next();
            }
            
            stm.close();
            res.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    return listData;
    }
    
    public static void main(String[] args) {
        
        Person obj = new Person();
        
        ArrayList <Person> lstPrs ;
        
        lstPrs = obj.getData();
        
    }
}

