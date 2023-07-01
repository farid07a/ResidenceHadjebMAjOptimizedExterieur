/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

//import com.sun.org.apache.bcel.internal.generic.DADD;
import java.awt.HeadlessException;
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
import net.sf.jasperreports.engine.JRException;
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
 * @author Basma01
 */
public class ExternalStudent extends Resident_Gl {

    private int ID_Rsident;
    private String Num_InscritBac;
    //private String Address;
    //private int ID_Wilaya;
    //private int Id_Nationalite;
    //private int BacYear;
    private int Id_BranchStd;
    //private int Id_Faculty;
    private int Id_LevelStudy;
    private Date DateInscrp;

    Statement stm;
    ResultSet res;
    //private ConnectionDB cnx=new ConnectionDB();

    public ExternalStudent() {
    }

    public ExternalStudent(String first_name, String last_name, int Numbre_CardResDate, String Barcode,
            Date DateBirth, String placeBirth, int ID_gender, int Id_Ptrn_Res, int CaseRes,
            String Num_InscritBac, int Id_BranchStd, int Id_LevelStudy, Date DateInscrp, int ID_Rsident) {

        super(first_name, last_name, Numbre_CardResDate, Barcode, DateBirth, placeBirth, ID_gender, Id_Ptrn_Res, CaseRes);
        // System.out.println(1);
        //  this.ID_Wilaya=ID_Wilaya;
        //this.Address=Address;
        //this.Id_Nationalite=Id_Nationalite;
        this.Num_InscritBac = Num_InscritBac;
        //this.BacYear=BacYear; 
        //this.Id_Faculty=Id_Faculty;
        this.Id_BranchStd = Id_BranchStd;
        this.Id_LevelStudy = Id_LevelStudy;
        this.DateInscrp = DateInscrp;
        this.ID_Rsident = ID_Rsident;
    }

    /**
     * *******************************************
     */
    @Override
    public void AddRsident() {

        String Query = "INSERT INTO Student_ResExt (Num_InscritBac,Id_BranchStd,Id_LevelStudy,DateInscrp,ID_Rsident) "
                + "VALUES (?,?,?,?,?)";
        PreparedStatement prstm = null;
        int ValMax = 0;
        String QuerySlct = "SELECT MAX(ID_Rsident) FROM Resident_Gl";
        cnx.Connecting();

        try {
            cnx.getConnect().setAutoCommit(false);

            super.AddRsident(); //To change body of generated methods, choose Tools | Templates.

            stm = cnx.getConnect().createStatement();
            res = stm.executeQuery(QuerySlct);

            if (res.next()) {
                ValMax = res.getInt(1);
                System.out.println("residence.Employer.AddRsident() Max ID" + ValMax);
            }
            //prstm = cnx.getConnect().prepareStatement(Query);            
            prstm = cnx.getConnect().prepareStatement(Query);
            prstm.setString(1, this.Num_InscritBac);
            prstm.setInt(2, this.Id_BranchStd);
            prstm.setInt(3, this.Id_LevelStudy);
            prstm.setDate(4, new java.sql.Date(new Date().getTime()));
            prstm.setInt(5, ValMax);
            int x = prstm.executeUpdate();
            if (x > 0) {
                //JOptionPane.showMessageDialog(null, "The External Student Is Added");
            } else {
                JOptionPane.showMessageDialog(null, "The External Student Isn't Added");
            }

            cnx.getConnect().commit();

            setValConfiramation(1);

        } catch (HeadlessException | SQLException e) {
            setValConfiramation(0);
            JOptionPane.showMessageDialog(null, "Error Sql In Add Student External Class ExternalSudent:" + e.getMessage());
            //JOptionPane.showMessageDialog(null, "Error in sql"+e.getMessage());
            if (cnx.getConnect() != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    cnx.getConnect().rollback();
                } catch (SQLException excep) {
                    excep.printStackTrace();
                }
            }
        } finally {

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

    @Override
    public void UpdateResident(int ID_Resident, int NumCard) {

        PreparedStatement prstm = null;
        // int ID_Resident=Get_ID_Resident(NumCard);
        String Query = "UPDATE Student_ResExt SET Id_BranchStd=? ,Id_LevelStudy=? ,"
                + " Num_InscritBac=? ,DateInscrp=? WHERE ID_Rsident=" + ID_Resident;

        //  String QueryUpdPrf="UPDATE Student_ResExt  SET ID_Profession=?"+"  WHERE ID_Rsident="+ID_Resident;
        cnx.Connecting();
        try {
            cnx.getConnect().setAutoCommit(false);
            super.UpdateResident(ID_Resident, NumCard);

            prstm = cnx.getConnect().prepareStatement(Query);
            prstm.setInt(1, this.Id_BranchStd);
            prstm.setInt(2, this.Id_LevelStudy);
            prstm.setString(3, this.Num_InscritBac);
            prstm.setDate(4, new java.sql.Date(new Date().getTime()));
            int x = prstm.executeUpdate();
            if (x > 0) {
                //JOptionPane.showMessageDialog(null, "The External Student Is Update ");
            } else {
                JOptionPane.showMessageDialog(null, " Error Update The External Student ");
            }
            cnx.getConnect().commit();

            setValConfiramation(1);

        } catch (Exception e) {
            setValConfiramation(0);
            JOptionPane.showMessageDialog(null, "Error in SQL Update Professor Class Professor " + e.getMessage());
            try {
                cnx.getConnect().rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "RollBack In Add Student Parent Class :" + ex.getMessage());
            }
        } finally {

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

    public void ListExternalStudent() {
        try {
            JasperReport jasperreport;
            // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file = getClass().getResourceAsStream("/Reports/ListExternalStd.jrxml");
            //NewReportCard
            JasperDesign jasperdesign = JRXmlLoader.load(file);
            jasperreport = JasperCompileManager.compileReport(jasperdesign);
            //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
            this.cnx.Connecting();
            Connection cnxt = this.cnx.getConnect();
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, null, cnxt);

            JasperViewer JspViewr = new JasperViewer(jasperprint, false);
            JspViewr.viewReport(jasperprint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    public void ListExternalStudentNewOrNot(String Case) {
        String Query = "SELECT Name_Resident,LastName_Resident,DateBirth,PlaceBirth,\n"
                + "Branch_Study.BranchStd_Name,\n"
                + "Level_Study.DescriptionLevel,Resident_Case.Resident_Case\n"
                + "FROM Resident_Gl,\n"
                + "Resident_Case,\n"
                + "Branch_Study,\n"
                + "Level_Study,\n"
                + "Student_ResExt \n"
                + "WHERE Resident_Gl.ID_Rsident=Student_ResExt.ID_Rsident\n"
                + "AND Student_ResExt.Id_BranchStd=Branch_Study.Id_BranchStd \n"
                + "AND Level_Study.Id_LevelStudy=Student_ResExt.Id_LevelStudy \n"
                + "AND Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident \n"
                + "AND Resident_Case.Resident_Case=N'" + Case + "' order by Name_Resident";

        try {
            JasperReport jasperreport;
            // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file = getClass().getResourceAsStream("/Reports/ListExternalStdNewORnot.jrxml");
            //NewReportCard
            JasperDesign jasperdesign = JRXmlLoader.load(file);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(Query);
            jasperdesign.setQuery(newQuery);
            jasperreport = JasperCompileManager.compileReport(jasperdesign);
            //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
            this.cnx.Connecting();
            Connection cnxt = this.cnx.getConnect();
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, null, cnxt);

            JasperViewer JspViewr = new JasperViewer(jasperprint, false);
            JspViewr.viewReport(jasperprint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    public void CardExternal(int NmCrd) {
        String Query = "Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,Branch_Study.BranchStd_Name,CodeBare_Resident,imageStd\n"
                + "FROM  Resident_Gl,Student_ResExt,Branch_Study Where \n"
                + "Resident_Gl.ID_Rsident=Student_ResExt.ID_Rsident\n"
                + "AND Branch_Study.Id_BranchStd=Student_ResExt.Id_BranchStd\n"
                + "AND NumCard_Resident=" + NmCrd + ";";
        try {
            JasperReport jasperreport;
            // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file = getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            //NewReportCard
            JasperDesign jasperdesign = JRXmlLoader.load(file);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(Query);
            jasperdesign.setQuery(newQuery);
            jasperreport = JasperCompileManager.compileReport(jasperdesign);
            //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
            this.cnx.Connecting();
            Connection cnxt = this.cnx.getConnect();
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, null, cnxt);

            JasperViewer JspViewr = new JasperViewer(jasperprint, false);
            JspViewr.viewReport(jasperprint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void PrinExternalStdEns() {

        String Query = "Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,Branch_Study.BranchStd_Name,CodeBare_Resident,imageStd FROM  Resident_Gl,Student_ResExt,Branch_Study\n"
                + "WHERE\n"
                + "Resident_Gl.ID_Rsident=Student_ResExt.ID_Rsident \n"
                + "AND Branch_Study.Id_BranchStd=Student_ResExt.Id_BranchStd\n"
                + "AND Id_Ptrn_Res=2;";
        try {
            JasperReport jasperreport;
            // InputStream file=getClass().getResourceAsStream("/Reports/CardResident.jrxml");
            InputStream file = getClass().getResourceAsStream("/Reports/EnsmRepExternal.jrxml");
            //NewReportCard
            JasperDesign jasperdesign = JRXmlLoader.load(file);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(Query);
            jasperdesign.setQuery(newQuery);
            jasperreport = JasperCompileManager.compileReport(jasperdesign);
            Map parametres = new HashMap<String, Object>();
            parametres.put("TypRes", "طالب خارجي");
            //JasperPrint jasperprint=JasperFillManager.fillReport(jasperreport,parametres, Cnx1);
            this.cnx.Connecting();
            Connection cnxt = this.cnx.getConnect();
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, parametres, cnxt);

            JasperViewer JspViewr = new JasperViewer(jasperprint, false);
            JspViewr.viewReport(jasperprint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ExternalStudent a = new ExternalStudent("basma", "ben aissa", 200, "123456", new Date(), "Biskra", 1, 2, 7,
                "branis", 1, 1, new Date(), 1);

        // a.CardExternal(5295);
        a.ListExternalStudentNewOrNot("خـــارجي غيـر مجــــدد");
    }

    /**
     * @return the ID_Rsident
     */
    public int getID_Rsident() {
        return ID_Rsident;
    }

    /**
     * @param ID_Rsident the ID_Rsident to set
     */
    public void setID_Rsident(int ID_Rsident) {
        this.ID_Rsident = ID_Rsident;
    }

    /**
     * @return the Id_BranchStd
     */
    public int getId_BranchStd() {
        return Id_BranchStd;
    }

    /**
     * @param Id_BranchStd the Id_BranchStd to set
     */
    public void setId_BranchStd(int Id_BranchStd) {
        this.Id_BranchStd = Id_BranchStd;
    }

    public int getId_LevelStudy() {
        return Id_LevelStudy;
    }

    /**
     * @param Id_LevelStudy the Id_LevelStudy to set
     */
    public void setId_LevelStudy(int Id_LevelStudy) {
        this.Id_LevelStudy = Id_LevelStudy;
    }

    /**
     * @return the DateInscrp
     */
    public Date getDateInscrp() {
        return DateInscrp;
    }

    /**
     * @param DateInscrp the DateInscrp to set
     */
    public void setDateInscrp(Date DateInscrp) {
        this.DateInscrp = DateInscrp;
    }

    /**
     * @return the Num_InscritBac
     */
    public String getNum_InscritBac() {
        return Num_InscritBac;
    }

    /**
     * @param Num_InscritBac the Num_InscritBac to set
     */
    public void setNum_InscritBac(String Num_InscritBac) {
        this.Num_InscritBac = Num_InscritBac;
    }

    private double getBacMoyen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param cnx the cnx to set
     */
}
