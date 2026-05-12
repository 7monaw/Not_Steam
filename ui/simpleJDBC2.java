import java.sql.* ;
import java.util.Scanner;

  public class simpleJDBC {
    public static void main ( String [ ] args ) throws SQLException
    {
      // Unique table names.  Either the user supplies a unique identifier as a command line argument, or the program makes one up.
        String tableName = "";
        int sqlCode=0;      // Variable to hold SQLCODE
        String sqlState="00000";  // Variable to hold SQLSTATE

        if ( args.length > 0 )
            tableName += args [ 0 ] ;
        else
          tableName += "exampletbl";

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

        Connection con = DriverManager.getConnection (url,your_userid,your_password) ;
        Statement statement = con.createStatement ( ) ;

        //Menu
        Scanner scanner = new Scanner(System.in);
        while (true) {
          System.out.println("Choose an option: 1. Option1 2. Option2 3. Option3 6. Quit");
          int choice = scanner.nextInt();

          switch (choice) {
            case 1:
                System.out.println("You selected Option 1");
                String dev_name, loc;
                System.out.println("Please enter the developer name:");
                dev_name = scanner.next();
                System.out.println("Please enter the location:");
                loc = scanner.next();

                simpleJDBC instance = new simpleJDBC();
                instance.targetClient(con, dev_name, loc);
                break;
            
            case 2:
                System.out.println("You selected Option 2");
                String friend, max_price;
                System.out.println("Please enter the friend's username:");
                friend = scanner.next();
                System.out.println("Please enter your desired maximum price:");
                max_price = scanner.next();

                simpleJDBC instance2 = new simpleJDBC();
                instance2.recommendGames(con, friend, max_price);
                break;

            case 3:
                System.out.println("You selected Option 3");
                String username, password, location;
                int age;

                // check username
                System.out.println("Please enter a new username: ");
                username = scanner.next();
                simpleJDBC instance3_1 = new simpleJDBC();
                boolean username_return = instance3_1.checkUsername(con, username);
                if(username_return == false) {
                  break;
                }

                //check password
                System.out.println("Please enter a password (minimum 8 characters): ");
                password = scanner.next();
                simpleJDBC instance3_2 = new simpleJDBC();
                boolean password_return = instance3_2.checkPassword(con, password);
                if(password_return == false) {
                  break;
                }
                
                // check location
                System.out.println("Please enter your location\nOptions: North American, South America, Europe, Asia, Africa, Oceania");
                location = scanner.next();
                simpleJDBC instance3_3 = new simpleJDBC();
                boolean location_return = instance3_3.checkLocation(con, location);
                if(location_return == false) {
                  break;
                }

                // get age
                System.out.println("Please enter your age: ");
                age = scanner.next();

                simpleJDBC instance3 = new simpleJDBC();
                instance3.createUser(username, password, age, location);
                instance3.createSteamWallet(username);
                break;
            case 5:
                System.out.println("You selected Option 5");
                int gameID;
                String usern;
                System.out.println("Please enter the game ID:");
                gameID = scanner.next();
                System.out.println("Please enter your username:");
                usern = scanner.next();

                simpleJDBC instance5 = new simpleJDBC();
                instance5.ageRating(con, friend, max_price);
                break;
            case 6:
                System.out.println("Exiting...");
                con.close();
                return;

            default:
                System.out.println("Invalid option");
                break;
          }
        scanner.close();
        }
        

        // Creating a table
        try
        {
          String createSQL = "CREATE TABLE " + tableName + " (id INTEGER, name VARCHAR (25)) ";
          System.out.println (createSQL ) ;
          statement.executeUpdate (createSQL ) ;
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

        // Inserting Data into the table
        try
        {
          String insertSQL = "INSERT INTO " + tableName + " VALUES ( 1 , \'Vicki\' ) " ;
          System.out.println ( insertSQL ) ;
          statement.executeUpdate ( insertSQL ) ;
          System.out.println ( "DONE" ) ;

          insertSQL = "INSERT INTO " + tableName + " VALUES ( 2 , \'Vera\' ) " ;
          System.out.println ( insertSQL ) ;
          statement.executeUpdate ( insertSQL ) ;
          System.out.println ( "DONE" ) ;
          insertSQL = "INSERT INTO " + tableName + " VALUES ( 3 , \'Franca\' ) " ;
          System.out.println ( insertSQL ) ;
          statement.executeUpdate ( insertSQL ) ;
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

        // Querying a table
        try
        {
          String querySQL = "SELECT id, name from " + tableName + " WHERE NAME = \'Vicki\'";
          System.out.println (querySQL) ;
          java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;

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

      //Updating a table
      try
      {
        String updateSQL = "UPDATE " + tableName + " SET NAME = \'Mimi\' WHERE id = 3";
        System.out.println(updateSQL);
        statement.executeUpdate(updateSQL);
        System.out.println("DONE");

        // Dropping a table
        String dropSQL = "DROP TABLE " + tableName;
        System.out.println ( dropSQL ) ;
        statement.executeUpdate ( dropSQL ) ;
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

      // Finally but importantly close the statement and connection
      statement.close ( ) ;
      con.close ( ) ;
    }

    private void targetClient(Connection con, String dev, String location) {

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


        try (PreparedStatement statement = con.prepareStatement(sql)) {
        statement.setString(1, dev);
        statement.setString(2, location);

        try (ResultSet results = statement.executeQuery()) {
            boolean found = false;
            System.out.println("Target Audience for " + dev + " in " + location + ":");

            while (results.next()) {
                System.out.println("- " + results.getString("username"));
                found = true;
            }

            if (!found) {
                System.out.println("No target clients found for " + dev);
            }
        }
      } catch (SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
        e.printStackTrace();
      }
    }

      private void recommendGames(Connection con, String friend, String maxPrice) {
        String sql = "WITH FriendGames AS ( " +
        "SELECT f.main_name, f.friend_name, b.gameID, g.price" +
        "FROM Friend f " +
        "WHERE f.main_name = ?" +
        "JOIN Buys b ON f.friend_name = b.username " +
        "JOIN Game g ON b.gameID = g.gameID), " +
        "UserGames AS ( " +
        "SELECT username, gameID " +
        "FROM Buys) " +
        "SELECT fg.game_name, fg.main_name AS username " +
        "FROM FriendGames fg "  +
        "LEFT JOIN UserGames ug ON fg.main_name = ug.username AND fg.gameID = ug.gameID " +
        "WHERE ug.gameID IS NULL AND fg.price <= ? "
        ;

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, friend);
            statement.setDouble(2, Double.parseDouble(maxPrice));

            try (ResultSet results = statement.executeQuery()) {
                boolean found = false;
                System.out.println("Game Recommendations from " + friend + ":");

                while (results.next()) {
                    System.out.println("- " + results.getString("title") + " ($" + results.getDouble("price") + ")");
                    found = true;
                }

                if (!found) {
                    System.out.println("No game recommendations found from " + friend);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
      }
      private boolean checkUsername(Connection con, String username) {
        String sql = "IF (" + 
        "SELECT COUNT(*)" +
        "FROM User " +
        "WHERE username = ?) > 0" +
        "THEN " + 
        "SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Username already exists';" +
        "END IF"
        ;
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.execute();

            System.out.println("Username is available.");
            return true;
        }
        catch (SQLException e) {
          if ("45000".equals(e.getSQLState())) {
              System.out.println(e.getMessage());
              return false;
          }
          e.printStackTrace();
        }
      }

      private boolean checkPassword(Connection con, String password) {
        String sql = "IF (" + 
        "CHAR_LENGTH(?) < 8" +
        "THEN " +
        "SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Password needs to be at least 8 characters long';" +
        "END IF"
        ;
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, password);
            statement.execute();

            System.out.println("Password is set.");
            return true;
        }
        catch (SQLException e) {
          if ("45000".equals(e.getSQLState())) {
              System.out.println(e.getMessage());
              return false;
          }
          e.printStackTrace();
        }
      }

      private boolean checkLocation(Connection con, String location) {
        String sql = "IF (" + 
        "? NOT IN ('North American', 'South America', 'Europe', 'Asia', 'Africa', 'Oceania')" +
        "THEN " +
        "SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid location';" +
        "END IF"
        ;
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, password);
            statement.execute();

            System.out.println("Location is set.");
            return true;
        }
        catch (SQLException e) {
          if ("45000".equals(e.getSQLState())) {
              System.out.println(e.getMessage());
              return false;
          }
          e.printStackTrace();
        }
      }

      private void createUser(String username, String password, int age, String locaiton) {
        String sql = "INSERT INTO User " +
        "(username, password, avatar, age, location) VALUES (?, ?, NULL, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setDouble(3, Integer.parseInt(age));
            statement.setString(4, location);

            try (ResultSet results = statement.executeQuery()) {
                System.out.println("User successfully created!");
            }
        }
        catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
      }

      private void createSteamWallet(String username) {
        String sql ="INSERT INTO SteamWallet (walletID, credit, username) " +
                 "VALUES ((SELECT COUNT(*) + 1 FROM SteamWallet), 0, ?)";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, username);
        }
        catch (SQLException e) {
            System.err.println("Error fetching count: " + e.getMessage());
        }

        String sql2 = "INSERT INTO SteamWallet " +
        "(walletID, credit, username) VALUES (?, 0, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setDouble(3, Integer.parseInt(age));
            statement.setString(4, location);

            try (ResultSet results = statement.executeQuery()) {
                System.out.println("SteamWallet successfully initialized!");
            }
        }
        catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
      }

      private void ageRating(Connection con, int gameID, String usrn) {
        String sql = "BEGIN " +
                 "IF NOT EXISTS ( " +
                 "SELECT 1 FROM User u, Game g " +
                 "WHERE u.username = ? AND g.gameID = ? AND u.age >= g.age_rating " +
                 ") THEN " +
                 "SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'You are too young to play'; " +
                 "END IF; " +
                 "END; ";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, usern);
            statement.setInt(2, gameID);
            statement.execute();

            System.out.println("You can play the game.");
        }
        catch (SQLException e) {
          if ("45000".equals(e.getSQLState())) {
              System.out.println(e.getMessage());
          }
          e.printStackTrace();
        }
      }
}
