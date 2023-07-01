/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Basma01
 */
public class Get_Nev_Inf {
    Home1 h;
    Statement stm;
    ResultSet res;
    ConnectionDB cnx=new ConnectionDB();
    //ConnectionDB2 cnx2=new ConnectionDB2();
    public  Get_Nev_Inf(Home1 hom){
       this.h=hom;
    }
    
     public int  Get_Info_Nev_Res(String Numinsc,JTextField txtNam_std,JTextField txtSurNam_std,
          JFormattedTextField DatBirth_std,JTextField txtPlcBirth_std,JTextField txtNam_Father,JTextField txtNam_mother,
                JTextField LastNamMothARTxt,JTextField txtCommuneStd,
                JComboBox National_list,JCheckBox CheckMale,JCheckBox checkFemal,
                JComboBox txtBranch_std,JComboBox LevelStd,int AnnBac){
      
       String str=AnnBac+"-"+(AnnBac+1);  
      String Query ="Select * from Nev_Resident where [Matricule du bac] = '"+Numinsc+"' AND [Année académique]='"+str+"'";
        cnx.Connecting();
       int  i=-1;
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);        
           if(res.next()) {       
               txtNam_std.setText(res.getString("Prénom arabe"));
               System.out.println("   "+txtNam_std.getText());
               txtSurNam_std.setText(res.getString("Nom arabe"));
               txtSurNam_std.setForeground(Color.blue);
               System.out.println(" "+txtSurNam_std.getText());
               SimpleDateFormat s=new SimpleDateFormat("dd/MM/yyyy");
               String date=s.format(res.getDate("Date de naissance"));
               DatBirth_std.setForeground(Color.blue);
               DatBirth_std.setText(date); 
               /*txtPlcBirth_std.setText(res.getString("Commune"));
               txtPlcBirth_std.setForeground(Color.blue);
               System.out.println("  "+DatBirth_std.getText());*/
               txtNam_Father.setForeground(Color.blue);
               txtNam_Father.setText(res.getString("Prénom père arabe"));
               txtNam_mother.setForeground(Color.blue);
               System.out.println("  "+txtNam_Father);
               txtNam_mother.setText(res.getString("Prénom mère arabe"));
               LastNamMothARTxt.setForeground(Color.blue);
               System.out.println("  "+txtNam_mother);
               LastNamMothARTxt.setText(res.getString("Nom mère arabe"));
               txtCommuneStd.setForeground(Color.blue);
               System.out.println("  "+LastNamMothARTxt);
               txtCommuneStd.setText(res.getString("Commune"));
               System.out.println("  "+txtCommuneStd);
               National_list.setSelectedIndex(1);
               CheckMale.setSelected(false);
               checkFemal.setSelected(true);
               //String Branche=res.getString("Code Filière");
               String NameBranch =res.getString("Domaine");
               //GetNameBranchArab(Branche);
               txtBranch_std.setSelectedItem(GetNameBranchArab(NameBranch));
               /*
                if(Branche.equals("B01"))txtBranch_std.setSelectedIndex(24);
                if(Branche.equals("D01"))txtBranch_std.setSelectedIndex(32);
                if(Branche.equals("C01"))txtBranch_std.setSelectedIndex(28);
                if(Branche.equals("F01"))txtBranch_std.setSelectedIndex(44);
                if(Branche.equals("N03"))txtBranch_std.setSelectedIndex(43);
                if(Branche.equals("B00"))txtBranch_std.setSelectedIndex(24);
                if(Branche.equals("D00"))txtBranch_std.setSelectedIndex(32);
                if(Branche.equals("F00"))txtBranch_std.setSelectedIndex(44);//44
                if(Branche.equals("C00"))txtBranch_std.setSelectedIndex(29);
                if(Branche.equals("N00"))txtBranch_std.setSelectedIndex(2);
                */
                
                
                
                
                //C00 29  /N00
                LevelStd.setSelectedIndex(1);
               
               
              h.NamFR=res.getString("Prénom latin");
           //    JOptionPane.showMessageDialog(null, " "+h.NamFR);
               h.PrénomFR=res.getString("Nom latin");
               h.PlcFR=res.getString("Commune");
             
             ///  NamFatheAr=res.getString("[Prénom père arabe]");
               h.NamFatheFr=res.getString("Prénom père latin");
              // System.out.println("  "+NamFatheAr+"  "+NamFatheFr);
               
               h.NamMatherFR=res.getString("Prénom mère arabe");
             
               h.LastMatherFR=res.getString("Nom mère latin");
                // JOptionPane.showMessageDialog(null, " "+ h.LastMatherFR);
                 i= 1;
               }else {
               
                    i= -1; 
               
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL In Get Data :"+e.getMessage());
        }
        
         try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Close :"+e.getMessage());
        }
   return i;
  }
    
     public String GetNameBranchArab(String NameBranchFr){
         
     Statement stm=null;
     ResultSet res=null;
     String NameBrnAr="";
     String Query="Select BranchStd_Name FROM Branch_Study WHERE BranchStd_NameFr='"+NameBranchFr+"'";
     //cnx.Connecting();
         try {
             stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
             if (res.next()) {
                 
                 NameBrnAr=res.getString("BranchStd_Name");
             }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"Error In get Name Branch Arabe"+e.getCause());
         }
         try {
             stm.close();
             res.close();
       //      cnx.Deconnect();
         } catch (Exception e) {
             
         }
     return NameBrnAr;
     }
     
}
