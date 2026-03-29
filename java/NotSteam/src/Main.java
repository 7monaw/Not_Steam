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
