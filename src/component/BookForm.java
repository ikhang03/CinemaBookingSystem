/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package component;
import Model.ModelUser;
import component.Animation1;
import DBConnection.DatabaseConnection;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author acer
 */
public class BookForm extends javax.swing.JFrame {
    Animation1 ColorOP = new Animation1();
    
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    /**
     * Creates new form BookForm
     */
    public BookForm() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        ColorOP.AnimateBook1();
          try {
            con = DatabaseConnection.getInstance().getConnection();
            if (con == null || con.isClosed()) {
                DatabaseConnection.getInstance().connectToDatabase();
                con = DatabaseConnection.getInstance().getConnection();
            }
            MovieList();
        } catch (SQLException e) {
            Logger.getLogger(BookForm.class.getName()).log(Level.SEVERE, "Error initializing database connection", e);
        }

        // Add item listener to JComboBox
        movelist.addItemListener(evt -> {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                MovieListCategory(evt);
            }
        });
    }
    
    
    public void MovieList(){
        String query = "SELECT title FROM movies";
        try {
            if (con == null || con.isClosed()) {
                DatabaseConnection.getInstance().connectToDatabase();
                con = DatabaseConnection.getInstance().getConnection();
            }

            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            movelist.removeAllItems(); 

            while (rs.next()) {
                movelist.addItem(rs.getString("title"));
            }
        } catch (SQLException e) {
            Logger.getLogger(BookForm.class.getName()).log(Level.SEVERE, "Error fetching movie list", e);
        } finally {
            // Cleanup resources to avoid memory leaks
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                Logger.getLogger(BookForm.class.getName()).log(Level.SEVERE, "Error closing resources", e);
            }
        }
    }
    
    private void MovieListCategory(ItemEvent evt) {
        String selectedTitle = movelist.getSelectedItem().toString();
        String query = "SELECT genre, duration, description FROM movies WHERE title = ?";
        try {
            if (con == null || con.isClosed()) {
                DatabaseConnection.getInstance().connectToDatabase();
                con = DatabaseConnection.getInstance().getConnection();
            }
            pst = con.prepareStatement(query);
            pst.setString(1, selectedTitle);
            rs = pst.executeQuery();

            if (rs.next()) {
                // Update text fields
                txtgenre.setText(rs.getString("genre"));
                txtlength.setText(String.valueOf(rs.getInt("duration")));
                txtdes.setLineWrap(true);  // Wrap text to fit the width
                txtdes.setWrapStyleWord(true); // Wrap at word boundaries
                txtdes.setEditable(false);
                txtdes.setText(rs.getString("description"));
            }
        } catch (SQLException e) {
            Logger.getLogger(BookForm.class.getName()).log(Level.SEVERE, "Error fetching movie details", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                Logger.getLogger(BookForm.class.getName()).log(Level.SEVERE, "Error closing resources", e);
            }
        }
    
    }
    private int getCurrentUserID() {
    // Retrieve user ID from login session or database.
    int userID = DatabaseConnection.getInstance().getUserID(); // Adjust this logic to your needs.
    return userID;
}

    public void insertToBooking() {
    try {
        // Ensure database connection is available
        if (con == null || con.isClosed()) {
            DatabaseConnection.getInstance().connectToDatabase();
            con = DatabaseConnection.getInstance().getConnection();
        }

        // Retrieve MovieID from the selected movie title
        String selectedMovie = movelist.getSelectedItem().toString();
        String movieID = null;
        String fetchMovieIDQuery = "SELECT movie_id FROM movies WHERE title = ?";
        PreparedStatement movieIDStmt = con.prepareStatement(fetchMovieIDQuery);
        movieIDStmt.setString(1, selectedMovie);
        ResultSet movieIDResult = movieIDStmt.executeQuery();
        if (movieIDResult.next()) {
            movieID = movieIDResult.getString("movie_id");
        }
        movieIDResult.close();
        movieIDStmt.close();

        if (movieID == null) {
            JOptionPane.showMessageDialog(null, "Movie not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int userID =  getCurrentUserID();
        if (userID <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid user session. Please log in.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve other inputs
        java.util.Date bookingDateUtil = date.getDate();
        java.sql.Date bookingDate = new java.sql.Date(bookingDateUtil.getTime());
        String bookingTime = time.getSelectedItem().toString(); // Assuming there is a field for time

        Integer standardTickets = Integer.parseInt(standard.getText());
        Integer premiumTickets = Integer.parseInt(premium.getText());
        
        
        double totalCost = standardTickets*5 + premiumTickets*10;
        txtTotal.setText(String.valueOf(totalCost));
        // Prepare the SQL query
        String query = "INSERT INTO bookings (UserID, MovieID, BookingDate, BookingTime, NumStandardTickets, NumPremiumTickets, TotalCost) VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        // Set the values
        ps.setInt(1, userID);
        ps.setString(2, movieID);
        ps.setDate(3, bookingDate);
        ps.setString(4, bookingTime);
        ps.setInt(5, standardTickets);
        ps.setInt(6, premiumTickets);
        ps.setDouble(7, totalCost);

        // Execute the query
        int updateRowCount = ps.executeUpdate();

        // Check result
        if (updateRowCount != 0) {
            JOptionPane.showMessageDialog(null, "Booking successful!", "Booking Status", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Booking failed. Please try again.", "Booking Status", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Invalid input. Please check your data.", "Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel3 = new javax.swing.JPanel();
        panel1 = new swing.Panel();
        panelClass2 = new component.PanelClass();
        labelwelcome1 = new javax.swing.JLabel();
        labelwelcome = new javax.swing.JLabel();
        paneldetail1 = new component.PanelGradient();
        labeldetail1 = new javax.swing.JLabel();
        panelbook1 = new component.PanelGradient();
        labelbook1 = new javax.swing.JLabel();
        panelfood1 = new component.PanelGradient();
        labelfood1 = new javax.swing.JLabel();
        panelhome1 = new component.PanelGradient();
        labelhome1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        movelist = new javax.swing.JComboBox<>();
        labellength = new javax.swing.JLabel();
        labeldes = new javax.swing.JLabel();
        labelgenre = new javax.swing.JLabel();
        labelgenre1 = new javax.swing.JLabel();
        txtgenre = new javax.swing.JTextField();
        txtlength = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdes = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        time = new javax.swing.JComboBox<>();
        date = new com.toedter.calendar.JDateChooser();
        premium = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        labelgenre2 = new javax.swing.JLabel();
        labelgenre3 = new javax.swing.JLabel();
        labelgenre4 = new javax.swing.JLabel();
        labelgenre6 = new javax.swing.JLabel();
        standard = new javax.swing.JTextField();
        labelgenre5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Check = new javax.swing.JButton();
        labelgenre7 = new javax.swing.JLabel();
        discount = new javax.swing.JTextField();
        buttonbook = new javax.swing.JButton();
        buttoncancel = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
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

        paneldetail1.setBackground(new java.awt.Color(23, 27, 36));
        paneldetail1.setToolTipText("");
        paneldetail1.setAlignmentX(0.0F);
        paneldetail1.setAlignmentY(0.0F);
        paneldetail1.setColorGradient(new java.awt.Color(23, 27, 36));
        paneldetail1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        paneldetail1.setMinimumSize(new java.awt.Dimension(180, 40));
        paneldetail1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneldetail1MousePressed(evt);
            }
        });
        paneldetail1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labeldetail1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labeldetail1.setForeground(new java.awt.Color(166, 166, 166));
        labeldetail1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labeldetail1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/detail.png"))); // NOI18N
        labeldetail1.setText("Booking Detail");
        labeldetail1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labeldetail1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labeldetail1MousePressed(evt);
            }
        });
        paneldetail1.add(labeldetail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 40));

        panelbook1.setBackground(new java.awt.Color(23, 27, 36));
        panelbook1.setToolTipText("");
        panelbook1.setAlignmentX(0.0F);
        panelbook1.setAlignmentY(0.0F);
        panelbook1.setColorGradient(new java.awt.Color(23, 27, 36));
        panelbook1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelbook1.setMinimumSize(new java.awt.Dimension(180, 40));
        panelbook1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelbook1MousePressed(evt);
            }
        });
        panelbook1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelbook1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelbook1.setForeground(new java.awt.Color(166, 166, 166));
        labelbook1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelbook1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/books.png"))); // NOI18N
        labelbook1.setText("Book");
        labelbook1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelbook1MousePressed(evt);
            }
        });
        panelbook1.add(labelbook1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        panelfood1.setBackground(new java.awt.Color(23, 27, 36));
        panelfood1.setToolTipText("");
        panelfood1.setAlignmentX(0.0F);
        panelfood1.setAlignmentY(0.0F);
        panelfood1.setColorGradient(new java.awt.Color(23, 27, 36));
        panelfood1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelfood1.setMinimumSize(new java.awt.Dimension(180, 40));
        panelfood1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelfood1MousePressed(evt);
            }
        });
        panelfood1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelfood1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelfood1.setForeground(new java.awt.Color(166, 166, 166));
        labelfood1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelfood1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/food.png"))); // NOI18N
        labelfood1.setText("Coming Soon");
        labelfood1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelfood1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelfood1MousePressed(evt);
            }
        });
        panelfood1.add(labelfood1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        panelhome1.setBackground(new java.awt.Color(23, 27, 36));
        panelhome1.setToolTipText("");
        panelhome1.setAlignmentX(0.0F);
        panelhome1.setAlignmentY(0.0F);
        panelhome1.setColorGradient(new java.awt.Color(23, 27, 36));
        panelhome1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelhome1.setMinimumSize(new java.awt.Dimension(180, 40));
        panelhome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelhome1MousePressed(evt);
            }
        });
        panelhome1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelhome1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelhome1.setForeground(new java.awt.Color(166, 166, 166));
        labelhome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelhome1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/home.png"))); // NOI18N
        labelhome1.setText("Home");
        labelhome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelhome1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelhome1MousePressed(evt);
            }
        });
        panelhome1.add(labelhome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

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
                        .addComponent(panelhome1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClass2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelbook1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClass2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelfood1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClass2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(paneldetail1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(panelhome1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelbook1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelfood1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(paneldetail1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBackground(new java.awt.Color(40, 62, 81));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(0, 0, 0));

        movelist.setBackground(new java.awt.Color(255, 255, 255));
        movelist.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        movelist.setForeground(new java.awt.Color(0, 0, 0));
        movelist.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        movelist.setBorder(null);

        labellength.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        labellength.setForeground(new java.awt.Color(0, 102, 102));
        labellength.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labellength.setText("Length");

        labeldes.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        labeldes.setForeground(new java.awt.Color(0, 102, 102));
        labeldes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labeldes.setText("Description");

        labelgenre.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        labelgenre.setForeground(new java.awt.Color(0, 102, 102));
        labelgenre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelgenre.setText("Genre");

        labelgenre1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        labelgenre1.setForeground(new java.awt.Color(0, 102, 102));
        labelgenre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelgenre1.setText("Film ");

        txtgenre.setBackground(new java.awt.Color(255, 255, 255));
        txtgenre.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtgenre.setForeground(new java.awt.Color(0, 0, 0));
        txtgenre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtgenre.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 153)));

        txtlength.setBackground(new java.awt.Color(255, 255, 255));
        txtlength.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtlength.setForeground(new java.awt.Color(0, 0, 0));
        txtlength.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtlength.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 153)));
        txtlength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlengthActionPerformed(evt);
            }
        });

        txtdes.setBackground(new java.awt.Color(255, 255, 255));
        txtdes.setColumns(20);
        txtdes.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtdes.setForeground(new java.awt.Color(0, 0, 0));
        txtdes.setRows(5);
        txtdes.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 153)));
        jScrollPane1.setViewportView(txtdes);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelgenre)
                            .addComponent(labelgenre1)
                            .addComponent(labellength))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(movelist, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtgenre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                .addComponent(txtlength, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(34, 34, 34))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labeldes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(movelist, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelgenre1))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelgenre)
                    .addComponent(txtgenre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labellength, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtlength, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(labeldes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setForeground(new java.awt.Color(0, 0, 0));

        time.setBackground(new java.awt.Color(255, 255, 255));
        time.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        time.setForeground(new java.awt.Color(0, 0, 0));
        time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "0:00", "1:00" }));
        time.setBorder(null);
        time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeActionPerformed(evt);
            }
        });

        date.setBackground(new java.awt.Color(255, 255, 255));
        date.setForeground(new java.awt.Color(0, 102, 102));

        premium.setBackground(new java.awt.Color(255, 255, 255));
        premium.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        premium.setForeground(new java.awt.Color(0, 0, 0));
        premium.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        premium.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 153)));
        premium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                premiumActionPerformed(evt);
            }
        });

        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(0, 0, 0));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 153)));

        labelgenre2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        labelgenre2.setForeground(new java.awt.Color(0, 102, 102));
        labelgenre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelgenre2.setText("Date");

        labelgenre3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        labelgenre3.setForeground(new java.awt.Color(0, 102, 102));
        labelgenre3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelgenre3.setText("Time");

        labelgenre4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        labelgenre4.setForeground(new java.awt.Color(0, 102, 102));
        labelgenre4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelgenre4.setText("Premium");

        labelgenre6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        labelgenre6.setForeground(new java.awt.Color(0, 102, 102));
        labelgenre6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelgenre6.setText("Total");

        standard.setBackground(new java.awt.Color(255, 255, 255));
        standard.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        standard.setForeground(new java.awt.Color(0, 0, 0));
        standard.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        standard.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 153)));

        labelgenre5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        labelgenre5.setForeground(new java.awt.Color(0, 102, 102));
        labelgenre5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelgenre5.setText("Standard");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Standard: $5");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Premium: $10");

        Check.setBackground(new java.awt.Color(0, 102, 153));
        Check.setFont(new java.awt.Font("SansSerif", 3, 12)); // NOI18N
        Check.setForeground(new java.awt.Color(255, 255, 255));
        Check.setText("Check Cost");
        Check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckActionPerformed(evt);
            }
        });

        labelgenre7.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        labelgenre7.setForeground(new java.awt.Color(0, 102, 102));
        labelgenre7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelgenre7.setText("Discount");

        discount.setBackground(new java.awt.Color(255, 255, 255));
        discount.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        discount.setForeground(new java.awt.Color(0, 0, 0));
        discount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        discount.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 153)));
        discount.setMinimumSize(new java.awt.Dimension(64, 30));
        discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(labelgenre4)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(labelgenre6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelgenre2)
                                    .addComponent(labelgenre3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(time, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(labelgenre5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addComponent(standard, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(labelgenre7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(discount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(premium, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))))
                        .addGap(22, 22, 22))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Check)
                .addGap(34, 34, 34))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelgenre2)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelgenre3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(standard, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelgenre5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelgenre4)
                    .addComponent(premium, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelgenre7)
                    .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelgenre6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Check)
                .addContainerGap())
        );

        buttonbook.setBackground(new java.awt.Color(0, 102, 153));
        buttonbook.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        buttonbook.setForeground(new java.awt.Color(255, 255, 255));
        buttonbook.setText("Book");
        buttonbook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonbookActionPerformed(evt);
            }
        });

        buttoncancel.setBackground(new java.awt.Color(0, 102, 153));
        buttoncancel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        buttoncancel.setForeground(new java.awt.Color(255, 255, 255));
        buttoncancel.setText("Cancel");
        buttoncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttoncancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonbook, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(buttoncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(43, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonbook, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttoncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(panelClass2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelClass2, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void labelhome1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelhome1MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateHome1();
    }//GEN-LAST:event_labelhome1MousePressed

    private void panelhome1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelhome1MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateHome1();
    }//GEN-LAST:event_panelhome1MousePressed

    private void labelbook1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelbook1MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateBook1();
    }//GEN-LAST:event_labelbook1MousePressed

    private void panelbook1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelbook1MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateBook1();
    }//GEN-LAST:event_panelbook1MousePressed

    private void labelfood1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelfood1MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateFood1();
    }//GEN-LAST:event_labelfood1MousePressed

    private void panelfood1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelfood1MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateFood1();
    }//GEN-LAST:event_panelfood1MousePressed

    private void labeldetail1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeldetail1MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateDetail1();
    }//GEN-LAST:event_labeldetail1MousePressed

    private void paneldetail1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneldetail1MousePressed
        // TODO add your handling code here:
        ColorOP.AnimateDetail1();
    }//GEN-LAST:event_paneldetail1MousePressed

    private void labelhome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelhome1MouseClicked
        // TODO add your handling code here:
        ModelUser user = new ModelUser();
        DashBoard df = new DashBoard(user);
        df.setLocation(this.getLocation());
        df.setVisible(true);
        dispose();
      
    }//GEN-LAST:event_labelhome1MouseClicked

    private void txtlengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlengthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlengthActionPerformed

    private void buttonbookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonbookActionPerformed
        // TODO add your handling code here:
        insertToBooking();
    }//GEN-LAST:event_buttonbookActionPerformed

    private void buttoncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttoncancelActionPerformed
        // TODO add your handling code here:
        ModelUser user = new ModelUser();
        DashBoard df = new DashBoard(user);
        df.setLocation(this.getLocation());
        df.setVisible(true);
        dispose();
    }//GEN-LAST:event_buttoncancelActionPerformed

    private void CheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckActionPerformed
        // TODO add your handling code here:
        try {
        // Fetch the number of standard and premium tickets from their respective text fields
        int standardTickets = Integer.parseInt(standard.getText());
        int premiumTickets = Integer.parseInt(premium.getText());
        String discountValue = discount.getText();


        // Calculate the total cost
        double totalCost = standardTickets * 5 + premiumTickets * 10;
        if ("10%".equals(discountValue)) {
            totalCost = totalCost * 0.9; 
            } else if (discountValue.isEmpty()) {
            totalCost = totalCost;
    }

        // Set the calculated total cost in the txtTotal JTextField
        txtTotal.setText(String.valueOf(totalCost));

        // Optionally display the total cost in a message dialog
        JOptionPane.showMessageDialog(this, "Check Total Cost: " + totalCost, "Checking Information", JOptionPane.INFORMATION_MESSAGE);
    } catch (NumberFormatException e) {
        // Handle invalid inputs gracefully
        JOptionPane.showMessageDialog(this, "Please enter valid numbers for tickets.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_CheckActionPerformed

    private void labeldetail1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labeldetail1MouseClicked
        // TODO add your handling code here:
        BookingDetail bd = new  BookingDetail();
        bd.setLocation(this.getLocation());
        bd.setVisible(true);
        dispose();
    }//GEN-LAST:event_labeldetail1MouseClicked

    private void labelfood1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelfood1MouseClicked
        // TODO add your handling code here:
        ComingSoon cs = new  ComingSoon();
        cs.setLocation(this.getLocation());
        cs.setVisible(true);
        dispose();
    }//GEN-LAST:event_labelfood1MouseClicked

    private void timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeActionPerformed
         String selectedValue = time.getSelectedItem().toString();
    
    // Define the discount period
    String startDiscount = "23:00";
    String endDiscount = "01:00";

    // Check if the selected time falls within the discount period
    if (isWithinDiscountPeriod(selectedValue, startDiscount, endDiscount)) {
        discount.setText("10%"); // Set "10%" in the JTextField
    } else {
        discount.setText(""); // Clear the JTextField if no discount
    }
    }//GEN-LAST:event_timeActionPerformed

    private void premiumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_premiumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_premiumActionPerformed

    private void discountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountActionPerformed
    
    private boolean isWithinDiscountPeriod(String selectedValue, String startDiscount, String endDiscount) {
    // Convert times to comparable integers (e.g., 23:00 -> 2300, 01:00 -> 100)
    int selectedTime = convertTimeToInt(selectedValue);
    int startTime = convertTimeToInt(startDiscount);
    int endTime = convertTimeToInt(endDiscount);

    // Special handling for overnight periods (23:00 to 01:00)
    if (startTime > endTime) {
        return (selectedTime >= startTime || selectedTime <= endTime);
    } else {
        return (selectedTime >= startTime && selectedTime <= endTime);
    }
}

// Helper method to convert time in "HH:mm" format to an integer (e.g., "23:00" -> 2300)
private int convertTimeToInt(String time) {
    return Integer.parseInt(time.replace(":", ""));
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
            java.util.logging.Logger.getLogger(BookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Check;
    private javax.swing.JButton buttonbook;
    private javax.swing.JButton buttoncancel;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JTextField discount;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel labelbook1;
    private javax.swing.JLabel labeldes;
    public static javax.swing.JLabel labeldetail1;
    public static javax.swing.JLabel labelfood1;
    private javax.swing.JLabel labelgenre;
    private javax.swing.JLabel labelgenre1;
    private javax.swing.JLabel labelgenre2;
    private javax.swing.JLabel labelgenre3;
    private javax.swing.JLabel labelgenre4;
    private javax.swing.JLabel labelgenre5;
    private javax.swing.JLabel labelgenre6;
    private javax.swing.JLabel labelgenre7;
    public static javax.swing.JLabel labelhome1;
    private javax.swing.JLabel labellength;
    private javax.swing.JLabel labelwelcome;
    private javax.swing.JLabel labelwelcome1;
    private javax.swing.JComboBox<String> movelist;
    private swing.Panel panel1;
    private component.PanelClass panelClass2;
    public static component.PanelGradient panelbook1;
    public static component.PanelGradient paneldetail1;
    public static component.PanelGradient panelfood1;
    public static component.PanelGradient panelhome1;
    private javax.swing.JTextField premium;
    private javax.swing.JTextField standard;
    private javax.swing.JComboBox<String> time;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextArea txtdes;
    private javax.swing.JTextField txtgenre;
    private javax.swing.JTextField txtlength;
    // End of variables declaration//GEN-END:variables
}
