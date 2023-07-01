/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
 * @author admin
 */
public class Professor_Res extends Resident_Gl{
    private int ID_Rsident;
    private  int Id_Room;
    private int ID_Profession;
    //ConnectionDB cnx=new ConnectionDB();
    Statement stm;
    ResultSet res;
    Resident_Gl resident_Gl=new Resident_Gl();
    /*public Professor_Res(int ID_Rsident,int Id_Room,int ID_Profession){
    this.ID_Rsident=ID_Rsident;
    this.Id_Room=Id_Room;
    this.ID_Profession=ID_Profession;
    
    }*/

    public Professor_Res(String first_name, String last_name, int Numbre_CardResDate, String Barcode, Date DateBirth, String placeBirth, int ID_gender, int Id_Ptrn_Res,int CaseRes,
           int ID_Profession, int Id_Room, int ID_Rsident ) {
        super(first_name, last_name, Numbre_CardResDate, Barcode, DateBirth, placeBirth, ID_gender, Id_Ptrn_Res,CaseRes);
        this.ID_Profession = ID_Profession;
        this.Id_Room = Id_Room; 
       // this.ID_Rsident = super.Get_MAX_Numbre_CardRes();
    }

     public Professor_Res(){
     
     }
    
    @Override
    public void AddRsident() {
         int IdRoom=new Room().Get_ID_Room("/");
         PreparedStatement prstm=null;
         int ValMax=0 ;
          String Query ="INSERT INTO Professor_Res(ID_Profession,Id_Rsident,Id_Room) VALUES (?,?,?)";
      String QuerySlct="SELECT MAX(ID_Rsident) FROM Resident_Gl";
      
        //To change body of generated methods, choose Tools | Templates.
    
     cnx.Connecting(); 

        try {
            cnx.getConnect().setAutoCommit(false);
            
             super.AddRsident();
             stm=cnx.getConnect().createStatement();
               res=stm.executeQuery(QuerySlct);        
               
               if (res.next()) {
                  ValMax = res.getInt(1); 
                   System.out.println("residence.Employer.AddRsident() Max ID"+ValMax);
               }
            
            prstm=cnx.getConnect().prepareStatement(Query);
            prstm.setInt(1, this.ID_Profession);
            prstm.setInt(2, ValMax);
            prstm.setInt(3, IdRoom);
            int x=prstm.executeUpdate();
            if (x>0) {
                //JOptionPane.showMessageDialog(null, "The Professor  Is Added");
            } else {
                JOptionPane.showMessageDialog(null, "The resident Isn't Added");
            }
            cnx.getConnect().commit();
            setValConfiramation(1);
            
        } catch (Exception e) {
            setValConfiramation(0);
            
                    JOptionPane.showMessageDialog(null, "Error Sql In Add Professor Class Professor:"+e.getMessage());
              //JOptionPane.showMessageDialog(null, "Error in sql"+e.getMessage());
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
        try {
            cnx.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Deconnect"+e.getMessage());
        }
        
    }
    /**********************************************************************************/
    @Override
    public void UpdateResident(int ID_Resident, int NumCard){  
       
         PreparedStatement prstm=null;
       // int ID_Resident=Get_ID_Resident(NumCard);
           String Query="UPDATE Resident_Gl SET Name_Resident=? ,LastName_Resident=? ,"
            + " DateBirth=? ,PlaceBirth=?,ID_gender=?,ID_Case_Resident=?  WHERE ID_Rsident="+ID_Resident;
           
            String QueryUpdPrf="UPDATE Professor_Res  SET ID_Profession=?"+"  WHERE ID_Rsident="+ID_Resident;
     cnx.Connecting();
            try {
             cnx.getConnect().setAutoCommit(false);
                 
             
        super.UpdateResident( ID_Resident, NumCard);
        
        prstm=cnx.getConnect().prepareStatement(QueryUpdPrf);
        prstm.setInt(1, this.ID_Profession);
        int x=prstm.executeUpdate();
                  if (x>0) {
            //JOptionPane.showMessageDialog(null, "The Resident Is Update ");
        }else{  JOptionPane.showMessageDialog(null, " Error Update The Resident ");
                  }
             cnx.getConnect().commit();
    
             setValConfiramation(1);
        }catch(Exception e){
                setValConfiramation(0);
                          JOptionPane.showMessageDialog(null, "Error in SQL Update Professor Class Professor "+e.getMessage());
             try {
              cnx.getConnect().rollback();
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :"+ex.getMessage());
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

    /************************controle Saisier Data Professor********************/
    public boolean ControlSaisiInf_Student(String Barcode, String Field) {
        String Query="SELECT ID_Student,first_name,last_name FROM Student WHERE "+Field+"='"+Barcode+"'";
        boolean etat=false;
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            
            if(res.next()){
               // JOptionPane.showMessageDialog(null, "Il'exist Déja : ID:"+res.getInt(1)+" Name:"+res.getString(2));
                etat= true;
                
            }else{
            
                etat= false;
            }
    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error in sql:"+ex.getMessage());
        }
        
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error in sql:"+ex.getMessage());
        }
        return etat;
    }
  /****************************************************************************/  
    public void ServicesClose(){
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in close "+e.getMessage());
        }
    }
    
    public void DisplayTabToTakeRoomProf(JTable tabRes){
  String Query="select Professor_Res.ID_Rsident,NumCard_Resident,Name_Resident,LastName_Resident,DateBirth,PlaceBirth, \n" +
"          ID_Case_Resident,Room_Code\n" +
"         from Resident_Gl,Professor_Res,Room\n" +
"        Where Professor_Res.ID_Rsident=Resident_Gl.ID_Rsident\n" +
"         and (Professor_Res.Id_Room=Room.Id_Room )";
      DefaultTableModel dftabMd=(DefaultTableModel)tabRes.getModel();   
        dftabMd.setRowCount(0);
     
    cnx.Connecting();
    get_Info_DB.filling_ArrayList("Resident_Case", ListInfo);
     try {
             stm=getCnx().getConnect().createStatement();
             res=stm.executeQuery(Query);
                     while (res.next()) {
                  Object arg[]={ListInfo.get(res.getInt("ID_Case_Resident")-1),res.getString("Room_Code"),"اســـتـاذ",
                  res.getString("PlaceBirth"),res.getDate("DateBirth"),res.getString("Name_Resident"),
                      res.getString("LastName_Resident"),res.getInt("NumCard_Resident"),res.getInt("ID_Rsident")};      
                         System.out.println("residence.Professor_Res.DisplayTabToTakeRoomProf()"+1);
                  dftabMd.addRow(arg);
             }
                       
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error in sql :"+e.getMessage());
         }

    
    }
  
    public void PrintEnsemble(int x){
    
String Query="Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,CodeBare_Resident,imageStd \n" +
"FROM  Resident_Gl\n" +
"WHERE\n" +
" Id_Ptrn_Res="+x+";";
        try {
             JasperReport jasperreport;          
          // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file=getClass().getResourceAsStream("/Reports/ReportEnsPrf_Emp.jrxml");
           //NewReportCard
            JasperDesign jasperdesign=JRXmlLoader.load(file);
          JRDesignQuery newQuery=new JRDesignQuery();
           newQuery.setText(Query);
           jasperdesign.setQuery(newQuery);
           jasperreport=JasperCompileManager.compileReport(jasperdesign);
            Map parametres=new HashMap<String ,Object>();
            parametres.put("TypRes", "بطاقـة الاطعام");
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
    
    public static void main(String[] args) {
       Professor_Res prf=new Professor_Res("mmmmmmmm", "mmmmmmmm", 14523, "10000", new Date(), "batna", 1, 2, 1,7 , 0, 2);
    //prf.UpdateResident(prf.Get_ID_Resident(5377),5377);
    prf.PrintEnsemble(4);
    }
    
    
}
