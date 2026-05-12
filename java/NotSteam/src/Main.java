<<<<<<< HEAD
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
=======
package NotSteam;

import java.lang.classfile.Label;
import java.sql.*;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.geometry.Pos;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/NotSteam.fxml"));
            primaryStage.setTitle("This is NotSteam");

            VBox vbox = new VBox();
            Label label = new Label("Pick an option:");


            Button btn1 = new Button("Query1");
            btn1.setText("Query1");
            btn1.setOnAction(e -> {
            
                /*prompt user for arg input(s)
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter argument for Query1: ");
                String arg1 = scanner.nextLine();
                Scanner scanner2 = new Scanner(System.in);
                System.out.print("Enter another argument for Query1: ");
                int arg2 = scanner2.nextInt();
                query1(arg1, arg2);*/
            });

            Button btn2 = new Button("Query2");
            btn2.setText("Query2");
            btn2.setOnAction(e -> {
                // Handle btn2 action
            });
        
            Button btn3 = new Button("Query3");
            btn3.setText("Query3");
            btn3.setOnAction(e -> {
                // Handle btn3 action
            });

            Button btn4 = new Button("Mod4");
            btn4.setText("Mod4");
            btn4.setOnAction(e -> {
                // Handle btn4 action
            });

            Button btn5 = new Button("Mod5");
            btn5.setText("Mod5");
            btn5.setOnAction(e -> {
                // Handle btn5 action
            });

            vbox.getChildren().addAll(label, btn1, btn2, btn3, btn4, btn5);
            vbox.setAlignment(Pos.CENTER);
            
            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        // Register the driver.  You must register the driver before you can use it.
        try { DriverManager.registerDriver ( new com.ibm.db2.jcc.DB2Driver() ) ; }
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
            launch(args);

            statement.close();
            con.close();
        } catch (Exception e) {
            System.err.println("Database connection error: " + e.getMessage());
            System.exit(1);
        }

        private Connection connectToDB() {
    

      DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
      this.databaseLink = DriverManager.getConnection(url, d_username, d_password);
      this.statement = databaseLink.createStatement();

      System.out.println("Database connected successfully!");
      } 
      catch (Exception e) {
        e.printStackTrace();
      }
      return databaseLink;
    }
    }
}
>>>>>>> cfa20b472dfeede0849e23a55b0672d88faa938f
