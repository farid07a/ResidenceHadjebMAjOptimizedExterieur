/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author farid
 */
public class Branche {

    private int Id_BranchStd;
    private String BranchStd_Name;
    private String BranchStd_NameFr;
    private String Description;
    private String branch_code;
    private String domaine_Code;
    private String domaine_Label;
    private String domaine_Label_ar;
    private static residence.ConnectionDB ConnectionObj = new ConnectionDB(); // Residence package connection 
    private static Statement Stm;
    private static ResultSet Res;

    public Branche(int Id_BranchStd, String BranchStd_Name, String BranchStd_NameFr,
            String Description, String branch_code, String domaine_Code, String domaine_Label,
            String domaine_Label_ar) {
        this.Id_BranchStd = Id_BranchStd;
        this.BranchStd_Name = BranchStd_Name;
        this.BranchStd_NameFr = BranchStd_NameFr;
        this.Description = Description;
        this.branch_code = branch_code;
        this.domaine_Code = domaine_Code;
        this.domaine_Label = domaine_Label;
        this.domaine_Label_ar = domaine_Label_ar;
    }

    public int getId_BranchStd() {
        return Id_BranchStd;
    }

    public void setId_BranchStd(int Id_BranchStd) {
        this.Id_BranchStd = Id_BranchStd;
    }

    public String getBranchStd_Name() {
        return BranchStd_Name;
    }

    public void setBranchStd_Name(String BranchStd_Name) {
        this.BranchStd_Name = BranchStd_Name;
    }

    public String getBranchStd_NameFr() {
        return BranchStd_NameFr;
    }

    public void setBranchStd_NameFr(String BranchStd_NameFr) {
        this.BranchStd_NameFr = BranchStd_NameFr;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getDomaine_Code() {
        return domaine_Code;
    }

    public void setDomaine_Code(String domaine_Code) {
        this.domaine_Code = domaine_Code;
    }

    public String getDomaine_Label() {
        return domaine_Label;
    }

    public void setDomaine_Label(String domaine_Label) {
        this.domaine_Label = domaine_Label;
    }

    public String getDomaine_Label_ar() {
        return domaine_Label_ar;
    }

    public void setDomaine_Label_ar(String domaine_Label_ar) {
        this.domaine_Label_ar = domaine_Label_ar;
    }

    public static ConnectionDB getConnectionObj() {
        return ConnectionObj;
    }

    public static void setConnectionObj(ConnectionDB ConnectionObj) {
        Branche.ConnectionObj = ConnectionObj;
    }

    public static Statement getStm() {
        return Stm;
    }

    public static void setStm(Statement Stm) {
        Branche.Stm = Stm;
    }

    public static ResultSet getRes() {
        return Res;
    }

    public static void setRes(ResultSet Res) {
        Branche.Res = Res;
    }

    public static ArrayList<Branche> getListObjectBranche() throws SQLException {
        String Query = "select * from Branch_Study";
        ArrayList<Branche> ListObjBranche = new ArrayList<>();
        //Statement stm=null;        
        getConnectionObj().Connecting();

        setStm(getConnectionObj().getConnect().createStatement());
        setRes(getStm().executeQuery(Query));
        while (getRes().next()) {
            ListObjBranche.add(new Branche(getRes().getInt(1), getRes().getString(2), getRes().getString(3),
                    getRes().getString(4), getRes().getString(5), getRes().getString(6),
                    getRes().getString(7), getRes().getString(8)));
        }

        return ListObjBranche;

    }

    public static ArrayList<String> getListDomaineName(String NameField) throws SQLException { // Field Domaine_Ar for Name Domaine AR
        //Domaine_Fr for DomaineNameFr
        ArrayList<String> ListDomaineNames = new ArrayList<>();

        String Query = "SELECT " + NameField + " FROM Domaine ";

        getConnectionObj().Connecting();
        setStm(getConnectionObj().getConnect().createStatement());
        setRes(getStm().executeQuery(Query));
        while (getRes().next()) {
            ListDomaineNames.add(getRes().getString(1));
        }
        
        return ListDomaineNames;
    }

//    
//    public void Update_Branch_(String BranchStd_Name,String BranchStd_NameFr,String branch_code,String domaine_Code,
//         String domaine_Label,String domaine_Label_ar,int ID,int choice){
//    PreparedStatement prstm=null;
//    String Query;
//     if (choice==0) {
//         Query="insert into Branch_Study (BranchStd_Name,BranchStd_NameFr,branch_code,domaine_Code,domaine_Label,domaine_Label_ar) "
//        + " values (N'"+BranchStd_Name+"','"+BranchStd_NameFr+"','"+branch_code+"','"+domaine_Code+"','"+domaine_Label+"',N'"+domaine_Label_ar+"' )";
//     } else if(choice==1){
//         Query="UPDATE Branch_Study SET BranchStd_Name=N'"+BranchStd_Name+"' , BranchStd_NameFr='"+BranchStd_NameFr+"' ,"
//        + " branch_code='"+branch_code+"' ,domaine_Code='"+domaine_Code+"',domaine_Label='"+domaine_Label+"', domaine_Label_ar=N'"+domaine_Label_ar+"' "
//            + "  WHERE Id_BranchStd = "+ID;
//          }else {
//     Query ="Delete From Branch_Study where Id_BranchStd ="+ID+" ";
//     }
// 
//   cnx.Connecting();
//    try {
//        prstm=cnx.getConnect().prepareStatement(Query);
//        
//         int x=prstm.executeUpdate();
//
//         
//    } catch (SQLException e) {
//              JOptionPane.showMessageDialog(null, "Error in SQL Branch_Faculty_Nationality "+e.getMessage());
//    }
//    
//    try {
//        prstm.close();
//        cnx.Deconnect();
//    } catch (Exception e) {
//    }
//}

    
    public void insert_branche()throws SQLException{
    
        String Query="insert into Branch_Study (BranchStd_Name,BranchStd_NameFr,branch_code,domaine_Code,domaine_Label,domaine_Label_ar) "
        + " values (N'"+BranchStd_Name+"','"+BranchStd_NameFr+"','"+branch_code+"','"+domaine_Code+"','"+domaine_Label+"',N'"+domaine_Label_ar+"' )";
        if (RunDMLQuery(Query)>0)
            System.out.println("Succes Insert Item Branch in db");
        else
            System.out.println("No Items Insert In DB");
        
    }
    
    public void update_branche(int ID)throws SQLException{
    
        String Query="UPDATE Branch_Study SET BranchStd_Name=N'"+BranchStd_Name+"' , BranchStd_NameFr='"+BranchStd_NameFr+"' ,"
        + " branch_code='"+branch_code+"' ,domaine_Code='"+domaine_Code+"',domaine_Label='"+domaine_Label+"', domaine_Label_ar=N'"+domaine_Label_ar+"' "
            + "  WHERE Id_BranchStd = "+ID;

        if (RunDMLQuery(Query)>0)
            System.out.println("Succes Update Item Branch in db");
        else
            System.out.println("No Items Update In DB");
        
    }
    
    public void delete_branche(int ID)throws SQLException{
    
        String Query="Delete From Branch_Study where Id_BranchStd ="+ID+""; 
        RunDMLQuery(Query);
        if (RunDMLQuery(Query)>0)
            System.out.println("Succes Delete Item Branch in db");
        else
            System.out.println("No Items Delete In DB");
    }
    
    private int RunDMLQuery(String Query) throws SQLException{
    
        getConnectionObj().Connecting();
        //prstm=getConnectionObj().getConnect().prepareStatement(Query);
        setStm(getConnectionObj().getConnect().createStatement());
        int CounRes;
        CounRes = getStm().executeUpdate(Query);
        getStm().close();
        getConnectionObj().Deconnect();
        return CounRes;
    }
    
    
    
    

    public static void main(String[] args) {
        Branche obj_branche=new Branche(1, "طب رياضي", "Medcine Sport", "Medcine Sport", "M02", "M02", "Medcine Sport", "طب رياضي");
        try {
            obj_branche.insert_branche();
        } catch (SQLException ex) {
            Logger.getLogger(Branche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}