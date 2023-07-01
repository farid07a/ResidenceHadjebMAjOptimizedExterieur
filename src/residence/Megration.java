/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Basma01
 */
public class Megration {
    
     private Statement stm;
    private ResultSet res;
    
    private ConnectionDB cnx=new ConnectionDB();

    public Megration() {
    }
    
    
    
    
      public void UpdatNBplace(int ID_Room,int NbrsRes){
        PreparedStatement prstm=null;
        String Query="Update Room set Nbr_Bed_Reserved =?  Where id_Room="+ID_Room+"";
        //  cnx.Connecting();
      try {
           prstm=cnx.getConnect().prepareStatement(Query);
           prstm.setInt(1, NbrsRes);
           int x=prstm.executeUpdate();
           if (x>0) {
            //  JOptionPane.showMessageDialog(null, "Update Room");
          }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
        }
      
      try {
        prstm.close();
      //  cnx.Deconnect();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error in Sql :"+e.getMessage());
    }
        
    }
        
    

     public int FilterResident(String Query,JTable tab,DefaultTableModel dm){  //filtrer dans le tableau fournisseur
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(dm);
        tab.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(Query));
        
        return tab.getRowCount();
      }
    
     public void RemplireplaceRoom(JTable TabS,JTable TabR){
      int i=0; 
         System.out.println("residence.Megration.RemplireplaceRoom()"+TabR.getRowCount());
         //JOptionPane.showMessageDialog(null, "TabR.getValueAt(i, 1)" +TabR.getValueAt(0,2) +" "+TabR.getValueAt(i,3));
         cnx.Connecting();
         while (i<TabR.getRowCount()) {             
             String NamRoom=(String) TabR.getValueAt(i,2);            
             int ID_Room=(int) TabR.getValueAt(i,3);
             int NmbrRes=FilterResident(NamRoom, TabS,(DefaultTableModel) TabS.getModel() );
             UpdatNBplace(ID_Room, NmbrRes);
             int j=FilterResident("", TabR,(DefaultTableModel) TabR.getModel() );
             i++;
         }
         
         cnx.Deconnect();
        System.out.println("residence.Megration.RemplireplaceRoom()"+i);
     }
    

    
}
