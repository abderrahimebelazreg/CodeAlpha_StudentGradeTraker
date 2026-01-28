/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package studentgradetraker;

/**
 *
 * @author Admin
 */

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());
    private int NumberOfStu = 0;
    private List<Integer> grades = new ArrayList<>();
    private DefaultTableModel tableModel;
    private int editingRow = -1;
    /**
     * Creates new form Main
     */
  
    public Main() {
        
        
        initComponents();
        
        
        tableModel = (DefaultTableModel) Table.getModel();
        CancelBtn.setVisible(false);
        setupTextFieldListener();
        setupTableActions();
        updateStatistics();
        
    }

    
    
    private void setupTableActions() {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println("Edit row " + row);
                EditStudent(row);
            }

            @Override
            public void onDelete(int row) {
                System.out.println("Delete row " + row);
                int confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete this","title",JOptionPane.YES_NO_OPTION);
                if(confirm == 0)
                {
                deleteStudent(row);
                    
                }
            }
        };
        Table.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
        Table.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(event));
        
        
        NmbrStu.setText(String.valueOf(NumberOfStu));
    }
    
    private void EditStudent(int row)
    {
        CancelBtn.setEnabled(true);
        CancelBtn.setVisible(true);
        ClearBtn.setEnabled(true);
        AddButton.setText("Edit");
        
        AddButton.setIcon(new ImageIcon(getClass().getResource("edit.png")));

        // Get data from the selected row
        if (row >= 0 && row < tableModel.getRowCount()) {
            // Get student name from column 1 (Name column)
            Object nameObj = tableModel.getValueAt(row, 1);
            if (nameObj != null) {
                jTextField1.setText(nameObj.toString());
            }

            // Get grade from column 2 (Grade column)
            Object gradeObj = tableModel.getValueAt(row, 2);
            if (gradeObj != null) {
                try {
                    int grade = Integer.parseInt(gradeObj.toString());
                    GradeNmbr.setValue(grade);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid grade format: " + e.getMessage());
                    GradeNmbr.setValue(0);
                }
            }

            // Store the row index being edited (you might need this later when saving the edit)
            // You can create a class variable to store the editing row index
            editingRow = row;
        }
    }
    private void deleteStudent(int row) 
    {
        if (row >= 0 && row < tableModel.getRowCount()) 
        {
            // If we're deleting the row being edited, cancel edit mode
            if (row == editingRow) 
            {
                
                CancelBtnActionPerformed(null);
            } 
            else if (row < editingRow) 
            {
                // If deleting a row before the editing row, adjust the editing index
                editingRow--;
            }

            // Rest of your delete code...
            Object gradeObj = tableModel.getValueAt(row, 2);
            if (gradeObj != null) 
            {
                try 
                {
                    int grade = Integer.parseInt(gradeObj.toString());
                    grades.remove(Integer.valueOf(grade));
                } 
                catch (NumberFormatException e) 
                {
                    System.err.println("Invalid grade format: " + e.getMessage());
                }
            }

            tableModel.removeRow(row);
            NumberOfStu = tableModel.getRowCount();
            updateStatistics();
        }
    }
    
       private void updateStatistics() {
        
        NmbrStu.setText(String.valueOf(NumberOfStu));
        
        if (grades.isEmpty()) {
            jLabel8.setText("00"); 
            jLabel9.setText("00"); 
            jLabel10.setText("00"); 
            return;
        }
        
        
        int highest = grades.stream().max(Integer::compare).orElse(0);
        jLabel8.setText(String.valueOf(highest));
        
        
        
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        double average = sum/NumberOfStu;
        jLabel9.setText(String.format("%.1f", average));
       //lowest
        int lowest = grades.stream().min(Integer::compare).orElse(0);
        jLabel10.setText(String.valueOf(lowest));
    }
       
       
       
       
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        GradeNmbr = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        AddButton = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        CancelBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        NmbrStu = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Grade Traker");
        setBackground(new java.awt.Color(204, 204, 204));

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));

        Table.setAutoCreateRowSorter(true);
        Table.setBackground(new java.awt.Color(255, 255, 229));
        Table.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Table.setForeground(new java.awt.Color(0, 0, 0));
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Grade", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.setFocusable(false);
        Table.setGridColor(new java.awt.Color(51, 51, 51));
        Table.setRowHeight(60);
        Table.setSelectionBackground(new java.awt.Color(204, 204, 255));
        Table.setSelectionForeground(new java.awt.Color(0, 0, 0));
        Table.setShowGrid(true);
        Table.setShowVerticalLines(false);
        Table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Table);

        jPanel1.setBackground(new java.awt.Color(255, 255, 229));

        jLabel1.setText("Name of student : ");

        jTextField1.setToolTipText("");

        GradeNmbr.setOpaque(true);

        jLabel2.setText("Grade                    :");

        AddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/cell/add.png"))); // NOI18N
        AddButton.setText("ADD");
        AddButton.addActionListener(this::AddButtonActionPerformed);

        ClearBtn.setText("CLEAR");
        ClearBtn.setEnabled(false);
        ClearBtn.addActionListener(this::ClearBtnActionPerformed);

        CancelBtn.setText("Cancel");
        CancelBtn.setEnabled(false);
        CancelBtn.addActionListener(this::CancelBtnActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(GradeNmbr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ClearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddButton, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CancelBtn)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ClearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GradeNmbr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 229));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Number of students");

        NmbrStu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        NmbrStu.setForeground(new java.awt.Color(0, 0, 0));
        NmbrStu.setText("00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(NmbrStu)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NmbrStu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Heighest Grade");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("00");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Average");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("00");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jPanel7.setBackground(new java.awt.Color(255, 204, 204));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Lowest Grade");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("00");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addGap(49, 49, 49)
                .addComponent(jLabel10)
                .addGap(14, 14, 14))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(68, 68, 68))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
       if(AddButton.getText().equals("ADD")) 
       {
            
            String name = jTextField1.getText().trim();
            Object gradeObj = GradeNmbr.getValue();

            if (name.isEmpty()) 
            {
                System.out.println("Please enter a student name");
                jTextField1.setBackground(new Color(255,204,204));
                
                return;
            }

            try 
            {
                int grade = Integer.parseInt(gradeObj.toString());
                if(grade<0 || grade>100)
                {
                    GradeNmbr.setBackground(new Color(255,204,204));
                    return;
                }
                // Add to table
                NumberOfStu++;
                int id = NumberOfStu;
                tableModel.addRow(new Object[]{id, name, grade, ""});

                grades.add(grade);

                
                updateStatistics();

                jTextField1.setText("");
                GradeNmbr.setValue(0);
                ClearBtn.setEnabled(false);

                System.out.println("Added student: " + name + " with grade: " + grade);
                jTextField1.setBackground(new Color(255,255,255));
            } 
            catch (NumberFormatException e) 
            {
                System.err.println("Invalid grade: " + e.getMessage());
            }
        }
        else if(AddButton.getText().equals("Edit")) 
        {
            // EDIT mode: Update the existing row
            String name = jTextField1.getText().trim();
            Object gradeObj = GradeNmbr.getValue();

            if (name.isEmpty()) 
            {
                System.out.println("Please enter a student name");
                jTextField1.setBackground(new Color(255,204,204));
                return;
            }

            try 
            {
                jTextField1.setBackground(new Color(255,255,255));
                int newGrade = Integer.parseInt(gradeObj.toString());

                // Get the old grade for statistics update
                Object oldGradeObj = tableModel.getValueAt(editingRow, 2);
                int oldGrade = 0;
                if (oldGradeObj != null) 
                {
                    oldGrade = Integer.parseInt(oldGradeObj.toString());
                }

                // Update the table row
                tableModel.setValueAt(name, editingRow, 1);
                tableModel.setValueAt(newGrade, editingRow, 2);

                // Update the grades list for statistics
                if (editingRow >= 0 && editingRow < grades.size()) 
                {
                    grades.set(editingRow, newGrade);
                }

                // Update statistics
                updateStatistics();

                // Clear input fields and reset to ADD mode
                jTextField1.setText("");
                GradeNmbr.setValue(0);
                ClearBtn.setEnabled(false);
                CancelBtn.setEnabled(false);
                CancelBtn.setVisible(false);
                AddButton.setText("ADD");
                AddButton.setIcon(new ImageIcon(getClass().getResource("add.png")));
                editingRow = -1; // Reset editing row

                System.out.println("Edited student at row " + editingRow + ": " + name + " with grade: " + newGrade);

            } 
            catch (NumberFormatException e) 
            {
                System.err.println("Invalid grade: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_AddButtonActionPerformed

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        
        
        
        jTextField1.setText("");
        GradeNmbr.setValue(0);
        ClearBtn.setEnabled(false);
        jTextField1.setBackground(new Color(255,255,255));
        
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void CancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBtnActionPerformed
        CancelBtn.setEnabled(false);
        CancelBtn.setVisible(false);
        jTextField1.setText("");
        GradeNmbr.setValue(0);
        AddButton.setText("ADD");
        AddButton.setIcon(new ImageIcon(getClass().getResource("add.png")));
        jTextField1.setBackground(new Color(255,255,255));
        
    }//GEN-LAST:event_CancelBtnActionPerformed

    public void setupTextFieldListener() {
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = jTextField1.getText().trim();
                
                if (text.isEmpty() && ClearBtn.isEnabled()) 
                {
                    ClearBtn.setEnabled(false);
                    System.out.println("disable");
                }
                else if(!text.isEmpty() && !ClearBtn.isEnabled()) 
                {
                    ClearBtn.setEnabled(true);
                    System.out.println("enable");
                }
            }
        });
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> 
        {
            Main main = new Main();
            main.setLocationRelativeTo(null);
            main.getContentPane().setBackground(Color.white);
            main.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton CancelBtn;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JSpinner GradeNmbr;
    private javax.swing.JLabel NmbrStu;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
