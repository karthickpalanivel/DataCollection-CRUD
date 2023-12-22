/*
 NOTE: You need to insert your database table in the public void connect method.
 */



import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UserFrame extends Frame implements ActionListener {

    Label lblTitle, lblName, lblEmail, lblId, lblCity, lblAge, lblStatus, lblPhone;
    TextField txtName, txtEmail, txtId, txtCity, txtAge, txtPhone;
    // id, name, phone, age, email, phone
    Button btnSave, btnClear, btnDelete;

    String qry = "";
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    Statement stmt = null;

    // Database connection (need to add your database table)
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/()?characterEncoding=utf8";
            String username = "root";
            String password = "root";
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        // TextField txtName, txtEmail, txtId, txtCity, txtAge, txtPhone;
        txtName.setText("");
        txtEmail.setText("");
        txtAge.setText("");
        txtCity.setText("");
        txtPhone.setText("");
        txtId.setText("");
        txtName.requestFocus();
    }

    public UserFrame() {
        connect();
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setTitle("User Management System");
        this.setLayout(null);
        Color formcolor = new Color(53, 59, 72);
        setBackground(formcolor);

        // setting fonts
        Font titleFont = new Font("arial", Font.BOLD, 25);
        Font lblFont = new Font("arial", Font.BOLD, 15);
        Font inputFont = new Font("arial", Font.PLAIN, 20);
        Font statusFont = new Font("arail", Font.BOLD, 25);

        lblTitle = new Label("User Management System");
        lblTitle.setBounds(250, 40, 400, 50);
        lblTitle.setFont(titleFont);
        lblTitle.setForeground(Color.yellow);
        add(lblTitle);

        lblId = new Label("ID");
        lblId.setBounds(250, 100, 100, 30);
        lblId.setFont(lblFont);
        lblId.setForeground(Color.white);
        add(lblId);

        txtId = new TextField();
        txtId.addActionListener(this);
        txtId.setBounds(400, 100, 300, 30);
        txtId.setFont(inputFont);
        add(txtId);

        lblName = new Label("NAME");
        lblName.setBounds(250, 150, 100, 30);
        lblName.setFont(lblFont);
        lblName.setForeground(Color.white);

        add(lblName);

        txtName = new TextField();
        txtName.addActionListener(this);
        txtName.setBounds(400, 150, 300, 30);
        txtName.setFont(inputFont);
        add(txtName);

        lblPhone = new Label("PHONE");
        lblPhone.setBounds(250, 200, 100, 30);
        lblPhone.setFont(lblFont);
        lblPhone.setForeground(Color.white);
        add(lblPhone);

        txtPhone = new TextField();
        txtPhone.addActionListener(this);
        txtPhone.setBounds(400, 200, 300, 30);
        txtPhone.setFont(inputFont);
        add(txtPhone);

        lblCity = new Label("CITY");
        lblCity.setBounds(250, 250, 100, 30);
        lblCity.setFont(lblFont);
        lblCity.setForeground(Color.white);
        add(lblCity);

        txtCity = new TextField();
        txtCity.addActionListener(this);
        txtCity.setBounds(400, 250, 300, 30);
        txtCity.setFont(inputFont);
        add(txtCity);

        lblAge = new Label("AGE");
        lblAge.setBounds(250, 300, 100, 30);
        lblAge.setFont(lblFont);
        lblAge.setForeground(Color.white);
        add(lblAge);

        txtAge = new TextField();
        txtAge.addActionListener(this);
        txtAge.setBounds(400, 300, 300, 30);
        txtAge.setFont(inputFont);
        add(txtAge);

        lblEmail = new Label("E-MAIL");
        lblEmail.setBounds(250, 350, 100, 30);
        lblEmail.setFont(lblFont);
        lblEmail.setForeground(Color.white);
        add(lblEmail);

        txtEmail = new TextField();
        txtEmail.addActionListener(this);
        txtEmail.setBounds(400, 350, 300, 30);
        txtEmail.setFont(inputFont);
        add(txtEmail);

        // Buttons

        btnSave = new Button("Save");
        btnSave.setBounds(250, 400, 75, 25);
        btnSave.setFont(inputFont);
        btnSave.setForeground(Color.white);
        btnSave.setBackground(Color.blue);
        btnSave.addActionListener(this);
        add(btnSave);

        btnClear = new Button("Clear");
        btnClear.setBounds(350, 400, 75, 25);
        btnClear.setFont(inputFont);
        btnClear.setForeground(Color.white);
        btnClear.setBackground(Color.gray);
        btnClear.addActionListener(this);
        add(btnClear);

        btnDelete = new Button("Delete");
        btnDelete.setBounds(450, 400, 75, 25);
        btnDelete.setFont(inputFont);
        btnDelete.setForeground(Color.white);
        btnDelete.setBackground(Color.red);
        btnDelete.addActionListener(this);
        add(btnDelete);

        lblStatus = new Label("Status");
        lblStatus.setFont(statusFont);
        lblStatus.setForeground(Color.white);
        lblStatus.setBounds(250, 450, 350, 30);
        add(lblStatus);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String id = txtId.getText(); 
            String name = txtName.getText();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            String city = txtCity.getText();
            String age = txtAge.getText();


            if (e.getSource().equals(txtId)) {
                qry = "SELECT ID, NAME, PHONE, CITY, AGE, EMAIL from users where ID = " + txtId.getText();
                stmt = con.createStatement();
                rs = stmt.executeQuery(qry);
                if (rs.next()) {
                    txtId.setText(rs.getString("ID"));
                    txtName.setText(rs.getString("NAME"));
                    txtPhone.setText(rs.getString("PHONE"));
                    txtCity.setText(rs.getString("CITY"));
                    txtAge.setText(rs.getString("AGE"));
                    txtEmail.setText(rs.getString("EMAIL"));
                }
                
            }

            if (e.getSource().equals(btnClear)) {
                clear();
                lblStatus.setText("");
            } else if (e.getSource().equals(btnSave)) {
                if (id.isEmpty() || id.equals("")) {
                    qry = "insert into users (NAME, PHONE, CITY, AGE, EMAIL) values(?,?,?,?,?)";
                    st = con.prepareStatement(qry);
                    st.setString(1, name);
                    st.setString(2, phone);
                    st.setString(3, city);
                    st.setString(4, age);
                    st.setString(5, email);
                    st.executeUpdate();
                    clear();
                } else {
                    qry = "udpate users set NAME=?, PHONE=?, CITY=?,AGE=?, EMAIL=? where ID = ?";
                    st = con.prepareStatement(qry);
                    st.setString(1, name);
                    st.setString(2, phone);
                    st.setString(3, city);
                    st.setString(4, age);
                    st.setString(5, email);
                    lblStatus.setText("Date Update Success");
                    clear();
                }
                   
            } else if (e.getSource().equals(btnDelete)) {
                if (!id.isEmpty() || !id.equals("")) {
                    qry = "delete from users where ID=?";
                    st = con.prepareStatement(qry);
                    st.setString(1, id);
                    st.executeUpdate();
                    lblStatus.setText("Data Deleted Successfully");
                    clear();
                } else {
                    lblStatus.setText("Please Enter the correct ID");
                }
            } 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

public class user {
    public static void main(String[] args) {
        UserFrame frm = new UserFrame();
    }
}