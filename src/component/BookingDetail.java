/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package component;
import DBConnection.DatabaseConnection;
import Model.ModelUser;
import component.Animation2;
import java.awt.Color;
import java.sql.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author acer
 */
public class BookingDetail extends javax.swing.JFrame {
    Animation2 ColorOP = new Animation2();
    /**
     * Creates new form BookForm
     */
    public BookingDetail() {
        
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        ColorOP.AnimateDetail2();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel1 = new swing.Panel();
        panelClass2 = new component.PanelClass();
        labelwelcome1 = new javax.swing.JLabel();
        labelwelcome = new javax.swing.JLabel();
        paneldetail2 = new component.PanelGradient();
        labeldetail2 = new javax.swing.JLabel();
        panelbook2 = new component.PanelGradient();
        labelbook2 = new javax.swing.JLabel();
        panelfood2 = new component.PanelGradient();
        labelfood2 = new javax.swing.JLabel();
        panelhome2 = new component.PanelGradient();
        labelhome2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        view = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookingdata = new javax.swing.JTable();
        search = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelClass2.setBackground(new java.awt.Color(23, 27, 36));
        panelClass2.setPreferredSize(new java.awt.Dimension(222, 554));

        labelwelcome1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        labelwelcome1.setForeground(new java.awt.Color(255, 255, 255));
        labelwelcome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelwelcome1.setText("DKH CINEMA");

        labelwelcome.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        labelwelcome.setForeground(new java.awt.Color(255, 255, 255));
        labelwelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelwelcome.setText("Welcome To ");

        paneldetail2.setBackground(new java.awt.Color(23, 27, 36));
        paneldetail2.setToolTipText("");
        paneldetail2.setAlignmentX(0.0F);
        paneldetail2.setAlignmentY(0.0F);
        paneldetail2.setColorGradient(new java.awt.Color(23, 27, 36));
        paneldetail2.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        paneldetail2.setMinimumSize(new java.awt.Dimension(180, 40));
        paneldetail2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneldetail2MousePressed(evt);
            }
        });
        paneldetail2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labeldetail2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labeldetail2.setForeground(new java.awt.Color(166, 166, 166));
        labeldetail2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labeldetail2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/detail.png"))); // NOI18N
        labeldetail2.setText("Booking Detail");
        labeldetail2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labeldetail2MousePressed(evt);
            }
        });
        paneldetail2.add(labeldetail2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 40));

        panelbook2.setBackground(new java.awt.Color(23, 27, 36));
        panelbook2.setToolTipText("");
        panelbook2.setAlignmentX(0.0F);
        panelbook2.setAlignmentY(0.0F);
        panelbook2.setColorGradient(new java.awt.Color(23, 27, 36));
        panelbook2.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelbook2.setMinimumSize(new java.awt.Dimension(180, 40));
        panelbook2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelbook2MousePressed(evt);
            }
        });
        panelbook2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelbook2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelbook2.setForeground(new java.awt.Color(166, 166, 166));
        labelbook2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelbook2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/books.png"))); // NOI18N
        labelbook2.setText("Book");
        labelbook2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelbook2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelbook2MousePressed(evt);
            }
        });
        panelbook2.add(labelbook2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        panelfood2.setBackground(new java.awt.Color(23, 27, 36));
        panelfood2.setToolTipText("");
        panelfood2.setAlignmentX(0.0F);
        panelfood2.setAlignmentY(0.0F);
        panelfood2.setColorGradient(new java.awt.Color(23, 27, 36));
        panelfood2.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelfood2.setMinimumSize(new java.awt.Dimension(180, 40));
        panelfood2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelfood2MousePressed(evt);
            }
        });
        panelfood2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelfood2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelfood2.setForeground(new java.awt.Color(166, 166, 166));
        labelfood2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelfood2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/food.png"))); // NOI18N
        labelfood2.setText("Coming Soon");
        labelfood2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelfood2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelfood2MousePressed(evt);
            }
        });
        panelfood2.add(labelfood2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        panelhome2.setBackground(new java.awt.Color(23, 27, 36));
        panelhome2.setToolTipText("");
        panelhome2.setAlignmentX(0.0F);
        panelhome2.setAlignmentY(0.0F);
        panelhome2.setColorGradient(new java.awt.Color(23, 27, 36));
        panelhome2.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelhome2.setMinimumSize(new java.awt.Dimension(180, 40));
        panelhome2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelhome2MousePressed(evt);
            }
        });
        panelhome2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelhome2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelhome2.setForeground(new java.awt.Color(166, 166, 166));
        labelhome2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelhome2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/home.png"))); // NOI18N
        labelhome2.setText("Home");
        labelhome2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelhome2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelhome2MousePressed(evt);
            }
        });
        panelhome2.add(labelhome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        javax.swing.GroupLayout panelClass2Layout = new javax.swing.GroupLayout(panelClass2);
        panelClass2.setLayout(panelClass2Layout);
        panelClass2Layout.setHorizontalGroup(
            panelClass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClass2Layout.createSequentialGroup()
                .addGroup(panelClass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClass2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(labelwelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClass2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelhome2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClass2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelbook2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClass2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelfood2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClass2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(paneldetail2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClass2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelwelcome1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        panelClass2Layout.setVerticalGroup(
            panelClass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClass2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(labelwelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(labelwelcome1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(panelhome2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelbook2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelfood2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(paneldetail2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(40, 62, 81));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search");

        view.setBackground(new java.awt.Color(0, 102, 102));
        view.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        view.setForeground(new java.awt.Color(255, 255, 255));
        view.setText("View");
        view.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        clear.setBackground(new java.awt.Color(0, 102, 102));
        clear.setForeground(new java.awt.Color(255, 255, 255));
        clear.setText("Clear");
        clear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        bookingdata.setBackground(new java.awt.Color(255, 255, 255));
        bookingdata.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 153)));
        bookingdata.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        bookingdata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Email", "Title", "Booking Date", "Booking Time", "Standard Tickets", "Premium Tickets", "Total Cost"
            }
        ));
        jScrollPane1.setViewportView(bookingdata);

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(view)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clear)
                .addGap(233, 233, 233))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(199, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clear)
                            .addComponent(view))
                        .addGap(124, 124, 124))))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(panelClass2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelClass2, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelhome2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelhome2MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateHome2();
    }//GEN-LAST:event_labelhome2MousePressed

    private void panelhome2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelhome2MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateHome2();
    }//GEN-LAST:event_panelhome2MousePressed

    private void labelbook2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelbook2MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateBook2();
    }//GEN-LAST:event_labelbook2MousePressed

    private void panelbook2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelbook2MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateBook2();
    }//GEN-LAST:event_panelbook2MousePressed

    private void labelfood2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelfood2MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateFood2();
    }//GEN-LAST:event_labelfood2MousePressed

    private void panelfood2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelfood2MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateFood2();
    }//GEN-LAST:event_panelfood2MousePressed

    private void labeldetail2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeldetail2MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateDetail2();
    }//GEN-LAST:event_labeldetail2MousePressed

    private void paneldetail2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneldetail2MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateDetail2();
    }//GEN-LAST:event_paneldetail2MousePressed

    private void labelhome2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelhome2MouseClicked
        // TODO add your handling code here:
        ModelUser user = new ModelUser();
        DashBoard df = new DashBoard(user);
        df.setLocation(this.getLocation());
        df.setVisible(true);
        dispose();
    }//GEN-LAST:event_labelhome2MouseClicked

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        // TODO add your handling code here:
    DefaultTableModel model = (DefaultTableModel) bookingdata.getModel();
    model.setRowCount(0);

    // SQL query to join the necessary tables and fetch the required fields, including username
    String query = "SELECT u.Email, m.title, b.BookingDate, b.BookingTime, b.NumStandardTickets, b.NumPremiumTickets, b.TotalCost " +
                   "FROM bookings b " +
                   "JOIN movies m ON b.MovieID = m.movie_id " +
                   "JOIN user u ON b.UserID = u.UserID";

    Connection con = null; // Declare the connection variable

    try {
        // Ensure the database connection is available
        if (con == null || con.isClosed()) {
            DatabaseConnection.getInstance().connectToDatabase();
            con = DatabaseConnection.getInstance().getConnection();
        }

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        // Loop through the result set and populate the table
        while (rs.next()) {
            String username = rs.getString("Email");
            String title = rs.getString("title");
            String bookingDate = rs.getString("BookingDate");
            String bookingTime = rs.getString("BookingTime");
            int numStandardTickets = rs.getInt("NumStandardTickets");
            int numPremiumTickets = rs.getInt("NumPremiumTickets");
            double totalCost = rs.getDouble("TotalCost");

            // Add the row to the table model
            model.addRow(new Object[]{username, title, bookingDate, bookingTime, numStandardTickets, numPremiumTickets, totalCost});
        }

        // Close the result set and statement
        rs.close();
        ps.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    } finally {
        try {
            if (con != null && !con.isClosed()) {
                con.close(); // Ensure the connection is closed
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error closing the database connection: " + ex.getMessage());
        }
    }
    }//GEN-LAST:event_viewActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) bookingdata.getModel();
        model.setRowCount(0); 
    }//GEN-LAST:event_clearActionPerformed

    private void labelbook2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelbook2MouseClicked
        // TODO add your handling code here:
        BookForm bf = new  BookForm();
        bf.setLocation(this.getLocation());
        bf.setVisible(true);
        dispose();
    }//GEN-LAST:event_labelbook2MouseClicked

    private void labelfood2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelfood2MouseClicked
        // TODO add your handling code here:
        ComingSoon cs = new  ComingSoon();
        cs.setLocation(this.getLocation());
        cs.setVisible(true);
        dispose();
    }//GEN-LAST:event_labelfood2MouseClicked
    
    
              
    private void searchKeyReleased(java.awt.event.KeyEvent evt) {                                   
    String searchString = search.getText();
    search(searchString);
}          

public void search(String str) {
    DefaultTableModel model = (DefaultTableModel) bookingdata.getModel();
    TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
    bookingdata.setRowSorter(trs);

    // Assuming the UserName column is at index 0 in the table
    try {
        trs.setRowFilter(RowFilter.regexFilter("(?i)" + str, 0)); // Use index 0 for UserName column
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Invalid search input: " + e.getMessage());
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
            java.util.logging.Logger.getLogger(BookingDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookingDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookingDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookingDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookingDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookingdata;
    private javax.swing.JButton clear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel labelbook2;
    public static javax.swing.JLabel labeldetail2;
    public static javax.swing.JLabel labelfood2;
    public static javax.swing.JLabel labelhome2;
    private javax.swing.JLabel labelwelcome;
    private javax.swing.JLabel labelwelcome1;
    private swing.Panel panel1;
    private component.PanelClass panelClass2;
    public static component.PanelGradient panelbook2;
    public static component.PanelGradient paneldetail2;
    public static component.PanelGradient panelfood2;
    public static component.PanelGradient panelhome2;
    private javax.swing.JTextField search;
    private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables
}
