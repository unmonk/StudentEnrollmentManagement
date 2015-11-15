/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Scott
 */
package pkg430test;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class StaffForm extends javax.swing.JFrame {
    

    /**
     * Creates new form StaffForm
     */
    public StaffForm() 
    {
        setTitle("Staff");
        initComponents();
        //Building all the tables at once. Loading by tab click is too slow
        updateFacultyTable();
        updateStaffTable();
        updateCourseTable();
        updateDeptTable();
        updateStudentTable();
        initEnrolledPage();
        updateEnrolledTable();
        
    }
    
    public static void setSelectedID(JComboBox combobox, String id, int type)
    {
        //Helper function for setting comboBox Items
        InstructorItem item;
        StudentItem item2;
        CourseItem item3;
        if(type == 1)
        {
            for(int i=0; i<combobox.getItemCount(); i++)
            {
                item = (InstructorItem)combobox.getItemAt(i);
                if(item.getid().equals(id))
                {
                    combobox.setSelectedIndex(i);
                }
            }
        }
        if(type == 2)
        {
             for(int i=0; i<combobox.getItemCount(); i++)
            {
                item2 = (StudentItem)combobox.getItemAt(i);
                if(item2.getid().equals(id))
                {
                    combobox.setSelectedIndex(i);
                }
            }
        }
        if(type == 3)
        {
             for(int i=0; i<combobox.getItemCount(); i++)
            {
                item3 = (CourseItem)combobox.getItemAt(i);
                if(item3.getid().equals(id))
                {
                    combobox.setSelectedIndex(i);
                }
            }
        }
        
    }
    private void updateStudentTable()
    {
        try {
            try (Connection conn = ConnectionConfig.getConnection()) {
                PreparedStatement st = conn.prepareStatement("Select * from Students ORDER BY SNAME ASC");
                ResultSet rs = st.executeQuery();
                StudentTable.setModel(DbUtils.resultSetToTableModel(rs));
                conn.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    private void updateEnrolledTable()
    {
        try {
            CourseItem item = (CourseItem) CourseSelectComboBox.getSelectedItem();
            try (Connection conn = ConnectionConfig.getConnection()) {
                PreparedStatement st = conn.prepareStatement("Select A.SID, B.SNAME, A.EXAM1, A.EXAM2, A.FINAL from Enrolled A JOIN Students B ON A.SID = B.SID WHERE A.CID='"+item.getid()+"' ORDER BY B.SNAME");
                ResultSet rs = st.executeQuery();
                EnrolledTable.setModel(DbUtils.resultSetToTableModel(rs));
                conn.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
       private void updateStaffTable()
    {
        try {
            try (Connection conn = ConnectionConfig.getConnection()) {
                PreparedStatement st = conn.prepareStatement("Select * from Staff ORDER BY SNAME ASC");
                ResultSet rs = st.executeQuery();
                StaffTable.setModel(DbUtils.resultSetToTableModel(rs));
                conn.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
         private void updateFacultyTable()
    {
        try {
            try (Connection conn = ConnectionConfig.getConnection()) {
                PreparedStatement st = conn.prepareStatement("Select * from Faculty ORDER BY FNAME ASC");
                ResultSet rs = st.executeQuery();
                FacultyTable.setModel(DbUtils.resultSetToTableModel(rs));
                conn.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
           private void updateDeptTable()
    {
        try {
            try (Connection conn = ConnectionConfig.getConnection()) {
                PreparedStatement st = conn.prepareStatement("Select * from DEPARTMENT ORDER BY DNAME ASC");
                ResultSet rs = st.executeQuery();
                DeptTable.setModel(DbUtils.resultSetToTableModel(rs));
                conn.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
           private void initEnrolledPage()
           {
               try {
                try (Connection conn = ConnectionConfig.getConnection()) {
                PreparedStatement st = conn.prepareStatement("Select * from COURSES");
                ResultSet rs = st.executeQuery();
                while (rs.next())
                        {
                            CourseSelectComboBox.addItem(new CourseItem(rs.getString("CNAME"), rs.getString("CID")));
                            EnrolledAddCourseCombo.addItem(new CourseItem(rs.getString("CNAME"), rs.getString("CID")));
                        }
                PreparedStatement st2 = conn.prepareStatement("Select * from STUDENTS");
                ResultSet rs2 = st2.executeQuery();
                while(rs2.next())
                {
                    EnrolledAddStudentCombo.addItem(new StudentItem(rs2.getString("SNAME"), rs2.getString("SID")));
                    
                }
                conn.close();
                
                
            }
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
           }
           private void updateCourseTable()
    {
        try {
            try (Connection conn = ConnectionConfig.getConnection()) {
                PreparedStatement st = conn.prepareStatement("Select * from COURSES ORDER BY CID ASC");
                ResultSet rs = st.executeQuery();
                CoursesTable.setModel(DbUtils.resultSetToTableModel(rs));
                PreparedStatement st2 = conn.prepareStatement("Select * from FACULTY");
                ResultSet rs2 = st2.executeQuery();
                while (rs2.next())
                        {
                            CourseInstructorComboBox.addItem(new InstructorItem(rs2.getString("FNAME"), rs2.getString("FID")));
                            CourseInstructorComboBoxNEW.addItem(new InstructorItem(rs2.getString("FNAME"), rs2.getString("FID")));
                        }
                conn.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainTab = new javax.swing.JTabbedPane();
        FacultyTab = new javax.swing.JTabbedPane();
        FacultyPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        FacultyTable = new javax.swing.JTable();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        FacultyFIDBox = new javax.swing.JTextField();
        FacultyFNAMEBox = new javax.swing.JTextField();
        FacultyDEPTIDBox = new javax.swing.JTextField();
        FacultyIDLabel = new javax.swing.JLabel();
        FacultyFNAMELabel = new javax.swing.JLabel();
        FacultyDEPTIDLabel = new javax.swing.JLabel();
        FacultySubmitButton = new javax.swing.JButton();
        FacultyFIDBoxNEW = new javax.swing.JTextField();
        FacultyIDLabel1 = new javax.swing.JLabel();
        FacultyFNAMELabel1 = new javax.swing.JLabel();
        FacultyFNAMEBoxNEW = new javax.swing.JTextField();
        FacultyDEPTIDLabel1 = new javax.swing.JLabel();
        FacultyDEPTIDBoxNEW = new javax.swing.JTextField();
        FacultySubmitButtonNEW = new javax.swing.JButton();
        FacultyRefreshButton = new javax.swing.JButton();
        FacultyDeleteRow = new javax.swing.JButton();
        StaffTab = new javax.swing.JTabbedPane();
        StaffPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        StaffTable = new javax.swing.JTable();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        StaffSIDBox = new javax.swing.JTextField();
        StaffSNAMEBox = new javax.swing.JTextField();
        StaffDEPTIDBox = new javax.swing.JTextField();
        StaffIDLabel = new javax.swing.JLabel();
        StaffSNAMELabel = new javax.swing.JLabel();
        StaffDEPTIDLabel = new javax.swing.JLabel();
        StaffSubmitButton = new javax.swing.JButton();
        StaffSIDBoxNEW = new javax.swing.JTextField();
        StaffIDLabel1 = new javax.swing.JLabel();
        StaffSNAMEBoxNEW = new javax.swing.JTextField();
        StaffSNAMELabel1 = new javax.swing.JLabel();
        StaffDEPTIDBoxNEW = new javax.swing.JTextField();
        StaffDEPTIDLabel1 = new javax.swing.JLabel();
        StaffSubmitButtonNEW = new javax.swing.JButton();
        StaffRefreshButton = new javax.swing.JButton();
        StaffDeleteRow = new javax.swing.JButton();
        CoursesTab = new javax.swing.JTabbedPane();
        CoursesPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        CoursesTable = new javax.swing.JTable();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        CourseIDBox = new javax.swing.JTextField();
        CourseNameBox = new javax.swing.JTextField();
        CourseBuildingBox = new javax.swing.JTextField();
        CourseIDLabel = new javax.swing.JLabel();
        CourseNameLabel = new javax.swing.JLabel();
        CourseRoomLabel = new javax.swing.JLabel();
        CourseSubmitButton = new javax.swing.JButton();
        CourseIDBoxNEW = new javax.swing.JTextField();
        CourseIDLabelNew = new javax.swing.JLabel();
        CourseNameBoxNEW = new javax.swing.JTextField();
        CourseNameLabelNew = new javax.swing.JLabel();
        CourseBuildingBoxNEW = new javax.swing.JTextField();
        CourseBuildingLabelNew = new javax.swing.JLabel();
        CourseSubmitButtonNEW = new javax.swing.JButton();
        CourseBuildingLabel = new javax.swing.JLabel();
        CourseMaxStudentsLabel = new javax.swing.JLabel();
        CourseInstructorLabel = new javax.swing.JLabel();
        CourseRoomLabelNew = new javax.swing.JLabel();
        CourseInstructorLabelNew = new javax.swing.JLabel();
        CourseMaxStudentsLabelNew = new javax.swing.JLabel();
        CourseMaxStudentsBoxNEW = new javax.swing.JTextField();
        CourseRoomBoxNEW = new javax.swing.JTextField();
        CourseMaxStudentsBox = new javax.swing.JTextField();
        CourseRoomBox = new javax.swing.JTextField();
        CourseInstructorComboBox = new javax.swing.JComboBox();
        CourseInstructorComboBoxNEW = new javax.swing.JComboBox();
        CourseRefreshButton = new javax.swing.JButton();
        CoursesDeleteRow = new javax.swing.JButton();
        Enrolled = new javax.swing.JTabbedPane();
        EnrolledPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        EnrolledTable = new javax.swing.JTable();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        EnrolledExam1Box = new javax.swing.JTextField();
        EnrolledStudentLabel = new javax.swing.JLabel();
        EnrolledExam1Label = new javax.swing.JLabel();
        EnrolledSubmitScoresButton = new javax.swing.JButton();
        EnrolledExam2Label = new javax.swing.JLabel();
        EnrolledExam2Box = new javax.swing.JTextField();
        EnrolledFinalLabel = new javax.swing.JLabel();
        EnrolledFinalBox = new javax.swing.JTextField();
        EnrolledStudentCHANGELabel = new javax.swing.JLabel();
        EnrolledStudentLabel1 = new javax.swing.JLabel();
        EnrolledStudentIDCHANGElabel = new javax.swing.JLabel();
        EnrolledRefreshButton = new javax.swing.JButton();
        EnrolledDeleteRow = new javax.swing.JButton();
        CourseSelectComboBox = new javax.swing.JComboBox();
        CourseSelectLAbel = new javax.swing.JLabel();
        EnrolledAddStudentCombo = new javax.swing.JComboBox();
        EnrolledAddCourseCombo = new javax.swing.JComboBox();
        EnrolledAddStudentButton = new javax.swing.JButton();
        DepartmentsTab = new javax.swing.JTabbedPane();
        DeptPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        DeptTable = new javax.swing.JTable();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        DeptIDBox = new javax.swing.JTextField();
        DeptNAMEBox = new javax.swing.JTextField();
        DeptIDLabel = new javax.swing.JLabel();
        DeptNAMELabel = new javax.swing.JLabel();
        DeptSubmitButton = new javax.swing.JButton();
        DeptIDBoxNEW = new javax.swing.JTextField();
        DeptIDLabel2 = new javax.swing.JLabel();
        DeptNAMEBoxNEW = new javax.swing.JTextField();
        DeptNAMELabel2 = new javax.swing.JLabel();
        DeptSubmitButtonNEW = new javax.swing.JButton();
        DeptRefreshButton = new javax.swing.JButton();
        DeptDeleteRow = new javax.swing.JButton();
        StudentsTab = new javax.swing.JTabbedPane();
        StudentPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StudentTable = new javax.swing.JTable();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        SIDBox = new javax.swing.JTextField();
        SNAMEBox = new javax.swing.JTextField();
        MAJORBox = new javax.swing.JTextField();
        AGEBox = new javax.swing.JTextField();
        SIDLabel = new javax.swing.JLabel();
        SNAMELabel = new javax.swing.JLabel();
        MAJORLabel = new javax.swing.JLabel();
        S_LEVELLabel = new javax.swing.JLabel();
        AGELabel = new javax.swing.JLabel();
        StudentSubmitButton = new javax.swing.JButton();
        SIDBoxNEW = new javax.swing.JTextField();
        SNAMEBoxNEW = new javax.swing.JTextField();
        MAJORBoxNEW = new javax.swing.JTextField();
        AGEBoxNEW = new javax.swing.JTextField();
        SIDLabel1 = new javax.swing.JLabel();
        SNAMELabel1 = new javax.swing.JLabel();
        MAJORLabel1 = new javax.swing.JLabel();
        S_LEVELLabel1 = new javax.swing.JLabel();
        AGELabel1 = new javax.swing.JLabel();
        StudentSubmitButtonNEW = new javax.swing.JButton();
        S_LEVELCOMBONEW = new javax.swing.JComboBox();
        S_LEVELCOMBO = new javax.swing.JComboBox();
        StudentRefreshButton = new javax.swing.JButton();
        StudentDeleteRow = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainTab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                MainTabStateChanged(evt);
            }
        });
        MainTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MainTabMouseClicked(evt);
            }
        });

        FacultyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        FacultyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FacultyTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(FacultyTable);

        FacultyIDLabel.setText("Faculty ID:");

        FacultyFNAMELabel.setText("Faculty Name:");

        FacultyDEPTIDLabel.setText("Department ID:");

        FacultySubmitButton.setText("Submit Edit");
        FacultySubmitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FacultySubmitButtonMouseClicked(evt);
            }
        });

        FacultyIDLabel1.setText("Faculty ID:");

        FacultyFNAMELabel1.setText("Faculty Name:");

        FacultyDEPTIDLabel1.setText("Department ID:");

        FacultySubmitButtonNEW.setText("Submit New");
        FacultySubmitButtonNEW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FacultySubmitButtonNEWMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FacultyIDLabel)
                    .addComponent(FacultyFNAMELabel)
                    .addComponent(FacultyDEPTIDLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FacultySubmitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(FacultyFNAMEBox)
                    .addComponent(FacultyFIDBox)
                    .addComponent(FacultyDEPTIDBox))
                .addGap(61, 61, 61)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FacultyIDLabel1)
                    .addComponent(FacultyFNAMELabel1)
                    .addComponent(FacultyDEPTIDLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FacultyFIDBoxNEW, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                        .addComponent(FacultyFNAMEBoxNEW)
                        .addComponent(FacultyDEPTIDBoxNEW))
                    .addComponent(FacultySubmitButtonNEW, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FacultyFIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FacultyIDLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FacultyFNAMEBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FacultyFNAMELabel))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FacultyDEPTIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FacultyDEPTIDLabel)))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FacultyFIDBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FacultyIDLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FacultyFNAMEBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FacultyFNAMELabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FacultyDEPTIDBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FacultyDEPTIDLabel1))))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FacultySubmitButton)
                    .addComponent(FacultySubmitButtonNEW))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane3.setLayer(FacultyFIDBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultyFNAMEBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultyDEPTIDBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultyIDLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultyFNAMELabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultyDEPTIDLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultySubmitButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultyFIDBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultyIDLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultyFNAMELabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultyFNAMEBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultyDEPTIDLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultyDEPTIDBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(FacultySubmitButtonNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);

        FacultyRefreshButton.setText("Refresh");
        FacultyRefreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FacultyRefreshButtonMouseClicked(evt);
            }
        });

        FacultyDeleteRow.setText("Delete Row");
        FacultyDeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FacultyDeleteRowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FacultyPanelLayout = new javax.swing.GroupLayout(FacultyPanel);
        FacultyPanel.setLayout(FacultyPanelLayout);
        FacultyPanelLayout.setHorizontalGroup(
            FacultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FacultyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                .addGroup(FacultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FacultyPanelLayout.createSequentialGroup()
                        .addComponent(FacultyRefreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FacultyDeleteRow)
                        .addGap(140, 140, 140))))
        );
        FacultyPanelLayout.setVerticalGroup(
            FacultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FacultyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FacultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FacultyPanelLayout.createSequentialGroup()
                        .addGap(0, 183, Short.MAX_VALUE)
                        .addGroup(FacultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FacultyRefreshButton)
                            .addComponent(FacultyDeleteRow))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FacultyPanelLayout.createSequentialGroup()
                        .addComponent(jLayeredPane3)
                        .addContainerGap())))
        );

        FacultyTab.addTab("Faculty", FacultyPanel);

        MainTab.addTab("Faculty", FacultyTab);

        StaffTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        StaffTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StaffTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(StaffTable);

        StaffIDLabel.setText("Staff ID:");

        StaffSNAMELabel.setText("Staff Name:");

        StaffDEPTIDLabel.setText("Department ID:");

        StaffSubmitButton.setText("Submit Edit");
        StaffSubmitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StaffSubmitButtonMouseClicked(evt);
            }
        });

        StaffIDLabel1.setText("Staff ID:");

        StaffSNAMELabel1.setText("Staff Name:");

        StaffDEPTIDLabel1.setText("Department ID:");

        StaffSubmitButtonNEW.setText("Submit New");
        StaffSubmitButtonNEW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StaffSubmitButtonNEWMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StaffIDLabel)
                    .addComponent(StaffSNAMELabel)
                    .addComponent(StaffDEPTIDLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(StaffSubmitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(StaffSNAMEBox)
                    .addComponent(StaffSIDBox)
                    .addComponent(StaffDEPTIDBox))
                .addGap(56, 56, 56)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StaffIDLabel1)
                    .addComponent(StaffSNAMELabel1)
                    .addComponent(StaffDEPTIDLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(StaffSIDBoxNEW)
                    .addComponent(StaffSNAMEBoxNEW)
                    .addComponent(StaffDEPTIDBoxNEW)
                    .addComponent(StaffSubmitButtonNEW, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StaffSIDBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StaffIDLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StaffSNAMEBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StaffSNAMELabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StaffDEPTIDBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StaffDEPTIDLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(StaffSubmitButtonNEW))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StaffSIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StaffIDLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StaffSNAMEBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StaffSNAMELabel))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StaffDEPTIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StaffDEPTIDLabel))
                        .addGap(18, 18, 18)
                        .addComponent(StaffSubmitButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane2.setLayer(StaffSIDBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffSNAMEBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffDEPTIDBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffIDLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffSNAMELabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffDEPTIDLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffSubmitButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffSIDBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffIDLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffSNAMEBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffSNAMELabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffDEPTIDBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffDEPTIDLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(StaffSubmitButtonNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);

        StaffRefreshButton.setText("Refresh");
        StaffRefreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StaffRefreshButtonMouseClicked(evt);
            }
        });

        StaffDeleteRow.setText("Delete Row");
        StaffDeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StaffDeleteRowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout StaffPanelLayout = new javax.swing.GroupLayout(StaffPanel);
        StaffPanel.setLayout(StaffPanelLayout);
        StaffPanelLayout.setHorizontalGroup(
            StaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StaffPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addGroup(StaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StaffPanelLayout.createSequentialGroup()
                        .addComponent(StaffRefreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(StaffDeleteRow)
                        .addGap(131, 131, 131))))
        );
        StaffPanelLayout.setVerticalGroup(
            StaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StaffPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StaffPanelLayout.createSequentialGroup()
                        .addGap(0, 183, Short.MAX_VALUE)
                        .addGroup(StaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StaffRefreshButton)
                            .addComponent(StaffDeleteRow))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StaffPanelLayout.createSequentialGroup()
                        .addComponent(jLayeredPane2)
                        .addContainerGap())))
        );

        StaffTab.addTab("Staff", StaffPanel);

        MainTab.addTab("Staff", StaffTab);

        CoursesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        CoursesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CoursesTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(CoursesTable);

        CourseIDLabel.setText("Course ID:");

        CourseNameLabel.setText("Course Name:");

        CourseRoomLabel.setText("Room:");

        CourseSubmitButton.setText("Submit Edit");
        CourseSubmitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CourseSubmitButtonMouseClicked(evt);
            }
        });

        CourseIDLabelNew.setText("Course ID:");

        CourseNameLabelNew.setText("Course Name:");

        CourseBuildingLabelNew.setText("Building:");

        CourseSubmitButtonNEW.setText("Submit New");
        CourseSubmitButtonNEW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CourseSubmitButtonNEWMouseClicked(evt);
            }
        });

        CourseBuildingLabel.setText("Building:");

        CourseMaxStudentsLabel.setText("Max Students:");

        CourseInstructorLabel.setText("Instructor:");

        CourseRoomLabelNew.setText("Room:");

        CourseInstructorLabelNew.setText("Instructor:");

        CourseMaxStudentsLabelNew.setText("Max Students:");

        CourseInstructorComboBox.setModel(new javax.swing.DefaultComboBoxModel());

        CourseInstructorComboBoxNEW.setModel(new javax.swing.DefaultComboBoxModel());

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addComponent(CourseMaxStudentsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CourseMaxStudentsBox, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                        .addGap(483, 483, 483))
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                                .addComponent(CourseInstructorLabel)
                                .addGap(18, 18, 18)
                                .addComponent(CourseInstructorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CourseIDLabel)
                                    .addComponent(CourseNameLabel)
                                    .addComponent(CourseRoomLabel)
                                    .addComponent(CourseBuildingLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CourseSubmitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(CourseNameBox)
                                    .addComponent(CourseIDBox)
                                    .addComponent(CourseBuildingBox)
                                    .addComponent(CourseRoomBox))))
                        .addGap(63, 63, 63)
                        .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CourseBuildingLabelNew)
                                    .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(CourseNameLabelNew, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(CourseIDLabelNew))
                                    .addComponent(CourseRoomLabelNew)
                                    .addComponent(CourseInstructorLabelNew))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CourseSubmitButtonNEW, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(CourseIDBoxNEW)
                                    .addComponent(CourseNameBoxNEW)
                                    .addComponent(CourseBuildingBoxNEW)
                                    .addComponent(CourseRoomBoxNEW)
                                    .addComponent(CourseInstructorComboBoxNEW, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                                .addComponent(CourseMaxStudentsLabelNew)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CourseMaxStudentsBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CourseIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CourseIDLabel)
                    .addComponent(CourseIDLabelNew)
                    .addComponent(CourseIDBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CourseNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CourseNameLabel)
                    .addComponent(CourseNameLabelNew)
                    .addComponent(CourseNameBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CourseBuildingBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CourseBuildingLabel)
                    .addComponent(CourseBuildingLabelNew)
                    .addComponent(CourseBuildingBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CourseRoomLabel)
                    .addComponent(CourseRoomLabelNew)
                    .addComponent(CourseRoomBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CourseRoomBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CourseInstructorLabel)
                    .addComponent(CourseInstructorLabelNew)
                    .addComponent(CourseInstructorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CourseInstructorComboBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CourseMaxStudentsLabel)
                    .addComponent(CourseMaxStudentsLabelNew)
                    .addComponent(CourseMaxStudentsBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CourseMaxStudentsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CourseSubmitButton)
                    .addComponent(CourseSubmitButtonNEW)))
        );
        jLayeredPane5.setLayer(CourseIDBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseNameBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseBuildingBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseIDLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseNameLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseRoomLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseSubmitButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseIDBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseIDLabelNew, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseNameBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseNameLabelNew, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseBuildingBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseBuildingLabelNew, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseSubmitButtonNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseBuildingLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseMaxStudentsLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseInstructorLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseRoomLabelNew, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseInstructorLabelNew, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseMaxStudentsLabelNew, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseMaxStudentsBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseRoomBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseMaxStudentsBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseRoomBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseInstructorComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(CourseInstructorComboBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);

        CourseRefreshButton.setText("Refresh");
        CourseRefreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CourseRefreshButtonMouseClicked(evt);
            }
        });

        CoursesDeleteRow.setText("Delete Row");
        CoursesDeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CoursesDeleteRowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CoursesPanelLayout = new javax.swing.GroupLayout(CoursesPanel);
        CoursesPanel.setLayout(CoursesPanelLayout);
        CoursesPanelLayout.setHorizontalGroup(
            CoursesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CoursesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(CoursesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CoursesPanelLayout.createSequentialGroup()
                        .addComponent(CourseRefreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CoursesDeleteRow)
                        .addGap(129, 129, 129))))
        );
        CoursesPanelLayout.setVerticalGroup(
            CoursesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CoursesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CoursesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CoursesPanelLayout.createSequentialGroup()
                        .addGap(0, 183, Short.MAX_VALUE)
                        .addGroup(CoursesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CourseRefreshButton)
                            .addComponent(CoursesDeleteRow))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CoursesPanelLayout.createSequentialGroup()
                        .addComponent(jLayeredPane5)
                        .addContainerGap())))
        );

        CoursesTab.addTab("Courses", CoursesPanel);

        MainTab.addTab("Courses", CoursesTab);

        EnrolledTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        EnrolledTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EnrolledTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(EnrolledTable);

        EnrolledStudentLabel.setText("Student:");

        EnrolledExam1Label.setText("Exam 1:");

        EnrolledSubmitScoresButton.setText("Submit Scores");
        EnrolledSubmitScoresButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EnrolledSubmitScoresButtonMouseClicked(evt);
            }
        });

        EnrolledExam2Label.setText("Exam 2:");

        EnrolledFinalLabel.setText("Final:");

        EnrolledStudentLabel1.setText("Student ID:");

        EnrolledStudentIDCHANGElabel.setText("        ");

        javax.swing.GroupLayout jLayeredPane6Layout = new javax.swing.GroupLayout(jLayeredPane6);
        jLayeredPane6.setLayout(jLayeredPane6Layout);
        jLayeredPane6Layout.setHorizontalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EnrolledStudentLabel)
                            .addComponent(EnrolledExam1Label)
                            .addComponent(EnrolledExam2Label)
                            .addComponent(EnrolledFinalLabel))
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EnrolledStudentCHANGELabel)
                            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(EnrolledSubmitScoresButton, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(EnrolledExam1Box)
                                    .addComponent(EnrolledExam2Box)
                                    .addComponent(EnrolledFinalBox))
                                .addContainerGap(211, Short.MAX_VALUE))))
                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                        .addComponent(EnrolledStudentLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EnrolledStudentIDCHANGElabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jLayeredPane6Layout.setVerticalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnrolledStudentLabel1)
                    .addComponent(EnrolledStudentIDCHANGElabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnrolledStudentLabel)
                    .addComponent(EnrolledStudentCHANGELabel))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnrolledExam1Box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnrolledExam1Label))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnrolledExam2Box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnrolledExam2Label))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnrolledFinalBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnrolledFinalLabel))
                .addGap(38, 38, 38)
                .addComponent(EnrolledSubmitScoresButton)
                .addContainerGap(237, Short.MAX_VALUE))
        );
        jLayeredPane6.setLayer(EnrolledExam1Box, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(EnrolledStudentLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(EnrolledExam1Label, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(EnrolledSubmitScoresButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(EnrolledExam2Label, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(EnrolledExam2Box, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(EnrolledFinalLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(EnrolledFinalBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(EnrolledStudentCHANGELabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(EnrolledStudentLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(EnrolledStudentIDCHANGElabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        EnrolledRefreshButton.setText("Refresh");
        EnrolledRefreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EnrolledRefreshButtonMouseClicked(evt);
            }
        });

        EnrolledDeleteRow.setText("Delete Row");
        EnrolledDeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnrolledDeleteRowActionPerformed(evt);
            }
        });

        CourseSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel());
        CourseSelectComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CourseSelectComboBoxPropertyChange(evt);
            }
        });

        CourseSelectLAbel.setText("Select A Course and Press Refresh");

        EnrolledAddStudentCombo.setModel(new javax.swing.DefaultComboBoxModel());
        EnrolledAddStudentCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                EnrolledAddStudentComboPropertyChange(evt);
            }
        });

        EnrolledAddCourseCombo.setModel(new javax.swing.DefaultComboBoxModel());
        EnrolledAddCourseCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                EnrolledAddCourseComboPropertyChange(evt);
            }
        });

        EnrolledAddStudentButton.setText("Add Student");
        EnrolledAddStudentButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EnrolledAddStudentButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout EnrolledPanelLayout = new javax.swing.GroupLayout(EnrolledPanel);
        EnrolledPanel.setLayout(EnrolledPanelLayout);
        EnrolledPanelLayout.setHorizontalGroup(
            EnrolledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EnrolledPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EnrolledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EnrolledPanelLayout.createSequentialGroup()
                        .addComponent(EnrolledAddStudentCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(EnrolledPanelLayout.createSequentialGroup()
                        .addGroup(EnrolledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(EnrolledAddCourseCombo, 0, 173, Short.MAX_VALUE)
                            .addComponent(EnrolledAddStudentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addGroup(EnrolledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EnrolledPanelLayout.createSequentialGroup()
                                .addGroup(EnrolledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CourseSelectLAbel)
                                    .addGroup(EnrolledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(CourseSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(EnrolledPanelLayout.createSequentialGroup()
                                            .addComponent(EnrolledRefreshButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(EnrolledDeleteRow))))
                                .addGap(134, 134, 134))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        EnrolledPanelLayout.setVerticalGroup(
            EnrolledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EnrolledPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EnrolledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EnrolledPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(EnrolledAddStudentCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CourseSelectLAbel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EnrolledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CourseSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EnrolledAddCourseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(EnrolledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EnrolledPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(EnrolledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(EnrolledRefreshButton)
                                    .addComponent(EnrolledDeleteRow))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EnrolledPanelLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(EnrolledAddStudentButton))))
                    .addGroup(EnrolledPanelLayout.createSequentialGroup()
                        .addComponent(jLayeredPane6)
                        .addContainerGap())))
        );

        Enrolled.addTab("Enrolled", EnrolledPanel);

        MainTab.addTab("Enrolled", Enrolled);

        DeptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        DeptTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeptTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(DeptTable);

        DeptIDLabel.setText("Dept ID:");

        DeptNAMELabel.setText("Dept Name:");

        DeptSubmitButton.setText("Submit Edit");
        DeptSubmitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeptSubmitButtonMouseClicked(evt);
            }
        });

        DeptIDLabel2.setText("Dept ID:");

        DeptNAMELabel2.setText("Dept Name:");

        DeptSubmitButtonNEW.setText("Submit New");
        DeptSubmitButtonNEW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeptSubmitButtonNEWMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DeptIDLabel)
                    .addComponent(DeptNAMELabel))
                .addGap(21, 21, 21)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DeptSubmitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(DeptNAMEBox)
                    .addComponent(DeptIDBox))
                .addGap(56, 56, 56)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DeptIDLabel2)
                    .addComponent(DeptNAMELabel2))
                .addGap(21, 21, 21)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DeptIDBoxNEW)
                    .addComponent(DeptNAMEBoxNEW)
                    .addComponent(DeptSubmitButtonNEW, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DeptIDBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DeptIDLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DeptNAMEBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DeptNAMELabel2))
                        .addGap(56, 56, 56)
                        .addComponent(DeptSubmitButtonNEW))
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DeptIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DeptIDLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DeptNAMEBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DeptNAMELabel))
                        .addGap(50, 50, 50)
                        .addComponent(DeptSubmitButton)))
                .addContainerGap(259, Short.MAX_VALUE))
        );
        jLayeredPane4.setLayer(DeptIDBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(DeptNAMEBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(DeptIDLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(DeptNAMELabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(DeptSubmitButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(DeptIDBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(DeptIDLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(DeptNAMEBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(DeptNAMELabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(DeptSubmitButtonNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);

        DeptRefreshButton.setText("Refresh");
        DeptRefreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeptRefreshButtonMouseClicked(evt);
            }
        });

        DeptDeleteRow.setText("Delete Row");
        DeptDeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeptDeleteRowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DeptPanelLayout = new javax.swing.GroupLayout(DeptPanel);
        DeptPanel.setLayout(DeptPanelLayout);
        DeptPanelLayout.setHorizontalGroup(
            DeptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeptPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addGroup(DeptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DeptPanelLayout.createSequentialGroup()
                        .addComponent(DeptRefreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DeptDeleteRow)
                        .addGap(134, 134, 134))))
        );
        DeptPanelLayout.setVerticalGroup(
            DeptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeptPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DeptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DeptPanelLayout.createSequentialGroup()
                        .addGap(0, 183, Short.MAX_VALUE)
                        .addGroup(DeptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DeptRefreshButton)
                            .addComponent(DeptDeleteRow))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DeptPanelLayout.createSequentialGroup()
                        .addComponent(jLayeredPane4)
                        .addContainerGap())))
        );

        DepartmentsTab.addTab("Staff", DeptPanel);

        MainTab.addTab("Departments", DepartmentsTab);

        StudentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        StudentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StudentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(StudentTable);

        SIDLabel.setText("Student ID:");

        SNAMELabel.setText("Student Name:");

        MAJORLabel.setText("Major:");

        S_LEVELLabel.setText("Year:");

        AGELabel.setText("Age:");

        StudentSubmitButton.setText("Submit Edit");
        StudentSubmitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StudentSubmitButtonMouseClicked(evt);
            }
        });

        SIDLabel1.setText("Student ID:");

        SNAMELabel1.setText("Student Name:");

        MAJORLabel1.setText("Major:");

        S_LEVELLabel1.setText("Year:");

        AGELabel1.setText("Age:");

        StudentSubmitButtonNEW.setText("Submit New");
        StudentSubmitButtonNEW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StudentSubmitButtonNEWMouseClicked(evt);
            }
        });

        S_LEVELCOMBONEW.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Freshman", "Sophomore", "Junior", "Senior", "Graduate" }));

        S_LEVELCOMBO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Freshman", "Sophomore", "Junior", "Senior", "Graduate" }));
        S_LEVELCOMBO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S_LEVELCOMBOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SIDLabel)
                    .addComponent(SNAMELabel)
                    .addComponent(MAJORLabel)
                    .addComponent(AGELabel)
                    .addComponent(S_LEVELLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AGEBox)
                    .addComponent(StudentSubmitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(SNAMEBox)
                    .addComponent(SIDBox)
                    .addComponent(MAJORBox)
                    .addComponent(S_LEVELCOMBO, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(62, 62, 62)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SIDLabel1)
                    .addComponent(SNAMELabel1)
                    .addComponent(MAJORLabel1)
                    .addComponent(S_LEVELLabel1)
                    .addComponent(AGELabel1))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SNAMEBoxNEW, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(SIDBoxNEW, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(MAJORBoxNEW, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(AGEBoxNEW, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(StudentSubmitButtonNEW, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(S_LEVELCOMBONEW, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SIDLabel)
                    .addComponent(SIDBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SIDLabel1))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SNAMEBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SNAMELabel)
                    .addComponent(SNAMELabel1)
                    .addComponent(SNAMEBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MAJORBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MAJORLabel)
                    .addComponent(MAJORBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MAJORLabel1))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(S_LEVELLabel)
                    .addComponent(S_LEVELLabel1)
                    .addComponent(S_LEVELCOMBONEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S_LEVELCOMBO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AGEBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AGELabel)
                    .addComponent(AGEBoxNEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AGELabel1))
                .addGap(38, 38, 38)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StudentSubmitButton)
                    .addComponent(StudentSubmitButtonNEW))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1.setLayer(SIDBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(SNAMEBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(MAJORBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(AGEBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(SIDLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(SNAMELabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(MAJORLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(S_LEVELLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(AGELabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(StudentSubmitButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(SIDBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(SNAMEBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(MAJORBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(AGEBoxNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(SIDLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(SNAMELabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(MAJORLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(S_LEVELLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(AGELabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(StudentSubmitButtonNEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(S_LEVELCOMBONEW, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(S_LEVELCOMBO, javax.swing.JLayeredPane.DEFAULT_LAYER);

        StudentRefreshButton.setText("Refresh");
        StudentRefreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StudentRefreshButtonMouseClicked(evt);
            }
        });

        StudentDeleteRow.setText("Delete Row");
        StudentDeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentDeleteRowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout StudentPanelLayout = new javax.swing.GroupLayout(StudentPanel);
        StudentPanel.setLayout(StudentPanelLayout);
        StudentPanelLayout.setHorizontalGroup(
            StudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(StudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StudentPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StudentPanelLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(StudentRefreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(StudentDeleteRow)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        StudentPanelLayout.setVerticalGroup(
            StudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StudentPanelLayout.createSequentialGroup()
                        .addGap(0, 183, Short.MAX_VALUE)
                        .addGroup(StudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StudentRefreshButton)
                            .addComponent(StudentDeleteRow))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StudentPanelLayout.createSequentialGroup()
                        .addComponent(jLayeredPane1)
                        .addContainerGap())))
        );

        StudentsTab.addTab("Students", StudentPanel);

        MainTab.addTab("Students", StudentsTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainTab)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainTab)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StudentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentTableMouseClicked
        try
        {
            int row = StudentTable.getSelectedRow();
            String tableClick = (StudentTable.getModel().getValueAt(row, 0).toString());
            String sql = "select * from Students where SID='"+tableClick+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next())
                    {
                        String sid = rs.getString("SID");
                        SIDBox.setText(sid);
                        String sname = rs.getString("SNAME");
                        SNAMEBox.setText(sname);
                        String major = rs.getString("MAJOR");
                        MAJORBox.setText(major);
                        String s_level = rs.getString("S_LEVEL");
                        S_LEVELCOMBO.setSelectedItem(s_level);
                        String age = rs.getString("AGE");
                        AGEBox.setText(age);
                        
                    }
            conn.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        
                }
    }//GEN-LAST:event_StudentTableMouseClicked

    private void StudentSubmitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentSubmitButtonMouseClicked
        try{
            String StudentID = SIDBox.getText();
            String SNAME = SNAMEBox.getText();
            String MAJOR = MAJORBox.getText();
            String S_LEVEL = S_LEVELCOMBO.getSelectedItem().toString();
            String Age = AGEBox.getText();
            String sql = "UPDATE Students SET SNAME='"+SNAME+"', Major='"+MAJOR+"', S_LEVEL='"+S_LEVEL+"', AGE='"+Age+"' WHERE SID='"+StudentID+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateStudentTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            
        }
    }//GEN-LAST:event_StudentSubmitButtonMouseClicked

    private void StudentRefreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentRefreshButtonMouseClicked
        updateStudentTable();
    }//GEN-LAST:event_StudentRefreshButtonMouseClicked

    private void StaffTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StaffTableMouseClicked
        try
        {
            int row = StaffTable.getSelectedRow();
            String tableClick = (StaffTable.getModel().getValueAt(row, 0).toString());
            String sql = "select * from Staff where SID='"+tableClick+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next())
                    {
                        String sid = rs.getString("SID");
                        StaffSIDBox.setText(sid);
                        String sname = rs.getString("SNAME");
                        StaffSNAMEBox.setText(sname);
                        String deptid = rs.getString("DEPTID");
                        StaffDEPTIDBox.setText(deptid);
                        
                    }
            conn.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        
                }
    }//GEN-LAST:event_StaffTableMouseClicked

    private void StaffSubmitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StaffSubmitButtonMouseClicked
          try{
            String StaffID = StaffSIDBox.getText();
            String StaffSNAME = StaffSNAMEBox.getText();
            String StaffDEPID = StaffDEPTIDBox.getText();
            String sql = "UPDATE Staff SET SNAME='"+StaffSNAME+"', DEPTID='"+StaffDEPID+"' WHERE SID='"+StaffID+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateStaffTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            
        }
    }//GEN-LAST:event_StaffSubmitButtonMouseClicked

    private void StaffRefreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StaffRefreshButtonMouseClicked
          updateStaffTable();
    }//GEN-LAST:event_StaffRefreshButtonMouseClicked

    private void FacultyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacultyTableMouseClicked
        try
        {
            int row = FacultyTable.getSelectedRow();
            String tableClick = (FacultyTable.getModel().getValueAt(row, 0).toString());
            String sql = "select * from Faculty where FID='"+tableClick+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next())
                    {
                        String fid = rs.getString("FID");
                        FacultyFIDBox.setText(fid);
                        String fname = rs.getString("FNAME");
                        FacultyFNAMEBox.setText(fname);
                        String deptid = rs.getString("DEPTID");
                        FacultyDEPTIDBox.setText(deptid);
                        
                    }
            conn.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        
                }
    }//GEN-LAST:event_FacultyTableMouseClicked

    private void FacultySubmitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacultySubmitButtonMouseClicked
         try{
            String FacultyID = FacultyFIDBox.getText();
            String FNAME = FacultyFNAMEBox.getText();
            String DEPTID = FacultyDEPTIDBox.getText();
            String sql = "UPDATE Faculty SET FNAME='"+FNAME+"', DEPTID='"+DEPTID+"' WHERE FID='"+FacultyID+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateFacultyTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            
        }
    }//GEN-LAST:event_FacultySubmitButtonMouseClicked

    private void FacultyRefreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacultyRefreshButtonMouseClicked
        updateFacultyTable();
    }//GEN-LAST:event_FacultyRefreshButtonMouseClicked

    private void StudentSubmitButtonNEWMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentSubmitButtonNEWMouseClicked
         try{
            String StudentID = SIDBoxNEW.getText();
            String SNAME = SNAMEBoxNEW.getText();
            String MAJOR = MAJORBoxNEW.getText();
            String S_LEVEL = S_LEVELCOMBONEW.getSelectedItem().toString();
            String Age = AGEBoxNEW.getText();
            String sql = "INSERT INTO Students (SID, SNAME, MAJOR, S_LEVEL, AGE) VALUES ('"+StudentID+"', '"+SNAME+"','"+MAJOR+"', '"+S_LEVEL+"', '"+Age+"')";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateStudentTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_StudentSubmitButtonNEWMouseClicked

    private void S_LEVELCOMBOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S_LEVELCOMBOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_S_LEVELCOMBOActionPerformed

    private void FacultySubmitButtonNEWMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacultySubmitButtonNEWMouseClicked
        try{
            String FacultyID = FacultyFIDBoxNEW.getText();
            String FNAME = FacultyFNAMEBoxNEW.getText();
            String DEPTID = FacultyDEPTIDBoxNEW.getText();
            String sql = "INSERT INTO Faculty (FID, FNAME, DEPTID) VALUES('"+FacultyID+"', '"+FNAME+"', '"+DEPTID+"')";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateFacultyTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            
        }
    }//GEN-LAST:event_FacultySubmitButtonNEWMouseClicked

    private void StaffSubmitButtonNEWMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StaffSubmitButtonNEWMouseClicked
       try{
            String StaffID = StaffSIDBoxNEW.getText();
            String StaffSNAME = StaffSNAMEBoxNEW.getText();
            String StaffDEPID = StaffDEPTIDBoxNEW.getText();
            String sql = "INSERT INTO Staff (SID, SNAME, DEPTID) VALUES('"+StaffID+"', '"+StaffSNAME+"', '"+StaffDEPID+"')";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateStaffTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            
        }
    }//GEN-LAST:event_StaffSubmitButtonNEWMouseClicked

    private void DeptTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeptTableMouseClicked
        try
        {
            int row = DeptTable.getSelectedRow();
            String tableClick = (DeptTable.getModel().getValueAt(row, 0).toString());
            String sql = "select * from DEPARTMENT where DID='"+tableClick+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next())
                    {
                        String did = rs.getString("DID");
                        DeptIDBox.setText(did);
                        String dname = rs.getString("DNAME");
                        DeptNAMEBox.setText(dname);
                    }
            conn.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        
                }
    }//GEN-LAST:event_DeptTableMouseClicked

    private void DeptSubmitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeptSubmitButtonMouseClicked
        try{
            String DID = DeptIDBox.getText();
            String DNAME = DeptNAMEBox.getText();
            String sql = "UPDATE DEPARTMENT SET DNAME='"+DNAME+"' WHERE DID='"+DID+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateFacultyTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            
        }
    }//GEN-LAST:event_DeptSubmitButtonMouseClicked

    private void DeptSubmitButtonNEWMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeptSubmitButtonNEWMouseClicked
          try{
            String DeptID = DeptIDBoxNEW.getText();
            String DNAME = DeptNAMEBoxNEW.getText();
            String sql = "INSERT INTO DEPARTMENT (DID, DNAME) VALUES('"+DeptID+"', '"+DNAME+"')";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateFacultyTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            
        }
    }//GEN-LAST:event_DeptSubmitButtonNEWMouseClicked

    private void DeptRefreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeptRefreshButtonMouseClicked
        updateDeptTable();
    }//GEN-LAST:event_DeptRefreshButtonMouseClicked

    private void CoursesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CoursesTableMouseClicked
         try
        {
            int row = CoursesTable.getSelectedRow();
            String tableClick = (CoursesTable.getModel().getValueAt(row, 0).toString());
            String sql = "select * from COURSES where CID='"+tableClick+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next())
                    {
                        String cid = rs.getString("CID");
                        CourseIDBox.setText(cid);
                        String cname = rs.getString("CNAME");
                        CourseNameBox.setText(cname);
                        String meetsat = rs.getString("MEETS_AT");
                        CourseBuildingBox.setText(meetsat);
                        String room = rs.getString("ROOM");
                        CourseRoomBox.setText(room);
                        String FID = rs.getString("FID");
                        setSelectedID(CourseInstructorComboBox, FID, 1);
                        String limit = rs.getString("LIMIT");
                        CourseMaxStudentsBox.setText(limit);
                        
                    }
            conn.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        
                }
    }//GEN-LAST:event_CoursesTableMouseClicked

    private void CourseSubmitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CourseSubmitButtonMouseClicked
        try{
            String CourseID = CourseIDBox.getText();
            String CourseName = CourseNameBox.getText();
            String CourseBuilding = CourseBuildingBox.getText();
            String CourseRoom = CourseRoomBox.getText();
            int i = CourseInstructorComboBox.getSelectedIndex();
            InstructorItem item = (InstructorItem) CourseInstructorComboBox.getSelectedItem();
            String CourseInstructor = item.getid();
            String MaxStudents = CourseMaxStudentsBox.getText();
            String sql = "UPDATE COURSES SET CNAME='"+CourseName+"', MEETS_AT='"+CourseBuilding+"', ROOM='"+CourseRoom+"', FID='"+CourseInstructor+"', LIMIT='"+MaxStudents+"' WHERE CID='"+CourseID+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateCourseTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            
        }
    }//GEN-LAST:event_CourseSubmitButtonMouseClicked

    private void CourseSubmitButtonNEWMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CourseSubmitButtonNEWMouseClicked
         try{
            String CourseID = CourseIDBoxNEW.getText();
            String CourseName = CourseNameBoxNEW.getText();
            String CourseBuilding = CourseBuildingBoxNEW.getText();
            String CourseRoom = CourseRoomBoxNEW.getText();
            InstructorItem item = (InstructorItem) CourseInstructorComboBoxNEW.getSelectedItem();
            String CourseInstructor = item.getid();
            String MaxStudents = CourseMaxStudentsBoxNEW.getText();
            String sql = "INSERT INTO COURSES (CID, CNAME, MEETS_AT, ROOM, FID, LIMIT) VALUES('"+CourseID+"', '"+CourseName+"', '"+CourseBuilding+"', '"+CourseRoom+"', '"+CourseInstructor+"', '"+MaxStudents+"')";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateCourseTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            
        }
    }//GEN-LAST:event_CourseSubmitButtonNEWMouseClicked

    private void CourseRefreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CourseRefreshButtonMouseClicked
        updateCourseTable();
    }//GEN-LAST:event_CourseRefreshButtonMouseClicked

    private void FacultyDeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FacultyDeleteRowActionPerformed
        try{
            String FID = FacultyFIDBox.getText();
            String sql = "DELETE FROM FACULTY WHERE FID='"+FID+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateFacultyTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_FacultyDeleteRowActionPerformed

    private void StaffDeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StaffDeleteRowActionPerformed
        try{
            String SID = StaffSIDBox.getText();
            String sql = "DELETE FROM STAFF WHERE SID='"+SID+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateStaffTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_StaffDeleteRowActionPerformed

    private void CoursesDeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CoursesDeleteRowActionPerformed
        try{
            String CID = CourseIDBox.getText();
            String sql = "DELETE FROM COURSES WHERE CID='"+CID+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateCourseTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_CoursesDeleteRowActionPerformed

    private void DeptDeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeptDeleteRowActionPerformed
        try{
            String DID = DeptIDBox.getText();
            String sql = "DELETE FROM DEPARTMENT WHERE DID='"+DID+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateDeptTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_DeptDeleteRowActionPerformed

    private void StudentDeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentDeleteRowActionPerformed
        try{
            String SID = SIDBox.getText();
            String sql = "DELETE FROM STUDENTS WHERE SID='"+SID+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateStudentTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_StudentDeleteRowActionPerformed

    private void MainTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MainTabMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_MainTabMouseClicked

    private void MainTabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_MainTabStateChanged
        /*if (evt.getSource() instanceof JTabbedPane) {
            JTabbedPane pane = (JTabbedPane) evt.getSource();
            if (pane.getSelectedIndex() == 1) {
                updateFacultyTable();
            }
            if (pane.getSelectedIndex() == 2) {
                updateStaffTable();
            }
            if (pane.getSelectedIndex() == 3) {
                updateCourseTable();
            }
            if (pane.getSelectedIndex() == 4) {

            }
            if (pane.getSelectedIndex() == 5) {
                updateDeptTable();
            }
            if (pane.getSelectedIndex() == 6) {
                updateStudentTable();
            }

        }*/
    }//GEN-LAST:event_MainTabStateChanged

    private void EnrolledTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnrolledTableMouseClicked
       try
        {
            int row = EnrolledTable.getSelectedRow();
            String tableClick = (EnrolledTable.getModel().getValueAt(row, 0).toString());
            String sql = "select SID, SNAME, EXAM1, EXAM2, FINAL from Enrolled NATURAL JOIN Students where SID='"+tableClick+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next())
                    {
                        String SID = rs.getString("SID");
                        EnrolledStudentIDCHANGElabel.setText(SID);
                        String sname = rs.getString("SNAME");
                        EnrolledStudentCHANGELabel.setText(sname);
                        String exam1 = rs.getString("EXAM1");
                        EnrolledExam1Box.setText(exam1);
                        String exam2 = rs.getString("EXAM2");
                        EnrolledExam2Box.setText(exam2);
                        String finalscore = rs.getString("FINAL");
                        EnrolledFinalBox.setText(finalscore);
                    }
            conn.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        
                }
    }//GEN-LAST:event_EnrolledTableMouseClicked

    private void EnrolledSubmitScoresButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnrolledSubmitScoresButtonMouseClicked
         try{
            String SID = EnrolledStudentIDCHANGElabel.getText();
            CourseItem item = (CourseItem) CourseSelectComboBox.getSelectedItem();
            String CID = item.getid();
            String exam1 = EnrolledExam1Box.getText();
            String exam2 = EnrolledExam2Box.getText();
            String finalscore = EnrolledFinalBox.getText();
            String sql = "UPDATE ENROLLED SET EXAM1='"+exam1+"', EXAM2='"+exam2+"', FINAL='"+finalscore+"' WHERE SID='"+SID+"' AND CID='"+CID+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateEnrolledTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            
        }
    }//GEN-LAST:event_EnrolledSubmitScoresButtonMouseClicked

    private void EnrolledRefreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnrolledRefreshButtonMouseClicked
        updateEnrolledTable();
    }//GEN-LAST:event_EnrolledRefreshButtonMouseClicked

    private void EnrolledDeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnrolledDeleteRowActionPerformed
        try{
            String SID = EnrolledStudentIDCHANGElabel.getText();
            String sql = "DELETE FROM ENROLLED WHERE SID='"+SID+"'";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateEnrolledTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_EnrolledDeleteRowActionPerformed

    private void CourseSelectComboBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CourseSelectComboBoxPropertyChange
     
    }//GEN-LAST:event_CourseSelectComboBoxPropertyChange

    private void EnrolledAddStudentComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_EnrolledAddStudentComboPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_EnrolledAddStudentComboPropertyChange

    private void EnrolledAddCourseComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_EnrolledAddCourseComboPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_EnrolledAddCourseComboPropertyChange

    private void EnrolledAddStudentButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnrolledAddStudentButtonMouseClicked
        try{
            StudentItem item = (StudentItem) EnrolledAddStudentCombo.getSelectedItem();
            CourseItem item2 = (CourseItem) EnrolledAddCourseCombo.getSelectedItem();
            String SID = item.getid();
            String CID = item2.getid();
            String sql = "INSERT INTO ENROLLED (SID, CID, EXAM1, EXAM2, FINAL) VALUES('"+SID+"', '"+CID+"', '0', '0', '0')";
            Connection conn = ConnectionConfig.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            updateEnrolledTable();
            conn.close();
            JOptionPane.showMessageDialog(null, "Completed");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
            
        }
    }//GEN-LAST:event_EnrolledAddStudentButtonMouseClicked

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AGEBox;
    private javax.swing.JTextField AGEBoxNEW;
    private javax.swing.JLabel AGELabel;
    private javax.swing.JLabel AGELabel1;
    private javax.swing.JTextField CourseBuildingBox;
    private javax.swing.JTextField CourseBuildingBoxNEW;
    private javax.swing.JLabel CourseBuildingLabel;
    private javax.swing.JLabel CourseBuildingLabelNew;
    private javax.swing.JTextField CourseIDBox;
    private javax.swing.JTextField CourseIDBoxNEW;
    private javax.swing.JLabel CourseIDLabel;
    private javax.swing.JLabel CourseIDLabelNew;
    private javax.swing.JComboBox CourseInstructorComboBox;
    private javax.swing.JComboBox CourseInstructorComboBoxNEW;
    private javax.swing.JLabel CourseInstructorLabel;
    private javax.swing.JLabel CourseInstructorLabelNew;
    private javax.swing.JTextField CourseMaxStudentsBox;
    private javax.swing.JTextField CourseMaxStudentsBoxNEW;
    private javax.swing.JLabel CourseMaxStudentsLabel;
    private javax.swing.JLabel CourseMaxStudentsLabelNew;
    private javax.swing.JTextField CourseNameBox;
    private javax.swing.JTextField CourseNameBoxNEW;
    private javax.swing.JLabel CourseNameLabel;
    private javax.swing.JLabel CourseNameLabelNew;
    private javax.swing.JButton CourseRefreshButton;
    private javax.swing.JTextField CourseRoomBox;
    private javax.swing.JTextField CourseRoomBoxNEW;
    private javax.swing.JLabel CourseRoomLabel;
    private javax.swing.JLabel CourseRoomLabelNew;
    private javax.swing.JComboBox CourseSelectComboBox;
    private javax.swing.JLabel CourseSelectLAbel;
    private javax.swing.JButton CourseSubmitButton;
    private javax.swing.JButton CourseSubmitButtonNEW;
    private javax.swing.JButton CoursesDeleteRow;
    private javax.swing.JPanel CoursesPanel;
    private javax.swing.JTabbedPane CoursesTab;
    private javax.swing.JTable CoursesTable;
    private javax.swing.JTabbedPane DepartmentsTab;
    private javax.swing.JButton DeptDeleteRow;
    private javax.swing.JTextField DeptIDBox;
    private javax.swing.JTextField DeptIDBoxNEW;
    private javax.swing.JLabel DeptIDLabel;
    private javax.swing.JLabel DeptIDLabel2;
    private javax.swing.JTextField DeptNAMEBox;
    private javax.swing.JTextField DeptNAMEBoxNEW;
    private javax.swing.JLabel DeptNAMELabel;
    private javax.swing.JLabel DeptNAMELabel2;
    private javax.swing.JPanel DeptPanel;
    private javax.swing.JButton DeptRefreshButton;
    private javax.swing.JButton DeptSubmitButton;
    private javax.swing.JButton DeptSubmitButtonNEW;
    private javax.swing.JTable DeptTable;
    private javax.swing.JTabbedPane Enrolled;
    private javax.swing.JComboBox EnrolledAddCourseCombo;
    private javax.swing.JButton EnrolledAddStudentButton;
    private javax.swing.JComboBox EnrolledAddStudentCombo;
    private javax.swing.JButton EnrolledDeleteRow;
    private javax.swing.JTextField EnrolledExam1Box;
    private javax.swing.JLabel EnrolledExam1Label;
    private javax.swing.JTextField EnrolledExam2Box;
    private javax.swing.JLabel EnrolledExam2Label;
    private javax.swing.JTextField EnrolledFinalBox;
    private javax.swing.JLabel EnrolledFinalLabel;
    private javax.swing.JPanel EnrolledPanel;
    private javax.swing.JButton EnrolledRefreshButton;
    private javax.swing.JLabel EnrolledStudentCHANGELabel;
    private javax.swing.JLabel EnrolledStudentIDCHANGElabel;
    private javax.swing.JLabel EnrolledStudentLabel;
    private javax.swing.JLabel EnrolledStudentLabel1;
    private javax.swing.JButton EnrolledSubmitScoresButton;
    private javax.swing.JTable EnrolledTable;
    private javax.swing.JTextField FacultyDEPTIDBox;
    private javax.swing.JTextField FacultyDEPTIDBoxNEW;
    private javax.swing.JLabel FacultyDEPTIDLabel;
    private javax.swing.JLabel FacultyDEPTIDLabel1;
    private javax.swing.JButton FacultyDeleteRow;
    private javax.swing.JTextField FacultyFIDBox;
    private javax.swing.JTextField FacultyFIDBoxNEW;
    private javax.swing.JTextField FacultyFNAMEBox;
    private javax.swing.JTextField FacultyFNAMEBoxNEW;
    private javax.swing.JLabel FacultyFNAMELabel;
    private javax.swing.JLabel FacultyFNAMELabel1;
    private javax.swing.JLabel FacultyIDLabel;
    private javax.swing.JLabel FacultyIDLabel1;
    private javax.swing.JPanel FacultyPanel;
    private javax.swing.JButton FacultyRefreshButton;
    private javax.swing.JButton FacultySubmitButton;
    private javax.swing.JButton FacultySubmitButtonNEW;
    private javax.swing.JTabbedPane FacultyTab;
    private javax.swing.JTable FacultyTable;
    private javax.swing.JTextField MAJORBox;
    private javax.swing.JTextField MAJORBoxNEW;
    private javax.swing.JLabel MAJORLabel;
    private javax.swing.JLabel MAJORLabel1;
    private javax.swing.JTabbedPane MainTab;
    private javax.swing.JTextField SIDBox;
    private javax.swing.JTextField SIDBoxNEW;
    private javax.swing.JLabel SIDLabel;
    private javax.swing.JLabel SIDLabel1;
    private javax.swing.JTextField SNAMEBox;
    private javax.swing.JTextField SNAMEBoxNEW;
    private javax.swing.JLabel SNAMELabel;
    private javax.swing.JLabel SNAMELabel1;
    private javax.swing.JComboBox S_LEVELCOMBO;
    private javax.swing.JComboBox S_LEVELCOMBONEW;
    private javax.swing.JLabel S_LEVELLabel;
    private javax.swing.JLabel S_LEVELLabel1;
    private javax.swing.JTextField StaffDEPTIDBox;
    private javax.swing.JTextField StaffDEPTIDBoxNEW;
    private javax.swing.JLabel StaffDEPTIDLabel;
    private javax.swing.JLabel StaffDEPTIDLabel1;
    private javax.swing.JButton StaffDeleteRow;
    private javax.swing.JLabel StaffIDLabel;
    private javax.swing.JLabel StaffIDLabel1;
    private javax.swing.JPanel StaffPanel;
    private javax.swing.JButton StaffRefreshButton;
    private javax.swing.JTextField StaffSIDBox;
    private javax.swing.JTextField StaffSIDBoxNEW;
    private javax.swing.JTextField StaffSNAMEBox;
    private javax.swing.JTextField StaffSNAMEBoxNEW;
    private javax.swing.JLabel StaffSNAMELabel;
    private javax.swing.JLabel StaffSNAMELabel1;
    private javax.swing.JButton StaffSubmitButton;
    private javax.swing.JButton StaffSubmitButtonNEW;
    private javax.swing.JTabbedPane StaffTab;
    private javax.swing.JTable StaffTable;
    private javax.swing.JButton StudentDeleteRow;
    private javax.swing.JPanel StudentPanel;
    private javax.swing.JButton StudentRefreshButton;
    private javax.swing.JButton StudentSubmitButton;
    private javax.swing.JButton StudentSubmitButtonNEW;
    private javax.swing.JTable StudentTable;
    private javax.swing.JTabbedPane StudentsTab;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    // End of variables declaration//GEN-END:variables
}


class InstructorItem
{
    private String name;
    private String id;

    public InstructorItem(String name, String id)
    {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public String getName()
    {
        return name;
    }

    public String getid()
    {
        return id;
    }
}
class StudentItem
{
    private String name;
    private String id;
    
    public StudentItem(String name, String id)
    {
        this.name = name;
        this.id = id;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
    public String getName()
    {
        return name;
    }
    public String getid()
    {
        return id;
    }
}
class CourseItem
{
    private String name;
    private String id;
    
    public CourseItem(String name, String id)
    {
        this.name = name;
        this.id = id;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
    public String getName()
    {
        return name;
    }
    public String getid()
    {
        return id;
    }
}
