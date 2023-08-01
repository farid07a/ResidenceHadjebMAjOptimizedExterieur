/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formes;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import residence.Branche;

/**
 *
 * @author client
 */
public class AddBranch extends javax.swing.JFrame {

    private residence.Branche Branche_obj;
    private static int ID_BRANCHE;
    private java.sql.Statement stm;
     ResultSet res=null;
            
    public AddBranch() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanFaculty = new javax.swing.JPanel();
        BtnUpdtSaveWilaya = new View.ButtonView();
        buttonView4 = new View.ButtonView();
        buttonView5 = new View.ButtonView();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabBranch = new javax.swing.JTable();
        BranchAR = new View.TextField();
        DomaineCombo = new View.Combobox();
        CodDom = new View.TextField();
        CodFil = new View.TextField();
        BranchFr = new View.TextField();
        Domaine = new View.TextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanFaculty.setBackground(new java.awt.Color(255, 255, 255));
        PanFaculty.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnUpdtSaveWilaya.setBackground(new java.awt.Color(0, 102, 102));
        BtnUpdtSaveWilaya.setForeground(new java.awt.Color(255, 255, 255));
        BtnUpdtSaveWilaya.setText("حفظ");
        BtnUpdtSaveWilaya.setBackgroundPainted(true);
        BtnUpdtSaveWilaya.setBorderPainted(false);
        BtnUpdtSaveWilaya.setEntred(false);
        BtnUpdtSaveWilaya.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BtnUpdtSaveWilaya.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        BtnUpdtSaveWilaya.setRounded(true);
        BtnUpdtSaveWilaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdtSaveWilayaActionPerformed(evt);
            }
        });
        PanFaculty.add(BtnUpdtSaveWilaya, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, 100, 30));

        buttonView4.setBackground(new java.awt.Color(0, 102, 102));
        buttonView4.setForeground(new java.awt.Color(255, 255, 255));
        buttonView4.setText("الغاء");
        buttonView4.setBackgroundPainted(true);
        buttonView4.setBorderPainted(false);
        buttonView4.setEntred(false);
        buttonView4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        buttonView4.setRounded(true);
        buttonView4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonView4ActionPerformed(evt);
            }
        });
        PanFaculty.add(buttonView4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, 100, 30));

        buttonView5.setBackground(new java.awt.Color(0, 102, 102));
        buttonView5.setForeground(new java.awt.Color(255, 255, 255));
        buttonView5.setText("خروج");
        buttonView5.setBackgroundPainted(true);
        buttonView5.setBorderPainted(false);
        buttonView5.setEntred(false);
        buttonView5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        buttonView5.setRounded(true);
        buttonView5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonView5ActionPerformed(evt);
            }
        });
        PanFaculty.add(buttonView5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 100, 30));

        jTextField2.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(204, 204, 204));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText("بحث");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        PanFaculty.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 130, 30));

        TabBranch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TabBranch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        TabBranch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الــتــخـــصــص", "Branche", "Code_filiere", "الشعبة", "Domaine", "Code_domaine", "ID"
            }
        ));
        TabBranch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabBranch.setRowHeight(22);
        TabBranch.setSelectionBackground(new java.awt.Color(51, 153, 0));
        TabBranch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabBranchMouseClicked(evt);
            }
        });
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        TabBranch.setDefaultRenderer(String.class, centerRenderer);
        jScrollPane3.setViewportView(TabBranch);

        PanFaculty.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 790, 220));

        BranchAR.setForeground(new java.awt.Color(102, 102, 102));
        BranchAR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BranchAR.setText("ادخل التخـصص");
        BranchAR.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        BranchAR.setLabelText("");
        BranchAR.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BranchARFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                BranchARFocusLost(evt);
            }
        });
        BranchAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BranchARActionPerformed(evt);
            }
        });
        BranchAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BranchARKeyReleased(evt);
            }
        });
        PanFaculty.add(BranchAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 9, 180, -1));

        DomaineCombo.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        DomaineCombo.setLabeText("");
        ((JLabel)DomaineCombo.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        DomaineCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DomaineComboItemStateChanged(evt);
            }
        });
        DomaineCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DomaineComboActionPerformed(evt);
            }
        });
        PanFaculty.add(DomaineCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 5, 330, 50));

        CodDom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CodDom.setLabelText("code domaine");
        PanFaculty.add(CodDom, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 78, 70, 40));

        CodFil.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CodFil.setLabelText("code filiere");
        CodFil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodFilActionPerformed(evt);
            }
        });
        PanFaculty.add(CodFil, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 80, 50));

        BranchFr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BranchFr.setLabelText("specialite");
        PanFaculty.add(BranchFr, new org.netbeans.lib.awtextra.AbsoluteConstraints(624, 70, 170, 45));

        Domaine.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Domaine.setLabelText("domaine");
        PanFaculty.add(Domaine, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 330, 45));

        getContentPane().add(PanFaculty, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 53, 810, 450));

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 40, 30));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Branche   إضــافــة شعبة");
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 0, 810, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnUpdtSaveWilayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdtSaveWilayaActionPerformed
        //String BranchStd_Name, String BranchStd_NameFr,
        //    String Description, String branch_code, String domaine_Code, String domaine_Label,
        //    String domaine_Label_ar)
        String BranchName_AR =BranchAR.getText();
        String BranchStd_NameFr=BranchFr.getText();
        String Desc="";
        String BranchCode=CodFil.getText();
        String domaine_Code=CodDom.getText();
        String domaine_Label=Domaine.getText();
        String domaine_Label_ar=  (String)DomaineCombo.getSelectedItem();
        
        Branche_obj=new Branche(0, BranchName_AR, BranchStd_NameFr, Desc, BranchCode, domaine_Code, domaine_Label, domaine_Label_ar);
        try {
            Branche_obj.insert_branche();
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro in insert New Branche"+e.getMessage());
        }
        
    }//GEN-LAST:event_BtnUpdtSaveWilayaActionPerformed

    private void buttonView4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonView4ActionPerformed
       
        
    }//GEN-LAST:event_buttonView4ActionPerformed

    private void buttonView5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonView5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonView5ActionPerformed

    private void TabBranchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabBranchMouseClicked
        int RowSlc=TabBranch.getSelectedRow(); 
        ID_BRANCHE=(int) TabBranch.getValueAt(RowSlc, 6);
        System.out.println("ID_BRANCHE:"+ID_BRANCHE);
    }//GEN-LAST:event_TabBranchMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BranchARFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BranchARFocusGained
        if (BranchAR.getText().equals("البحث")) {
            BranchAR.setText("");
            BranchAR.setForeground(Color.black);
        }
    }//GEN-LAST:event_BranchARFocusGained

    private void BranchARFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BranchARFocusLost
//        if(BranchAR.getText().equals("")){
//            IconClearSrchWilay.setIcon(null);
//            BranchAR.setText("البحث");
//            BranchAR.setForeground(Color.gray);
//        }
    }//GEN-LAST:event_BranchARFocusLost

    private void BranchARKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BranchARKeyReleased

//        if(!BranchAR.getText().equals("")){
//            FilterDataIntable(BranchAR.getText(), TableWilaya, (DefaultTableModel) TableWilaya.getModel());
//
//            IconClearSrchWilay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/iconsCls.png"))); // NOI18N
//        }else{
//
//            IconClearSrchWilay.setIcon(null); // NOI18N
//            FilterDataIntable(BranchAR.getText(), TableWilaya, (DefaultTableModel) TableWilaya.getModel());
//        }

    }//GEN-LAST:event_BranchARKeyReleased

    private void DomaineComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DomaineComboItemStateChanged
//        FillTableCommune_Combobox((String) DomaineCombo.getSelectedItem(), TableCommune, null, 0);
//        InitialiseFieldCommune();
//        EnableFieldCommune(false);
    }//GEN-LAST:event_DomaineComboItemStateChanged

    private void DomaineComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DomaineComboActionPerformed
        // FillTableCommune_Combobox((String) CombWilCom.getSelectedItem(), TableCommune, null, 0);
    }//GEN-LAST:event_DomaineComboActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased

    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        if (jTextField2.getText().equals("بحث")) {
            jTextField2.setText("");
        }
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void CodFilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodFilActionPerformed
        if (CodFil.getText().equals("")){
            CodFil.setText("");
            CodFil.setForeground(Color.BLACK);
            CodFil.setFont(new java.awt.Font("Times New Roman", 1, 13));
            //java.awt.Font("Times New Roman", 1, 14)
        }
    }//GEN-LAST:event_CodFilActionPerformed

    private void BranchARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BranchARActionPerformed
        if (BranchAR.getText().equals("ادخل التخـصص")){
            BranchAR.setText("");
            BranchAR.setForeground(Color.BLACK);
            BranchAR.setFont(new java.awt.Font("Times New Roman", 1, 13));
            //java.awt.Font("Times New Roman", 1, 14)
        }
        
    }//GEN-LAST:event_BranchARActionPerformed

    
    public void prepareGUI(){
    this.FillDataInTableBranche();
    this.FillDataInComboDomaine();
    this.setVisible(true);
    }
    
    private  void FillDataInTableBranche(){
        try {
            DefaultTableModel df=(DefaultTableModel)TabBranch.getModel();
            df.setRowCount(0);
            
            ArrayList<Branche> ListObjectBranche=Branche.getListObjectBranche();
            
            //ListObjectBranche.get(0).
            
            for (Branche BranchObj : ListObjectBranche) {
                Object arg[]={BranchObj.getBranchStd_Name(),BranchObj.getBranchStd_NameFr(),BranchObj.getBranch_code(),
                              BranchObj.getDomaine_Label_ar(),BranchObj.getDomaine_Label(),BranchObj.getDomaine_Code(),
                              BranchObj.getId_BranchStd()};
                df.addRow(arg);
            }
            
            TabBranch.setModel(df);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cannot Loading data in table Branche");
        }
        
    }
    
    
    //Field Domaine_Ar for Name Domaine AR
    //Domaine_Fr for DomaineNameFr
    private void FillDataInComboDomaine(){
            
        try {
            ArrayList<String> ListDomaineName=Branche.getListDomaineName("Domaine_Ar");// 
            DefaultComboBoxModel Dfcmb;
            //Dfcmb = new DefaultComboBoxModel();
            Dfcmb=(DefaultComboBoxModel) DomaineCombo.getModel();
            Dfcmb.removeAllElements();
            for (String DomaineName : ListDomaineName) {
                Dfcmb.addElement(DomaineName);
            }
            this.DomaineCombo.setModel(Dfcmb);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro When Loading domaine names in combob Domaine"+ex.getMessage());
        }
    
    }
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddBranch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBranch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBranch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBranch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               AddBranch FormBranche = new AddBranch();
               FormBranche.prepareGUI();
               //FormBranche.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.TextField BranchAR;
    private View.TextField BranchFr;
    private View.ButtonView BtnUpdtSaveWilaya;
    private View.TextField CodDom;
    private View.TextField CodFil;
    private View.TextField Domaine;
    private View.Combobox DomaineCombo;
    private javax.swing.JPanel PanFaculty;
    private javax.swing.JTable TabBranch;
    private View.ButtonView buttonView4;
    private View.ButtonView buttonView5;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}