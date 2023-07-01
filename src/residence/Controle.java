/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Basma01
 */
public class Controle {
    
    MessageError MsgErr;
    
    public boolean ControleField_Add_Student2(JTextField txt_NumInsc,JTextField txtNam_std,JTextField txtSurNam_std ,
                                             JTextField DatBirth_std,JTextField txtPlcBirth_std , JTextField txtNam_Father,JTextField txtProfission_Std
                                             ,JTextField txtNam_mother,JTextField txtProfission_Moth,JTextField txtAddress_Std ,JTextField txtDairaStd 
                                             ,JTextField txtCommuneStd,JTextField TtxtBacYear,JTextField txtBacMoy,JTextField txtPlaceGetBac,JTextField DatInscrpInUniv)
        {
       
        
        if(  txt_NumInsc.getText().equals("")||txt_NumInsc.getText().equals("رقــم تــسجـيــل الــبـكــالــوريــا ")||
            txtNam_std.getText().equals("")||txtNam_std.getText().equals(".......................................................................")||
            txtSurNam_std.getText().equals("")||txtSurNam_std.getText().equals("........................................................................")||
            DatBirth_std.getText().equals("  /  /    ")||
            txtPlcBirth_std.getText().equals("")||txtPlcBirth_std.getText().equals("................................................................")||
            txtNam_Father.getText().equals("")|| txtNam_Father.getText().equals(".......................................................................")||
               
            txtProfission_Std.getText().equals("")||txtProfission_Std.getText().equals("........................................................................")||                                    
            txtNam_mother.getText().equals("")||txtNam_mother.getText().equals("..................................................................")||
            txtProfission_Moth.getText().equals("")||txtProfission_Moth.getText().equals(".......................................................................")||
            txtAddress_Std.getText().equals("") ||
            txtAddress_Std.getText().equals("...............................................................................................................................................................")||
             txtDairaStd.getText().equals("")|| txtDairaStd.getText().equals("........................................")||
            txtCommuneStd.getText().equals("")||txtCommuneStd.getText().equals("..................................")||
            TtxtBacYear.getText().equals("")|| 
            txtBacMoy.getText().equals("")||
            txtPlaceGetBac.getText().equals("")||
            txtPlaceGetBac.getText().equals("............................................................................................................................")||
               DatInscrpInUniv.getText().equals("  /  /    ")
               ){
         return true;
       }
           
        else    return false;          
  }
/********************************controle rapide *******************/
       public boolean ControleField_Add_Student2(JTextField txtNam_std,JTextField txtSurNam_std ,
                                             JTextField DatBirth_std,JTextField txtPlcBirth_std  ,
                                            JComboBox cmb1)
        {
       
        
        if( 
            txtNam_std.getText().equals("")||txtNam_std.getText().equals(".......................................................................")||
            txtSurNam_std.getText().equals("")||txtSurNam_std.getText().equals("........................................................................")||
            DatBirth_std.getText().equals("  /  /    ")||
            txtPlcBirth_std.getText().equals("")||txtPlcBirth_std.getText().equals("................................................................")||
           
            cmb1.getSelectedItem().equals("اختر التخـصص الدراســـــــي ....")
           
               ){
         return true;
       }
           
        else    return false;          
  }
/******************************************************************/
    
      public boolean ControleField_Add_Proffesor(JTextField txtNam_std,JTextField txtSurNam_std,JTextField txtPlcBirth_std,
                                                JTextField txtResid_std,JTextField txtBarcode_std,JTextField txtNumCard_std,
                                                JTextField txtBranch_std
                                            ){
        if(txtNam_std.getText().equals("")||txtNam_std.getText().equals("ادخل الاسم")||
            txtSurNam_std.getText().equals("")||txtSurNam_std.getText().equals("ادخل اللقب")||
            txtPlcBirth_std.getText().equals("")||txtPlcBirth_std.getText().equals("ادخل مكان الميلاد")||
            txtResid_std.getText().equals("")||txtResid_std.getText().equals("الاقــــامة الجامعيـــــــة")||
            txtBarcode_std.getText().equals("") ||txtBarcode_std.getText().equals("Code barre")||
              txtNumCard_std.getText().equals("ادخل رقم البطاقة  ")||txtNumCard_std.getText().equals("")||
              txtBranch_std.getText().equals("")|| txtBranch_std.getText().equals("نوع التخصص الدراسـي")
           ){ 
        return  false;// il exist un erreur 
        
        }else return true; 
    }
      
      
    

}

