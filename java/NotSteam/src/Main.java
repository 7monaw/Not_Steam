package NotSteam;

import java.sql.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import java.awt.*;



public class Main {
    private Connection dbConnection;

    public void setConnection(Connection connection) {
        this.dbConnection = connection;
    }

    JTextArea output;
    JTextArea resultArea;

    private void createUI() {
        // Set up the scene
        JFrame frame = new JFrame("This is NotSteam");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
        //Layout
        frame.setLayout(new BorderLayout());

        //Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));

        output = new JTextArea();
        JScrollPane scroll = new JScrollPane(output);

        JButton b1 = new JButton("Q1");
        JButton b2 = new JButton("Q2");
            
        JTextField dev = new JTextField(20);
        JTextField location = new JTextField(20);
        JButton b3 = new JButton("Find Target Clients");

        JButton b4 = new JButton("Q4");
        JButton b5 = new JButton("Q5");
        JButton b6 = new JButton("Q6");

        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        buttonPanel.add(b5);
        buttonPanel.add(b6);

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);

        b1.addActionListener(e -> System.out.println("Q1 not implemented yet"));
        b2.addActionListener(e -> System.out.println("Q2 not implemented yet"));
        b3.addActionListener(e -> {
            String Input_dev = JOptionPane.showInputDialog("Enter a developer name: ");
            String Input_location = JOptionPane.showInputDialog("Enter location: ");
            if (dev != null && location != null) {
                targetClient(dev.getText().trim(), location.getText().trim());
            }
        });
        b4.addActionListener(e -> System.out.println("Q4 not implemented yet"));
        b5.addActionListener(e -> System.out.println("Q5 not implemented yet"));
        b6.addActionListener(e -> System.out.println("Q6 not implemented yet"));
            
        frame.setVisible(true);
    }

    private void targetClient(String dev, String location) {
        if (dev.isEmpty() || location.isEmpty()) {
            output.setText("Please first enter the developer name and then enter the desired location:");
            return;
        }

        String sql = "SELECT DISTINCT u.username " +
        "FROM User u " +
        "JOIN InterestedIn ii ON u.username = ii.username " +
        "JOIN Associates a ON ii.genre_name = a.genre_name " +
        "JOIN Releases r ON a.gameID = r.gameID " +
        "JOIN Game g ON g.gameID = r.gameID " +
        "JOIN Available_In ai ON ai.gameID = g.gameID " +
        "WHERE r.dev_name = ? " +
        "AND ai.loc_name = ? " +
        "AND u.age >= CASE g.age_rating WHEN 'T' THEN 13 WHEN 'M' THEN 17 ELSE 0 END";

        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setString(1, dev);
            statement.setString(2, location);
            
            try (ResultSet results = statement.executeQuery()) {
                StringBuilder str = new StringBuilder("Target Audience for " + dev + " in " + location + ":\n");
                boolean found = false;

                while (results.next()) {
                    str.append("- ").append(results.getString("username")).append("\n");
                    found = true;
                }

                resultArea.setText(found ? str.toString() : "No target clients found for " + dev);
            }
        } catch (SQLException e) {
            resultArea.setText("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Register the driver.  You must register the driver before you can use it.
        try {DriverManager.registerDriver ( new com.ibm.db2.jcc.DB2Driver() ) ; }
        catch (Exception cnfe){ System.out.println("Class not found"); }

        // This is the url you must use for DB2.
        //Note: This url may not valid now ! Check for the correct year and semester and server name.
        String url = "jdbc:db2://winter2026-comp421.cs.mcgill.ca:50000/comp421";

        //REMEMBER to remove your user id and password before submitting your code!!
        String your_userid = "cs421g07";
        String your_password = "NotSteam07";
        //AS AN ALTERNATIVE, you can just set your password in the shell environment in the Unix (as shown below) and read it from there.
        //$  export SOCSPASSWD=yoursocspasswd 
        if(your_userid == null && (your_userid = System.getenv("SOCSUSER")) == null)
        {
          System.err.println("Error!! do not have a password to connect to the database!");
          System.exit(1);
        }
        if(your_password == null && (your_password = System.getenv("SOCSPASSWD")) == null)
        {
          System.err.println("Error!! do not have a password to connect to the database!");
          System.exit(1);
        }

        try {
            Connection con = DriverManager.getConnection(url, your_userid, your_password);
            Statement statement = con.createStatement();

            // Store connection for JavaFX controller, then launch UI
            // you can use a static field or pass through FXMLLoader as described earlier
            new Main().createUI();

            statement.close();
            con.close();
        } catch (Exception e) {
            System.err.println("Database connection error: " + e.getMessage());
            System.exit(1);
        }
    }
}
