package residence;

import View.RoundedLineBorder;
import com.wia.api.Wia4j;
import com.wia.api.WiaOperationException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.awt.Frame.ICONIFIED;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MaskFormatter;

public class Home1 extends javax.swing.JFrame implements MouseListener, ActionListener {

    Student_Res student_Res;
    Student_Res student_ResRemplissage = new Student_Res();
    Account accountRemplissage = new Account();
    AddWilaya addwilyGUI;
    boolean etat_account = false;
    Resident_Gl resident_Gl = new Resident_Gl();
    Pavilion PavilionRemplissage = new Pavilion();
    Resident_Gl Resident_GlRemplissage = new Resident_Gl();
    MessageErrorControl messagerror;
    Controle ControleSaisi = new Controle();
    Professor_Res professor_resRemplissage = new Professor_Res();
    Professor_Res professor_res;
    ExternalStudent ExternalstudentRemplissage = new ExternalStudent();
    ExternalStudent externalstudent;
    Employer Employer_Remplissage = new Employer();
    Employer Employer_Resident;
    Guest guestRmpissage = new Guest();
//Megration megration=new Megration();
    UserService userserviceRemplissage = new UserService();
    int choose;
    JLabel TableMenuItem[];
    int PanResidentChoiceToSaisi = 0;
    int Gender;
    String Level;
    String PatternToTakeRoom;
    String Branche;
    private String PatternResident;
    String PaternToChangeCase;
    int IndiceSelected;
    ArrayList<JButton> listBtnPanPV = new ArrayList<>();
    String SituationFam;
    Guest guest;
    Room RoomRemplissage = new Room();
    File ImageResidentToSv = new File("D:\\Image_Signature\\imageRes.png");
    Get_Info_DB Fill_Data = new Get_Info_DB();
//Login  login;
    Ok1 ok;
    TakeRepast takeRepastRemplissage = new TakeRepast();
    TakeRepast takeRepast;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    Timer timer;
    Wia4j ScannerTool = new Wia4j();
    String NamFR = "";
    String PrénomFR = "";
    String NamFatheFr = "";
    String NamMatherFR = "";
    String LastMatherFR = "";
    String PlcFR = "";
    Get_Nev_Inf Get_Nev_Inf;
    Advanced_prp AdvPrp;

    RoundedLineBorder roundedLineBorder = new RoundedLineBorder(Color.BLACK, 1, 10, true);
    JTextField[] ListText;
    ButtonGroup GrpBtnMarriedStd, GrpBtnGenderStd, GrpBtnItemsUpd_Form;

    public Home1() {
        initComponents();
        this.ListText = new JTextField[]{txtNam_std, txtSurNam_std, txtPlcBirth_std, txtNam_Father, txtProfission_Std, txtNam_mother, LastNamMothARTxt, txtProfission_Moth, txtDairaStd, txtAddress_Std, TtxtBacYear, txtPlaceGetBac1, txtBacMoy1};
        HintTextField(ListText);
        //setBorderPanSaisieStd();
        AlignRightAllCombInsetResids();
        Gender = 1;
        SituationFam = RadBtnSingleStd.getText();
        Filling_Pavilon(panCnt);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        TableMenuItem = new JLabel[Pn_Menu.getComponentCount()];

        //Remplir_TableMenu();
        dR1 = (DefaultTableModel) TabResidentToConsult.getModel();
        dR2 = (DefaultTableModel) TabExtStdToConsult.getModel();
        dR3 = (DefaultTableModel) TabProfToConsult.getModel();
        dR4 = (DefaultTableModel) TabEmpToConsult.getModel();
        GrpBtnMarriedStd = new ButtonGroup();

        GrpBtnMarriedStd.add(RadBtnSingleStd);
        GrpBtnMarriedStd.add(RadBtnMarriedStd);

        GrpBtnGenderStd = new ButtonGroup();
        GrpBtnGenderStd.add(BtnRdMaleStd);
        GrpBtnGenderStd.add(BtnRdFemaleStd);

        timeLabel.setText(sdf.format(new Date(System.currentTimeMillis())));
        timer = new Timer(500, this);
        timer.setRepeats(true);
        timer.start();
        Gs = new Guest1();
        F = new Conf(this);
        R = new Restu();
        Fill_Data.DesignTable(TabResidentToConsult, 1, 30);
        Fill_Data.DesignTable(TabResidentToConsult, 2, 30);
        Fill_Data.GetNameTable();
        F.Verify_Col_Wilaya();
        F.Verify_Colums_ComWil();
        F.CreateCommuneTable();
        F.CreateDomaineTable();
        F.FillTableCommune_Combobox((String) WilayaList.getSelectedItem(), null, CombCommuneSlct, 1);
        //F.FillTableCommune_Combobox((String)WilayaList.getSelectedItem(),null,ComboxHome,1);
        AdvPrp = new Advanced_prp(F);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        firstYrs.setValue(year);

        //setBorderPanMenu();
        // setBorderPanSaisieStd();
        SetCountsOfStudentsISaisiPan();
        GoupRadioBtnComponents();
        darck_mode();
    }

    /**
     * * Darck Mode***
     */
    public void darck_mode() {
        PanCntMenu.setBackground(new Color(40, 44, 52));
        Pn_Menu.setBackground(new Color(40, 44, 52));
        jPanel1.setBackground(new Color(40, 44, 52));
        SecondCentrePan.setBackground(new Color(40, 44, 52));
        PanAllServiceStudent.setBackground(new Color(40, 44, 52));
        PanInfoCard.setBackground(new Color(40, 44, 52));
        UpdateStd.setBackground(new Color(40, 44, 52));
        PanTakeRoom.setBackground(new Color(40, 44, 52));
        PanMenuAllStudent1.setBackground(new Color(40, 44, 52));
        PanToChckPatern1.setBackground(new Color(40, 44, 52));
        PanAllResidentToConsult.setBackground(new Color(40, 44, 52));
        PanEXtrStdToConsult.setBackground(new Color(40, 44, 52));
        PanEmpToConsult.setBackground(new Color(40, 44, 52));
        jPanel8.setBackground(new Color(40, 44, 52));
        PanStdResident.setBackground(new Color(40, 44, 52));

        PanStdEXtern.setBackground(new Color(40, 44, 52));
        PnIUpdtusr.setBackground(new Color(40, 44, 52));
        Pnform2.setBackground(new Color(40, 44, 52));
        jPanel10.setBackground(new Color(40, 44, 52));

        //btns
        Menu_Update_info.setBackground(new Color(35, 40, 49));
        Menu_Residence_dossier.setBackground(new Color(35, 40, 49));
        Menu_Consult_Info.setBackground(new Color(35, 40, 49));
        Menu_Reports.setBackground(new Color(35, 40, 49));
        Menu_RoomService.setBackground(new Color(35, 40, 49));
        Menu_Config.setBackground(new Color(35, 40, 49));
        Btn_Annul_UserToUPDATE21.setBackground(new Color(35, 40, 49));
        jButton1.setBackground(new Color(35, 40, 49));
        jButton3.setBackground(new Color(35, 40, 49));

        jButton4.setBackground(new Color(35, 40, 49));
        jButton5.setBackground(new Color(35, 40, 49));
        jButton18.setBackground(new Color(35, 40, 49));
        jButton19.setBackground(new Color(35, 40, 49));
        jButton20.setBackground(new Color(35, 40, 49));
        jButton22.setBackground(new Color(35, 40, 49));
        jButton21.setBackground(new Color(35, 40, 49));
        jButton15.setBackground(new Color(35, 40, 49));
        jButton32.setBackground(new Color(35, 40, 49));
        jButton27.setBackground(new Color(35, 40, 49));
        jButton14.setBackground(new Color(35, 40, 49));
        //combox
        //CaseN1
        //CaseN2  
        //CaseN3
        //pavilion2
        //Branch4 Pavlion4  Pavlion5   LisLvlStd
        // CombExternNewOrNot
        // BranchStd_year

        //labels
        jLabel14.setBackground(new Color(40, 44, 52));
        jLabel32.setBackground(new Color(40, 44, 52));
        jLabel49.setBackground(new Color(40, 44, 52));
        jLabel5.setBackground(new Color(40, 44, 52));
        jLabel9.setBackground(new Color(40, 44, 52));
        jLabel16.setBackground(new Color(40, 44, 52));
        jLabel20.setBackground(new Color(40, 44, 52));
        jLabel15.setBackground(new Color(40, 44, 52));
        jLabel100.setBackground(new Color(40, 44, 52));
        LbInsPrnom2.setBackground(new Color(40, 44, 52));
        LbInsID2.setBackground(new Color(40, 44, 52));
        LbInsNom2.setBackground(new Color(40, 44, 52));
        LbInsPsw2.setBackground(new Color(40, 44, 52));
        Lb_Droit_User2.setBackground(new Color(40, 44, 52));
        LbInsConfrm2.setBackground(new Color(40, 44, 52));
        LabTexTypCount.setBackground(new Color(40, 44, 52));
        LabCountTabConsult.setBackground(new Color(40, 44, 52));
        jLabel28.setBackground(new Color(40, 44, 52));
        jLabel63.setBackground(new Color(40, 44, 52));
        jLabel34.setBackground(new Color(40, 44, 52));
        jLabel62.setBackground(new Color(40, 44, 52));
        jLabel66.setBackground(new Color(40, 44, 52));
        jLabel35.setBackground(new Color(40, 44, 52));
        jLabel37.setBackground(new Color(40, 44, 52));
        jLabel201.setBackground(new Color(40, 44, 52));
        jLabel202.setBackground(new Color(40, 44, 52));
        jLabel47.setBackground(new Color(40, 44, 52));
        jLabel56.setBackground(new Color(40, 44, 52));
        jLabel57.setBackground(new Color(40, 44, 52));
        jLabel58.setBackground(new Color(40, 44, 52));
        jLabel59.setBackground(new Color(40, 44, 52));

        //Separator4
        //tab
        //jScrollPane4  TabUserService TabResidentToConsult  TabProfToConsult  TabExtStdToConsult TabEmpToConsult jTable1 jSpinner1
        //textfield
        TxtInsNomusr2.setBackground(new Color(91, 101, 124));
        TxtInsPrenomUsr.setBackground(new Color(91, 101, 124));
        TxtInsIdentifiantUsr.setBackground(new Color(91, 101, 124));
        TxtInsNomusr.setBackground(new Color(91, 101, 124));
        TxtInsPswUsr.setBackground(new Color(91, 101, 124));
        TxtInsConPswUsr.setBackground(new Color(91, 101, 124));
        txtBrchToPrint.setBackground(new Color(91, 101, 124));
        jTextField1.setBackground(new Color(91, 101, 124));

        //checkbox
        CheckSrvRoom.setBackground(new Color(91, 101, 124));
        ChecAccessUsr.setBackground(new Color(91, 101, 124));
        CheckRestarUsr.setBackground(new Color(91, 101, 124));
        CheckAdministrationusr.setBackground(new Color(91, 101, 124));
        CheckInscrRessevSrv.setBackground(new Color(91, 101, 124));
        check_Std2_x.setBackground(new Color(91, 101, 124));
        check_StdExt2_x.setBackground(new Color(91, 101, 124));
        check_Prof2_x.setBackground(new Color(91, 101, 124));
        check_Prof2_x.setBackground(new Color(91, 101, 124));

    }

    /**
     * *Close Dark Mode **
     */
    public void FillDataOfWilayaAndCommuneInHome() {

        F.FillTableCommune_Combobox((String) WilayaList.getSelectedItem(), null, CombCommuneSlct, 1);
        Fill_Data.Filling(WilayaList, "Wilaya", "NameWilaya", 1);
        WilayaList.removeItem("/");
    }

    public void SetCountsOfStudentsISaisiPan() {
        buttonView12.setText(Fill_Data.GetCount("Resident_Gl", "ID_Case_Resident", 1) + "\n " + "الطلبة الجدد");
        //buttonView13.setText(Fill_Data.GetCount("الطلبة المسجلين \n"+"Resident_Gl","ID_Case_Resident" ,1)+"");
    }

    public void HintTextField(JTextField[] TabTextField) {

        for (JTextField TextField : TabTextField) {
            TextField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    switch (TextField.getText()) {
                        case "الاسم":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            TextField.getInputContext().selectInputMethod(new Locale("ar", "DZ"));
                            break;

//                    case "Prenom" :
//                    TextField.setText("");
//                    TextField.setForeground(Color.black);
//                    TextField.getInputContext().selectInputMethod(new Locale("fr", "FR"));
//                    break;
                        case "الـلـــقـــب":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            TextField.getInputContext().selectInputMethod(new Locale("ar", "DZ"));
                            break;

//                    case "Nom" :
//                    TextField.setText("");
//                    TextField.setForeground(Color.black);
//                    TextField.getInputContext().selectInputMethod(new Locale("fr", "FR"));
//                    break;
                        case "مــكـان الـميــلاد":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            TextField.getInputContext().selectInputMethod(new Locale("ar", "DZ"));
                            break;

//                    case "lieu de naissance" :
//                    TextField.setText("");
//                    TextField.setForeground(Color.black);
//                    TextField.getInputContext().selectInputMethod(new Locale("fr", "FR"));
//                    break;
                        case "اسم الأب":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            TextField.getInputContext().selectInputMethod(new Locale("ar", "DZ"));
                            break;
                        //Prenem de pere
//                    case "Prenem de pere" :
//                    TextField.setText("");
//                    TextField.setForeground(Color.black);
//                    TextField.getInputContext().selectInputMethod(new Locale("fr", "FR"));
//                    break;

                        case "مهنة الاب":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            TextField.getInputContext().selectInputMethod(new Locale("ar", "DZ"));
                            break;

                        case "اسم  الام":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            TextField.getInputContext().selectInputMethod(new Locale("ar", "DZ"));
                            break;
                        case "لقب  الام":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            TextField.getInputContext().selectInputMethod(new Locale("ar", "DZ"));
                            break;
                        case "مهنة الام":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            TextField.getInputContext().selectInputMethod(new Locale("ar", "DZ"));
                            break;
                        case "العـنوان":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            TextField.getInputContext().selectInputMethod(new Locale("ar", "DZ"));
                            break;
                        case "الدائرة":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            TextField.getInputContext().selectInputMethod(new Locale("ar", "DZ"));
                            break;

                        case "مــكـان الــحــصـول عــلــي الــبــكـالـــوريـــا":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            TextField.getInputContext().selectInputMethod(new Locale("ar", "DZ"));
                            break;
                        case "2018":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            break;
                        case "00.00":
                            TextField.setText("");
                            TextField.setForeground(Color.black);
                            break;

                    }

                }

                @Override
                public void focusLost(FocusEvent e) {

                    if (TextField.equals(txtNam_std) && TextField.getText().equals("")) {
                        TextField.setText("الاسم");
                        TextField.setForeground(new Color(204, 204, 204));

                    } else if (TextField.equals(txtSurNam_std) && TextField.getText().equals("")) {
                        TextField.setText("الـلـــقـــب");
                        TextField.setForeground(new Color(204, 204, 204));

                    } else if (TextField.equals(txtPlcBirth_std) && TextField.getText().equals("")) {
                        TextField.setText("مــكـان الـميــلاد");
                        TextField.setForeground(new Color(204, 204, 204));

                    } else if (TextField.equals(txtNam_Father) && TextField.getText().equals("")) {
                        TextField.setText("اسم الأب");
                        TextField.setForeground(new Color(204, 204, 204));

                    } else if (TextField.equals(txtProfission_Std) && TextField.getText().equals("")) {
                        TextField.setText("مهنة الاب");
                        TextField.setForeground(new Color(204, 204, 204));

                    } else if (TextField.equals(txtNam_mother) && TextField.getText().equals("")) {
                        TextField.setText("اسم  الام");
                        TextField.setForeground(new Color(204, 204, 204));

                    } else if (TextField.equals(LastNamMothARTxt) && TextField.getText().equals("")) {
                        TextField.setText("لقب  الام");
                        TextField.setForeground(new Color(204, 204, 204));

                    } else if (TextField.equals(txtProfission_Moth) && TextField.getText().equals("")) {
                        TextField.setText("مهنة الام");
                        TextField.setForeground(new Color(204, 204, 204));

                    } else if (TextField.equals(txtDairaStd) && TextField.getText().equals("")) {
                        TextField.setText("الدائرة");
                        TextField.setForeground(new Color(204, 204, 204));
                    } else if (TextField.equals(txtAddress_Std) && TextField.getText().equals("")) {
                        TextField.setText("العـنوان");
                        TextField.setForeground(new Color(204, 204, 204));
                    } else if (TextField.equals(TtxtBacYear) && TextField.getText().equals("")) {
                        TextField.setText("2018");
                        TextField.setForeground(new Color(204, 204, 204));
                    } else if (TextField.equals(txtBacMoy1) && TextField.getText().equals("")) {
                        TextField.setText("00.00");
                        TextField.setForeground(new Color(204, 204, 204));
                    } else if (TextField.equals(txtPlaceGetBac1) && TextField.getText().equals("")) {
                        TextField.setText("مــكـان الــحــصـول عــلــي الــبــكـالـــوريـــا");
                        TextField.setForeground(new Color(204, 204, 204));
                    }

                }
            });
        }

    }

//    public void setBorderPanMenu() {
//        //ItemMenLogout.setBorder(roundedLineBorder);
//    }
    public void setBorderPanSaisieStd() {

        txt_NumInsc.setBorder(roundedLineBorder);
        txtNam_std.setBorder(roundedLineBorder);
        txtSurNam_std.setBorder(roundedLineBorder);
        DatBirth_std.setBorder(roundedLineBorder);
        txtPlcBirth_std.setBorder(roundedLineBorder);

        txtNam_Father.setBorder(roundedLineBorder);
        txtProfission_Std.setBorder(roundedLineBorder);
        txtNam_mother.setBorder(roundedLineBorder);

        LastNamMothARTxt.setBorder(roundedLineBorder);
        txtProfission_Moth.setBorder(roundedLineBorder);
        txtDairaStd.setBorder(roundedLineBorder);
        txtAddress_Std.setBorder(roundedLineBorder);
        txtPlaceGetBac1.setBorder(roundedLineBorder);

    }

    public void AlignRightAllCombInsetResids() {

        ((JLabel) WilayaList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) National_list.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) txtBranch_std.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
//        ((JLabel) txtBranch_stdExtr.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
//        ((JLabel) LevelStd_StdExtrn.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) LevelStd.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) txtDepa_Std.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        ((JLabel) WilayaListUpdtForm.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) National_listStdUpdtForm.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) Branch_stdUpdtForm.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) LevelStdUpdtForm.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) Depa_StdUpdtForm.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) Profession.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        ((JLabel) CaseResident.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) CaseResidentProfUpdtForm.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) CaseResident2.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) CaseResident3.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) Branch_stdExternStdUpdtForm.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) LevelStdExtUpdtForm.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) ProfessionToUpdate.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

    }
    ArrayList<String> ListNmStRs;

    /* public void GetNameStudentInRoom(JPanel l){
        ListNmStRs=new  ArrayList<>();
    //int SlcRow=TabRoom.getSelectedRow();
    int SlcRow=RowR;
    String NameRoom=(String)TabRoom.getValueAt(SlcRow, 2);
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>((DefaultTableModel)TabStdInternTotakeRoom.getModel());
        TabStdInternTotakeRoom.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(NameRoom));
        int NmbRowFiltred=TabStdInternTotakeRoom.getRowCount();
       //  JOptionPane.showMessageDialog(null, "The nbr Row filtred is"+NmbRowFiltred);
        int i=0;
        while(i<NmbRowFiltred){
            String NmPrs=(String)TabStdInternTotakeRoom.getValueAt(i, 5);
          //  JOptionPane.showMessageDialog(null, "The Name IS"+NmPrs);
            ListNmStRs.add(NmPrs);
           /* JLabel btnPv1=new JLabel();
                  btnPv1.setName("A");
                  
                  btnPv1.setText(NmPrs);
                    btnPv1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
                    btnPv1.setForeground(new java.awt.Color(255, 255, 255));
                   // btnPv1.setContentAreaFilled(false);
                    btnPv1.setBackground(new Color(0,173,153));
                   // btnPv1.setPreferredSize(new Dimension(55, 25));
                    btnPv1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnPv1.setOpaque(true);
            panCnt1.add(btnPv1);*/
    //  JOptionPane.showMessageDialog(null, "The Label is Added :");
    /* i++;
        } 
    
   // tr=new TableRowSorter<DefaultTableModel>((DefaultTableModel)TabStdInternTotakeRoom.getModel());
      //  TabStdInternTotakeRoom.setRowSorter(tr);
     //   tr.setRowFilter(RowFilter.regexFilter(""));
     tr=null;
     
    }*/
    public void Filling_Name(JPanel pan, int Row) {
        String Room = (String) TabRoom.getValueAt(Row, 2);
        String NameStd = "";
        int CountCmp = pan.getComponentCount();
        int i = 0;
        student_ResRemplissage.FilterResident(Room, TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());
        if (RoomSelctd != Row) {
            pan.removeAll();
            pan.revalidate();
            pan.repaint();
            CountCmp = pan.getComponentCount();
            while (i < TabStdInternTotakeRoom.getRowCount()) {
                NameStd = (String) TabStdInternTotakeRoom.getValueAt(i, 6) + " " + (String) TabStdInternTotakeRoom.getValueAt(i, 5) + "   ";
                JLabel btnPv1 = new JLabel();
                btnPv1.setText(NameStd);
                btnPv1.setFont(new java.awt.Font("Times New Roman", 1, 15));
                btnPv1.setForeground(new java.awt.Color(0, 0, 0));
                btnPv1.setBackground(new Color(255, 255, 215));
                btnPv1.setOpaque(true);
                pan.add(btnPv1);
                i++;
            }
        }
        RoomSelctd = Row;
        student_ResRemplissage.FilterResident(Word, TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());
    }
    int RoomSelctd = -1;

    public void MakeActionListenerBtn() {
        for (JButton btnPv : listBtnPanPV) {
            btnPv.addMouseListener(this);
        }
    }

    public void remplirlistfromPan() {
        int i = 0;
        while (i < panCnt.getComponentCount()) {
            if (panCnt.getComponent(i) instanceof JButton) {
                listBtnPanPV.add((JButton) panCnt.getComponent(i));
            }
            i++;
        }
    }

    public void RemoveActionListner() {
        for (JButton btnPv : listBtnPanPV) {
            btnPv.removeMouseListener(this);
        }

    }

    public void Filling_Pavilon(JPanel pan) {
        Statement stm = null;
        ResultSet res = null;

        String Query = "SELECT Pavilion_Name   FROM Pavilion";

        String NamePavilion = "";

        ConnectionDB cnx = new ConnectionDB();
        cnx.Connecting();
        try {
            stm = cnx.getConnect().createStatement();
            res = stm.executeQuery(Query);
            while (res.next()) {

                NamePavilion = res.getString("Pavilion_Name");
                System.out.println("residence.Pavilion.DisplayAllPavilionInPan()" + NamePavilion);
                // btnPv1=new JButton();
                JButton btnPv1 = new JButton();
                btnPv1.setName(NamePavilion);

                btnPv1.setText(NamePavilion);
                btnPv1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
                btnPv1.setForeground(new java.awt.Color(255, 255, 255));
                btnPv1.setContentAreaFilled(false);
                btnPv1.setBackground(new Color(0, 173, 153));
                btnPv1.setPreferredSize(new Dimension(55, 25));
                btnPv1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnPv1.setOpaque(true);
                //  btnPv1.addActionListener(this);
                pan.add(btnPv1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in sql " + e.getMessage());
        }
        try {
            stm.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cnx.Deconnect();
    }

    public int[] remplir_Inf_usr1(JCheckBox CheckachatUsr, JCheckBox CheckventeUsr, JCheckBox CheckproductionUsr,
            JCheckBox CheckPayementUsr, JCheckBox CheckAdministrationusr
    ) { //This function to create New User SERVICES Not ImportanaNow
        int tab1[] = {
            GetValueService(CheckachatUsr),
            GetValueService(CheckventeUsr),
            GetValueService(CheckproductionUsr),
            GetValueService(CheckPayementUsr),
            //GetValueService(CheckStatiqueUsr),
            GetValueService(CheckAdministrationusr)
        };

        return tab1;
    }

    /* public void GetMenuUser(int tabs[]) {
        int NumCmp = Pn_Menu.getComponentCount();
        Component TabCmp[] = Pn_Menu.getComponents();

        TabCmp[0] = panInscResev1;
        TabCmp[1] = PanSrvRoom;
        TabCmp[2] = panUsers1;
        TabCmp[3] = panRestaurN;
        TabCmp[4] = panAccess;
        TabCmp[5] = PanConfig;
        TabCmp[6] = pan12;

        if (tabs[4] == 0) {
            // if (tabs[2]==1    ) {  //For panel of Restauration 
            if (tabs[2] == 1 || tabs[3] == 1) {

                if (tabs[2] == 1) {
                    int i = 0;
                    while (i < (TabCmp.length - 1)) {  //for las panel to exit 
                        if (i != 3) {
                            TabCmp[i].setVisible(false);
                        }
                        i++;
                    }

                } else {
                    if (tabs[3] == 1) {      //For Panel of Access
                        int i = 0;
                        while (i < (TabCmp.length - 1)) {
                            if (i != 4) {
                                TabCmp[i].setVisible(false);
                            }
                            i++;
                        }
                    }
                }
            } else {

                if (tabs[0] == 1) {   //for Panel Inscrp
                    TabCmp[0].setVisible(true);
                } else {
                    TabCmp[0].setVisible(false);
                }

                if (tabs[1] == 1) {     //for Panel I
                    TabCmp[1].setVisible(true);
                } else {
                    TabCmp[1].setVisible(false);
                }
            }
        }
        Pn_Menu.repaint();
        Pn_Menu.revalidate();
    }
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        PanCntMenu = new javax.swing.JPanel();
        Pn_Menu = new javax.swing.JPanel();
        Menu_Update_info = new View.ButtonView();
        Menu_Residence_dossier = new View.ButtonView();
        Menu_Consult_Info = new View.ButtonView();
        Menu_Reports = new View.ButtonView();
        Menu_RoomService = new View.ButtonView();
        Menu_Config = new View.ButtonView();
        buttonView13 = new View.ButtonView();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        SecondCentrePan = new javax.swing.JPanel();
        PanAllServiceStudent = new javax.swing.JPanel();
        PanInfoCard = new javax.swing.JPanel();
        jPanel4_ = new javax.swing.JPanel();
        PanPrfAfterSaisStd = new javax.swing.JPanel();
        LabNameRestToPrint = new javax.swing.JLabel();
        LabBranchStd = new javax.swing.JLabel();
        LabDateBirth_Plc = new javax.swing.JLabel();
        LabPrfBranch_Std2 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        LabPrfNumCard_StdRes = new javax.swing.JLabel();
        LabLastNameRestToPrint = new javax.swing.JLabel();
        LabPlaceBirth_Res = new javax.swing.JLabel();
        NumOrder = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jLabel98 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        BtnNext = new javax.swing.JButton();
        CLabImg = new ViewMyPrj.CLabel();
        LabPrfResd_Std3 = new javax.swing.JLabel();
        PrinRecWtoutSh = new javax.swing.JButton();
        jPanel5_ = new javax.swing.JPanel();
        timeLabel = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        BtnSaveStd = new View.ButtonView();
        buttonView12 = new View.ButtonView();
        jPanel2_ = new javax.swing.JPanel();
        check_Std = new javax.swing.JCheckBox();
        check_StdExt = new javax.swing.JCheckBox();
        check_Emp = new javax.swing.JCheckBox();
        check_Prof = new javax.swing.JCheckBox();
        txt_NumInsc = new javax.swing.JTextField();
        buttonView10 = new View.ButtonView();
        buttonView11 = new View.ButtonView();
        jLabel18 = new javax.swing.JLabel();
        Pan_All_PansSais_ = new javax.swing.JPanel();
        panSaisiStd_ = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNam_std = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        MaskFormatter formatter3 = new TimeFormatter();
        DatBirth_std = new javax.swing.JFormattedTextField(formatter3);
        jLabel71 = new javax.swing.JLabel();
        txtSurNam_std = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        txtPlcBirth_std = new javax.swing.JTextField();
        txtNam_Father = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txtProfission_Std = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        txtNam_mother = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        LastNamMothARTxt = new javax.swing.JTextField();
        txtProfission_Moth = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        BtnShowRooms = new javax.swing.JLabel();
        WilayaList = new javax.swing.JComboBox<>();
        jLabel80 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        CombCommuneSlct = new javax.swing.JComboBox<>();
        jLabel81 = new javax.swing.JLabel();
        txtDairaStd = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        txtAddress_Std = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        National_list = new javax.swing.JComboBox<>();
        PavillonPanInf = new javax.swing.JComboBox<>();
        jLabel67 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        RomPvinPanInf = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNam_std_FR = new javax.swing.JTextField();
        txtSurNam_std_FR = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        LastNamMothARTxt_FR = new javax.swing.JTextField();
        txtNam_Father_FR = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        txtPlcBirth_std_FR = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        txtNam_mother_FR = new javax.swing.JTextField();
        jLabel182 = new javax.swing.JLabel();
        TtxtBacYear = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        txtBacMoy1 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        txtPlaceGetBac1 = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        DatInscrpInUniv = new javax.swing.JFormattedTextField();
        LevelStd = new javax.swing.JComboBox<>();
        txtDepa_Std = new javax.swing.JComboBox<>();
        txtBranch_std = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        LabelShowNationalite = new javax.swing.JLabel();
        RadBtnMarriedStd = new javax.swing.JRadioButton();
        RadBtnSingleStd = new javax.swing.JRadioButton();
        BtnRdFemaleStd = new javax.swing.JRadioButton();
        BtnRdMaleStd = new javax.swing.JRadioButton();
        jLabel70 = new javax.swing.JLabel();
        firstYrs = new javax.swing.JSpinner();
        jLabel95 = new javax.swing.JLabel();
        Annet1 = new javax.swing.JTextField();
        PanSaisiEmp_ = new javax.swing.JPanel();
        jLabel174 = new javax.swing.JLabel();
        txtSurNam_Empl = new javax.swing.JTextField();
        txtNam_EmplInsr = new javax.swing.JTextField();
        txtPlcBirth_Emply = new javax.swing.JTextField();
        DatBirth_Empl = new javax.swing.JFormattedTextField();
        Profession = new javax.swing.JComboBox<>();
        Gdr_Emp_Mal1 = new javax.swing.JCheckBox();
        Gdr_Emp_femal1 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jTextField2 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        UpdateStd = new javax.swing.JPanel();
        PanAllUpdateResident = new javax.swing.JPanel();
        InfoStdToUpdate_1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Img_StdUpdate = new javax.swing.JLabel();
        NumCardToUpdt = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        txtAddress_StdUpdtForm = new javax.swing.JTextField();
        txt_NumInscUpdtForm = new javax.swing.JTextField();
        CaseResident = new javax.swing.JComboBox<>();
        txtSurNam_std5 = new javax.swing.JTextField();
        PlaceBirthFrStdUpdForm = new javax.swing.JTextField();
        LastName_ResidentFrUp = new javax.swing.JTextField();
        txtPlcBirth_stdUpdtForm = new javax.swing.JTextField();
        Name_ResidentFrUp = new javax.swing.JTextField();
        DatBirth_stdUpdtForm = new javax.swing.JFormattedTextField();
        txtNam_std5 = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtNam_FatherStdUpdtForm = new javax.swing.JTextField();
        txtProfissionfath_StdUpdtForm = new javax.swing.JTextField();
        Name_FatherFrUp = new javax.swing.JTextField();
        LastName_MotheFrUp = new javax.swing.JTextField();
        NamMrArStdUpdtForm = new javax.swing.JTextField();
        txtDairaStdUpdtForm = new javax.swing.JTextField();
        txtCommuneStdUpdtForm = new javax.swing.JTextField();
        WilayaListUpdtForm = new javax.swing.JComboBox<>();
        National_listStdUpdtForm = new javax.swing.JComboBox<>();
        checkFemalStdUpdtForm = new javax.swing.JCheckBox();
        CheckMaleStdUpdtForm = new javax.swing.JCheckBox();
        CombCommuneUpdtForm = new javax.swing.JComboBox<>();
        jLabel146 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        CombRomInUpdtForm = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        LstPvlinUpdtForm = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        txtNam_MotherStdUpdtForm = new javax.swing.JTextField();
        txtProfission_MothStdUpdtForm = new javax.swing.JTextField();
        Name_MotherFrUp = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        Std_MariedStdUpdtForm_x = new javax.swing.JRadioButton();
        Sti_SingleStdUpdtForm_x = new javax.swing.JRadioButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel151 = new javax.swing.JLabel();
        Branch_stdUpdtForm = new javax.swing.JComboBox<>();
        jLabel105 = new javax.swing.JLabel();
        LevelStdUpdtForm = new javax.swing.JComboBox<>();
        Depa_StdUpdtForm = new javax.swing.JComboBox<>();
        jLabel154 = new javax.swing.JLabel();
        txtPlaceGetBacStdUpdtForm = new javax.swing.JTextField();
        txtBacMoy2 = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        TtxtBacYearStdUpdtForm = new javax.swing.JTextField();
        PanProfToUpdate = new javax.swing.JPanel();
        MessgControl5 = new javax.swing.JLabel();
        panSaisiInfoProf = new javax.swing.JPanel();
        jLabel162 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        txtSurNam_Prof1 = new javax.swing.JTextField();
        jLabel166 = new javax.swing.JLabel();
        txtNam_Prof1 = new javax.swing.JTextField();
        ftxtPlcBirth_Prof = new javax.swing.JTextField();
        DatBirth_Pro = new javax.swing.JFormattedTextField();
        txtProfession_Prof2 = new javax.swing.JTextField();
        jLabel167 = new javax.swing.JLabel();
        Gdr_Prf_Malp = new javax.swing.JCheckBox();
        Gdr_Prf_femalp = new javax.swing.JCheckBox();
        jPanel27 = new javax.swing.JPanel();
        NumCardToUpdt2 = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        CaseResidentProfUpdtForm = new javax.swing.JComboBox<>();
        jLabel123 = new javax.swing.JLabel();
        Img_Std1 = new javax.swing.JLabel();
        Img_profupdate = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        PanStdExtToUpdate = new javax.swing.JPanel();
        Img_StdUpdate1 = new javax.swing.JLabel();
        PanSaisiInfoStdExt1 = new javax.swing.JPanel();
        jLabel192 = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        txtSurNam_StdExt1 = new javax.swing.JTextField();
        jLabel195 = new javax.swing.JLabel();
        txtNam_StdExt1 = new javax.swing.JTextField();
        txtPlcBirth_StdExt1 = new javax.swing.JTextField();
        DatBirth_StdExt1 = new javax.swing.JFormattedTextField();
        jLabel196 = new javax.swing.JLabel();
        Branch_stdExternStdUpdtForm = new javax.swing.JComboBox<>();
        jLabel197 = new javax.swing.JLabel();
        LevelStdExtUpdtForm = new javax.swing.JComboBox<>();
        jLabel198 = new javax.swing.JLabel();
        txt_NumInscSdExt1 = new javax.swing.JTextField();
        jLabel199 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        NumCardToUpdt3 = new javax.swing.JTextField();
        jLabel118 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        CaseResident2 = new javax.swing.JComboBox<>();
        Gdr_Prf_Malp1 = new javax.swing.JCheckBox();
        Gdr_Prf_femalp1 = new javax.swing.JCheckBox();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        PanSaisiEmp1 = new javax.swing.JPanel();
        PanSaisInfoEmp1 = new javax.swing.JPanel();
        jLabel184 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        txtSurNam_Prof7 = new javax.swing.JTextField();
        jLabel188 = new javax.swing.JLabel();
        txtNam_Prof7 = new javax.swing.JTextField();
        txtPlcBirth_Prof7 = new javax.swing.JTextField();
        DatBirth_Prof7 = new javax.swing.JFormattedTextField();
        jLabel189 = new javax.swing.JLabel();
        jLabel190 = new javax.swing.JLabel();
        ProfessionToUpdate = new javax.swing.JComboBox<>();
        jPanel31 = new javax.swing.JPanel();
        NumCardToUpdt4 = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        Gdr_Emp_femalE = new javax.swing.JCheckBox();
        Gdr_Emp_MalE = new javax.swing.JCheckBox();
        jLabel125 = new javax.swing.JLabel();
        CaseResident3 = new javax.swing.JComboBox<>();
        Img_EmpUpdate = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TabResident = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;

            }

        };
        CountTabStdIn = new javax.swing.JLabel();
        LabNamesForRes = new javax.swing.JLabel();
        btnUpdRes = new javax.swing.JButton();
        BtnCancel = new javax.swing.JButton();
        Search_Field_upd_std = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        AncianRoom = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        BtnSavUpdt = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel106 = new javax.swing.JLabel();
        PanToChckPatern = new javax.swing.JPanel();
        check_Std1_x = new javax.swing.JRadioButton();
        check_Prof1_x = new javax.swing.JRadioButton();
        check_StdExt1_x = new javax.swing.JRadioButton();
        check_Emp1_x = new javax.swing.JRadioButton();
        PanTakeRoom = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        LabNam = new javax.swing.JLabel();
        PlcBirth = new javax.swing.JLabel();
        NumCard = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        LabSurNam = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDatBirth = new javax.swing.JLabel();
        NameRoom = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        LabIDResident = new javax.swing.JLabel();
        panCnt = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabStdInternTotakeRoom = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;

            }
        };
        jTextField3 = new javax.swing.JTextField();
        check_Prof_TakeRom = new javax.swing.JCheckBox();
        check_Std_TakeRom = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        NameRoomr = new javax.swing.JLabel();
        LabIDRoomN = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabRoom = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;

            }
        };
        ChangeRoom = new javax.swing.JButton();
        BtnSaveStd2 = new javax.swing.JButton();
        RserveRoom = new javax.swing.JButton();
        SaveReservRoom = new javax.swing.JButton();
        panCnt1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        IDRoomA = new javax.swing.JLabel();
        paterntxt = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lstPav = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        PanMenuAllStudent1 = new javax.swing.JPanel();
        PanToChckPatern1 = new javax.swing.JPanel();
        check_Std2_x = new javax.swing.JRadioButton();
        check_StdExt2_x = new javax.swing.JRadioButton();
        check_Prof2_x = new javax.swing.JRadioButton();
        check_Emp2_x = new javax.swing.JRadioButton();
        PanAllResidentToConsult = new javax.swing.JPanel();
        panResidentToConsult = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        TabResidentToConsult = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;

            }
            @Override
            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                Object value = getModel().getValueAt(rowIndex,columnIndex);

                if(columnIndex == 0){

                    if(value.equals("ســــاكن"))
                    {

                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }
                    if(value.equals("مسجل فقط")){
                        // if date  equal current date
                        componenet.setBackground(new Color(0,204,255));
                        componenet.setForeground(Color.BLACK);
                    }

                    if(value.equals("مجــــدد")){
                        // if date  equal current date
                        componenet.setBackground(Color.YELLOW);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(value.equals("غيـر مجــــدد")){
                        // if date  equal current date
                        componenet.setBackground(Color.ORANGE);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(value.equals("متحـــــــول")){
                        // if date  equal current date
                        componenet.setBackground(Color.cyan);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(value.equals("منهـــــي")){
                        // if date  equal current date
                        componenet.setBackground(Color.RED);
                        componenet.setForeground(Color.WHITE);
                    }

                }
                /*
                else {

                    componenet.setBackground(Color.WHITE);
                    componenet.setForeground(Color.BLACK);
                }
                */
                return componenet;
            }

        };
        PanProfToConsult = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TabProfToConsult = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                Object value = getModel().getValueAt(rowIndex,columnIndex);

                if(columnIndex == 0){

                    if(value.equals("ســــاكن"))
                    {

                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }
                    if(value.equals("مسجل فقط")){
                        // if date  equal current date
                        componenet.setBackground(new Color(0,204,255));
                        componenet.setForeground(Color.BLACK);
                    }

                    if(value.equals("مجــــدد")){
                        // if date  equal current date
                        componenet.setBackground(Color.YELLOW);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(value.equals("غيـر مجــــدد")){
                        // if date  equal current date
                        componenet.setBackground(Color.ORANGE);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(value.equals("متحـــــــول")){
                        // if date  equal current date
                        componenet.setBackground(Color.cyan);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(value.equals("منهـــــي")){
                        // if date  equal current date
                        componenet.setBackground(Color.RED);
                        componenet.setForeground(Color.WHITE);
                    }

                }
                /*
                else {

                    componenet.setBackground(Color.WHITE);
                    componenet.setForeground(Color.BLACK);
                }
                */
                return componenet;
            }
        };
        PanEXtrStdToConsult = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        TabExtStdToConsult = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;

            }
        };
        PanEmpToConsult = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        TabEmpToConsult = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;

            }};
            LabTexTypCount = new javax.swing.JLabel();
            LabCountTabConsult = new javax.swing.JLabel();
            jPanel8 = new javax.swing.JPanel();
            PanStdResident = new javax.swing.JPanel();
            txtBrchToPrint = new javax.swing.JComboBox<>();
            jLabel28 = new javax.swing.JLabel();
            jButton18 = new javax.swing.JButton();
            CaseN1 = new javax.swing.JComboBox<>();
            jLabel34 = new javax.swing.JLabel();
            jLabel62 = new javax.swing.JLabel();
            CaseN2 = new javax.swing.JComboBox<>();
            jButton19 = new javax.swing.JButton();
            CaseN3 = new javax.swing.JComboBox<>();
            jLabel63 = new javax.swing.JLabel();
            pavilion2 = new javax.swing.JComboBox<>();
            jLabel66 = new javax.swing.JLabel();
            jButton20 = new javax.swing.JButton();
            Branch4 = new javax.swing.JComboBox<>();
            jButton22 = new javax.swing.JButton();
            jButton21 = new javax.swing.JButton();
            Pavlion4 = new javax.swing.JComboBox<>();
            jLabel35 = new javax.swing.JLabel();
            Pavlion5 = new javax.swing.JComboBox<>();
            jLabel37 = new javax.swing.JLabel();
            jButton15 = new javax.swing.JButton();
            LisLvlStd = new javax.swing.JComboBox<>();
            jButton32 = new javax.swing.JButton();
            PanStdEXtern = new javax.swing.JPanel();
            jLabel201 = new javax.swing.JLabel();
            jLabel202 = new javax.swing.JLabel();
            jLabel47 = new javax.swing.JLabel();
            jScrollPane3 = new javax.swing.JScrollPane();
            jTable1 = new javax.swing.JTable();
            jTextField1 = new javax.swing.JTextField();
            jLabel56 = new javax.swing.JLabel();
            jLabel57 = new javax.swing.JLabel();
            jLabel58 = new javax.swing.JLabel();
            jLabel59 = new javax.swing.JLabel();
            jSpinner1 = new javax.swing.JSpinner();
            jButton27 = new javax.swing.JButton();
            CombExternNewOrNot = new javax.swing.JComboBox<>();
            jButton14 = new javax.swing.JButton();
            BranchStd_year = new javax.swing.JComboBox<>();
            TxtType = new javax.swing.JComboBox<>();
            jLabel204 = new javax.swing.JLabel();
            jLabel206 = new javax.swing.JLabel();
            jButton6 = new javax.swing.JButton();
            jButton7 = new javax.swing.JButton();
            jButton8 = new javax.swing.JButton();
            jButton10 = new javax.swing.JButton();
            jButton12 = new javax.swing.JButton();
            jButton17 = new javax.swing.JButton();
            jFormattedTextField1 = new javax.swing.JFormattedTextField();
            jLabel17 = new javax.swing.JLabel();
            jButton11 = new javax.swing.JButton();
            jButton23 = new javax.swing.JButton();
            jButton25 = new javax.swing.JButton();
            jLabel205 = new javax.swing.JLabel();
            PanChangeCase = new javax.swing.JPanel();
            jLabel207 = new javax.swing.JLabel();
            jLabel208 = new javax.swing.JLabel();
            jLabel209 = new javax.swing.JLabel();
            NamToChangCase = new javax.swing.JLabel();
            SurNamToChangCase = new javax.swing.JLabel();
            CaseA = new javax.swing.JLabel();
            CaseN = new javax.swing.JComboBox<>();
            jLabel210 = new javax.swing.JLabel();
            NumCardTochangCase = new javax.swing.JLabel();
            jLabel211 = new javax.swing.JLabel();
            jLabel212 = new javax.swing.JLabel();
            jLabel213 = new javax.swing.JLabel();
            jLabel31 = new javax.swing.JLabel();
            jPanel3 = new javax.swing.JPanel();
            jLabel21 = new javax.swing.JLabel();
            jLabel22 = new javax.swing.JLabel();
            jLabel23 = new javax.swing.JLabel();
            jLabel25 = new javax.swing.JLabel();
            jLabel26 = new javax.swing.JLabel();
            jLabel48 = new javax.swing.JLabel();
            jLabel52 = new javax.swing.JLabel();
            SaveAllChangeCaseRes = new javax.swing.JLabel();
            LabChangeCaseAll = new javax.swing.JLabel();
            Rad1 = new javax.swing.JRadioButton();
            Rad4 = new javax.swing.JRadioButton();
            Rad3 = new javax.swing.JRadioButton();
            Rad2 = new javax.swing.JRadioButton();
            jButton24 = new javax.swing.JButton();
            LabPrfResd_Std4 = new javax.swing.JLabel();
            NumCardToUpdt1 = new javax.swing.JTextField();
            NumCardToUpdt6 = new javax.swing.JTextField();
            NumCardToUpdt7 = new javax.swing.JTextField();
            NumCardToUpdt8 = new javax.swing.JTextField();
            jLabel19 = new javax.swing.JLabel();
            jLabel30 = new javax.swing.JLabel();
            jLabel33 = new javax.swing.JLabel();
            jLabel36 = new javax.swing.JLabel();
            jLabel38 = new javax.swing.JLabel();
            jButton16 = new javax.swing.JButton();
            PnIUpdtusr = new javax.swing.JPanel();
            Pnform2 = new javax.swing.JPanel();
            LbInsPrnom2 = new javax.swing.JLabel();
            TxtInsPrenomUsr = new javax.swing.JTextField();
            TxtInsIdentifiantUsr = new javax.swing.JTextField();
            TxtInsNomusr = new javax.swing.JTextField();
            LbInsID2 = new javax.swing.JLabel();
            LbInsNom2 = new javax.swing.JLabel();
            LbInsPsw2 = new javax.swing.JLabel();
            Separator4 = new javax.swing.JSeparator();
            CheckSrvRoom = new javax.swing.JCheckBox();
            Lb_Droit_User2 = new javax.swing.JLabel();
            ChecAccessUsr = new javax.swing.JCheckBox();
            CheckRestarUsr = new javax.swing.JCheckBox();
            CheckAdministrationusr = new javax.swing.JCheckBox();
            CheckInscrRessevSrv = new javax.swing.JCheckBox();
            TxtInsPswUsr = new javax.swing.JPasswordField();
            TxtInsConPswUsr = new javax.swing.JPasswordField();
            LbInsConfrm2 = new javax.swing.JLabel();
            jButton4 = new javax.swing.JButton();
            jButton5 = new javax.swing.JButton();
            jPanel10 = new javax.swing.JPanel();
            jLabel10 = new javax.swing.JLabel();
            TxtInsNomusr2 = new javax.swing.JTextField();
            jLabel15 = new javax.swing.JLabel();
            jScrollPane4 = new javax.swing.JScrollPane();
            TabUserService = new javax.swing.JTable(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;

                }
            };
            jLabel9 = new javax.swing.JLabel();
            jLabel16 = new javax.swing.JLabel();
            jLabel20 = new javax.swing.JLabel();
            Btn_Annul_UserToUPDATE21 = new javax.swing.JButton();
            jButton1 = new javax.swing.JButton();
            jButton3 = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setBackground(new java.awt.Color(255, 255, 255));

            PanCntMenu.setBackground(new java.awt.Color(255, 255, 255));
            PanCntMenu.setPreferredSize(new java.awt.Dimension(150, 700));
            PanCntMenu.setLayout(new java.awt.CardLayout());

            Pn_Menu.setBackground(new java.awt.Color(255, 255, 255));
            Pn_Menu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            Pn_Menu.setPreferredSize(new java.awt.Dimension(150, 370));

            Menu_Update_info.setBackground(new java.awt.Color(0, 102, 102));
            Menu_Update_info.setForeground(new java.awt.Color(255, 255, 255));
            Menu_Update_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/StUpd.png"))); // NOI18N
            Menu_Update_info.setText("تعديل البيانات");
            Menu_Update_info.setBackgroundPainted(true);
            Menu_Update_info.setBorderPainted(false);
            Menu_Update_info.setEntred(false);
            Menu_Update_info.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Menu_Update_info.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            Menu_Update_info.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Menu_Update_info.setRounded(true);
            Menu_Update_info.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Menu_Update_infoActionPerformed(evt);
                }
            });

            Menu_Residence_dossier.setBackground(new java.awt.Color(0, 102, 102));
            Menu_Residence_dossier.setForeground(new java.awt.Color(255, 255, 255));
            Menu_Residence_dossier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/AddUsr.png"))); // NOI18N
            Menu_Residence_dossier.setText("ملــف الايـواء");
            Menu_Residence_dossier.setBackgroundPainted(true);
            Menu_Residence_dossier.setBorderPainted(false);
            Menu_Residence_dossier.setEntred(false);
            Menu_Residence_dossier.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Menu_Residence_dossier.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Menu_Residence_dossier.setRounded(true);
            Menu_Residence_dossier.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Menu_Residence_dossierActionPerformed(evt);
                }
            });

            Menu_Consult_Info.setBackground(new java.awt.Color(0, 102, 102));
            Menu_Consult_Info.setForeground(new java.awt.Color(255, 255, 255));
            Menu_Consult_Info.setText("معاينة البيانات");
            Menu_Consult_Info.setBackgroundPainted(true);
            Menu_Consult_Info.setBorderPainted(false);
            Menu_Consult_Info.setEntred(false);
            Menu_Consult_Info.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Menu_Consult_Info.setRounded(true);
            Menu_Consult_Info.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Menu_Consult_InfoActionPerformed(evt);
                }
            });

            Menu_Reports.setBackground(new java.awt.Color(0, 102, 102));
            Menu_Reports.setForeground(new java.awt.Color(255, 255, 255));
            Menu_Reports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Consult.png"))); // NOI18N
            Menu_Reports.setText("التقأاريــــــر");
            Menu_Reports.setBackgroundPainted(true);
            Menu_Reports.setBorderPainted(false);
            Menu_Reports.setEntred(false);
            Menu_Reports.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Menu_Reports.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Menu_Reports.setRounded(true);
            Menu_Reports.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Menu_ReportsActionPerformed(evt);
                }
            });

            Menu_RoomService.setBackground(new java.awt.Color(0, 102, 102));
            Menu_RoomService.setForeground(new java.awt.Color(255, 255, 255));
            Menu_RoomService.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/ICO_residencebed.png"))); // NOI18N
            Menu_RoomService.setText("خدمة الغرف");
            Menu_RoomService.setBackgroundPainted(true);
            Menu_RoomService.setBorderPainted(false);
            Menu_RoomService.setEntred(false);
            Menu_RoomService.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Menu_RoomService.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Menu_RoomService.setRounded(true);
            Menu_RoomService.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Menu_RoomServiceActionPerformed(evt);
                }
            });

            Menu_Config.setBackground(new java.awt.Color(0, 102, 102));
            Menu_Config.setForeground(new java.awt.Color(255, 255, 255));
            Menu_Config.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/btn-settings-over.png"))); // NOI18N
            Menu_Config.setText("الاعــدادات");
            Menu_Config.setBackgroundPainted(true);
            Menu_Config.setBorderPainted(false);
            Menu_Config.setEntred(false);
            Menu_Config.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Menu_Config.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Menu_Config.setRounded(true);
            Menu_Config.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Menu_ConfigActionPerformed(evt);
                }
            });

            buttonView13.setBackground(new java.awt.Color(204, 204, 204));
            buttonView13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/out.png"))); // NOI18N
            buttonView13.setText("خـــروج");
            buttonView13.setBackgroundPainted(true);
            buttonView13.setBorderPainted(false);
            buttonView13.setEntred(false);
            buttonView13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            buttonView13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            buttonView13.setRounded(true);
            buttonView13.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    buttonView13ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout Pn_MenuLayout = new javax.swing.GroupLayout(Pn_Menu);
            Pn_Menu.setLayout(Pn_MenuLayout);
            Pn_MenuLayout.setHorizontalGroup(
                Pn_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_MenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(Pn_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Menu_Residence_dossier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Menu_Update_info, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Menu_Consult_Info, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Menu_RoomService, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Menu_Reports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Menu_Config, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonView13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            Pn_MenuLayout.setVerticalGroup(
                Pn_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_MenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Menu_Residence_dossier, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Menu_Update_info, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Menu_Consult_Info, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(Menu_RoomService, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Menu_Reports, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Menu_Config, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 340, Short.MAX_VALUE)
                    .addComponent(buttonView13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            PanCntMenu.add(Pn_Menu, "card2");

            getContentPane().add(PanCntMenu, java.awt.BorderLayout.LINE_END);

            jPanel1.setBackground(new java.awt.Color(255, 255, 255));
            jPanel1.setPreferredSize(new java.awt.Dimension(1200, 0));

            jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
            jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Captions_Close.png"))); // NOI18N
            jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel14MouseClicked(evt);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jLabel14MouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jLabel14MouseExited(evt);
                }
            });

            jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Captions_Min.png"))); // NOI18N
            jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel32MouseClicked(evt);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jLabel32MouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jLabel32MouseExited(evt);
                }
            });

            jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel49MouseClicked(evt);
                }
            });

            jLabel5.setFont(new java.awt.Font("Tahoma", 3, 20)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(0, 153, 0));
            jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel5.setText("الاقــــــــامــــة الـجــــــامـعــيـــــــــــــــــــــة الـحــــــاجـــــــــب  بـسـكــــــــــــــــرة");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(99, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(206, 206, 206)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 600, Short.MAX_VALUE)
                        .addComponent(jLabel49)
                        .addGap(0, 600, Short.MAX_VALUE)))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 14, Short.MAX_VALUE)
                        .addComponent(jLabel49)
                        .addGap(0, 13, Short.MAX_VALUE)))
            );

            getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

            SecondCentrePan.setBackground(new java.awt.Color(153, 153, 0));
            SecondCentrePan.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));
            SecondCentrePan.setPreferredSize(new java.awt.Dimension(1000, 700));
            SecondCentrePan.setLayout(new java.awt.CardLayout());

            PanAllServiceStudent.setPreferredSize(new java.awt.Dimension(1025, 700));
            PanAllServiceStudent.setLayout(new java.awt.CardLayout());

            PanInfoCard.setLayout(new java.awt.BorderLayout());

            jPanel4_.setBackground(new java.awt.Color(255, 255, 255));
            jPanel4_.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jPanel4_.setPreferredSize(new java.awt.Dimension(180, 500));

            PanPrfAfterSaisStd.setBackground(new java.awt.Color(255, 255, 255));
            PanPrfAfterSaisStd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
            PanPrfAfterSaisStd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            LabNameRestToPrint.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            LabNameRestToPrint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            LabNameRestToPrint.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanPrfAfterSaisStd.add(LabNameRestToPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 50, 25));

            LabBranchStd.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            LabBranchStd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            LabBranchStd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanPrfAfterSaisStd.add(LabBranchStd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 140, 25));

            LabDateBirth_Plc.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            LabDateBirth_Plc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            LabDateBirth_Plc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanPrfAfterSaisStd.add(LabDateBirth_Plc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 50, 25));

            LabPrfBranch_Std2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            LabPrfBranch_Std2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            LabPrfBranch_Std2.setText("تخصص اعلام الي");
            LabPrfBranch_Std2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanPrfAfterSaisStd.add(LabPrfBranch_Std2, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 365, 151, 25));

            jPanel19.setBackground(new java.awt.Color(40, 44, 52));
            jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel86.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel86.setForeground(new java.awt.Color(255, 255, 255));
            jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel86.setText("N°  Carte :");
            jLabel86.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
            jPanel19.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 33));

            LabPrfNumCard_StdRes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            LabPrfNumCard_StdRes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            LabPrfNumCard_StdRes.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
            jPanel19.add(LabPrfNumCard_StdRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 0, 80, 33));

            PanPrfAfterSaisStd.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 160, -1));

            LabLastNameRestToPrint.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            LabLastNameRestToPrint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            LabLastNameRestToPrint.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanPrfAfterSaisStd.add(LabLastNameRestToPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, 25));

            LabPlaceBirth_Res.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            LabPlaceBirth_Res.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            LabPlaceBirth_Res.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanPrfAfterSaisStd.add(LabPlaceBirth_Res, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 80, 25));

            NumOrder.setBackground(new java.awt.Color(255, 255, 255));
            NumOrder.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            NumOrder.setForeground(new java.awt.Color(204, 0, 0));
            NumOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            NumOrder.setText(""+(Resident_GlRemplissage.GetMAX_ID_Resident()));

            jLabel12.setBackground(new java.awt.Color(255, 255, 255));
            jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel12.setText("N°  Order :");
            jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

            jButton26.setBackground(new java.awt.Color(255, 255, 255));
            jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/scan.png"))); // NOI18N
            jButton26.setContentAreaFilled(false);
            jButton26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jButton26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jButton26.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton26ActionPerformed(evt);
                }
            });

            jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/FileSelect.png"))); // NOI18N
            jLabel98.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabel98.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel98MouseClicked(evt);
                }
            });

            jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/uploadPict1.png"))); // NOI18N
            jButton2.setContentAreaFilled(false);
            jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });

            BtnNext.setBackground(new java.awt.Color(255, 255, 255));
            BtnNext.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            BtnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/ReImprimer.png"))); // NOI18N
            BtnNext.setText("طباعـة البطاقة");
            BtnNext.setToolTipText("");
            BtnNext.setBorder(null);
            BtnNext.setContentAreaFilled(false);
            BtnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            BtnNext.setDefaultCapable(false);
            BtnNext.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            BtnNext.setOpaque(true);
            BtnNext.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            BtnNext.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            BtnNext.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    BtnNextActionPerformed(evt);
                }
            });

            CLabImg.setBackground(new java.awt.Color(255, 255, 255));
            CLabImg.setText("");

            LabPrfResd_Std3.setBackground(new java.awt.Color(255, 255, 255));
            LabPrfResd_Std3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            LabPrfResd_Std3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            LabPrfResd_Std3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/view_icon.png"))); // NOI18N
            LabPrfResd_Std3.setText("معاينة مقررة الايواء");
            LabPrfResd_Std3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            LabPrfResd_Std3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            LabPrfResd_Std3.setOpaque(true);
            LabPrfResd_Std3.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    LabPrfResd_Std3MouseClicked(evt);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    LabPrfResd_Std3MouseEntered(evt);
                }
            });

            PrinRecWtoutSh.setBackground(new java.awt.Color(255, 255, 255));
            PrinRecWtoutSh.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            PrinRecWtoutSh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/print.png"))); // NOI18N
            PrinRecWtoutSh.setText("طباعة مقررة الايواء");
            PrinRecWtoutSh.setBorder(null);
            PrinRecWtoutSh.setContentAreaFilled(false);
            PrinRecWtoutSh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            PrinRecWtoutSh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            PrinRecWtoutSh.setOpaque(true);
            PrinRecWtoutSh.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    PrinRecWtoutShActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel4_Layout = new javax.swing.GroupLayout(jPanel4_);
            jPanel4_.setLayout(jPanel4_Layout);
            jPanel4_Layout.setHorizontalGroup(
                jPanel4_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4_Layout.createSequentialGroup()
                    .addGroup(jPanel4_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4_Layout.createSequentialGroup()
                            .addGroup(jPanel4_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4_Layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addGroup(jPanel4_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4_Layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(CLabImg, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel4_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel4_Layout.createSequentialGroup()
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(NumOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel4_Layout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addComponent(BtnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4_Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(PanPrfAfterSaisStd, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4_Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel4_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LabPrfResd_Std3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PrinRecWtoutSh, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap())
            );
            jPanel4_Layout.setVerticalGroup(
                jPanel4_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4_Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(jPanel4_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(NumOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel4_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4_Layout.createSequentialGroup()
                            .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(CLabImg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(PanPrfAfterSaisStd, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(LabPrfResd_Std3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(PrinRecWtoutSh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                    .addComponent(BtnNext)
                    .addGap(32, 32, 32))
            );

            PanInfoCard.add(jPanel4_, java.awt.BorderLayout.LINE_START);

            jPanel5_.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jPanel5_.setPreferredSize(new java.awt.Dimension(1000, 50));

            timeLabel.setBackground(new java.awt.Color(255, 255, 255));
            timeLabel.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
            timeLabel.setForeground(new java.awt.Color(0, 0, 255));
            timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            timeLabel.setOpaque(true);
            timeLabel.setPreferredSize(new java.awt.Dimension(0, 30));
            timeLabel.setText(new Date().getHours()+":"+new Date().getMinutes()+":"+new Date().getSeconds());
            timeLabel.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    timeLabelFocusGained(evt);
                }
            });

            jLabel55.setForeground(new java.awt.Color(255, 255, 255));

            BtnSaveStd.setBackground(new java.awt.Color(40, 44, 52));
            BtnSaveStd.setForeground(new java.awt.Color(255, 255, 255));
            BtnSaveStd.setText("حـفـظ");
            BtnSaveStd.setBackgroundPainted(true);
            BtnSaveStd.setBorderPainted(false);
            BtnSaveStd.setEntred(false);
            BtnSaveStd.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            BtnSaveStd.setRounded(true);
            BtnSaveStd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    BtnSaveStdActionPerformed(evt);
                }
            });

            buttonView12.setBackground(new java.awt.Color(40, 44, 52));
            buttonView12.setForeground(new java.awt.Color(255, 255, 255));
            buttonView12.setText("الــغـــاء");
            buttonView12.setBackgroundPainted(true);
            buttonView12.setBorderPainted(false);
            buttonView12.setEntred(false);
            buttonView12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            buttonView12.setRounded(true);
            buttonView12.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    buttonView12ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel5_Layout = new javax.swing.GroupLayout(jPanel5_);
            jPanel5_.setLayout(jPanel5_Layout);
            jPanel5_Layout.setHorizontalGroup(
                jPanel5_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5_Layout.createSequentialGroup()
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(97, 97, 97)
                    .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(224, 224, 224)
                    .addComponent(buttonView12, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(121, 121, 121)
                    .addComponent(BtnSaveStd, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(162, Short.MAX_VALUE))
            );
            jPanel5_Layout.setVerticalGroup(
                jPanel5_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5_Layout.createSequentialGroup()
                    .addGroup(jPanel5_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5_Layout.createSequentialGroup()
                            .addGroup(jPanel5_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(timeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buttonView12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BtnSaveStd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );

            PanInfoCard.add(jPanel5_, java.awt.BorderLayout.PAGE_END);

            jPanel2_.setBackground(new java.awt.Color(255, 255, 255));
            jPanel2_.setPreferredSize(new java.awt.Dimension(1000, 50));

            check_Std.setBackground(new java.awt.Color(255, 255, 255));
            check_Std.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_Std.setText("طالب داخلي");
            check_Std.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 102, 255)));
            check_Std.setBorderPainted(true);
            check_Std.setBorderPaintedFlat(true);
            check_Std.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            check_Std.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            check_Std.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_Std.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_StdActionPerformed(evt);
                }
            });
            check_Std.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    check_StdKeyPressed(evt);
                }
            });

            check_StdExt.setBackground(new java.awt.Color(255, 255, 255));
            check_StdExt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_StdExt.setText("طالب خـــارجــي");
            check_StdExt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 102, 255)));
            check_StdExt.setBorderPainted(true);
            check_StdExt.setBorderPaintedFlat(true);
            check_StdExt.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            check_StdExt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            check_StdExt.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_StdExt.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_StdExtActionPerformed(evt);
                }
            });

            check_Emp.setBackground(new java.awt.Color(255, 255, 255));
            check_Emp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_Emp.setText("عـــامـــل");
            check_Emp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 102, 255)));
            check_Emp.setBorderPainted(true);
            check_Emp.setBorderPaintedFlat(true);
            check_Emp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            check_Emp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            check_Emp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_Emp.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_EmpActionPerformed(evt);
                }
            });
            check_Emp.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    check_EmpKeyPressed(evt);
                }
            });

            check_Prof.setBackground(new java.awt.Color(255, 255, 255));
            check_Prof.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_Prof.setText("اســـتـاذ");
            check_Prof.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 102, 255)));
            check_Prof.setBorderPainted(true);
            check_Prof.setBorderPaintedFlat(true);
            check_Prof.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            check_Prof.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            check_Prof.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_Prof.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_ProfActionPerformed(evt);
                }
            });
            check_Prof.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    check_ProfKeyPressed(evt);
                }
            });

            txt_NumInsc.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txt_NumInsc.setForeground(new java.awt.Color(153, 153, 153));
            txt_NumInsc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txt_NumInsc.setText("رقــم تــسجـيــل الــبـكــالــوريــا ");
            txt_NumInsc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txt_NumInsc.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txt_NumInscFocusGained(evt);
                }
            });
            txt_NumInsc.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txt_NumInscActionPerformed(evt);
                }
            });
            txt_NumInsc.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txt_NumInscKeyPressed(evt);
                }
            });

            buttonView10.setBackground(new java.awt.Color(40, 44, 52));
            buttonView10.setForeground(new java.awt.Color(255, 255, 255));
            buttonView10.setText("طالب خارجي");
            buttonView10.setBackgroundPainted(true);
            buttonView10.setBorderPainted(false);
            buttonView10.setEntred(false);
            buttonView10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            buttonView10.setRounded(true);
            buttonView10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    buttonView10ActionPerformed(evt);
                }
            });

            buttonView11.setBackground(new java.awt.Color(40, 44, 52));
            buttonView11.setForeground(new java.awt.Color(255, 255, 255));
            buttonView11.setText("عامل");
            buttonView11.setBackgroundPainted(true);
            buttonView11.setBorderPainted(false);
            buttonView11.setEntred(false);
            buttonView11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            buttonView11.setRounded(true);
            buttonView11.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    buttonView11ActionPerformed(evt);
                }
            });

            jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/ExternPop.png"))); // NOI18N
            jLabel18.setToolTipText("اضافة طالب خارجي-عامل-استاذ");
            jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel18MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel2_Layout = new javax.swing.GroupLayout(jPanel2_);
            jPanel2_.setLayout(jPanel2_Layout);
            jPanel2_Layout.setHorizontalGroup(
                jPanel2_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2_Layout.createSequentialGroup()
                    .addComponent(jLabel18)
                    .addGap(253, 253, 253)
                    .addComponent(buttonView11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonView10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(85, 85, 85)
                    .addComponent(check_Emp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(90, 90, 90)
                    .addComponent(check_StdExt, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(106, 106, 106)
                    .addComponent(check_Prof, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(84, 84, 84)
                    .addComponent(check_Std, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(115, 115, 115)
                    .addComponent(txt_NumInsc, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel2_Layout.setVerticalGroup(
                jPanel2_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2_Layout.createSequentialGroup()
                    .addGroup(jPanel2_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2_Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2_Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel2_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(check_Std, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(check_StdExt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(check_Emp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(check_Prof, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonView10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonView11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(txt_NumInsc, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );

            PanInfoCard.add(jPanel2_, java.awt.BorderLayout.PAGE_START);

            Pan_All_PansSais_.setBackground(new java.awt.Color(255, 255, 255));
            Pan_All_PansSais_.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            Pan_All_PansSais_.setPreferredSize(new java.awt.Dimension(1000, 500));
            Pan_All_PansSais_.setLayout(new java.awt.CardLayout());

            panSaisiStd_.setBackground(new java.awt.Color(255, 255, 255));
            panSaisiStd_.setPreferredSize(new java.awt.Dimension(1000, 804));

            jPanel6.setBackground(new java.awt.Color(255, 255, 255));
            jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jPanel6.setPreferredSize(new java.awt.Dimension(100, 30));
            jPanel6.setLayout(new java.awt.GridBagLayout());

            jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel2.setText("الاســــــم");
            jLabel2.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 7;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
            jPanel6.add(jLabel2, gridBagConstraints);

            txtNam_std.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtNam_std.setForeground(new java.awt.Color(204, 204, 204));
            txtNam_std.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNam_std.setText("الاسم");
            txtNam_std.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_std.setPreferredSize(new java.awt.Dimension(100, 30));
            txtNam_std.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNam_stdKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 7;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
            jPanel6.add(txtNam_std, gridBagConstraints);

            jLabel72.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel72.setText("تـاريـخ الـميـلاد");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel72, gridBagConstraints);

            DatBirth_std.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            DatBirth_std.setForeground(new java.awt.Color(153, 153, 153));
            try {
                DatBirth_std.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }
            DatBirth_std.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            DatBirth_std.setFont(new java.awt.Font("Times New Roman", 1,16));
            DatBirth_std.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    DatBirth_stdFocusGained(evt);
                }
            });
            DatBirth_std.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    DatBirth_stdKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(DatBirth_std, gridBagConstraints);

            jLabel71.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel71.setText("الـلـــقـــب");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel71, gridBagConstraints);

            txtSurNam_std.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtSurNam_std.setForeground(new java.awt.Color(204, 204, 204));
            txtSurNam_std.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtSurNam_std.setText("الـلـــقـــب");
            txtSurNam_std.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtSurNam_std.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtSurNam_stdKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(txtSurNam_std, gridBagConstraints);

            jLabel73.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel73.setText("مــكـان الـميــلاد");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel73, gridBagConstraints);

            txtPlcBirth_std.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtPlcBirth_std.setForeground(new java.awt.Color(204, 204, 204));
            txtPlcBirth_std.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPlcBirth_std.setText("مــكـان الـميــلاد");
            txtPlcBirth_std.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtPlcBirth_std.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtPlcBirth_stdKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
            jPanel6.add(txtPlcBirth_std, gridBagConstraints);

            txtNam_Father.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtNam_Father.setForeground(new java.awt.Color(204, 204, 204));
            txtNam_Father.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNam_Father.setText("اسم الأب");
            txtNam_Father.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_Father.setPreferredSize(new java.awt.Dimension(100, 30));
            txtNam_Father.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNam_FatherKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 7;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
            jPanel6.add(txtNam_Father, gridBagConstraints);

            jLabel75.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel75.setText("التخـصص الدراسي ");
            jLabel75.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 7;
            gridBagConstraints.gridy = 16;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
            jPanel6.add(jLabel75, gridBagConstraints);

            txtProfission_Std.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtProfission_Std.setForeground(new java.awt.Color(204, 204, 204));
            txtProfission_Std.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtProfission_Std.setText("مهنة الاب");
            txtProfission_Std.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtProfission_Std.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtProfission_StdKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(txtProfission_Std, gridBagConstraints);

            jLabel74.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel74.setText("مهنــة الاب ");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel74, gridBagConstraints);

            jLabel161.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel161.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel161.setText(" اســم  الام");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel161, gridBagConstraints);

            txtNam_mother.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtNam_mother.setForeground(new java.awt.Color(204, 204, 204));
            txtNam_mother.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNam_mother.setText("اسم  الام");
            txtNam_mother.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_mother.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNam_motherKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
            jPanel6.add(txtNam_mother, gridBagConstraints);

            jLabel76.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel76.setText("لقب  الام");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel76, gridBagConstraints);

            LastNamMothARTxt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            LastNamMothARTxt.setForeground(new java.awt.Color(204, 204, 204));
            LastNamMothARTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            LastNamMothARTxt.setText("لقب  الام");
            LastNamMothARTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            LastNamMothARTxt.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    LastNamMothARTxtKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
            jPanel6.add(LastNamMothARTxt, gridBagConstraints);

            txtProfission_Moth.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtProfission_Moth.setForeground(new java.awt.Color(204, 204, 204));
            txtProfission_Moth.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtProfission_Moth.setText("مهنة الام");
            txtProfission_Moth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtProfission_Moth.setPreferredSize(new java.awt.Dimension(100, 35));
            txtProfission_Moth.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtProfission_MothKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 7;
            gridBagConstraints.gridy = 8;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
            jPanel6.add(txtProfission_Moth, gridBagConstraints);

            jLabel77.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel77.setText("مهنـة الام");
            jLabel77.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 7;
            gridBagConstraints.gridy = 7;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
            jPanel6.add(jLabel77, gridBagConstraints);

            BtnShowRooms.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            BtnShowRooms.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            BtnShowRooms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/ViewSmal.jpg"))); // NOI18N
            BtnShowRooms.setToolTipText("معاينة الغرف المحجوزة");
            BtnShowRooms.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            BtnShowRooms.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    BtnShowRoomsMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    BtnShowRoomsMouseExited(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 12;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
            jPanel6.add(BtnShowRooms, gridBagConstraints);

            WilayaList.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            WilayaList.setAutoscrolls(true);
            WilayaList.setPreferredSize(new java.awt.Dimension(100, 35));
            WilayaList.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    WilayaListActionPerformed(evt);
                }
            });
            WilayaList.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    WilayaListKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 6;
            gridBagConstraints.gridy = 8;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            jPanel6.add(WilayaList, gridBagConstraints);

            jLabel80.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel80.setText("الــولايـــة");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 6;
            gridBagConstraints.gridy = 7;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            jPanel6.add(jLabel80, gridBagConstraints);

            jLabel88.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel88.setText("الــبلــديــة");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 7;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel88, gridBagConstraints);

            CombCommuneSlct.setPreferredSize(new java.awt.Dimension(100, 35));
            CombCommuneSlct.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    CombCommuneSlctItemStateChanged(evt);
                }
            });
            CombCommuneSlct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    CombCommuneSlctActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 8;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(CombCommuneSlct, gridBagConstraints);

            jLabel81.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel81.setText("الــدائـــرة");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 7;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel81, gridBagConstraints);

            txtDairaStd.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtDairaStd.setForeground(new java.awt.Color(204, 204, 204));
            txtDairaStd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtDairaStd.setText("الدائرة");
            txtDairaStd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtDairaStd.setPreferredSize(new java.awt.Dimension(100, 35));
            txtDairaStd.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtDairaStdKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 8;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
            jPanel6.add(txtDairaStd, gridBagConstraints);

            jLabel78.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel78.setText(" الــعـنــوان ");
            jLabel78.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 7;
            gridBagConstraints.gridy = 10;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
            jPanel6.add(jLabel78, gridBagConstraints);

            txtAddress_Std.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtAddress_Std.setForeground(new java.awt.Color(204, 204, 204));
            txtAddress_Std.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtAddress_Std.setText("العـنوان");
            txtAddress_Std.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtAddress_Std.setPreferredSize(new java.awt.Dimension(100, 32));
            txtAddress_Std.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtAddress_StdKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 11;
            gridBagConstraints.gridwidth = 4;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
            jPanel6.add(txtAddress_Std, gridBagConstraints);

            jLabel79.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel79.setText("الجنـسـية");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 10;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel79, gridBagConstraints);

            National_list.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            National_list.setAutoscrolls(true);
            National_list.setPreferredSize(new java.awt.Dimension(100, 35));
            National_list.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    National_listItemStateChanged(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 11;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
            jPanel6.add(National_list, gridBagConstraints);

            PavillonPanInf.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            PavillonPanInf.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    PavillonPanInfActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 11;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
            jPanel6.add(PavillonPanInf, gridBagConstraints);

            jLabel67.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel67.setText("الجناح");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 10;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
            jPanel6.add(jLabel67, gridBagConstraints);

            jLabel65.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel65.setText("الغرفة");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 10;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
            jPanel6.add(jLabel65, gridBagConstraints);

            RomPvinPanInf.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            RomPvinPanInf.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    RomPvinPanInfItemStateChanged(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 11;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(RomPvinPanInf, gridBagConstraints);

            jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
            jCheckBox1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jCheckBox1.setText("الغاء حجز الغرفة");
            jCheckBox1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            jCheckBox1.setBorderPainted(true);
            jCheckBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox1ActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 12;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
            jPanel6.add(jCheckBox1, gridBagConstraints);

            jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/C_Add_Mini_h.png"))); // NOI18N
            jLabel11.setToolTipText("اضافة جنسية جديدة");
            jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jLabel11MouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jLabel11MouseExited(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 17;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
            jPanel6.add(jLabel11, gridBagConstraints);

            jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/C_Add_Mini_h.png"))); // NOI18N
            jLabel13.setToolTipText("معاينة بيانات الولاية وبلدياتها");
            jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel13MouseClicked(evt);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jLabel13MouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jLabel13MouseExited(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 8;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            jPanel6.add(jLabel13, gridBagConstraints);

            txtNam_std_FR.setBackground(new java.awt.Color(204, 204, 204));
            txtNam_std_FR.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtNam_std_FR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNam_std_FR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_std_FR.setPreferredSize(new java.awt.Dimension(100, 30));
            txtNam_std_FR.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtNam_std_FRFocusGained(evt);
                }
            });
            txtNam_std_FR.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNam_std_FRKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 6;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            jPanel6.add(txtNam_std_FR, gridBagConstraints);

            txtSurNam_std_FR.setBackground(new java.awt.Color(204, 204, 204));
            txtSurNam_std_FR.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtSurNam_std_FR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtSurNam_std_FR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtSurNam_std_FR.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtSurNam_std_FRFocusGained(evt);
                }
            });
            txtSurNam_std_FR.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtSurNam_std_FRKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
            jPanel6.add(txtSurNam_std_FR, gridBagConstraints);

            jLabel82.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel82.setText("Nom");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel82, gridBagConstraints);

            jLabel94.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel94.setText("Nom de mere");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel94, gridBagConstraints);

            LastNamMothARTxt_FR.setBackground(new java.awt.Color(204, 204, 204));
            LastNamMothARTxt_FR.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            LastNamMothARTxt_FR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            LastNamMothARTxt_FR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            LastNamMothARTxt_FR.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    LastNamMothARTxt_FRFocusGained(evt);
                }
            });
            LastNamMothARTxt_FR.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    LastNamMothARTxt_FRKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
            jPanel6.add(LastNamMothARTxt_FR, gridBagConstraints);

            txtNam_Father_FR.setBackground(new java.awt.Color(204, 204, 204));
            txtNam_Father_FR.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtNam_Father_FR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNam_Father_FR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_Father_FR.setPreferredSize(new java.awt.Dimension(100, 30));
            txtNam_Father_FR.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtNam_Father_FRFocusGained(evt);
                }
            });
            txtNam_Father_FR.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNam_Father_FRKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 6;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            jPanel6.add(txtNam_Father_FR, gridBagConstraints);

            jLabel99.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel99.setText("Prenem de pere");
            jLabel99.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 6;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            jPanel6.add(jLabel99, gridBagConstraints);

            jLabel64.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel64.setText("Prenom");
            jLabel64.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 6;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.weightx = 1.0;
            jPanel6.add(jLabel64, gridBagConstraints);

            txtPlcBirth_std_FR.setBackground(new java.awt.Color(204, 204, 204));
            txtPlcBirth_std_FR.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtPlcBirth_std_FR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPlcBirth_std_FR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtPlcBirth_std_FR.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtPlcBirth_std_FRFocusGained(evt);
                }
            });
            txtPlcBirth_std_FR.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtPlcBirth_std_FRKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
            jPanel6.add(txtPlcBirth_std_FR, gridBagConstraints);

            jLabel100.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel100.setText("lieu de naissance");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.weightx = 1.0;
            jPanel6.add(jLabel100, gridBagConstraints);

            txtNam_mother_FR.setBackground(new java.awt.Color(204, 204, 204));
            txtNam_mother_FR.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtNam_mother_FR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNam_mother_FR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_mother_FR.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtNam_mother_FRFocusGained(evt);
                }
            });
            txtNam_mother_FR.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNam_mother_FRKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(txtNam_mother_FR, gridBagConstraints);

            jLabel182.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel182.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel182.setText("Prenom Mere");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel182, gridBagConstraints);

            TtxtBacYear.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            TtxtBacYear.setForeground(new java.awt.Color(204, 204, 204));
            TtxtBacYear.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            TtxtBacYear.setText("2018");
            TtxtBacYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            TtxtBacYear.setPreferredSize(new java.awt.Dimension(100, 30));
            TtxtBacYear.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    TtxtBacYearKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 7;
            gridBagConstraints.gridy = 14;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
            jPanel6.add(TtxtBacYear, gridBagConstraints);

            jLabel101.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel101.setText("اســــم الأب");
            jLabel101.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 7;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
            jPanel6.add(jLabel101, gridBagConstraints);

            txtBacMoy1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtBacMoy1.setForeground(new java.awt.Color(204, 204, 204));
            txtBacMoy1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtBacMoy1.setText("00.00");
            txtBacMoy1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtBacMoy1.setPreferredSize(new java.awt.Dimension(100, 30));
            txtBacMoy1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtBacMoy1KeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 6;
            gridBagConstraints.gridy = 14;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            jPanel6.add(txtBacMoy1, gridBagConstraints);

            jLabel102.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel102.setText("تاريخ الـتسجيل بالجامعة");
            jLabel102.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 13;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            jPanel6.add(jLabel102, gridBagConstraints);

            jLabel103.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel103.setText("المـسـتوي الـدراسـي");
            jLabel103.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 16;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel103, gridBagConstraints);

            txtPlaceGetBac1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtPlaceGetBac1.setForeground(new java.awt.Color(204, 204, 204));
            txtPlaceGetBac1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPlaceGetBac1.setText("مــكـان الــحــصـول عــلــي الــبــكـالـــوريـــا");
            txtPlaceGetBac1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtPlaceGetBac1.setPreferredSize(new java.awt.Dimension(100, 30));
            txtPlaceGetBac1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtPlaceGetBac1KeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 14;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
            jPanel6.add(txtPlaceGetBac1, gridBagConstraints);

            jLabel84.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel84.setText("الــبـكـالــوريـا  دورة");
            jLabel84.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 7;
            gridBagConstraints.gridy = 13;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
            jPanel6.add(jLabel84, gridBagConstraints);

            jLabel104.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel104.setText("الــمــعــدل");
            jLabel104.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 6;
            gridBagConstraints.gridy = 13;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            jPanel6.add(jLabel104, gridBagConstraints);

            jLabel109.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel109.setText("مــكـان الــحــصـول عــلــي الــبــكـالـــوريـــا");
            jLabel109.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 13;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel109, gridBagConstraints);

            jLabel110.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel110.setText("الـــكلـيـة");
            jLabel110.setPreferredSize(new java.awt.Dimension(100, 30));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 16;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(jLabel110, gridBagConstraints);

            DatInscrpInUniv.setForeground(java.awt.Color.blue);
            try {
                DatInscrpInUniv.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }
            DatInscrpInUniv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            DatInscrpInUniv.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            DatInscrpInUniv.setPreferredSize(new java.awt.Dimension(0, 35));
            SimpleDateFormat formatDat=new SimpleDateFormat("dd/MM/YYYY");
            String DatInscrpStr=formatDat.format(new Date());

            DatInscrpInUniv.setText(DatInscrpStr);
            DatInscrpInUniv.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    DatInscrpInUnivKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 14;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
            gridBagConstraints.weightx = 1.0;
            jPanel6.add(DatInscrpInUniv, gridBagConstraints);

            LevelStd.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            LevelStd.setAutoscrolls(true);
            LevelStd.setPreferredSize(new java.awt.Dimension(0, 35));
            LevelStd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    LevelStdActionPerformed(evt);
                }
            });
            LevelStd.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    LevelStdKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 17;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
            gridBagConstraints.weightx = 1.0;
            jPanel6.add(LevelStd, gridBagConstraints);

            txtDepa_Std.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtDepa_Std.setAutoscrolls(true);
            txtDepa_Std.setPreferredSize(new java.awt.Dimension(0, 35));
            txtDepa_Std.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtDepa_StdKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 17;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
            gridBagConstraints.weightx = 1.0;
            jPanel6.add(txtDepa_Std, gridBagConstraints);

            txtBranch_std.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            txtBranch_std.setAutoscrolls(true);
            txtBranch_std.setPreferredSize(new java.awt.Dimension(0, 35));
            txtBranch_std.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    txtBranch_stdItemStateChanged(evt);
                }
            });
            txtBranch_std.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtBranch_stdKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 6;
            gridBagConstraints.gridy = 17;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
            jPanel6.add(txtBranch_std, gridBagConstraints);

            jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/C_Add_Mini_h.png"))); // NOI18N
            jLabel60.setToolTipText("اضافة جنسية جديدة");
            jLabel60.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabel60.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jLabel60MouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jLabel60MouseExited(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 17;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
            jPanel6.add(jLabel60, gridBagConstraints);

            jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/C_Add_Mini_h.png"))); // NOI18N
            jLabel61.setToolTipText("اضافة جنسية جديدة");
            jLabel61.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabel61.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jLabel61MouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jLabel61MouseExited(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = 17;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 3);
            jPanel6.add(jLabel61, gridBagConstraints);

            LabelShowNationalite.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            LabelShowNationalite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/C_Add_Mini_h.png"))); // NOI18N
            LabelShowNationalite.setToolTipText("اضافة جنسية جديدة");
            LabelShowNationalite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            LabelShowNationalite.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    LabelShowNationaliteMouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    LabelShowNationaliteMouseExited(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 11;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
            jPanel6.add(LabelShowNationalite, gridBagConstraints);

            RadBtnMarriedStd.setBackground(new java.awt.Color(255, 255, 255));
            RadBtnMarriedStd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            RadBtnMarriedStd.setText("مــتــزوج");
            RadBtnMarriedStd.setBorder(null);
            RadBtnMarriedStd.setBorderPainted(true);
            RadBtnMarriedStd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RadBtnMarriedStd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            RadBtnMarriedStd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            RadBtnMarriedStd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RadBtnMarriedStdActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 8;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
            jPanel6.add(RadBtnMarriedStd, gridBagConstraints);

            RadBtnSingleStd.setBackground(new java.awt.Color(255, 255, 255));
            RadBtnSingleStd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            RadBtnSingleStd.setSelected(true);
            RadBtnSingleStd.setText("اعـــزب");
            RadBtnSingleStd.setBorder(null);
            RadBtnSingleStd.setBorderPainted(true);
            RadBtnSingleStd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RadBtnSingleStd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            RadBtnSingleStd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            RadBtnSingleStd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RadBtnSingleStdActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 8;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
            jPanel6.add(RadBtnSingleStd, gridBagConstraints);

            BtnRdFemaleStd.setBackground(new java.awt.Color(255, 255, 255));
            BtnRdFemaleStd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            BtnRdFemaleStd.setText("مؤنث");
            BtnRdFemaleStd.setBorder(null);
            BtnRdFemaleStd.setBorderPainted(true);
            BtnRdFemaleStd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            BtnRdFemaleStd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            BtnRdFemaleStd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            BtnRdFemaleStd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    BtnRdFemaleStdActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 7;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
            jPanel6.add(BtnRdFemaleStd, gridBagConstraints);

            BtnRdMaleStd.setBackground(new java.awt.Color(255, 255, 255));
            BtnRdMaleStd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            BtnRdMaleStd.setSelected(true);
            BtnRdMaleStd.setText("مذكر");
            BtnRdMaleStd.setBorder(null);
            BtnRdMaleStd.setBorderPainted(true);
            BtnRdMaleStd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            BtnRdMaleStd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            BtnRdMaleStd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            BtnRdMaleStd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    BtnRdMaleStdActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 7;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
            jPanel6.add(BtnRdMaleStd, gridBagConstraints);

            jLabel70.setBackground(new java.awt.Color(255, 255, 255));
            jLabel70.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel70.setText("السنـة الجامعية:");

            firstYrs.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            firstYrs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            firstYrs.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    firstYrsStateChanged(evt);
                }
            });

            jLabel95.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel95.setText("/");

            Annet1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Annet1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            Annet1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

            javax.swing.GroupLayout panSaisiStd_Layout = new javax.swing.GroupLayout(panSaisiStd_);
            panSaisiStd_.setLayout(panSaisiStd_Layout);
            panSaisiStd_Layout.setHorizontalGroup(
                panSaisiStd_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panSaisiStd_Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Annet1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(firstYrs, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jLabel70)
                    .addContainerGap())
            );
            panSaisiStd_Layout.setVerticalGroup(
                panSaisiStd_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panSaisiStd_Layout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panSaisiStd_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panSaisiStd_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Annet1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(firstYrs, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                    .addContainerGap(111, Short.MAX_VALUE))
            );

            Pan_All_PansSais_.add(panSaisiStd_, "card2");

            PanSaisiEmp_.setBackground(new java.awt.Color(255, 255, 255));

            jLabel174.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel174.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel174.setText("المهنـــــــــــــة      : ");
            jLabel174.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

            txtSurNam_Empl.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtSurNam_Empl.setForeground(new java.awt.Color(153, 153, 153));
            txtSurNam_Empl.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtSurNam_Empl.setText("ادخل اللقب");
            txtSurNam_Empl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtSurNam_Empl.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtSurNam_EmplFocusGained(evt);
                }
            });
            txtSurNam_Empl.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtSurNam_EmplKeyPressed(evt);
                }
            });

            txtNam_EmplInsr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtNam_EmplInsr.setForeground(new java.awt.Color(153, 153, 153));
            txtNam_EmplInsr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNam_EmplInsr.setText("ادخل الاسم");
            txtNam_EmplInsr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_EmplInsr.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtNam_EmplInsrFocusGained(evt);
                }
            });
            txtNam_EmplInsr.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNam_EmplInsrKeyPressed(evt);
                }
            });

            txtPlcBirth_Emply.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtPlcBirth_Emply.setForeground(new java.awt.Color(153, 153, 153));
            txtPlcBirth_Emply.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPlcBirth_Emply.setText("ادخل مكان الميلاد");
            txtPlcBirth_Emply.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtPlcBirth_Emply.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtPlcBirth_EmplyFocusGained(evt);
                }
            });
            txtPlcBirth_Emply.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtPlcBirth_EmplyKeyPressed(evt);
                }
            });

            DatBirth_Empl.setForeground(java.awt.Color.blue);
            try {
                DatBirth_Empl.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }
            DatBirth_Empl.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            DatBirth_Empl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            DatBirth_Empl.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            DatBirth_Empl.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    DatBirth_EmplKeyPressed(evt);
                }
            });

            Profession.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            Profession.setToolTipText("");
            Profession.setOpaque(false);
            Profession.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    ProfessionKeyPressed(evt);
                }
            });

            Gdr_Emp_Mal1.setBackground(new java.awt.Color(255, 255, 255));
            Gdr_Emp_Mal1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Gdr_Emp_Mal1.setSelected(true);
            Gdr_Emp_Mal1.setText("مذكر");
            Gdr_Emp_Mal1.setToolTipText("");
            Gdr_Emp_Mal1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            Gdr_Emp_Mal1.setBorderPainted(true);
            Gdr_Emp_Mal1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            Gdr_Emp_Mal1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Gdr_Emp_Mal1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Gdr_Emp_Mal1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Gdr_Emp_Mal1ActionPerformed(evt);
                }
            });

            Gdr_Emp_femal1.setBackground(new java.awt.Color(255, 255, 255));
            Gdr_Emp_femal1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Gdr_Emp_femal1.setText("مؤنث");
            Gdr_Emp_femal1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            Gdr_Emp_femal1.setBorderPainted(true);
            Gdr_Emp_femal1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            Gdr_Emp_femal1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Gdr_Emp_femal1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Gdr_Emp_femal1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Gdr_Emp_femal1ActionPerformed(evt);
                }
            });

            jCheckBox4.setBackground(new java.awt.Color(255, 255, 255));
            jCheckBox4.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jCheckBox4.setText("وظيفة جديدة");
            jCheckBox4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jCheckBox4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jCheckBox4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox4ActionPerformed(evt);
                }
            });

            jTextField2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

            jLabel69.setBackground(new java.awt.Color(255, 255, 255));
            jLabel69.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel69.setText("حفظ ");
            jLabel69.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jLabel69.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel69.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel69MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout PanSaisiEmp_Layout = new javax.swing.GroupLayout(PanSaisiEmp_);
            PanSaisiEmp_.setLayout(PanSaisiEmp_Layout);
            PanSaisiEmp_Layout.setHorizontalGroup(
                PanSaisiEmp_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanSaisiEmp_Layout.createSequentialGroup()
                    .addGap(226, 226, 226)
                    .addGroup(PanSaisiEmp_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanSaisiEmp_Layout.createSequentialGroup()
                            .addGap(105, 105, 105)
                            .addComponent(txtNam_EmplInsr, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanSaisiEmp_Layout.createSequentialGroup()
                            .addGap(105, 105, 105)
                            .addComponent(txtSurNam_Empl, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanSaisiEmp_Layout.createSequentialGroup()
                            .addGap(105, 105, 105)
                            .addComponent(DatBirth_Empl, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanSaisiEmp_Layout.createSequentialGroup()
                            .addGap(105, 105, 105)
                            .addComponent(txtPlcBirth_Emply, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanSaisiEmp_Layout.createSequentialGroup()
                            .addGap(105, 105, 105)
                            .addComponent(Gdr_Emp_femal1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(Gdr_Emp_Mal1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanSaisiEmp_Layout.createSequentialGroup()
                            .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(Profession, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel174, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanSaisiEmp_Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(200, Short.MAX_VALUE))
            );
            PanSaisiEmp_Layout.setVerticalGroup(
                PanSaisiEmp_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanSaisiEmp_Layout.createSequentialGroup()
                    .addGap(102, 102, 102)
                    .addComponent(txtNam_EmplInsr, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14)
                    .addComponent(txtSurNam_Empl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14)
                    .addComponent(DatBirth_Empl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14)
                    .addComponent(txtPlcBirth_Emply, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14)
                    .addGroup(PanSaisiEmp_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Gdr_Emp_femal1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Gdr_Emp_Mal1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(34, 34, 34)
                    .addGroup(PanSaisiEmp_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Profession, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel174, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(14, 14, 14)
                    .addGroup(PanSaisiEmp_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(112, 112, 112))
            );

            Pan_All_PansSais_.add(PanSaisiEmp_, "card5");

            PanInfoCard.add(Pan_All_PansSais_, java.awt.BorderLayout.CENTER);

            PanAllServiceStudent.add(PanInfoCard, "card2");

            UpdateStd.setPreferredSize(new java.awt.Dimension(1180, 600));
            UpdateStd.setLayout(new java.awt.BorderLayout());

            PanAllUpdateResident.setLayout(new java.awt.CardLayout());

            InfoStdToUpdate_1.setBackground(new java.awt.Color(255, 255, 255));
            InfoStdToUpdate_1.setLayout(new java.awt.BorderLayout());

            jPanel4.setBackground(new java.awt.Color(255, 255, 255));
            jPanel4.setPreferredSize(new java.awt.Dimension(500, 200));
            jPanel4.setLayout(new java.awt.BorderLayout());

            Img_StdUpdate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            Img_StdUpdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Img_StdUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/imageRes.png"))); // NOI18N
            Img_StdUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            Img_StdUpdate.setPreferredSize(new java.awt.Dimension(150, 150));
            Img_StdUpdate.setIcon(new ImageIcon(getClass().getResource("/residence/Image/imageRes.png")));

            NumCardToUpdt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            NumCardToUpdt.setForeground(new java.awt.Color(255, 0, 0));
            NumCardToUpdt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            NumCardToUpdt.setText("رقم البطاقة");
            NumCardToUpdt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            NumCardToUpdt.setPreferredSize(new java.awt.Dimension(80, 35));
            NumCardToUpdt.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    NumCardToUpdtKeyPressed(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    NumCardToUpdtKeyTyped(evt);
                }
            });

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Img_StdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(NumCardToUpdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(NumCardToUpdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Img_StdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(40, Short.MAX_VALUE))
            );

            jPanel4.add(jPanel2, java.awt.BorderLayout.WEST);

            jPanel5.setBackground(new java.awt.Color(255, 255, 255));
            jPanel5.setPreferredSize(new java.awt.Dimension(918, 180));
            jPanel5.setLayout(new java.awt.GridBagLayout());

            txtAddress_StdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            txtAddress_StdUpdtForm.setForeground(java.awt.Color.gray);
            txtAddress_StdUpdtForm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtAddress_StdUpdtForm.setText("العنوان");
            txtAddress_StdUpdtForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtAddress_StdUpdtForm.setMinimumSize(new java.awt.Dimension(4, 21));
            txtAddress_StdUpdtForm.setPreferredSize(new java.awt.Dimension(33, 35));
            txtAddress_StdUpdtForm.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtAddress_StdUpdtFormFocusGained(evt);
                }
            });
            txtAddress_StdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtAddress_StdUpdtFormKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.ipady = 9;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
            jPanel5.add(txtAddress_StdUpdtForm, gridBagConstraints);

            txt_NumInscUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            txt_NumInscUpdtForm.setForeground(new java.awt.Color(153, 153, 153));
            txt_NumInscUpdtForm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txt_NumInscUpdtForm.setText("رقــم تــسجـيــل  ");
            txt_NumInscUpdtForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txt_NumInscUpdtForm.setPreferredSize(new java.awt.Dimension(80, 35));
            txt_NumInscUpdtForm.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txt_NumInscUpdtFormFocusGained(evt);
                }
            });
            txt_NumInscUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txt_NumInscUpdtFormKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
            jPanel5.add(txt_NumInscUpdtForm, gridBagConstraints);

            CaseResident.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            CaseResident.setPreferredSize(new java.awt.Dimension(150, 35));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 150, 0, 0);
            jPanel5.add(CaseResident, gridBagConstraints);

            txtSurNam_std5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            txtSurNam_std5.setForeground(java.awt.Color.gray);
            txtSurNam_std5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtSurNam_std5.setText("اللقب");
            txtSurNam_std5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtSurNam_std5.setMaximumSize(new java.awt.Dimension(2, 21));
            txtSurNam_std5.setPreferredSize(new java.awt.Dimension(150, 35));
            txtSurNam_std5.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtSurNam_std5FocusGained(evt);
                }
            });
            txtSurNam_std5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtSurNam_std5ActionPerformed(evt);
                }
            });
            txtSurNam_std5.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtSurNam_std5KeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
            jPanel5.add(txtSurNam_std5, gridBagConstraints);

            PlaceBirthFrStdUpdForm.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            PlaceBirthFrStdUpdForm.setForeground(new java.awt.Color(153, 153, 153));
            PlaceBirthFrStdUpdForm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            PlaceBirthFrStdUpdForm.setText("Lieu _Naissance");
            PlaceBirthFrStdUpdForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            PlaceBirthFrStdUpdForm.setPreferredSize(new java.awt.Dimension(150, 35));
            PlaceBirthFrStdUpdForm.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    PlaceBirthFrStdUpdFormFocusGained(evt);
                }
            });
            PlaceBirthFrStdUpdForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    PlaceBirthFrStdUpdFormKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 6, 0, 0);
            jPanel5.add(PlaceBirthFrStdUpdForm, gridBagConstraints);

            LastName_ResidentFrUp.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            LastName_ResidentFrUp.setForeground(new java.awt.Color(153, 153, 153));
            LastName_ResidentFrUp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            LastName_ResidentFrUp.setText("Nom");
            LastName_ResidentFrUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            LastName_ResidentFrUp.setPreferredSize(new java.awt.Dimension(150, 35));
            LastName_ResidentFrUp.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    LastName_ResidentFrUpFocusGained(evt);
                }
            });
            LastName_ResidentFrUp.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    LastName_ResidentFrUpActionPerformed(evt);
                }
            });
            LastName_ResidentFrUp.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    LastName_ResidentFrUpKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 6, 0, 0);
            jPanel5.add(LastName_ResidentFrUp, gridBagConstraints);

            txtPlcBirth_stdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            txtPlcBirth_stdUpdtForm.setForeground(java.awt.Color.gray);
            txtPlcBirth_stdUpdtForm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPlcBirth_stdUpdtForm.setText("مكان الميلاد");
            txtPlcBirth_stdUpdtForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtPlcBirth_stdUpdtForm.setMaximumSize(new java.awt.Dimension(2, 21));
            txtPlcBirth_stdUpdtForm.setPreferredSize(new java.awt.Dimension(150, 35));
            txtPlcBirth_stdUpdtForm.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtPlcBirth_stdUpdtFormFocusGained(evt);
                }
            });
            txtPlcBirth_stdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtPlcBirth_stdUpdtFormKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
            jPanel5.add(txtPlcBirth_stdUpdtForm, gridBagConstraints);

            Name_ResidentFrUp.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            Name_ResidentFrUp.setForeground(new java.awt.Color(153, 153, 153));
            Name_ResidentFrUp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            Name_ResidentFrUp.setText("Prénom");
            Name_ResidentFrUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            Name_ResidentFrUp.setPreferredSize(new java.awt.Dimension(150, 35));
            Name_ResidentFrUp.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    Name_ResidentFrUpFocusGained(evt);
                }
            });
            Name_ResidentFrUp.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    Name_ResidentFrUpKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
            jPanel5.add(Name_ResidentFrUp, gridBagConstraints);

            DatBirth_stdUpdtForm.setForeground(java.awt.Color.blue);
            try {
                DatBirth_stdUpdtForm.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }
            DatBirth_stdUpdtForm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            DatBirth_stdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            DatBirth_stdUpdtForm.setMaximumSize(new java.awt.Dimension(2, 21));
            DatBirth_stdUpdtForm.setMinimumSize(new java.awt.Dimension(2, 21));
            DatBirth_stdUpdtForm.setPreferredSize(new java.awt.Dimension(150, 35));
            DatBirth_stdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    DatBirth_stdUpdtFormKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 150, 0, 0);
            jPanel5.add(DatBirth_stdUpdtForm, gridBagConstraints);

            txtNam_std5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            txtNam_std5.setForeground(java.awt.Color.gray);
            txtNam_std5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNam_std5.setText("الاسم");
            txtNam_std5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_std5.setPreferredSize(new java.awt.Dimension(150, 35));
            txtNam_std5.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtNam_std5FocusGained(evt);
                }
            });
            txtNam_std5.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNam_std5KeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
            jPanel5.add(txtNam_std5, gridBagConstraints);

            jLabel87.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel87.setText("المعلومات الشخضية للطـالب");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            jPanel5.add(jLabel87, gridBagConstraints);

            jPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

            InfoStdToUpdate_1.add(jPanel4, java.awt.BorderLayout.PAGE_START);

            jPanel7.setBackground(new java.awt.Color(255, 255, 255));
            jPanel7.setMinimumSize(new java.awt.Dimension(700, 500));
            jPanel7.setPreferredSize(new java.awt.Dimension(500, 417));
            jPanel7.setLayout(new java.awt.GridBagLayout());

            txtNam_FatherStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtNam_FatherStdUpdtForm.setForeground(java.awt.Color.gray);
            txtNam_FatherStdUpdtForm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            txtNam_FatherStdUpdtForm.setText("اسم الاب");
            txtNam_FatherStdUpdtForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_FatherStdUpdtForm.setMaximumSize(new java.awt.Dimension(2, 21));
            txtNam_FatherStdUpdtForm.setPreferredSize(new java.awt.Dimension(150, 35));
            txtNam_FatherStdUpdtForm.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtNam_FatherStdUpdtFormFocusGained(evt);
                }
            });
            txtNam_FatherStdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNam_FatherStdUpdtFormKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
            jPanel7.add(txtNam_FatherStdUpdtForm, gridBagConstraints);

            txtProfissionfath_StdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtProfissionfath_StdUpdtForm.setForeground(java.awt.Color.gray);
            txtProfissionfath_StdUpdtForm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtProfissionfath_StdUpdtForm.setText("وظيفة الاب");
            txtProfissionfath_StdUpdtForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtProfissionfath_StdUpdtForm.setPreferredSize(new java.awt.Dimension(200, 35));
            txtProfissionfath_StdUpdtForm.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtProfissionfath_StdUpdtFormFocusGained(evt);
                }
            });
            txtProfissionfath_StdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtProfissionfath_StdUpdtFormKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
            jPanel7.add(txtProfissionfath_StdUpdtForm, gridBagConstraints);

            Name_FatherFrUp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Name_FatherFrUp.setForeground(new java.awt.Color(153, 153, 153));
            Name_FatherFrUp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            Name_FatherFrUp.setText("Prénom Père");
            Name_FatherFrUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            Name_FatherFrUp.setPreferredSize(new java.awt.Dimension(150, 35));
            Name_FatherFrUp.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    Name_FatherFrUpFocusGained(evt);
                }
            });
            Name_FatherFrUp.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    Name_FatherFrUpKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
            jPanel7.add(Name_FatherFrUp, gridBagConstraints);

            LastName_MotheFrUp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            LastName_MotheFrUp.setForeground(new java.awt.Color(153, 153, 153));
            LastName_MotheFrUp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            LastName_MotheFrUp.setText("Nom_Mère");
            LastName_MotheFrUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            LastName_MotheFrUp.setPreferredSize(new java.awt.Dimension(150, 35));
            LastName_MotheFrUp.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    LastName_MotheFrUpFocusGained(evt);
                }
            });
            LastName_MotheFrUp.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    LastName_MotheFrUpActionPerformed(evt);
                }
            });
            LastName_MotheFrUp.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    LastName_MotheFrUpKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            jPanel7.add(LastName_MotheFrUp, gridBagConstraints);

            NamMrArStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            NamMrArStdUpdtForm.setForeground(new java.awt.Color(153, 153, 153));
            NamMrArStdUpdtForm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            NamMrArStdUpdtForm.setText("لقب الام");
            NamMrArStdUpdtForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            NamMrArStdUpdtForm.setPreferredSize(new java.awt.Dimension(150, 35));
            NamMrArStdUpdtForm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    NamMrArStdUpdtFormActionPerformed(evt);
                }
            });
            NamMrArStdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    NamMrArStdUpdtFormKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
            jPanel7.add(NamMrArStdUpdtForm, gridBagConstraints);

            txtDairaStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtDairaStdUpdtForm.setForeground(java.awt.Color.gray);
            txtDairaStdUpdtForm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtDairaStdUpdtForm.setText("الدائرة");
            txtDairaStdUpdtForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtDairaStdUpdtForm.setPreferredSize(new java.awt.Dimension(150, 35));
            txtDairaStdUpdtForm.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtDairaStdUpdtFormFocusGained(evt);
                }
            });
            txtDairaStdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtDairaStdUpdtFormKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 7;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
            jPanel7.add(txtDairaStdUpdtForm, gridBagConstraints);

            txtCommuneStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtCommuneStdUpdtForm.setForeground(java.awt.Color.gray);
            txtCommuneStdUpdtForm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCommuneStdUpdtForm.setText("البلدية");
            txtCommuneStdUpdtForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtCommuneStdUpdtForm.setPreferredSize(new java.awt.Dimension(150, 35));
            txtCommuneStdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtCommuneStdUpdtFormKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 7;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
            jPanel7.add(txtCommuneStdUpdtForm, gridBagConstraints);

            WilayaListUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            WilayaListUpdtForm.setOpaque(false);
            WilayaListUpdtForm.setPreferredSize(new java.awt.Dimension(0, 35));
            WilayaListUpdtForm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    WilayaListUpdtFormActionPerformed(evt);
                }
            });
            WilayaListUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    WilayaListUpdtFormKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = 7;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
            jPanel7.add(WilayaListUpdtForm, gridBagConstraints);

            National_listStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            National_listStdUpdtForm.setPreferredSize(new java.awt.Dimension(0, 35));
            National_listStdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    National_listStdUpdtFormKeyPressed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = 9;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            jPanel7.add(National_listStdUpdtForm, gridBagConstraints);

            checkFemalStdUpdtForm.setBackground(new java.awt.Color(255, 255, 255));
            checkFemalStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            checkFemalStdUpdtForm.setText("مؤنث");
            checkFemalStdUpdtForm.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            checkFemalStdUpdtForm.setBorderPainted(true);
            checkFemalStdUpdtForm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            checkFemalStdUpdtForm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            checkFemalStdUpdtForm.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            checkFemalStdUpdtForm.setMaximumSize(new java.awt.Dimension(50, 19));
            checkFemalStdUpdtForm.setMinimumSize(new java.awt.Dimension(50, 19));
            checkFemalStdUpdtForm.setPreferredSize(new java.awt.Dimension(50, 19));
            checkFemalStdUpdtForm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    checkFemalStdUpdtFormActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 9;
            jPanel7.add(checkFemalStdUpdtForm, gridBagConstraints);

            CheckMaleStdUpdtForm.setBackground(new java.awt.Color(255, 255, 255));
            CheckMaleStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            CheckMaleStdUpdtForm.setSelected(true);
            CheckMaleStdUpdtForm.setText("مذكر");
            CheckMaleStdUpdtForm.setToolTipText("");
            CheckMaleStdUpdtForm.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            CheckMaleStdUpdtForm.setBorderPainted(true);
            CheckMaleStdUpdtForm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            CheckMaleStdUpdtForm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            CheckMaleStdUpdtForm.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            CheckMaleStdUpdtForm.setMaximumSize(new java.awt.Dimension(50, 19));
            CheckMaleStdUpdtForm.setMinimumSize(new java.awt.Dimension(50, 19));
            CheckMaleStdUpdtForm.setPreferredSize(new java.awt.Dimension(50, 19));
            CheckMaleStdUpdtForm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    CheckMaleStdUpdtFormActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 9;
            jPanel7.add(CheckMaleStdUpdtForm, gridBagConstraints);

            CombCommuneUpdtForm.setPreferredSize(new java.awt.Dimension(0, 35));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 7;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
            jPanel7.add(CombCommuneUpdtForm, gridBagConstraints);

            jLabel146.setBackground(new java.awt.Color(255, 255, 255));
            jLabel146.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel146.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel146.setText("الــولايـــة ");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = 6;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
            jPanel7.add(jLabel146, gridBagConstraints);

            jLabel145.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel145.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel145.setText("الــجــنـســيـــة");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = 8;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
            jPanel7.add(jLabel145, gridBagConstraints);

            jLabel97.setBackground(new java.awt.Color(255, 255, 255));
            jLabel97.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel97.setText(" البلدية");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 6;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
            jPanel7.add(jLabel97, gridBagConstraints);

            CombRomInUpdtForm.setPreferredSize(new java.awt.Dimension(50, 35));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
            jPanel7.add(CombRomInUpdtForm, gridBagConstraints);

            jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel29.setText("الغرفة");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 50, 0, 50);
            jPanel7.add(jLabel29, gridBagConstraints);

            LstPvlinUpdtForm.setPreferredSize(new java.awt.Dimension(50, 35));
            LstPvlinUpdtForm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    LstPvlinUpdtFormActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 6;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
            jPanel7.add(LstPvlinUpdtForm, gridBagConstraints);

            jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel44.setText("الجناح");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
            jPanel7.add(jLabel44, gridBagConstraints);

            jButton29.setBackground(new java.awt.Color(255, 255, 255));
            jButton29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jButton29.setText("حفظ التعديل");
            jButton29.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton29ActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 7;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
            jPanel7.add(jButton29, gridBagConstraints);

            txtNam_MotherStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtNam_MotherStdUpdtForm.setForeground(java.awt.Color.gray);
            txtNam_MotherStdUpdtForm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            txtNam_MotherStdUpdtForm.setText("اسم الام");
            txtNam_MotherStdUpdtForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_MotherStdUpdtForm.setPreferredSize(new java.awt.Dimension(150, 35));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
            jPanel7.add(txtNam_MotherStdUpdtForm, gridBagConstraints);

            txtProfission_MothStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtProfission_MothStdUpdtForm.setForeground(java.awt.Color.gray);
            txtProfission_MothStdUpdtForm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            txtProfission_MothStdUpdtForm.setText("وظيفة الام");
            txtProfission_MothStdUpdtForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtProfission_MothStdUpdtForm.setPreferredSize(new java.awt.Dimension(150, 35));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            jPanel7.add(txtProfission_MothStdUpdtForm, gridBagConstraints);

            Name_MotherFrUp.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            Name_MotherFrUp.setForeground(new java.awt.Color(153, 153, 153));
            Name_MotherFrUp.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            Name_MotherFrUp.setText("Prenom Mere");
            Name_MotherFrUp.setToolTipText("");
            Name_MotherFrUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            Name_MotherFrUp.setPreferredSize(new java.awt.Dimension(150, 35));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
            jPanel7.add(Name_MotherFrUp, gridBagConstraints);

            jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
            jRadioButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jRadioButton1.setText("مؤنث");
            jRadioButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jRadioButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            jRadioButton1.setMaximumSize(new java.awt.Dimension(80, 23));
            jRadioButton1.setMinimumSize(new java.awt.Dimension(80, 23));
            jRadioButton1.setPreferredSize(new java.awt.Dimension(80, 23));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
            jPanel7.add(jRadioButton1, gridBagConstraints);

            jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
            jRadioButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jRadioButton2.setText("مـــذكر");
            jRadioButton2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jRadioButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            jRadioButton2.setMaximumSize(new java.awt.Dimension(80, 23));
            jRadioButton2.setMinimumSize(new java.awt.Dimension(80, 23));
            jRadioButton2.setPreferredSize(new java.awt.Dimension(80, 23));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 5;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
            jPanel7.add(jRadioButton2, gridBagConstraints);

            Std_MariedStdUpdtForm_x.setBackground(new java.awt.Color(255, 255, 255));
            Std_MariedStdUpdtForm_x.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            Std_MariedStdUpdtForm_x.setText("مــتــزوج");
            Std_MariedStdUpdtForm_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            Std_MariedStdUpdtForm_x.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            Std_MariedStdUpdtForm_x.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Std_MariedStdUpdtForm_x.setMaximumSize(new java.awt.Dimension(80, 23));
            Std_MariedStdUpdtForm_x.setMinimumSize(new java.awt.Dimension(80, 23));
            Std_MariedStdUpdtForm_x.setPreferredSize(new java.awt.Dimension(80, 23));
            Std_MariedStdUpdtForm_x.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Std_MariedStdUpdtForm_xActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
            jPanel7.add(Std_MariedStdUpdtForm_x, gridBagConstraints);

            Sti_SingleStdUpdtForm_x.setBackground(new java.awt.Color(255, 255, 255));
            Sti_SingleStdUpdtForm_x.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            Sti_SingleStdUpdtForm_x.setText("اعـــزب");
            Sti_SingleStdUpdtForm_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            Sti_SingleStdUpdtForm_x.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            Sti_SingleStdUpdtForm_x.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Sti_SingleStdUpdtForm_x.setMaximumSize(new java.awt.Dimension(80, 23));
            Sti_SingleStdUpdtForm_x.setMinimumSize(new java.awt.Dimension(80, 23));
            Sti_SingleStdUpdtForm_x.setPreferredSize(new java.awt.Dimension(80, 23));
            Sti_SingleStdUpdtForm_x.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Sti_SingleStdUpdtForm_xActionPerformed(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
            jPanel7.add(Sti_SingleStdUpdtForm_x, gridBagConstraints);

            jLabel45.setText("اسم الاب");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 6;
            jPanel7.add(jLabel45, gridBagConstraints);

            jLabel92.setBackground(new java.awt.Color(255, 255, 255));
            jLabel92.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
            jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel92.setText("وظيفة الام");
            jLabel92.setPreferredSize(new java.awt.Dimension(150, 35));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = 4;
            gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
            jPanel7.add(jLabel92, gridBagConstraints);

            jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel39.setText("معلومات الغرفة");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            jPanel7.add(jLabel39, gridBagConstraints);

            jLabel27.setText("jLabel27");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 9;
            jPanel7.add(jLabel27, gridBagConstraints);

            jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            jLabel40.setText("اسم الأب");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
            jPanel7.add(jLabel40, gridBagConstraints);

            jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            jLabel41.setText("اسم الأم");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 5;
            gridBagConstraints.gridy = 2;
            jPanel7.add(jLabel41, gridBagConstraints);

            jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            jLabel42.setText("اسم الأم بالفرنسية");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 2;
            jPanel7.add(jLabel42, gridBagConstraints);

            jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            jLabel46.setText("لقب الأم");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 2;
            jPanel7.add(jLabel46, gridBagConstraints);

            jLabel68.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            jLabel68.setText("لقب الأم بالفرنسية");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 2;
            jPanel7.add(jLabel68, gridBagConstraints);

            jLabel83.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            jLabel83.setText("اسم الأب بالفرنسية");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 4;
            gridBagConstraints.gridy = 0;
            jPanel7.add(jLabel83, gridBagConstraints);

            jLabel85.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            jLabel85.setText("وظيفة الأب");
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 3;
            gridBagConstraints.gridy = 0;
            jPanel7.add(jLabel85, gridBagConstraints);

            InfoStdToUpdate_1.add(jPanel7, java.awt.BorderLayout.CENTER);

            jPanel9.setPreferredSize(new java.awt.Dimension(641, 130));

            jLabel151.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel151.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel151.setText("التخـصص الدراســـــــي ");

            Branch_stdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            Branch_stdUpdtForm.setPreferredSize(new java.awt.Dimension(0, 35));
            Branch_stdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    Branch_stdUpdtFormKeyPressed(evt);
                }
            });

            jLabel105.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel105.setText("المستوئ الدراسي");

            LevelStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            LevelStdUpdtForm.setPreferredSize(new java.awt.Dimension(0, 35));
            LevelStdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    LevelStdUpdtFormKeyPressed(evt);
                }
            });

            Depa_StdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            Depa_StdUpdtForm.setPreferredSize(new java.awt.Dimension(0, 35));
            Depa_StdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    Depa_StdUpdtFormKeyPressed(evt);
                }
            });

            jLabel154.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel154.setText(" الـــكـــلــــيــــة");

            txtPlaceGetBacStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtPlaceGetBacStdUpdtForm.setForeground(java.awt.Color.gray);
            txtPlaceGetBacStdUpdtForm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPlaceGetBacStdUpdtForm.setText("مكان الحصول على البكالوريا");
            txtPlaceGetBacStdUpdtForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtPlaceGetBacStdUpdtForm.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtPlaceGetBacStdUpdtFormFocusGained(evt);
                }
            });
            txtPlaceGetBacStdUpdtForm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtPlaceGetBacStdUpdtFormActionPerformed(evt);
                }
            });
            txtPlaceGetBacStdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtPlaceGetBacStdUpdtFormKeyPressed(evt);
                }
            });

            txtBacMoy2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtBacMoy2.setForeground(java.awt.Color.gray);
            txtBacMoy2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtBacMoy2.setText("00.00");
            txtBacMoy2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtBacMoy2.setPreferredSize(new java.awt.Dimension(0, 35));
            txtBacMoy2.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtBacMoy2FocusGained(evt);
                }
            });
            txtBacMoy2.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtBacMoy2KeyPressed(evt);
                }
            });

            jLabel96.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel96.setText("معدل البكالوريا");

            jLabel93.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel93.setText("سنة البكالوريا");

            TtxtBacYearStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            TtxtBacYearStdUpdtForm.setForeground(java.awt.Color.gray);
            TtxtBacYearStdUpdtForm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            TtxtBacYearStdUpdtForm.setText("2018");
            TtxtBacYearStdUpdtForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            TtxtBacYearStdUpdtForm.setPreferredSize(new java.awt.Dimension(0, 35));
            TtxtBacYearStdUpdtForm.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    TtxtBacYearStdUpdtFormFocusGained(evt);
                }
            });
            TtxtBacYearStdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    TtxtBacYearStdUpdtFormKeyPressed(evt);
                }
            });

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addContainerGap(86, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(319, 319, 319)
                            .addComponent(jLabel96)
                            .addGap(36, 36, 36)
                            .addComponent(jLabel93))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(txtPlaceGetBacStdUpdtForm, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(txtBacMoy2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(TtxtBacYearStdUpdtForm, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(128, 128, 128)
                            .addComponent(jLabel105)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel151))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(Depa_StdUpdtForm, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(128, 128, 128)
                            .addComponent(LevelStdUpdtForm, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(Branch_stdUpdtForm, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(72, 72, 72))
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel96)
                        .addComponent(jLabel93))
                    .addGap(6, 6, 6)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPlaceGetBacStdUpdtForm, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBacMoy2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TtxtBacYearStdUpdtForm, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(5, 5, 5)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel105)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel154)
                                .addComponent(jLabel151))))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(Depa_StdUpdtForm, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(LevelStdUpdtForm, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Branch_stdUpdtForm, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 44, Short.MAX_VALUE))
            );

            InfoStdToUpdate_1.add(jPanel9, java.awt.BorderLayout.PAGE_END);

            PanAllUpdateResident.add(InfoStdToUpdate_1, "card6");

            PanProfToUpdate.setBackground(new java.awt.Color(255, 255, 255));

            MessgControl5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            MessgControl5.setForeground(new java.awt.Color(255, 0, 0));
            MessgControl5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            MessgControl5.setText("\n");

            panSaisiInfoProf.setBackground(new java.awt.Color(255, 255, 255));
            panSaisiInfoProf.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    panSaisiInfoProfFocusGained(evt);
                }
            });
            panSaisiInfoProf.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel162.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel162.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel162.setText("المهنـــــــــــــة      : ");
            jLabel162.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            panSaisiInfoProf.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 102, 27));

            jLabel163.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel163.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel163.setText("اللقب                    : ");
            jLabel163.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            panSaisiInfoProf.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 102, 27));

            jLabel164.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel164.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel164.setText("تاريخ الميلاد           : ");
            jLabel164.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            panSaisiInfoProf.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 102, 27));

            jLabel165.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel165.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel165.setText("الاسم                     : ");
            jLabel165.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            panSaisiInfoProf.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 170, 110, 25));

            txtSurNam_Prof1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtSurNam_Prof1.setForeground(new java.awt.Color(153, 153, 153));
            txtSurNam_Prof1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtSurNam_Prof1.setText("ادخل اللقب");
            txtSurNam_Prof1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtSurNam_Prof1.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtSurNam_Prof1FocusGained(evt);
                }
            });
            txtSurNam_Prof1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtSurNam_Prof1KeyPressed(evt);
                }
            });
            panSaisiInfoProf.add(txtSurNam_Prof1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 165, 28));

            jLabel166.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel166.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel166.setText("مكان الميلاد           : ");
            jLabel166.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            panSaisiInfoProf.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 102, 27));

            txtNam_Prof1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtNam_Prof1.setForeground(new java.awt.Color(153, 153, 153));
            txtNam_Prof1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNam_Prof1.setText("ادخل الاسم");
            txtNam_Prof1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_Prof1.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtNam_Prof1FocusGained(evt);
                }
            });
            txtNam_Prof1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNam_Prof1KeyPressed(evt);
                }
            });
            panSaisiInfoProf.add(txtNam_Prof1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 167, 28));

            ftxtPlcBirth_Prof.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            ftxtPlcBirth_Prof.setForeground(new java.awt.Color(153, 153, 153));
            ftxtPlcBirth_Prof.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            ftxtPlcBirth_Prof.setText("ادخل مكان الميلاد");
            ftxtPlcBirth_Prof.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            ftxtPlcBirth_Prof.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    ftxtPlcBirth_ProfFocusGained(evt);
                }
            });
            ftxtPlcBirth_Prof.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    ftxtPlcBirth_ProfKeyPressed(evt);
                }
            });
            panSaisiInfoProf.add(ftxtPlcBirth_Prof, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 165, 28));

            try {
                DatBirth_Pro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }
            DatBirth_Pro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            DatBirth_Pro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            DatBirth_Pro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            DatBirth_Pro.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    DatBirth_ProKeyPressed(evt);
                }
            });
            panSaisiInfoProf.add(DatBirth_Pro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 165, 28));

            txtProfession_Prof2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtProfession_Prof2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtProfession_Prof2.setText("أســـتـاذ");
            txtProfession_Prof2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtProfession_Prof2.setEnabled(false);
            txtProfession_Prof2.setOpaque(false);
            txtProfession_Prof2.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtProfession_Prof2FocusGained(evt);
                }
            });
            txtProfession_Prof2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtProfession_Prof2ActionPerformed(evt);
                }
            });
            panSaisiInfoProf.add(txtProfession_Prof2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 165, 28));

            jLabel167.setBackground(new java.awt.Color(255, 255, 255));
            jLabel167.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel167.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel167.setText("معلـومـــــات الاستـــــــاذ المقيــــــــــم     ");
            jLabel167.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            panSaisiInfoProf.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 310, 30));

            Gdr_Prf_Malp.setBackground(new java.awt.Color(255, 255, 255));
            Gdr_Prf_Malp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Gdr_Prf_Malp.setText("مذكر");
            Gdr_Prf_Malp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            Gdr_Prf_Malp.setBorderPainted(true);
            Gdr_Prf_Malp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            Gdr_Prf_Malp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            Gdr_Prf_Malp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Gdr_Prf_Malp.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Gdr_Prf_MalpActionPerformed(evt);
                }
            });
            panSaisiInfoProf.add(Gdr_Prf_Malp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, -1, -1));

            Gdr_Prf_femalp.setBackground(new java.awt.Color(255, 255, 255));
            Gdr_Prf_femalp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Gdr_Prf_femalp.setText("مؤنث");
            Gdr_Prf_femalp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            Gdr_Prf_femalp.setBorderPainted(true);
            Gdr_Prf_femalp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            Gdr_Prf_femalp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            Gdr_Prf_femalp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Gdr_Prf_femalp.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Gdr_Prf_femalpActionPerformed(evt);
                }
            });
            panSaisiInfoProf.add(Gdr_Prf_femalp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, -1, -1));

            jPanel27.setBackground(new java.awt.Color(255, 255, 255));
            jPanel27.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jPanel27MouseClicked(evt);
                }
            });
            jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            NumCardToUpdt2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            NumCardToUpdt2.setForeground(new java.awt.Color(255, 0, 0));
            NumCardToUpdt2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            NumCardToUpdt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            NumCardToUpdt2.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    NumCardToUpdt2KeyPressed(evt);
                }
            });
            jPanel27.add(NumCardToUpdt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 170, 28));

            jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel116.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/SearchRes.png"))); // NOI18N
            jLabel116.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel116.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel116MouseClicked(evt);
                }
            });
            jPanel27.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 25));

            panSaisiInfoProf.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 200, -1));

            jLabel117.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel117.setText("حـــالة المقيـــم     :");
            jLabel117.setToolTipText("");
            jLabel117.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            panSaisiInfoProf.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 110, 25));

            CaseResidentProfUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            panSaisiInfoProf.add(CaseResidentProfUpdtForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 170, 25));

            jLabel123.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel123.setText("رقـم االبـطـاقـة       :");
            jLabel123.setToolTipText("");
            jLabel123.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            panSaisiInfoProf.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 110, 25));

            Img_Std1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            Img_Std1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Img_Std1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            Img_Std1.setIcon(new ImageIcon(getClass().getResource("/residence/Image/imageRes.png")));

            Img_profupdate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            Img_profupdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Img_profupdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            Img_profupdate.setIcon(new ImageIcon(getClass().getResource("/residence/Image/imageRes.png")));

            jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel128.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/FileSelect.png"))); // NOI18N
            jLabel128.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel128.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel128MouseClicked(evt);
                }
            });

            jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel129.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/uploadPict1.png"))); // NOI18N
            jLabel129.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel129.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel129MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout PanProfToUpdateLayout = new javax.swing.GroupLayout(PanProfToUpdate);
            PanProfToUpdate.setLayout(PanProfToUpdateLayout);
            PanProfToUpdateLayout.setHorizontalGroup(
                PanProfToUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanProfToUpdateLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MessgControl5, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanProfToUpdateLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Img_profupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(PanProfToUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panSaisiInfoProf, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26))
                .addGroup(PanProfToUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanProfToUpdateLayout.createSequentialGroup()
                        .addGap(0, 320, Short.MAX_VALUE)
                        .addComponent(Img_Std1)
                        .addGap(0, 319, Short.MAX_VALUE)))
            );
            PanProfToUpdateLayout.setVerticalGroup(
                PanProfToUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanProfToUpdateLayout.createSequentialGroup()
                    .addGroup(PanProfToUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panSaisiInfoProf, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                        .addGroup(PanProfToUpdateLayout.createSequentialGroup()
                            .addGroup(PanProfToUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(PanProfToUpdateLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(Img_profupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PanProfToUpdateLayout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(MessgControl5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32))
                .addGroup(PanProfToUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanProfToUpdateLayout.createSequentialGroup()
                        .addGap(0, 341, Short.MAX_VALUE)
                        .addComponent(Img_Std1)
                        .addGap(0, 342, Short.MAX_VALUE)))
            );

            PanAllUpdateResident.add(PanProfToUpdate, "card3");

            PanStdExtToUpdate.setBackground(new java.awt.Color(255, 255, 255));

            Img_StdUpdate1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            Img_StdUpdate1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Img_StdUpdate1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            Img_StdUpdate1.setIcon(new ImageIcon(getClass().getResource("/residence/Image/imageRes.png")));

            PanSaisiInfoStdExt1.setBackground(new java.awt.Color(255, 255, 255));
            PanSaisiInfoStdExt1.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    PanSaisiInfoStdExt1FocusGained(evt);
                }
            });
            PanSaisiInfoStdExt1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel192.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel192.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel192.setText("اللقب                    : ");
            jLabel192.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisiInfoStdExt1.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, 102, 27));

            jLabel193.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel193.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel193.setText("تاريخ الميلاد           : ");
            jLabel193.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisiInfoStdExt1.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, 102, 27));

            jLabel194.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel194.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel194.setText("الاسم                     : ");
            jLabel194.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisiInfoStdExt1.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 102, 25));

            txtSurNam_StdExt1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtSurNam_StdExt1.setForeground(new java.awt.Color(153, 153, 153));
            txtSurNam_StdExt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtSurNam_StdExt1.setText("ادخل اللقب");
            txtSurNam_StdExt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtSurNam_StdExt1.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtSurNam_StdExt1FocusGained(evt);
                }
            });
            txtSurNam_StdExt1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtSurNam_StdExt1KeyPressed(evt);
                }
            });
            PanSaisiInfoStdExt1.add(txtSurNam_StdExt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 180, 28));

            jLabel195.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel195.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel195.setText("مكان الميلاد           : ");
            jLabel195.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisiInfoStdExt1.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 102, 27));

            txtNam_StdExt1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtNam_StdExt1.setForeground(new java.awt.Color(153, 153, 153));
            txtNam_StdExt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNam_StdExt1.setText("ادخل الاسم");
            txtNam_StdExt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_StdExt1.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtNam_StdExt1FocusGained(evt);
                }
            });
            txtNam_StdExt1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNam_StdExt1KeyPressed(evt);
                }
            });
            PanSaisiInfoStdExt1.add(txtNam_StdExt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 180, 28));

            txtPlcBirth_StdExt1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtPlcBirth_StdExt1.setForeground(new java.awt.Color(153, 153, 153));
            txtPlcBirth_StdExt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPlcBirth_StdExt1.setText("ادخل مكان الميلاد");
            txtPlcBirth_StdExt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtPlcBirth_StdExt1.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtPlcBirth_StdExt1FocusGained(evt);
                }
            });
            PanSaisiInfoStdExt1.add(txtPlcBirth_StdExt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 180, 28));

            try {
                DatBirth_StdExt1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }
            DatBirth_StdExt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            DatBirth_StdExt1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            DatBirth_StdExt1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            DatBirth_StdExt1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    DatBirth_StdExt1ActionPerformed(evt);
                }
            });
            DatBirth_StdExt1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    DatBirth_StdExt1KeyPressed(evt);
                }
            });
            PanSaisiInfoStdExt1.add(DatBirth_StdExt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 180, 28));

            jLabel196.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel196.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel196.setText("التخـصص الدراســـــــي      :");
            PanSaisiInfoStdExt1.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 130, 25));

            Branch_stdExternStdUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            Branch_stdExternStdUpdtForm.setAutoscrolls(true);
            Branch_stdExternStdUpdtForm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Branch_stdExternStdUpdtFormActionPerformed(evt);
                }
            });
            Branch_stdExternStdUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    Branch_stdExternStdUpdtFormKeyPressed(evt);
                }
            });
            PanSaisiInfoStdExt1.add(Branch_stdExternStdUpdtForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 250, 30));

            jLabel197.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel197.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel197.setText("الــمـسـتــوي الـدراسـي        :");
            PanSaisiInfoStdExt1.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, 130, 30));

            LevelStdExtUpdtForm.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            LevelStdExtUpdtForm.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    LevelStdExtUpdtFormActionPerformed(evt);
                }
            });
            LevelStdExtUpdtForm.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    LevelStdExtUpdtFormKeyPressed(evt);
                }
            });
            PanSaisiInfoStdExt1.add(LevelStdExtUpdtForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 250, 30));

            jLabel198.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel198.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel198.setText("رقــم تــسجـيـل         :");
            jLabel198.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisiInfoStdExt1.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 100, 30));

            txt_NumInscSdExt1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            txt_NumInscSdExt1.setForeground(new java.awt.Color(153, 153, 153));
            txt_NumInscSdExt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txt_NumInscSdExt1.setText("رقــم التــسجـيــل  ");
            txt_NumInscSdExt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txt_NumInscSdExt1.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txt_NumInscSdExt1FocusGained(evt);
                }
            });
            PanSaisiInfoStdExt1.add(txt_NumInscSdExt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 180, 30));

            jLabel199.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel199.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel199.setText("معلـومـــــات الـــطـــالـــب الــخـــارجـــي     ");
            jLabel199.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            PanSaisiInfoStdExt1.add(jLabel199, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 320, 30));

            jPanel24.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jPanel24MouseClicked(evt);
                }
            });

            jPanel28.setBackground(new java.awt.Color(255, 255, 255));
            jPanel28.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jPanel28MouseClicked(evt);
                }
            });
            jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            NumCardToUpdt3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            NumCardToUpdt3.setForeground(new java.awt.Color(255, 0, 0));
            NumCardToUpdt3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            NumCardToUpdt3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            NumCardToUpdt3.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    NumCardToUpdt3KeyPressed(evt);
                }
            });
            jPanel28.add(NumCardToUpdt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 170, 28));

            jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel118.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/SearchRes.png"))); // NOI18N
            jLabel118.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel118.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel118MouseClicked(evt);
                }
            });
            jPanel28.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 25));

            jLabel120.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel120.setText("رقـم االبـطـاقـة       :");
            jLabel120.setToolTipText("");
            jLabel120.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

            javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
            jPanel24.setLayout(jPanel24Layout);
            jPanel24Layout.setHorizontalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 340, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
            );
            jPanel24Layout.setVerticalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 28, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
            );

            PanSaisiInfoStdExt1.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 200, -1));

            jLabel114.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel114.setText("رقـم االبـطـاقـة       :");
            jLabel114.setToolTipText("");
            jLabel114.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisiInfoStdExt1.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 110, 30));

            jLabel124.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel124.setText("حـــالة المقيـــم     :");
            jLabel124.setToolTipText("");
            jLabel124.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisiInfoStdExt1.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 110, 25));

            CaseResident2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            PanSaisiInfoStdExt1.add(CaseResident2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 170, 25));

            Gdr_Prf_Malp1.setBackground(new java.awt.Color(255, 255, 255));
            Gdr_Prf_Malp1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Gdr_Prf_Malp1.setText("مذكر");
            Gdr_Prf_Malp1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            Gdr_Prf_Malp1.setBorderPainted(true);
            Gdr_Prf_Malp1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            Gdr_Prf_Malp1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            Gdr_Prf_Malp1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Gdr_Prf_Malp1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Gdr_Prf_Malp1ActionPerformed(evt);
                }
            });
            PanSaisiInfoStdExt1.add(Gdr_Prf_Malp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, -1, -1));

            Gdr_Prf_femalp1.setBackground(new java.awt.Color(255, 255, 255));
            Gdr_Prf_femalp1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Gdr_Prf_femalp1.setText("مؤنث");
            Gdr_Prf_femalp1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            Gdr_Prf_femalp1.setBorderPainted(true);
            Gdr_Prf_femalp1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            Gdr_Prf_femalp1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            Gdr_Prf_femalp1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Gdr_Prf_femalp1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Gdr_Prf_femalp1ActionPerformed(evt);
                }
            });
            PanSaisiInfoStdExt1.add(Gdr_Prf_femalp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, -1, -1));

            jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel131.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/uploadPict1.png"))); // NOI18N
            jLabel131.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel131.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel131MouseClicked(evt);
                }
            });

            jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel132.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/FileSelect.png"))); // NOI18N
            jLabel132.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel132.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel132MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout PanStdExtToUpdateLayout = new javax.swing.GroupLayout(PanStdExtToUpdate);
            PanStdExtToUpdate.setLayout(PanStdExtToUpdateLayout);
            PanStdExtToUpdateLayout.setHorizontalGroup(
                PanStdExtToUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanStdExtToUpdateLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Img_StdUpdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(PanStdExtToUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanSaisiInfoStdExt1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18))
            );
            PanStdExtToUpdateLayout.setVerticalGroup(
                PanStdExtToUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanStdExtToUpdateLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(PanStdExtToUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(PanSaisiInfoStdExt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanStdExtToUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanStdExtToUpdateLayout.createSequentialGroup()
                                .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Img_StdUpdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(154, Short.MAX_VALUE))
            );

            PanAllUpdateResident.add(PanStdExtToUpdate, "card4");

            PanSaisiEmp1.setBackground(new java.awt.Color(255, 255, 255));
            PanSaisiEmp1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            PanSaisInfoEmp1.setBackground(new java.awt.Color(255, 255, 255));
            PanSaisInfoEmp1.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    PanSaisInfoEmp1FocusGained(evt);
                }
            });
            PanSaisInfoEmp1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel184.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel184.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel184.setText("المهنـــــــــــــة      : ");
            jLabel184.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisInfoEmp1.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 102, 27));

            jLabel185.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel185.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel185.setText("اللقب                    : ");
            jLabel185.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisInfoEmp1.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 102, 27));

            jLabel186.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel186.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel186.setText("تاريخ الميلاد           : ");
            jLabel186.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisInfoEmp1.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 102, 27));

            jLabel187.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel187.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel187.setText("الاسم                     : ");
            jLabel187.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisInfoEmp1.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 102, 25));

            txtSurNam_Prof7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtSurNam_Prof7.setForeground(new java.awt.Color(153, 153, 153));
            txtSurNam_Prof7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtSurNam_Prof7.setText("ادخل اللقب");
            txtSurNam_Prof7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtSurNam_Prof7.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtSurNam_Prof7FocusGained(evt);
                }
            });
            txtSurNam_Prof7.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtSurNam_Prof7KeyPressed(evt);
                }
            });
            PanSaisInfoEmp1.add(txtSurNam_Prof7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 165, 28));

            jLabel188.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel188.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel188.setText("مكان الميلاد           : ");
            jLabel188.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisInfoEmp1.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 102, 27));

            txtNam_Prof7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtNam_Prof7.setForeground(new java.awt.Color(153, 153, 153));
            txtNam_Prof7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNam_Prof7.setText("ادخل الاسم");
            txtNam_Prof7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtNam_Prof7.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtNam_Prof7FocusGained(evt);
                }
            });
            txtNam_Prof7.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNam_Prof7KeyPressed(evt);
                }
            });
            PanSaisInfoEmp1.add(txtNam_Prof7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 167, 28));

            txtPlcBirth_Prof7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtPlcBirth_Prof7.setForeground(new java.awt.Color(153, 153, 153));
            txtPlcBirth_Prof7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPlcBirth_Prof7.setText("ادخل مكان الميلاد");
            txtPlcBirth_Prof7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtPlcBirth_Prof7.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    txtPlcBirth_Prof7FocusGained(evt);
                }
            });
            txtPlcBirth_Prof7.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtPlcBirth_Prof7KeyPressed(evt);
                }
            });
            PanSaisInfoEmp1.add(txtPlcBirth_Prof7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 165, 28));

            try {
                DatBirth_Prof7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }
            DatBirth_Prof7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            DatBirth_Prof7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            DatBirth_Prof7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            DatBirth_Prof7.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    DatBirth_Prof7KeyPressed(evt);
                }
            });
            PanSaisInfoEmp1.add(DatBirth_Prof7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 165, 28));

            jLabel189.setBackground(new java.awt.Color(255, 255, 255));
            jLabel189.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel189.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel189.setText("مـعـلـــومـــــات الـــعـــامـــل     ");
            jLabel189.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            PanSaisInfoEmp1.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 310, 30));

            jLabel190.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel190.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel190.setText("رقـم االبـطـاقـة       :");
            jLabel190.setToolTipText("");
            jLabel190.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisInfoEmp1.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 110, 30));

            ProfessionToUpdate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            ProfessionToUpdate.setToolTipText("");
            ProfessionToUpdate.setOpaque(false);
            ProfessionToUpdate.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    ProfessionToUpdateKeyPressed(evt);
                }
            });
            PanSaisInfoEmp1.add(ProfessionToUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 220, 30));

            jPanel31.setBackground(new java.awt.Color(255, 255, 255));
            jPanel31.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jPanel31MouseClicked(evt);
                }
            });
            jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            NumCardToUpdt4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            NumCardToUpdt4.setForeground(new java.awt.Color(255, 0, 0));
            NumCardToUpdt4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            NumCardToUpdt4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            NumCardToUpdt4.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    NumCardToUpdt4KeyPressed(evt);
                }
            });
            jPanel31.add(NumCardToUpdt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 170, 28));

            jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel121.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/SearchRes.png"))); // NOI18N
            jLabel121.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel121.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel121MouseClicked(evt);
                }
            });
            jPanel31.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 25));

            PanSaisInfoEmp1.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 48, 200, 30));

            Gdr_Emp_femalE.setBackground(new java.awt.Color(255, 255, 255));
            Gdr_Emp_femalE.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Gdr_Emp_femalE.setText("مـؤنـث");
            Gdr_Emp_femalE.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            Gdr_Emp_femalE.setBorderPainted(true);
            Gdr_Emp_femalE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Gdr_Emp_femalE.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Gdr_Emp_femalE.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Gdr_Emp_femalEActionPerformed(evt);
                }
            });
            PanSaisInfoEmp1.add(Gdr_Emp_femalE, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 60, -1));

            Gdr_Emp_MalE.setBackground(new java.awt.Color(255, 255, 255));
            Gdr_Emp_MalE.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Gdr_Emp_MalE.setText("مذكــر");
            Gdr_Emp_MalE.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            Gdr_Emp_MalE.setBorderPainted(true);
            Gdr_Emp_MalE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Gdr_Emp_MalE.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Gdr_Emp_MalE.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Gdr_Emp_MalEActionPerformed(evt);
                }
            });
            PanSaisInfoEmp1.add(Gdr_Emp_MalE, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 50, 20));

            jLabel125.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel125.setText("حـــالة المقيـــم     :");
            jLabel125.setToolTipText("");
            jLabel125.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanSaisInfoEmp1.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 110, 25));

            CaseResident3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            PanSaisInfoEmp1.add(CaseResident3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 170, 25));

            PanSaisiEmp1.add(PanSaisInfoEmp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 480, 459));

            Img_EmpUpdate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            Img_EmpUpdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Img_EmpUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            Img_EmpUpdate.setIcon(new ImageIcon(getClass().getResource("/residence/Image/imageRes.png")));
            PanSaisiEmp1.add(Img_EmpUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 110));

            jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel133.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/FileSelect.png"))); // NOI18N
            jLabel133.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel133.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel133MouseClicked(evt);
                }
            });
            PanSaisiEmp1.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 30, 30));

            jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel153.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/uploadPict1.png"))); // NOI18N
            jLabel153.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel153.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel153MouseClicked(evt);
                }
            });
            PanSaisiEmp1.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 30, 30));

            PanAllUpdateResident.add(PanSaisiEmp1, "card6");

            UpdateStd.add(PanAllUpdateResident, java.awt.BorderLayout.CENTER);

            jPanel25.setBackground(new java.awt.Color(255, 255, 255));

            TabResident.getTableHeader().setReorderingAllowed(false);
            TabResident.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            TabResident.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            TabResident.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "مكان الميلاد", "تاريخ الميلاد", "الاسم ", "اللقب", "الرقم"
                }
            ));
            TabResident.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            TabResident.setRowHeight(24);
            TabResident.setSelectionBackground(new java.awt.Color(51, 204, 0));
            TabResident.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    TabResidentMouseClicked(evt);
                }
            });
            TabResident.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    TabResidentKeyPressed(evt);
                }
            });
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            TabResident.setDefaultRenderer(String.class, centerRenderer);
            jScrollPane6.setViewportView(TabResident);

            CountTabStdIn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            CountTabStdIn.setForeground(new java.awt.Color(255, 0, 51));
            CountTabStdIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            CountTabStdIn.setText("5");

            LabNamesForRes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            LabNamesForRes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            LabNamesForRes.setText("عدد الطلاب :");

            btnUpdRes.setBackground(new java.awt.Color(0, 153, 153));
            btnUpdRes.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            btnUpdRes.setForeground(new java.awt.Color(255, 255, 255));
            btnUpdRes.setText("تعـــــديل");
            btnUpdRes.setBorder(null);
            btnUpdRes.setContentAreaFilled(false);
            btnUpdRes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            btnUpdRes.setEnabled(false);
            btnUpdRes.setOpaque(true);
            btnUpdRes.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnUpdResActionPerformed(evt);
                }
            });

            BtnCancel.setBackground(new java.awt.Color(0, 153, 153));
            BtnCancel.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            BtnCancel.setForeground(new java.awt.Color(255, 255, 255));
            BtnCancel.setText("الغــــاء");
            BtnCancel.setBorder(null);
            BtnCancel.setContentAreaFilled(false);
            BtnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            BtnCancel.setOpaque(true);
            BtnCancel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    BtnCancelActionPerformed(evt);
                }
            });

            Search_Field_upd_std.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            Search_Field_upd_std.setForeground(java.awt.Color.gray);
            Search_Field_upd_std.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            Search_Field_upd_std.setText("البحث");
            Search_Field_upd_std.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    Search_Field_upd_stdFocusGained(evt);
                }
            });
            Search_Field_upd_std.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    Search_Field_upd_stdKeyReleased(evt);
                }
            });

            jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/clean.png"))); // NOI18N
            jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel7MouseClicked(evt);
                }
            });

            jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel115.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/FileSelect.png"))); // NOI18N
            jLabel115.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel115.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel115MouseClicked(evt);
                }
            });

            jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/uploadPict1.png"))); // NOI18N
            jLabel113.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel113.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel113MouseClicked(evt);
                }
            });

            BtnSavUpdt.setBackground(new java.awt.Color(51, 204, 0));
            BtnSavUpdt.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            BtnSavUpdt.setForeground(new java.awt.Color(255, 255, 255));
            BtnSavUpdt.setText("حفــــظ");
            BtnSavUpdt.setBorder(null);
            BtnSavUpdt.setContentAreaFilled(false);
            BtnSavUpdt.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            BtnSavUpdt.setEnabled(false);
            BtnSavUpdt.setOpaque(true);
            BtnSavUpdt.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    BtnSavUpdtActionPerformed(evt);
                }
            });

            jButton9.setBackground(new java.awt.Color(51, 204, 0));
            jButton9.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jButton9.setForeground(new java.awt.Color(255, 255, 255));
            jButton9.setText("طباعـــة البطاقــة");
            jButton9.setBorder(null);
            jButton9.setContentAreaFilled(false);
            jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton9.setOpaque(true);
            jButton9.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton9ActionPerformed(evt);
                }
            });

            jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel106.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/SearchRes.png"))); // NOI18N
            jLabel106.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel106.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel106MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
            jPanel25.setLayout(jPanel25Layout);
            jPanel25Layout.setHorizontalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addComponent(CountTabStdIn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(LabNamesForRes, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addGap(52, 52, 52)
                            .addComponent(BtnSavUpdt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14)
                            .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(54, 54, 54)
                            .addComponent(btnUpdRes, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(AncianRoom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel106)
                                    .addGap(8, 8, 8)
                                    .addComponent(Search_Field_upd_std, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap())
            );
            jPanel25Layout.setVerticalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Search_Field_upd_std, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel106, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AncianRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnSavUpdt, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabNamesForRes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CountTabStdIn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 87, Short.MAX_VALUE)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdRes, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );

            UpdateStd.add(jPanel25, java.awt.BorderLayout.WEST);

            PanToChckPatern.setBackground(new java.awt.Color(255, 255, 255));

            check_Std1_x.setBackground(new java.awt.Color(255, 255, 255));
            check_Std1_x.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_Std1_x.setText("طالب داخلي");
            check_Std1_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            check_Std1_x.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            check_Std1_x.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_Std1_x.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_Std1_xActionPerformed(evt);
                }
            });

            check_Prof1_x.setBackground(new java.awt.Color(255, 255, 255));
            check_Prof1_x.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_Prof1_x.setText("اســـتـاذ");
            check_Prof1_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            check_Prof1_x.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            check_Prof1_x.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_Prof1_x.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_Prof1_xActionPerformed(evt);
                }
            });

            check_StdExt1_x.setBackground(new java.awt.Color(255, 255, 255));
            check_StdExt1_x.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_StdExt1_x.setText("طالب خـــارجــي");
            check_StdExt1_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            check_StdExt1_x.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            check_StdExt1_x.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_StdExt1_x.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_StdExt1_xActionPerformed(evt);
                }
            });

            check_Emp1_x.setBackground(new java.awt.Color(255, 255, 255));
            check_Emp1_x.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_Emp1_x.setText("عـــامـــل");
            check_Emp1_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            check_Emp1_x.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            check_Emp1_x.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_Emp1_x.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_Emp1_xActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout PanToChckPaternLayout = new javax.swing.GroupLayout(PanToChckPatern);
            PanToChckPatern.setLayout(PanToChckPaternLayout);
            PanToChckPaternLayout.setHorizontalGroup(
                PanToChckPaternLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanToChckPaternLayout.createSequentialGroup()
                    .addContainerGap(689, Short.MAX_VALUE)
                    .addComponent(check_Emp1_x)
                    .addGap(45, 45, 45)
                    .addComponent(check_StdExt1_x)
                    .addGap(34, 34, 34)
                    .addComponent(check_Prof1_x)
                    .addGap(30, 30, 30)
                    .addComponent(check_Std1_x)
                    .addGap(27, 27, 27))
            );
            PanToChckPaternLayout.setVerticalGroup(
                PanToChckPaternLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanToChckPaternLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanToChckPaternLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(check_Std1_x)
                        .addComponent(check_Prof1_x)
                        .addComponent(check_StdExt1_x)
                        .addComponent(check_Emp1_x)))
            );

            UpdateStd.add(PanToChckPatern, java.awt.BorderLayout.NORTH);

            PanAllServiceStudent.add(UpdateStd, "card3");

            PanTakeRoom.setBackground(new java.awt.Color(255, 255, 255));
            PanTakeRoom.setForeground(new java.awt.Color(255, 255, 255));
            PanTakeRoom.setPreferredSize(new java.awt.Dimension(1180, 600));
            PanTakeRoom.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jButton13.setBackground(new java.awt.Color(0, 153, 153));
            jButton13.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jButton13.setForeground(new java.awt.Color(255, 255, 255));
            jButton13.setText("الــغــرف");
            jButton13.setContentAreaFilled(false);
            jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton13.setOpaque(true);
            jButton13.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton13ActionPerformed(evt);
                }
            });
            PanTakeRoom.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 90, 30));

            jPanel22.setBackground(new java.awt.Color(255, 255, 215));
            jPanel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            jPanel22.setForeground(new java.awt.Color(204, 0, 0));
            jPanel22.setEnabled(false);
            jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel107.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel107.setText("الاســـم و الـلــقــب  :");
            jPanel22.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 100, 35));

            jLabel111.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel111.setText("تاريخ ومكان الميلاد:");
            jPanel22.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 100, 35));

            LabNam.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            LabNam.setForeground(new java.awt.Color(204, 0, 0));
            LabNam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jPanel22.add(LabNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 70, 35));

            PlcBirth.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            PlcBirth.setForeground(new java.awt.Color(204, 0, 0));
            PlcBirth.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jPanel22.add(PlcBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 80, 35));

            NumCard.setBackground(new java.awt.Color(204, 255, 255));
            NumCard.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            NumCard.setForeground(new java.awt.Color(255, 0, 51));
            NumCard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            NumCard.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            NumCard.setOpaque(true);
            jPanel22.add(NumCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 1, 430, 30));

            jLabel1.setBackground(new java.awt.Color(204, 255, 255));
            jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel1.setText("N ° :");
            jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            jLabel1.setOpaque(true);
            jPanel22.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 42, 30));

            LabSurNam.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            LabSurNam.setForeground(new java.awt.Color(255, 51, 51));
            LabSurNam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jPanel22.add(LabSurNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 70, 35));

            jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel3.setText("بــ");
            jLabel3.setToolTipText("");
            jPanel22.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 30, 35));

            txtDatBirth.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            txtDatBirth.setForeground(new java.awt.Color(204, 0, 0));
            txtDatBirth.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jPanel22.add(txtDatBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 70, 35));

            NameRoom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            NameRoom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            jPanel22.add(NameRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 40, 30));

            jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel8.setText("الــغـرفة :");
            jLabel8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            jPanel22.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 50, 30));
            jPanel22.add(LabIDResident, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 90, 20));

            PanTakeRoom.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 470, 140));

            panCnt.setBackground(new java.awt.Color(255, 255, 255));
            /*
            */
            /*
            panCnt.setLayout(new javax.swing.BoxLayout(panCnt, javax.swing.BoxLayout.PAGE_AXIS));

            */
            panCnt.setLayout(new FlowLayout(10, 10, 10));

            PanTakeRoom.add(panCnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 170, 330));

            TabStdInternTotakeRoom.getTableHeader().setReorderingAllowed(false);
            TabStdInternTotakeRoom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            TabStdInternTotakeRoom.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            TabStdInternTotakeRoom.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "الحالة", "الغرقة", "النوع", "مكان الميلاد", "تاريخ الميلاد", "الـلــقــب", "الاسـم", "رقـم البطاقة", "ID"
                }
            ));
            TabStdInternTotakeRoom.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            TabStdInternTotakeRoom.setRowHeight(22);
            TabStdInternTotakeRoom.setSelectionBackground(new java.awt.Color(51, 204, 0));
            TabStdInternTotakeRoom.setSelectionForeground(new java.awt.Color(0, 0, 0));
            TabStdInternTotakeRoom.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    TabStdInternTotakeRoomMouseClicked(evt);
                }
            });
            TabStdInternTotakeRoom.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    TabStdInternTotakeRoomKeyPressed(evt);
                }
            });
            jScrollPane1.setViewportView(TabStdInternTotakeRoom);

            PanTakeRoom.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 470, 300));

            jTextField3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jTextField3.setForeground(new java.awt.Color(153, 153, 153));
            jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField3FocusGained(evt);
                }
            });
            jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    jTextField3KeyReleased(evt);
                }
            });
            PanTakeRoom.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 170, 30));

            check_Prof_TakeRom.setBackground(new java.awt.Color(255, 255, 255));
            check_Prof_TakeRom.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_Prof_TakeRom.setText("اســـتـاذ");
            check_Prof_TakeRom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 102, 255)));
            check_Prof_TakeRom.setBorderPainted(true);
            check_Prof_TakeRom.setBorderPaintedFlat(true);
            check_Prof_TakeRom.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            check_Prof_TakeRom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            check_Prof_TakeRom.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_Prof_TakeRom.setPreferredSize(new java.awt.Dimension(28, 28));
            check_Prof_TakeRom.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_Prof_TakeRomActionPerformed(evt);
                }
            });
            PanTakeRoom.add(check_Prof_TakeRom, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 120, 33));

            check_Std_TakeRom.setBackground(new java.awt.Color(255, 255, 255));
            check_Std_TakeRom.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_Std_TakeRom.setText("طالب داخلي");
            check_Std_TakeRom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 102, 255)));
            check_Std_TakeRom.setBorderPainted(true);
            check_Std_TakeRom.setBorderPaintedFlat(true);
            check_Std_TakeRom.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            check_Std_TakeRom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            check_Std_TakeRom.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_Std_TakeRom.setPreferredSize(new java.awt.Dimension(28, 28));
            check_Std_TakeRom.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_Std_TakeRomActionPerformed(evt);
                }
            });
            PanTakeRoom.add(check_Std_TakeRom, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 120, 33));

            jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel4.setText("الــغـرفة    :");
            jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanTakeRoom.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 410, 70, 30));

            NameRoomr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            NameRoomr.setForeground(new java.awt.Color(255, 0, 51));
            NameRoomr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            PanTakeRoom.add(NameRoomr, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, 40, 30));

            LabIDRoomN.setBackground(new java.awt.Color(255, 255, 255));
            LabIDRoomN.setForeground(new java.awt.Color(255, 255, 255));
            LabIDRoomN.setText("0");
            PanTakeRoom.add(LabIDRoomN, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 50, 30));

            TabStdInternTotakeRoom.getTableHeader().setReorderingAllowed(false);
            TabRoom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            TabRoom.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            TabRoom.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "الامـاكن المحـجـوزة", "عدد الاماكن", "الـغـرفة", "رقـم الغرفة"
                }
            ));
            TabRoom.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            TabRoom.setRowHeight(22);
            TabRoom.setSelectionBackground(new java.awt.Color(51, 204, 0));
            TabRoom.setSelectionForeground(new java.awt.Color(0, 0, 0));
            TabRoom.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    TabRoomMouseClicked(evt);
                }
            });
            TabRoom.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    TabRoomKeyPressed(evt);
                }
            });
            jScrollPane2.setViewportView(TabRoom);

            PanTakeRoom.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 300, 300));

            ChangeRoom.setBackground(new java.awt.Color(0, 153, 153));
            ChangeRoom.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            ChangeRoom.setForeground(new java.awt.Color(255, 255, 255));
            ChangeRoom.setText("تــغيـيــر");
            ChangeRoom.setToolTipText("");
            ChangeRoom.setActionCommand("تــغـيـيـرالغــرفـة");
            ChangeRoom.setContentAreaFilled(false);
            ChangeRoom.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            ChangeRoom.setDefaultCapable(false);
            ChangeRoom.setOpaque(true);
            ChangeRoom.setPreferredSize(new java.awt.Dimension(57, 36));
            ChangeRoom.setSelected(true);
            ChangeRoom.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ChangeRoomActionPerformed(evt);
                }
            });
            PanTakeRoom.add(ChangeRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 110, 30));

            BtnSaveStd2.setBackground(new java.awt.Color(51, 204, 0));
            BtnSaveStd2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            BtnSaveStd2.setForeground(new java.awt.Color(255, 255, 255));
            BtnSaveStd2.setText("الــغــاء");
            BtnSaveStd2.setToolTipText("");
            BtnSaveStd2.setContentAreaFilled(false);
            BtnSaveStd2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            BtnSaveStd2.setDefaultCapable(false);
            BtnSaveStd2.setOpaque(true);
            BtnSaveStd2.setPreferredSize(new java.awt.Dimension(57, 36));
            BtnSaveStd2.setSelected(true);
            BtnSaveStd2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    BtnSaveStd2ActionPerformed(evt);
                }
            });
            PanTakeRoom.add(BtnSaveStd2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 600, 110, 40));

            RserveRoom.setBackground(new java.awt.Color(0, 153, 153));
            RserveRoom.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            RserveRoom.setForeground(new java.awt.Color(255, 255, 255));
            RserveRoom.setText("حــجــــز");
            RserveRoom.setToolTipText("");
            RserveRoom.setContentAreaFilled(false);
            RserveRoom.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            RserveRoom.setDefaultCapable(false);
            RserveRoom.setOpaque(true);
            RserveRoom.setPreferredSize(new java.awt.Dimension(57, 36));
            RserveRoom.setSelected(true);
            RserveRoom.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RserveRoomActionPerformed(evt);
                }
            });
            PanTakeRoom.add(RserveRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 110, 30));

            SaveReservRoom.setBackground(new java.awt.Color(51, 204, 0));
            SaveReservRoom.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            SaveReservRoom.setForeground(new java.awt.Color(255, 255, 255));
            SaveReservRoom.setText("حـفـظ");
            SaveReservRoom.setToolTipText("");
            SaveReservRoom.setContentAreaFilled(false);
            SaveReservRoom.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            SaveReservRoom.setDefaultCapable(false);
            SaveReservRoom.setOpaque(true);
            SaveReservRoom.setPreferredSize(new java.awt.Dimension(57, 36));
            SaveReservRoom.setSelected(true);
            SaveReservRoom.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    SaveReservRoomMouseClicked(evt);
                }
            });
            SaveReservRoom.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    SaveReservRoomActionPerformed(evt);
                }
            });
            PanTakeRoom.add(SaveReservRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 600, 110, 40));

            panCnt1.setBackground(new java.awt.Color(255, 255, 215));
            panCnt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            /*

            */
            /*
            panCnt.setLayout(new javax.swing.BoxLayout(panCnt, javax.swing.BoxLayout.PAGE_AXIS));

            javax.swing.GroupLayout panCnt1Layout = new javax.swing.GroupLayout(panCnt1);
            panCnt1.setLayout(panCnt1Layout);
            panCnt1Layout.setHorizontalGroup(
                panCnt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 118, Short.MAX_VALUE)
            );
            panCnt1Layout.setVerticalGroup(
                panCnt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 148, Short.MAX_VALUE)
            );

            */
            panCnt.setLayout(new FlowLayout(10, 10, 10));

            PanTakeRoom.add(panCnt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 120, 150));

            jLabel6.setBackground(new java.awt.Color(255, 255, 215));
            jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel6.setText("الــطلبة الــمقـيمـيــن بالـغـرفــة ");
            jLabel6.setToolTipText("");
            jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jLabel6.setOpaque(true);
            PanTakeRoom.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 120, 30));

            IDRoomA.setBackground(new java.awt.Color(255, 255, 255));
            IDRoomA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            IDRoomA.setForeground(new java.awt.Color(255, 255, 255));
            PanTakeRoom.add(IDRoomA, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 540, 70, 30));

            paterntxt.setBackground(new java.awt.Color(255, 255, 255));
            paterntxt.setForeground(new java.awt.Color(255, 255, 255));
            PanTakeRoom.add(paterntxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 540, 80, 30));

            jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/print.png"))); // NOI18N
            jLabel24.setText("قائمـــة الطلبــة بالجناح");
            jLabel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel24MouseClicked(evt);
                }
            });
            PanTakeRoom.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 120, 30));

            lstPav.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            lstPav.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            PanTakeRoom.add(lstPav, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, 40, 30));

            jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel43.setForeground(new java.awt.Color(204, 204, 204));
            jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel43.setText("الـلـقـــب ");
            PanTakeRoom.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 50, 20));

            jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel50.setForeground(new java.awt.Color(204, 204, 204));
            jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel50.setText("رقم البطـاقة");
            PanTakeRoom.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 60, 70, 20));

            jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel51.setForeground(new java.awt.Color(204, 204, 204));
            jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel51.setText("الاســم ");
            PanTakeRoom.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 50, 20));

            jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel53.setForeground(new java.awt.Color(204, 204, 204));
            jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel53.setText("تاريخ الميلاد");
            PanTakeRoom.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 80, 20));

            jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/clean.png"))); // NOI18N
            jLabel54.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel54.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel54MouseClicked(evt);
                }
            });
            PanTakeRoom.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, -1, -1));

            jTextField9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            jTextField9.setForeground(java.awt.Color.blue);
            jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    jTextField9KeyReleased(evt);
                }
            });
            PanTakeRoom.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 90, 25));

            jTextField10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            jTextField10.setForeground(java.awt.Color.blue);
            jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    jTextField10KeyReleased(evt);
                }
            });
            PanTakeRoom.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 90, 25));

            jTextField11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            jTextField11.setForeground(new java.awt.Color(255, 0, 51));
            jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    jTextField11KeyReleased(evt);
                }
            });
            PanTakeRoom.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 70, 25));

            jTextField12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            jTextField12.setForeground(java.awt.Color.blue);
            jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    jTextField12KeyReleased(evt);
                }
            });
            PanTakeRoom.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, 80, 25));

            PanAllServiceStudent.add(PanTakeRoom, "card6");

            PanMenuAllStudent1.setBackground(new java.awt.Color(255, 255, 255));
            PanMenuAllStudent1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            PanMenuAllStudent1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            PanMenuAllStudent1.setPreferredSize(new java.awt.Dimension(1180, 600));
            PanMenuAllStudent1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            PanToChckPatern1.setBackground(new java.awt.Color(255, 255, 255));

            check_Std2_x.setBackground(new java.awt.Color(255, 255, 255));
            check_Std2_x.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_Std2_x.setText("طالب داخلي");
            check_Std2_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            check_Std2_x.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            check_Std2_x.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_Std2_x.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_Std2_xActionPerformed(evt);
                }
            });

            check_StdExt2_x.setBackground(new java.awt.Color(255, 255, 255));
            check_StdExt2_x.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_StdExt2_x.setText("طالب خـــارجــي");
            check_StdExt2_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            check_StdExt2_x.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            check_StdExt2_x.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_StdExt2_x.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_StdExt2_xActionPerformed(evt);
                }
            });

            check_Prof2_x.setBackground(new java.awt.Color(255, 255, 255));
            check_Prof2_x.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_Prof2_x.setText("اســـتـاذ");
            check_Prof2_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            check_Prof2_x.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            check_Prof2_x.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_Prof2_x.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_Prof2_xActionPerformed(evt);
                }
            });

            check_Emp2_x.setBackground(new java.awt.Color(255, 255, 255));
            check_Emp2_x.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            check_Emp2_x.setText("عـــامـــل");
            check_Emp2_x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            check_Emp2_x.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            check_Emp2_x.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            check_Emp2_x.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    check_Emp2_xActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout PanToChckPatern1Layout = new javax.swing.GroupLayout(PanToChckPatern1);
            PanToChckPatern1.setLayout(PanToChckPatern1Layout);
            PanToChckPatern1Layout.setHorizontalGroup(
                PanToChckPatern1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanToChckPatern1Layout.createSequentialGroup()
                    .addGap(94, 94, 94)
                    .addComponent(check_Emp2_x)
                    .addGap(89, 89, 89)
                    .addComponent(check_Prof2_x)
                    .addGap(97, 97, 97)
                    .addComponent(check_StdExt2_x)
                    .addGap(73, 73, 73)
                    .addComponent(check_Std2_x)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            PanToChckPatern1Layout.setVerticalGroup(
                PanToChckPatern1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanToChckPatern1Layout.createSequentialGroup()
                    .addGroup(PanToChckPatern1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(check_Emp2_x)
                        .addGroup(PanToChckPatern1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(check_Std2_x)
                            .addComponent(check_StdExt2_x))
                        .addComponent(check_Prof2_x))
                    .addGap(0, 25, Short.MAX_VALUE))
            );

            PanMenuAllStudent1.add(PanToChckPatern1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 700, 50));

            PanAllResidentToConsult.setBackground(new java.awt.Color(255, 255, 255));
            PanAllResidentToConsult.setLayout(new java.awt.CardLayout());

            panResidentToConsult.setBackground(new java.awt.Color(255, 255, 255));

            TabResidentToConsult.getTableHeader().setReorderingAllowed(false);
            TabResidentToConsult.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            TabResidentToConsult.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            TabResidentToConsult.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "الحالة", "الغرفة", "المستوى", "الشعبة", "العنوان", "الولاية", "الجنس", "مكان الميلاد", "تاريخ الميلاد", "اللقب", "الاسم", "الرقم"
                }
            ));
            TabResidentToConsult.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            TabResidentToConsult.setRowHeight(24);
            TabResidentToConsult.setSelectionBackground(new java.awt.Color(51, 204, 0));
            TabResidentToConsult.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    TabResidentToConsultMouseClicked(evt);
                }
            });
            jScrollPane7.setViewportView(TabResidentToConsult);

            javax.swing.GroupLayout panResidentToConsultLayout = new javax.swing.GroupLayout(panResidentToConsult);
            panResidentToConsult.setLayout(panResidentToConsultLayout);
            panResidentToConsultLayout.setHorizontalGroup(
                panResidentToConsultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panResidentToConsultLayout.createSequentialGroup()
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            panResidentToConsultLayout.setVerticalGroup(
                panResidentToConsultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            jScrollPane7.getViewport().setBackground(Color.white);

            PanAllResidentToConsult.add(panResidentToConsult, "card2");

            PanProfToConsult.setBackground(new java.awt.Color(255, 255, 255));

            TabProfToConsult.getTableHeader().setReorderingAllowed(false);
            TabProfToConsult.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            TabProfToConsult.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            TabProfToConsult.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "الحالة", "الغرفة", "المهنة", "الجنس", "مكان الميلاد", "تاريخ الميلاد", "اللقب", "الاسم ", "الرقم"
                }
            ));
            TabProfToConsult.setOpaque(false);
            TabProfToConsult.setRowHeight(24);
            TabProfToConsult.setSelectionBackground(new java.awt.Color(51, 204, 0));
            TabProfToConsult.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    TabProfToConsultMouseClicked(evt);
                }
            });
            jScrollPane8.setViewportView(TabProfToConsult);

            javax.swing.GroupLayout PanProfToConsultLayout = new javax.swing.GroupLayout(PanProfToConsult);
            PanProfToConsult.setLayout(PanProfToConsultLayout);
            PanProfToConsultLayout.setHorizontalGroup(
                PanProfToConsultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
            );
            PanProfToConsultLayout.setVerticalGroup(
                PanProfToConsultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
            );

            jScrollPane8.getViewport().setBackground(Color.WHITE);

            PanAllResidentToConsult.add(PanProfToConsult, "card3");

            PanEXtrStdToConsult.setBackground(new java.awt.Color(255, 255, 255));

            TabExtStdToConsult.getTableHeader().setReorderingAllowed(false);
            TabExtStdToConsult.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            TabExtStdToConsult.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            TabExtStdToConsult.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "الحـالة", "المستوى", "الـشعـبة", "الجنس", "مكان الميلاد", "تاريخ الميلاد", "اللقب", "الاسم", "الرقـم"
                }
            ));
            TabExtStdToConsult.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            TabExtStdToConsult.setOpaque(false);
            TabExtStdToConsult.setRowHeight(24);
            TabExtStdToConsult.setSelectionBackground(new java.awt.Color(51, 204, 0));
            TabExtStdToConsult.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    TabExtStdToConsultMouseClicked(evt);
                }
            });
            jScrollPane10.setViewportView(TabExtStdToConsult);

            javax.swing.GroupLayout PanEXtrStdToConsultLayout = new javax.swing.GroupLayout(PanEXtrStdToConsult);
            PanEXtrStdToConsult.setLayout(PanEXtrStdToConsultLayout);
            PanEXtrStdToConsultLayout.setHorizontalGroup(
                PanEXtrStdToConsultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
            );
            PanEXtrStdToConsultLayout.setVerticalGroup(
                PanEXtrStdToConsultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
            );

            jScrollPane10.getViewport().setBackground(Color.WHITE);

            PanAllResidentToConsult.add(PanEXtrStdToConsult, "card4");

            PanEmpToConsult.setBackground(new java.awt.Color(255, 255, 255));

            TabEmpToConsult.getTableHeader().setReorderingAllowed(false);
            TabEmpToConsult.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            TabEmpToConsult.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            TabEmpToConsult.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "الـحالة", "المهنة ", "الجنس", "مكان الميلاد", "تاريخ الميلاد", "اللقب", "الاسم ", "الرقم"
                }
            ));
            TabEmpToConsult.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            TabEmpToConsult.setOpaque(false);
            TabEmpToConsult.setRowHeight(24);
            TabEmpToConsult.setSelectionBackground(new java.awt.Color(51, 204, 0));
            TabEmpToConsult.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    TabEmpToConsultMouseClicked(evt);
                }
            });
            jScrollPane9.setViewportView(TabEmpToConsult);

            javax.swing.GroupLayout PanEmpToConsultLayout = new javax.swing.GroupLayout(PanEmpToConsult);
            PanEmpToConsult.setLayout(PanEmpToConsultLayout);
            PanEmpToConsultLayout.setHorizontalGroup(
                PanEmpToConsultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
            );
            PanEmpToConsultLayout.setVerticalGroup(
                PanEmpToConsultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
            );

            jScrollPane9.getViewport().setBackground(Color.WHITE);

            PanAllResidentToConsult.add(PanEmpToConsult, "card5");

            PanMenuAllStudent1.add(PanAllResidentToConsult, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 810, 330));

            LabTexTypCount.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            LabTexTypCount.setText("عـــدد الــطـلاب  :");
            PanMenuAllStudent1.add(LabTexTypCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 130, 30));

            LabCountTabConsult.setBackground(new java.awt.Color(255, 255, 255));
            LabCountTabConsult.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            LabCountTabConsult.setForeground(new java.awt.Color(255, 102, 102));
            LabCountTabConsult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            LabCountTabConsult.setText("2");
            LabCountTabConsult.setToolTipText("");
            LabCountTabConsult.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            LabCountTabConsult.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            LabCountTabConsult.setOpaque(true);
            PanMenuAllStudent1.add(LabCountTabConsult, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, 70, 30));

            jPanel8.setLayout(new java.awt.CardLayout());

            PanStdResident.setBackground(new java.awt.Color(255, 255, 230));
            PanStdResident.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            PanStdResident.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            txtBrchToPrint.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            txtBrchToPrint.setAutoscrolls(true);
            txtBrchToPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            txtBrchToPrint.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtBrchToPrintActionPerformed(evt);
                }
            });
            txtBrchToPrint.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtBrchToPrintKeyPressed(evt);
                }
            });
            PanStdResident.add(txtBrchToPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 260, 30));

            jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel28.setText("الـتـخـصـص :");
            jLabel28.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanStdResident.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 70, 30));

            jButton18.setBackground(new java.awt.Color(0, 204, 255));
            jButton18.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/print.png"))); // NOI18N
            jButton18.setText("قائمــة الطلبــة");
            jButton18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton18.setContentAreaFilled(false);
            jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton18.setOpaque(true);
            jButton18.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton18ActionPerformed(evt);
                }
            });
            PanStdResident.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 90, 30));

            PanStdResident.add(CaseN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 100, 30));

            jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel34.setText("الجنــاح :");
            jLabel34.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanStdResident.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, -1, 30));

            jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel62.setText("الحالة :");
            jLabel62.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanStdResident.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 70, 30));

            PanStdResident.add(CaseN2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 120, 30));

            jButton19.setBackground(new java.awt.Color(0, 204, 255));
            jButton19.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/print.png"))); // NOI18N
            jButton19.setText("قائمــة الطلبــة");
            jButton19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton19.setContentAreaFilled(false);
            jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton19.setOpaque(true);
            jButton19.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton19ActionPerformed(evt);
                }
            });
            PanStdResident.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

            PanStdResident.add(CaseN3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 70, 30));

            jLabel63.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel63.setText("الحالة :");
            jLabel63.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanStdResident.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 70, 30));

            PanStdResident.add(pavilion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 50, 30));

            jLabel66.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel66.setText("الحالة :");
            jLabel66.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanStdResident.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, 30));

            jButton20.setBackground(new java.awt.Color(0, 204, 255));
            jButton20.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/print.png"))); // NOI18N
            jButton20.setText("قائمــة الطلبــة");
            jButton20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton20.setContentAreaFilled(false);
            jButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton20.setOpaque(true);
            jButton20.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton20ActionPerformed(evt);
                }
            });
            PanStdResident.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 90, 30));

            PanStdResident.add(Branch4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 160, 30));

            jButton22.setBackground(new java.awt.Color(0, 204, 255));
            jButton22.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/print.png"))); // NOI18N
            jButton22.setText("قائمــة الطلبــة");
            jButton22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton22.setContentAreaFilled(false);
            jButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton22.setOpaque(true);
            jButton22.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton22ActionPerformed(evt);
                }
            });
            PanStdResident.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 90, 30));

            jButton21.setBackground(new java.awt.Color(0, 204, 255));
            jButton21.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/print.png"))); // NOI18N
            jButton21.setText("بيان تجهيزات الغرفة");
            jButton21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton21.setContentAreaFilled(false);
            jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton21.setOpaque(true);
            jButton21.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton21ActionPerformed(evt);
                }
            });
            PanStdResident.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 120, 30));

            PanStdResident.add(Pavlion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 120, 30));

            jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel35.setText("الجنــاح :");
            jLabel35.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanStdResident.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 40, 30));

            PanStdResident.add(Pavlion5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 70, 30));

            jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel37.setText("الجنــاح :");
            jLabel37.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanStdResident.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 70, 30));

            jButton15.setBackground(new java.awt.Color(0, 204, 255));
            jButton15.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
            jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/print.png"))); // NOI18N
            jButton15.setText("بيــان مراقبــة");
            jButton15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton15.setContentAreaFilled(false);
            jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton15.setOpaque(true);
            jButton15.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton15ActionPerformed(evt);
                }
            });
            PanStdResident.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 90, 30));

            PanStdResident.add(LisLvlStd, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 160, 30));

            jButton32.setBackground(new java.awt.Color(255, 255, 255));
            jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/print.png"))); // NOI18N
            jButton32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton32.setContentAreaFilled(false);
            jButton32.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton32.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton32ActionPerformed(evt);
                }
            });
            PanStdResident.add(jButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 35, 30));

            jPanel8.add(PanStdResident, "card2");

            PanStdEXtern.setBackground(new java.awt.Color(255, 255, 230));
            PanStdEXtern.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel201.setBackground(new java.awt.Color(0, 204, 255));
            jLabel201.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel201.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel201.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Printer-Ink-icon32.png"))); // NOI18N
            jLabel201.setText("قائمــــة الطلبـــة الخارجيين  ");
            jLabel201.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jLabel201.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel201.setOpaque(true);
            jLabel201.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel201MouseClicked(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jLabel201MouseExited(evt);
                }
            });
            PanStdEXtern.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 210, 30));

            jLabel202.setBackground(new java.awt.Color(0, 204, 255));
            jLabel202.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel202.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel202.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Printer-Ink-icon32.png"))); // NOI18N
            jLabel202.setText("قائمــــة العمـــــــــال   ");
            jLabel202.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jLabel202.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel202.setOpaque(true);
            jLabel202.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel202MouseClicked(evt);
                }
            });
            PanStdEXtern.add(jLabel202, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 210, 30));

            jLabel47.setBackground(new java.awt.Color(0, 204, 255));
            jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Printer-Ink-icon32.png"))); // NOI18N
            jLabel47.setText("قـــائمـــــــة الطلبـــة الأجــــانب ");
            jLabel47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jLabel47.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel47.setOpaque(true);
            jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel47MouseClicked(evt);
                }
            });
            PanStdEXtern.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 210, 30));

            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "رقم البطاقة", "N° "
                }
            ));
            jTable1.setRowHeight(22);
            jScrollPane3.setViewportView(jTable1);

            PanStdEXtern.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 130, 140));

            jTextField1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            PanStdEXtern.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 30));

            jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/AddCrd.png"))); // NOI18N
            jLabel56.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jLabel56.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel56.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel56MouseClicked(evt);
                }
            });
            PanStdEXtern.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 30, 30));

            jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/DelCrd.png"))); // NOI18N
            jLabel57.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jLabel57.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel57MouseClicked(evt);
                }
            });
            PanStdEXtern.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 30, 30));

            jLabel58.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel58.setText("طباعـــة");
            jLabel58.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jLabel58.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel58.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel58MouseClicked(evt);
                }
            });
            PanStdEXtern.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 100, 30));

            jLabel59.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel59.setText("سنة البكالوريا");
            PanStdEXtern.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 60, 30));
            PanStdEXtern.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 70, 30));

            jButton27.setBackground(new java.awt.Color(255, 255, 255));
            jButton27.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jButton27.setText("قائمة الطلبة");
            jButton27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton27.setContentAreaFilled(false);
            jButton27.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton27ActionPerformed(evt);
                }
            });
            PanStdEXtern.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 80, 30));

            CombExternNewOrNot.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            PanStdEXtern.add(CombExternNewOrNot, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 120, 30));

            jButton14.setBackground(new java.awt.Color(255, 255, 255));
            jButton14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jButton14.setText("قائمة الطلبة");
            jButton14.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton14ActionPerformed(evt);
                }
            });
            PanStdEXtern.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, 30));

            PanStdEXtern.add(BranchStd_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 210, 30));

            jPanel8.add(PanStdEXtern, "card3");

            PanMenuAllStudent1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 500, 700, 190));

            TxtType.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            TxtType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "الــطـلبة الــمقــيــمـيـن", "الــطـلبة الخــارجــيـيــن", "جــمـيـع الــطــلــبـــة", "الاســاتــذة", "الــعـــمــال" }));
            TxtType.setAutoscrolls(true);
            TxtType.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            TxtType.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    TxtTypeActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(TxtType, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 450, 200, 30));
            PanMenuAllStudent1.add(jLabel204, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 670, 70, 20));

            jLabel206.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel206.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel206.setText("الــــنـــوع  :");
            jLabel206.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanMenuAllStudent1.add(jLabel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 450, 70, 30));

            jButton6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jButton6.setText("طبــاعة البطـاقـة");
            jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton6.setContentAreaFilled(false);
            jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton6ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, 110, 34));

            jButton7.setBackground(new java.awt.Color(0, 153, 204));
            jButton7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jButton7.setForeground(new java.awt.Color(255, 255, 255));
            jButton7.setText("تبرئــة");
            jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton7.setContentAreaFilled(false);
            jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton7.setOpaque(true);
            jButton7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton7ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, 100, 34));

            jButton8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jButton8.setText("تبرئة جميع  الطلبة الساكنين");
            jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton8.setContentAreaFilled(false);
            jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton8ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 120, 34));

            jButton10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jButton10.setText("قرار منح السكـن");
            jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton10.setContentAreaFilled(false);
            jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton10ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, 100, 34));

            jButton12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jButton12.setText("اعـادة قبول الايواء");
            jButton12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton12.setContentAreaFilled(false);
            jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton12.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton12ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 580, 100, 34));

            jButton17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jButton17.setText("استمارة الطالب");
            jButton17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton17.setContentAreaFilled(false);
            jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton17.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton17ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 540, 110, 34));

            try {
                jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
            } catch (java.text.ParseException ex) {
                ex.printStackTrace();
            }
            jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            PanMenuAllStudent1.add(jFormattedTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 120, 34));

            jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
            jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel17.setText("تاريخ الاجراء قبل");
            PanMenuAllStudent1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 550, 70, 30));

            jButton11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
            jButton11.setText("قـائمة الطلبة المقيمين");
            jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton11.setContentAreaFilled(false);
            jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton11.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton11ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 660, 110, 30));

            jButton23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jButton23.setText("بيــان مراقبــة");
            jButton23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton23.setContentAreaFilled(false);
            jButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton23.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton23ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 580, 110, 34));

            jButton25.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jButton25.setText("بيان تجهبزات الغرفة");
            jButton25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton25.setContentAreaFilled(false);
            jButton25.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton25.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton25ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 120, 34));

            jLabel205.setBackground(new java.awt.Color(0, 153, 153));
            jLabel205.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            jLabel205.setForeground(new java.awt.Color(255, 255, 255));
            jLabel205.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel205.setText("اجـــراء بـدايـة و نــهــايــة الـسـنـة لـلــطـالــب");
            jLabel205.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel205.setOpaque(true);
            jLabel205.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel205MouseClicked(evt);
                }
            });
            PanMenuAllStudent1.add(jLabel205, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, 40));

            PanChangeCase.setBackground(new java.awt.Color(0, 190, 190));
            PanChangeCase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel207.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel207.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel207.setText("الاســـــم          :");
            jLabel207.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanChangeCase.add(jLabel207, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 90, 30));

            jLabel208.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel208.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel208.setText("الــلــقــب        :");
            jLabel208.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanChangeCase.add(jLabel208, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 90, 30));

            jLabel209.setBackground(new java.awt.Color(255, 255, 230));
            jLabel209.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel209.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel209.setText("الـحـالة الجـديـدة:");
            jLabel209.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            jLabel209.setOpaque(true);
            PanChangeCase.add(jLabel209, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 90, 29));

            NamToChangCase.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            NamToChangCase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            NamToChangCase.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            PanChangeCase.add(NamToChangCase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 130, 29));

            SurNamToChangCase.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            SurNamToChangCase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            SurNamToChangCase.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            PanChangeCase.add(SurNamToChangCase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 130, 28));

            CaseA.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            CaseA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            CaseA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            PanChangeCase.add(CaseA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 130, 30));

            PanChangeCase.add(CaseN, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 130, 29));

            jLabel210.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel210.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel210.setText("الـــحــالــة       :");
            jLabel210.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanChangeCase.add(jLabel210, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 90, 30));

            NumCardTochangCase.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            NumCardTochangCase.setForeground(new java.awt.Color(255, 51, 51));
            NumCardTochangCase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            NumCardTochangCase.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            PanChangeCase.add(NumCardTochangCase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, 28));

            jLabel211.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel211.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel211.setText("رقــم الــبــطــاقــة :");
            jLabel211.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
            PanChangeCase.add(jLabel211, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 90, 28));

            jLabel212.setBackground(new java.awt.Color(0, 204, 0));
            jLabel212.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel212.setForeground(new java.awt.Color(255, 255, 255));
            jLabel212.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel212.setText("حـــفــظ");
            jLabel212.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel212.setOpaque(true);
            jLabel212.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel212MouseClicked(evt);
                }
            });
            PanChangeCase.add(jLabel212, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 100, 31));

            jLabel213.setBackground(new java.awt.Color(0, 204, 0));
            jLabel213.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel213.setForeground(new java.awt.Color(255, 255, 255));
            jLabel213.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel213.setText("الـــغــاء");
            jLabel213.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel213.setOpaque(true);
            jLabel213.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel213MouseClicked(evt);
                }
            });
            PanChangeCase.add(jLabel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 100, 31));

            PanMenuAllStudent1.add(PanChangeCase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 240, 320));

            jLabel31.setBackground(new java.awt.Color(0, 153, 153));
            jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            jLabel31.setForeground(new java.awt.Color(255, 255, 255));
            jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel31.setText("حـــذف  طــالــب من قـاعـدة الـبيـانـات");
            jLabel31.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jLabel31.setOpaque(true);
            jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel31MouseClicked(evt);
                }
            });
            PanMenuAllStudent1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 240, 35));

            jPanel3.setBackground(new java.awt.Color(0, 190, 190));
            jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel21.setText("طالب خـــارجــي");
            jLabel21.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
            jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 80, 30));

            jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel22.setText("عـــامـــل");
            jLabel22.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
            jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 80, 30));

            jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel23.setText("مـــقـــيــــم");
            jLabel23.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
            jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 80, 30));

            jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            jLabel25.setForeground(new java.awt.Color(255, 255, 255));
            jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel25.setText("غيـر مجــــدد");
            jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 30));

            jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            jLabel26.setForeground(new java.awt.Color(255, 255, 255));
            jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel26.setText("خـــارجي غيـر مجــــدد");
            jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 30));

            jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            jLabel48.setForeground(new java.awt.Color(255, 255, 255));
            jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel48.setText("عـــامـــل غيـر مجــــدد ");
            jPanel3.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 120, 30));

            jLabel52.setBackground(new java.awt.Color(0, 204, 0));
            jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            jLabel52.setForeground(new java.awt.Color(255, 255, 255));
            jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel52.setText("الــــغـــاء");
            jLabel52.setOpaque(true);
            jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel52MouseClicked(evt);
                }
            });
            jPanel3.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 100, 30));

            SaveAllChangeCaseRes.setBackground(new java.awt.Color(0, 204, 0));
            SaveAllChangeCaseRes.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            SaveAllChangeCaseRes.setForeground(new java.awt.Color(255, 255, 255));
            SaveAllChangeCaseRes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            SaveAllChangeCaseRes.setText("حــــفـــظ");
            SaveAllChangeCaseRes.setOpaque(true);
            SaveAllChangeCaseRes.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    SaveAllChangeCaseResMouseClicked(evt);
                }
            });
            jPanel3.add(SaveAllChangeCaseRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 100, 30));

            PanMenuAllStudent1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 240, 220));

            LabChangeCaseAll.setBackground(new java.awt.Color(0, 153, 153));
            LabChangeCaseAll.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            LabChangeCaseAll.setForeground(new java.awt.Color(255, 255, 255));
            LabChangeCaseAll.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            LabChangeCaseAll.setText("تـغـيـيـر حالة جميع الساكنين أو المجددين");
            LabChangeCaseAll.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            LabChangeCaseAll.setOpaque(true);
            LabChangeCaseAll.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    LabChangeCaseAllMouseClicked(evt);
                }
            });
            PanMenuAllStudent1.add(LabChangeCaseAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 240, 40));

            Rad1.setBackground(new java.awt.Color(255, 255, 255));
            Rad1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Rad1.setForeground(java.awt.Color.blue);
            Rad1.setText("طـالب داخلي");
            Rad1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            Rad1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            Rad1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Rad1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rad1ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(Rad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 620, -1, 30));

            Rad4.setBackground(new java.awt.Color(255, 255, 255));
            Rad4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Rad4.setForeground(java.awt.Color.blue);
            Rad4.setText("عــامل");
            Rad4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            Rad4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            Rad4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Rad4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rad4ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(Rad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 620, 60, 30));

            Rad3.setBackground(new java.awt.Color(255, 255, 255));
            Rad3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Rad3.setForeground(java.awt.Color.blue);
            Rad3.setText("استــاذ");
            Rad3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            Rad3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            Rad3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Rad3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rad3ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(Rad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 620, 60, 30));

            Rad2.setBackground(new java.awt.Color(255, 255, 255));
            Rad2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Rad2.setForeground(java.awt.Color.blue);
            Rad2.setText("طـالب خارجـي");
            Rad2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            Rad2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            Rad2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Rad2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rad2ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(Rad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 620, 100, 30));

            jButton24.setBackground(new java.awt.Color(0, 153, 204));
            jButton24.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jButton24.setForeground(new java.awt.Color(255, 255, 255));
            jButton24.setText("طباعــة البطاقات");
            jButton24.setContentAreaFilled(false);
            jButton24.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton24.setOpaque(true);
            jButton24.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton24ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 110, 34));

            LabPrfResd_Std4.setBackground(new java.awt.Color(0, 204, 204));
            LabPrfResd_Std4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            LabPrfResd_Std4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            LabPrfResd_Std4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/preview-icon24.png"))); // NOI18N
            LabPrfResd_Std4.setText("معاينة مقررة الايواء");
            LabPrfResd_Std4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            LabPrfResd_Std4.setOpaque(true);
            LabPrfResd_Std4.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    LabPrfResd_Std4MouseClicked(evt);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    LabPrfResd_Std4MouseEntered(evt);
                }
            });
            PanMenuAllStudent1.add(LabPrfResd_Std4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 130, 40));

            NumCardToUpdt1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            NumCardToUpdt1.setForeground(java.awt.Color.blue);
            NumCardToUpdt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            NumCardToUpdt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            NumCardToUpdt1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    NumCardToUpdt1KeyReleased(evt);
                }
            });
            PanMenuAllStudent1.add(NumCardToUpdt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 68, 80, 30));

            NumCardToUpdt6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            NumCardToUpdt6.setForeground(java.awt.Color.blue);
            NumCardToUpdt6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            NumCardToUpdt6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            NumCardToUpdt6.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    NumCardToUpdt6KeyReleased(evt);
                }
            });
            PanMenuAllStudent1.add(NumCardToUpdt6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 68, 80, 30));

            NumCardToUpdt7.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            NumCardToUpdt7.setForeground(new java.awt.Color(255, 0, 0));
            NumCardToUpdt7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            NumCardToUpdt7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            NumCardToUpdt7.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    NumCardToUpdt7KeyReleased(evt);
                }
            });
            PanMenuAllStudent1.add(NumCardToUpdt7, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 68, 90, 30));

            NumCardToUpdt8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            NumCardToUpdt8.setForeground(java.awt.Color.blue);
            NumCardToUpdt8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            NumCardToUpdt8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            NumCardToUpdt8.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    NumCardToUpdt8KeyReleased(evt);
                }
            });
            PanMenuAllStudent1.add(NumCardToUpdt8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 68, 80, 30));

            jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel19.setForeground(new java.awt.Color(204, 204, 204));
            jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel19.setText("الاســم :");
            PanMenuAllStudent1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 68, 50, 30));

            jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel30.setForeground(new java.awt.Color(204, 204, 204));
            jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel30.setText("تاريخ الميلاد :");
            PanMenuAllStudent1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 68, 70, 30));

            jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel33.setForeground(new java.awt.Color(204, 204, 204));
            jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel33.setText("رقـم الـبطـاقـة :");
            PanMenuAllStudent1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 68, 80, 30));

            jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLabel36.setForeground(new java.awt.Color(204, 204, 204));
            jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel36.setText("الـلـقـــب :");
            PanMenuAllStudent1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 68, 40, 30));

            jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/clean.png"))); // NOI18N
            jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel38MouseClicked(evt);
                }
            });
            PanMenuAllStudent1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 20, 30));

            jButton16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jButton16.setText("طباعة اختيارية");
            jButton16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jButton16.setContentAreaFilled(false);
            jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton16.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton16ActionPerformed(evt);
                }
            });
            PanMenuAllStudent1.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, 110, 40));

            PanAllServiceStudent.add(PanMenuAllStudent1, "card5");

            SecondCentrePan.add(PanAllServiceStudent, "card2");

            PnIUpdtusr.setBackground(new java.awt.Color(255, 255, 255));
            PnIUpdtusr.setPreferredSize(new java.awt.Dimension(960, 700));
            PnIUpdtusr.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            Pnform2.setBackground(new java.awt.Color(255, 255, 255));
            Pnform2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            LbInsPrnom2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            LbInsPrnom2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            LbInsPrnom2.setText("الــلـقــب");
            LbInsPrnom2.setToolTipText("");
            Pnform2.add(LbInsPrnom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 75, 50, 20));

            TxtInsPrenomUsr.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
            TxtInsPrenomUsr.setForeground(new java.awt.Color(153, 153, 153));
            TxtInsPrenomUsr.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            TxtInsPrenomUsr.setText("ادخــل الاســـم");
            TxtInsPrenomUsr.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    TxtInsPrenomUsrFocusGained(evt);
                }
            });
            TxtInsPrenomUsr.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    TxtInsPrenomUsrKeyPressed(evt);
                }
            });
            Pnform2.add(TxtInsPrenomUsr, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 102, 170, 30));

            TxtInsIdentifiantUsr.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
            TxtInsIdentifiantUsr.setForeground(new java.awt.Color(204, 204, 204));
            TxtInsIdentifiantUsr.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            TxtInsIdentifiantUsr.setText("اســم المستخدم");
            TxtInsIdentifiantUsr.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    TxtInsIdentifiantUsrFocusGained(evt);
                }
            });
            TxtInsIdentifiantUsr.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    TxtInsIdentifiantUsrActionPerformed(evt);
                }
            });
            TxtInsIdentifiantUsr.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    TxtInsIdentifiantUsrKeyPressed(evt);
                }
            });
            Pnform2.add(TxtInsIdentifiantUsr, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 27, 368, 30));

            TxtInsNomusr.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
            TxtInsNomusr.setForeground(new java.awt.Color(153, 153, 153));
            TxtInsNomusr.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            TxtInsNomusr.setText("ادخــل الــلــقــب");
            TxtInsNomusr.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    TxtInsNomusrFocusGained(evt);
                }
            });
            TxtInsNomusr.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    TxtInsNomusrKeyPressed(evt);
                }
            });
            Pnform2.add(TxtInsNomusr, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 102, 180, 30));

            LbInsID2.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            LbInsID2.setText("اســم المستخدم");
            Pnform2.add(LbInsID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(337, 0, -1, 20));

            LbInsNom2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            LbInsNom2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            LbInsNom2.setText("الاســــم");
            Pnform2.add(LbInsNom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 75, 50, 20));

            LbInsPsw2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            LbInsPsw2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            LbInsPsw2.setText("كــلـمة الـمـرور");
            Pnform2.add(LbInsPsw2, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 139, 84, 18));
            Pnform2.add(Separator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 266, 320, -1));

            CheckSrvRoom.setBackground(new java.awt.Color(255, 255, 255));
            CheckSrvRoom.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            CheckSrvRoom.setText("خــدمـة الغـــرف");
            CheckSrvRoom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            CheckSrvRoom.setBorderPainted(true);
            CheckSrvRoom.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            CheckSrvRoom.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            CheckSrvRoom.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Pnform2.add(CheckSrvRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 176, 32));

            Lb_Droit_User2.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
            Lb_Droit_User2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Lb_Droit_User2.setText("صلاحيات الـمستـخـدم");
            Pnform2.add(Lb_Droit_User2, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 266, 150, 30));

            ChecAccessUsr.setBackground(new java.awt.Color(255, 255, 255));
            ChecAccessUsr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            ChecAccessUsr.setText("خـدمـات الدخـول للاقـــامة");
            ChecAccessUsr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            ChecAccessUsr.setBorderPainted(true);
            ChecAccessUsr.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            ChecAccessUsr.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            ChecAccessUsr.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Pnform2.add(ChecAccessUsr, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 353, 176, 30));

            CheckRestarUsr.setBackground(new java.awt.Color(255, 255, 255));
            CheckRestarUsr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            CheckRestarUsr.setText("خــدمـة الاطـعــــــام");
            CheckRestarUsr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            CheckRestarUsr.setBorderPainted(true);
            CheckRestarUsr.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            CheckRestarUsr.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            CheckRestarUsr.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Pnform2.add(CheckRestarUsr, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 353, 170, 30));

            CheckAdministrationusr.setBackground(new java.awt.Color(255, 255, 255));
            CheckAdministrationusr.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            CheckAdministrationusr.setText("صلاحيات مديــر النـظـام");
            CheckAdministrationusr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            CheckAdministrationusr.setBorderPainted(true);
            CheckAdministrationusr.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            CheckAdministrationusr.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            CheckAdministrationusr.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            CheckAdministrationusr.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    CheckAdministrationusrActionPerformed(evt);
                }
            });
            Pnform2.add(CheckAdministrationusr, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 401, 155, 30));

            CheckInscrRessevSrv.setBackground(new java.awt.Color(255, 255, 255));
            CheckInscrRessevSrv.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            CheckInscrRessevSrv.setText("خــدمـة الايــــواء");
            CheckInscrRessevSrv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            CheckInscrRessevSrv.setBorderPainted(true);
            CheckInscrRessevSrv.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            CheckInscrRessevSrv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            CheckInscrRessevSrv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            Pnform2.add(CheckInscrRessevSrv, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 170, 32));

            TxtInsPswUsr.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
            TxtInsPswUsr.setForeground(new java.awt.Color(204, 204, 204));
            TxtInsPswUsr.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            TxtInsPswUsr.setText("jPasswordField1");
            TxtInsPswUsr.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    TxtInsPswUsrFocusGained(evt);
                }
                public void focusLost(java.awt.event.FocusEvent evt) {
                    TxtInsPswUsrFocusLost(evt);
                }
            });
            TxtInsPswUsr.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    TxtInsPswUsrKeyPressed(evt);
                }
            });
            Pnform2.add(TxtInsPswUsr, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 164, 180, 30));

            TxtInsConPswUsr.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
            TxtInsConPswUsr.setForeground(new java.awt.Color(204, 204, 204));
            TxtInsConPswUsr.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            TxtInsConPswUsr.setText("jPasswordField1");
            TxtInsConPswUsr.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    TxtInsConPswUsrFocusGained(evt);
                }
            });
            TxtInsConPswUsr.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    TxtInsConPswUsrKeyPressed(evt);
                }
            });
            Pnform2.add(TxtInsConPswUsr, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 164, 170, 30));

            LbInsConfrm2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            LbInsConfrm2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            LbInsConfrm2.setText("اعـادة كــلـمة الـمـرور");
            Pnform2.add(LbInsConfrm2, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 139, 101, 18));

            jButton4.setBackground(new java.awt.Color(0, 102, 0));
            jButton4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jButton4.setForeground(new java.awt.Color(255, 255, 255));
            jButton4.setText("حـــفـظ");
            jButton4.setContentAreaFilled(false);
            jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton4.setOpaque(true);
            jButton4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton4ActionPerformed(evt);
                }
            });
            Pnform2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 100, 40));

            jButton5.setBackground(new java.awt.Color(0, 102, 0));
            jButton5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jButton5.setForeground(new java.awt.Color(255, 255, 255));
            jButton5.setText("الغـــــاء");
            jButton5.setContentAreaFilled(false);
            jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton5.setOpaque(true);
            jButton5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton5ActionPerformed(evt);
                }
            });
            Pnform2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 100, 40));

            PnIUpdtusr.add(Pnform2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 420, 500));

            jPanel10.setBackground(new java.awt.Color(255, 255, 255));
            jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

            TxtInsNomusr2.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
            TxtInsNomusr2.setForeground(new java.awt.Color(153, 153, 153));
            TxtInsNomusr2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            TxtInsNomusr2.setText("Search ...");
            TxtInsNomusr2.setBorder(null);
            TxtInsNomusr2.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    TxtInsNomusr2FocusGained(evt);
                }
            });
            TxtInsNomusr2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    TxtInsNomusr2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
            jPanel10.setLayout(jPanel10Layout);
            jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                    .addComponent(TxtInsNomusr2, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TxtInsNomusr2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            );

            PnIUpdtusr.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 240, 30));

            jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
            jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel15.setText("ادارة المــستخـدمـيـن");
            jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jLabel15.setOpaque(true);
            PnIUpdtusr.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 40));

            TabUserService.getTableHeader().setResizingAllowed(false);
            TabUserService.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            TabUserService.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "اسم المستخدم", "الاســـم", "اللقــب", "كلمـة المرور"
                }
            ));
            TabUserService.setDragEnabled(true);
            TabUserService.setRowHeight(24);
            TabUserService.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    TabUserServiceMouseClicked(evt);
                }
            });
            jScrollPane4.setViewportView(TabUserService);

            PnIUpdtusr.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 490, 310));

            jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel9.setText("عـــــــدد المستخـــــدميــــــــن");
            PnIUpdtusr.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 160, 20));

            jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
            jLabel16.setForeground(new java.awt.Color(255, 0, 0));
            jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            PnIUpdtusr.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 60, 20));

            jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jLabel20.setForeground(new java.awt.Color(255, 255, 255));
            PnIUpdtusr.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 414, 150, 20));

            Btn_Annul_UserToUPDATE21.setBackground(new java.awt.Color(0, 102, 0));
            Btn_Annul_UserToUPDATE21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            Btn_Annul_UserToUPDATE21.setForeground(new java.awt.Color(255, 255, 255));
            Btn_Annul_UserToUPDATE21.setText("تـــعــــديــــل");
            Btn_Annul_UserToUPDATE21.setContentAreaFilled(false);
            Btn_Annul_UserToUPDATE21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            Btn_Annul_UserToUPDATE21.setOpaque(true);
            Btn_Annul_UserToUPDATE21.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Btn_Annul_UserToUPDATE21ActionPerformed(evt);
                }
            });
            PnIUpdtusr.add(Btn_Annul_UserToUPDATE21, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 450, 120, 40));

            jButton1.setBackground(new java.awt.Color(0, 102, 0));
            jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jButton1.setForeground(new java.awt.Color(255, 255, 255));
            jButton1.setText("حــذف مـسـتــخــدم");
            jButton1.setContentAreaFilled(false);
            jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton1.setOpaque(true);
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
            PnIUpdtusr.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, 120, 40));

            jButton3.setBackground(new java.awt.Color(0, 102, 0));
            jButton3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
            jButton3.setForeground(new java.awt.Color(255, 255, 255));
            jButton3.setText("الغـــــاء");
            jButton3.setContentAreaFilled(false);
            jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jButton3.setOpaque(true);
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });
            PnIUpdtusr.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 100, 40));

            SecondCentrePan.add(PnIUpdtusr, "card5");

            getContentPane().add(SecondCentrePan, java.awt.BorderLayout.CENTER);

            setSize(new java.awt.Dimension(1241, 705));
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked

        System.exit(0);
    }//GEN-LAST:event_jLabel14MouseClicked

    public void ValidateNumber_Next() {
        NumOrder.setText("" + (Resident_GlRemplissage.GetMAX_ID_Resident()));

        LabPrfResd_Std3.setVisible(true);
        PrinRecWtoutSh.setVisible(true);

        switch (getPatternResident()) {
            case "طالب داخلي":

                PanPrfAfterSaisStd.setVisible(true);
                break;

            case "اســـتـاذ":
                PanPrfAfterSaisStd.setVisible(true);
                break;

            default:

                PanPrfAfterSaisStd.setVisible(true);
                LabPrfResd_Std3.setVisible(false);
                PrinRecWtoutSh.setVisible(false);
                break;
        }
    }

    public void InitialsedPhotoResident() {
        ImageResidentToSv = new File("D:\\Image_Signature\\imageRes.png");
        ImageIcon imgeicon = new ImageIcon(new ImageIcon(ImageResidentToSv.getAbsolutePath()).getImage().getScaledInstance(CLabImg.getWidth(), CLabImg.getHeight(), Image.SCALE_SMOOTH));
        CLabImg.setIcon(imgeicon);
    }

    public void ScanningPhotos(String NameImage) throws IOException {

        String pathFile = "D:\\Photo_residents\\";
        // String Path1="\\src\\residence\\Image\\ResidentIc.png";
        String FileName = "";

        File dir = new File(pathFile);
        File[] files = dir.listFiles();
        if (files.length != 0) {

            Path From = Paths.get(files[0].getAbsolutePath());
            Path To = Paths.get("D:\\FileNaming");

            String fileName = files[0].getName();
            String extension = "";

            int i = fileName.lastIndexOf('.');
            if (i > 0) {
                extension = fileName.substring(i);

            }
            Files.copy(From, To.resolve(fileName), REPLACE_EXISTING);

            files = null;
        }
    }
    Guest1 Gs;
    Restu R;
    int ValCont = 0;
    Conf F;
    DefaultTableModel mdlaTabStudent;
    int UpdateOrCancel = 1;
    ComfirmationUp_Sv comfirmationup_Sv;
    int Etat_Reserv = 0;
    int RowR;
    int RowS;
    UserService usrService;

    public void Etat_InisialejTxtFiled_User() {
        TxtInsIdentifiantUsr.setText("اســم المستخدم");
        TxtInsIdentifiantUsr.setForeground(Color.gray);
        TxtInsNomusr.setText("ادخــل الــلــقــب");
        TxtInsNomusr.setForeground(Color.gray);

        TxtInsPrenomUsr.setText("ادخــل الاســـم");
        TxtInsPrenomUsr.setForeground(Color.gray);
        /* 
        TxtInsPswUsr.setText("**********");
        TxtInsPswUsr.setForeground(Color.gray);
         */
        TxtInsConPswUsr.setText("**********");
        TxtInsConPswUsr.setForeground(Color.gray);

        CheckInscrRessevSrv.setSelected(false);
        CheckSrvRoom.setSelected(false);
        CheckRestarUsr.setSelected(false);
        ChecAccessUsr.setSelected(false);

        CheckAdministrationusr.setSelected(false);
    }

    public int GetValueService(JCheckBox service) {   // this Methode to Get Etat Of vALUE CHECK box if selected Rten 1 Else Rtrn 0;

        if (service.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }


    private void jLabel32MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseEntered
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Captions_Min_Hot.png"))); // NOI18N

    }//GEN-LAST:event_jLabel32MouseEntered

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Captions_Close_Hot.png"))); // NOI18N

    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Captions_Close.png")));
    }//GEN-LAST:event_jLabel14MouseExited

    private void jLabel32MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseExited
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/Captions_Min.png")));
    }//GEN-LAST:event_jLabel32MouseExited

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        this.setState(ICONIFIED);
        if (R.isVisible()) {
            R.setState(ICONIFIED);
        } else if (Gs.isVisible()) {
            Gs.setState(ICONIFIED);
        } else {
            F.setState(ICONIFIED);
        }
    }//GEN-LAST:event_jLabel32MouseClicked
    int RowSlectstd = -1;
    String Word = "";
    int RowRoomA;
    int RowPanCons = -1;

    public void PrintingCrd(JLabel lab) {

        if (lab.getText().equals("")) {
            messagerror = new MessageErrorControl(this, true, "لم يتم العثـــور علي رقــم البطــاقـة");
            messagerror.setVisible(true);
        } else {
            if (getPatternResident() == "عـــامـــل" || getPatternResident() == "اســـتـاذ") {
                // Employer_Remplissage.PrintCardEmpl_Prof(Integer.parseInt(lab.getText()), getPatternResident());
                if (getPatternResident() == "عـــامـــل") {
                    Employer_Remplissage.PrintCardEmpl_Prof(Integer.parseInt(lab.getText()), "بطاقـة مهنية");
                } else {
                    Employer_Remplissage.PrintCardEmpl_ProfOnly(Integer.parseInt(lab.getText()), "بـطاقـة مقيـــم");
                }
            }
            if (getPatternResident() == "طالب خـــارجــي") {
                ExternalstudentRemplissage.CardExternal(Integer.parseInt(lab.getText()));
            }
            if (getPatternResident() == "طالب داخلي") {
                student_ResRemplissage.TestCard(Integer.parseInt(lab.getText()));
            }
        }
    }
    String Idantifiant_usrUpdt = "";
    UserService UserServiceRemplissage = new UserService();
    int ValUpdateUsers;
    String valCaseChoice = "";
    int PanChange1 = 0;
    int PanChange2 = 0;
    int ValSuprime = 0;

    public void ChangUpdCas_NotNew() {
        Resident_GlRemplissage.UpdateAllResident();
        jPanel3.setVisible(false);
        PanChange2 = 0;
        ok = new Ok1(this, true, "لــقـد تــم تــغيـيـر جــيــع الــحــالات الــى غــيــر مـجــدد");
        ok.setVisible(true);
    }
    int ChRdPrint = 0;

    public void SelectResToPrt(JRadioButton rd1, JRadioButton rd2, JRadioButton rd3, JRadioButton rd4, int v) {
        if (rd1.isSelected()) {
            rd2.setSelected(false);
            rd3.setSelected(false);
            rd4.setSelected(false);
            ChRdPrint = v;
        }
    }

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }//GEN-LAST:event_jLabel49MouseClicked
    int i_Indice = 1;
    int xx, yy;
    private void timeLabelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_timeLabelFocusGained

    }//GEN-LAST:event_timeLabelFocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ValUpdateUsers = 0;
        Etat_InisialejTxtFiled_User();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setPatternResident("User");
        comfirmationup_Sv = new ComfirmationUp_Sv(this, true, "هل تر يـد حــذف الــمــســتـخدم");
        comfirmationup_Sv.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Btn_Annul_UserToUPDATE21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Annul_UserToUPDATE21ActionPerformed
        ValUpdateUsers = 1;
        //JOptionPane.showMessageDialog(null, " ValUpdateUsers : " + ValUpdateUsers);
        int Row = TabUserService.getSelectedRow();
        if (Row != -1) {
            Idantifiant_usrUpdt = (String) TabUserService.getValueAt(Row, 0);
            UserServiceRemplissage.GetInformation(Idantifiant_usrUpdt, TxtInsIdentifiantUsr, TxtInsPrenomUsr, TxtInsNomusr, TxtInsConPswUsr, CheckInscrRessevSrv,
                    CheckSrvRoom, CheckRestarUsr, ChecAccessUsr, CheckAdministrationusr);
        } else {
            messagerror = new MessageErrorControl(this, true, "الـــرجـــاء اخـــتــيــار الــمـســتـخــدم مــن الـجـــدول");
            messagerror.setVisible(true);
        }
    }//GEN-LAST:event_Btn_Annul_UserToUPDATE21ActionPerformed

    private void TabUserServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabUserServiceMouseClicked
        int Row = (int) TabUserService.getSelectedRow();
        jLabel20.setText((String) TabUserService.getValueAt(Row, 0));
    }//GEN-LAST:event_TabUserServiceMouseClicked

    private void TxtInsNomusr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtInsNomusr2ActionPerformed
        // TODO add your handling code here:
        TxtInsNomusr2.setText("");
        TxtInsNomusr2.setForeground(Color.black);
    }//GEN-LAST:event_TxtInsNomusr2ActionPerformed

    private void TxtInsNomusr2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtInsNomusr2FocusGained
        // TODO add your handling code here:
        TxtInsNomusr2.setText("");
        TxtInsNomusr2.setForeground(Color.black);
    }//GEN-LAST:event_TxtInsNomusr2FocusGained

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ValUpdateUsers = 0;
        Etat_InisialejTxtFiled_User();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (ValUpdateUsers == 1) {    //update
            //   JOptionPane.showMessageDialog(null, "in update operation");
            usrService = new UserService(TxtInsIdentifiantUsr.getText(), TxtInsPrenomUsr.getText(), TxtInsNomusr.getText(), TxtInsConPswUsr.getText(),
                    remplir_Inf_usr1(CheckInscrRessevSrv, CheckSrvRoom, CheckRestarUsr, ChecAccessUsr, CheckAdministrationusr));
            usrService.UpdateUsers(Idantifiant_usrUpdt);
            Etat_InisialejTxtFiled_User();
        } else {
            usrService = new UserService(TxtInsIdentifiantUsr.getText(), TxtInsPrenomUsr.getText(), TxtInsNomusr.getText(), TxtInsPswUsr.getText(),
                    remplir_Inf_usr1(CheckInscrRessevSrv, CheckSrvRoom, CheckRestarUsr, ChecAccessUsr, CheckAdministrationusr));
            usrService.InsertUser();

            Etat_InisialejTxtFiled_User();

            Object arg[] = {usrService.getIdantifiant(), usrService.getName(), usrService.getLastName(), usrService.getPassword()};
        }
        //  DmTabUserService.addRow(arg);
        userserviceRemplissage.DisplayUserIntable((DefaultTableModel) TabUserService.getModel());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void TxtInsConPswUsrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtInsConPswUsrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtInsPswUsr.requestFocus();
        }
        TxtInsPswUsr.setForeground(Color.black);
    }//GEN-LAST:event_TxtInsConPswUsrKeyPressed

    private void TxtInsConPswUsrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtInsConPswUsrFocusGained
        TxtInsConPswUsr.setText("");
        TxtInsConPswUsr.setForeground(Color.black);
    }//GEN-LAST:event_TxtInsConPswUsrFocusGained

    private void TxtInsPswUsrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtInsPswUsrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            CheckInscrRessevSrv.requestFocus();
        }
        CheckInscrRessevSrv.setForeground(Color.black);
    }//GEN-LAST:event_TxtInsPswUsrKeyPressed

    private void TxtInsPswUsrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtInsPswUsrFocusLost
        if (!TxtInsPswUsr.getText().equals(TxtInsConPswUsr.getText())) {
            messagerror = new MessageErrorControl(this, true, "خطــأ في تطـابق كلمـــة المــرور");
            messagerror.setVisible(true);
        }
    }//GEN-LAST:event_TxtInsPswUsrFocusLost

    private void TxtInsPswUsrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtInsPswUsrFocusGained
        TxtInsPswUsr.setText("");
        TxtInsPswUsr.setForeground(Color.black);
    }//GEN-LAST:event_TxtInsPswUsrFocusGained

    private void CheckAdministrationusrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckAdministrationusrActionPerformed
        // TODO add your handling code here:
        if (CheckAdministrationusr.isSelected()) {    //si check service Administrateur check tout les services

            CheckInscrRessevSrv.setSelected(true);
            CheckSrvRoom.setSelected(true);
            CheckRestarUsr.setSelected(true);
            ChecAccessUsr.setSelected(true);

        } else {
            CheckInscrRessevSrv.setSelected(false);
            CheckSrvRoom.setSelected(false);
            CheckRestarUsr.setSelected(false);
            ChecAccessUsr.setSelected(false);
        }
    }//GEN-LAST:event_CheckAdministrationusrActionPerformed

    private void TxtInsNomusrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtInsNomusrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtInsConPswUsr.requestFocus();
        }
    }//GEN-LAST:event_TxtInsNomusrKeyPressed

    private void TxtInsNomusrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtInsNomusrFocusGained
        if (TxtInsNomusr.getText().equals("ادخــل الــلــقــب")) {
            TxtInsNomusr.setText("");
        }
        TxtInsNomusr.setForeground(Color.blue);
    }//GEN-LAST:event_TxtInsNomusrFocusGained

    private void TxtInsIdentifiantUsrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtInsIdentifiantUsrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtInsPrenomUsr.requestFocus();
        }
    }//GEN-LAST:event_TxtInsIdentifiantUsrKeyPressed

    private void TxtInsIdentifiantUsrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtInsIdentifiantUsrActionPerformed

    }//GEN-LAST:event_TxtInsIdentifiantUsrActionPerformed

    private void TxtInsIdentifiantUsrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtInsIdentifiantUsrFocusGained
        if (TxtInsIdentifiantUsr.getText().equals("اســم المستخدم")) {
            TxtInsIdentifiantUsr.setText("");
        }
        TxtInsIdentifiantUsr.setForeground(Color.blue);
    }//GEN-LAST:event_TxtInsIdentifiantUsrFocusGained

    private void TxtInsPrenomUsrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtInsPrenomUsrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TxtInsNomusr.requestFocus();
        }
    }//GEN-LAST:event_TxtInsPrenomUsrKeyPressed

    private void TxtInsPrenomUsrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtInsPrenomUsrFocusGained
        if (TxtInsPrenomUsr.getText().equals("ادخــل الاســـم")) {
            TxtInsPrenomUsr.setText("");
        }
        TxtInsPrenomUsr.setForeground(Color.blue);
    }//GEN-LAST:event_TxtInsPrenomUsrFocusGained

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed

        DiselectPan(PanStdEXtern, PanStdResident);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
        Fill_Data.CleanTextFld(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6);
        cleanPanCangeCase();
        if (getPatternResident() == "طالب داخلي") {
            Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabResidentToConsult, (DefaultTableModel) TabResidentToConsult.getModel());
        } else {
            if (getPatternResident() == "طالب خـــارجــي") {
                Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabExtStdToConsult, (DefaultTableModel) TabExtStdToConsult.getModel());
            } else {
                if (getPatternResident() == "اســـتـاذ") {
                    Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabProfToConsult, (DefaultTableModel) TabProfToConsult.getModel());
                } else {
                    Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabEmpToConsult, (DefaultTableModel) TabEmpToConsult.getModel());
                }
            }
        }
    }//GEN-LAST:event_jLabel38MouseClicked

    private void NumCardToUpdt8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumCardToUpdt8KeyReleased
        if (getPatternResident() == "طالب داخلي") {
            Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabResidentToConsult, (DefaultTableModel) TabResidentToConsult.getModel());
        } else {
            if (getPatternResident() == "طالب خـــارجــي") {
                Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabExtStdToConsult, (DefaultTableModel) TabExtStdToConsult.getModel());
            } else {
                if (getPatternResident() == "اســـتـاذ") {
                    Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabProfToConsult, (DefaultTableModel) TabProfToConsult.getModel());
                } else {
                    Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabEmpToConsult, (DefaultTableModel) TabEmpToConsult.getModel());
                }
            }
        }
    }//GEN-LAST:event_NumCardToUpdt8KeyReleased

    private void NumCardToUpdt7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumCardToUpdt7KeyReleased
        // Fill_Data.FilterResidentMlt(NumCardToUpdt7,NumCardToUpdt1,NumCardToUpdt8,NumCardToUpdt6,TabResidentToConsult,(DefaultTableModel) TabResidentToConsult.getModel());  //filtrer dans le tableau fournisseur

        if (getPatternResident() == "طالب داخلي") {
            Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabResidentToConsult, (DefaultTableModel) TabResidentToConsult.getModel());
        } else {
            if (getPatternResident() == "طالب خـــارجــي") {
                Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabExtStdToConsult, (DefaultTableModel) TabExtStdToConsult.getModel());
            } else {
                if (getPatternResident() == "اســـتـاذ") {
                    Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabProfToConsult, (DefaultTableModel) TabProfToConsult.getModel());
                } else {
                    Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabEmpToConsult, (DefaultTableModel) TabEmpToConsult.getModel());
                }
            }
        }/*
        if (getPatternResident() == "طالب داخلي") {
            Fill_Data.FilterResidentMlt(NumCardToUpdt1, NumCardToUpdt7, NumCardToUpdt8, NumCardToUpdt6, TabResidentToConsult, (DefaultTableModel) TabResidentToConsult.getModel());
        } else {
            if (getPatternResident() == "طالب خـــارجــي") {
                Fill_Data.FilterResidentMlt(NumCardToUpdt1, NumCardToUpdt7, NumCardToUpdt8, NumCardToUpdt6, TabExtStdToConsult, (DefaultTableModel) TabExtStdToConsult.getModel());
            } else {
                if (getPatternResident() == "اســـتـاذ") {
                    Fill_Data.FilterResidentMlt(NumCardToUpdt1, NumCardToUpdt7, NumCardToUpdt8, NumCardToUpdt6, TabProfToConsult, (DefaultTableModel) TabProfToConsult.getModel());
                } else { Fill_Data.FilterResidentMlt(NumCardToUpdt1, NumCardToUpdt7, NumCardToUpdt8, NumCardToUpdt6, TabEmpToConsult, (DefaultTableModel)TabEmpToConsult.getModel());
                }
            }
        }*/
    }//GEN-LAST:event_NumCardToUpdt7KeyReleased

    private void NumCardToUpdt6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumCardToUpdt6KeyReleased
        if (getPatternResident() == "طالب داخلي") {
            Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabResidentToConsult, (DefaultTableModel) TabResidentToConsult.getModel());
        } else {
            if (getPatternResident() == "طالب خـــارجــي") {
                Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabExtStdToConsult, (DefaultTableModel) TabExtStdToConsult.getModel());
            } else {
                if (getPatternResident() == "اســـتـاذ") {
                    Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabProfToConsult, (DefaultTableModel) TabProfToConsult.getModel());
                } else {
                    Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabEmpToConsult, (DefaultTableModel) TabEmpToConsult.getModel());
                }
            }
        }
    }//GEN-LAST:event_NumCardToUpdt6KeyReleased

    private void NumCardToUpdt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumCardToUpdt1KeyReleased
        if (getPatternResident() == "طالب داخلي") {
            Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabResidentToConsult, (DefaultTableModel) TabResidentToConsult.getModel());
        } else {
            if (getPatternResident() == "طالب خـــارجــي") {
                Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabExtStdToConsult, (DefaultTableModel) TabExtStdToConsult.getModel());
            } else {
                if (getPatternResident() == "اســـتـاذ") {
                    Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabProfToConsult, (DefaultTableModel) TabProfToConsult.getModel());
                } else {
                    Fill_Data.FilterResidentMlt(NumCardToUpdt7, NumCardToUpdt1, NumCardToUpdt8, NumCardToUpdt6, TabEmpToConsult, (DefaultTableModel) TabEmpToConsult.getModel());
                }
            }
        }
    }//GEN-LAST:event_NumCardToUpdt1KeyReleased

    private void LabPrfResd_Std4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabPrfResd_Std4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_LabPrfResd_Std4MouseEntered

    private void LabPrfResd_Std4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabPrfResd_Std4MouseClicked
        SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
        if (TabResidentToConsult.getSelectedRow() != -1) {
            int Row = TabResidentToConsult.getSelectedRow();
            int NumCard = (int) TabResidentToConsult.getValueAt(Row, 11);
            String Nam = (String) TabResidentToConsult.getValueAt(Row, 10);
            String LastNam = (String) TabResidentToConsult.getValueAt(Row, 9);
            Date date = (Date) TabResidentToConsult.getValueAt(Row, 8);
            String place = (String) TabResidentToConsult.getValueAt(Row, 7);
            String Branche = (String) TabResidentToConsult.getValueAt(Row, 3);
            student_ResRemplissage.DisplayAccommdation_Rapporteur(Nam, LastNam,
                    NumCard, Format.format(date), place, Branche, "الاقـــامة الجامعية الحاجب", "0");

        } else {
            if (TabProfToConsult.getSelectedRow() != -1) {
                int Row = TabProfToConsult.getSelectedRow();
                int NumCard = (int) TabProfToConsult.getValueAt(Row, 8);
                String Nam = (String) TabProfToConsult.getValueAt(Row, 7);
                String LastNam = (String) TabProfToConsult.getValueAt(Row, 6);
                Date date = (Date) TabProfToConsult.getValueAt(Row, 5);
                String place = (String) TabProfToConsult.getValueAt(Row, 4);
                String Branche = "";
                student_ResRemplissage.DisplayAccommdation_Rapporteur(Nam, LastNam,
                        NumCard, Format.format(date), place, Branche, "الاقـــامة الجامعية الحاجب", "0");
            } else {
                messagerror = new MessageErrorControl(this, true, "الرجــاء اختـر المقيــــم من الجــدول");
                messagerror.setVisible(true);
            }
        }
    }//GEN-LAST:event_LabPrfResd_Std4MouseClicked

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        if (ChRdPrint == 0) {
            messagerror = new MessageErrorControl(this, true, "الرجاء اختر نوع المقيــــم ");
            messagerror.setVisible(true);
        } else {
            if (ChRdPrint == 1) {
                Employer_Remplissage.PrintEnsemble();
            } else if (ChRdPrint == 2) {
                ExternalstudentRemplissage.PrinExternalStdEns();
            } else if (ChRdPrint == 3) {
                professor_resRemplissage.PrintEnsemble(3);
            } else {
                professor_resRemplissage.PrintEnsemble(4);
            }
            //prf.PrintEnsemble();

        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void Rad2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rad2ActionPerformed
        SelectResToPrt(Rad2, Rad1, Rad3, Rad4, 2);
    }//GEN-LAST:event_Rad2ActionPerformed

    private void Rad3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rad3ActionPerformed
        SelectResToPrt(Rad3, Rad1, Rad2, Rad4, 3);
    }//GEN-LAST:event_Rad3ActionPerformed

    private void Rad4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rad4ActionPerformed
        SelectResToPrt(Rad4, Rad1, Rad3, Rad2, 4);
    }//GEN-LAST:event_Rad4ActionPerformed

    private void Rad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rad1ActionPerformed
        SelectResToPrt(Rad1, Rad2, Rad3, Rad4, 1);
    }//GEN-LAST:event_Rad1ActionPerformed

    private void LabChangeCaseAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabChangeCaseAllMouseClicked
        if (PanChange2 == 0) {
            jPanel3.setVisible(true);
            PanChangeCase.setVisible(false);
            PanChange1 = 0;
            PanChange2 = 1;
        } else {
            jPanel3.setVisible(false);
            PanChange2 = 0;
        }
    }//GEN-LAST:event_LabChangeCaseAllMouseClicked

    private void SaveAllChangeCaseResMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveAllChangeCaseResMouseClicked
        ValSuprime = 2;
        setPatternResident("User");
        comfirmationup_Sv = new ComfirmationUp_Sv(this, true, "هل تريد تــغيـيـر جــيــع الــحــالات الــى غــيــر مـجــدد ");
        comfirmationup_Sv.setVisible(true);
    }//GEN-LAST:event_SaveAllChangeCaseResMouseClicked

    private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseClicked
        PanChange2 = 0;
        jPanel3.setVisible(false);
    }//GEN-LAST:event_jLabel52MouseClicked

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        if (!(TabExtStdToConsult.getSelectedRow() != -1 || TabEmpToConsult.getSelectedRow() != -1 || TabProfToConsult.getSelectedRow() != -1
                || TabResidentToConsult.getSelectedRow() != -1) || NumCardTochangCase.getText().equals("")) {
            messagerror = new MessageErrorControl(this, true, "عـــلـيك الاخــتـيــار مــن الــجـــدول لـيــتـم الـحــذف");
            messagerror.setVisible(true);
        } else {
            ValSuprime = 1;
            setPatternResident("User");
            comfirmationup_Sv = new ComfirmationUp_Sv(this, true, "هل تريد الــحــذف");
            comfirmationup_Sv.setVisible(true);
        }
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel213MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel213MouseClicked
        cleanPanCangeCase();
        PanChangeCase.setVisible(false);
        PanChange1 = 0;
    }//GEN-LAST:event_jLabel213MouseClicked

    private void jLabel212MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel212MouseClicked
        // JOptionPane.showMessageDialog(null, "j click ");
        if (NumCardTochangCase.getText() != "") {
            // JOptionPane.showMessageDialog(null, "j suis dans if ");
            Resident_GlRemplissage.UpdateCase(Integer.parseInt(NumCardTochangCase.getText()), (String) CaseN.getSelectedItem());

            switch (getPatternResident()) {
                case "طالب داخلي":
                    student_ResRemplissage.DisplayAllResidentInTablToConsult(TabResidentToConsult, LabTexTypCount, "عــدد الطلبـــة المقيمين", LabCountTabConsult, getPatternResident());
                    break;
                case "طالب خـــارجــي":
                    student_ResRemplissage.DisplayAllResidentInTablToConsult(TabExtStdToConsult, LabTexTypCount, "عدد الطلبة الخارجيين  :", LabCountTabConsult, getPatternResident());
                    break;
                case "اســـتـاذ":
                    student_ResRemplissage.DisplayAllResidentInTablToConsult(TabProfToConsult, LabTexTypCount, "عــددالاسـاتذة المقيمين:", LabCountTabConsult, getPatternResident());
                    break;
                case "عـــامـــل":
                    student_ResRemplissage.DisplayAllResidentInTablToConsult(TabEmpToConsult, LabTexTypCount, "عدد العمال:", LabCountTabConsult, getPatternResident());
                    break;
            }
            cleanPanCangeCase();
            PanChangeCase.setVisible(false);
            PanChange1 = 0;
        } else {
            messagerror = new MessageErrorControl(this, true, "الــرجــاءا خــتـيــارالــطـالــب  مــن الـجــدول للـتـغـيـيـر ");
            messagerror.setVisible(true);
        }
    }//GEN-LAST:event_jLabel212MouseClicked

    private void jLabel205MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel205MouseClicked
        if (PanChange1 == 0) {
            PanChangeCase.setVisible(true);
            jPanel3.setVisible(false);
            PanChange2 = 0;
            PanChange1 = 1;
        } else {
            PanChangeCase.setVisible(false);
            PanChange1 = 0;
        }
    }//GEN-LAST:event_jLabel205MouseClicked

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed

        if (TabResidentToConsult.getSelectedRow() == -1) {
            messagerror = new MessageErrorControl(this, true, "الرجــاءاختيارالطــالب من الجــدول ");
            messagerror.setVisible(true);
        } else {
            student_ResRemplissage.ToolsRoomTotal_PvlOne(Integer.parseInt(NumCardTochangCase.getText()));
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed

        if (TabResidentToConsult.getSelectedRow() == -1) {
            messagerror = new MessageErrorControl(this, true, "الرجــاءاختيارالطــالب من الجــدول ");
            messagerror.setVisible(true);
        } else {
            student_ResRemplissage.ControleFichOne(Integer.parseInt(NumCardTochangCase.getText()));
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        student_ResRemplissage.ListStudentInterne();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        if (TabResidentToConsult.getSelectedRow() == -1) {
            messagerror = new MessageErrorControl(this, true, "الـــرجـــاء اخـــتــيــار الطالب مــن الـجـــدول");
            messagerror.setVisible(true);
        } else {
            student_ResRemplissage.FormStudentInf(Integer.parseInt(NumCardTochangCase.getText()));
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        if (TabResidentToConsult.getSelectedRow() == -1) {
            messagerror = new MessageErrorControl(this, true, "الرجــاءاختيارالطــالب من الجــدول ");
            messagerror.setVisible(true);
        } else {
            student_ResRemplissage.AcceptReapteResid(Integer.parseInt(NumCardTochangCase.getText()));
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // std.Decision_Housing(4198,"10/10/2009");
        if (TabResidentToConsult.getSelectedRow() == -1) {
            messagerror = new MessageErrorControl(this, true, "الرجــاءاختيارالطــالب من الجــدول ");
            messagerror.setVisible(true);
        } else {
            student_ResRemplissage.Decision_Housing(Integer.parseInt(NumCardTochangCase.getText()), jFormattedTextField1.getText());
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        student_ResRemplissage.Acquittal_ReportTotal("ســــاكن");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (NumCardTochangCase.getText().equals("")) {
            messagerror = new MessageErrorControl(this, true, "الرجــاء اختـر المقيــــم من الجــدول");
            messagerror.setVisible(true);
        } else {
            student_ResRemplissage.SetHomeMsg(this);
            student_ResRemplissage.Acquittal_Report(Integer.parseInt(NumCardTochangCase.getText()));
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        PrintingCrd(NumCardTochangCase);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void TxtTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTypeActionPerformed
        if (TxtType.getSelectedItem().equals("الــطـلبة الــمقــيــمـيـن")) {
            DiselectPan(PanStdResident, PanStdEXtern);
        } else {
            if (TxtType.getSelectedItem().equals("الــطـلبة الخــارجــيـيــن")) {

                DiselectPan(PanStdEXtern, PanStdResident);
                Fill_Data.Filling_Comb_NewExtrnalANDnot(CombExternNewOrNot);

                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                int year = cal.get(Calendar.YEAR);

                jSpinner1.setValue(year);
            } else if (TxtType.getSelectedItem().equals("الــعـــمــال")) {
                DiselectPan(PanStdEXtern, PanStdResident);
            }
        }
    }//GEN-LAST:event_TxtTypeActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        //ListStdBacYear
        int X;

        int AnnBac = (int) jSpinner1.getValue();
        if (BranchStd_year.getSelectedItem().equals("جميع التخصصات")) {
            student_ResRemplissage.ListStdBacYear(AnnBac);
        } else {
            student_ResRemplissage.ListStdBacYear(AnnBac, (String) BranchStd_year.getSelectedItem());
        }


    }//GEN-LAST:event_jButton27ActionPerformed

    private void jLabel58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseClicked

        DefaultTableModel dft = (DefaultTableModel) jTable1.getModel();
        if (dft.getRowCount() > 0) {
            String Query = Fill_Data.CreateQuery(jTable1);
            System.out.println("residence.Home1.jLabel51MouseClicked()" + Query);

            switch (getPatternResident()) {
                case "طالب داخلي":
                    String SS = "Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,Branch_Study.BranchStd_Name,CodeBare_Resident,imageStd,Room_Code FROM  Resident_Gl,Student_Res,Branch_Study,Room\n"
                            + "WHERE\n"
                            + "Resident_Gl.ID_Rsident=Student_Res.ID_Rsident \n"
                            + "AND Branch_Study.Id_BranchStd=Student_Res.Id_BranchStd\n"
                            + "AND Student_Res.Id_Room=Room.Id_Room\n"
                            + "AND NumCard_Resident IN" + Query;
                    Fill_Data.printSelectedCard(SS);
                    break;
                case "طالب خـــارجــي":
                    String SS2 = "Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,Branch_Study.BranchStd_Name,CodeBare_Resident,imageStd\n"
                            + "FROM  Resident_Gl,Student_ResExt,Branch_Study Where \n"
                            + "Resident_Gl.ID_Rsident=Student_ResExt.ID_Rsident\n"
                            + "AND Branch_Study.Id_BranchStd=Student_ResExt.Id_BranchStd\n"
                            + "AND  NumCard_Resident IN" + Query;
                    Fill_Data.printSelectedCardExterStd(SS2);
                    break;
                case "اســـتـاذ":
                    break;

                case "عـــامـــل":
                    String SS1 = "Select Name_Resident,LastName_Resident ,DateBirth,NumCard_Resident,PlaceBirth,CodeBare_Resident,imageStd,Profession\n"
                            + "FROM  Resident_Gl,Profession,Employer Where  \n"
                            + "Employer.ID_Rsident=Resident_Gl.ID_Rsident\n"
                            + "AND Employer.ID_Profession=Profession.ID_Profession\n"
                            + "AND NumCard_Resident IN " + Query;
                    Fill_Data.printSelectedCardEmployer(SS1);
                    break;

            }

            dft.setRowCount(0);
            i_Indice = 1;
            jTextField1.setText("");
        } else {
            messagerror = new MessageErrorControl(this, true, "لم يتم العتور علي البطاقات الرجاء اضافة البطاقة");
            messagerror.setVisible(true);
        }
    }//GEN-LAST:event_jLabel58MouseClicked

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked
        if (jTable1.getSelectedRow() == -1) {
            messagerror = new MessageErrorControl(this, true, "الـرجـاء اخـــتــيــار رقم البطاقة مــن الجدول");
            messagerror.setVisible(true);
        } else {
            DefaultTableModel dft = (DefaultTableModel) jTable1.getModel();
            int SlcRow = jTable1.getSelectedRow();
            dft.removeRow(SlcRow);
        }
    }//GEN-LAST:event_jLabel57MouseClicked

    private void jLabel56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel56MouseClicked
        //jTextField1
        if (!NumCardTochangCase.getText().equals("")) {
            DefaultTableModel dft = (DefaultTableModel) jTable1.getModel();
            Object arg[] = {jTextField1.getText(), i_Indice};
            dft.addRow(arg);
            i_Indice++;
        } else {
            messagerror = new MessageErrorControl(this, true, "الـــرجـــاء اخـــتــيــار الطالب مــن الـجـــدول");
            messagerror.setVisible(true);
        }
    }//GEN-LAST:event_jLabel56MouseClicked

    private void jLabel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseClicked
        Resident_GlRemplissage.ListEtrangerStd();
    }//GEN-LAST:event_jLabel47MouseClicked

    private void jLabel202MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel202MouseClicked
        Employer_Remplissage.ListEmployer();
    }//GEN-LAST:event_jLabel202MouseClicked

    private void jLabel201MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel201MouseClicked
        ExternalstudentRemplissage.ListExternalStudent();
    }//GEN-LAST:event_jLabel201MouseClicked

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        student_ResRemplissage.ControleFich((String) Pavlion5.getSelectedItem());
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        student_ResRemplissage.ToolsRoomTotal_Pvl((String) Pavlion4.getSelectedItem());
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // Branch4
        student_ResRemplissage.List_Branch_ofStd((String) Branch4.getSelectedItem());
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        student_ResRemplissage.ListStdPavilon_Case((String) pavilion2.getSelectedItem(), (String) CaseN3.getSelectedItem());
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        student_ResRemplissage.ListStd_Cas_Brch((String) CaseN1.getSelectedItem(), (String) txtBrchToPrint.getSelectedItem());

    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        student_ResRemplissage.ListStudentWithCase((String) CaseN2.getSelectedItem());
    }//GEN-LAST:event_jButton18ActionPerformed

    private void txtBrchToPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBrchToPrintKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBrchToPrintKeyPressed

    private void txtBrchToPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBrchToPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBrchToPrintActionPerformed

    private void TabEmpToConsultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabEmpToConsultMouseClicked
        RowPanCons = TabEmpToConsult.getSelectedRow();
        NumCardTochangCase.setText((int) TabEmpToConsult.getValueAt(RowPanCons, 7) + "");
        NamToChangCase.setText((String) TabEmpToConsult.getValueAt(RowPanCons, 6));
        SurNamToChangCase.setText((String) TabEmpToConsult.getValueAt(RowPanCons, 5));
        CaseA.setText((String) TabEmpToConsult.getValueAt(RowPanCons, 0));
        jTextField1.setText((int) TabEmpToConsult.getValueAt(RowPanCons, 7) + "");
    }//GEN-LAST:event_TabEmpToConsultMouseClicked

    private void TabExtStdToConsultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabExtStdToConsultMouseClicked
        RowPanCons = TabExtStdToConsult.getSelectedRow();
        NumCardTochangCase.setText((int) TabExtStdToConsult.getValueAt(RowPanCons, 8) + "");
        NamToChangCase.setText((String) TabExtStdToConsult.getValueAt(RowPanCons, 7));
        SurNamToChangCase.setText((String) TabExtStdToConsult.getValueAt(RowPanCons, 6));
        CaseA.setText((String) TabExtStdToConsult.getValueAt(RowPanCons, 0));
        jTextField1.setText((int) TabExtStdToConsult.getValueAt(RowPanCons, 8) + "");
    }//GEN-LAST:event_TabExtStdToConsultMouseClicked

    private void TabProfToConsultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabProfToConsultMouseClicked
        RowPanCons = TabProfToConsult.getSelectedRow();
        NumCardTochangCase.setText((int) TabProfToConsult.getValueAt(RowPanCons, 8) + "");
        NamToChangCase.setText((String) TabProfToConsult.getValueAt(RowPanCons, 7));
        SurNamToChangCase.setText((String) TabProfToConsult.getValueAt(RowPanCons, 6));
        CaseA.setText((String) TabProfToConsult.getValueAt(RowPanCons, 0));
        jTextField1.setText((int) TabProfToConsult.getValueAt(RowPanCons, 8) + "");
    }//GEN-LAST:event_TabProfToConsultMouseClicked

    private void TabResidentToConsultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabResidentToConsultMouseClicked

        RowPanCons = TabResidentToConsult.getSelectedRow();
        NumCardTochangCase.setText((int) TabResidentToConsult.getValueAt(RowPanCons, 11) + "");
        NamToChangCase.setText((String) TabResidentToConsult.getValueAt(RowPanCons, 10));
        SurNamToChangCase.setText((String) TabResidentToConsult.getValueAt(RowPanCons, 9));
        CaseA.setText((String) TabResidentToConsult.getValueAt(RowPanCons, 0));
        jTextField1.setText((int) TabResidentToConsult.getValueAt(RowPanCons, 11) + "");
    }//GEN-LAST:event_TabResidentToConsultMouseClicked

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        Fill_Data.FilterResidentMlt(jTextField9, jTextField10, jTextField11, jTextField12, TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());  //filtrer dans le tableau fournisseur
    }//GEN-LAST:event_jTextField12KeyReleased

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        Fill_Data.FilterResidentMlt(jTextField9, jTextField10, jTextField11, jTextField12, TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());  //filtrer dans le tableau fournisseur
    }//GEN-LAST:event_jTextField11KeyReleased

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
        Fill_Data.FilterResidentMlt(jTextField9, jTextField10, jTextField11, jTextField12, TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());  //filtrer dans le tableau fournisseur
    }//GEN-LAST:event_jTextField10KeyReleased

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
        Fill_Data.FilterResidentMlt(jTextField9, jTextField10, jTextField11, jTextField12, TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());  //filtrer dans le tableau fournisseur
    }//GEN-LAST:event_jTextField9KeyReleased

    private void jLabel54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel54MouseClicked
        Fill_Data.CleanTextFld(jTextField9, jTextField10, jTextField11, jTextField12);
        Fill_Data.FilterResidentMlt(jTextField9, jTextField10, jTextField11, jTextField12, TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());  //filtrer dans le tableau fournisseur
    }//GEN-LAST:event_jLabel54MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        student_ResRemplissage.ListStidentInPavilion((String) lstPav.getSelectedItem());
    }//GEN-LAST:event_jLabel24MouseClicked

    private void SaveReservRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveReservRoomActionPerformed
        String Patern = paterntxt.getText();
        int Control = 0;
        if (Etat_Reserv == 1) {
            //JOptionPane.showMessageDialog(null, "Etat 01"+TabRoom.getSelectedRow());
            if (TabRoom.getSelectedRow() >= 0 && NumCard.getText() != "") {
                //  JOptionPane.showMessageDialog(null, "Etat 01");
                RowS = (int) TabStdInternTotakeRoom.getSelectedRow();
                RowR = (int) TabRoom.getSelectedRow();
                int ID_Resident = Integer.parseInt(LabIDResident.getText());
                //   JOptionPane.showMessageDialog(null, "ID_Resident : "+ID_Resident+"  "+LabIDRoom.getText());
                int ID_Room = Integer.parseInt(LabIDRoomN.getText());
                String NamRom = (String) TabRoom.getValueAt(RowR, 2);
                //JOptionPane.showMessageDialog(null, "  "+ID_Resident +" "+ID_Room+" "+Patern);
                Resident_GlRemplissage.ReserveRoom(ID_Resident, ID_Room, Patern);//Faridch
                //  RserveRoom.setBackground(new Color(0,153,153));
                //JOptionPane.showMessageDialog(null, "fin");
                Control = 1;
            } else {
                if (TabRoom.getSelectedRow() < 0 && LabIDResident.getText() == "") {
                    messagerror = new MessageErrorControl(this, true, "الــرجــاء اخـتـيـار الــمـقـيـم و الــغــرفــة مــن الــجــدوليـن");
                    messagerror.setVisible(true);
                } else {
                    if (TabRoom.getSelectedRow() < 0) {
                        messagerror = new MessageErrorControl(this, true, "الــرجــاء اخـتـيـار  الــغــرفــة مــن الــجــدول");
                        messagerror.setVisible(true);
                    } else {
                        messagerror = new MessageErrorControl(this, true, "الــرجــاء اخـتـيـارالــمــقـيـم  مــن الــجــدول");
                        messagerror.setVisible(true);

                    }
                }
            }
        } else {
            if (Etat_Reserv == 2) {  //cahnge Room
                //JOptionPane.showMessageDialog(null, "Etat 02");
                if (TabRoom.getSelectedRow() >= 0 && NumCard.getText() != "") {
                    //  JOptionPane.showMessageDialog(null, "table is selected");
                    RowS = (int) TabStdInternTotakeRoom.getSelectedRow();
                    RowR = (int) TabRoom.getSelectedRow();
                    int ID_Resident = Integer.parseInt(LabIDResident.getText());
                    //   JOptionPane.showMessageDialog(null, "ID_Resident : "+ID_Resident+"  "+LabIDRoom.getText());
                    int ID_RoomA = Integer.parseInt(IDRoomA.getText());/////////////////////////////
                    int ID_RoomN = Integer.parseInt(LabIDRoomN.getText());
                    String NamRomN = NameRoomr.getText();
                    String NamRomA = NameRoom.getText();

                    //  JOptionPane.showMessageDialog(null, "ID_Resident, :"+ID_Resident+" ID_RoomA, :"+ID_RoomA+" ID_RoomN, :"+ID_RoomN+ "PatternResident"+ Patern);
                    Resident_GlRemplissage.ChangeRoom(ID_Resident, ID_RoomA, ID_RoomN, Patern);
                    //   JOptionPane.showMessageDialog(null," I change Room");
                    Control = 1;
                } else {
                    if (TabRoom.getSelectedRow() < 0 && LabIDResident.getText() == "") {
                        messagerror = new MessageErrorControl(this, true, "الــرجــاء اخـتـيـار الــمـقـيـم و الــغــرفــة مــن الــجــدوليـن");
                        messagerror.setVisible(true);
                    } else {
                        if (TabRoom.getSelectedRow() < 0) {
                            messagerror = new MessageErrorControl(this, true, "الــرجــاء اخـتـيـار  الــغــرفــة مــن الــجــدول");
                            messagerror.setVisible(true);
                        } else {
                            messagerror = new MessageErrorControl(this, true, "الــرجــاء اخـتـيـار  الــمــقـيـم مــن الــجــدول");
                            messagerror.setVisible(true);
                        }
                    }
                }
            } else {
                messagerror = new MessageErrorControl(this, true, "الــرجــاءالــضــغـط عــلـى حــجــز او تــغــيـيــر لـيـتـم الحــفــظ");
                //  MessageErrorControl m=newMessageErrorControl(this, true,);
                messagerror.setVisible(true);
            }
        }
        if (Control == 1) {
            TabRoom.getSelectionModel().clearSelection();
            TabStdInternTotakeRoom.getSelectionModel().clearSelection();
            LabIDResident.setText("");
            NumCard.setText("");
            NameRoom.setText("");
            LabNam.setText("");
            LabSurNam.setText("");
            txtDatBirth.setText("");
            PlcBirth.setText("");
            paterntxt.setText("");
            NameRoomr.setText("");
            LabIDRoomN.setText("");
            RserveRoom.setBackground(new Color(0, 153, 153));
            ChangeRoom.setBackground(new Color(0, 153, 153));
            student_ResRemplissage.FilterResident("", TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());
            student_ResRemplissage.FilterResident("", TabRoom, (DefaultTableModel) TabRoom.getModel());
            Resident_GlRemplissage.DisplayTabToTakeRoom(TabStdInternTotakeRoom, Etat_Reserv);
            RoomRemplissage.DisplayTabRoom(TabRoom);
            TabStdInternTotakeRoom.getSelectionModel().clearSelection();
            Etat_Reserv = 0;
            DefaultTableModel dm = (DefaultTableModel) TabRoom.getModel();
            dm.removeRow((TabRoom.getRowCount() - 1));

            panCnt1.removeAll();
            panCnt1.revalidate();
            panCnt1.repaint();
        }
    }//GEN-LAST:event_SaveReservRoomActionPerformed

    private void SaveReservRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveReservRoomMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SaveReservRoomMouseClicked

    private void RserveRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RserveRoomActionPerformed

        TabStdInternTotakeRoom.getSelectionModel().clearSelection();
        TabRoom.getSelectionModel().clearSelection();
        LabIDResident.setText("");
        NumCard.setText("");
        NameRoom.setText("");
        LabNam.setText("");
        LabSurNam.setText("");
        txtDatBirth.setText("");
        PlcBirth.setText("");
        paterntxt.setText("");
        NameRoomr.setText("");
        LabIDRoomN.setText("");
        Resident_GlRemplissage.FilterResident("", TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());
        //RowSlectstd=-1;
        Etat_Reserv = 1;  //faridch
        RserveRoom.setBackground(new Color(204, 0, 204));
        ChangeRoom.setBackground(new Color(0, 153, 153));
        Resident_GlRemplissage.DisplayTabToTakeRoom(TabStdInternTotakeRoom, Etat_Reserv);
        panCnt1.removeAll();
        panCnt1.revalidate();
        panCnt1.repaint();
    }//GEN-LAST:event_RserveRoomActionPerformed

    private void BtnSaveStd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSaveStd2ActionPerformed
        Etat_Reserv = 0;
        TabStdInternTotakeRoom.getSelectionModel().clearSelection();
        TabRoom.getSelectionModel().clearSelection();
        LabIDResident.setText("");
        NumCard.setText("");
        NameRoom.setText("");
        LabNam.setText("");
        LabSurNam.setText("");
        txtDatBirth.setText("");
        PlcBirth.setText("");
        paterntxt.setText("");
        NameRoomr.setText("");
        LabIDRoomN.setText("");
        Resident_GlRemplissage.FilterResident("", TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());
        student_ResRemplissage.FilterResident("", TabRoom, (DefaultTableModel) TabRoom.getModel());
        ChangeRoom.setBackground(new Color(0, 153, 153));
        RserveRoom.setBackground(new Color(0, 153, 153));
        panCnt1.removeAll();
        panCnt1.revalidate();
        panCnt1.repaint();
    }//GEN-LAST:event_BtnSaveStd2ActionPerformed

    private void ChangeRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeRoomActionPerformed
        panCnt1.removeAll();
        panCnt1.revalidate();
        panCnt1.repaint();

        TabRoom.getSelectionModel().clearSelection();
        TabStdInternTotakeRoom.getSelectionModel().clearSelection();

        LabIDResident.setText("");
        NumCard.setText("");
        NameRoom.setText("");
        LabNam.setText("");
        LabSurNam.setText("");
        txtDatBirth.setText("");
        PlcBirth.setText("");
        paterntxt.setText("");
        NameRoomr.setText("");
        LabIDRoomN.setText("");
        Resident_GlRemplissage.FilterResident("", TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());
        //RowSlectstd=-1;
        Etat_Reserv = 2;//faridch
        ChangeRoom.setBackground(new Color(204, 0, 204));
        RserveRoom.setBackground(new Color(0, 153, 153));
        //    Resident_GlRemplissage.DisplayTabToTakeRoom(TabStdInternTotakeRoom);
        Resident_GlRemplissage.DisplayTabToTakeRoom(TabStdInternTotakeRoom, Etat_Reserv);
        // Resident_GlRemplissage.NotFilterResident("/", TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());
        //  Resident_GlRemplissage.NotFilterResident("/", TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());
        Word = "/";
    }//GEN-LAST:event_ChangeRoomActionPerformed

    private void TabRoomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabRoomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            RowR = TabRoom.getSelectedRow();
            NameRoomr.setText((String) TabRoom.getValueAt(RowR, 2));
            LabIDResident.setText((int) (TabRoom.getValueAt(RowR, 3)) + "");
        }
    }//GEN-LAST:event_TabRoomKeyPressed

    private void TabRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRoomMouseClicked
        int Row = TabRoom.getSelectedRow();
        //  JOptionPane.showMessageDialog(null, " "+Etat_Reserv);

        Resident_GlRemplissage.DisplayTabToTakeRoom(TabStdInternTotakeRoom, 0);
        Filling_Name(panCnt1, Row);  //farid ch

        if (Etat_Reserv == 2) {
            Resident_GlRemplissage.FilterResident("", TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());
            Resident_GlRemplissage.DisplayTabToTakeRoom(TabStdInternTotakeRoom, Etat_Reserv);
        } else {
            if (Etat_Reserv == 1) {
                Resident_GlRemplissage.FilterResident("", TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());
                Resident_GlRemplissage.DisplayTabToTakeRoom(TabStdInternTotakeRoom, Etat_Reserv);
            } else {
                Resident_GlRemplissage.FilterResident("", TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());
            }
            Resident_GlRemplissage.DisplayTabToTakeRoom(TabStdInternTotakeRoom, Etat_Reserv);

        }
        //  JOptionPane.showMessageDialog(null, "     Fin ");
        // Resident_GlRemplissage.DisplayTabToTakeRoom(TabStdInternTotakeRoom,Etat_Reserv);
        NameRoomr.setText((String) TabRoom.getValueAt(Row, 2));
        LabIDRoomN.setText((int) (TabRoom.getValueAt(Row, 3)) + "");
        //  if(RowSlectstd!=-1)  TabStdInternTotakeRoom.setRowSelectionInterval(RowSlectstd, RowSlectstd);
    }//GEN-LAST:event_TabRoomMouseClicked

    private void check_Std_TakeRomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_Std_TakeRomActionPerformed
        if (check_Std_TakeRom.isSelected() == true) {
            check_Std_TakeRom.setSelected(true);
            check_Std_TakeRom.setBackground(new Color(0, 153, 0));
            check_Prof_TakeRom.setSelected(false);
            check_Prof_TakeRom.setBackground(new Color(255, 255, 255));
            PatternToTakeRoom = check_Std_TakeRom.getText();
            student_ResRemplissage.DisplayTabToTakeRoomStd(TabStdInternTotakeRoom);
        } else {
            check_Std_TakeRom.setBackground(Color.WHITE);
            if (!check_Std_TakeRom.isSelected() && !check_Prof_TakeRom.isSelected()) {
                Resident_GlRemplissage.DisplayTabToTakeRoom(TabStdInternTotakeRoom, Etat_Reserv);
            }

        }
    }//GEN-LAST:event_check_Std_TakeRomActionPerformed

    private void check_Prof_TakeRomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_Prof_TakeRomActionPerformed
        if (check_Prof_TakeRom.isSelected() == true) {
            check_Prof_TakeRom.setSelected(true);
            check_Prof_TakeRom.setBackground(new Color(0, 153, 0));
            check_Std_TakeRom.setSelected(false);
            check_Std_TakeRom.setBackground(new Color(255, 255, 255));
            PatternToTakeRoom = check_Prof_TakeRom.getText();
            professor_resRemplissage.DisplayTabToTakeRoomProf(TabStdInternTotakeRoom);
        } else {
            check_Prof_TakeRom.setBackground(Color.WHITE);
            if (!check_Std_TakeRom.isSelected() && !check_Prof_TakeRom.isSelected()) {
                Resident_GlRemplissage.DisplayTabToTakeRoom(TabStdInternTotakeRoom, Etat_Reserv);
            }

        }
    }//GEN-LAST:event_check_Prof_TakeRomActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        student_ResRemplissage.FilterResident(jTextField3.getText(), TabRoom, (DefaultTableModel) TabRoom.getModel());
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained
        jTextField3.setText("");
        jTextField3.setForeground(new Color(255, 0, 51));
    }//GEN-LAST:event_jTextField3FocusGained

    private void TabStdInternTotakeRoomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabStdInternTotakeRoomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
            RowS = TabStdInternTotakeRoom.getSelectedRow();
            LabNam.setText((String) TabStdInternTotakeRoom.getValueAt(RowS, 6));
            LabSurNam.setText((String) TabStdInternTotakeRoom.getValueAt(RowS, 5));
            Date date = (Date) TabStdInternTotakeRoom.getValueAt(RowS, 4);
            txtDatBirth.setText(form.format(date));
            PlcBirth.setText((String) TabStdInternTotakeRoom.getValueAt(RowS, 3));
            NumCard.setText((int) TabStdInternTotakeRoom.getValueAt(RowS, 7) + "");
            LabIDResident.setText((int) TabStdInternTotakeRoom.getValueAt(RowS, 7) + "");
            NameRoom.setText((String) TabStdInternTotakeRoom.getValueAt(RowS, 1));

        }
    }//GEN-LAST:event_TabStdInternTotakeRoomKeyPressed

    private void TabStdInternTotakeRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabStdInternTotakeRoomMouseClicked

        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        int Row = TabStdInternTotakeRoom.getSelectedRow();   //Faridch
        // if(Etat_Reserv==1||Etat_Reserv==2) RowSlectstd=Row;
        RowS = TabStdInternTotakeRoom.getSelectedRow();
        //JOptionPane.showMessageDialog(null, " Tab STd "+Row+" ");
        LabNam.setText((String) TabStdInternTotakeRoom.getValueAt(Row, 6));
        LabSurNam.setText((String) TabStdInternTotakeRoom.getValueAt(Row, 5));
        Date date = (Date) TabStdInternTotakeRoom.getValueAt(Row, 4);
        txtDatBirth.setText(form.format(date));
        PlcBirth.setText((String) TabStdInternTotakeRoom.getValueAt(Row, 3));
        NumCard.setText((int) TabStdInternTotakeRoom.getValueAt(Row, 7) + "");
        LabIDResident.setText((int) TabStdInternTotakeRoom.getValueAt(Row, 8) + "");
        NameRoom.setText((String) TabStdInternTotakeRoom.getValueAt(Row, 1));
        paterntxt.setText((String) TabStdInternTotakeRoom.getValueAt(Row, 2));//faridch
        if (Etat_Reserv == 2) {
            RowRoomA = RoomRemplissage.Get_ID_Room(NameRoom.getText());
            IDRoomA.setText(RowRoomA + "");
        }
        //RowSlectstd=TabStdInternTotakeRoom.getSelectedRow();
    }//GEN-LAST:event_TabStdInternTotakeRoomMouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        student_ResRemplissage.FilterResident("", TabRoom, (DefaultTableModel) TabRoom.getModel());
        RoomRemplissage.DisplayTabRoom(TabRoom);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (getPatternResident() == "عـــامـــل" || getPatternResident() == "اســـتـاذ") {
            if (getPatternResident() == "عـــامـــل") {
                Employer_Remplissage.PrintCardEmpl_Prof(Integer.parseInt(NumCardToUpdt4.getText()), "بطاقـة عــامل");
            } else {
                //NumCardToUpdt2
                Employer_Remplissage.PrintCardEmpl_ProfOnly(Integer.parseInt(NumCardToUpdt2.getText()), "بـطاقـة مقيـم");
            }
        } else {
            if (getPatternResident() == "طالب خـــارجــي") {
                ExternalstudentRemplissage.CardExternal(Integer.parseInt(NumCardToUpdt3.getText()));

            } else {
                if (NumCardToUpdt.getText().equals("")) {
                    messagerror = new MessageErrorControl(this, true, "الرجــاء اختـر المقيــــم من الجــدول");
                    messagerror.setVisible(true);
                } else {
                    student_ResRemplissage.SetHomeMsg(this);
                    student_ResRemplissage.TestCard(Integer.parseInt(NumCardToUpdt.getText()));
                }
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void BtnSavUpdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSavUpdtActionPerformed

        int NumCard = 0;
        int ID_Resident = 0;
        switch (getPatternResident()) { //Select the Resident to insert into DataBase

            case "طالب داخلي":
                jLabel55.setText(DatBirth_stdUpdtForm.getText());
                String a[] = jLabel55.getText().split("/");
                String NevDat = a[0] + "-" + a[1] + "-" + a[2];
                try {
                    student_Res = new Student_Res(txtNam_std5.getText(), txtSurNam_std5.getText(), Integer.parseInt(NumCardToUpdt.getText()), NumCardToUpdt.getText(),
                            new SimpleDateFormat("dd-MM-yyyy").parse(NevDat), txtPlcBirth_stdUpdtForm.getText(), Gender, 1,
                            Fill_Data.GetId_From_DB("ID_Case_Resident", "Resident_Case", "Resident_Case", (String) CaseResident.getSelectedItem()),
                            txtNam_FatherStdUpdtForm.getText(), txtNam_MotherStdUpdtForm.getText(), txtProfissionfath_StdUpdtForm.getText(), txtProfission_MothStdUpdtForm.getText(),
                            txtAddress_StdUpdtForm.getText(),
                            Fill_Data.GetId_From_DB("ID_Wilaya", "Wilaya", "NameWilaya", (String) WilayaListUpdtForm.getSelectedItem()),
                            /*txtCommuneStd2.getText()*/ (String) CombCommuneUpdtForm.getSelectedItem(), txtDairaStdUpdtForm.getText(),
                            Fill_Data.GetId_From_DB("Id_Nationalite", "Nationalite", "Nationalite", (String) National_listStdUpdtForm.getSelectedItem()),
                            SituationFam, Integer.parseInt(TtxtBacYearStdUpdtForm.getText()), Double.parseDouble(txtBacMoy2.getText()), txtPlaceGetBacStdUpdtForm.getText(),
                            Fill_Data.GetId_From_DB("Id_BranchStd", "Branch_Study", "BranchStd_Name", (String) Branch_stdUpdtForm.getSelectedItem()), new Date(),
                            // HIDE_ON_CLOSE, SituationFam, Gender, ABORT, Level, FRAMEBITS, DateInscrp, WIDTH, HIDE_ON_CLOSE,
                            Fill_Data.GetId_From_DB("Id_Faculty", "Faculty", "NameFact", (String) Depa_StdUpdtForm.getSelectedItem()),
                            Fill_Data.GetId_From_DB("Id_LevelStudy", "Level_Study", "DescriptionLevel", (String) LevelStdUpdtForm.getSelectedItem()),
                            881, txt_NumInscUpdtForm.getText(), 1,
                            Name_ResidentFrUp.getText(), LastName_ResidentFrUp.getText(), PlaceBirthFrStdUpdForm.getText(), Name_FatherFrUp.getText(), Name_MotherFrUp.getText(), LastName_MotheFrUp.getText(),
                            NamMrArStdUpdtForm.getText());
                } catch (Exception e) {
                }
                NumCard = Integer.parseInt(NumCardToUpdt.getText());
                ID_Resident = student_Res.Get_ID_Resident(NumCard);

                student_Res.setImageRes(ImageResidentToSv);
                student_Res.SetVarPht(student_ResRemplissage.GetVarPht());
                student_Res.UpdateResident(ID_Resident, NumCard);
                if (student_Res.getValConfiramation() == 1) {
                    Choice_Resident(check_Std1_x, check_Prof1_x, check_StdExt1_x, check_Emp1_x, PanAllUpdateResident, InfoStdToUpdate_1, TabResident, "عــدد الطلبـــة المقيمين"
                    );
                    initialisationFormUpdate_InterStudent();
                    InitFormUpdIntlStd(false);
                    messagerror = new MessageErrorControl(this, true, "لقد تم التعديل بنجاح");
                    messagerror.setVisible(true);
                } else {

                    messagerror = new MessageErrorControl(this, true, " عذرا لقد حصــل خطـــااثناء عملية التعديل   ");
                    messagerror.setVisible(true);
                }
                break;
            case "اســـتـاذ":
                jLabel55.setText(DatBirth_Pro.getText());
                String b[] = jLabel55.getText().split("/");
                String NevDatb = b[0] + "-" + b[1] + "-" + b[2];
                try {
                    professor_res = new Professor_Res(txtNam_Prof1.getText(), txtSurNam_Prof1.getText(), Integer.parseInt(NumCardToUpdt2.getText()), "251" + Integer.parseInt(NumCardToUpdt2.getText()),
                            new SimpleDateFormat("dd-MM-yyyy").parse(NevDatb),
                            ftxtPlcBirth_Prof.getText(), Gender, 3, Fill_Data.GetId_From_DB("ID_Case_Resident", "Resident_Case", "Resident_Case", (String) CaseResidentProfUpdtForm.getSelectedItem()),
                            1, 1, 1);
                } catch (Exception e) {
                }
                NumCard = Integer.parseInt(NumCardToUpdt2.getText());
                ID_Resident = professor_res.Get_ID_Resident(NumCard);
                professor_res.setImageRes(ImageResidentToSv);
                professor_res.SetVarPht(student_ResRemplissage.GetVarPht());
                professor_res.UpdateResident(ID_Resident, NumCard);

                if (professor_res.getValConfiramation() == 1) {

                    Choice_Resident(check_Prof1_x, check_Std1_x, check_StdExt1_x, check_Emp1_x, PanAllUpdateResident, PanProfToUpdate,
                            TabResident, "عـــدد الاساتذة");
                    InitialiseFormUpdateProfessor();
                    InitFormUpdIntlProf(false);
                    messagerror = new MessageErrorControl(this, true, "لقد تم التعديل بنجاح");
                    messagerror.setVisible(true);
                } else {
                    messagerror = new MessageErrorControl(this, true, " عذرا لقد حصــل خطـــااثناء عملية التعديل   ");
                    messagerror.setVisible(true);
                }
                break;
            case "طالب خـــارجــي":
                jLabel55.setText(DatBirth_StdExt1.getText());
                String c[] = jLabel55.getText().split("/");
                String NevDatc = c[0] + "-" + c[1] + "-" + c[2];
                try {
                    externalstudent = new ExternalStudent(txtNam_StdExt1.getText(), txtSurNam_StdExt1.getText(), NumCard, "0251" + NumCard,
                            new SimpleDateFormat("dd-MM-yyyy").parse(NevDatc),
                            txtPlcBirth_StdExt1.getText(), Gender, 2, Fill_Data.GetId_From_DB("ID_Case_Resident", "Resident_Case", "Resident_Case", (String) CaseResident2.getSelectedItem()),
                            txt_NumInscSdExt1.getText(), Fill_Data.GetId_From_DB("Id_BranchStd", "Branch_Study", "BranchStd_Name", (String) Branch_stdExternStdUpdtForm.getSelectedItem()),
                            Fill_Data.GetId_From_DB("Id_LevelStudy", "Level_Study", "DescriptionLevel", (String) LevelStdExtUpdtForm.getSelectedItem()), new Date(), 1);
                    NumCard = Integer.parseInt(NumCardToUpdt3.getText());
                } catch (Exception e) {
                }
                ID_Resident = externalstudent.Get_ID_Resident(NumCard);
                //  JOptionPane.showMessageDialog(null, "I am After Get Id Resident ");
                externalstudent.setImageRes(ImageResidentToSv);
                externalstudent.SetVarPht(student_ResRemplissage.GetVarPht());
                externalstudent.UpdateResident(ID_Resident, NumCard);
                if (externalstudent.getValConfiramation() == 1) {
                    Choice_Resident(check_StdExt1_x, check_Std1_x, check_Prof1_x, check_Emp1_x,
                            PanAllUpdateResident, PanStdExtToUpdate, TabResident, "عــدد الطلبـة الخارجيين");
                    Initialise_ExternalStudent_Resident(txt_NumInscSdExt1, txtNam_StdExt1, txtSurNam_StdExt1, DatBirth_StdExt1,
                            txtPlcBirth_StdExt1, Gdr_Prf_Malp1, Branch_stdExternStdUpdtForm, LevelStdExtUpdtForm, CaseResident2, 2);
                    InitFormUpdIntlExtStd(false);
                    messagerror = new MessageErrorControl(this, true, "لقد تم التعديل بنجاح");
                    messagerror.setVisible(true);
                } else {
                    messagerror = new MessageErrorControl(this, true, " عذرا لقد حصــل خطـــااثناء عملية التعديل   ");
                    messagerror.setVisible(true);
                }
                break;
            case "عـــامـــل":
                jLabel55.setText(DatBirth_Prof7.getText());
                String s[] = jLabel55.getText().split("/");
                String NevDats = s[0] + "-" + s[1] + "-" + s[2];
                try {
                    Employer_Resident = new Employer(txtNam_Prof7.getText(), txtSurNam_Prof7.getText(), NumCard, "0251" + NumCard,
                            new SimpleDateFormat("dd-MM-yyyy").parse(NevDats),
                            txtPlcBirth_Prof7.getText(), Gender, 4,
                            Fill_Data.GetId_From_DB("ID_Case_Resident", "Resident_Case", "Resident_Case", (String) CaseResident3.getSelectedItem()),
                            Fill_Data.GetId_From_DB("ID_Profession", "Profession", "Profession", (String) ProfessionToUpdate.getSelectedItem()), 1, 1);
                } catch (Exception e) {

                }
                NumCard = Integer.parseInt(NumCardToUpdt4.getText());
                ID_Resident = Employer_Resident.Get_ID_Resident(NumCard);
                // JOptionPane.showMessageDialog(null, "I am After Get Id Resident ");
                Employer_Resident.setImageRes(ImageResidentToSv);
                Employer_Resident.SetVarPht(student_ResRemplissage.GetVarPht());
                Employer_Resident.UpdateResident(ID_Resident, NumCard);

                if (Employer_Resident.getValConfiramation() == 1) {

                    Choice_Resident(check_Emp1_x, check_Std1_x, check_StdExt1_x, check_Prof1_x, PanAllUpdateResident, PanSaisiEmp1, TabResident, "عـــدد العمــال");
                    Gdr_Emp_MalE.setSelected(true);

                    Initialise_Employer_Resident(txtNam_Prof7, txtSurNam_Prof7,
                            DatBirth_Prof7, txtPlcBirth_Prof7, Gdr_Emp_MalE, ProfessionToUpdate, CaseResident3, 2);
                    InitFormUpdIntlEmploy(false);
                    messagerror = new MessageErrorControl(this, true, "لقد تم التعديل بنجاح");
                    messagerror.setVisible(true);
                } else {
                    messagerror = new MessageErrorControl(this, true, " عذرا لقد حصــل خطـــااثناء عملية التعديل   ");
                    messagerror.setVisible(true);
                }
                break;

            default:
                break;
        }
        student_ResRemplissage.SetVarPht(0);
    }//GEN-LAST:event_BtnSavUpdtActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        Fill_Data.CleanTextFld(null, null, Search_Field_upd_std, null);
        Fill_Data.FilterResidentMlt(null, null, Search_Field_upd_std, null, TabResident, (DefaultTableModel) TabResident.getModel());  //filtrer dans le tableau fournisseur
    }//GEN-LAST:event_jLabel7MouseClicked

    private void Search_Field_upd_stdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Search_Field_upd_stdKeyReleased
        Fill_Data.FilterResidentMlt(null, null, Search_Field_upd_std, null, TabResident, (DefaultTableModel) TabResident.getModel());  //filtrer dans le tableau fournisseur
    }//GEN-LAST:event_Search_Field_upd_stdKeyReleased

    private void BtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelActionPerformed
        UpdateOrCancel = 2;
        String paternResident = this.getPatternResident();
        switch (paternResident) {
            case "طالب داخلي":
                initialisationFormUpdate_InterStudent();
                InitFormUpdIntlStd(false);

                break;

            case "اســـتـاذ":
                InitialiseFormUpdateProfessor();
                InitFormUpdIntlProf(false);
                break;
            case "طالب خـــارجــي":
                Initialise_ExternalStudent_Resident(txt_NumInscSdExt1, txtNam_StdExt1, txtSurNam_StdExt1, DatBirth_StdExt1,
                        txtPlcBirth_StdExt1, Gdr_Prf_Malp1, Branch_stdExternStdUpdtForm, LevelStdExtUpdtForm, CaseResident2, 2);
                this.InitFormUpdIntlExtStd(false);
                break;
            case "عـــامـــل":
                Initialise_Employer_Resident(txtNam_Prof7, txtSurNam_Prof7,
                        DatBirth_Prof7, txtPlcBirth_Prof7, Gdr_Emp_MalE, ProfessionToUpdate, CaseResident3, 2);
                InitFormUpdIntlEmploy(false);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Error ");
        }
        Fill_Data.FilterResidentMlt(null, null, Search_Field_upd_std, null, TabResident, (DefaultTableModel) TabResident.getModel());  //filtrer dans le tableau fournisseur
        student_ResRemplissage.SetVarPht(0);
    }//GEN-LAST:event_BtnCancelActionPerformed

    private void btnUpdResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdResActionPerformed
        UpdateOrCancel = 1;
        comfirmationup_Sv = new ComfirmationUp_Sv(this, true, "هل تريد تعديل المعطيات");
        comfirmationup_Sv.setVisible(true);
    }//GEN-LAST:event_btnUpdResActionPerformed

    private void TabResidentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabResidentKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            int SlctRow = TabResident.getSelectedRow();
            int NumCard = (int) TabResident.getValueAt(SlctRow, 4);
            switch (getPatternResident()) {
                case "طالب داخلي": {
                    try {
                        student_ResRemplissage.GetInformationResidentN(NumCard, NumCardToUpdt, CaseResident, txtNam_std5, txtSurNam_std5,
                                DatBirth_stdUpdtForm, txtPlcBirth_stdUpdtForm, CheckMaleStdUpdtForm, checkFemalStdUpdtForm, txt_NumInscUpdtForm, txtNam_FatherStdUpdtForm, txtNam_MotherStdUpdtForm,
                                txtProfissionfath_StdUpdtForm, txtProfission_MothStdUpdtForm, txtAddress_StdUpdtForm, WilayaListUpdtForm, txtDairaStdUpdtForm, /*txtCommuneStd2*/ CombCommuneUpdtForm, National_listStdUpdtForm,
                                Sti_SingleStdUpdtForm_x, Std_MariedStdUpdtForm_x, TtxtBacYearStdUpdtForm, txtBacMoy2, txtPlaceGetBacStdUpdtForm,
                                Branch_stdUpdtForm, Depa_StdUpdtForm, LevelStdUpdtForm, Img_StdUpdate, getPatternResident(), SituationFam,
                                Name_ResidentFrUp, LastName_ResidentFrUp, PlaceBirthFrStdUpdForm, Name_FatherFrUp, Name_MotherFrUp, LastName_MotheFrUp, NamMrArStdUpdtForm);
                    } catch (IOException ex) {
                        // Logger.getLogger(Home1.class.getName()).log(Level.SEVERE, null, ex);
                        ex.printStackTrace();
                    }
                }

                break;
                case "اســـتـاذ":
                    try {
                        // JOptionPane.showMessageDialog(null, "Im in KeyEnter And Val is " + Integer.parseInt(NumCardToUpdt2.getText()));
                        professor_resRemplissage.setHome1(this);
                        professor_resRemplissage.GetInformationResidentN(NumCard, NumCardToUpdt2, CaseResidentProfUpdtForm, txtNam_Prof1,
                                txtSurNam_Prof1, DatBirth_Pro, ftxtPlcBirth_Prof, Gdr_Prf_Malp, Gdr_Prf_femalp, txt_NumInscUpdtForm, txtNam_FatherStdUpdtForm, txtNam_MotherStdUpdtForm,
                                txtProfissionfath_StdUpdtForm, txtProfission_MothStdUpdtForm, txtAddress_StdUpdtForm, WilayaListUpdtForm, txtDairaStdUpdtForm, /*txtCommuneStd2*/ CombCommuneUpdtForm, National_listStdUpdtForm,
                                Sti_SingleStdUpdtForm_x, Std_MariedStdUpdtForm_x, TtxtBacYearStdUpdtForm, txtBacMoy2,
                                txtPlaceGetBacStdUpdtForm, Branch_stdUpdtForm, Depa_StdUpdtForm, LevelStdUpdtForm, Img_profupdate, getPatternResident(), SituationFam,
                                null, null, null, null, null, null, null);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "طالب خـــارجــي":

                    ExternalstudentRemplissage.setHome1(this);
                     {
                        try {
                            ExternalstudentRemplissage.GetInformationResidentN(NumCard, NumCardToUpdt3, CaseResident2, txtNam_StdExt1,
                                    txtSurNam_StdExt1, DatBirth_StdExt1, txtPlcBirth_StdExt1, Gdr_Prf_Malp1, Gdr_Prf_femalp1, txt_NumInscSdExt1, null, null,
                                    null, null, null, null, null, null, null,
                                    null, null, null, null, null, Branch_stdExternStdUpdtForm, null,
                                    LevelStdExtUpdtForm, Img_StdUpdate1, getPatternResident(), SituationFam,
                                    null, null, null, null, null, null, null);
                        } catch (IOException ex) {

                        }
                    }

                    break;
                case "عـــامـــل":
                    try {

                        Employer_Remplissage.setHome1(this);
                        Employer_Remplissage.GetInformationResidentN(NumCard, NumCardToUpdt4, CaseResident3, txtNam_Prof7, txtSurNam_Prof7,
                                DatBirth_Prof7, txtPlcBirth_Prof7, Gdr_Emp_MalE, Gdr_Emp_femalE, null, null, null,
                                null, null, null, null, null, null, null,
                                null, null, null, null, null, null, null, ProfessionToUpdate, Img_EmpUpdate, getPatternResident(), SituationFam,
                                null, null, null, null, null, null, null);
                    } catch (IOException ex) {

                    }
                    break;
                default:
                    break;

            }

        }
    }//GEN-LAST:event_TabResidentKeyPressed

    private void TabResidentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabResidentMouseClicked

        if (TabResident.getSelectedRowCount() == 0) {
            messagerror = new MessageErrorControl(this, true, "الرجاء اختر الطــــالب");
            messagerror.setVisible(true);
        } else {

            int SlctRow = TabResident.getSelectedRow();
            int NumCard = (int) TabResident.getValueAt(SlctRow, 4);

            btnUpdRes.setEnabled(true);
            switch (getPatternResident()) {
                case "طالب داخلي":
                    try {
                        student_ResRemplissage.GetInformationResidentN(NumCard, NumCardToUpdt, CaseResident, txtNam_std5, txtSurNam_std5,
                                DatBirth_stdUpdtForm, txtPlcBirth_stdUpdtForm, CheckMaleStdUpdtForm, checkFemalStdUpdtForm, txt_NumInscUpdtForm, txtNam_FatherStdUpdtForm, txtNam_MotherStdUpdtForm,
                                txtProfissionfath_StdUpdtForm, txtProfission_MothStdUpdtForm, txtAddress_StdUpdtForm,
                                WilayaListUpdtForm, txtDairaStdUpdtForm,
                                /*txtCommuneStd2*/ CombCommuneUpdtForm, National_listStdUpdtForm,
                                Sti_SingleStdUpdtForm_x, Std_MariedStdUpdtForm_x, TtxtBacYearStdUpdtForm, txtBacMoy2, txtPlaceGetBacStdUpdtForm, Branch_stdUpdtForm, Depa_StdUpdtForm, LevelStdUpdtForm, Img_StdUpdate, getPatternResident(), SituationFam,
                                Name_ResidentFrUp, LastName_ResidentFrUp, PlaceBirthFrStdUpdForm, Name_FatherFrUp, Name_MotherFrUp, LastName_MotheFrUp, NamMrArStdUpdtForm);
                        RoomRemplissage.GetRoomANDPavillionForStudent(NumCard, LstPvlinUpdtForm, CombRomInUpdtForm, AncianRoom);
                    } catch (IOException ex) {

                    }
                    break;
                case "اســـتـاذ":
                    try {
                        //  professor_resRemplissage.setHome1(this);
                        professor_resRemplissage.GetInformationResidentN(NumCard, NumCardToUpdt2, CaseResidentProfUpdtForm, txtNam_Prof1,
                                txtSurNam_Prof1, DatBirth_Pro, ftxtPlcBirth_Prof, Gdr_Prf_Malp, Gdr_Prf_femalp, txt_NumInscUpdtForm, txtNam_FatherStdUpdtForm, txtNam_MotherStdUpdtForm,
                                txtProfissionfath_StdUpdtForm, txtProfission_MothStdUpdtForm, txtAddress_StdUpdtForm, WilayaListUpdtForm, txtDairaStdUpdtForm, /*txtCommuneStd2*/ CombCommuneUpdtForm, National_listStdUpdtForm,
                                Sti_SingleStdUpdtForm_x, Std_MariedStdUpdtForm_x, TtxtBacYearStdUpdtForm, txtBacMoy2, txtPlaceGetBacStdUpdtForm, Branch_stdUpdtForm, Depa_StdUpdtForm, LevelStdUpdtForm, Img_profupdate, getPatternResident(), SituationFam,
                                null, null, null, null, null, null, null);
                    } catch (IOException ex) {
                    }
                    break;
                case "طالب خـــارجــي":

                    ExternalstudentRemplissage.setHome1(this);
                    try {
                        ExternalstudentRemplissage.GetInformationResidentN(NumCard, NumCardToUpdt3, CaseResident2, txtNam_StdExt1,
                                txtSurNam_StdExt1, DatBirth_StdExt1, txtPlcBirth_StdExt1, Gdr_Prf_Malp1, Gdr_Prf_femalp1, txt_NumInscSdExt1, null, null,
                                null, null, null, null, null, null, null,
                                null, null, null, null, null, Branch_stdExternStdUpdtForm, null, LevelStdExtUpdtForm, Img_StdUpdate1,
                                getPatternResident(), SituationFam,
                                null, null, null, null, null, null, null);
                    } catch (IOException ex) {

                    }

                    break;
                case "عـــامـــل":
                    try {
                        //Employer_Remplissage.setHome1(this);
                        Employer_Remplissage.GetInformationResidentN(NumCard, NumCardToUpdt4, CaseResident3, txtNam_Prof7, txtSurNam_Prof7,
                                DatBirth_Prof7, txtPlcBirth_Prof7, Gdr_Emp_MalE, Gdr_Emp_femalE, null, null, null,
                                null, null, null, null, null, null, null,
                                null, null, null, null, null, null, null, ProfessionToUpdate, Img_EmpUpdate, getPatternResident(), SituationFam,
                                null, null, null, null, null, null, null);

                    } catch (IOException ex) {
                    }
                    break;
                default:
                    break;

            }

        }
    }//GEN-LAST:event_TabResidentMouseClicked

    private void jLabel153MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel153MouseClicked
        try {
            ImageResidentToSv = UploadPictResident(Img_EmpUpdate);
        } catch (IOException ex) {
            messagerror = new MessageErrorControl(this, true, "خــطأ في تحميــــل صـــــورةالعامل ");
            messagerror.setVisible(true);
        }
    }//GEN-LAST:event_jLabel153MouseClicked

    private void jLabel133MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel133MouseClicked
        ImageResidentToSv = SelectPictToResident(Img_EmpUpdate);
    }//GEN-LAST:event_jLabel133MouseClicked

    private void PanSaisInfoEmp1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PanSaisInfoEmp1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_PanSaisInfoEmp1FocusGained

    private void Gdr_Emp_MalEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gdr_Emp_MalEActionPerformed

        if (Gdr_Emp_MalE.isSelected() == true) {
            Gdr_Emp_femalE.setSelected(false);
            Gender = 1;
        } else {
            Gdr_Emp_femalE.setSelected(true);
            Gender = 2;
        }
    }//GEN-LAST:event_Gdr_Emp_MalEActionPerformed

    private void Gdr_Emp_femalEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gdr_Emp_femalEActionPerformed
        if (Gdr_Emp_femalE.isSelected() == true) {
            Gdr_Emp_MalE.setSelected(false);
            Gender = 2;
        } else {
            Gdr_Emp_MalE.setSelected(true);
            Gender = 1;
        }
    }//GEN-LAST:event_Gdr_Emp_femalEActionPerformed

    private void jPanel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel31MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel31MouseClicked

    private void jLabel121MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel121MouseClicked

        if (NumCardToUpdt4.getText().equals("")) {

            messagerror = new MessageErrorControl(this, true, "تأكد من تعبئـة حقـل البطـاقـة");
            messagerror.setVisible(true);
        } else {

            try {

                Employer_Remplissage.setHome1(this);
                Employer_Remplissage.GetInformationResidentN(Integer.parseInt(NumCardToUpdt4.getText()), NumCardToUpdt4, CaseResident3, txtNam_Prof7, txtSurNam_Prof7,
                        DatBirth_Prof7, txtPlcBirth_Prof7, Gdr_Emp_MalE, Gdr_Emp_femalE, null, null, null,
                        null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, ProfessionToUpdate, Img_EmpUpdate,
                        getPatternResident(), SituationFam,
                        null, null, null, null, null, null, null);
            } catch (IOException ex) {
                // Logger.getLogger(Home1.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }//GEN-LAST:event_jLabel121MouseClicked

    private void NumCardToUpdt4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumCardToUpdt4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (NumCardToUpdt4.getText().equals("")) {

                messagerror = new MessageErrorControl(this, true, "تأكد من تعبئـة حقـل البطـاقـة");
                messagerror.setVisible(true);
            } else {

                try {

                    Employer_Remplissage.setHome1(this);
                    Employer_Remplissage.GetInformationResidentN(Integer.parseInt(NumCardToUpdt4.getText()), NumCardToUpdt4, CaseResident3, txtNam_Prof7, txtSurNam_Prof7,
                            DatBirth_Prof7, txtPlcBirth_Prof7, Gdr_Emp_MalE, Gdr_Emp_femalE, null, null, null,
                            null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, ProfessionToUpdate, Img_EmpUpdate,
                            getPatternResident(), SituationFam, null, null, null, null, null, null, null);
                } catch (IOException ex) {
                    // Logger.getLogger(Home1.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }//GEN-LAST:event_NumCardToUpdt4KeyPressed

    private void ProfessionToUpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProfessionToUpdateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfessionToUpdateKeyPressed

    private void DatBirth_Prof7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DatBirth_Prof7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DatBirth_Prof7KeyPressed

    private void txtPlcBirth_Prof7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlcBirth_Prof7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlcBirth_Prof7KeyPressed

    private void txtPlcBirth_Prof7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPlcBirth_Prof7FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlcBirth_Prof7FocusGained

    private void txtNam_Prof7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNam_Prof7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNam_Prof7KeyPressed

    private void txtNam_Prof7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNam_Prof7FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNam_Prof7FocusGained

    private void txtSurNam_Prof7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSurNam_Prof7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSurNam_Prof7KeyPressed

    private void txtSurNam_Prof7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSurNam_Prof7FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSurNam_Prof7FocusGained

    private void jLabel132MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel132MouseClicked

        ImageResidentToSv = SelectPictToResident(Img_StdUpdate1);
    }//GEN-LAST:event_jLabel132MouseClicked

    private void jLabel131MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel131MouseClicked
        try {
            ImageResidentToSv = UploadPictResident(Img_StdUpdate1);
        } catch (IOException ex) {
            messagerror = new MessageErrorControl(this, true, "خــطأ في تحميــــل صـــــورةالطالب ");
            messagerror.setVisible(true);
        }
    }//GEN-LAST:event_jLabel131MouseClicked

    private void PanSaisiInfoStdExt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PanSaisiInfoStdExt1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_PanSaisiInfoStdExt1FocusGained

    private void Gdr_Prf_femalp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gdr_Prf_femalp1ActionPerformed

        if (Gdr_Prf_femalp1.isSelected() == true) {
            Gdr_Prf_Malp1.setSelected(false);
            Gender = 2;
        } else {
            Gdr_Prf_Malp1.setSelected(true);
            Gender = 1;
        }
    }//GEN-LAST:event_Gdr_Prf_femalp1ActionPerformed

    private void Gdr_Prf_Malp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gdr_Prf_Malp1ActionPerformed
        if (Gdr_Prf_Malp1.isSelected() == true) {
            Gdr_Prf_femalp1.setSelected(false);
            Gender = 1;
        } else {
            Gdr_Prf_femalp1.setSelected(true);
            Gender = 2;
        }
    }//GEN-LAST:event_Gdr_Prf_Malp1ActionPerformed

    private void jPanel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel24MouseClicked

    private void jPanel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel28MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel28MouseClicked

    private void jLabel118MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel118MouseClicked

        if (NumCardToUpdt3.getText().equals("")) {

            messagerror = new MessageErrorControl(this, true, "تأكد من تعبئـة حقـل البطـاقـة");
            messagerror.setVisible(true);
        } else {
            try {
                ExternalstudentRemplissage.setHome1(this);
                ExternalstudentRemplissage.GetInformationResidentN(Integer.parseInt(NumCardToUpdt3.getText()), NumCardToUpdt3, CaseResident2, txtNam_StdExt1,
                        txtSurNam_StdExt1, DatBirth_StdExt1, txtPlcBirth_StdExt1, Gdr_Prf_Malp1, Gdr_Prf_femalp1, txt_NumInscSdExt1, null, null,
                        null, null, null, null, null, null, null,
                        null, null, null, null, null, Branch_stdExternStdUpdtForm, null,
                        LevelStdExtUpdtForm, Img_StdUpdate1, getPatternResident(), SituationFam,
                        null, null, null, null, null, null, null);
            } catch (IOException ex) {
                //Logger.getLogger(Home1.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

        /*

        try {
            ExternalstudentRemplissage.setHome1(this);
            ExternalstudentRemplissage.GetInformationResidentN(Integer.parseInt(NumCardToUpdt3.getText()), NumCardToUpdt3,CaseResident2 ,txtNam_StdExt1,
                txtSurNam_StdExt1, DatBirth_StdExt1, txtPlcBirth_StdExt1, Gdr_Prf_Malp1, Gdr_Prf_femalp1, txt_NumInscSdExt1, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, txtBranch_stdExternStd, null, LevelStdExtToupd,Img_StdUpdate1,
                getPatternResident(),SituationFam);
        } catch (IOException ex) {
            //Logger.getLogger(Home1.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }*/
    }//GEN-LAST:event_jLabel118MouseClicked

    private void NumCardToUpdt3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumCardToUpdt3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            /* try {
                ExternalstudentRemplissage.setHome1(this);
                ExternalstudentRemplissage.GetInformationResidentN(Integer.parseInt(NumCardToUpdt3.getText()), NumCardToUpdt3,CaseResident2 ,txtNam_StdExt1,
                    txtSurNam_StdExt1, DatBirth_StdExt1, txtPlcBirth_StdExt1, Gdr_Prf_Malp1, Gdr_Prf_femalp1, txt_NumInscSdExt1, null, null,
                    null, null, null, null, null, null, null,
                    null, null, null, null, null, txtBranch_stdExternStd, null,
                    LevelStdExtToupd,Img_StdUpdate1, getPatternResident(),SituationFam);
            } catch (IOException ex) {
                //Logger.getLogger(Home1.class.getName()).log(Level.SEVERE, null, ex);

            }*/

            if (NumCardToUpdt3.getText().equals("")) {

                messagerror = new MessageErrorControl(this, true, "تأكد من تعبئـة حقـل البطـاقـة");
                messagerror.setVisible(true);
            } else {
                try {
                    ExternalstudentRemplissage.setHome1(this);
                    ExternalstudentRemplissage.GetInformationResidentN(Integer.parseInt(NumCardToUpdt3.getText()), NumCardToUpdt3, CaseResident2, txtNam_StdExt1,
                            txtSurNam_StdExt1, DatBirth_StdExt1, txtPlcBirth_StdExt1, Gdr_Prf_Malp1, Gdr_Prf_femalp1, txt_NumInscSdExt1, null, null,
                            null, null, null, null, null, null, null,
                            null, null, null, null, null, Branch_stdExternStdUpdtForm, null,
                            LevelStdExtUpdtForm, Img_StdUpdate1, getPatternResident(), SituationFam,
                            null, null, null, null, null, null, null);
                } catch (IOException ex) {
                    //Logger.getLogger(Home1.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }//GEN-LAST:event_NumCardToUpdt3KeyPressed

    private void txt_NumInscSdExt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_NumInscSdExt1FocusGained
        txt_NumInscSdExt1.setForeground(Color.black);
    }//GEN-LAST:event_txt_NumInscSdExt1FocusGained

    private void LevelStdExtUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LevelStdExtUpdtFormKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LevelStdExtUpdtFormKeyPressed

    private void LevelStdExtUpdtFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevelStdExtUpdtFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LevelStdExtUpdtFormActionPerformed

    private void Branch_stdExternStdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Branch_stdExternStdUpdtFormKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Branch_stdExternStdUpdtFormKeyPressed

    private void Branch_stdExternStdUpdtFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Branch_stdExternStdUpdtFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Branch_stdExternStdUpdtFormActionPerformed

    private void DatBirth_StdExt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DatBirth_StdExt1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DatBirth_StdExt1KeyPressed

    private void DatBirth_StdExt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DatBirth_StdExt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DatBirth_StdExt1ActionPerformed

    private void txtPlcBirth_StdExt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPlcBirth_StdExt1FocusGained

        txtPlcBirth_StdExt1.setForeground(Color.black);
    }//GEN-LAST:event_txtPlcBirth_StdExt1FocusGained

    private void txtNam_StdExt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNam_StdExt1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNam_StdExt1KeyPressed

    private void txtNam_StdExt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNam_StdExt1FocusGained
        txtNam_StdExt1.setForeground(Color.black);
    }//GEN-LAST:event_txtNam_StdExt1FocusGained

    private void txtSurNam_StdExt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSurNam_StdExt1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSurNam_StdExt1KeyPressed

    private void txtSurNam_StdExt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSurNam_StdExt1FocusGained
        txtSurNam_StdExt1.setForeground(Color.black);
    }//GEN-LAST:event_txtSurNam_StdExt1FocusGained

    private void jLabel129MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel129MouseClicked
        try {
            ImageResidentToSv = UploadPictResident(Img_profupdate);
        } catch (IOException ex) {
            messagerror = new MessageErrorControl(this, true, "خــطأ في تحميــــل صـــــورةالاستــــاذ ");
            messagerror.setVisible(true);
        }
    }//GEN-LAST:event_jLabel129MouseClicked

    private void jLabel128MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel128MouseClicked
        ImageResidentToSv = SelectPictToResident(Img_profupdate);
    }//GEN-LAST:event_jLabel128MouseClicked

    private void panSaisiInfoProfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panSaisiInfoProfFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_panSaisiInfoProfFocusGained

    private void jPanel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel27MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel27MouseClicked

    private void jLabel116MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel116MouseClicked

        if (NumCardToUpdt2.getText().equals("")) {

            messagerror = new MessageErrorControl(this, true, "تأكد من تعبئـة حقـل البطـاقـة");
            messagerror.setVisible(true);
        } else {
            try {
                // JOptionPane.showMessageDialog(null, "Im in KeyEnter And Val is " + Integer.parseInt(NumCardToUpdt2.getText()));
                professor_resRemplissage.setHome1(this);
                professor_resRemplissage.GetInformationResidentN(Integer.parseInt(NumCardToUpdt2.getText()), NumCardToUpdt2, CaseResidentProfUpdtForm, txtNam_Prof1,
                        txtSurNam_Prof1, DatBirth_Pro, ftxtPlcBirth_Prof, Gdr_Prf_Malp, Gdr_Prf_femalp, txt_NumInscUpdtForm, txtNam_FatherStdUpdtForm, txtNam_MotherStdUpdtForm,
                        txtProfissionfath_StdUpdtForm, txtProfission_MothStdUpdtForm, txtAddress_StdUpdtForm, WilayaListUpdtForm, txtDairaStdUpdtForm, /*txtCommuneStd2*/ CombCommuneUpdtForm, National_listStdUpdtForm,
                        Sti_SingleStdUpdtForm_x, Std_MariedStdUpdtForm_x, TtxtBacYearStdUpdtForm, txtBacMoy2, txtPlaceGetBacStdUpdtForm, Branch_stdUpdtForm, Depa_StdUpdtForm, LevelStdUpdtForm, Img_profupdate, getPatternResident(), SituationFam,
                        null, null, null, null, null, null, null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jLabel116MouseClicked

    private void NumCardToUpdt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumCardToUpdt2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (NumCardToUpdt2.getText().equals("")) {

                messagerror = new MessageErrorControl(this, true, "تأكد من تعبئـة حقـل البطـاقـة");
                messagerror.setVisible(true);
            } else {
                try {
                    // JOptionPane.showMessageDialog(null, "Im in KeyEnter And Val is " + Integer.parseInt(NumCardToUpdt2.getText()));
                    professor_resRemplissage.setHome1(this);
                    professor_resRemplissage.GetInformationResidentN(Integer.parseInt(NumCardToUpdt2.getText()), NumCardToUpdt2, CaseResidentProfUpdtForm, txtNam_Prof1,
                            txtSurNam_Prof1, DatBirth_Pro, ftxtPlcBirth_Prof, Gdr_Prf_Malp, Gdr_Prf_femalp, txt_NumInscUpdtForm, txtNam_FatherStdUpdtForm, txtNam_MotherStdUpdtForm,
                            txtProfissionfath_StdUpdtForm, txtProfission_MothStdUpdtForm, txtAddress_StdUpdtForm, WilayaListUpdtForm, txtDairaStdUpdtForm, /*txtCommuneStd2*/ CombCommuneUpdtForm, National_listStdUpdtForm,
                            Sti_SingleStdUpdtForm_x, Std_MariedStdUpdtForm_x, TtxtBacYearStdUpdtForm, txtBacMoy2, txtPlaceGetBacStdUpdtForm, Branch_stdUpdtForm, Depa_StdUpdtForm, LevelStdUpdtForm, Img_profupdate, getPatternResident(), SituationFam,
                            null, null, null, null, null, null, null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }//GEN-LAST:event_NumCardToUpdt2KeyPressed

    private void Gdr_Prf_femalpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gdr_Prf_femalpActionPerformed
        if (Gdr_Prf_femalp.isSelected() == true) {
            Gdr_Prf_Malp.setSelected(false);
            Gender = 2;
        } else {
            Gdr_Prf_Malp.setSelected(true);
            Gender = 1;
        }
    }//GEN-LAST:event_Gdr_Prf_femalpActionPerformed

    private void Gdr_Prf_MalpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gdr_Prf_MalpActionPerformed

        if (Gdr_Prf_Malp.isSelected() == true) {
            Gdr_Prf_femalp.setSelected(false);
            Gender = 1;
        } else {
            Gdr_Prf_femalp.setSelected(true);
            Gender = 2;
        }
    }//GEN-LAST:event_Gdr_Prf_MalpActionPerformed

    private void txtProfession_Prof2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProfession_Prof2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProfession_Prof2ActionPerformed

    private void txtProfession_Prof2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfession_Prof2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProfession_Prof2FocusGained

    private void DatBirth_ProKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DatBirth_ProKeyPressed
        //  ftxtPlcBirth_Prof
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ftxtPlcBirth_Prof.requestFocus();
        }
    }//GEN-LAST:event_DatBirth_ProKeyPressed

    private void ftxtPlcBirth_ProfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftxtPlcBirth_ProfKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Gdr_Prf_Malp.requestFocus();
        }
    }//GEN-LAST:event_ftxtPlcBirth_ProfKeyPressed

    private void ftxtPlcBirth_ProfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtPlcBirth_ProfFocusGained
        ftxtPlcBirth_Prof.setForeground(Color.black);
    }//GEN-LAST:event_ftxtPlcBirth_ProfFocusGained

    private void txtNam_Prof1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNam_Prof1KeyPressed
        //
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSurNam_Prof1.requestFocus();
        }
    }//GEN-LAST:event_txtNam_Prof1KeyPressed

    private void txtNam_Prof1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNam_Prof1FocusGained
        txtNam_Prof1.setForeground(Color.black);
    }//GEN-LAST:event_txtNam_Prof1FocusGained

    private void txtSurNam_Prof1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSurNam_Prof1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DatBirth_Pro.requestFocus();
        }
    }//GEN-LAST:event_txtSurNam_Prof1KeyPressed

    private void txtSurNam_Prof1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSurNam_Prof1FocusGained
        txtSurNam_Prof1.setForeground(Color.black);
    }//GEN-LAST:event_txtSurNam_Prof1FocusGained

    private void NamMrArStdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamMrArStdUpdtFormKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            LastName_MotheFrUp.requestFocus();
        }
    }//GEN-LAST:event_NamMrArStdUpdtFormKeyPressed

    private void NamMrArStdUpdtFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamMrArStdUpdtFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamMrArStdUpdtFormActionPerformed

    private void LastName_ResidentFrUpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LastName_ResidentFrUpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DatBirth_stdUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_LastName_ResidentFrUpKeyPressed

    private void LastName_ResidentFrUpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LastName_ResidentFrUpFocusGained
        if (LastName_ResidentFrUp.getText().equals("Nom")) {
            LastName_ResidentFrUp.setText("");
        }
        LastName_ResidentFrUp.setForeground(Color.blue);
    }//GEN-LAST:event_LastName_ResidentFrUpFocusGained

    private void Name_ResidentFrUpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Name_ResidentFrUpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSurNam_std5.requestFocus();
        }
    }//GEN-LAST:event_Name_ResidentFrUpKeyPressed

    private void Name_ResidentFrUpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Name_ResidentFrUpFocusGained
        if (Name_ResidentFrUp.getText().equals("Prénom")) {
            Name_ResidentFrUp.setText("");
        }
        Name_ResidentFrUp.setForeground(Color.blue);
    }//GEN-LAST:event_Name_ResidentFrUpFocusGained

    private void PlaceBirthFrStdUpdFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PlaceBirthFrStdUpdFormKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNam_FatherStdUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_PlaceBirthFrStdUpdFormKeyPressed

    private void PlaceBirthFrStdUpdFormFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PlaceBirthFrStdUpdFormFocusGained
        if (PlaceBirthFrStdUpdForm.getText().equals("Lieu _Naissance")) {
            PlaceBirthFrStdUpdForm.setText("");
        }
        PlaceBirthFrStdUpdForm.setForeground(Color.blue);
    }//GEN-LAST:event_PlaceBirthFrStdUpdFormFocusGained

    private void Name_FatherFrUpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Name_FatherFrUpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtProfissionfath_StdUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_Name_FatherFrUpKeyPressed

    private void Name_FatherFrUpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Name_FatherFrUpFocusGained
        if (Name_FatherFrUp.getText().equals("Prénom Père")) {
            Name_FatherFrUp.setText("");
        }
        Name_FatherFrUp.setForeground(Color.blue);
    }//GEN-LAST:event_Name_FatherFrUpFocusGained

    private void LastName_MotheFrUpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LastName_MotheFrUpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtProfission_MothStdUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_LastName_MotheFrUpKeyPressed

    private void LastName_MotheFrUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastName_MotheFrUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LastName_MotheFrUpActionPerformed

    private void LastName_MotheFrUpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LastName_MotheFrUpFocusGained
        if (LastName_MotheFrUp.getText().equals("Nom_Mère")) {
            LastName_MotheFrUp.setText("");
        }
        LastName_MotheFrUp.setForeground(Color.blue);
    }//GEN-LAST:event_LastName_MotheFrUpFocusGained

    private void jLabel115MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel115MouseClicked
        // TODO add your handling code here:
        ImageResidentToSv = SelectPictToResident(Img_StdUpdate);
    }//GEN-LAST:event_jLabel115MouseClicked

    private void jLabel113MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel113MouseClicked
        try {
            ImageResidentToSv = UploadPictResident(Img_StdUpdate);
        } catch (IOException ex) {
            messagerror = new MessageErrorControl(this, true, "خــطأ في تحميــــل صـــــورةالطالب ");
            messagerror.setVisible(true);
        }
    }//GEN-LAST:event_jLabel113MouseClicked

    private void jLabel106MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel106MouseClicked

        if (NumCardToUpdt.getText().equals("")) {

            messagerror = new MessageErrorControl(this, true, "تأكد من تعبئـة حقـل البطـاقـة");
            messagerror.setVisible(true);
        } else {
            try {
                student_ResRemplissage.GetInformationResidentN(Integer.parseInt(NumCardToUpdt.getText()), NumCardToUpdt, CaseResident, txtNam_std5, txtSurNam_std5,
                        DatBirth_stdUpdtForm, txtPlcBirth_stdUpdtForm, CheckMaleStdUpdtForm, checkFemalStdUpdtForm, txt_NumInscUpdtForm, txtNam_FatherStdUpdtForm, txtNam_MotherStdUpdtForm,
                        txtProfissionfath_StdUpdtForm, txtProfission_MothStdUpdtForm, txtAddress_StdUpdtForm, WilayaListUpdtForm, txtDairaStdUpdtForm, /*txtCommuneStd2*/ CombCommuneUpdtForm, National_listStdUpdtForm,
                        Sti_SingleStdUpdtForm_x, Std_MariedStdUpdtForm_x, TtxtBacYearStdUpdtForm, txtBacMoy2, txtPlaceGetBacStdUpdtForm, Branch_stdUpdtForm, Depa_StdUpdtForm, LevelStdUpdtForm, Img_StdUpdate, getPatternResident(), SituationFam,
                        Name_ResidentFrUp, LastName_ResidentFrUp, PlaceBirthFrStdUpdForm, Name_FatherFrUp, Name_MotherFrUp, LastName_MotheFrUp, NamMrArStdUpdtForm);
            } catch (IOException ex) {
            }
        }
    }//GEN-LAST:event_jLabel106MouseClicked

    private void NumCardToUpdtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumCardToUpdtKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_NumCardToUpdtKeyTyped

    private void NumCardToUpdtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumCardToUpdtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (NumCardToUpdt.getText().equals("")) {
                messagerror = new MessageErrorControl(this, true, "تأكد من تعبئـة حقـل البطـاقـة");
                messagerror.setVisible(true);
            } else {
                try {
                    student_ResRemplissage.GetInformationResidentN(Integer.parseInt(NumCardToUpdt.getText()), NumCardToUpdt, CaseResident, txtNam_std5, txtSurNam_std5,
                            DatBirth_stdUpdtForm, txtPlcBirth_stdUpdtForm, CheckMaleStdUpdtForm, checkFemalStdUpdtForm, txt_NumInscUpdtForm, txtNam_FatherStdUpdtForm, txtNam_MotherStdUpdtForm,
                            txtProfissionfath_StdUpdtForm, txtProfission_MothStdUpdtForm, txtAddress_StdUpdtForm, WilayaListUpdtForm, txtDairaStdUpdtForm, /*txtCommuneStd2*/ CombCommuneUpdtForm, National_listStdUpdtForm,
                            Sti_SingleStdUpdtForm_x, Std_MariedStdUpdtForm_x, TtxtBacYearStdUpdtForm, txtBacMoy2, txtPlaceGetBacStdUpdtForm, Branch_stdUpdtForm, Depa_StdUpdtForm, LevelStdUpdtForm, Img_StdUpdate, getPatternResident(), SituationFam,
                            Name_ResidentFrUp, LastName_ResidentFrUp, PlaceBirthFrStdUpdForm, Name_FatherFrUp, Name_MotherFrUp, LastName_MotheFrUp, NamMrArStdUpdtForm);
                } catch (IOException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_NumCardToUpdtKeyPressed

    private void Depa_StdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Depa_StdUpdtFormKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            LevelStdUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_Depa_StdUpdtFormKeyPressed

    private void LevelStdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LevelStdUpdtFormKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LevelStdUpdtFormKeyPressed

    private void Branch_stdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Branch_stdUpdtFormKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Depa_StdUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_Branch_stdUpdtFormKeyPressed

    private void txtPlaceGetBacStdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlaceGetBacStdUpdtFormKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Branch_stdUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_txtPlaceGetBacStdUpdtFormKeyPressed

    private void txtPlaceGetBacStdUpdtFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlaceGetBacStdUpdtFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlaceGetBacStdUpdtFormActionPerformed

    private void txtPlaceGetBacStdUpdtFormFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPlaceGetBacStdUpdtFormFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlaceGetBacStdUpdtFormFocusGained

    private void TtxtBacYearStdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtxtBacYearStdUpdtFormKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtBacMoy2.requestFocus();
        }
    }//GEN-LAST:event_TtxtBacYearStdUpdtFormKeyPressed

    private void TtxtBacYearStdUpdtFormFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TtxtBacYearStdUpdtFormFocusGained
        TtxtBacYearStdUpdtForm.setForeground(Color.black);
    }//GEN-LAST:event_TtxtBacYearStdUpdtFormFocusGained

    private void txtBacMoy2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBacMoy2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPlaceGetBacStdUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_txtBacMoy2KeyPressed

    private void txtBacMoy2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBacMoy2FocusGained
        txtBacMoy2.setForeground(Color.black);
    }//GEN-LAST:event_txtBacMoy2FocusGained

    private void WilayaListUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WilayaListUpdtFormKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDairaStdUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_WilayaListUpdtFormKeyPressed

    private void National_listStdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_National_listStdUpdtFormKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_National_listStdUpdtFormKeyPressed

    private void CheckMaleStdUpdtFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckMaleStdUpdtFormActionPerformed

        if (CheckMaleStdUpdtForm.isSelected() == true) {
            checkFemalStdUpdtForm.setSelected(false);
            Gender = 1;
        } else {
            checkFemalStdUpdtForm.setSelected(true);
            Gender = 2;
        }
    }//GEN-LAST:event_CheckMaleStdUpdtFormActionPerformed

    private void checkFemalStdUpdtFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkFemalStdUpdtFormActionPerformed
        if (checkFemalStdUpdtForm.isSelected() == true) {
            CheckMaleStdUpdtForm.setSelected(false);
            Gender = 2;

        } else {
            CheckMaleStdUpdtForm.setSelected(true);
            Gender = 1;
        }
    }//GEN-LAST:event_checkFemalStdUpdtFormActionPerformed

    private void txtAddress_StdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddress_StdUpdtFormKeyPressed
        // --
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            WilayaListUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_txtAddress_StdUpdtFormKeyPressed

    private void txtAddress_StdUpdtFormFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAddress_StdUpdtFormFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddress_StdUpdtFormFocusGained

    private void txtProfissionfath_StdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfissionfath_StdUpdtFormKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNam_MotherStdUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_txtProfissionfath_StdUpdtFormKeyPressed

    private void txtProfissionfath_StdUpdtFormFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProfissionfath_StdUpdtFormFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProfissionfath_StdUpdtFormFocusGained

    private void txtPlcBirth_stdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlcBirth_stdUpdtFormKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            PlaceBirthFrStdUpdForm.requestFocus();
        }
    }//GEN-LAST:event_txtPlcBirth_stdUpdtFormKeyPressed

    private void txtPlcBirth_stdUpdtFormFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPlcBirth_stdUpdtFormFocusGained

    }//GEN-LAST:event_txtPlcBirth_stdUpdtFormFocusGained

    private void txtNam_FatherStdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNam_FatherStdUpdtFormKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Name_FatherFrUp.requestFocus();
        }
    }//GEN-LAST:event_txtNam_FatherStdUpdtFormKeyPressed

    private void txtNam_FatherStdUpdtFormFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNam_FatherStdUpdtFormFocusGained

    }//GEN-LAST:event_txtNam_FatherStdUpdtFormFocusGained

    private void DatBirth_stdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DatBirth_stdUpdtFormKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPlcBirth_stdUpdtForm.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_DatBirth_stdUpdtFormKeyPressed

    private void txtSurNam_std5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSurNam_std5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            LastName_ResidentFrUp.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtSurNam_std5KeyPressed

    private void txtSurNam_std5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSurNam_std5FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSurNam_std5FocusGained

    private void txtNam_std5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNam_std5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSurNam_std5.requestFocus();
        }
    }//GEN-LAST:event_txtNam_std5KeyPressed

    private void txtNam_std5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNam_std5FocusGained
        txtNam_std5.setForeground(Color.gray);
    }//GEN-LAST:event_txtNam_std5FocusGained

    private void txt_NumInscUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NumInscUpdtFormKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NumInscUpdtFormKeyPressed

    private void txt_NumInscUpdtFormFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_NumInscUpdtFormFocusGained
        txt_NumInscUpdtForm.setForeground(Color.black);
    }//GEN-LAST:event_txt_NumInscUpdtFormFocusGained

    private void BtnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNextActionPerformed

        PrintingCrd(LabPrfNumCard_StdRes);
    }//GEN-LAST:event_BtnNextActionPerformed

    private void jLabel98MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel98MouseClicked
        // TODO add your handling code here:
        ImageResidentToSv = SelectPictToResident(CLabImg);
    }//GEN-LAST:event_jLabel98MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // InsertPictResident

            ImageResidentToSv = UploadPictResident(CLabImg);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(PanCntMenu, messagerror);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void PrinRecWtoutShActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrinRecWtoutShActionPerformed
        switch (PatternResident) {
            case "طالب داخلي":
                student_ResRemplissage.PrintingWithoutDialogAccommdation_Rapporteur(LabNameRestToPrint.getText(), LabLastNameRestToPrint.getText(),
                        Integer.parseInt(LabPrfNumCard_StdRes.getText()), LabDateBirth_Plc.getText(),
                        LabPlaceBirth_Res.getText(), LabBranchStd.getText(), "الاقـــامة الجامعية الحاجب", "0");
                break;

        }
    }//GEN-LAST:event_PrinRecWtoutShActionPerformed

    private void LabPrfResd_Std3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabPrfResd_Std3MouseEntered
        //LabPrfResd_Std3.setBorder(BorderFactory.createLineBorder(Color.blue));
    }//GEN-LAST:event_LabPrfResd_Std3MouseEntered

    private void LabPrfResd_Std3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabPrfResd_Std3MouseClicked
        student_ResRemplissage.DisplayAccommdation_Rapporteur(LabNameRestToPrint.getText(), LabLastNameRestToPrint.getText(),
                Integer.parseInt(LabPrfNumCard_StdRes.getText()), LabDateBirth_Plc.getText(),
                LabPlaceBirth_Res.getText(), LabBranchStd.getText(), "الاقـــامة الجامعية الحاجب", "0");
    }//GEN-LAST:event_LabPrfResd_Std3MouseClicked

    private void check_StdExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_StdExtActionPerformed
//        setPatternResident(check_StdExt.getText());
//        Choice_Resident(check_StdExt, check_Prof, check_Emp, check_Std, SecondCentrePan);
//        PanPrfAfterSaisStd.setVisible(false);
//        Gdr_StdExt_Mal.setSelected(true);
//        Gender = 1;
//        //Gdr_Emp_Mal1
//        Fill_Data.Filling(txtBranch_stdExtr, "Branch_Study", "BranchStd_Name", 4);
//        txtBranch_std5.removeItem("/");
//        Fill_Data.Filling(LevelStd_StdExtrn, "Level_Study", "DescriptionLevel", 3);
//        LevelStd.removeItem("/");
        //LevelStd_StdExtrn
    }//GEN-LAST:event_check_StdExtActionPerformed

    private void check_StdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_check_StdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_StdKeyPressed

    private void check_StdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_StdActionPerformed
        setPatternResident(check_Std.getText());
        //Choice_Resident(check_Std, check_Prof, check_StdExt, check_Emp, SecondCentrePan);

        Fill_Data.Filling(WilayaListUpdtForm, "Wilaya", "NameWilaya", 1);
        WilayaListUpdtForm.removeItem("/");
        Fill_Data.Filling(National_listStdUpdtForm, "Nationalite", "Nationalite", 2);
        National_listStdUpdtForm.removeItem("/");
        Fill_Data.Filling(LevelStdUpdtForm, "Level_Study", "DescriptionLevel", 3);
        LevelStdUpdtForm.removeItem("/");
        Fill_Data.Filling(Branch_stdUpdtForm, "Branch_Study", "BranchStd_Name", 4);
        Branch_stdUpdtForm.removeItem("/");
        Fill_Data.Filling(Depa_StdUpdtForm, "Faculty", "NameFact", 5);
        Depa_StdUpdtForm.removeItem("/");
        PanPrfAfterSaisStd.setVisible(false);
        BtnRdMaleStd.setSelected(true);
        Gender = 1;
    }//GEN-LAST:event_check_StdActionPerformed

    private void check_ProfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_check_ProfKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_ProfKeyPressed

    private void check_ProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_ProfActionPerformed
//        setPatternResident(check_Prof.getText());
//        Choice_Resident(check_Prof, check_Std, check_StdExt, check_Emp, SecondCentrePan);
//
//        PanPrfAfterSaisStd.setVisible(false);
//        Gdr_Prf_Mal.setSelected(true);
//        Gender = 1;

        //txtBranch_stdExtr
    }//GEN-LAST:event_check_ProfActionPerformed

    private void check_EmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_check_EmpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_EmpKeyPressed

    private void check_EmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_EmpActionPerformed
//        setPatternResident(check_Emp.getText());
//        Choice_Resident(check_Emp, check_Std, check_Prof, check_StdExt, SecondCentrePan);
//        Gdr_Emp_Mal1.setSelected(true);
//        Gender = 1;
//        //PatternResident=check_Emp.getText();
//        Fill_Data.Filling(Profession, "Profession", "Profession", 6);
//        Profession.removeItem("/");
//        PanPrfAfterSaisStd.setVisible(false);
    }//GEN-LAST:event_check_EmpActionPerformed

    private void Gdr_Emp_femal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gdr_Emp_femal1ActionPerformed
        if (Gdr_Emp_femal1.isSelected() == true) {
            Gdr_Emp_Mal1.setSelected(false);
            Gender = 2;
            Profession.requestFocus();
        } else {
            Gdr_Emp_Mal1.setSelected(true);
            Gender = 1;
        }
    }//GEN-LAST:event_Gdr_Emp_femal1ActionPerformed

    private void Gdr_Emp_Mal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gdr_Emp_Mal1ActionPerformed
        if (Gdr_Emp_Mal1.isSelected() == true) {
            Gdr_Emp_femal1.setSelected(false);
            Gender = 1;
            Profession.requestFocus();
        } else {
            Gdr_Emp_femal1.setSelected(true);
            Gender = 2;
        }
    }//GEN-LAST:event_Gdr_Emp_Mal1ActionPerformed

    private void ProfessionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProfessionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfessionKeyPressed

    private void DatBirth_EmplKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DatBirth_EmplKeyPressed
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) && (!DatBirth_Empl.getText().equals(""))) {
            txtPlcBirth_Emply.requestFocus();
        }
    }//GEN-LAST:event_DatBirth_EmplKeyPressed

    private void txtPlcBirth_EmplyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlcBirth_EmplyKeyPressed
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) && (!txtPlcBirth_Emply.getText().equals(""))) {
            Gdr_Emp_Mal1.requestFocus();
        }
    }//GEN-LAST:event_txtPlcBirth_EmplyKeyPressed

    private void txtPlcBirth_EmplyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPlcBirth_EmplyFocusGained
        if (txtPlcBirth_Emply.getText().equals("ادخل مكان الميلاد")) {
            txtPlcBirth_Emply.setText("");
        }
        txtPlcBirth_Emply.setForeground(Color.blue);
    }//GEN-LAST:event_txtPlcBirth_EmplyFocusGained

    private void txtNam_EmplInsrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNam_EmplInsrKeyPressed
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) && (!txtNam_EmplInsr.getText().equals(""))) {
            txtSurNam_Empl.requestFocus();
        }
    }//GEN-LAST:event_txtNam_EmplInsrKeyPressed

    private void txtNam_EmplInsrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNam_EmplInsrFocusGained
        if (txtNam_EmplInsr.getText().equals("ادخل الاسم")) {
            txtNam_EmplInsr.setText("");
        }
        txtNam_EmplInsr.setForeground(Color.blue);
    }//GEN-LAST:event_txtNam_EmplInsrFocusGained

    private void txtSurNam_EmplKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSurNam_EmplKeyPressed
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) && (!txtSurNam_Empl.getText().equals(""))) {
            DatBirth_Empl.requestFocus();
        }
    }//GEN-LAST:event_txtSurNam_EmplKeyPressed

    private void txtSurNam_EmplFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSurNam_EmplFocusGained
        if (txtSurNam_Empl.getText().equals("ادخل اللقب")) {
            txtSurNam_Empl.setText("");
        }
        txtSurNam_Empl.setForeground(Color.blue);
    }//GEN-LAST:event_txtSurNam_EmplFocusGained

    private void DatBirth_stdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DatBirth_stdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPlcBirth_std.requestFocus();
        }
    }//GEN-LAST:event_DatBirth_stdKeyPressed

    private void DatBirth_stdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DatBirth_stdFocusGained
        DatBirth_std.setForeground(Color.blue);
    }//GEN-LAST:event_DatBirth_stdFocusGained

    private void LastNamMothARTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LastNamMothARTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            LastNamMothARTxt_FR.requestFocus();
        }
    }//GEN-LAST:event_LastNamMothARTxtKeyPressed

    private void txtBranch_stdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBranch_stdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DatInscrpInUniv.requestFocus();
        }
    }//GEN-LAST:event_txtBranch_stdKeyPressed

    private void txtDepa_StdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepa_StdKeyPressed
        // TODO add your handling code here:

        BtnSaveStd.requestFocus();

        BtnSaveStd.setBackground(Color.red);
    }//GEN-LAST:event_txtDepa_StdKeyPressed

    private void LevelStdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LevelStdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDepa_Std.requestFocus();
            txtDepa_Std.showPopup();
        }
    }//GEN-LAST:event_LevelStdKeyPressed

    private void LevelStdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevelStdActionPerformed
        // TODO add your handling code here:
        Level = (String) LevelStd.getSelectedItem();
    }//GEN-LAST:event_LevelStdActionPerformed

    private void DatInscrpInUnivKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DatInscrpInUnivKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            LevelStd.requestFocus();
        }
    }//GEN-LAST:event_DatInscrpInUnivKeyPressed

    private void WilayaListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WilayaListKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDairaStd.requestFocus();
        }
    }//GEN-LAST:event_WilayaListKeyPressed

    private void txtDairaStdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDairaStdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CombCommuneSlct.requestFocus();
        }
    }//GEN-LAST:event_txtDairaStdKeyPressed

    private void txtAddress_StdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddress_StdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            National_list.requestFocus();
        }
    }//GEN-LAST:event_txtAddress_StdKeyPressed

    private void txtProfission_MothKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfission_MothKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            WilayaList.requestFocus();
        }
    }//GEN-LAST:event_txtProfission_MothKeyPressed

    private void txtNam_motherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNam_motherKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNam_mother_FR.requestFocus();
            //txtProfission_Moth.requestFocus();
        }
    }//GEN-LAST:event_txtNam_motherKeyPressed

    private void txtNam_FatherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNam_FatherKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNam_Father_FR.requestFocus();
        }
    }//GEN-LAST:event_txtNam_FatherKeyPressed

    private void txtProfission_StdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProfission_StdKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNam_mother.requestFocus();
        }
    }//GEN-LAST:event_txtProfission_StdKeyPressed

    private void txtPlcBirth_stdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlcBirth_stdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPlcBirth_std_FR.requestFocus();
        }
    }//GEN-LAST:event_txtPlcBirth_stdKeyPressed

    private void txtSurNam_stdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSurNam_stdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSurNam_std_FR.requestFocus();
        }
    }//GEN-LAST:event_txtSurNam_stdKeyPressed

    private void txtNam_stdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNam_stdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNam_std_FR.requestFocus();
        }
    }//GEN-LAST:event_txtNam_stdKeyPressed

    private void txt_NumInscKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NumInscKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNam_std.requestFocus();
        }
    }//GEN-LAST:event_txt_NumInscKeyPressed

    private void txt_NumInscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NumInscActionPerformed
        /*  SimpleDateFormat Frmat=new SimpleDateFormat("yy");
        String AnnIns=Frmat.format(new Date());
        String NumInsc=txt_NumInsc.getText();
        Get_Nev_Inf =new Get_Nev_Inf(this);
        String NumInscription="";
        if (NumInsc.startsWith((AnnIns+"/"))) {
            NumInscription= NumInsc.substring(3);
        }else{
            NumInscription=NumInsc;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int i =Get_Nev_Inf.Get_Info_Nev_Res(NumInscription, txtNam_std, txtSurNam_std,
            DatBirth_std,txtPlcBirth_std,txtNam_Father, txtNam_mother,
            LastNamMothARTxt, txtCommuneStd, National_list, CheckMale, checkFemal,
            txtBranch_std, LevelStd,year);

        if(i!=1){
            NamFR="";
            PrénomFR="";
            NamFatheFr="";
            NamMatherFR="";
            LastMatherFR="";
            PlcFR="";
         */
 /*messagerror = new MessageErrorControl(this, true, " الرقـــم غـيـر مــوجـود الـرجـاء الـتأكـد مـنـه");
            messagerror.setVisible(true);*/
 /*jLabel60.setText("الرقم غير موجود");
            Initialise_Student_Resident();
            txt_NumInsc.setText(NumInsc);
            txt_NumInsc.setForeground(Color.red);
         }*/
        txtNam_std.requestFocus();
    }//GEN-LAST:event_txt_NumInscActionPerformed

    private void txt_NumInscFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_NumInscFocusGained
        if (txt_NumInsc.getText().equals("رقــم تــسجـيــل الــبـكــالــوريــا ")) {
            txt_NumInsc.setText("");
        }
        txt_NumInsc.setForeground(Color.RED);
    }//GEN-LAST:event_txt_NumInscFocusGained

    public void CloseAdvancedprp() {
        //filling_Object_Menu();
        //ItemMenRegistrationMouseClicked(null);
    }

    public void filling_Object_Menu() {

        //  BackgroundMenu(ItemMenResidence);
        //   BackgroundMenu(jLabel11, PanCtnLbInsRm);
//        ChoixPanSrvdetaille(PanServiceDetaill, PanRegistrationStd);
        //ChoixPanSrvdetaille(PnMnRegis, PanCtnLbInsRm);
        ChoixPanSrvdetaille(PanAllServiceStudent, PanInfoCard);
        ChoixPanSrvdetaille(Pan_All_PansSais_, panSaisiStd_);
        PanResidentChoiceToSaisi = 1;
        //DeSelectedPatern(check_Std, check_Prof, check_StdExt, check_Emp);  //For Deselect all Jcheck
        check_Std.setSelected(true);
        Choice_Resident_Sais(check_Std, check_Prof, check_StdExt, check_Emp, panSaisiStd_);  //For Choice First Check
        setPatternResident(check_Std.getText());
        txt_NumInsc.requestFocus();
        Fill_Data.Filling(WilayaList, "Wilaya", "NameWilaya", 1);
        WilayaList.removeItem("/");
        Fill_Data.Filling(National_list, "Nationalite", "Nationalite", 2);
        National_list.removeItem("/");

        Fill_Data.Filling(LevelStd, "Level_Study", "DescriptionLevel", 3);
        LevelStd.removeItem("/");

        Fill_Data.Filling(txtBranch_std, "Branch_Study", "BranchStd_Name", 4);
        txtBranch_std.removeItem("/");
        Gender = 1;
        Fill_Data.Filling(txtDepa_Std, "Faculty", "NameFact", 5);

        txtDepa_Std.removeItem("/");

        Fill_Data.Filling(PavillonPanInf, "Pavilion", "Pavilion_Name", 8);

        PanPrfAfterSaisStd.setVisible(false);
        if (F.isVisible()) {
            F.dispose();
        } else if (AdvPrp.isVisible()) {
            AdvPrp.dispose();;
        }
    }


    private void jLabel201MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel201MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel201MouseExited

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        ExternalstudentRemplissage.ListExternalStudentNewOrNot((String) CombExternNewOrNot.getSelectedItem());
    }//GEN-LAST:event_jButton14ActionPerformed

    private void LstPvlinUpdtFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LstPvlinUpdtFormActionPerformed
        RoomRemplissage.FillComboboxRooms(CombRomInUpdtForm, (String) LstPvlinUpdtForm.getSelectedItem());
    }//GEN-LAST:event_LstPvlinUpdtFormActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed

        if (NumCardToUpdt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "الرجاء اختيار الطالب من الجدول ");
        } else {

            int idResChngRom = Fill_Data.GetIdResidentByNumCard(Integer.parseInt(NumCardToUpdt.getText()));
            if (!AncianRoom.getText().equals("")) {
                int idRmAns = RoomRemplissage.Get_ID_Room(AncianRoom.getText());
                int idRmNov = RoomRemplissage.Get_ID_Room((String) CombRomInUpdtForm.getSelectedItem());
                Resident_GlRemplissage.ChangeRoom(idResChngRom, idRmAns, idRmNov, getPatternResident());
                JOptionPane.showMessageDialog(null, "تم تغيير الغرفة بنجاح");
            }

        }

        //Resident_GlRemplissage.ChangeRoom(ID_Resident, ID_RoomA, ID_RoomN, getPatternResident());

    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:ListLevelStd
        student_ResRemplissage.ListLevelStd((String) LisLvlStd.getSelectedItem());
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        if (jCheckBox4.isSelected()) {
            jTextField2.setVisible(true);
            jLabel69.setVisible(true);
        } else {
            jLabel69.setVisible(false);
            jTextField2.setVisible(false);
        }

    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jLabel69MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel69MouseClicked
        String Proffession_txt = jTextField2.getText();
        if ("".equals(Proffession_txt)) {
            JOptionPane.showMessageDialog(null, "تأكد من تعبئة حقل المهنة");
        } else {
            if (Employer_Remplissage.ConrolSais_Prof(Proffession_txt) == 1) {
                JOptionPane.showMessageDialog(null, "هذه المهنـة موجودة سابقا");
            } else {
                int cn = Employer_Remplissage.InsertProffession(Proffession_txt);
                if (cn == 1) {
                    Fill_Data.Filling(Profession, "Profession", "Profession", 6);
                    Profession.removeItem("/");
                    jTextField2.setText("");
                    Profession.setSelectedItem(Proffession_txt);
                }
            }
        }
    }//GEN-LAST:event_jLabel69MouseClicked

    private void CombCommuneSlctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CombCommuneSlctActionPerformed
        //        String CommuneSlc=(String)ComboxHome.getSelectedItem();
        //        if (!CommuneSlc.equals("اختر البلدية")) {
        //            CommuneSlc.setText(CommuneSlc);
        //        }
    }//GEN-LAST:event_CombCommuneSlctActionPerformed

    private void PavillonPanInfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PavillonPanInfActionPerformed
        RoomRemplissage.FillComboboxRooms(RomPvinPanInf, (String) PavillonPanInf.getSelectedItem());
    }//GEN-LAST:event_PavillonPanInfActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            PavillonPanInf.setEnabled(false);
            RomPvinPanInf.setEnabled(false);
        } else {
            PavillonPanInf.setEnabled(true);
            RomPvinPanInf.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void WilayaListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WilayaListActionPerformed
        F.FillTableCommune_Combobox((String) WilayaList.getSelectedItem(), null, CombCommuneSlct, 1);
    }//GEN-LAST:event_WilayaListActionPerformed

    private void txtDairaStdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDairaStdUpdtFormKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCommuneStdUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_txtDairaStdUpdtFormKeyPressed

    private void txtDairaStdUpdtFormFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDairaStdUpdtFormFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDairaStdUpdtFormFocusGained

    private void WilayaListUpdtFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WilayaListUpdtFormActionPerformed
        F.FillTableCommune_Combobox((String) WilayaListUpdtForm.getSelectedItem(), null, CombCommuneUpdtForm, 1);
    }//GEN-LAST:event_WilayaListUpdtFormActionPerformed

    private void txtCommuneStdUpdtFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCommuneStdUpdtFormKeyPressed
        // --
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            National_listStdUpdtForm.requestFocus();
        }
    }//GEN-LAST:event_txtCommuneStdUpdtFormKeyPressed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        ImageResidentToSv = GetPhotoStudentByScanner(CLabImg);

        /*try {
            // use scan function of Wia api and create file with order 
            ScannerTool.scan("D:\\Photo_residents\\" + NumOrder.getText() + ".jpg");

        } catch (WiaOperationException ex) {
            JOptionPane.showMessageDialog(null, "حدث خطأ بالماسح الضوئي\n الرجاء تحميل الصورة يدويا");
        }*/
    }//GEN-LAST:event_jButton26ActionPerformed
    public File GetPhotoStudentByScanner(JLabel CLabImg) {

        Wia4j wia4j = new Wia4j();
        File fileImage = null;
        try {
            fileImage = new File("D:\\Photo_residents\\" + NumOrder.getText() + ".jpg");
            if (fileImage.exists()) {
                JOptionPane.showMessageDialog(null, "This fil isExists ");
                fileImage.delete();
                JOptionPane.showMessageDialog(null, "Success deleting file:" + fileImage.getName());
            }
            wia4j.scan("D:\\Photo_residents\\" + NumOrder.getText() + ".jpg");

        } catch (WiaOperationException ex) {
            System.out.println("Error Exception ");
        }
        BufferedImage bufImg = null;
        try {
            bufImg = ImageIO.read(fileImage);
            System.out.println("succes read file");
        } catch (IOException ex) {
            //Logger.getLogger(FormEmployer.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        CLabImg.setIcon(new ImageIcon(new ImageIcon(bufImg).getImage().getScaledInstance(CLabImg.getWidth(), CLabImg.getHeight(), Image.SCALE_SMOOTH)));
        return fileImage;
    }
    private void BtnShowRoomsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnShowRoomsMouseEntered
        BtnShowRooms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/view_icon.png"))); // NOI18N

    }//GEN-LAST:event_BtnShowRoomsMouseEntered

    private void BtnShowRoomsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnShowRoomsMouseExited
        BtnShowRooms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/ViewSmal.jpg"))); // NOI18N
    }//GEN-LAST:event_BtnShowRoomsMouseExited

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/C_Add_Mini_h.png"))); // NOI18N
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/residence/Image/C_Add_Mini.png"))); // NOI18N
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseExited

    private void txtNam_std_FRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNam_std_FRKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSurNam_std.requestFocus();
        }
    }//GEN-LAST:event_txtNam_std_FRKeyPressed

    private void txtSurNam_std_FRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSurNam_std_FRKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DatBirth_std.requestFocus();
        }
    }//GEN-LAST:event_txtSurNam_std_FRKeyPressed

    private void LastNamMothARTxt_FRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LastNamMothARTxt_FRKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LastNamMothARTxt_FRKeyPressed

    private void txtNam_Father_FRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNam_Father_FRKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtProfission_Std.requestFocus();
        }
    }//GEN-LAST:event_txtNam_Father_FRKeyPressed

    private void txtPlcBirth_std_FRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlcBirth_std_FRKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNam_Father.requestFocus();
        }
    }//GEN-LAST:event_txtPlcBirth_std_FRKeyPressed

    private void txtNam_mother_FRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNam_mother_FRKeyPressed
        // TODO add your handling code here:LastNamMothARTxt
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            LastNamMothARTxt.requestFocus();
        }
    }//GEN-LAST:event_txtNam_mother_FRKeyPressed

    private void TtxtBacYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtxtBacYearKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtBacMoy1.requestFocus();
        }
    }//GEN-LAST:event_TtxtBacYearKeyPressed

    private void txtBacMoy1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBacMoy1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPlaceGetBac1.requestFocus();
        }
    }//GEN-LAST:event_txtBacMoy1KeyPressed

    private void txtPlaceGetBac1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlaceGetBac1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtBranch_std.requestFocus();
        }
    }//GEN-LAST:event_txtPlaceGetBac1KeyPressed

    private void CombCommuneSlctItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CombCommuneSlctItemStateChanged
        txtDairaStd.requestFocus();
    }//GEN-LAST:event_CombCommuneSlctItemStateChanged

    private void National_listItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_National_listItemStateChanged
        PavillonPanInf.requestFocus();
    }//GEN-LAST:event_National_listItemStateChanged

    private void RomPvinPanInfItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RomPvinPanInfItemStateChanged
        TtxtBacYear.requestFocus();
    }//GEN-LAST:event_RomPvinPanInfItemStateChanged

    private void txtBranch_stdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtBranch_stdItemStateChanged
        DatInscrpInUniv.requestFocus();
    }//GEN-LAST:event_txtBranch_stdItemStateChanged

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        addwilyGUI = new AddWilaya(this);
        addwilyGUI.PrepareCreateGuiForm();
        addwilyGUI.setLocationRelativeTo(null);
        addwilyGUI.setVisible(true);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void Menu_Update_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Update_infoActionPerformed
        ChoixPanSrvdetaille(PanAllServiceStudent, UpdateStd);
        ChoixPanSrvdetaille(PanAllUpdateResident, InfoStdToUpdate_1);

        Fill_Data.Filling(CaseResident, "Resident_Case", "Resident_Case", 7);

        Fill_Data.Filling(WilayaListUpdtForm, "Wilaya", "NameWilaya", 1);
        WilayaListUpdtForm.removeItem("/");

        Fill_Data.Filling(National_listStdUpdtForm, "Nationalite", "Nationalite", 2);
        National_listStdUpdtForm.removeItem("/");

        Fill_Data.Filling(LevelStdUpdtForm, "Level_Study", "DescriptionLevel", 3);
        LevelStdUpdtForm.removeItem("/");

        Fill_Data.Filling(Branch_stdUpdtForm, "Branch_Study", "BranchStd_Name", 4);
        Branch_stdUpdtForm.removeItem("/");

        Fill_Data.Filling(Depa_StdUpdtForm, "Faculty", "NameFact", 5);
        Depa_StdUpdtForm.removeItem("/");

        /**
         * ********************************************************************
         */
        Fill_Data.Filling(Branch_stdExternStdUpdtForm, "Branch_Study", "BranchStd_Name", 4);
        Branch_stdExternStdUpdtForm.removeItem("/");

        Fill_Data.Filling(LevelStdExtUpdtForm, "Level_Study", "DescriptionLevel", 3);
        LevelStdExtUpdtForm.removeItem("/");

        Fill_Data.Filling(ProfessionToUpdate, "Profession", "Profession", 6);
        ProfessionToUpdate.removeItem("/");

        Fill_Data.Filling(LstPvlinUpdtForm, "Pavilion", "Pavilion_Name", 8);
        DefaultTableModel dfmd = (DefaultTableModel) TabResident.getModel();

        /**
         * *********************************************************************
         */
        setPatternResident(check_Std1_x.getText());
        student_ResRemplissage.DisplayAllResidentInTable(TabResident, Fill_Data.GetId_From_DB("Id_Ptrn_Res", "Pattern_Person_Res", "Name_Patern", getPatternResident()), LabNamesForRes, "عـــــدد الطلاب", CountTabStdIn);

        Sti_SingleStdUpdtForm_x.setSelected(true);
        SituationFam = Sti_SingleStdUpdtForm_x.getText();

        InitFormUpdIntlStd(false);


    }//GEN-LAST:event_Menu_Update_infoActionPerformed

    private void Menu_Residence_dossierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Residence_dossierActionPerformed
        ChoixPanSrvdetaille(PanAllServiceStudent, PanInfoCard);
        ChoixPanSrvdetaille(Pan_All_PansSais_, panSaisiStd_);
        setPatternResident("طالب داخلي");
        txt_NumInsc.requestFocus();

        Fill_Data.Filling(WilayaList, "Wilaya", "NameWilaya", 1); // Fill Combo Of Wilaya
        WilayaList.removeItem("/");

        Fill_Data.Filling(National_list, "Nationalite", "Nationalite", 2);
        National_list.removeItem("/");

        Fill_Data.Filling(LevelStd, "Level_Study", "DescriptionLevel", 3);
        LevelStd.removeItem("/");

        Fill_Data.Filling(txtBranch_std, "Branch_Study", "BranchStd_Name", 4);
        txtBranch_std.removeItem("/");

        Gender = 1;

        Fill_Data.Filling(txtDepa_Std, "Faculty", "NameFact", 5);
        txtDepa_Std.removeItem("/");

        Fill_Data.Filling(PavillonPanInf, "Pavilion", "Pavilion_Name", 8);

    }//GEN-LAST:event_Menu_Residence_dossierActionPerformed

    private void Menu_Consult_InfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Consult_InfoActionPerformed
        //ChoixPanSrvdetaille(PanAllServiceStudent, PanMenuAllStudent1);

        //ChoixPanSrvdetaille(PanServiceDetaill, PnIUpdtusr);
        //BackgroundMenu(jLabel130, PanCtnLbInsRm);
//        ChoixPanSrvdetaille(PanServiceDetaill, PanRegistrationStd);
        ChoixPanSrvdetaille(PanAllServiceStudent, PanMenuAllStudent1);
        check_Std2_x.setSelected(true);
        setPatternResident(check_Std2_x.getText());
        check_Std2_x.setBackground(new Color(0, 153, 0));
        check_Prof2_x.setSelected(false);
        check_Prof2_x.setBackground(Color.WHITE);
        check_Emp2_x.setSelected(false);
        check_Emp2_x.setBackground(Color.WHITE);
        check_StdExt2_x.setSelected(false);
        check_StdExt2_x.setBackground(Color.WHITE);
        PaternToChangeCase = "طالب داخلي";
        IndiceSelected = 1;
        Choice_Resident(check_Std2_x, check_Prof2_x, check_StdExt2_x, check_Emp2_x,
                PanAllResidentToConsult, panResidentToConsult, TabResidentToConsult, "عــدد الطلبـــة المقيمين");
        student_ResRemplissage.DisplayAllResidentInTablToConsult(TabResidentToConsult, LabTexTypCount, "عدد الطلبة", LabCountTabConsult, getPatternResident());
        Fill_Data.Filling(txtBrchToPrint, "Branch_Study", "BranchStd_Name", 4);
        txtBrchToPrint.removeItem("/");
        Fill_Data.Filling(Branch4, "Branch_Study", "BranchStd_Name", 4);
        Branch4.removeItem("/");
        Fill_Data.Filling(LisLvlStd, "Level_Study", "DescriptionLevel", 3);
        LevelStd.removeItem("/");
        Fill_Data.Filling(BranchStd_year, "Branch_Study", "BranchStd_Name", 4);

        BranchStd_year.removeItem("/");
        BranchStd_year.addItem("جميع التخصصات");

        Fill_Data.Filling(CaseN1, "Resident_Case", "Resident_Case", 8);
        Fill_Data.Filling(CaseN2, "Resident_Case", "Resident_Case", 8);
        Fill_Data.Filling(CaseN3, "Resident_Case", "Resident_Case", 8);
        //CaseN3
        Fill_Data.Filling(pavilion2, "Pavilion", "Pavilion_Name", 8);
        Fill_Data.Filling(Pavlion4, "Pavilion", "Pavilion_Name", 8);
        Fill_Data.Filling(Pavlion5, "Pavilion", "Pavilion_Name", 8);
        //pavilion2
        PanChangeCase.setVisible(false);
        Fill_Data.FillingCase(CaseN, "طالب داخلي");
        PaternToChangeCase = check_Std2_x.getText();
        IndiceSelected = 1;
        cleanPanCangeCase();
        student_ResRemplissage.FilterResident("", TabResidentToConsult, (DefaultTableModel) TabResidentToConsult.getModel());
        jPanel3.setVisible(false);
        PanChange2 = 0;
        /* userserviceRemplissage.DisplayUserIntable((DefaultTableModel) TabUserService.getModel());
        if (Gs.isVisible()) {
            Gs.dispose();
        } else if (F.isVisible()) {
            F.dispose();;
        } else {
            R.dispose();;
        }*/

    }//GEN-LAST:event_Menu_Consult_InfoActionPerformed

    private void Menu_ReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_ReportsActionPerformed
        //BackgroundMenu(ItemMenReport);
        AdvPrp = new Advanced_prp(F);
        AdvPrp.setVisible(true);

        if (F.isVisible()) {
            F.dispose();
        }

        /* 
        if(!R.isVisible()){
            R = new Restu();
           
           Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
            Point Pt = PanServiceDetaill.getLocationOnScreen();
            Dimension d = PanServiceDetaill.getSize();
            R.setLocation(Pt);
            R.setSize(d);

            R.setVisible(true);

            if (Gs.isVisible()){
                Gs.dispose();
            } else if(F.isVisible()){
                F.dispose();
            }*/
        // ChoixPanSrvdetaille(PanServiceDetaill, PanPrintCrd);
        //}
    }//GEN-LAST:event_Menu_ReportsActionPerformed

    private void Menu_RoomServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_RoomServiceActionPerformed
        //BackgroundMenu(ItemMenReserveRom);

//        ChoixPanSrvdetaille(PanServiceDetaill, PanRegistrationStd);
        //ChoixPanSrvdetaille(PnMnRegis, PaneCntLbRm);
        //BackgroundMenu(LabTakeRoom1, PaneCntLbRm);
        ChoixPanSrvdetaille(PanAllServiceStudent, PanTakeRoom);

        Etat_Reserv = 0;
        check_Std_TakeRom.setSelected(false);
        check_Std_TakeRom.setBackground(new Color(255, 255, 255));
        check_Prof_TakeRom.setSelected(false);
        check_Prof_TakeRom.setBackground(new Color(255, 255, 255));
        PatternToTakeRoom = check_Std_TakeRom.getText();
        student_ResRemplissage.FilterResident("", TabStdInternTotakeRoom, (DefaultTableModel) TabStdInternTotakeRoom.getModel());
        student_ResRemplissage.FilterResident("", TabRoom, (DefaultTableModel) TabRoom.getModel());
        Resident_GlRemplissage.DisplayTabToTakeRoom(TabStdInternTotakeRoom, Etat_Reserv);
        Fill_Data.Filling(lstPav, "Pavilion", "Pavilion_Name", 8);

        //       JOptionPane.showMessageDialog(null, "la==id_room :"+TabStdInternTotakeRoom.getValueAt(0,9));
        listBtnPanPV = new ArrayList<>();
        remplirlistfromPan();

        RemoveActionListner();

        MakeActionListenerBtn();
        // MakeActionListenerBtn();
        // TabRoom.setModel(null);

        RoomRemplissage.DisplayTabRoom(TabRoom);
        DefaultTableModel dm = (DefaultTableModel) TabRoom.getModel();
        dm.removeRow((TabRoom.getRowCount() - 1));
        student_ResRemplissage.FilterResident("", TabRoom, (DefaultTableModel) TabRoom.getModel());
        jTextField3.setText("ادخــل الــغــرفـــة");
        jTextField3.setForeground(new Color(153, 153, 153));
        ChangeRoom.setBackground(new Color(0, 153, 153));
        RserveRoom.setBackground(new Color(0, 153, 153));
        if (Gs.isVisible()) {
            Gs.dispose();
        } else if (F.isVisible()) {
            F.dispose();
        } else if (R.isVisible()) {
            R.dispose();
        }
    }//GEN-LAST:event_Menu_RoomServiceActionPerformed

    private void Menu_ConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_ConfigActionPerformed
        //BackgroundMenu(ItemMenSystemConf);
        if (!F.isVisible()) {
            // F = new Conf();
            F.FillTableManager();
            Point Pt = SecondCentrePan.getLocationOnScreen();
            Dimension d = SecondCentrePan.getSize();
            F.setLocation(Pt);
            F.setSize(d);
            F.setVisible(true);
            if (R.isVisible()) {
                R.dispose();
            } else if (Gs.isVisible()) {
                Gs.dispose();
            }
            //ChoixPanSrvdetaille(PanServiceDetaill, PanPrintCrd);
        }
    }//GEN-LAST:event_Menu_ConfigActionPerformed

    private void buttonView10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonView10ActionPerformed
        FormExterieurResidents frmExtr = new FormExterieurResidents();
        frmExtr.setVisible(true);

    }//GEN-LAST:event_buttonView10ActionPerformed

    private void buttonView11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonView11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonView11ActionPerformed

    private void jLabel60MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel60MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel60MouseEntered

    private void jLabel60MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel60MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel60MouseExited

    private void jLabel61MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel61MouseEntered

    private void jLabel61MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel61MouseExited

    private void LabelShowNationaliteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelShowNationaliteMouseEntered

    }//GEN-LAST:event_LabelShowNationaliteMouseEntered

    private void LabelShowNationaliteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelShowNationaliteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_LabelShowNationaliteMouseExited

    private void txtNam_std_FRFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNam_std_FRFocusGained
        txtNam_std_FR.getInputContext().selectInputMethod(new Locale("fr", "FR"));
    }//GEN-LAST:event_txtNam_std_FRFocusGained

    private void txtSurNam_std_FRFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSurNam_std_FRFocusGained
        txtSurNam_std_FR.getInputContext().selectInputMethod(new Locale("fr", "FR"));
    }//GEN-LAST:event_txtSurNam_std_FRFocusGained

    private void txtPlcBirth_std_FRFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPlcBirth_std_FRFocusGained
        txtPlcBirth_std_FR.getInputContext().selectInputMethod(new Locale("fr", "FR"));
    }//GEN-LAST:event_txtPlcBirth_std_FRFocusGained

    private void txtNam_Father_FRFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNam_Father_FRFocusGained
        // txtNam_Father_FR.getInputContext().selectInputMethod(new Locale("ar", "DZ"));
        txtNam_Father_FR.getInputContext().selectInputMethod(new Locale("fr", "FR"));

    }//GEN-LAST:event_txtNam_Father_FRFocusGained

    private void txtNam_mother_FRFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNam_mother_FRFocusGained
        txtNam_mother_FR.getInputContext().selectInputMethod(new Locale("fr", "FR"));
    }//GEN-LAST:event_txtNam_mother_FRFocusGained

    private void LastNamMothARTxt_FRFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LastNamMothARTxt_FRFocusGained
        LastNamMothARTxt_FR.getInputContext().selectInputMethod(new Locale("fr", "FR"));
    }//GEN-LAST:event_LastNamMothARTxt_FRFocusGained

    private void firstYrsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_firstYrsStateChanged
        // JOptionPane.showMessageDialog(null, "Test Jspinner Mouse Clicked");
        int NextAn = (int) firstYrs.getValue();
        NextAn = NextAn + 1;
        Annet1.setText(NextAn + "");
    }//GEN-LAST:event_firstYrsStateChanged

    private void buttonView12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonView12ActionPerformed
        ImageResidentToSv = InitialiseImageResident(CLabImg);
        switch (getPatternResident()) {
            case "طالب داخلي":
                Initialise_Student_Resident();
                break;
            case "عـــامـــل":
                Initialise_Employer_Resident(txtNam_EmplInsr, txtSurNam_Empl, DatBirth_Empl, txtPlcBirth_Emply, Gdr_Emp_Mal1, Profession, null, 1);
                break;
//            case "طالب خـــارجــي":
//                Initialise_ExternalStudent_Resident(txt_NumInscSdExt1, txtNam_StdExt, txtSurNam_StdExt, DatBirth_StdExt, txtPlcBirth_StdExt, Gdr_StdExt_Mal, txtBranch_stdExtr, LevelStd_StdExtrn, null, 1);
//                break;
            case "اســـتـاذ":
                Initialise_Professor_Resident();
                break;
        }
    }//GEN-LAST:event_buttonView12ActionPerformed

    private void BtnSaveStdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSaveStdActionPerformed
        int Num_Cardes = (Resident_GlRemplissage.Get_MAX_Numbre_CardRes() + 1);
        SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
        switch (getPatternResident()) { //Select the Resident to insert into DataBase

            case "طالب داخلي":
                if (ControleSaisi.ControleField_Add_Student2(txtNam_std, txtSurNam_std,
                        DatBirth_std, txtPlcBirth_std, txtBranch_std)) {
                    messagerror = new MessageErrorControl(this, true, "تـــأكــد  مــن  تعــبـئـة الــبـيــانـات");
                    messagerror.setVisible(true);
                } else {
                    try {
                        choose = 0;
                        int bacyear = 0;
                        double MoyBac = 0;
                        if (TtxtBacYear.getText().equals("")) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(new Date());
                            bacyear = cal.get(Calendar.YEAR);
                        } else {
                            bacyear = Integer.parseInt(TtxtBacYear.getText());
                        }

                        if (!txtBacMoy1.getText().equals("")) {
                            MoyBac = Double.parseDouble(txtBacMoy1.getText());
                        }//new SimpleDateFormat("dd-MM-yyyy").parse(DatBirth_std.getText())
                        jLabel55.setText(DatBirth_std.getText());
                        String a[] = jLabel55.getText().split("/");
                        String NevDat = a[0] + "-" + a[1] + "-" + a[2];

                        String Num = DatInscrpInUniv.getText();
                        String a2[] = Num.split("/");

                        String NumInsc = a2[0] + "-" + a2[1] + "-" + a2[2];
                        student_Res = new Student_Res(txtNam_std.getText(), txtSurNam_std.getText(), Num_Cardes, "0251" + Num_Cardes, new SimpleDateFormat("dd-MM-yyyy").parse(NevDat) /*new Date(DatBirth_std.getText())*/,
                                txtPlcBirth_std.getText(), Gender, Fill_Data.GetId_From_DB("Id_Ptrn_Res", "Pattern_Person_Res", "Name_Patern", getPatternResident()), 7,
                                txtNam_Father.getText(), txtNam_mother.getText(),// Here attribut of Student
                                txtProfission_Std.getText(), txtProfission_Moth.getText(), txtAddress_Std.getText(),
                                Fill_Data.GetId_From_DB("ID_Wilaya", "Wilaya", "NameWilaya", (String) WilayaList.getSelectedItem()), (String) CombCommuneSlct.getSelectedItem(), txtDairaStd.getText(),
                                Fill_Data.GetId_From_DB("Id_Nationalite", "Nationalite", "Nationalite", (String) National_list.getSelectedItem()),
                                SituationFam, bacyear,
                                MoyBac, txtPlaceGetBac1.getText(),
                                Fill_Data.GetId_From_DB("Id_BranchStd", "Branch_Study", "BranchStd_Name", (String) txtBranch_std.getSelectedItem()),
                                new SimpleDateFormat("dd-MM-yyyy").parse(NumInsc), Fill_Data.GetId_From_DB("Id_Faculty", "Faculty", "NameFact", (String) txtDepa_Std.getSelectedItem()),
                                Fill_Data.GetId_From_DB("Id_LevelStudy", "Level_Study", "DescriptionLevel", (String) LevelStd.getSelectedItem()), 30, txt_NumInsc.getText(),
                                1,
                                "", "", "", "", "", "", LastNamMothARTxt.getText());

                        student_Res.setImageRes(ImageResidentToSv);

                        student_Res.AddRsident();
                        if (student_Res.getValConfiramation() != 0) {
                            LabPrfNumCard_StdRes.setText("" + student_Res.getNumbre_CardRes());
                            LabNameRestToPrint.setText(student_Res.getFirst_name());
                            LabLastNameRestToPrint.setText(student_Res.getLast_name());
                            LabDateBirth_Plc.setText(Format.format(student_Res.getDateBirth()));
                            /// JOptionPane.showMessageDialog(null, "La date est :"+Format.format(student_Res.getDateBirth()));

                            LabPlaceBirth_Res.setText(student_Res.getPlaceBirth());
                            LabBranchStd.setText((String) txtBranch_std.getSelectedItem());
                            new SuccessAlert1(this).setVisible(true);
                            Initialise_Student_Resident();
                            ImageResidentToSv = InitialiseImageResident(CLabImg);
                            System.out.println("residence.Home1.BtnSaveStdActionPerformed()" + "The Student And Resident Is Added  ");
                        } else {
                            messagerror = new MessageErrorControl(this, true, "الرجــاء اعــادة ادخــال البيانات");
                            messagerror.setVisible(true);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(Home1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                break;
            case "اســـتـاذ":
                // JOptionPane.showMessageDialog(null, "The Resident :"+getPatternResident());
//                jLabel55.setText(DatBirth_Prof.getText());
//                String a[] = jLabel55.getText().split("/");
//                String NevDat = a[0] + "-" + a[1] + "-" + a[2];
//
//                try {
//                    professor_res = new Professor_Res(txtNam_Prof.getText(), txtSurNam_Prof.getText(), Num_Cardes, "251" + Num_Cardes, new SimpleDateFormat("dd-MM-yyyy").parse(NevDat), txtPlcBirth_Emply.getText(), Gender, Fill_Data.GetId_From_DB("Id_Ptrn_Res", "Pattern_Person_Res", "Name_Patern", getPatternResident()), 2,
//                            1, 0, 0);
//                } catch (Exception ex) {
//                    Logger.getLogger(Home1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//                }
//
//                professor_res.setImageRes(ImageResidentToSv);
//
//                professor_res.AddRsident();
//                if (professor_res.getValConfiramation() != 0) {
//                    LabPrfNumCard_StdRes.setText("" + professor_res.getNumbre_CardRes());
//                    LabNameRestToPrint.setText(professor_res.getFirst_name());
//                    LabLastNameRestToPrint.setText(professor_res.getLast_name());
//                    LabDateBirth_Plc.setText(Format.format(professor_res.getDateBirth()));
//                    LabPlaceBirth_Res.setText(professor_res.getPlaceBirth());
//                    LabBranchStd.setText("اســـتـاذ");
//                    //  JOptionPane.showMessageDialog(null, "The Value Confirmation :"+student_Res.getValConfiramation());
//                    new SuccessAlert1(this).setVisible(true);
//                    Initialise_Professor_Resident();
//                    ImageResidentToSv = InitialiseImageResident(CLabImg);
//                    System.out.println("residence.Home1.BtnSaveStdActionPerformed()" + "The Student And Resident Is Added  ");
//                } else {
//                    messagerror = new MessageErrorControl(this, true, "الرجــاء اعــادة ادخــال البيانات");
//                    messagerror.setVisible(true);
//                }

                break;
            case "طالب خـــارجــي":
//
//                jLabel55.setText(DatBirth_StdExt.getText());
//                //JOptionPane.showMessageDialog(null, "The Resident :"+getPatternResident()+"Date Birth is :"+DatBirth_StdExt.getText());
//                String b[] = jLabel55.getText().split("/");
//                String NevDatb = b[0] + "-" + b[1] + "-" + b[2];
//                // JOptionPane.showMessageDialog(null, "The Resident :"+getPatternResident());
//                try {
//                    externalstudent = new ExternalStudent(txtNam_StdExt.getText(), txtSurNam_StdExt.getText(), 1, "", new SimpleDateFormat("dd-MM-yyyy").parse(NevDatb), txtPlcBirth_StdExt.getText(),
//                            Gender, Fill_Data.GetId_From_DB("Id_Ptrn_Res", "Pattern_Person_Res", "Name_Patern", getPatternResident()),
//                            8, txt_NumInscSdExt.getText(), Fill_Data.GetId_From_DB("Id_BranchStd", "Branch_Study", "BranchStd_Name", (String) txtBranch_stdExtr.getSelectedItem()),
//                            Fill_Data.GetId_From_DB("Id_LevelStudy", "Level_Study", "DescriptionLevel", (String) LevelStd_StdExtrn.getSelectedItem()), new Date(), 0);
//                } catch (Exception e) {
//                }
//                externalstudent.setImageRes(ImageResidentToSv);
//                externalstudent.AddRsident();
//                if (externalstudent.getValConfiramation() != 0) {
//                    LabPrfNumCard_StdRes.setText("" + externalstudent.getNumbre_CardRes());
//                    LabNameRestToPrint.setText(externalstudent.getFirst_name());
//                    LabLastNameRestToPrint.setText(externalstudent.getLast_name());
//                    LabDateBirth_Plc.setText(Format.format(externalstudent.getDateBirth()));
//                    LabPlaceBirth_Res.setText(externalstudent.getPlaceBirth());
//                    LabBranchStd.setText((String) txtBranch_stdExtr.getSelectedItem());
//
//                    new SuccessAlert1(this).setVisible(true);
//                    Initialise_ExternalStudent_Resident(txt_NumInscSdExt, txtNam_StdExt, txtSurNam_StdExt, DatBirth_StdExt, txtPlcBirth_StdExt, Gdr_StdExt_Mal, txtBranch_stdExtr, LevelStd_StdExtrn, null, 1);
//                    ImageResidentToSv = InitialiseImageResident(CLabImg);
//                } else {
//                    messagerror = new MessageErrorControl(this, true, "الرجــاء اعــادة ادخــال البيانات");
//                    messagerror.setVisible(true);
//                }

                break;
            case "عـــامـــل":
                // JOptionPane.showMessageDialog(null, "The Resident :"+getPatternResident());
                //  Employer_Resident=new Employer();
                jLabel55.setText(DatBirth_Empl.getText());
                String c[] = jLabel55.getText().split("/");
                String NevDatc = c[0] + "-" + c[1] + "-" + c[2];
                try {
                    Employer_Resident = new Employer(txtNam_EmplInsr.getText(),
                            txtSurNam_Empl.getText(),
                            1,
                            "",
                            new SimpleDateFormat("dd-MM-yyyy").parse(NevDatc),
                            txtPlcBirth_Emply.getText(),
                            Gender,
                            Fill_Data.GetId_From_DB("Id_Ptrn_Res", "Pattern_Person_Res", "Name_Patern", getPatternResident()),
                            10,
                            Fill_Data.GetId_From_DB("ID_Profession", "Profession", "Profession", (String) Profession.getSelectedItem()),
                            3, 3);
                } catch (ParseException e) {
                }

                Employer_Resident.setImageRes(ImageResidentToSv);
                Employer_Resident.AddRsident();
                if (Employer_Resident.getValConfiramation() != 0) {
                    LabPrfNumCard_StdRes.setText("" + Employer_Resident.getNumbre_CardRes());
                    LabNameRestToPrint.setText(Employer_Resident.getFirst_name());
                    LabLastNameRestToPrint.setText(Employer_Resident.getLast_name());
                    LabDateBirth_Plc.setText(Format.format(Employer_Resident.getDateBirth()));
                    LabPlaceBirth_Res.setText(Employer_Resident.getPlaceBirth());
                    LabBranchStd.setText((String) Profession.getSelectedItem());
                    new SuccessAlert1(this).setVisible(true);
                    Initialise_Employer_Resident(txtNam_EmplInsr, txtSurNam_Empl, DatBirth_Empl, txtPlcBirth_Emply, Gdr_Emp_Mal1, Profession, null, 1);
                    ImageResidentToSv = InitialiseImageResident(CLabImg);
                } else {
                    messagerror = new MessageErrorControl(this, true, "الرجــاء اعــادة ادخــال البيانات");
                    messagerror.setVisible(true);
                }
                break;

            default:
                JOptionPane.showMessageDialog(null, "The Default :" + getPatternResident());
                break;
        }
        student_ResRemplissage.SetVarPht(0);
    }//GEN-LAST:event_BtnSaveStdActionPerformed

    private void RadBtnSingleStdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadBtnSingleStdActionPerformed
        if (RadBtnSingleStd.isSelected()) {

            SituationFam = RadBtnSingleStd.getText();
            TtxtBacYear.requestFocus();
        } else {
            SituationFam = RadBtnMarriedStd.getText();
            TtxtBacYear.requestFocus();
        }
    }//GEN-LAST:event_RadBtnSingleStdActionPerformed

    private void RadBtnMarriedStdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadBtnMarriedStdActionPerformed
        if (RadBtnMarriedStd.isSelected()) {

            SituationFam = RadBtnMarriedStd.getText();
            TtxtBacYear.requestFocus();
        } else {
            SituationFam = RadBtnSingleStd.getText();
            TtxtBacYear.requestFocus();
        }
    }//GEN-LAST:event_RadBtnMarriedStdActionPerformed

    private void BtnRdMaleStdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRdMaleStdActionPerformed
        if (BtnRdMaleStd.isSelected()) {

            Gender = 1;
            RadBtnSingleStd.requestFocus();
        } else {

            RadBtnSingleStd.requestFocus();
            Gender = 2;
        }
    }//GEN-LAST:event_BtnRdMaleStdActionPerformed

    private void BtnRdFemaleStdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRdFemaleStdActionPerformed
        if (BtnRdFemaleStd.isSelected()) {

            Gender = 2;
            RadBtnSingleStd.requestFocus();
        } else {

            Gender = 1;
        }
    }//GEN-LAST:event_BtnRdFemaleStdActionPerformed

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        FormExterieurResidents frmExtr = new FormExterieurResidents();
        frmExtr.setVisible(true);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void Sti_SingleStdUpdtForm_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sti_SingleStdUpdtForm_xActionPerformed
        if (Sti_SingleStdUpdtForm_x.isSelected() == true) {

            SituationFam = Sti_SingleStdUpdtForm_x.getText();
//            Std_MariedStdUpdtForm.setSelected(false);

        } else {
//            Std_MariedStdUpdtForm.setSelected(true);
            SituationFam = Std_MariedStdUpdtForm_x.getText();
            //Std_Maried2.getText();
        }
    }//GEN-LAST:event_Sti_SingleStdUpdtForm_xActionPerformed

    private void Std_MariedStdUpdtForm_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Std_MariedStdUpdtForm_xActionPerformed
        if (Std_MariedStdUpdtForm_x.isSelected() == true) {

            SituationFam = Std_MariedStdUpdtForm_x.getText();
            //Sti_SingleStdUpdtForm.setSelected(false);

        } else {
            //Sti_SingleStdUpdtForm.setSelected(true);
            SituationFam = Sti_SingleStdUpdtForm_x.getText();
            //Std_Maried2.getText();
        }
    }//GEN-LAST:event_Std_MariedStdUpdtForm_xActionPerformed

    private void Search_Field_upd_stdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Search_Field_upd_stdFocusGained
        if (Search_Field_upd_std.getText().equals("البحث")) {
            Search_Field_upd_std.setText("");

        }
    }//GEN-LAST:event_Search_Field_upd_stdFocusGained

    private void check_Std1_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_Std1_xActionPerformed

        if (check_Std1_x.isSelected()) {
            Choice_Resident(check_Std1_x, check_Prof1_x, check_StdExt1_x, check_Emp1_x, PanAllUpdateResident, InfoStdToUpdate_1, TabResident, "عــدد الطلبـــة المقيمين");
            initialisationFormUpdate_InterStudent();
            InitFormUpdIntlStd(false);
        }

    }//GEN-LAST:event_check_Std1_xActionPerformed

    private void check_Prof1_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_Prof1_xActionPerformed
        Choice_Resident(check_Prof1_x, check_Std1_x, check_StdExt1_x, check_Emp1_x, PanAllUpdateResident, PanProfToUpdate,
                TabResident, "عـــدد الاساتذة");
        InitialiseFormUpdateProfessor();
        InitFormUpdIntlProf(false);

        Gdr_Prf_Malp.setSelected(true);

        //  SituationFam=Gdr_Prf_Malp.getText();
        Gender = 1;
    }//GEN-LAST:event_check_Prof1_xActionPerformed

    private void check_StdExt1_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_StdExt1_xActionPerformed
        Choice_Resident(check_StdExt1_x, check_Std1_x, check_Prof1_x, check_Emp1_x, PanAllUpdateResident, PanStdExtToUpdate, TabResident, "عــدد الطلبـة الخارجيين");
        //initialisationFormUpdate_InterStudent();

        Initialise_ExternalStudent_Resident(txt_NumInscSdExt1, txtNam_StdExt1, txtSurNam_StdExt1, DatBirth_StdExt1,
                txtPlcBirth_StdExt1, Gdr_Prf_Malp1, Branch_stdExternStdUpdtForm, LevelStdExtUpdtForm, CaseResident2, 2);
        InitFormUpdIntlExtStd(false);

        Gdr_Prf_Malp1.setSelected(true);
        Gender = 1;
    }//GEN-LAST:event_check_StdExt1_xActionPerformed

    private void check_Emp1_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_Emp1_xActionPerformed
        // TODO add your handling code here:
        Choice_Resident(check_Emp1_x, check_Std1_x, check_StdExt1_x, check_Prof1_x, PanAllUpdateResident, PanSaisiEmp1, TabResident, "عـــدد العمــال");
        Initialise_Employer_Resident(txtNam_Prof7, txtSurNam_Prof7,
                DatBirth_Prof7, txtPlcBirth_Prof7, Gdr_Emp_MalE, ProfessionToUpdate, CaseResident3, 2);
        InitFormUpdIntlEmploy(false);

        Gdr_Emp_MalE.setSelected(true);

        Gender = 1;
    }//GEN-LAST:event_check_Emp1_xActionPerformed

    private void check_Std2_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_Std2_xActionPerformed
        //chengement   basmach
        //setPatternResident(check_Std2.getText());
        setPatternResident(check_Std2_x.getText());
        PaternToChangeCase = check_Std2_x.getText();
        IndiceSelected = 1;
        Fill_Data.FillingCase(CaseN, "طالب داخلي");
        Choice_Resident(check_Std2_x, check_Prof2_x, check_StdExt2_x, check_Emp2_x, PanAllResidentToConsult, panResidentToConsult, TabResidentToConsult, "عــدد الطلبـــة المقيمين"
        );
        student_ResRemplissage.DisplayAllResidentInTablToConsult(TabResidentToConsult, LabTexTypCount, "عــدد الطلبـــة المقيمين", LabCountTabConsult, getPatternResident());
        cleanPanCangeCase();
        jButton7.setEnabled(true);
        //TabResidentToConsult
        //student_ResRemplissage.FilterResident("", TabResidentToConsult,(DefaultTableModel) TabResidentToConsult.getModel() );
        NumCardToUpdt1.setText("");
        student_ResRemplissage.FilterResident("", TabProfToConsult, (DefaultTableModel) TabProfToConsult.getModel());
        student_ResRemplissage.FilterResident("", TabExtStdToConsult, (DefaultTableModel) TabExtStdToConsult.getModel());
        student_ResRemplissage.FilterResident("", TabEmpToConsult, (DefaultTableModel) TabEmpToConsult.getModel());
    }//GEN-LAST:event_check_Std2_xActionPerformed

    private void check_StdExt2_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_StdExt2_xActionPerformed
        //chengement
        setPatternResident(check_StdExt2_x.getText());
        Choice_Resident(check_StdExt2_x, check_Prof2_x, check_Std2_x, check_Emp2_x, PanAllResidentToConsult, PanEXtrStdToConsult, TabExtStdToConsult, "عدد الطلبة الخارجيين :");
        //check_StdExt2
        PaternToChangeCase = check_StdExt2_x.getText();
        //setPatternResident(check_StdExt2.getText());
        IndiceSelected = 2;
        Fill_Data.FillingCase(CaseN, "طالب خـــارجــي");
        student_ResRemplissage.DisplayAllResidentInTablToConsult(TabExtStdToConsult, LabTexTypCount, "عدد الطلبة الخارجيين  :", LabCountTabConsult, getPatternResident());
        cleanPanCangeCase();

        jButton7.setEnabled(false);
        NumCardToUpdt1.setText("");
        student_ResRemplissage.FilterResident("", TabResidentToConsult, (DefaultTableModel) TabResidentToConsult.getModel());

        student_ResRemplissage.FilterResident("", TabProfToConsult, (DefaultTableModel) TabProfToConsult.getModel());
        //student_ResRemplissage.FilterResident("", TabExtStdToConsult,(DefaultTableModel) TabExtStdToConsult.getModel() );
        student_ResRemplissage.FilterResident("", TabEmpToConsult, (DefaultTableModel) TabEmpToConsult.getModel());

    }//GEN-LAST:event_check_StdExt2_xActionPerformed

    private void check_Prof2_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_Prof2_xActionPerformed
        setPatternResident(check_Prof2_x.getText());
        Choice_Resident(check_Prof2_x, check_Std2_x, check_StdExt2_x, check_Emp2_x, PanAllResidentToConsult, PanProfToConsult, TabProfToConsult, "عــددالاسـاتذة المقيمين:");
        //setPatternResident(check_Prof2.getText());
        //check_Prof2
        PaternToChangeCase = check_Prof2_x.getText();
        IndiceSelected = 3;
        Fill_Data.FillingCase(CaseN, "اســـتـاذ");
        student_ResRemplissage.DisplayAllResidentInTablToConsult(TabProfToConsult, LabTexTypCount, "عــددالاسـاتذة المقيمين:", LabCountTabConsult, getPatternResident());
        cleanPanCangeCase();
        jButton7.setEnabled(true);
        NumCardToUpdt1.setText("");
        student_ResRemplissage.FilterResident("", TabResidentToConsult, (DefaultTableModel) TabResidentToConsult.getModel());

        // student_ResRemplissage.FilterResident("", TabProfToConsult,(DefaultTableModel) TabProfToConsult.getModel() );
        student_ResRemplissage.FilterResident("", TabExtStdToConsult, (DefaultTableModel) TabExtStdToConsult.getModel());
        student_ResRemplissage.FilterResident("", TabEmpToConsult, (DefaultTableModel) TabEmpToConsult.getModel());

    }//GEN-LAST:event_check_Prof2_xActionPerformed

    private void check_Emp2_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_Emp2_xActionPerformed
        //chengement check_Emp2
        setPatternResident(check_Emp2_x.getText());
        Choice_Resident(check_Emp2_x, check_StdExt2_x, check_Prof2_x, check_Std2_x, PanAllResidentToConsult, PanEmpToConsult, TabEmpToConsult, "عــددالعمال:");
        PaternToChangeCase = check_Emp2_x.getText();
        //setPatternResident(check_Emp2.getText());
        IndiceSelected = 4;
        Fill_Data.FillingCase(CaseN, "عـــامـــل");
        student_ResRemplissage.DisplayAllResidentInTablToConsult(TabEmpToConsult, LabTexTypCount, "عدد العمال:", LabCountTabConsult, getPatternResident());
        cleanPanCangeCase();
        NumCardToUpdt1.setText("");
        student_ResRemplissage.FilterResident("", TabResidentToConsult, (DefaultTableModel) TabResidentToConsult.getModel());

        student_ResRemplissage.FilterResident("", TabProfToConsult, (DefaultTableModel) TabProfToConsult.getModel());
        student_ResRemplissage.FilterResident("", TabExtStdToConsult, (DefaultTableModel) TabExtStdToConsult.getModel());
        //student_ResRemplissage.FilterResident("", TabEmpToConsult,(DefaultTableModel) TabEmpToConsult.getModel() );

        jButton7.setEnabled(false);

    }//GEN-LAST:event_check_Emp2_xActionPerformed

    private void LastName_ResidentFrUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastName_ResidentFrUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LastName_ResidentFrUpActionPerformed

    private void txtSurNam_std5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSurNam_std5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSurNam_std5ActionPerformed

    private void buttonView13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonView13ActionPerformed
        new Main();
        R.dispose();
        Gs.dispose();
        F.dispose();
        dispose();
    }//GEN-LAST:event_buttonView13ActionPerformed

    /**
     * ************************ Initialisation Student_Resident
     * **************************
     */
    public void Initialise_Student_Resident() {

        txt_NumInsc.setText("رقــم تــسجـيــل الــبـكــالــوريــا ");
        txt_NumInsc.setForeground(Color.gray);
        txtNam_std.setText("الاسم");
        txtNam_std.setForeground(Color.gray);
        txtSurNam_std.setText("الـلـــقـــب");
        txtSurNam_std.setForeground(Color.gray);
        DatBirth_std.setText("");
        DatBirth_std.setForeground(Color.black);
        txtPlcBirth_std.setText("مــكـان الـميــلاد");
        txtPlcBirth_std.setForeground(Color.gray);
        txtNam_Father.setText("اسم الأب");
        txtNam_Father.setForeground(Color.gray);
        txtProfission_Std.setText("مهنة الاب");
        txtProfission_Std.setForeground(Color.gray);
        txtNam_mother.setText("اسم  الام");
        txtNam_mother.setForeground(Color.gray);
        LastNamMothARTxt.setText("لقب  الام");
        LastNamMothARTxt.setForeground(Color.gray);

        txtProfission_Moth.setText("مهنة الام");
        txtProfission_Moth.setForeground(Color.gray);
        txtAddress_Std.setText("العـنوان");
        txtAddress_Std.setForeground(Color.gray);
        WilayaList.setSelectedIndex(0);

        txtDairaStd.setText("الدائرة");
        txtDairaStd.setForeground(Color.gray);
        CombCommuneSlct.setSelectedIndex(0);
        //txtCommuneStd.setForeground(Color.BLACK);
        National_list.setSelectedIndex(0);
        BtnRdMaleStd.setSelected(true);
        Gender = 1;

        RadBtnSingleStd.setSelected(false);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        TtxtBacYear.setText(year + "");
        TtxtBacYear.setForeground(Color.gray);
        txtBacMoy1.setText("00.00");
        txtBacMoy1.setForeground(Color.gray);
        txtPlaceGetBac1.setText("مــكـان الــحــصـول عــلــي الــبــكـالـــوريـــا");
        txtPlaceGetBac1.setForeground(Color.BLACK);
        txtBranch_std.setSelectedIndex(0);
        DatInscrpInUniv.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        LevelStd.setSelectedIndex(0);
        txtDepa_Std.setSelectedIndex(0);

//        LastNamMothARTxt.setText("..............................");
//        LastNamMothARTxt.setForeground(Color.black);
    }

    /**
     * *********************************************************************************
     */
    public void Initialise_Professor_Resident() {

//        txtNam_Prof.setText("ادخل الاسم");
//        txtNam_Prof.setForeground(Color.gray);
//        txtSurNam_Prof.setText("ادخل اللقب");
//        txtSurNam_Prof.setForeground(Color.gray);
//        DatBirth_Prof.setText("");
//        txtPlcBirth_Prof.setText("ادخل مكان الميلاد");
//        txtPlcBirth_Prof.setForeground(Color.gray);
//        Gdr_Prf_Mal.setSelected(true);
//        Gender = 1;

        /*
          File   FileName=new File("src\\residence\\Image\\imageRes.png");
      ImageIcon imgeicon=new ImageIcon (new ImageIcon(FileName.getAbsolutePath()).getImage().getScaledInstance(Img_Std.getWidth(), Img_Std.getHeight(), Image.SCALE_SMOOTH));
          Img_Std.setIcon(imgeicon);
          
          
          ImageResidentToSv=FileName;*/
    }

    /**
     * ********************************************************************************
     */
    public void Initialise_ExternalStudent_Resident(JTextField txt_NumInscSdExt, JTextField txtNam_StdExt, JTextField txtSurNam_StdExt, JFormattedTextField DatBirth_StdExt, JTextField txtPlcBirth_StdExt, JCheckBox Gdr_StdExt_Mal,
            JComboBox txtBranch_stdExtr, JComboBox LevelStd_StdExtrn, JComboBox caseResident, int ValChoice) {
        txt_NumInscSdExt.setText("رقــم التــسجـيــل  ");
        txt_NumInscSdExt.setForeground(Color.gray);
        txtNam_StdExt.setText("ادخل الاسم");
        txtNam_StdExt.setForeground(Color.gray);
        txtSurNam_StdExt.setText("ادخل اللقب");
        txtSurNam_StdExt.setForeground(Color.gray);
        DatBirth_StdExt.setText("");
        txtPlcBirth_StdExt.setText("ادخل مكان الميلاد");
        txtPlcBirth_StdExt.setForeground(Color.gray);
        Gdr_StdExt_Mal.setSelected(true);
        Gender = 1;
        txtBranch_stdExtr.setSelectedIndex(0);
        LevelStd_StdExtrn.setSelectedIndex(0);
        if (ValChoice == 2) {
            NumCardToUpdt3.setText("");
            caseResident.setSelectedIndex(0);
            Gdr_Prf_femalp1.setSelected(false);
            ImageResidentToSv = InitialiseImageResident(Img_StdUpdate1);
        }
    }

    /**
     * ********************************************************************************
     */
    public void Initialise_Employer_Resident(JTextField txtNam_EmplInsr, JTextField txtSurNam_Empl, JFormattedTextField DatBirth_Empl, JTextField txtPlcBirth_Emply, JCheckBox Gdr_Emp_Mal1, JComboBox Profession, JComboBox CaseResident, int ValChoice) {

        //1 For Add Employer 
        //2 For Update Employer
        txtNam_EmplInsr.setText("ادخل الاسم");
        txtNam_EmplInsr.setForeground(Color.gray);
        txtSurNam_Empl.setText("ادخل اللقب");
        txtSurNam_Empl.setForeground(Color.gray);
        DatBirth_Empl.setText("");
        txtPlcBirth_Emply.setText("ادخل مكان الميلاد");
        txtPlcBirth_Emply.setForeground(Color.gray);
        Gdr_Emp_Mal1.setSelected(true);
        Gender = 1;
        Profession.setSelectedIndex(0);
        if (ValChoice == 2) {
            CaseResident.setSelectedIndex(0);
            NumCardToUpdt4.setText("");
            ImageResidentToSv = InitialiseImageResident(Img_EmpUpdate);
        }

    }

    /*
       public void Initialise_Employer_ResidentEnable(boolean EtatEnable
               /*JTextField txtNam_EmplInsr,JTextField txtSurNam_Empl,JFormattedTextField DatBirth_Empl,JTextField txtPlcBirth_Emply,JCheckBox Gdr_Emp_Mal1,JComboBox Profession,JComboBox CaseResident,JLabel jLabel133,JLabel jLabel153,int ValChoice,boolean EtatEnable*/
 /*){
    txtNam_Prof7.setEnabled(EtatEnable);
    txtSurNam_Prof7.setEnabled(EtatEnable);
    DatBirth_Prof7.setEnabled(EtatEnable);
    txtPlcBirth_Prof7.setEnabled(EtatEnable);
    Gdr_Emp_MalE.setEnabled(EtatEnable);
    ProfessionToUpdate.setEnabled(EtatEnable);
            CaseResident3.setEnabled(EtatEnable);
            jLabel153.setEnabled(EtatEnable);
            jLabel133.setEnabled(EtatEnable);
           Initialise_Employer_Resident(txtNam_Prof7,txtSurNam_Prof7,
                    DatBirth_Prof7, txtPlcBirth_Prof7, Gdr_Emp_MalE,ProfessionToUpdate, CaseResident3,2);
    
    }*/
    /**
     * **************************************************
     */
    public void InitFormUpdIntlStd(boolean Etat) {
        Component cmp[] = InfoStdToUpdate_1.getComponents();
        for (Component jLabel : cmp) {

            if (jLabel != Img_StdUpdate && jLabel != jLabel115 && jLabel != jLabel113) {
                jLabel.setEnabled(Etat);
            }
        }
        jLabel115.setVisible(Etat);
        jLabel113.setVisible(Etat);
        BtnSavUpdt.setEnabled(Etat);
        btnUpdRes.setEnabled(Etat);
    }

    public void InitFormUpdIntlProf(boolean Etat) {
        Component cmp[] = panSaisiInfoProf.getComponents();
        for (Component jLabel : cmp) {

            if (jLabel != Img_profupdate && jLabel != jLabel128 && jLabel != jLabel128) {
                jLabel.setEnabled(Etat);
            }
        }
        jLabel128.setVisible(Etat);
        jLabel129.setVisible(Etat);
        BtnSavUpdt.setEnabled(Etat);
        btnUpdRes.setEnabled(Etat);
    }

    /**
     * ******************* Initialisation Profile_Account Student
     * ********************
     */
    public void InitFormUpdIntlExtStd(boolean Etat) {
        Component cmp[] = PanSaisiInfoStdExt1.getComponents();
        for (Component jLabel : cmp) {

            if (jLabel != Img_StdUpdate1 && jLabel != jLabel131 && jLabel != jLabel132) {
                jLabel.setEnabled(Etat);
            }
        }

        jLabel131.setVisible(Etat);
        jLabel132.setVisible(Etat);
        BtnSavUpdt.setEnabled(Etat);
        btnUpdRes.setEnabled(Etat);

    }

    public void InitFormUpdIntlEmploy(boolean Etat) {
        Component cmp[] = PanSaisInfoEmp1.getComponents();
        for (Component jLabel : cmp) {

            if (jLabel != Img_EmpUpdate && jLabel != jLabel133 && jLabel != jLabel153) {
                jLabel.setEnabled(Etat);
            }
        }

        jLabel133.setVisible(Etat);
        jLabel153.setVisible(Etat);
        BtnSavUpdt.setEnabled(Etat);
        btnUpdRes.setEnabled(Etat);
    }

    /**
     * *************** Fin Initialisation Profile_Account
     * Student********************
     */
    /**
     * ******************************InitialiseFormUpdateProfessor********************
     */
    public void InitialiseFormUpdateProfessor() {
        NumCardToUpdt2.setText("");
        CaseResidentProfUpdtForm.setSelectedIndex(0);
        txtNam_Prof1.setText("ادخل الاسم");
        txtSurNam_Prof1.setText("ادخل اللقب");
        DatBirth_Pro.setText("01/01/2001");
        ftxtPlcBirth_Prof.setText("ادخل مكان الميلاد");
        Gdr_Prf_Malp.setSelected(true);
        Gender = 1;
        Gdr_Prf_femalp1.setSelected(false);

        ImageResidentToSv = InitialiseImageResident(Img_profupdate);
    }

    /**
     * ****************************Zone Design App*****************************
     */
    public void ChoixPanSrvdetaille(JPanel parent, JPanel fils) {

        int i = 0;

        fils.setVisible(true);

        while (i < parent.getComponentCount()) {

            if (!parent.getComponent(i).equals(fils)) {

                parent.getComponent(i).setVisible(false);
            }
            i++;
        }

    }

    /*---------------------------------------------------------*/
//    public void Remplir_TableMenu() {
//
////        TableMenuItem[0] = ItemMenRegistration;
////        TableMenuItem[1] = ItemMenReserveRom;
////        TableMenuItem[2] = ItemMenConsult;
////        TableMenuItem[3] = ItemMenReport;
////        TableMenuItem[4] = ItemMenUpdate;
////        TableMenuItem[5] = ItemMenSystemConf;
//        TableMenuItem[6] = ItemMenLogout;
//    }
//    public void BackgroundMenu(JLabel l) {// for menu principal
//        int i = 0, sizeTable = TableMenuItem.length;
//
//        sizeTable = 7;
//        while (i < sizeTable) {
//            if (TableMenuItem[i] == l) {
//                // 51,204,255 [153,153,153] [102,204,255
//                l.setOpaque(true);
//
//                //l.setBackground(new Color(220,220,220));
//                l.setBackground(new Color(153, 153, 153));
//            } else {
//                TableMenuItem[i].setBackground(new Color(255, 255, 255));
//            }
//            i++;
//        }
//    }
    /**
     * ***********************************************************
     */
    public void BackgroundMenu(JLabel l, JPanel pan) {// for menu principal
        int i = 0;
        Component TableMenuLabelPavilon[] = pan.getComponents();
        while (i < TableMenuLabelPavilon.length) {
            if (TableMenuLabelPavilon[i] == l) {
                // 51,204,255 [153,153,153] [102,204,255
                l.setOpaque(true);

                //l.setBackground(Color.red);
                l.setBackground(new Color(153, 153, 153));
                l.setForeground(new Color(255, 255, 255));
            } else {
                TableMenuLabelPavilon[i].setBackground(new Color(255, 255, 255));
                TableMenuLabelPavilon[i].setForeground(new Color(0, 0, 0));
            }
            i++;
        }
    }

    /**
     * ****************************Fin Zone Design****************************
     */
    // This Function For deselected Jcheckbox
    /**
     * ********************************************************************
     */
    private void GoupRadioBtnComponents() {

        GrpBtnItemsUpd_Form = new ButtonGroup();
        GrpBtnItemsUpd_Form.add(check_Std1_x);
        GrpBtnItemsUpd_Form.add(check_Prof1_x);
        GrpBtnItemsUpd_Form.add(check_StdExt1_x);
        GrpBtnItemsUpd_Form.add(check_Emp1_x);

    }

    public void Choice_Resident_Sais(JCheckBox j1, JCheckBox j2, JCheckBox j3, JCheckBox j4, JPanel panSai) {
        if (j1.isSelected() && (!j2.isSelected() || !j3.isSelected() || !j4.isSelected())) {
            j1.setBackground(new Color(0, 153, 0));
            j2.setSelected(false);
            j2.setBackground(new Color(255, 255, 255));
            j3.setSelected(false);
            j3.setBackground(new Color(255, 255, 255));
            j4.setSelected(false);
            j4.setBackground(new Color(255, 255, 255));
            ChoixPanSrvdetaille(Pan_All_PansSais_, panSai);
            setPatternResident(j1.getText());
            //JOptionPane.showMessageDialog(null, "The Patern is:"+PatternResident);
            //NumOrder.setText(""+(Resident_GlRemplissage.Get_MAX_Numbre_CardRes()+1));
        } else {
            messagerror = new MessageErrorControl(this, true, "الرجاء اختر نوع المقيــــم ");
            messagerror.setVisible(true);
            j1.setSelected(true);
        }
    }

    /**
     * *********************************************************************
     */
    public void Choice_Resident(JRadioButton j1, JRadioButton j2, JRadioButton j3, JRadioButton j4, JPanel Parent, JPanel Child, JTable Tab, String TypeRes) {
        if (j1.isSelected() && (!j2.isSelected() || !j3.isSelected() || !j4.isSelected())) {
            j1.setBackground(new Color(0, 153, 0));
            j2.setSelected(false);
            j2.setBackground(new Color(255, 255, 255));
            j3.setSelected(false);
            j3.setBackground(new Color(255, 255, 255));
            j4.setSelected(false);
            j4.setBackground(new Color(255, 255, 255));
            ChoixPanSrvdetaille(Parent, Child);
            setPatternResident(j1.getText());
            Resident_GlRemplissage.DisplayAllResidentInTable(Tab, Fill_Data.GetId_From_DB("Id_Ptrn_Res", "Pattern_Person_Res", "Name_Patern", getPatternResident()), LabNamesForRes, TypeRes, CountTabStdIn);
            if (Parent != PanAllResidentToConsult) {

                Resident_GlRemplissage.DisplayAllResidentInTable(Tab, Fill_Data.GetId_From_DB("Id_Ptrn_Res", "Pattern_Person_Res", "Name_Patern", getPatternResident()), LabNamesForRes, TypeRes, CountTabStdIn);
            }

            //JOptionPane.showMessageDialog(null, "The Patern is:"+PatternResident);
            // NumOrder.setText(""+(Resident_GlRemplissage.Get_MAX_Numbre_CardRes()+1));
        } else {
            messagerror = new MessageErrorControl(this, true, "الرجاء اختر نوع المقيــــم ");
            messagerror.setVisible(true);
            j1.setSelected(true);
        }
    }

    public File InitialiseImageResident(JLabel labImage) {
        File fileImage = new File("D:\\Image_Signature\\imageRes.png");
        String FileName = fileImage.getAbsolutePath();
        System.out.println("residence.Resident_Gl.UploadPictResident()" + FileName);
        ImageIcon imgeicon = new ImageIcon(new ImageIcon(FileName).getImage().getScaledInstance(labImage.getWidth(), labImage.getHeight(), Image.SCALE_SMOOTH));
        labImage.setIcon(imgeicon);

        return fileImage;
    }

    /**
     * ******************************Upload Pict
     * *******************************
     */
    String getPatternResident() {

        return PatternResident;
    }
    int ValChoice;

    public File UploadPictResident(JLabel label) throws IOException {
        String path = "D:\\Photo_residents\\";

        File fileImage = new File("D:\\Image_Signature\\imageRes.png");

        File dir = new File(path);
        File[] files = dir.listFiles();

        if (files == null || files.length == 0) {
            messagerror = new MessageErrorControl(this, true, "لا توجــد صــورة الرجــاء اضــافة صــورة ");
            messagerror.setVisible(true);
            student_ResRemplissage.SetVarPht(0);
            return fileImage;
        } else {

            File lastModifiedFile = files[0];
            for (int i = 1; i < files.length; i++) {
                if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                    lastModifiedFile = files[i];
                    System.out.println("residence.Home1.UploadPictResident()" + lastModifiedFile.getName());
                }
            }
            // JOptionPane.showMessageDialog(null, "is a  FILES Gets in Folder And The File is :"+lastModifiedFile.getAbsolutePath());
            BufferedImage bufImg = ImageIO.read(lastModifiedFile);
            label.setIcon(new ImageIcon(new ImageIcon(bufImg).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
            student_ResRemplissage.SetVarPht(1);
            return lastModifiedFile;

        }

    }

    public File SelectPictToResident(JLabel labImg) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File fileImage = chooser.getSelectedFile();
        if (fileImage != null) {
            BufferedImage bufImg = null;
            try {
                bufImg = ImageIO.read(fileImage);
            } catch (IOException ex) {
                Logger.getLogger(Home1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            labImg.setIcon(new ImageIcon(new ImageIcon(bufImg).getImage().getScaledInstance(labImg.getWidth(), labImg.getHeight(), Image.SCALE_SMOOTH)));
            student_ResRemplissage.SetVarPht(1);
            return fileImage;
        } else {
            student_ResRemplissage.SetVarPht(0);
            return ImageResidentToSv;
        }
    }

    /*
     * ********************************************************
     */
//    public void DeSelectedPatern(JCheckBox chk1, JCheckBox chk2, JCheckBox chk3, JCheckBox chk4) {
//        chk1.setSelected(false);
//        chk1.setBackground(Color.white);
//        chk2.setSelected(false);
//        chk2.setBackground(Color.white);
//
//        chk3.setSelected(false);
//        chk3.setBackground(Color.white);
//        chk4.setSelected(false);
//        chk4.setBackground(Color.white);
//    }
    /**
     * *****************************************************************************
     */
    public void initialisationFormUpdate_InterStudent() {
        // JOptionPane.showMessageDialog(null, "j initialise");
        NumCardToUpdt.setText("");

        CaseResident.setSelectedIndex(6);
        txtNam_std5.setText("");
        txtSurNam_std5.setText("");
        DatBirth_stdUpdtForm.setText("");
        txtPlcBirth_stdUpdtForm.setText("");
        CheckMaleStdUpdtForm.setSelected(true);
        checkFemalStdUpdtForm.setSelected(false);
        txt_NumInscUpdtForm.setText("رقــم تــسجـيــل الــبـكــالــوريــا ");
        txt_NumInscUpdtForm.setForeground(new Color(204, 204, 204));
        txtNam_FatherStdUpdtForm.setText("");
        txtNam_MotherStdUpdtForm.setText("");
        txtProfissionfath_StdUpdtForm.setText("");
        txtProfission_MothStdUpdtForm.setText("");
        txtAddress_StdUpdtForm.setText("");
        WilayaListUpdtForm.setSelectedIndex(0);
        txtDairaStdUpdtForm.setText("");
        txtCommuneStdUpdtForm.setText("");
        National_listStdUpdtForm.setSelectedIndex(0);
        Sti_SingleStdUpdtForm_x.setSelected(true);
        Gender = 1;
        Std_MariedStdUpdtForm_x.setSelected(false);

        TtxtBacYearStdUpdtForm.setText("2018");
        TtxtBacYearStdUpdtForm.setForeground(new Color(204, 204, 204));
        txtBacMoy2.setText("00.00");
        txtBacMoy2.setForeground(new Color(204, 204, 204));
        txtPlaceGetBacStdUpdtForm.setText("");
        Branch_stdUpdtForm.setSelectedIndex(0);
        Depa_StdUpdtForm.setSelectedIndex(0);
        LevelStdUpdtForm.setSelectedIndex(0);

        LastName_ResidentFrUp.setText("");
        PlaceBirthFrStdUpdForm.setText("");
        Name_ResidentFrUp.setText("");
        Name_FatherFrUp.setText("");
        Name_MotherFrUp.setText("");
        LastName_MotheFrUp.setText("");
        NamMrArStdUpdtForm.setText("");
        Img_StdUpdate.setIcon(new ImageIcon(getClass().getResource("/residence/Image/imageRes.png")));
    }

    public void setPatternResident(String PatternResident) {
        this.PatternResident = PatternResident;
    }

    public void NumbrOnly(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isLetter(c) && !evt.isAltDown()) {
            evt.consume();
        }
    }

    public void cleanPanCangeCase() {
        NumCardTochangCase.setText("");
        NamToChangCase.setText("");
        SurNamToChangCase.setText("");
        CaseA.setText("");
    }

    public void DiselectPan(JPanel j1, JPanel j2) {
        j1.setVisible(true);
        j2.setVisible(false);

    }

    public void DeleteResident() {
        int NumCards = Integer.parseInt(NumCardTochangCase.getText());
        Resident_GlRemplissage.DeleteResident(PaternToChangeCase, NumCards);
        if (check_Std2_x.isSelected()) {
            dR1.setRowCount(0);
            student_ResRemplissage.DisplayAllResidentInTablToConsult(TabResidentToConsult, LabTexTypCount, "عــدد الطلبـــة المقيمين", LabCountTabConsult, "طالب داخلي");
            ok = new Ok1(this, true, " لـقـد تـــم حـــذف الـــطــالـــب");
            ok.setVisible(true);
        }
        if (check_StdExt2_x.isSelected()) {
            dR2.setRowCount(0);
            student_ResRemplissage.DisplayAllResidentInTablToConsult(TabExtStdToConsult, LabTexTypCount, "عدد الطلبة الخارجيين :", LabCountTabConsult, "طالب خـــارجــي");
            ok = new Ok1(this, true, " لـقـد تـــم حـــذف الـــطــالـــب");
            ok.setVisible(true);
        }

        if (check_Prof2_x.isSelected()) {
            dR3.setRowCount(0);
            student_ResRemplissage.DisplayAllResidentInTablToConsult(TabProfToConsult, LabTexTypCount, "عــددالاسـاتذة المقيمين:", LabCountTabConsult, "اســـتـاذ");
            ok = new Ok1(this, true, " لـقـد تـــم حـــذف الاســـتـاذ");
            ok.setVisible(true);
        }
        if (check_Emp2_x.isSelected()) {
            dR4.setRowCount(0);
            student_ResRemplissage.DisplayAllResidentInTablToConsult(TabEmpToConsult, LabTexTypCount, "عدد العمال:", LabCountTabConsult, "عـــامـــل");
            ok = new Ok1(this, true, " لـقـد تـــم حـــذف العـــامـــل");
            ok.setVisible(true);
        }

    }
    DefaultTableModel dR1;
    DefaultTableModel dR2;
    DefaultTableModel dR3;
    DefaultTableModel dR4;

    public void DeleteUser() {
        //JOptionPane.showMessageDialog(null, " User : " + jLabel20.getText());
        userserviceRemplissage.DeletUser(jLabel20.getText());
        DefaultTableModel d = (DefaultTableModel) TabUserService.getModel();
        d.setRowCount(0);
        userserviceRemplissage.DisplayUserIntable((DefaultTableModel) TabUserService.getModel());
    }

    public static void main(String args[]) {

        Home1 home = new Home1();
        //Login login = new Login(home);

        /*home.setVisible(true);*/
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // login.setVisible(true);
                home.setVisible(true);
            }
        });
    }

    public int getChoose() {
        return choose;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AncianRoom;
    private javax.swing.JTextField Annet1;
    private javax.swing.JComboBox<String> Branch4;
    private javax.swing.JComboBox<String> BranchStd_year;
    private javax.swing.JComboBox<String> Branch_stdExternStdUpdtForm;
    private javax.swing.JComboBox<String> Branch_stdUpdtForm;
    private javax.swing.JButton BtnCancel;
    private javax.swing.JButton BtnNext;
    private javax.swing.JRadioButton BtnRdFemaleStd;
    private javax.swing.JRadioButton BtnRdMaleStd;
    private javax.swing.JButton BtnSavUpdt;
    private View.ButtonView BtnSaveStd;
    private javax.swing.JButton BtnSaveStd2;
    private javax.swing.JLabel BtnShowRooms;
    private javax.swing.JButton Btn_Annul_UserToUPDATE21;
    private ViewMyPrj.CLabel CLabImg;
    private javax.swing.JLabel CaseA;
    private javax.swing.JComboBox<String> CaseN;
    private javax.swing.JComboBox<String> CaseN1;
    private javax.swing.JComboBox<String> CaseN2;
    private javax.swing.JComboBox<String> CaseN3;
    private javax.swing.JComboBox<String> CaseResident;
    private javax.swing.JComboBox<String> CaseResident2;
    private javax.swing.JComboBox<String> CaseResident3;
    private javax.swing.JComboBox<String> CaseResidentProfUpdtForm;
    private javax.swing.JButton ChangeRoom;
    private javax.swing.JCheckBox ChecAccessUsr;
    private javax.swing.JCheckBox CheckAdministrationusr;
    private javax.swing.JCheckBox CheckInscrRessevSrv;
    private javax.swing.JCheckBox CheckMaleStdUpdtForm;
    private javax.swing.JCheckBox CheckRestarUsr;
    private javax.swing.JCheckBox CheckSrvRoom;
    private javax.swing.JComboBox<String> CombCommuneSlct;
    private javax.swing.JComboBox<String> CombCommuneUpdtForm;
    private javax.swing.JComboBox<String> CombExternNewOrNot;
    private javax.swing.JComboBox<String> CombRomInUpdtForm;
    private javax.swing.JLabel CountTabStdIn;
    private javax.swing.JFormattedTextField DatBirth_Empl;
    private javax.swing.JFormattedTextField DatBirth_Pro;
    private javax.swing.JFormattedTextField DatBirth_Prof7;
    private javax.swing.JFormattedTextField DatBirth_StdExt1;
    private javax.swing.JFormattedTextField DatBirth_std;
    private javax.swing.JFormattedTextField DatBirth_stdUpdtForm;
    private javax.swing.JFormattedTextField DatInscrpInUniv;
    private javax.swing.JComboBox<String> Depa_StdUpdtForm;
    private javax.swing.JCheckBox Gdr_Emp_Mal1;
    private javax.swing.JCheckBox Gdr_Emp_MalE;
    private javax.swing.JCheckBox Gdr_Emp_femal1;
    private javax.swing.JCheckBox Gdr_Emp_femalE;
    private javax.swing.JCheckBox Gdr_Prf_Malp;
    private javax.swing.JCheckBox Gdr_Prf_Malp1;
    private javax.swing.JCheckBox Gdr_Prf_femalp;
    private javax.swing.JCheckBox Gdr_Prf_femalp1;
    private javax.swing.JLabel IDRoomA;
    private javax.swing.JLabel Img_EmpUpdate;
    private javax.swing.JLabel Img_Std1;
    private javax.swing.JLabel Img_StdUpdate;
    private javax.swing.JLabel Img_StdUpdate1;
    private javax.swing.JLabel Img_profupdate;
    private javax.swing.JPanel InfoStdToUpdate_1;
    private javax.swing.JLabel LabBranchStd;
    private javax.swing.JLabel LabChangeCaseAll;
    private javax.swing.JLabel LabCountTabConsult;
    private javax.swing.JLabel LabDateBirth_Plc;
    private javax.swing.JLabel LabIDResident;
    private javax.swing.JLabel LabIDRoomN;
    private javax.swing.JLabel LabLastNameRestToPrint;
    private javax.swing.JLabel LabNam;
    private javax.swing.JLabel LabNameRestToPrint;
    private javax.swing.JLabel LabNamesForRes;
    private javax.swing.JLabel LabPlaceBirth_Res;
    private javax.swing.JLabel LabPrfBranch_Std2;
    private javax.swing.JLabel LabPrfNumCard_StdRes;
    private javax.swing.JLabel LabPrfResd_Std3;
    private javax.swing.JLabel LabPrfResd_Std4;
    private javax.swing.JLabel LabSurNam;
    private javax.swing.JLabel LabTexTypCount;
    private javax.swing.JLabel LabelShowNationalite;
    private javax.swing.JTextField LastNamMothARTxt;
    private javax.swing.JTextField LastNamMothARTxt_FR;
    private javax.swing.JTextField LastName_MotheFrUp;
    private javax.swing.JTextField LastName_ResidentFrUp;
    private javax.swing.JLabel LbInsConfrm2;
    private javax.swing.JLabel LbInsID2;
    private javax.swing.JLabel LbInsNom2;
    private javax.swing.JLabel LbInsPrnom2;
    private javax.swing.JLabel LbInsPsw2;
    private javax.swing.JLabel Lb_Droit_User2;
    private javax.swing.JComboBox<String> LevelStd;
    private javax.swing.JComboBox<String> LevelStdExtUpdtForm;
    private javax.swing.JComboBox<String> LevelStdUpdtForm;
    private javax.swing.JComboBox<String> LisLvlStd;
    private javax.swing.JComboBox<String> LstPvlinUpdtForm;
    private View.ButtonView Menu_Config;
    private View.ButtonView Menu_Consult_Info;
    private View.ButtonView Menu_Reports;
    private View.ButtonView Menu_Residence_dossier;
    private View.ButtonView Menu_RoomService;
    private View.ButtonView Menu_Update_info;
    private javax.swing.JLabel MessgControl5;
    private javax.swing.JTextField NamMrArStdUpdtForm;
    private javax.swing.JLabel NamToChangCase;
    private javax.swing.JLabel NameRoom;
    private javax.swing.JLabel NameRoomr;
    private javax.swing.JTextField Name_FatherFrUp;
    private javax.swing.JTextField Name_MotherFrUp;
    private javax.swing.JTextField Name_ResidentFrUp;
    private javax.swing.JComboBox<String> National_list;
    private javax.swing.JComboBox<String> National_listStdUpdtForm;
    private javax.swing.JLabel NumCard;
    private javax.swing.JTextField NumCardToUpdt;
    private javax.swing.JTextField NumCardToUpdt1;
    private javax.swing.JTextField NumCardToUpdt2;
    private javax.swing.JTextField NumCardToUpdt3;
    private javax.swing.JTextField NumCardToUpdt4;
    private javax.swing.JTextField NumCardToUpdt6;
    private javax.swing.JTextField NumCardToUpdt7;
    private javax.swing.JTextField NumCardToUpdt8;
    private javax.swing.JLabel NumCardTochangCase;
    private javax.swing.JLabel NumOrder;
    private javax.swing.JPanel PanAllResidentToConsult;
    private javax.swing.JPanel PanAllServiceStudent;
    private javax.swing.JPanel PanAllUpdateResident;
    private javax.swing.JPanel PanChangeCase;
    private javax.swing.JPanel PanCntMenu;
    private javax.swing.JPanel PanEXtrStdToConsult;
    private javax.swing.JPanel PanEmpToConsult;
    private javax.swing.JPanel PanInfoCard;
    private javax.swing.JPanel PanMenuAllStudent1;
    private javax.swing.JPanel PanPrfAfterSaisStd;
    private javax.swing.JPanel PanProfToConsult;
    private javax.swing.JPanel PanProfToUpdate;
    private javax.swing.JPanel PanSaisInfoEmp1;
    private javax.swing.JPanel PanSaisiEmp1;
    private javax.swing.JPanel PanSaisiEmp_;
    private javax.swing.JPanel PanSaisiInfoStdExt1;
    private javax.swing.JPanel PanStdEXtern;
    private javax.swing.JPanel PanStdExtToUpdate;
    private javax.swing.JPanel PanStdResident;
    private javax.swing.JPanel PanTakeRoom;
    private javax.swing.JPanel PanToChckPatern;
    private javax.swing.JPanel PanToChckPatern1;
    private javax.swing.JPanel Pan_All_PansSais_;
    private javax.swing.JComboBox<String> PavillonPanInf;
    private javax.swing.JComboBox<String> Pavlion4;
    private javax.swing.JComboBox<String> Pavlion5;
    private javax.swing.JTextField PlaceBirthFrStdUpdForm;
    private javax.swing.JLabel PlcBirth;
    private javax.swing.JPanel PnIUpdtusr;
    private javax.swing.JPanel Pn_Menu;
    private javax.swing.JPanel Pnform2;
    private javax.swing.JButton PrinRecWtoutSh;
    private javax.swing.JComboBox<String> Profession;
    private javax.swing.JComboBox<String> ProfessionToUpdate;
    private javax.swing.JRadioButton Rad1;
    private javax.swing.JRadioButton Rad2;
    private javax.swing.JRadioButton Rad3;
    private javax.swing.JRadioButton Rad4;
    private javax.swing.JRadioButton RadBtnMarriedStd;
    private javax.swing.JRadioButton RadBtnSingleStd;
    private javax.swing.JComboBox<String> RomPvinPanInf;
    private javax.swing.JButton RserveRoom;
    private javax.swing.JLabel SaveAllChangeCaseRes;
    private javax.swing.JButton SaveReservRoom;
    private javax.swing.JTextField Search_Field_upd_std;
    private javax.swing.JPanel SecondCentrePan;
    private javax.swing.JSeparator Separator4;
    private javax.swing.JRadioButton Std_MariedStdUpdtForm_x;
    private javax.swing.JRadioButton Sti_SingleStdUpdtForm_x;
    private javax.swing.JLabel SurNamToChangCase;
    private javax.swing.JTable TabEmpToConsult;
    private javax.swing.JTable TabExtStdToConsult;
    private javax.swing.JTable TabProfToConsult;
    private javax.swing.JTable TabResident;
    private javax.swing.JTable TabResidentToConsult;
    private javax.swing.JTable TabRoom;
    private javax.swing.JTable TabStdInternTotakeRoom;
    private javax.swing.JTable TabUserService;
    private javax.swing.JTextField TtxtBacYear;
    private javax.swing.JTextField TtxtBacYearStdUpdtForm;
    private javax.swing.JPasswordField TxtInsConPswUsr;
    private javax.swing.JTextField TxtInsIdentifiantUsr;
    private javax.swing.JTextField TxtInsNomusr;
    private javax.swing.JTextField TxtInsNomusr2;
    private javax.swing.JTextField TxtInsPrenomUsr;
    private javax.swing.JPasswordField TxtInsPswUsr;
    private javax.swing.JComboBox<String> TxtType;
    private javax.swing.JPanel UpdateStd;
    private javax.swing.JComboBox<String> WilayaList;
    private javax.swing.JComboBox<String> WilayaListUpdtForm;
    private javax.swing.JButton btnUpdRes;
    private View.ButtonView buttonView10;
    private View.ButtonView buttonView11;
    private View.ButtonView buttonView12;
    private View.ButtonView buttonView13;
    private javax.swing.JCheckBox checkFemalStdUpdtForm;
    private javax.swing.JCheckBox check_Emp;
    private javax.swing.JRadioButton check_Emp1_x;
    private javax.swing.JRadioButton check_Emp2_x;
    private javax.swing.JCheckBox check_Prof;
    private javax.swing.JRadioButton check_Prof1_x;
    private javax.swing.JRadioButton check_Prof2_x;
    private javax.swing.JCheckBox check_Prof_TakeRom;
    private javax.swing.JCheckBox check_Std;
    private javax.swing.JRadioButton check_Std1_x;
    private javax.swing.JRadioButton check_Std2_x;
    private javax.swing.JCheckBox check_StdExt;
    private javax.swing.JRadioButton check_StdExt1_x;
    private javax.swing.JRadioButton check_StdExt2_x;
    private javax.swing.JCheckBox check_Std_TakeRom;
    private javax.swing.JSpinner firstYrs;
    private javax.swing.JTextField ftxtPlcBirth_Prof;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel2_;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel4_;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel5_;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JComboBox<String> lstPav;
    private javax.swing.JPanel panCnt;
    private javax.swing.JPanel panCnt1;
    private javax.swing.JPanel panResidentToConsult;
    private javax.swing.JPanel panSaisiInfoProf;
    private javax.swing.JPanel panSaisiStd_;
    private javax.swing.JLabel paterntxt;
    private javax.swing.JComboBox<String> pavilion2;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JTextField txtAddress_Std;
    private javax.swing.JTextField txtAddress_StdUpdtForm;
    private javax.swing.JTextField txtBacMoy1;
    private javax.swing.JTextField txtBacMoy2;
    private javax.swing.JComboBox<String> txtBranch_std;
    private javax.swing.JComboBox<String> txtBrchToPrint;
    private javax.swing.JTextField txtCommuneStdUpdtForm;
    private javax.swing.JTextField txtDairaStd;
    private javax.swing.JTextField txtDairaStdUpdtForm;
    private javax.swing.JLabel txtDatBirth;
    private javax.swing.JComboBox<String> txtDepa_Std;
    private javax.swing.JTextField txtNam_EmplInsr;
    private javax.swing.JTextField txtNam_Father;
    private javax.swing.JTextField txtNam_FatherStdUpdtForm;
    private javax.swing.JTextField txtNam_Father_FR;
    private javax.swing.JTextField txtNam_MotherStdUpdtForm;
    private javax.swing.JTextField txtNam_Prof1;
    private javax.swing.JTextField txtNam_Prof7;
    private javax.swing.JTextField txtNam_StdExt1;
    private javax.swing.JTextField txtNam_mother;
    private javax.swing.JTextField txtNam_mother_FR;
    private javax.swing.JTextField txtNam_std;
    private javax.swing.JTextField txtNam_std5;
    private javax.swing.JTextField txtNam_std_FR;
    private javax.swing.JTextField txtPlaceGetBac1;
    private javax.swing.JTextField txtPlaceGetBacStdUpdtForm;
    private javax.swing.JTextField txtPlcBirth_Emply;
    private javax.swing.JTextField txtPlcBirth_Prof7;
    private javax.swing.JTextField txtPlcBirth_StdExt1;
    private javax.swing.JTextField txtPlcBirth_std;
    private javax.swing.JTextField txtPlcBirth_stdUpdtForm;
    private javax.swing.JTextField txtPlcBirth_std_FR;
    private javax.swing.JTextField txtProfession_Prof2;
    private javax.swing.JTextField txtProfission_Moth;
    private javax.swing.JTextField txtProfission_MothStdUpdtForm;
    private javax.swing.JTextField txtProfission_Std;
    private javax.swing.JTextField txtProfissionfath_StdUpdtForm;
    private javax.swing.JTextField txtSurNam_Empl;
    private javax.swing.JTextField txtSurNam_Prof1;
    private javax.swing.JTextField txtSurNam_Prof7;
    private javax.swing.JTextField txtSurNam_StdExt1;
    private javax.swing.JTextField txtSurNam_std;
    private javax.swing.JTextField txtSurNam_std5;
    private javax.swing.JTextField txtSurNam_std_FR;
    private javax.swing.JTextField txt_NumInsc;
    private javax.swing.JTextField txt_NumInscSdExt1;
    private javax.swing.JTextField txt_NumInscUpdtForm;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        int i = 0;
        while (i < listBtnPanPV.size()) {

            if (e.getComponent() == listBtnPanPV.get(i)) {
                //  JOptionPane.showMessageDialog(null,"Button = "+e.getComponent().getName());
                student_ResRemplissage.FilterResident(e.getComponent().getName(), TabRoom, (DefaultTableModel) TabRoom.getModel());

            }

            i++;

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(timer)) {
            timeLabel.setText(sdf.format(new Date(System.currentTimeMillis())));
        }

    }

}
