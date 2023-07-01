/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Basma
 * 
 */
public class Get_Info_DB {
    
    Statement stm;
    ResultSet res;
    ConnectionDB cnx=new ConnectionDB();
    
    public Get_Info_DB(){
    
    }
    public void filling_ArrayList(String Tab,ArrayList<String> List){
         String Query="SELECT * FROM  "+Tab+"" ;
         cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
             res=stm.executeQuery(Query);
            while (res.next()){                
                List.add(res.getString(2));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Get_Info_DB.class.getName()).log(Level.SEVERE, null, ex);
        }  
            try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Close :"+e.getMessage());
        }
    }
    
    
   /***********************FillingCombobo******************/

     public void Filling(JComboBox cmb,String Tab,String Field,int i){
        String Query="SELECT * FROM  "+Tab+"" ;
        
        DefaultComboBoxModel Dfcmb=new DefaultComboBoxModel();
        Dfcmb=(DefaultComboBoxModel) cmb.getModel();
        Dfcmb.removeAllElements();
        cnx.Connecting();
         switch(i){
             case 1:
                 Dfcmb.addElement("اخــتر الولايــة....");
                 break;
             case 2:
                  Dfcmb.addElement("اخــتــر الــجــنــســيــة ....");
                 break;
             case 3:
                  Dfcmb.addElement("الــمـسـتــوي الـدراسـي ");
                 break;
             case 4:
                  Dfcmb.addElement("اختر التخـصص الدراســـــــي ");
                 break;
            
             case 5:
                  Dfcmb.addElement("اخــتــر الـــكـــلــــيــــة ....");
                 break;
                 
             case 6 :
                  Dfcmb.addElement("اخــتر المــهـــنـة....");
                 break;
            
         }
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            
            while (res.next()){                
                Dfcmb.addElement(res.getString(Field));
                
            }
            cmb.setModel(Dfcmb);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.getMessage());
        }
        
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Close :"+e.getMessage());
        }
        
    } 
     
    /*******************************GetIdPatternResident******************************/
    public int GetId_From_DB(String ID,String Table,String Field,String value){
    
        if ((value=="اخــتر الولايــة....")||(value=="اخــتــر الــجــنــســيــة ....")||
                (value=="اختر التخـصص الدراســـــــي ")||(value=="الــمـسـتــوي الـدراسـي ")||(value=="اخــتــر الـــكـــلــــيــــة ....")
                ||(value=="اخــتر المــهـــنـة....")) {
           value="/"; 
        }
   
        
     String Query="SELECT "+ID+" FROM  "+ Table +" WHERE " +Field+ " = N'"+value+"'" ;
        int id=0;
        cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            
           if(res.next()) 
           {          
                id=res.getInt(ID);    
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.getMessage());
        }
        
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Close :"+e.getMessage());
        }
        
        return id;
    }
   /************************************************/
       public String GetNAME_From_DB(String ColName,String Table,String Field_ID,int ID){
        
     String Query="SELECT "+ColName+" FROM  "+ Table +" WHERE " +Field_ID+ " = "+ID+"" ;
         String Name="";
        cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            
           if(res.next()) 
           {          
                Name=res.getString(ColName);    
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.getMessage());
        }
        
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Close :"+e.getMessage());
        }
        
        return Name;
    }
       /************************************************************************/
    public void FillingCase(JComboBox cmb,String pattern){
       DefaultComboBoxModel Dfcmb=new DefaultComboBoxModel();
        Dfcmb=(DefaultComboBoxModel) cmb.getModel();
        Dfcmb.removeAllElements();
       
    String Query1="select Resident_Case \n" +
"From Resident_Case where ID_Case_Resident=1 or ID_Case_Resident=2 or\n" +
"ID_Case_Resident=3 or ID_Case_Resident=4 or ID_Case_Resident=5 or ID_Case_Resident=6\n" +
"or ID_Case_Resident=7";
    String Query2="select Resident_Case \n" +
"From Resident_Case where ID_Case_Resident=8 or ID_Case_Resident=9 ";
    String Query3="select Resident_Case \n" +
"From Resident_Case where ID_Case_Resident=10 or ID_Case_Resident=11 ";
    cnx.Connecting();
   switch(pattern){
           
       case "طالب داخلي" : case "اســـتـاذ" : 
           try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query1);
            
            while (res.next()){                
                Dfcmb.addElement(res.getString(1));
                
            }
            cmb.setModel(Dfcmb);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.getMessage());
        }
        
         break;
       case "طالب خـــارجــي" :
           try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query2);
            
            while (res.next()){                
                Dfcmb.addElement(res.getString(1));
                
            }
            cmb.setModel(Dfcmb);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.getMessage());
        }

           break;
       case "عـــامـــل":
           try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query3);
            
            while (res.next()){                
                Dfcmb.addElement(res.getString(1));
                
            }
            cmb.setModel(Dfcmb);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.getMessage());
        }
        break;
   }
    try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Close :"+e.getMessage());
        }
        
   }
 public void GetAllBranche(JTable table,String ID,String Tab,String C1,String C2){
     String Query="Select "+ID+" , "+C1+" , "+C2+" from "+Tab+"";  
     DefaultTableModel dftabMd=(DefaultTableModel)table.getModel();   
         dftabMd.setRowCount(0); 
     
        cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
             res=stm.executeQuery(Query);
             
             while (res.next()){
               Object arg[]={res.getString(C1),res.getString(C2),res.getInt(ID)};
              dftabMd.addRow(arg);
             }
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL : "+ex.getMessage());
         }
      
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN Close () :"+ex);
        }
      
    
}
public int GetCount(String tab,String ID,int val){
    String Query="Select  Count( "+ID+" ) from "+tab+"  where "+ID+" = "+val+" ";  
  int Count=0;
        cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
             res=stm.executeQuery(Query);
          
             if(res.next()){
              Count=res.getInt(1);
             }
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL : "+ex.getMessage());
         }
      
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN Close () :"+ex);
        }
  return Count;
}
public int GetCountResident(){
    String Query="Select Count(ID_Rsident) from Resident_Gl";  
  int Count=0;
        cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
             res=stm.executeQuery(Query);
          
             if(res.next()){
              Count=res.getInt(1);
             }
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL : "+ex.getMessage());
         }
      
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN Close () :"+ex);
        }
  return Count;
}
public void Update_Branch_Faculty_Nationality(String Tab,String C1,String C2,String NumID,int ID,String val1,String val2){
    PreparedStatement prstm=null;
String Query="UPDATE "+Tab+" SET "+C1+"=? , "+C2+"=? "
            + "  WHERE "+NumID+" = "+ID;
   cnx.Connecting();
    try {
        prstm=cnx.getConnect().prepareStatement(Query);
        prstm.setString(1,val1);
        prstm.setString(2,val2);
        
        
         int x=prstm.executeUpdate();

         if (x>0) {
            //JOptionPane.showMessageDialog(null, "The  Is Update Branch_Faculty_Nationality");
        }else  JOptionPane.showMessageDialog(null, " Error Update Branch_Faculty_Nationality ");
        
    } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Error in SQL Branch_Faculty_Nationality "+e.getMessage());
            
             try {
              cnx.getConnect().rollback();
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :"+ex.getMessage());
          }
        
    }
    
    try {
        prstm.close();
        cnx.Deconnect();
    } catch (Exception e) {
    }
}
 public void  InsertIntoDB(String Tab,String C1,String C2,String Val1,String Val2) {
     PreparedStatement prstm=null;
    String Query="INSERT  "+Tab+" ( "+C1+" , "+C2+" ) "
                  + "VALUES (?,?)";
    cnx.Connecting();
    
        try {
            cnx.getConnect().setAutoCommit(false);
            prstm=cnx.getConnect().prepareStatement(Query);
            prstm.setString(1, Val1);
            prstm.setString(2,Val2);
            int x=prstm.executeUpdate();
            if (x>0) {
              //JOptionPane.showMessageDialog(null, "The  "+ Tab +"  is added ");
            }else 
                JOptionPane.showMessageDialog(null, "Error in added ");
        }catch (Exception e) {    
              JOptionPane.showMessageDialog(null, "Error in Add Student In DataBase Class Student"+e.getMessage());
             if (cnx.getConnect() != null) {
                    try {
                        System.err.print("Transaction is being rolled back");
                        cnx.getConnect().rollback();
                    } catch(SQLException excep) {
                        excep.printStackTrace();
                    }
             }       
        }finally{
        
              try {
                    prstm.close();
                  } catch (Exception e) {
                        e.printStackTrace();
                        }
                try {
                    cnx.getConnect().setAutoCommit(true);
                } catch (SQLException ex) {
                   ex.printStackTrace();
                }
        }
         cnx.Deconnect(); 
    }
     public void FilterResidentMlt(JTextField NumCard,JTextField NameRes,JTextField LastNm,JTextField DateNais,JTable tab,DefaultTableModel dm){  //filtrer dans le tableau fournisseur
       TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(dm);
        tab.setRowSorter(tr);
        
List <RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);


if (! Objects.isNull(NumCard)) filters.add(RowFilter.regexFilter(NumCard.getText()));
if (! Objects.isNull(NameRes) ) filters.add(RowFilter.regexFilter(NameRes.getText()));
if (! Objects.isNull(LastNm)) filters.add(RowFilter.regexFilter(LastNm.getText()));
if (! Objects.isNull(DateNais)) filters.add(RowFilter.regexFilter(DateNais.getText()));

RowFilter rf = RowFilter.andFilter(filters);
tr.setRowFilter(rf); 
}
    void CleanTextFld (JTextField NumCard,JTextField NameRes,JTextField LastNm,JTextField DateNais){
     if (! Objects.isNull(NumCard)) NumCard.setText("");
     if (! Objects.isNull(NameRes)) NameRes.setText("");
     if (! Objects.isNull(LastNm)) LastNm.setText("");
     if (! Objects.isNull(DateNais)) DateNais.setText("");
     }    
    
    
 public int GetCountEtranger(){
String Query1="SELECT Count(Name_Resident) from Nationalite,Resident_Gl,Student_Res  WHERE \n" +
"Resident_Gl.ID_Rsident=Student_Res.ID_Rsident \n" +
"AND Nationalite.Id_Nationalite=Student_Res.Id_Nationalite\n" +
"AND (not (Nationalite=N'جزائرية' or Nationalite='/' ))\n" +
"AND Resident_Gl.ID_Case_Resident=2;";
  int Count=0;
        cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
             res=stm.executeQuery(Query1);
          
             if(res.next()){
              Count=res.getInt(1);
             }
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN SQL : "+ex.getMessage());
         }
      
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error IN Close () :"+ex);
        }
  return Count;
  
 }
 public String CreateQuery(JTable jTable1){
        String Query="(";
        int i=0;
        while (i<jTable1.getRowCount()){    
            if (i<jTable1.getRowCount()-1) {
//            System.out.println("javaapplication95.NewJFrame1.CreateQuery()   "+(int)jTable1.getValueAt(i, 0));
            Query=Query+(String)jTable1.getValueAt(i,0);
            Query=Query+",";
            System.out.println("javaapplication95.NewJFrame1.CreateQuery()   "+Query);
            }else
            {
                
             Query=Query+(String)jTable1.getValueAt(i,0)+"";
              Query=Query+")";
            }
            i++;
        }
   return Query;
  }

 public void PrinListStd_Restaurant(String Query,String Repat,String DateRep){
        try {
             JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/ListStdRestaurant.jrxml");
           //NewReportCard
            JasperDesign jasperdesign=JRXmlLoader.load(file);
          JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(Query);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
            Map parametres=new HashMap<String ,Object>();
            parametres.put("RepatNm", Repat);
            parametres.put("DateRepat", DateRep);
           //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres,cnxt);
           
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
 
 public void printSelectedCard(String SS){

        try {
             JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/CardSelected.jrxml");
           //NewReportCard
            JasperDesign jasperdesign=JRXmlLoader.load(file);
          JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(SS);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
            Map parametres=new HashMap<String ,Object>();
            parametres.put("TypRes", "بطاقة الطالب المقيم");
           //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres,cnxt);
           
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
 
public void printSelectedCardEmployer(String SS){
        try {
             JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/CardSelctedEmployer.jrxml");
           //NewReportCard
            JasperDesign jasperdesign=JRXmlLoader.load(file);
          JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(SS);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
            Map parametres=new HashMap<String ,Object>();
            parametres.put("TypRes", "بطاقةعامل");
           //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
           JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres,cnxt);
           
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void printSelectedCardExterStd(String SS){
        try {
             JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/CardSelectExternal.jrxml");
           //NewReportCard
            JasperDesign jasperdesign=JRXmlLoader.load(file);
          JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(SS);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
            Map parametres=new HashMap<String ,Object>();
            parametres.put("TypRes", "بطاقةعامل");
           //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
           this.cnx.Connecting();
           Connection cnxt=this.cnx.getConnect();
          JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,null,cnxt);
        
    // JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres,cnxt);
           
            JasperViewer JspViewr=new JasperViewer(jasperprint, false);
              JspViewr.viewReport(jasperprint,false);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public int Del_Nat_Lvl_Brn(String Tab,String Champ,int ID){
    PreparedStatement prst=null;
    int x=0;    
    String Query="Delete From "+Tab+" where "+Champ+ "="+ID+"";
        cnx.Connecting();
        try {
            prst=cnx.getConnect().prepareStatement(Query);
            x=prst.executeUpdate();
            if (x>0) {
                JOptionPane.showMessageDialog(null  , "لقد تمت عملية الحذف بنجاح");
            }
            prst.close();
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null  , "Error in Delete "+e.getMessage());
        }
        
        cnx.Deconnect();
        return x;
    }

public void DesignTable(JTable table,int NumCol,int Size){
table.getColumnModel().getColumn(NumCol).setPreferredWidth(Size);
}


 public void Filling_Comb_NewExtrnalANDnot(JComboBox cmb/*,String Tab,String Field,String Value1,String Value2*/){
        //String Query="SELECT "+Field+"FROM  "+Tab+" where "+Field+"=N'"+Value1+" or "+Field+"=N'"+Value2 ;
        String Query01="SELECT Resident_Case FROM Resident_Case WHERE ID_Case_Resident=8 OR  ID_Case_Resident=9";
        DefaultComboBoxModel Dfcmb=new DefaultComboBoxModel();
        Dfcmb=(DefaultComboBoxModel) cmb.getModel();
        Dfcmb.removeAllElements();
       cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query01);
            
            while (res.next()){                
                Dfcmb.addElement(res.getString("Resident_Case"));
               // System.err.println("The valis"+res.getString(Field));
            }
            cmb.setModel(Dfcmb);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error SQL :"+e.getMessage());
        }
        
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Close :"+e.getMessage());
        }
        
    } 
public int GetIdResidentByNumCard(int NumCard){
     String Query="select ID_Rsident FROM Resident_Gl WHERE NumCard_Resident="+NumCard+"";
           cnx.Connecting();
           Statement stm=null;
           ResultSet res=null;
           int IdResident=0;
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            if (res.next()) {
                IdResident=res.getInt("ID_Rsident");
            }
        }catch(SQLException ex){
        
        }
         try {
             stm.close();
             res.close();
             cnx.Deconnect();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error In Sql GetIdResidentByNumCard :"+e.getMessage());
         }
     return IdResident;
     }

public void GetNameTable(){
    
    ResultSet resTab=null;
    Statement stm=null;
     ArrayList<String> listTable=new ArrayList<String> ();
    cnx.Connecting();
    
    String Query="CREATE TABLE Manager " +
"( id_Manager int  IDENTITY(1,1) not NULL, " +
"  FirstName NVARCHAR(255) ,  " +
"  LastName NVARCHAR(255) , " +
"  Num_Decision INTEGER," +
"  Date_Decision datetime,"+
 " kind_Manager int "+          
"  PRIMARY KEY ( id_Manager ));";
        try {
            DatabaseMetaData dmd=cnx.getConnect().getMetaData();
            String Type[]={"TABLE"};
            resTab =dmd.getTables(null, null, null, Type);
            int i=1;
           
            while(resTab.next()){
                
             /*   if (resTab.getString("TABLE_NAME").equals("Student_Res")) {
                    System.out.println("residence.Get_Info_DB.GetNameTable()----"+resTab.getString("TABLE_NAME"));
                }else
                {         if (resTab.) {
                        System.out.println("I am last row ");
                    }
                }*/
             
                System.out.println("Table Name :"+resTab.getString("TABLE_NAME")+" Num: "+i);
                listTable.add(resTab.getString("TABLE_NAME"));
         i++;
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error In Get Names Of Tables Database "+ex.getMessage());
        }
        
        if (listTable.contains("Manager")) {
            System.out.println("The Table Is Obtained ");
            System.out.println("Index Of Table Student Residets :"+listTable.indexOf("Student_Res"));
            
            
            
            
        }else {
            
            System.out.println("No Item Found ......!");
                    
        try {
            stm=cnx.getConnect().createStatement();
           stm.execute(Query);
        } catch (SQLException ex) {
            System.err.println("Error Sql Create Table :"+ex.getMessage());
        }
                    
        
            try {
                stm.close();
                cnx.Deconnect();
            } catch (SQLException  e) {
                e.printStackTrace();
            }
                    }
    
    }
    public static void main(String[] args) {
      new Get_Info_DB().printSelectedCardExterStd("Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,Branch_Study.BranchStd_Name,CodeBare_Resident,imageStd\n" +
"FROM  Resident_Gl,Student_ResExt,Branch_Study Where \n" +
"Resident_Gl.ID_Rsident=Student_ResExt.ID_Rsident\n" +
"AND Branch_Study.Id_BranchStd=Student_ResExt.Id_BranchStd\n" +
"AND  NumCard_Resident IN (4997);");
    }
    
}
