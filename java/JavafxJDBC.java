import java.sql.*;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavafxJDBC extends Application
{
    // private String tableName = "table";
    // int sqlCode=0;      // Variable to hold SQLCODE
    // String sqlState="00000";  // Variable to hold SQLSTATE

    private Connection databaseLink;
    private Statement statement;

    @Override
    public void start(Stage stage) throws Exception{
      try {
        this.databaseLink = connectToDB();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.setConnection(this.databaseLink);
        
        stage.setTitle("NotSteam");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
      }
      catch (Exception e) {
        System.out.println("Failed to initialize application:");
        e.printStackTrace();
      }
    }

    private Connection connectToDB() {
    try {
      String d_username = "cs421g07";
      String d_password = "NotSteam07";
      String url = "jdbc:db2://winter2026-comp421.cs.mcgill.ca:50000/comp421";

      // Error checks
      if(d_username == null && (d_username = System.getenv("SOCSUSER")) == null)
      {
        System.err.println("Error!! do not have a password to connect to the database!");
        System.exit(1);
      }
      if(d_password == null && (d_password = System.getenv("SOCSPASSWD")) == null)
      {
        System.err.println("Error!! do not have a password to connect to the database!");
        System.exit(1);
      }

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

    @Override
    public void stop() throws Exception {
      if (databaseLink != null && !databaseLink.isClosed()) {
          databaseLink.close();
          System.out.println("Database connection closed.");
      }
    }
    public static void main(String[] args) {
      launch(args);
    }
}
