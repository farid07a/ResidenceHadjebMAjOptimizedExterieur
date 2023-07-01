      /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Basma01
 */
public class Conf extends javax.swing.JFrame {

   Get_Info_DB get_Info_DB=new Get_Info_DB();
   ConnectionDB cnx=new ConnectionDB();
   TakeRepast takeRepastRemplissage=new TakeRepast();
   Ok1 ok;
   ExcelReader ImportData=new ExcelReader();
   Import_Export_Data ExportDataExce=new Import_Export_Data();
   MessageErrorControl  messageerror;
   Student_Res student_ResRemplissage=new Student_Res();
   Home1 h;
    public Conf(Home1 h) {
        initComponents();
        this.h=h;

        LabBranch.setBackground(new Color(0,190,190));
        LabFaculty.setBackground(new Color(0,153,153));
        LabNationality.setBackground(new Color(0,153,153));
        PanBranch.setVisible(true);
        PanNationality.setVisible(false);
        PanFaculty.setVisible(false);

       get_Info_DB.GetAllBranche(TabBranch,"Id_BranchStd","Branch_Study","BranchStd_NameFr", "BranchStd_Name");
       RemoveEle(TabBranch);
       
       jLabel2.setText(get_Info_DB.GetCount("Resident_Gl","ID_Case_Resident",2)+"");
       jLabel3.setText(get_Info_DB.GetCount("Resident_Gl","ID_Case_Resident" ,1)+"");
       jLabel4.setText(get_Info_DB.GetCount("Resident_Gl","ID_Case_Resident" ,4)+"");
       jLabel5.setText(get_Info_DB.GetCount("Resident_Gl","ID_Case_Resident" ,3)+"");
       jLabel19.setText(get_Info_DB.GetCount("Resident_Gl", "Id_Ptrn_Res" ,2)+"");
       jLabel18.setText(get_Info_DB.GetCount("Resident_Gl","ID_Case_Resident" ,8)+"");
       jLabel17.setText(get_Info_DB.GetCount("Resident_Gl","ID_Case_Resident" ,9)+"");
       jLabel11.setText(get_Info_DB.GetCountResident()+"");
       jLabel12.setText(get_Info_DB.GetCount("Resident_Gl","Id_Ptrn_Res" ,4)+"");
        jLabel22.setText(get_Info_DB.GetCountEtranger()+"");
        get_Info_DB.Filling(CaseStudent, "Resident_Case", "Resident_Case", 8);  
        ButtonGroup btnGp=new ButtonGroup();
        btnGp.add(DrcResicheck);
        btnGp.add(DrcDriectResicheck);
         FillDomaineCombobox(DomaineCombo, "Domaine_Ar");
        FillDomaineCombobox(DomaineCombo1, "Domaine_Fr");
        
        LabBranchMouseClicked(null);
        
    }  

  String QueryRest="SELECT Name_Resident,LastName_Resident,DateBirth,PlaceBirth,Branch_Study.BranchStd_Name,\n" +
"Level_Study.DescriptionLevel,Resident_Case.Resident_Case,Room_Code,Hour_Take,Min_Take\n" +
"FROM Resident_Gl,Resident_Case,Branch_Study,Level_Study,Student_Res,Room,Res_Take_Repat \n" +
"WHERE Resident_Gl.ID_Rsident=Student_Res.ID_Rsident\n" +
"AND Student_Res.Id_BranchStd=Branch_Study.Id_BranchStd \n" +
"AND Level_Study.Id_LevelStudy=Student_Res.Id_LevelStudy \n" +
"AND Resident_Case.ID_Case_Resident=Resident_Gl.ID_Case_Resident\n" +
"AND Student_Res.Id_Room=Room.Id_Room\n" +
"AND Res_Take_Repat.ID_Rsident=Resident_Gl.ID_Rsident\n" +
"AND (Res_Take_Repat.Hour_Take>=17 AND Res_Take_Repat.Hour_Take<=21)AND Date_Take='"+new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"'";
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanConfiguration = new javax.swing.JPanel();
        LabBranch = new javax.swing.JLabel();
        LabFaculty = new javax.swing.JLabel();
        LabNationality = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        PanBranch = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabBranch = new javax.swing.JTable();
        BranchAR = new javax.swing.JTextField();
        BranchFr = new javax.swing.JTextField();
        SaveBranch = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        CodDom = new javax.swing.JTextField();
        CodFil = new javax.swing.JTextField();
        DomaineAr = new javax.swing.JTextField();
        Domaine = new javax.swing.JTextField();
        DomaineCombo = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        DomaineCombo1 = new javax.swing.JComboBox<>();
        PanFaculty = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TabFaculty = new javax.swing.JTable();
        FacultyAr = new javax.swing.JTextField();
        SaveFaculty = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        FacultyFr = new javax.swing.JTextField();
        PanNationality = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        TabNationaliy = new javax.swing.JTable();
        SaveNaTio = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        NationalityAr = new javax.swing.JTextField();
        NationalityFr = new javax.swing.JTextField();
        PanLevelStudy = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        LevelTab = new javax.swing.JTable();
        SaveNaTio1 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        LevlStdFR = new javax.swing.JTextField();
        LevlStdAR = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        PanComANDWilaya = new javax.swing.JPanel();
        CommNameFr = new javax.swing.JTextField();
        CodeCom = new javax.swing.JTextField();
        NameWilaya = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        CommNameAR = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        Code_ComLab = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        SaveNaTio3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        LabLevel = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        CaseStudent = new javax.swing.JComboBox<>();
        PanInfDrc = new javax.swing.JPanel();
        CanSvDirct = new javax.swing.JButton();
        NamDrct = new javax.swing.JTextField();
        nmDrctLab = new javax.swing.JLabel();
        lasNamLab = new javax.swing.JLabel();
        LastNmDrc = new javax.swing.JTextField();
        NumDcsLab = new javax.swing.JLabel();
        DatDcsLab = new javax.swing.JLabel();
        DcsnDrct = new javax.swing.JTextField();
        DrcResicheck = new javax.swing.JRadioButton();
        DrcDriectResicheck = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableDirct = new javax.swing.JTable();
        BtnSavDirc = new javax.swing.JButton();
        UpdtInDrc = new javax.swing.JButton();
        DateDcsDrct = new javax.swing.JFormattedTextField();
        DelDrct = new javax.swing.JButton();
        NewDrct = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        LabLevel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        PanConfiguration.setBackground(new java.awt.Color(255, 255, 255));
        PanConfiguration.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanConfiguration.setPreferredSize(new java.awt.Dimension(960, 710));
        PanConfiguration.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabBranch.setBackground(new java.awt.Color(0, 153, 153));
        LabBranch.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        LabBranch.setForeground(new java.awt.Color(255, 255, 255));
        LabBranch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabBranch.setText("اضــافــة  تــخــصــص");
        LabBranch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LabBranch.setOpaque(true);
        LabBranch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabBranchMouseClicked(evt);
            }
        });
        PanConfiguration.add(LabBranch, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, 110, 40));

        LabFaculty.setBackground(new java.awt.Color(0, 153, 153));
        LabFaculty.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        LabFaculty.setForeground(new java.awt.Color(255, 255, 255));
        LabFaculty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabFaculty.setText("اضــافــة  كــلــيــة");
        LabFaculty.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LabFaculty.setOpaque(true);
        LabFaculty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabFacultyMouseClicked(evt);
            }
        });
        PanConfiguration.add(LabFaculty, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 90, 110, 40));

        LabNationality.setBackground(new java.awt.Color(0, 153, 153));
        LabNationality.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        LabNationality.setForeground(new java.awt.Color(255, 255, 255));
        LabNationality.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabNationality.setText("اضــافــة  جــنــســيــة");
        LabNationality.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LabNationality.setOpaque(true);
        LabNationality.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabNationalityMouseClicked(evt);
            }
        });
        PanConfiguration.add(LabNationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 140, 110, 40));

        jPanel7.setBackground(new java.awt.Color(0, 190, 190));
        jPanel7.setLayout(new java.awt.CardLayout());

        PanBranch.setBackground(new java.awt.Color(0, 190, 190));
        PanBranch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        PanBranch.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 720, 140));

        BranchAR.setBackground(new java.awt.Color(0, 190, 190));
        BranchAR.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BranchAR.setForeground(new java.awt.Color(204, 255, 255));
        BranchAR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BranchAR.setText("ادخــــل الــتــخـــصــص ");
        BranchAR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BranchAR.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BranchARFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                BranchARFocusLost(evt);
            }
        });
        PanBranch.add(BranchAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 240, 30));

        BranchFr.setBackground(new java.awt.Color(0, 190, 190));
        BranchFr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BranchFr.setForeground(new java.awt.Color(204, 255, 255));
        BranchFr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BranchFr.setText("Branche");
        BranchFr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BranchFr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BranchFrFocusGained(evt);
            }
        });
        BranchFr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BranchFrActionPerformed(evt);
            }
        });
        PanBranch.add(BranchFr, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 200, 30));

        SaveBranch.setBackground(new java.awt.Color(0, 204, 0));
        SaveBranch.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        SaveBranch.setForeground(new java.awt.Color(255, 255, 255));
        SaveBranch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SaveBranch.setText("حـــفــــظ");
        SaveBranch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SaveBranch.setOpaque(true);
        SaveBranch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveBranchMouseClicked(evt);
            }
        });
        PanBranch.add(SaveBranch, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 80, 35));

        jLabel102.setBackground(new java.awt.Color(0, 204, 0));
        jLabel102.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("الـــــغـــاء");
        jLabel102.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel102.setOpaque(true);
        jLabel102.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel102MouseClicked(evt);
            }
        });
        PanBranch.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 80, 35));

        CodDom.setBackground(new java.awt.Color(0, 190, 190));
        CodDom.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        CodDom.setForeground(new java.awt.Color(204, 255, 255));
        CodDom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CodDom.setText("Code domaine");
        CodDom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CodDom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CodDomFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CodDomFocusLost(evt);
            }
        });
        CodDom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodDomActionPerformed(evt);
            }
        });
        PanBranch.add(CodDom, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 90, 30));

        CodFil.setBackground(new java.awt.Color(0, 190, 190));
        CodFil.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        CodFil.setForeground(new java.awt.Color(204, 255, 255));
        CodFil.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CodFil.setText("Code filiere");
        CodFil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CodFil.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CodFilFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CodFilFocusLost(evt);
            }
        });
        PanBranch.add(CodFil, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 90, 30));

        DomaineAr.setBackground(new java.awt.Color(0, 190, 190));
        DomaineAr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        DomaineAr.setForeground(new java.awt.Color(204, 255, 255));
        DomaineAr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DomaineAr.setText("الشعبة");
        DomaineAr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DomaineAr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                DomaineArFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                DomaineArFocusLost(evt);
            }
        });
        PanBranch.add(DomaineAr, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 200, 30));

        Domaine.setBackground(new java.awt.Color(0, 190, 190));
        Domaine.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Domaine.setForeground(new java.awt.Color(204, 255, 255));
        Domaine.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Domaine.setText("Domaine");
        Domaine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Domaine.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                DomaineFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                DomaineFocusLost(evt);
            }
        });
        Domaine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DomaineActionPerformed(evt);
            }
        });
        PanBranch.add(Domaine, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 200, 30));

        DomaineCombo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DomaineCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DomaineComboActionPerformed(evt);
            }
        });
        PanBranch.add(DomaineCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 210, 30));

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton9.setText("جديد");
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanBranch.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(487, 50, 0, 30));

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
        PanBranch.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, 120, 30));

        DomaineCombo1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DomaineCombo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DomaineCombo1ActionPerformed(evt);
            }
        });
        PanBranch.add(DomaineCombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 210, 30));

        jPanel7.add(PanBranch, "card2");

        PanFaculty.setBackground(new java.awt.Color(0, 190, 190));
        PanFaculty.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabFaculty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TabFaculty.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        TabFaculty.getTableHeader().setReorderingAllowed(false);
        TabFaculty.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Faculty", "الــكـــلـــيـــة", "ID"
            }
        ));
        TabFaculty.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabFaculty.setRowHeight(22);
        TabFaculty.setSelectionBackground(new java.awt.Color(51, 153, 0));
        TabFaculty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabFacultyMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TabFaculty);

        PanFaculty.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 720, 220));

        FacultyAr.setBackground(new java.awt.Color(0, 190, 190));
        FacultyAr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        FacultyAr.setForeground(new java.awt.Color(204, 255, 255));
        FacultyAr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FacultyAr.setText("ادخــــل الــكـــلـــيـــة ");
        FacultyAr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        FacultyAr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                FacultyArFocusGained(evt);
            }
        });
        PanFaculty.add(FacultyAr, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 460, 30));

        SaveFaculty.setBackground(new java.awt.Color(0, 204, 0));
        SaveFaculty.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        SaveFaculty.setForeground(new java.awt.Color(255, 255, 255));
        SaveFaculty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SaveFaculty.setText("حـــفــــظ");
        SaveFaculty.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SaveFaculty.setOpaque(true);
        SaveFaculty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveFacultyMouseClicked(evt);
            }
        });
        PanFaculty.add(SaveFaculty, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 100, 35));

        jLabel158.setBackground(new java.awt.Color(0, 204, 0));
        jLabel158.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel158.setForeground(new java.awt.Color(255, 255, 255));
        jLabel158.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel158.setText("الـــــغـــاء");
        jLabel158.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel158.setOpaque(true);
        jLabel158.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel158MouseClicked(evt);
            }
        });
        PanFaculty.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 100, 35));

        FacultyFr.setBackground(new java.awt.Color(0, 190, 190));
        FacultyFr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        FacultyFr.setForeground(new java.awt.Color(204, 255, 255));
        FacultyFr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FacultyFr.setText("Faculté");
        FacultyFr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        FacultyFr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                FacultyFrFocusGained(evt);
            }
        });
        PanFaculty.add(FacultyFr, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 460, 30));

        jPanel7.add(PanFaculty, "card2");

        PanNationality.setBackground(new java.awt.Color(0, 190, 190));
        PanNationality.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TabNationaliy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TabNationaliy.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        TabNationaliy.getTableHeader().setReorderingAllowed(false);
        TabNationaliy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nationality", "الـجــنــســيــة", "ID"
            }
        ));
        TabNationaliy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabNationaliy.setRowHeight(22);
        TabNationaliy.setSelectionBackground(new java.awt.Color(51, 153, 0));
        TabNationaliy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabNationaliyMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(TabNationaliy);

        PanNationality.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 720, 190));

        SaveNaTio.setBackground(new java.awt.Color(0, 204, 0));
        SaveNaTio.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        SaveNaTio.setForeground(new java.awt.Color(255, 255, 255));
        SaveNaTio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SaveNaTio.setText("حـــفــــظ");
        SaveNaTio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SaveNaTio.setOpaque(true);
        SaveNaTio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveNaTioMouseClicked(evt);
            }
        });
        PanNationality.add(SaveNaTio, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 130, 35));

        jLabel182.setBackground(new java.awt.Color(0, 204, 0));
        jLabel182.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel182.setForeground(new java.awt.Color(255, 255, 255));
        jLabel182.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel182.setText("الـــــغـــاء");
        jLabel182.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel182.setOpaque(true);
        jLabel182.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel182MouseClicked(evt);
            }
        });
        PanNationality.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 140, 35));

        NationalityAr.setBackground(new java.awt.Color(0, 190, 190));
        NationalityAr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        NationalityAr.setForeground(new java.awt.Color(204, 255, 255));
        NationalityAr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NationalityAr.setText("ادخــــل الـجــنــســيــة ");
        NationalityAr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        NationalityAr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NationalityArFocusGained(evt);
            }
        });
        PanNationality.add(NationalityAr, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 470, 30));

        NationalityFr.setBackground(new java.awt.Color(0, 190, 190));
        NationalityFr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        NationalityFr.setForeground(new java.awt.Color(204, 255, 255));
        NationalityFr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NationalityFr.setText("Nationalité ");
        NationalityFr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        NationalityFr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NationalityFrFocusGained(evt);
            }
        });
        NationalityFr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NationalityFrActionPerformed(evt);
            }
        });
        PanNationality.add(NationalityFr, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 470, 30));

        jPanel7.add(PanNationality, "card2");

        PanLevelStudy.setBackground(new java.awt.Color(0, 190, 190));
        PanLevelStudy.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LevelTab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LevelTab.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        LevelTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code Niveau", "المستوي الدراسي", "ID"
            }
        ));
        LevelTab.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LevelTab.setRowHeight(22);
        LevelTab.setSelectionBackground(new java.awt.Color(51, 153, 0));
        LevelTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LevelTabMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(LevelTab);

        PanLevelStudy.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 720, 210));

        SaveNaTio1.setBackground(new java.awt.Color(0, 204, 0));
        SaveNaTio1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        SaveNaTio1.setForeground(new java.awt.Color(255, 255, 255));
        SaveNaTio1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SaveNaTio1.setText("حـــفــــظ");
        SaveNaTio1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SaveNaTio1.setOpaque(true);
        SaveNaTio1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveNaTio1MouseClicked(evt);
            }
        });
        PanLevelStudy.add(SaveNaTio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 100, 35));

        jLabel183.setBackground(new java.awt.Color(0, 204, 0));
        jLabel183.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel183.setForeground(new java.awt.Color(255, 255, 255));
        jLabel183.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel183.setText("الـــــغـــاء");
        jLabel183.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel183.setOpaque(true);
        jLabel183.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel183MouseClicked(evt);
            }
        });
        PanLevelStudy.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 100, 35));

        LevlStdFR.setBackground(new java.awt.Color(0, 190, 190));
        LevlStdFR.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LevlStdFR.setForeground(new java.awt.Color(204, 255, 255));
        LevlStdFR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        LevlStdFR.setText("Code Niveau");
        LevlStdFR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LevlStdFR.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                LevlStdFRFocusGained(evt);
            }
        });
        LevlStdFR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LevlStdFRActionPerformed(evt);
            }
        });
        PanLevelStudy.add(LevlStdFR, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 420, 30));

        LevlStdAR.setBackground(new java.awt.Color(0, 190, 190));
        LevlStdAR.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LevlStdAR.setForeground(new java.awt.Color(204, 255, 255));
        LevlStdAR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        LevlStdAR.setText("ادخــــل المستوي الدراسي ");
        LevlStdAR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LevlStdAR.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                LevlStdARFocusGained(evt);
            }
        });
        LevlStdAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LevlStdARActionPerformed(evt);
            }
        });
        PanLevelStudy.add(LevlStdAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 420, 30));

        jPanel7.add(PanLevelStudy, "card5");

        jPanel1.setLayout(new java.awt.CardLayout());

        PanComANDWilaya.setBackground(new java.awt.Color(0, 153, 153));
        PanComANDWilaya.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CommNameFr.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        CommNameFr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CommNameFr.setText("Commune");
        CommNameFr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CommNameFrFocusGained(evt);
            }
        });
        PanComANDWilaya.add(CommNameFr, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 120, 30));

        CodeCom.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        CodeCom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CodeCom.setText("Code");
        CodeCom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CodeComFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CodeComFocusLost(evt);
            }
        });
        PanComANDWilaya.add(CodeCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 108, 31));

        NameWilaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameWilayaActionPerformed(evt);
            }
        });
        PanComANDWilaya.add(NameWilaya, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 150, 31));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel25.setText(" اختر الولاية :");
        PanComANDWilaya.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 70, 30));

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton10.setText("تعديل");
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        PanComANDWilaya.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 70, 30));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("FR");
        PanComANDWilaya.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 20, 30));

        CommNameAR.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        CommNameAR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CommNameAR.setText("البلدية");
        CommNameAR.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CommNameARFocusGained(evt);
            }
        });
        PanComANDWilaya.add(CommNameAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 250, 31));

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 204));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("اضافة ولاية");
        jLabel27.setToolTipText("اضافة ولاية");
        jLabel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel27.setOpaque(true);
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        PanComANDWilaya.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 60, 30));

        jButton11.setBackground(new java.awt.Color(255, 255, 255));
        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton11.setText("حفظ");
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        PanComANDWilaya.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 70, 30));

        jScrollPane1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Code", "البلدية", "Commune"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        PanComANDWilaya.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 440, 160));

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton12.setText("حذف");
        jButton12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        PanComANDWilaya.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 70, 30));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("AR");
        PanComANDWilaya.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 20, 30));

        Code_ComLab.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Code_ComLab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PanComANDWilaya.add(Code_ComLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel29.setText("رقم:");
        PanComANDWilaya.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 50, 0, 0));

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton13.setText("الغاء");
        jButton13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        PanComANDWilaya.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, 70, 30));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "مهنة الموظفين", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 11))); // NOI18N

        jTextField4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButton34.setBackground(new java.awt.Color(255, 255, 255));
        jButton34.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton34.setText("اضافة مهنـة");
        jButton34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton34.setContentAreaFilled(false);
        jButton34.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        PanComANDWilaya.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 280, 160));

        jPanel1.add(PanComANDWilaya, "card2");

        jPanel7.add(jPanel1, "card6");

        PanConfiguration.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 760, 340));

        SaveNaTio3.setBackground(new java.awt.Color(0, 153, 153));
        SaveNaTio3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        SaveNaTio3.setForeground(new java.awt.Color(255, 255, 255));
        SaveNaTio3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SaveNaTio3.setText("تــعـــديــــل");
        SaveNaTio3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SaveNaTio3.setOpaque(true);
        SaveNaTio3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveNaTio3MouseClicked(evt);
            }
        });
        PanConfiguration.add(SaveNaTio3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 330, 110, 40));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("jLabel1");
        PanConfiguration.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 430, 70, 0));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 2, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 590, 130, 30));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 2, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 590, 100, 30));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 2, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 590, 90, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 2, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 660, 90, 30));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("عــدد الـــطـلـبـة الـمـقـيم ");
        jLabel6.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 560, 130, 30));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("الــمـجـدديـن");
        jLabel7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 560, 100, 30));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("غـيــر مـجــدديـن");
        jLabel8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 560, 90, 30));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("الــمـنــهـيـيـن");
        jLabel9.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 630, 90, 30));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("الــسـاكـنين");
        jLabel10.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 560, 130, 30));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 590, 130, 30));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 2, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 590, 100, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel13.setText("عــــدد الــعــمـال ");
        jLabel13.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 2, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 560, 100, 30));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("الــمـجـدديـن");
        jLabel14.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 1, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 630, 130, 30));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("غـيــر مـجــدديـن");
        jLabel15.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 630, 100, 30));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("عــدد الـطـلـبـة الـخـارجــي ");
        jLabel16.setToolTipText("");
        jLabel16.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 1, 1, 2, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 630, 130, 30));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 2, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 660, 100, 30));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 51, 51));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 1, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 660, 130, 30));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(0, 0, 0)));
        PanConfiguration.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 660, 130, 30));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/iconsCls.png"))); // NOI18N
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        PanConfiguration.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, -1, -1));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("الأجـــانـب");
        jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        PanConfiguration.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 630, 100, 30));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 51));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        PanConfiguration.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 660, 100, 30));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 153, 51));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/ExprtIc.jpg"))); // NOI18N
        jButton3.setText("استيراد المعطيات");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        PanConfiguration.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 500, 140, 50));

        jTextField1.setEnabled(false);
        PanConfiguration.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, 150, 30));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setText("...");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        PanConfiguration.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 510, 30, 30));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 153, 51));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/ExprtIc.jpg"))); // NOI18N
        jButton2.setText("بيانات الطلبـــة");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        PanConfiguration.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 500, 130, 50));

        LabLevel.setBackground(new java.awt.Color(0, 153, 153));
        LabLevel.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        LabLevel.setForeground(new java.awt.Color(255, 255, 255));
        LabLevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabLevel.setText("اضـافــة مستوي");
        LabLevel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LabLevel.setOpaque(true);
        LabLevel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabLevelMouseClicked(evt);
            }
        });
        PanConfiguration.add(LabLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 190, 110, 40));

        jButton4.setBackground(new java.awt.Color(255, 51, 51));
        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("حــــذف");
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.setOpaque(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        PanConfiguration.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 290, 110, 30));

        CaseStudent.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        CaseStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CaseStudent.setBackground(new java.awt.Color(204, 204, 204));
        CaseStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaseStudentActionPerformed(evt);
            }
        });
        PanConfiguration.add(CaseStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 510, 140, 34));

        PanInfDrc.setBackground(new java.awt.Color(255, 255, 255));
        PanInfDrc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanInfDrc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CanSvDirct.setBackground(new java.awt.Color(0, 102, 102));
        CanSvDirct.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        CanSvDirct.setForeground(new java.awt.Color(255, 255, 255));
        CanSvDirct.setText("الغاء");
        CanSvDirct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CanSvDirct.setContentAreaFilled(false);
        CanSvDirct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CanSvDirct.setOpaque(true);
        CanSvDirct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanSvDirctActionPerformed(evt);
            }
        });
        PanInfDrc.add(CanSvDirct, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 90, 30));

        NamDrct.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        NamDrct.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NamDrct.setEnabled(false);
        PanInfDrc.add(NamDrct, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 110, 30));

        nmDrctLab.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        nmDrctLab.setText("الاسم :");
        PanInfDrc.add(nmDrctLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 30, 30));

        lasNamLab.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        lasNamLab.setText("اللقب :");
        PanInfDrc.add(lasNamLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 35, 44, 30));

        LastNmDrc.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        LastNmDrc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        LastNmDrc.setEnabled(false);
        PanInfDrc.add(LastNmDrc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, 100, 30));

        NumDcsLab.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        NumDcsLab.setText("رقم القرار:");
        PanInfDrc.add(NumDcsLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, -1, 30));

        DatDcsLab.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        DatDcsLab.setText("تاريخ القرار:");
        PanInfDrc.add(DatDcsLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 70, -1, 30));

        DcsnDrct.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        DcsnDrct.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        DcsnDrct.setEnabled(false);
        PanInfDrc.add(DcsnDrct, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 110, 30));

        DrcResicheck.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        DrcResicheck.setSelected(true);
        DrcResicheck.setText("مدير الاقامة الجامعية");
        DrcResicheck.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DrcResicheck.setHideActionText(true);
        DrcResicheck.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DrcResicheck.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        DrcResicheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DrcResicheckActionPerformed(evt);
            }
        });
        PanInfDrc.add(DrcResicheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, -1, 30));

        DrcDriectResicheck.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        DrcDriectResicheck.setText("مدير الخدمات الجامعية");
        DrcDriectResicheck.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DrcDriectResicheck.setHideActionText(true);
        DrcDriectResicheck.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DrcDriectResicheck.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        DrcDriectResicheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DrcDriectResicheckActionPerformed(evt);
            }
        });
        PanInfDrc.add(DrcDriectResicheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 30));

        TableDirct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "N°", "الاسم", "اللقب", "رقم القرار", "تاريخ القرار"
            }
        ));
        TableDirct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TableDirct.setRowHeight(25);
        TableDirct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableDirctMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableDirct);

        PanInfDrc.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 320, 90));

        BtnSavDirc.setBackground(new java.awt.Color(0, 102, 102));
        BtnSavDirc.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        BtnSavDirc.setForeground(new java.awt.Color(255, 255, 255));
        BtnSavDirc.setText("حفظ");
        BtnSavDirc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnSavDirc.setContentAreaFilled(false);
        BtnSavDirc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnSavDirc.setEnabled(false);
        BtnSavDirc.setFocusTraversalPolicyProvider(true);
        BtnSavDirc.setOpaque(true);
        BtnSavDirc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSavDircActionPerformed(evt);
            }
        });
        PanInfDrc.add(BtnSavDirc, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 100, 30));

        UpdtInDrc.setBackground(new java.awt.Color(0, 102, 102));
        UpdtInDrc.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        UpdtInDrc.setForeground(new java.awt.Color(255, 255, 255));
        UpdtInDrc.setText("تعديل");
        UpdtInDrc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        UpdtInDrc.setContentAreaFilled(false);
        UpdtInDrc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        UpdtInDrc.setOpaque(true);
        UpdtInDrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdtInDrcActionPerformed(evt);
            }
        });
        PanInfDrc.add(UpdtInDrc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 90, 30));

        try {
            DateDcsDrct.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        DateDcsDrct.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DateDcsDrct.setEnabled(false);
        PanInfDrc.add(DateDcsDrct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, 30));

        DelDrct.setBackground(new java.awt.Color(255, 51, 51));
        DelDrct.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        DelDrct.setForeground(new java.awt.Color(255, 255, 255));
        DelDrct.setText("حذف");
        DelDrct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DelDrct.setContentAreaFilled(false);
        DelDrct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DelDrct.setOpaque(true);
        DelDrct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelDrctActionPerformed(evt);
            }
        });
        PanInfDrc.add(DelDrct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 90, 30));

        NewDrct.setBackground(new java.awt.Color(0, 102, 102));
        NewDrct.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        NewDrct.setForeground(new java.awt.Color(255, 255, 255));
        NewDrct.setText("جديد");
        NewDrct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        NewDrct.setContentAreaFilled(false);
        NewDrct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NewDrct.setOpaque(true);
        NewDrct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewDrctActionPerformed(evt);
            }
        });
        PanInfDrc.add(NewDrct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 90, 30));

        PanConfiguration.add(PanInfDrc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 320, 300));

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton6.setText("نسخة احتياطية");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        PanConfiguration.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 450, 100, 40));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton5.setText("تحميل المعطيات");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        PanConfiguration.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 450, 90, 40));

        LabLevel1.setBackground(new java.awt.Color(0, 153, 153));
        LabLevel1.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        LabLevel1.setForeground(new java.awt.Color(255, 255, 255));
        LabLevel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabLevel1.setText("اعدادات اخرى");
        LabLevel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LabLevel1.setOpaque(true);
        LabLevel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabLevel1MouseClicked(evt);
            }
        });
        PanConfiguration.add(LabLevel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 240, 110, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanConfiguration, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(PanConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LabBranchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabBranchMouseClicked
        initialisation();
        LabBranch.setBackground(new Color(0,190,190));
        LabFaculty.setBackground(new Color(0,153,153));
        LabNationality.setBackground(new Color(0,153,153));
        
        LabLevel.setBackground(new Color(0,153,153));
        PanLevelStudy.setVisible(false);        
        
        PanBranch.setVisible(true);
        PanNationality.setVisible(false);
        PanFaculty.setVisible(false);
         get_Info_DB.GetAllBranche(TabBranch,"Id_BranchStd","Branch_Study","BranchStd_NameFr", "BranchStd_Name");
         FillAllBranche(TabBranch);
         RemoveEle(TabBranch);
    }//GEN-LAST:event_LabBranchMouseClicked

    private void LabFacultyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabFacultyMouseClicked
        initialisation();
        LabFaculty.setBackground(new Color(0,190,190));
        LabBranch.setBackground(new Color(0,153,153));
        LabNationality.setBackground(new Color(0,153,153));
        LabLevel.setBackground(new Color(0,153,153));
        
        PanLevelStudy.setVisible(false);
        PanBranch.setVisible(false);
        PanNationality.setVisible(false);
        PanFaculty.setVisible(true);
         get_Info_DB.GetAllBranche(TabFaculty,"Id_Faculty","Faculty","NameFact_Fr", "NameFact");
         RemoveEle(TabFaculty);
    }//GEN-LAST:event_LabFacultyMouseClicked

    private void LabNationalityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabNationalityMouseClicked
        initialisation();
        LabNationality.setBackground(new Color(0,190,190));
        LabBranch.setBackground(new Color(0,153,153));
        LabFaculty.setBackground(new Color(0,153,153));
        LabLevel.setBackground(new Color(0,153,153));
        PanLevelStudy.setVisible(false);
        PanBranch.setVisible(false);
        PanNationality.setVisible(true);
        PanFaculty.setVisible(false);
        get_Info_DB.GetAllBranche(TabNationaliy,"Id_Nationalite","Nationalite","Nationalite_Fr", "Nationalite");
        RemoveEle(TabNationaliy);
    }//GEN-LAST:event_LabNationalityMouseClicked

    private void TabFacultyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabFacultyMouseClicked
        val=2;
         SaveNaTio3.setBackground(new Color(0,153,153));
         int RowSlc=TabFaculty.getSelectedRow();
         int idFaculty=(int) TabFaculty.getValueAt(RowSlc, 2);
         jLabel1.setText(idFaculty+"");
    }//GEN-LAST:event_TabFacultyMouseClicked

    private void TabNationaliyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabNationaliyMouseClicked
       val=3;
       SaveNaTio3.setBackground(new Color(0,153,153));
       int RowSlc=TabNationaliy.getSelectedRow();
         int idNational=(int) TabNationaliy.getValueAt(RowSlc, 2);
         jLabel1.setText(idNational+"");
    }//GEN-LAST:event_TabNationaliyMouseClicked

    private void SaveNaTioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveNaTioMouseClicked
       if(val==3){
           SaveNaTio3.setBackground(new Color(0,153,153));
            // JOptionPane.showMessageDialog(null, " MOOOOOOOOdIIIIIIIIIIIfIcation");
        IDTab=Integer.parseInt(jLabel1.getText()); 
           //JOptionPane.showMessageDialog(null," ID " +IDTab+" "+NationalityAr.getText()+"  "+NationalityFr.getText());
      get_Info_DB.Update_Branch_Faculty_Nationality("Nationalite","Nationalite","Nationalite_Fr", "Id_Nationalite",
              IDTab, NationalityAr.getText(), NationalityFr.getText());
      get_Info_DB.GetAllBranche(TabNationaliy,"Id_Nationalite","Nationalite","Nationalite_Fr", "Nationalite");
      RemoveEle(TabNationaliy);
       val=0;
       }  else{
            if(!NationalityAr.getText().equals("ادخــــل الـجــنــســيــة ")&&!NationalityAr.getText().equals("")){
             get_Info_DB.InsertIntoDB("Nationalite", "Nationalite", "Nationalite_Fr",NationalityAr.getText() ,NationalityFr.getText());

                get_Info_DB.GetAllBranche(TabNationaliy,"Id_Nationalite","Nationalite","Nationalite_Fr", "Nationalite");
        
         RemoveEle(TabNationaliy);
             }
       }
        initialisation();
    }//GEN-LAST:event_SaveNaTioMouseClicked
    int IDTab;
    private void SaveFacultyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveFacultyMouseClicked
        if(val==2){
          SaveNaTio3.setBackground(new Color(0,153,153));
      // JOptionPane.showMessageDialog(null, " MOOOOOOOOdIIIIIIIIIIIfIcation");
       IDTab=Integer.parseInt(jLabel1.getText());
      // JOptionPane.showMessageDialog(null, "  "+FacultyAr.getText()+"  "+FacultyFr.getText());
       get_Info_DB.Update_Branch_Faculty_Nationality("Faculty","NameFact","NameFact_Fr","Id_Faculty",IDTab,FacultyAr.getText(),FacultyFr.getText());
       get_Info_DB.GetAllBranche(TabFaculty,"Id_Faculty","Faculty","NameFact_Fr", "NameFact");
        RemoveEle(TabFaculty);
       val=0;
       } else{
        if(!FacultyAr.getText().equals("ادخــــل الــكـــلـــيـــة ") &&!FacultyAr.getText().equals("")){
       get_Info_DB.InsertIntoDB("Faculty", "NameFact", "NameFact_Fr",FacultyAr.getText() ,FacultyFr.getText());

        get_Info_DB.GetAllBranche(TabFaculty,"Id_Faculty","Faculty","NameFact_Fr", "NameFact");
        RemoveEle(TabFaculty);
       }
       }
            initialisation();
    }//GEN-LAST:event_SaveFacultyMouseClicked
  public void initialisation(){
    FacultyAr.setText("ادخــــل الــكـــلـــيـــة ");
    FacultyAr.setForeground(new Color(204,255,255));
    FacultyFr.setText("Faculty");
    FacultyFr.setForeground(new Color(204,255,255));
    BranchAR.setText("ادخــــل الــتــخـــصــص ");
   BranchAR.setForeground(new Color(204,255,255));
    BranchFr.setText("Branche");
    BranchFr.setForeground(new Color(204,255,255));
    NationalityAr.setText("ادخــــل الـجــنــســيــة ");
    NationalityAr.setForeground(new Color(204,255,255));
    NationalityFr.setText("Nationalité");
    NationalityFr.setForeground(new Color(204,255,255));
  }
/*******************************************************************/
  public void FillDomaineCombobox(JComboBox cmb,String Domaine_Ar){
 String Query01="SELECT "+Domaine_Ar+" FROM Domaine ";
 Statement stm=null;
 ResultSet res = null;
        DefaultComboBoxModel Dfcmb=new DefaultComboBoxModel();
        Dfcmb=(DefaultComboBoxModel) cmb.getModel();
        Dfcmb.removeAllElements();
       cnx.Connecting();
       try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query01);
            
            while (res.next()){                
                Dfcmb.addElement(res.getString(Domaine_Ar));
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
/*****************************************************************/  
  
    private void NationalityArFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NationalityArFocusGained
        if(NationalityAr.getText().equals("ادخــــل الـجــنــســيــة ")){
            NationalityAr.setText("");
        }
        NationalityAr.setForeground(Color.WHITE);
    }//GEN-LAST:event_NationalityArFocusGained

    private void FacultyArFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FacultyArFocusGained
        if(FacultyAr.getText().equals("ادخــــل الــكـــلـــيـــة ")){
            FacultyAr.setText("");
        }
        FacultyAr.setForeground(Color.WHITE);
    }//GEN-LAST:event_FacultyArFocusGained

    private void jLabel158MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel158MouseClicked
        SaveNaTio3.setBackground(new Color(0,153,153));
        initialisation();
        val=0;
    }//GEN-LAST:event_jLabel158MouseClicked

    private void jLabel182MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel182MouseClicked
         SaveNaTio3.setBackground(new Color(0,153,153));
       initialisation();
       val=0;
    }//GEN-LAST:event_jLabel182MouseClicked
        int val=0;
    private void SaveNaTio3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveNaTio3MouseClicked

        if(val==1){
        int Row=(int)TabBranch.getSelectedRow();
        //JOptionPane.showMessageDialog(null, " Row "+Row);
        int RowSlc=TabBranch.getSelectedRow();
          BranchAR.setText((String)TabBranch.getValueAt(RowSlc, 0));
          BranchFr.setText((String)TabBranch.getValueAt(RowSlc, 1));
          CodFil.setText((String)TabBranch.getValueAt(RowSlc, 2));
          DomaineAr.setText((String)TabBranch.getValueAt(RowSlc, 3));
          DomaineCombo.setSelectedItem((String)TabBranch.getValueAt(RowSlc, 3));
          Domaine.setText((String)TabBranch.getValueAt(RowSlc, 4));
          CodDom.setText((String)TabBranch.getValueAt(RowSlc, 5));
          int idBranch=(int) TabBranch.getValueAt(RowSlc, 6);
          jLabel1.setText(idBranch+"");
          SaveNaTio3.setBackground(new Color(0,190,190));
        }

        if(val==2){
        int Row=(int)TabFaculty.getSelectedRow();
        FacultyAr.setText((String)TabFaculty.getValueAt(Row,1));
        FacultyFr.setText((String)TabFaculty.getValueAt(Row, 0));
        jLabel1.setText((int)TabFaculty.getValueAt(Row,2)+"");
        SaveNaTio3.setBackground(new Color(0,190,190));
        }
         if(val==3){
        int Row=(int)TabNationaliy.getSelectedRow();
        NationalityAr.setText((String)TabNationaliy.getValueAt(Row,1));
        NationalityFr.setText((String)TabNationaliy.getValueAt(Row, 0));
        jLabel1.setText((int)TabNationaliy.getValueAt(Row,2)+"");
        SaveNaTio3.setBackground(new Color(0,190,190));
        }
         if(val==4){
        int Row=(int)LevelTab.getSelectedRow();
        LevlStdAR.setText((String)LevelTab.getValueAt(Row,1));
        LevlStdFR.setText((String)LevelTab.getValueAt(Row, 0));
        jLabel1.setText((int)LevelTab.getValueAt(Row,2)+"");
        SaveNaTio3.setBackground(new Color(0,190,190));
        }
         
        if(val==0){
          messageerror=new MessageErrorControl(this, true, "الــــــرجــــاء الاخــــتـــيــــار مـــــن الـــجـــــدول ثــم لــتــعــديــل");
          messageerror.setVisible(true);
        }
    }//GEN-LAST:event_SaveNaTio3MouseClicked

    private void NationalityFrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NationalityFrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NationalityFrActionPerformed

    private void NationalityFrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NationalityFrFocusGained
        if(NationalityFr.getText().equals("Nationalité")){
         NationalityFr.setText("");
        }
        NationalityFr.setForeground(Color.WHITE);
    }//GEN-LAST:event_NationalityFrFocusGained

    private void FacultyFrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FacultyFrFocusGained
        if(FacultyFr.getText().equals("Faculté")){
         FacultyFr.setText("");
        }
      FacultyFr.setForeground(Color.WHITE);                             
    }//GEN-LAST:event_FacultyFrFocusGained

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        this.dispose();
        
        
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

             if(jTextField1.getText().endsWith(".xlsx")){
          
             try {
         int x =JOptionPane.showConfirmDialog(null, "هل تريد استيراد البيانات","رسالة تأكيد",1);
              //JOptionPane.showMessageDialog(null, "X :::"+x);
                 if (x==0) {
                     
                     ImportData.ReadDataExcel();
                 }
         
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error In ReadExcel file: "+ex.getMessage());
        }
            
    }
    
    else {
     MessageErrorControl messagerror = new MessageErrorControl(this, true, "الرجاء اختـــر ملف الاكسل لقائمة الطلبــة");
            messagerror.setVisible(true);
    }
        
        /*   try {
            ImportData.ReadDataExcel();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error In ReadExcel");
        }*/
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        jfc.setDialogTitle("اختر ملف الطلبـــة");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File xlsx", "xlsx");
        jfc.addChoosableFileFilter(filter);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();

            System.out.println(selectedFile.getAbsolutePath());
            jTextField1.setText(selectedFile.getAbsolutePath());
            ImportData.SetFileExcel(selectedFile);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            ExportDataExce.Fill_FileExcel_StdNoCodeCommune((String) CaseStudent.getSelectedItem());
        } catch (IOException ex) {
            Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void LabLevelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabLevelMouseClicked
        // TODO add your handling code here:
        initialisation();

        LabLevel.setBackground(new Color(0,190,190));
        LabNationality.setBackground(new Color(0,153,153));
        LabBranch.setBackground(new Color(0,153,153));
        LabFaculty.setBackground(new Color(0,153,153));
        PanLevelStudy.setVisible(true);
        PanBranch.setVisible(false);
        PanNationality.setVisible(false);
        PanFaculty.setVisible(false);
        get_Info_DB.GetAllBranche(LevelTab,"Id_LevelStudy","Level_Study","Level_study", "DescriptionLevel");
        RemoveEle(LevelTab);
    }//GEN-LAST:event_LabLevelMouseClicked

    private void LevelTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LevelTabMouseClicked
        val=4;
        SaveNaTio3.setBackground(new Color(0,153,153));  
        int RowSlc=LevelTab.getSelectedRow();
         int idLevel=(int) LevelTab.getValueAt(RowSlc, 2);
         jLabel1.setText(idLevel+"");
    }//GEN-LAST:event_LevelTabMouseClicked

    private void SaveNaTio1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveNaTio1MouseClicked
        if(val==4){
            SaveNaTio1.setBackground(new Color(0,153,153));
            //JOptionPane.showMessageDialog(null, " MOOOOOOOOdIIIIIIIIIIIfIcation");
            IDTab=Integer.parseInt(jLabel1.getText());
            //JOptionPane.showMessageDialog(null," ID " +IDTab+" "+NationalityAr.getText()+"  "+NationalityFr.getText());
            get_Info_DB.Update_Branch_Faculty_Nationality("Level_Study","Level_study","DescriptionLevel", "Id_LevelStudy",
                IDTab, LevlStdFR.getText(), LevlStdAR.getText());
            get_Info_DB.GetAllBranche(LevelTab,"Id_LevelStudy","Level_Study","Level_study", "DescriptionLevel");
            RemoveEle(LevelTab);
            val=0;
        }  else{
            if(!LevlStdAR.getText().equals("ادخــــل المستوي الدراسي ")&&!LevlStdAR.getText().equals("")){

                get_Info_DB.InsertIntoDB("Level_Study", "Level_study", "DescriptionLevel",LevlStdFR.getText() ,LevlStdAR.getText());

                get_Info_DB.GetAllBranche(LevelTab,"Id_LevelStudy","Level_Study","Level_study", "DescriptionLevel");

                RemoveEle(TabNationaliy);
            }
        }
        initialisation();

    }//GEN-LAST:event_SaveNaTio1MouseClicked

    private void jLabel183MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel183MouseClicked
        LevlStdAR.setText("ادخــــل المستوي الدراسي ");
        LevlStdFR.setText("Code Niveau");
    }//GEN-LAST:event_jLabel183MouseClicked

    private void LevlStdFRFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LevlStdFRFocusGained
        if (LevlStdFR.getText().equals("Code Niveau")) {
            LevlStdFR.setText("");
        }
        LevlStdFR.setForeground(Color.WHITE);
    }//GEN-LAST:event_LevlStdFRFocusGained

    private void LevlStdFRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevlStdFRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LevlStdFRActionPerformed

    private void LevlStdARFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LevlStdARFocusGained
        // TODO add your handling code here:
        if (LevlStdAR.getText().equals("ادخــــل المستوي الدراسي ")) {
            LevlStdAR.setText("");
        }
        LevlStdAR.setForeground(Color.WHITE);
    }//GEN-LAST:event_LevlStdARFocusGained

    private void LevlStdARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevlStdARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LevlStdARActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int n=JOptionPane.showConfirmDialog(null,
            "هل انت متأكد من عملية الحــذف",
            "عملية الحذف", JOptionPane.YES_NO_OPTION);
        ///JOptionPane.showMessageDialog(null, "The x :"+x);

        /*JOptionPane.showConfirmDialog(null,
            panConf,
            "JOptionPane Example : ",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);*/
        int ChoiceID=Integer.parseInt(jLabel1.getText());

        if (jLabel1.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "الرجاء اختيار العنصر من الجدول");
        }else{

            /*
            Object[] options = {"نعم بالتأكيد",
                "لا شكرا",
                "الغاء"};
            int n = JOptionPane.showOptionDialog(this,
                "هل انت متأكد من عملية الحــذف",
                "عملية الحذف",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);

            JOptionPane.showMessageDialog(null, "The N :"+n);
            */
            if (n==0) {

                switch(val){
                    case 1:
                    //JOptionPane.showMessageDialog(null, "I am in case 1 AND choice is :"+ChoiceID);
                    get_Info_DB.Del_Nat_Lvl_Brn("Branch_Study", "Id_BranchStd", ChoiceID);
                    get_Info_DB.GetAllBranche(TabBranch,"Id_BranchStd","Branch_Study","BranchStd_NameFr", "BranchStd_Name");
                    RemoveEle(TabBranch);
                    break;
                    case 2:
                    get_Info_DB.Del_Nat_Lvl_Brn("Faculty", "Id_Faculty", ChoiceID);
                    get_Info_DB.GetAllBranche(TabFaculty,"Id_Faculty","Faculty","NameFact_Fr", "NameFact");
                    RemoveEle(TabFaculty);
                    break;
                    case 3:
                    get_Info_DB.Del_Nat_Lvl_Brn("Nationalite", "Id_Nationalite", ChoiceID);
                    get_Info_DB.GetAllBranche(TabNationaliy,"Id_Nationalite","Nationalite","Nationalite_Fr", "Nationalite");
                    RemoveEle(TabNationaliy);
                    break;

                    case 4:
                    get_Info_DB.Del_Nat_Lvl_Brn("Level_Study", "Id_LevelStudy", ChoiceID);
                    get_Info_DB.GetAllBranche(LevelTab,"Id_LevelStudy","Level_Study","Level_study", "DescriptionLevel");
                    RemoveEle(LevelTab);
                    break;
                }

                //JOptionPane.showMessageDialog(null  , "لقد تمت عملية الحذف بنجاح");
            }
        }
        //JOptionPane.showMessageDialog(null, "The N :"+n);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void CaseStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaseStudentActionPerformed
        // TODO add your handling code here:
        String Case= (String) CaseStudent.getSelectedItem();
        // JOptionPane.showMessageDialog(null, "I am select Sud Item"+Wilaye);

    }//GEN-LAST:event_CaseStudentActionPerformed
public void EnableFieldManger(boolean b){
            LastNmDrc.setEnabled(b);
            NamDrct.setEnabled(b);
            DcsnDrct.setEnabled(b);
            DateDcsDrct.setEnabled(b);
}
public void EmptyFieldManager(){
        LastNmDrc.setText("");
            NamDrct.setText("");
            DcsnDrct.setText("");
            DateDcsDrct.setText("");
        }

    private void CanSvDirctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanSvDirctActionPerformed

        EnableFieldManger(false);
        EmptyFieldManager();
        BtnSavDirc.setText("حفظ");
        TableDirct.getSelectionModel().clearSelection();

    }//GEN-LAST:event_CanSvDirctActionPerformed
int KindManger=1;
    private void DrcResicheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DrcResicheckActionPerformed
        KindManger=1;
    }//GEN-LAST:event_DrcResicheckActionPerformed

    private void DrcDriectResicheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DrcDriectResicheckActionPerformed
        KindManger=2;
    }//GEN-LAST:event_DrcDriectResicheckActionPerformed
int RowSlct=-1;
    private void TableDirctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableDirctMouseClicked
        //int SelcRow=0;
        if(TableDirct.getSelectedRow()==-1) {

        }else{
            //JOptionPane.showMessageDialog(null, "The Row Selected is:"+jTable2.getSelectedRow());
            RowSlct=TableDirct.getSelectedRow();
            if (TableDirct.getValueAt(RowSlct, 0).equals("الاقامةالجامعية")) {
                DrcResicheck.setSelected(true);
                KindManger=1;
            }else{
                DrcDriectResicheck.setSelected(true);
                KindManger=2;
            }

            NamDrct.setText((String)TableDirct.getValueAt(RowSlct, 1));
            LastNmDrc.setText((String)TableDirct.getValueAt(RowSlct, 2));
            //String s=jTable2.getValueAt(RowSlct, 3);
            DcsnDrct.setText((int)TableDirct.getValueAt(RowSlct, 3)+"");
            String DateStr=new SimpleDateFormat("dd/MM/yyyy").format((Date)TableDirct.getValueAt(RowSlct, 4));
            DateDcsDrct.setText(DateStr);
        }

    }//GEN-LAST:event_TableDirctMouseClicked
int Value=0;
    private void BtnSavDircActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSavDircActionPerformed

        if (BtnSavDirc.getText()==("حفظ")/*.equals("حفظ")*/) {
            //JOptionPane.showMessageDialog(null, "It comparable ");

            String Query="Select count(FirstName) from Manager where kind_Manager="+KindManger;
            Statement stm=null;
            ResultSet res=null;
            cnx.Connecting();
            try {
                stm=cnx.getConnect().createStatement();
                res=stm.executeQuery(Query);
                if (res.next()) {
                    System.out.println("residence.Conf.jButton9ActionPerformed()" +res.getInt(1));
                    //JOptionPane.showMessageDialog(null, "The value is :"+res.getInt(1));

                    Value=res.getInt(1);
                }
                stm.close();
                res.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "The Error is  :"+e.getMessage());
            }

            cnx.Deconnect();

            if(Value>0){

                int x= JOptionPane.showConfirmDialog(null, "تم الحصول علي مدير في قاعدةالبيانات هل تريد تعديل معلومات ","رسالة تأكيد",1);

                JOptionPane.showMessageDialog(null, "THe Value x:"+x);

                if (x==0) {

                    String QuerUpdate="Select * from Manager where kind_Manager="+KindManger;
                    /* String QuerUpdate2="update  Manager set FirstName =?,LastName=?,Num_Decision=?,Date_Decision=?  where kind_Manager="+KindManger;
                    PreparedStatement prstm=null;*/
                    cnx.Connecting();
                    try {

                        /*  prstm=cnx.getConnect().prepareStatement(QuerUpdate2);

                        prstm.setString(1, "");
                        prstm.setString(2, "");
                        prstm.setInt(3, 2);
                        prstm.setDate(4, new java.sql.Date(new Date().getTime()));
                        */

                        stm=cnx.getConnect().createStatement();
                        res=stm.executeQuery(QuerUpdate);
                        if (res.next()) {

                            /* JOptionPane.showMessageDialog(null, res.getString("FirstName")+" "+res.getString("LastName")+" "+
                                res.getInt("Num_Decision")+" "+res.getDate("Date_Decision"));*/
                            NamDrct.setText(res.getString("FirstName"));
                            LastNmDrc.setText(res.getString("LastName"));
                            DcsnDrct.setText(Integer.toString(res.getInt("Num_Decision")));
                            SimpleDateFormat format=new  SimpleDateFormat("dd/MM/yyyy");
                            String s=format.format(res.getDate("Date_Decision"));
                            DateDcsDrct.setText(s);
                            BtnSavDirc.setText("حفظ التعديلات");
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "ERROOOOOOOOR"+e.getMessage());
                    }
                    try {
                        stm.close();
                        res.close();
                        cnx.Deconnect();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

            }else{
                Date date = null;
                try {
                    String Sdate=DateDcsDrct.getText();
                    SimpleDateFormat Sdf=new SimpleDateFormat("dd/MM/yyyy");

                    date=Sdf.parse(Sdate);
                } catch (ParseException ex) {
                    System.err.println("The Error Is :"+ex.getMessage());
                }
                // JOptionPane.showMessageDialog(null, "The Date :"+date);
                InsertManager(NamDrct.getText(), LastNmDrc.getText(), Integer.parseInt(DcsnDrct.getText()), date, KindManger);
                EnableFieldManger(false);
                EmptyFieldManager();
                //BtnSavDirc.setText("حفظ");
                TableDirct.getSelectionModel().clearSelection();
            }

        }else{
            //JOptionPane.showMessageDialog(null, "It's Not  comparable Now We must Update Director");
            PreparedStatement prst=null;

            cnx.Connecting();
            String QueryUpdatMng="UPDATE Manager set FirstName=?,LastName=?,Num_Decision=?,Date_Decision=? where kind_Manager="+KindManger;
            try {
                prst=cnx.getConnect().prepareStatement(QueryUpdatMng);
                prst.setString(1, NamDrct.getText());
                prst.setString(2, LastNmDrc.getText());
                prst.setInt(3,Integer.parseInt(DcsnDrct.getText()));
                Date Dat=new SimpleDateFormat("dd/MM/yyyy").parse(DateDcsDrct.getText());
                prst.setDate(4,new java.sql.Date(Dat.getTime()));
                int c=prst.executeUpdate();
                if (c>0) {
                    JOptionPane.showMessageDialog(null, "تم التعديل بنجاح ");
                    FillTableManager();
                }else {
                    JOptionPane.showMessageDialog(null, "Error  Update ");
                }
                EnableFieldManger(false);
                EmptyFieldManager();
                //BtnSavDirc.setText("حفظ");
                TableDirct.getSelectionModel().clearSelection();

                prst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error  Sql update Manager"+e);
            } catch (ParseException ex) {
                Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
            }
            cnx.Deconnect();

            BtnSavDirc.setText("حفظ");
        }

    }//GEN-LAST:event_BtnSavDircActionPerformed
public void InsertManager(String FNam,String LNam,int NumDcs,Date DatDcs,int KndMng){
   
        PreparedStatement prstm=null;
        String Query="insert into Manager(FirstName,LastName,Num_Decision,Date_Decision,kind_Manager)"
                + "values (?,?,?,?,?) ";
    cnx.Connecting();
        try {
            prstm=cnx.getConnect().prepareStatement(Query);
            prstm.setString(1, FNam);
            prstm.setString(2, LNam);
            prstm.setInt(3, NumDcs);
            prstm.setDate(4, new java.sql.Date(DatDcs.getTime()));
            prstm.setInt(5,KndMng);
            int x=prstm.executeUpdate();
            if (x>0) {
                //JOptionPane.showMessageDialog(null, "Succesful Add..");
                FillTableManager();
            }else JOptionPane.showMessageDialog(null, "Error In Add Manager ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in Insert Manager :"+e.getMessage());
        }
        finally{
        
            if (cnx.getConnect()!=null) {
                try {
                    prstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
                }
                cnx.Deconnect();
            }
        }
       
    
    }
    private void UpdtInDrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdtInDrcActionPerformed
        if (TableDirct.getSelectedRow()>=0) {
            EnableFieldManger(true);
            BtnSavDirc.setText("حفظ التعديلات");
            BtnSavDirc.setEnabled(true);

        }else {
            JOptionPane.showMessageDialog(null, "الرجاء اختيار من الجدول");
        }
    }//GEN-LAST:event_UpdtInDrcActionPerformed

    private void DelDrctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DelDrctActionPerformed
        if (TableDirct.getSelectedRow()==-1) {
            JOptionPane.showMessageDialog(null, "الرجاء اختيار من الجدول");

        }else {        //deleteManager(jtab);
            deleteManager((String)TableDirct.getValueAt(TableDirct.getSelectedRow(), 0));
            EmptyFieldManager();
            EnableFieldManger(false);

        }

    }//GEN-LAST:event_DelDrctActionPerformed

    private void NewDrctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewDrctActionPerformed
        BtnSavDirc.setEnabled(true);
        EmptyFieldManager();

        EnableFieldManger(true);

        BtnSavDirc.setText("حفظ");
        TableDirct.getSelectionModel().clearSelection();

    }//GEN-LAST:event_NewDrctActionPerformed
settings setting=new settings();
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int x = 0;
        x=JOptionPane.showConfirmDialog(null,
            "هل تريد انشاء نسخة احتياطية ",
            "تأكيد الرسالة",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.CLOSED_OPTION);

        if ((x==0)) {            //&&(TimeRepat1>=13 && TimeRepat1<21)
            // takeRepastRemplissage.DeletAllResTakeRopast();
            setting.Create_Backup();

        }else{

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        setting.Export_Data_To_AccessDB();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        String Proffession_txt=jTextField4.getText();
        Employer emp=new Employer();
        if (Proffession_txt.equals("")) {
            JOptionPane.showMessageDialog(null, "تأكد من تعبئة حقل المهنة");
        }else{
            emp.InsertProffession(Proffession_txt);
            JOptionPane.showMessageDialog(null, "تمت الاضـافة بنجاح");
        }
    }//GEN-LAST:event_jButton34ActionPerformed

    private void CommNameFrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CommNameFrFocusGained
        if (CommNameFr.getText().equals("Commune")) {
            CommNameFr.setText("");
        }
    }//GEN-LAST:event_CommNameFrFocusGained

    private void CodeComFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CodeComFocusGained
        if (CodeCom.getText().equals("Code")) {
            CodeCom.setText("");
        }
    }//GEN-LAST:event_CodeComFocusGained

    private void CodeComFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CodeComFocusLost
        if (CodeCom.getText().equals("")) {
            CodeCom.setText("Code");
        }

    }//GEN-LAST:event_CodeComFocusLost

    private void NameWilayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameWilayaActionPerformed
        FillTableCommune_Combobox((String)NameWilaya.getSelectedItem(),jTable1,null,0);
    }//GEN-LAST:event_NameWilayaActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int n=JOptionPane.showConfirmDialog(null,"هل تريد تعديل على البلدية","عملية التعديل", JOptionPane.YES_NO_OPTION);
        if (n==0) {
            int idWilaya=get_Info_DB.GetId_From_DB("ID_Wilaya", "Wilaya", "NameWilaya", (String) NameWilaya.getSelectedItem());
            InsertCommune(CodeCom.getText(),CommNameAR.getText(),CommNameFr.getText(),idWilaya,1);

            FillTableCommune_Combobox((String)NameWilaya.getSelectedItem(),jTable1,null,0);
            CodeCom.setText("Code");
            CommNameAR.setText("البلدية");
            CommNameFr.setText("Commune");
            Code_ComLab.setText("");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void CommNameARFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CommNameARFocusGained
        if (CommNameAR.getText().equals("البلدية")) {
            CommNameAR.setText("");
        }

    }//GEN-LAST:event_CommNameARFocusGained
AddWilaya adwl;
    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        adwl=new AddWilaya(this);
        adwl.setVisible(true);
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        if (CodeCom.getText().equals("Code")||CodeCom.getText().equals("")||
            CommNameFr.getText().equals("")||CommNameFr.getText().equals("Commune")||
            CommNameAR.getText().equals("")||CommNameAR.getText().equals("البلدية"))
        {
            JOptionPane.showMessageDialog(null, "تأكد من ادخال معلوماتك صحيحة");
        }else{int idWilaya=get_Info_DB.GetId_From_DB("ID_Wilaya", "Wilaya", "NameWilaya", (String) NameWilaya.getSelectedItem());
            // AddCommune(CodeCom.getText(), CommNameAR.getText(),CommNameFr.getText(),  idWilaya);
            InsertCommune(CodeCom.getText(),CommNameAR.getText(),CommNameFr.getText(),idWilaya,0);
            FillTableCommune_Combobox((String)NameWilaya.getSelectedItem(),jTable1,null,0);
            CodeCom.setText("Code");
            CommNameAR.setText("البلدية");
            CommNameFr.setText("Commune");
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int slcRow=jTable1.getSelectedRow();
        CodeCom.setText((String)jTable1.getValueAt(slcRow, 0));
        int idComm=GetId_Commune(CodeCom.getText());

        Code_ComLab.setText(idComm+""); //id Of Commune

        CommNameAR.setText((String)jTable1.getValueAt(slcRow, 1));
        CommNameFr.setText((String)jTable1.getValueAt(slcRow, 2));

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        int n=JOptionPane.showConfirmDialog(null,
            "هل تريد حذف  البلدية",
            "عملية الحذف", JOptionPane.YES_NO_OPTION);
        if (n==0) {
            //            int slcRow=jTable1.getSelectedRow();
            //            CodeCom.setText((String)jTable1.getValueAt(slcRow, 0));
            //             CodeCom.setText((String)jTable1.getValueAt(slcRow, 1));
            //              CodeCom.setText((String)jTable1.getValueAt(slcRow, 2));

            int idWilaya=get_Info_DB.GetId_From_DB("ID_Wilaya", "Wilaya", "NameWilaya", (String) NameWilaya.getSelectedItem());
            deleteCommune();
            FillTableCommune_Combobox((String)NameWilaya.getSelectedItem(),jTable1,null,0);
            CodeCom.setText("Code");
            CommNameAR.setText("البلدية");
            CommNameFr.setText("Commune");
            Code_ComLab.setText("");
        }

    }//GEN-LAST:event_jButton12ActionPerformed

    private void LabLevel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabLevel1MouseClicked
        //PanComANDWilaya
        LabLevel1.setBackground(new Color(0,190,190));
        LabNationality.setBackground(new Color(0,153,153));
        LabBranch.setBackground(new Color(0,153,153));
        LabFaculty.setBackground(new Color(0,153,153));
        LabLevel.setBackground(new Color(0,153,153));
        PanLevelStudy.setVisible(false);
        PanBranch.setVisible(false);
        PanNationality.setVisible(false);
        PanFaculty.setVisible(false);
        jPanel1.setVisible(true);
        // get_Info_DB.GetId_From_DB("ID_Wilaya", "Wilaya", "NameWilaya", (String) NameWilaya.getSelectedItem());
        //        get_Info_DB.Filling(NameWilaya, "Wilaya", "NameWilaya", 1);
        //        NameWilaya.removeItem("/");

        Code_ComLab.setVisible(false);

        jLabel25.setVisible(false);
        Filling_NameWilaya(NameWilaya);

    }//GEN-LAST:event_LabLevel1MouseClicked

    private void TabBranchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabBranchMouseClicked
        val=1 ;// val=1 for update && 0 for  insert 
        SaveNaTio3.setBackground(new Color(0,153,153));
        int RowSlc=TabBranch.getSelectedRow();

        //          BranchAR.setText((String)TabBranch.getValueAt(RowSlc, 0));
        //
        //          BranchFr.setText((String)TabBranch.getValueAt(RowSlc, 1));
        //
        //          CodFil.setText((String)TabBranch.getValueAt(RowSlc, 2));
        //
        //          DomaineAr.setText((String)TabBranch.getValueAt(RowSlc, 3));
        //          DomaineCombo.setSelectedItem((String)TabBranch.getValueAt(RowSlc, 3));
        //
        //          Domaine.setText((String)TabBranch.getValueAt(RowSlc, 4));
        //
        //
        //          CodDom.setText((String)TabBranch.getValueAt(RowSlc, 5));
        int idBranch=(int) TabBranch.getValueAt(RowSlc, 6);
        jLabel1.setText(idBranch+"");

    }//GEN-LAST:event_TabBranchMouseClicked

    private void BranchARFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BranchARFocusGained
        if(BranchAR.getText().equals("ادخــــل الــتــخـــصــص ")){
            BranchAR.setText("");
        }
        BranchAR.setForeground(Color.WHITE);
    }//GEN-LAST:event_BranchARFocusGained

    private void BranchARFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BranchARFocusLost
        if (BranchAR.getText().equals("")) {  //الــتــخـــصــص
            BranchAR.setText("ادخــــل الــتــخـــصــص ");
        }
        //الــتــخـــصــص
    }//GEN-LAST:event_BranchARFocusLost

    private void BranchFrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BranchFrFocusGained
        if(BranchFr.getText().equals("Branche")){
            BranchFr.setText("");
        }
        BranchFr.setForeground(Color.WHITE);
    }//GEN-LAST:event_BranchFrFocusGained

    private void BranchFrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BranchFrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BranchFrActionPerformed

     public  void FillAllBranche(JTable tab){
    
        String Query="select * from Branch_Study";
        DefaultTableModel df=(DefaultTableModel)tab.getModel();
        df.setRowCount(0);
        Statement stm=null;
        ResultSet res=null;
        cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            while (res.next()) {                
                Object arg[]={res.getString("BranchStd_Name"),res.getString("BranchStd_NameFr"),res.getString("branch_code"),
                              res.getString("domaine_Label_ar"),res.getString("domaine_Label"),res.getString("domaine_Code"),
                               
                               res.getInt("Id_BranchStd")
                                };
                
                df.addRow(arg);
            }
        tab.setModel(df);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in fILLAllBranche"+e.getMessage());
        }
        
        try {
            stm.close();
            res.close();
            cnx.Deconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
     /****************************************************/
     public void Update_Branch_(String BranchStd_Name,String BranchStd_NameFr,String branch_code,String domaine_Code,
         String domaine_Label,String domaine_Label_ar,int ID,int choice){
    PreparedStatement prstm=null;
    String Query;
     if (choice==0) {
         Query="insert into Branch_Study (BranchStd_Name,BranchStd_NameFr,branch_code,domaine_Code,domaine_Label,domaine_Label_ar) "
        + " values (N'"+BranchStd_Name+"','"+BranchStd_NameFr+"','"+branch_code+"','"+domaine_Code+"','"+domaine_Label+"',N'"+domaine_Label_ar+"' )";
     } else if(choice==1){
         Query="UPDATE Branch_Study SET BranchStd_Name=N'"+BranchStd_Name+"' , BranchStd_NameFr='"+BranchStd_NameFr+"' ,"
        + " branch_code='"+branch_code+"' ,domaine_Code='"+domaine_Code+"',domaine_Label='"+domaine_Label+"', domaine_Label_ar=N'"+domaine_Label_ar+"' "
            + "  WHERE Id_BranchStd = "+ID;
          }else {
     Query ="Delete From Branch_Study where Id_BranchStd ="+ID+" ";
     }
 
   cnx.Connecting();
    try {
        prstm=cnx.getConnect().prepareStatement(Query);
        
         int x=prstm.executeUpdate();

         
    } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Error in SQL Branch_Faculty_Nationality "+e.getMessage());
    }
    
    try {
        prstm.close();
        cnx.Deconnect();
    } catch (Exception e) {
    }
}
    
     /***************************************************/
     public String GetCodeDomaine(String Domaine,int val){
     String Code_Domaie="";
     String Query="SELECT Code_Domaine from Domaine where ";
     
     if (val==1) {
         Query+=" Domaine_Ar=N'"+Domaine+"' ";
     }else Query+=" Domaine_Fr='"+Domaine+"' ";
 
 Statement stm=null;
 ResultSet res=null;
 
         cnx.Connecting();
     try {
         stm=cnx.getConnect().createStatement();
         res=stm.executeQuery(Query);
         if (res.next()) {
             Code_Domaie=res.getString("Code_Domaine");
         }
         stm.close();
         res.close();
       cnx.Deconnect();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Error In get  Code Domaine "+e.getMessage());
     }
 return Code_Domaie;
 }
    
    
    private void SaveBranchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveBranchMouseClicked
        if(val==1){
            SaveNaTio3.setBackground(new Color(0,153,153));
            // JOptionPane.showMessageDialog(null, " MOOOOOOOOdIIIIIIIIIIIfIcation");
            IDTab=Integer.parseInt(jLabel1.getText());
            Update_Branch_(BranchAR.getText(),BranchFr.getText(),CodFil.getText(),CodDom.getText(),
                Domaine.getText(),DomaineAr.getText(),Integer.parseInt( jLabel1.getText()),1);
            val=0;
            //get_Info_DB.GetAllBranche(TabBranch,"Id_BranchStd","Branch_Study","BranchStd_NameFr", "BranchStd_Name");
            FillAllBranche(TabBranch);
            RemoveEle(TabBranch);
            JOptionPane.showMessageDialog(null, "تم التعديل بنجاح");

        } else{  // this for insert New Branche
            if(!BranchAR.getText().equals("ادخــــل الــتــخـــصــص ")&&!BranchAR.getText().equals("")){
                Update_Branch_(BranchAR.getText(),BranchFr.getText(),CodFil.getText(),CodDom.getText(),
                    Domaine.getText(),DomaineAr.getText(),1/*Integer.parseInt( jLabel1.getText())*/,0);  
                FillAllBranche(TabBranch);
                RemoveEle(TabBranch);
                JOptionPane.showMessageDialog(null, "تمت الاضافة بنجاح");
            }
        }
        initialisation();
    }//GEN-LAST:event_SaveBranchMouseClicked

    private void jLabel102MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel102MouseClicked
        SaveNaTio3.setBackground(new Color(0,153,153));
        initialisation();
        val=0;
    }//GEN-LAST:event_jLabel102MouseClicked

    private void CodDomFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CodDomFocusGained
        if (CodDom.getText().equals("Code domaine")) {
            CodDom.setText("");
        }
    }//GEN-LAST:event_CodDomFocusGained

    private void CodDomFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CodDomFocusLost
        // TODO add your handling code here: Code domaine
        if (CodDom.getText().equals("")) {
            CodDom.setText("Code domaine");
        }
    }//GEN-LAST:event_CodDomFocusLost

    private void CodDomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodDomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodDomActionPerformed

    private void CodFilFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CodFilFocusGained
        if (CodFil.getText().equals("Code filiere")) {
            CodFil.setText("");
        }
    }//GEN-LAST:event_CodFilFocusGained

    private void CodFilFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CodFilFocusLost
        if (CodFil.getText().equals("")) {
            CodFil.setText("Code filiere");
        }
    }//GEN-LAST:event_CodFilFocusLost

    private void DomaineArFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DomaineArFocusGained
        if (DomaineAr.getText().equals("الشعبة")) {
            DomaineAr.setText("");
        }
    }//GEN-LAST:event_DomaineArFocusGained

    private void DomaineArFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DomaineArFocusLost
        if (DomaineAr.getText().equals("")) {
            DomaineAr.setText("الشعبة");
        }
    }//GEN-LAST:event_DomaineArFocusLost

    private void DomaineFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DomaineFocusGained
        if (Domaine.getText().equals("Domaine")) {
            Domaine.setText("");
        }
    }//GEN-LAST:event_DomaineFocusGained

    private void DomaineFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DomaineFocusLost
        if (Domaine.getText().equals("")) {
            Domaine.setText("Domaine");
        }
    }//GEN-LAST:event_DomaineFocusLost

    private void DomaineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DomaineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DomaineActionPerformed

    private void DomaineComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DomaineComboActionPerformed
        DomaineAr.setText((String)DomaineCombo.getSelectedItem());

        CodDom.setText(GetCodeDomaine((String)DomaineCombo.getSelectedItem(),1));
    }//GEN-LAST:event_DomaineComboActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        if (jTextField2.getText().equals("بحث")) {
            jTextField2.setText("");
        }
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        student_ResRemplissage.FilterResident(jTextField2.getText(), TabBranch, (DefaultTableModel) TabBranch.getModel());
    }//GEN-LAST:event_jTextField2KeyReleased

    private void DomaineCombo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DomaineCombo1ActionPerformed
        Domaine.setText((String)DomaineCombo1.getSelectedItem());
        CodDom.setText(GetCodeDomaine((String)DomaineCombo1.getSelectedItem(),0));
    }//GEN-LAST:event_DomaineCombo1ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
            CommNameAR.setText("البلدية");
            CommNameFr.setText("Commune");
            CodeCom.setText("Code");
            NameWilaya.setSelectedIndex(0);
    }//GEN-LAST:event_jButton13ActionPerformed
String RepatNm="";
    public void RemoveEle(JTable tab){
       int i=0;
      DefaultTableModel dftabMd=(DefaultTableModel)tab.getModel();
        while(i<tab.getRowCount()){
            String a=(String) tab.getValueAt(i,1);
            if(a.equals("/")){
                dftabMd.removeRow(i);
                break;
            }
            i++;
        }
    }
    
  public void FillTableManager(){
    DefaultTableModel dfm=new DefaultTableModel();
    //dfm=(DefaultTableModel)jTable2.getModel();
    String Query="select *from Manager";
    Statement stm=null;
    ResultSet Res=null;
    dfm.addColumn("مدير");
    dfm.addColumn("الاسم");
    dfm.addColumn("اللقب");
    dfm.addColumn("رقم القرار");
    dfm.addColumn("تاريخ القرار");
    
    cnx.Connecting();
    try{
    stm=cnx.getConnect().createStatement();
    Res=stm.executeQuery(Query);
    String mang;
        while (Res.next()) {
        
            if (Res.getInt("kind_Manager")==1) {
              mang="الاقامةالجامعية" ;
            }else mang="الخدمات الجامعية";
    
         Object arg[]={mang,Res.getString("FirstName"),Res.getString("LastName"),
         Res.getInt("Num_Decision"), Res.getDate("Date_Decision")
         };           
         dfm.addRow(arg);
        }
        TableDirct.setModel(dfm);
        
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Error In Fill Table Of managers "+e.getMessage());
    
    }
    
        try {
            stm.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error In Close Statement"+e.getMessage());
        }
    }  
  
     public void deleteManager(String val){
    int typ=0;
        //JOptionPane.showMessageDialog(null, "The word is :"+val);
        if (val.equals("الاقامةالجامعية")) {
         typ=1;   
        }else typ=2;
        
        //JOptionPane.showMessageDialog(null, "The value  is :"+typ);
    
    String Del="delete from Manager where kind_Manager="+typ;
    PreparedStatement prstm=null;
    cnx.Connecting();
        try {
            prstm=cnx.getConnect().prepareStatement(Del);
            int x=prstm.executeUpdate();
            if (x>0) {
                //JOptionPane.showMessageDialog(null, "Succeful Delete..");
                FillTableManager();
            }else {
            JOptionPane.showMessageDialog(null, "لا يمكن الحذف");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in delete manager :"+e.getMessage());
        }
        try {
            prstm.close();
            cnx.Deconnect();
        } catch (Exception e) {
            
        }
    }
    /*********************************************************/
     public void FillTableCommune_Combobox(String Wly,JTable tab,JComboBox cmb,int choice){
    
    //dfm=(DefaultTableModel)jTable2.getModel();
    String Query="SELECT * FROM Commune,Wilaya where Commune.ID_Wilaya=Wilaya.ID_Wilaya "
            + " AND Wilaya.NameWilaya=N'"+Wly+"' ;";
    
    Statement stm=null;
    ResultSet Res=null;
    cnx.Connecting();
    if (choice==0) {
    //DefaultTableModel dfm=new DefaultTableModel();
   DefaultTableModel dfm=(DefaultTableModel) tab.getModel();
    dfm.setRowCount(0);
//    dfm.addColumn("Code");
//    
//    dfm.addColumn("البلدية");
//    dfm.addColumn("Commune");
     try{
    stm=cnx.getConnect().createStatement();
    Res=stm.executeQuery(Query);
    System.out.println("Run query passed");
        while (Res.next()) {
         Object arg[]={Res.getString("Code_commune"),Res.getString("Commune_Ar"),Res.getString("Commune_Fr")};  
            //System.out.println("Arg:"+arg.toString());         
         dfm.addRow(arg);
        }
        tab.setModel(dfm);
        
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Error In Fill Table Of managers "+e.getMessage());
    
    }
    
    }else 
    {
     DefaultComboBoxModel Dfcmb=new DefaultComboBoxModel();
     Dfcmb=(DefaultComboBoxModel) cmb.getModel();
     Dfcmb.removeAllElements();
     try{
       stm=cnx.getConnect().createStatement();
       Res=stm.executeQuery(Query);
       Dfcmb.addElement("اختر البلدية");
            while (Res.next()){                
                Dfcmb.addElement(Res.getString("Commune_Ar"));
             }
            cmb.setModel(Dfcmb);
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Error In Fill Table Of managers "+e.getMessage());
    
    }
    
    }
    try {
            stm.close();
            Res.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error In Close Statement"+e.getMessage());
        }
    }
   
     
     public void InsertCommune(String Code_commune,String Commune_Ar,String Commune_Fr,int ID_Wilaya,int choice)
{   
PreparedStatement prstm=null;
String Query="insert into Commune (Code_commune,Commune_Ar,Commune_Fr,ID_Wilaya) values "
             + "('"+Code_commune+"' , N'"+Commune_Ar+"' ,'"+Commune_Fr+"',"+ID_Wilaya+");";

 //QueryUpdt="update  Commune set Code_commune='"+Code_commune+"' ,Commune_Ar=N'"+Commune_Ar+"',Commune_Fr='"+Commune_Fr+"' where ID_Wilaya="+ID_Wilaya+";";
        
    if (choice==1) {
        
       // int id_Commune=GetId_Commune();
        int id_Commune=Integer.parseInt(Code_ComLab.getText());
        Query="update  Commune SET Code_commune='"+Code_commune+"' ,Commune_Ar=N'"+Commune_Ar+"',Commune_Fr='"+Commune_Fr+"' where id_Commune="+id_Commune+";";
     
    }
 
cnx.Connecting();
        try {
                prstm=cnx.getConnect().prepareStatement(Query);
                int x=prstm.executeUpdate();
                System.out.println("Insert Query  Executed..");
                if (x>0) {
                    System.out.println("Add Commune Success");
                }else  System.out.println("Failed Commune Added");         
                prstm.close();
                cnx.Deconnect();
    }catch (SQLException e) {
         e.printStackTrace();
    }
}   
     /*****************************************************************/
     public void FillingWilayInFormConf(){
    Filling_NameWilaya(NameWilaya);
    }
     
      public void Filling_NameWilaya(JComboBox NameWilaya){
    get_Info_DB.Filling(NameWilaya, "Wilaya", "NameWilaya", 1);
        NameWilaya.removeItem("/");
     }
     /***************************************************************/
      public int GetId_Commune(String Code_commune){
        int Id_Commune=0;
        String Query="select id_Commune FROM Commune WHERE Code_commune='"+Code_commune+"' ";
        Statement stm;
        ResultSet res;
        cnx.Connecting();
        try {
            stm=cnx.getConnect().createStatement();
            res=stm.executeQuery(Query);
            if (res.next()) {
               Id_Commune=res.getInt("id_Commune");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error In Get Id Commune"+e.getMessage());
        }
        return Id_Commune;
    }
      public void deleteCommune(){
//    int typ=0;
//        //JOptionPane.showMessageDialog(null, "The word is :"+val);
//        if (val.equals("الاقامةالجامعية")) {
//         typ=1;   
//        }else typ=2;
//        
        //JOptionPane.showMessageDialog(null, "The value  is :"+typ);
    int id_commune=Integer.parseInt(Code_ComLab.getText());
    String Del="delete from Commune where id_Commune="+id_commune+" ";
    PreparedStatement prstm=null;
    cnx.Connecting();
        try {
            prstm=cnx.getConnect().prepareStatement(Del);
            int x=prstm.executeUpdate();
            if (x>0) {
                //JOptionPane.showMessageDialog(null, "Succeful Delete..");
                FillTableCommune_Combobox((String)NameWilaya.getSelectedItem(),jTable1,null,0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in delete Commune :"+e.getMessage());
        }
        try {
            prstm.close();
            cnx.Deconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      
      
     public void Verify_Col_Wilaya (){
        Statement stm;
        ResultSet resTab;
         String Query ="select * from Wilaya ;";
         cnx.Connecting(); 
       try {
           stm=cnx.getConnect().createStatement();
           resTab=stm.executeQuery(Query);
           ResultSetMetaData metaData = resTab.getMetaData();  //get Names of colums in table Wilaya
           int NbrClm= metaData.getColumnCount(),i=1;          // get count of colums 
           
           ArrayList<String> list_name;                         //array to store names of colums 
           list_name = new ArrayList<>();
           
           if (NbrClm!=4) {                 //when Nbr Colums not correct !=4
            
            while (i<=NbrClm) 
            {
                list_name.add(metaData.getColumnName(i));           // add columus names in arrayList
                System.out.println("The Name Of Colums in array :"+list_name.get(i-1));
                
               i++;
            }
            
            if (!list_name.contains("DescWilaya")) {
                   addColumeBranchStudy("Wilaya", "DescWilaya", 2); // this fun add colums in table wilaya
               }
            
               if (!list_name.contains("NumWilaya")) {
                   addColumeBranchStudy("Wilaya", "NumWilaya", 1);
               }
               
               String WilayaCode="Adrar-1/Chlef-2/Laghouat-3/Oum El Bouaghi-4/Batna-5/Béjaïa-6/Biskra-7/Béchar-8/"
                       + "Blida-9/Bouira-10/Tamanrasset-11/Tébessa-12/Tlemcen-13/Tiaret-14/Tizi Ouzou-15/Alger-16/Djelfa-17/"
                       + "Jijel-18/Sétif-19/Saïda-20/Skikda-21/Sidi Bel Abbès-22/Annaba-23/Guelma-24/Constantine-25/"
                       + "Médéa-26/Mostaganem-27/MSila-28/Mascara-29/Ouargla-30/Oran-32/El Bayadh-33/Illizi-34/Bordj Bou Arreridj-34/"
                       + "Boumerdès-35/El Tarf-36/Tindouf-37/Tissemsilt-38/El Oued-39/Khenchela-40/Souk Ahras-41/Tipaza-42/Mila-43/Aïn Defla-44/"
                       + "Naàma-45/Aïn Témouchent-46/Ghardaïa-47/Relizane-48";
            
            String WilayaNames[]=WilayaCode.split("/");                  //split text (name wilaya and add in table WilayaNames)
            int LenghtTabWilaya=WilayaNames.length; 
               System.out.println(Arrays.toString(WilayaNames));
            System.out.println("The Size of wilaya codes"+LenghtTabWilaya);
            
            PreparedStatement prstm;
            String DescWilaya;
            int    NumWilaya;
            String [] Wil_Num;
            
            for (i=0;i<LenghtTabWilaya;i++ )    
           {
               
               System.out.println("The Name Of wilaya-xx :"+WilayaNames[i]);
              Wil_Num= WilayaNames[i].split("-");        //split wilaya-xx to wilaya  xx
              
              DescWilaya=Wil_Num[0];                    //  DescWilaya=Wil_Num[0]; 
              NumWilaya=Integer.valueOf(Wil_Num[1]);    //  NumWilaya=Wil_Num[1]
              // QueryUpdate = "UPDATE Wilaya SET DescWilaya='"+DescWilaya+"' , NumWilaya= "+NumWilaya +" where ID_Wilaya = "+i+" ";
              prstm=cnx.getConnect().prepareStatement("UPDATE Wilaya SET DescWilaya='"+DescWilaya+"' , NumWilaya= "+NumWilaya +" where ID_Wilaya = "+(i+1)+" ");
              prstm.executeUpdate();
              prstm.close();   
              
           }
            
            prstm=cnx.getConnect().prepareStatement("UPDATE Wilaya SET DescWilaya='/' , NumWilaya= "+0+" where NameWilaya ='/' ");
            prstm.executeUpdate();
            prstm.close();
            
           }
           
    
        resTab.close();
        stm.close();
     
     }catch(SQLException ex){
        ex.printStackTrace();
     }
         
         try {
             
           cnx.Deconnect();
             
         } catch (Exception e) {
         }
         
         
     }
      
      
      
      
    /*********************************************************************/
      public  void   Verify_Colums_ComWil(/*String TableNM,String NamCol*/){
   // String Query="Select * from ";
   //boolean stat=true;
    ResultSet resTab=null;
    Statement stm=null;
     ArrayList<String> ListNamCol=new ArrayList<> ();
    cnx.Connecting();
    /************************Verifier Colum of Branch*********************************/
       String Query22 ="select * from Branch_Study ;";
       try {
           
           stm=cnx.getConnect().createStatement();
           resTab=stm.executeQuery(Query22);
           ResultSetMetaData metaData = resTab.getMetaData();
           int NbrClm= metaData.getColumnCount();
           
           System.out.println("Colum Name:"+metaData.getColumnName(1));
//           if (metaData.getColumnName(1) == null ? NamCol == null : metaData.getColumnName(1).equals(NamCol)) {
//               return 1;
//           }
        
          int i=1;
          while (i<=NbrClm) {               
               System.out.println("Colum Name:"+metaData.getColumnName(i));
                ListNamCol.add(metaData.getColumnName(i));
               i+=1;
         }
          /**************************************************/
           if (!ListNamCol.contains("branch_code")) {
               addColumeBranchStudy("Branch_Study", "branch_code", 2);
           }
            if (!ListNamCol.contains("domaine_Code")) {
               addColumeBranchStudy("Branch_Study", "domaine_Code", 2);
               
           }
            
            if (!ListNamCol.contains("domaine_Label")) {
               addColumeBranchStudy("Branch_Study", "domaine_Label", 2);
           }
          if (!ListNamCol.contains("domaine_Label_ar")) {
               addColumeBranchStudy("Branch_Study", "domaine_Label_ar", 3);//arab field nvarchar(200) type
           } 
//           System.out.println("the Resident"+ listTable.toString());
           /**********************************************************************************/
       } catch (SQLException ex) {
           System.out.println("after Exception running"+ ex.getMessage());
     
       }
        try {

           resTab.close();
           stm.close();
          
           cnx.Deconnect();
          
       } catch (SQLException ex) {
           System.out.println("SQLException"+ ex.getMessage());
          
       }
       
      }
      /*******************************************************************/
      public void addColumeBranchStudy(String Table,String NamCol,int IndColType){
    Statement stm=null;
    String TypeCol="";
        if (IndColType==1) {
            TypeCol=" int ";
        }else if(IndColType==2){ //string french
        TypeCol=" varchar(200) ";
        }else if (IndColType==3) {
            TypeCol=" nvarchar(200) ";
        }  
    String Query1="ALTER TABLE "+Table+" ADD "+NamCol+" "+TypeCol+" ;";
     //String Query="ALTER TABLE Branch_Study ADD Code_filiere varchar(200)"; 
      try {
          stm=cnx.getConnect().createStatement();
          int x=stm.executeUpdate(Query1);
            System.out.println("residence.Advanced_prp.addColume()");

         } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR CREATE COLUM :"+e.getMessage());
        }
       try {
           stm.close();
       } catch (SQLException ex) {
           Logger.getLogger(Advanced_prp.class.getName()).log(Level.SEVERE, null, ex);
       }
    
    }
      /****************************************************************/
       public void CreateDomaineTable(){
    
     ResultSet resTab=null;
    Statement stm=null;
    ArrayList<String> listTable=new ArrayList<> ();
    cnx.Connecting(); 
    
    String Query="CREATE TABLE Domaine " +
"( id_Domaine int  IDENTITY(1,1) not NULL, " +
"  Code_Domaine VARCHAR(200) ,  " +
"  Domaine_Ar NVARCHAR(200) , " +
"  Domaine_Fr VARCHAR(200) , " +                 
"  PRIMARY KEY ( id_Domaine )  );";
     try {
            DatabaseMetaData dmd=cnx.getConnect().getMetaData();  //TO GET Name table of db
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
        
        if (listTable.contains("Domaine")) {
            System.out.println("The Table Is Obtained ");
            System.out.println("Index Of Table Student Residets :"+listTable.indexOf("Commune"));
        }else{
            
            System.out.println("No Item Found ......!");
                    
        try {
            stm=cnx.getConnect().createStatement();
           boolean xx=stm.execute(Query);
           
            System.out.println("the boolean xx :"+xx);
            stm.close();
       cnx.Deconnect();
        } catch (SQLException ex) {
            System.err.println("Error Sql Create Table :"+ex.getMessage());
        }
        
        AddDomaine("D01", "علوم و تكنولوجيا", "Sciences et Technologies");
        AddDomaine("D02", "علوم المادة", "Sciences de la Matière");
        AddDomaine("D03", "رياضيات و إعلام آلي", "Sciences de la Matière");
        AddDomaine("D04", "علوم الطبيعة و الحياة", "Sciences de la Nature et de la Vie");
        AddDomaine("D05", "علوم الارض و الكون", "Sciences de la Terre et de l Univers");
        AddDomaine("D06", "علوم إقتصادية و التسيير و علوم تجارية", "Sciences Economiques de Gestion et Commerciales");
        AddDomaine("D07", "حقوق و علوم سياسية", "Droit et Sciences Politiques");
        AddDomaine("D08", "آداب و لغات اجنبية", "Lettres et Langues Etrangères");
        AddDomaine("D09", "علوم انسانية و اجتماعية", "Sciences Humaines et Sociales");
        AddDomaine("D10", "علوم و تقنيات النشاطات البدنية و الرياضية", "Sciences et Techniques des Activités Physiques Sportives");
        AddDomaine("D12", "لغة وأدب عربي", "Langue et Littératures Arabes");
        AddDomaine("D14", "هندسة معمارية، عمران ومهن المدن", "Architecture, urbanisme et métiers de la ville");

        }    
    }
    
     public void AddDomaine(String Code_Domaine,String Domaine_Ar,String Domaine_Fr)
    {
        PreparedStatement prstm=null;
      //  String Query="insert into Domaine (Code_Domaine,Domaine_Ar,Domaine_Fr) VALUES (?,?,?)";
        
        String Query="insert into Domaine (Code_Domaine,Domaine_Ar,Domaine_Fr) VALUES ('"+Code_Domaine+"' , N'"+Domaine_Ar+"'"+", '"+Domaine_Fr+"');";
       cnx.Connecting();
        try {
            prstm=cnx.getConnect().prepareStatement(Query);
//            System.out.println("after preparestatement");
             int xx=prstm.executeUpdate();
             System.out.println("after prstm.executeUpdate()");          
             prstm.close();
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Error add domaine"+e.getMessage());
        }
        cnx.Deconnect();
    }   
    /******************************************************************/
     public void CreateCommuneTable(){
    
     ResultSet resTab=null;
    Statement stm=null;
     ArrayList<String> listTable=new ArrayList<> ();
    cnx.Connecting(); 
    String Query="CREATE TABLE Commune " +
    "( id_Commune int  IDENTITY(1,1) not NULL, " +
    "  Code_commune VARCHAR(10) ,  " +
    "  Commune_Ar NVARCHAR(255) , " +
    "  Commune_Fr VARCHAR(255) , " +
    "  ID_Wilaya int , "+                   
    "  PRIMARY KEY ( id_Commune ) , "+ 
    "  CONSTRAINT FK_ComuneWilaya FOREIGN KEY (ID_Wilaya)  REFERENCES Wilaya(ID_Wilaya) );";
     try {
            DatabaseMetaData dmd=cnx.getConnect().getMetaData();  //TO GET Name table of db
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
        
        if (listTable.contains("Commune")) {
            System.out.println("The Table Is Obtained ");
            System.out.println("Index Of Table Student Residets :"+listTable.indexOf("Commune"));
        }else{
            System.out.println("No Item Found ......!");
         try {
            stm=cnx.getConnect().createStatement();
           boolean xx=stm.execute(Query);
           
            System.out.println("the boolean xx :"+xx);
           
        } catch (SQLException ex) {
            System.err.println("Error Sql Create Table :"+ex.getMessage());
        }
            FillCommuneFromExcel();
         try {
                stm.close();
                cnx.Deconnect();
            } catch (SQLException  e) {
                e.printStackTrace();
            }
        
        }
    
    }
     /******************************************************************/
     public void FillCommuneFromExcel(){
     FileInputStream inputStream = null;
       try {
           String excelFilePath = "Communes.xlsx";
           inputStream = new FileInputStream(new File(excelFilePath));
           //FileInputStream fis = new FileInputStream(fis);   //obtaining bytes from the file  
//creating Workbook instance that refers to .xlsx file  
     XSSFWorkbook wb = new XSSFWorkbook(inputStream);   
     XSSFSheet sheet = wb.getSheetAt(0);      
     Iterator<Row> Itr=sheet.iterator();
  String WilayaFr=null;
  String Wilaya_ar=null;
  String Code_commune=null;
  String Commune_Ar=null;
  String Commune_Fr=null;
  
               while (Itr.hasNext()) {
               Row nextRow = Itr.next();
               if (nextRow.getRowNum()!=0) {
                   WilayaFr=nextRow.getCell(1).getStringCellValue();
               Wilaya_ar=nextRow.getCell(2).getStringCellValue();
               double valCode=nextRow.getCell(3).getNumericCellValue();
               int Cod_commNum =(int) valCode;
               Commune_Ar=nextRow.getCell(4).getStringCellValue();
               Commune_Fr=nextRow.getCell(5).getStringCellValue();
                   if (Cod_commNum<1000) {
                    Code_commune="0"+Cod_commNum;
                   }else Code_commune=""+Cod_commNum;
               System.out.println("WilayaFr :"+WilayaFr);
               System.out.println("Wilaya_ar :"+Wilaya_ar);
               System.out.println("Code_commune :"+Code_commune);
               System.out.println("Commune_Ar :"+Commune_Ar);
               System.out.println("Commune_Fr :"+Commune_Fr);
               int idWly= new Get_Info_DB().GetId_From_DB("ID_Wilaya", "Wilaya", "NameWilaya", Wilaya_ar);
                System.out.println("Row Num:"+nextRow.getRowNum()+" Code_commune:"+Code_commune+" WilayaFr:"+WilayaFr+" Wilaya_ar:"+Wilaya_ar+" Code_commune:"+Code_commune+" Commune_Ar:"+Commune_Ar+" Commune_Fr:"+Commune_Fr);
                 System.out.println("id wilaya:"+idWly);
                if (idWly>0 && idWly<49) {
                      InsertCommune(Code_commune, Commune_Ar, Commune_Fr, idWly,0); 
                   }else {
                       System.out.println("The Wilaya Not Considered is id:"+idWly+ " : "+WilayaFr);
                   //JOptionPane.showMessageDialog(null, "The Wilaya Not Considered is id:"+idWly+ " : "+WilayaFr);
                   }
               }
//               if (nextRow.getRowNum()==3) {}               
//               Iterator<Cell> itrCell=nextRow.cellIterator();
//               while (itrCell.hasNext()) {
//                   Cell nextCell = itrCell.next();
//                    System.out.println("Cell "+nextCell.getStringCellValue());
//                 }
               
           }
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
       } finally {
           try {
               inputStream.close();
           } catch (IOException ex) {
               Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
 }  
     /******************************************************************/
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
            java.util.logging.Logger.getLogger(Conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Conf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//              //  new Conf().setVisible(true);
//            }
//        });
new Conf(null).Verify_Col_Wilaya();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BranchAR;
    private javax.swing.JTextField BranchFr;
    private javax.swing.JButton BtnSavDirc;
    private javax.swing.JButton CanSvDirct;
    private javax.swing.JComboBox<String> CaseStudent;
    private javax.swing.JTextField CodDom;
    private javax.swing.JTextField CodFil;
    private javax.swing.JTextField CodeCom;
    private javax.swing.JLabel Code_ComLab;
    private javax.swing.JTextField CommNameAR;
    private javax.swing.JTextField CommNameFr;
    private javax.swing.JLabel DatDcsLab;
    private javax.swing.JFormattedTextField DateDcsDrct;
    private javax.swing.JTextField DcsnDrct;
    private javax.swing.JButton DelDrct;
    private javax.swing.JTextField Domaine;
    private javax.swing.JTextField DomaineAr;
    private javax.swing.JComboBox<String> DomaineCombo;
    private javax.swing.JComboBox<String> DomaineCombo1;
    private javax.swing.JRadioButton DrcDriectResicheck;
    private javax.swing.JRadioButton DrcResicheck;
    private javax.swing.JTextField FacultyAr;
    private javax.swing.JTextField FacultyFr;
    private javax.swing.JLabel LabBranch;
    private javax.swing.JLabel LabFaculty;
    private javax.swing.JLabel LabLevel;
    private javax.swing.JLabel LabLevel1;
    private javax.swing.JLabel LabNationality;
    private javax.swing.JTextField LastNmDrc;
    private javax.swing.JTable LevelTab;
    private javax.swing.JTextField LevlStdAR;
    private javax.swing.JTextField LevlStdFR;
    private javax.swing.JTextField NamDrct;
    private javax.swing.JComboBox<String> NameWilaya;
    private javax.swing.JTextField NationalityAr;
    private javax.swing.JTextField NationalityFr;
    private javax.swing.JButton NewDrct;
    private javax.swing.JLabel NumDcsLab;
    private javax.swing.JPanel PanBranch;
    private javax.swing.JPanel PanComANDWilaya;
    private javax.swing.JPanel PanConfiguration;
    private javax.swing.JPanel PanFaculty;
    private javax.swing.JPanel PanInfDrc;
    private javax.swing.JPanel PanLevelStudy;
    private javax.swing.JPanel PanNationality;
    private javax.swing.JLabel SaveBranch;
    private javax.swing.JLabel SaveFaculty;
    private javax.swing.JLabel SaveNaTio;
    private javax.swing.JLabel SaveNaTio1;
    private javax.swing.JLabel SaveNaTio3;
    private javax.swing.JTable TabBranch;
    private javax.swing.JTable TabFaculty;
    private javax.swing.JTable TabNationaliy;
    private javax.swing.JTable TableDirct;
    private javax.swing.JButton UpdtInDrc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lasNamLab;
    private javax.swing.JLabel nmDrctLab;
    // End of variables declaration//GEN-END:variables
}
