import java.sql.*;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class simpleJDBC extends Application
{
    private Connection con;
    private Statement statement;
    private String tableName = "table";
    String tableName = "";
    int sqlCode=0;      // Variable to hold SQLCODE
    String sqlState="00000";  // Variable to hold SQLSTATE

    @Override
    public void start(Stage stage) {
      startJDBC();

      stage.setTitle("NotSteam");
        
      // Use VBox instead of StackPane so buttons aren't stacked on top of each other
      Button button1 = new Button("Create Table");
      Button button2 = new Button("Insert Data");
      Button button3  = new Button("Run Query");
      Button button4 = new Button("Update & Drop");

      // 3. Map Button Actions to your original logic
      button1.setOnAction(e -> executeCreate());
      button2.setOnAction(e -> executeInsert());
      button3.setOnAction(e -> executeQuery());
      button4.setOnAction(e -> executeUpdateAndDrop());

      VBox root = new VBox(10, "Label", button1, button2, button3, button4);
      root.setStyle("-fx-padding: 20; -fx-alignment: left;");
        
      stage.setScene(new Scene(root, 400, 300));
      stage.show();
    }

    private void connectToDB() {
      try {
        DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
        String url = "jdbc:db2://winter2024-comp421.cs.mcgill.ca:50000/comp421";
            
        // Using environment variables as per your logic
        String user = System.getenv("SOCSUSER");
        String pass = System.getenv("SOCSPASSWD");

        this.con = DriverManager.getConnection(url, user, pass);
        this.statement = con.createStatement();
        } 
        catch (Exception e) {
          e.printStackTrace();
        }
    }
        
    // Creating a table
    private void create() {
      try
      {
        String createSQL = "CREATE TABLE " + tableName + " (id INTEGER, name VARCHAR (25)) ";
        System.out.println (createSQL ) ;
        statement.update (createSQL ) ;
        System.out.println ("DONE");
      }
      catch (SQLException e)
      {
        sqlCode = e.getErrorCode(); // Get SQLCODE
        sqlState = e.getSQLState(); // Get SQLSTATE

        // Your code to handle errors comes here;
        // something more meaningful than a print would be good
        System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        System.out.println(e);
      }
    }  

    private void insert() {
      try
      {
        String insertSQL = "INSERT INTO " + tableName + " VALUES ( 1 , \'Vicki\' ) " ;
        System.out.println ( insertSQL ) ;
        statement.update ( insertSQL ) ;
        System.out.println ( "DONE" ) ;

        insertSQL = "INSERT INTO " + tableName + " VALUES ( 2 , \'Vera\' ) " ;
        System.out.println ( insertSQL ) ;
        statement.update ( insertSQL ) ;
        System.out.println ( "DONE" ) ;
        insertSQL = "INSERT INTO " + tableName + " VALUES ( 3 , \'Franca\' ) " ;
        System.out.println ( insertSQL ) ;
        statement.update ( insertSQL ) ;
        System.out.println ( "DONE" ) ;

      }
      catch (SQLException e)
      {
        sqlCode = e.getErrorCode(); // Get SQLCODE
        sqlState = e.getSQLState(); // Get SQLSTATE

        // Your code to handle errors comes here;
        // something more meaningful than a print would be good
        System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        System.out.println(e);
        }
      }
        

      // Querying a table
      private void query() {
        try
        {
          String querySQL = "SELECT id, name from " + tableName + " WHERE NAME = \'Vicki\'";
          System.out.println (querySQL) ;
          java.sql.ResultSet rs = statement.query ( querySQL ) ;

          while ( rs.next ( ) )
          {
            int id = rs.getInt ( 1 ) ;
            String name = rs.getString (2);
            System.out.println ("id:  " + id);
            System.out.println ("name:  " + name);
          }
         System.out.println ("DONE");
        }
        catch (SQLException e)
        {
          sqlCode = e.getErrorCode(); // Get SQLCODE
          sqlState = e.getSQLState(); // Get SQLSTATE

          // Your code to handle errors comes here;
          // something more meaningful than a print would be good
          System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
          System.out.println(e);
        }
      }

      //Updating a table
      private void update() {
        try
        {
          String updateSQL = "UPDATE " + tableName + " SET NAME = \'Mimi\' WHERE id = 3";
          System.out.println(updateSQL);
          statement.update(updateSQL);
          System.out.println("DONE");
          
          // Dropping a table
          String dropSQL = "DROP TABLE " + tableName;
          System.out.println ( dropSQL ) ;
          statement.update ( dropSQL ) ;
          System.out.println ("DONE");
        }
        catch (SQLException e)
        {
          sqlCode = e.getErrorCode(); // Get SQLCODE
          sqlState = e.getSQLState(); // Get SQLSTATE
                    
          // Your code to handle errors comes here;
          // something more meaningful than a print would be good
          System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
          System.out.println(e);
        }S
      }
      

      // Finally but importantly close the statement and connection
      statement.close();
      con.close();
      super.stop();

      public static void main(String[] args) {
        launch(args);
      }
  }
}
