import java.sql.* ;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

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
          System.out.println("Option 1: Get users from the input location that are the target clients of the input game developer.");
          System.out.println("Option 2: Get the name and price of the game that your friends have purchased with desired maximum price.");
          System.out.println("Option 3: Creates a new user");
          System.out.println("Option 4: Modify the price of all games from a publisher based on the profit of the publisher compared to the average profit of all publishers.");
          System.out.println("Option 5: Check if user can play a certain game based on its rating.");
          System.out.println("Quit: quit the application.");
          System.out.println("Choose an option: 1: Option 1, 2: Option 2, 3: Option 3, 4: Option 4, 5: Option 5, 6: Quit");
          
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
                String username, password, location, yesno;
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
                age = scanner.nextInt();

                simpleJDBC instance3 = new simpleJDBC();
                boolean userCreated = instance3.createUser(con, username, password, age, location);
                if (userCreated) {
                  System.out.println("Do you want to initialize your steam wallet? Yes/No.");
                  yesno = scanner.next();
                  if (yesno.equals("Yes")) {
                    instance3.createSteamWallet(con, username);
                    System.out.println("Steam wallet created.");
                    break;
                  } else if (yesno.equals("No")) {
                    break;
                  } else {
                    System.out.println("You can only enter 'Yes' or 'No'. Steam wallet not initialized, please try again later.");
                    break;
                  }
                }
                break;

            case 4:
                System.out.println("You selected Option 4");
                String publisher;
                System.out.println("Please enter the publisher name:");
                publisher = scanner.next();
                
                simpleJDBC instance4 = new simpleJDBC();
                instance4.priceChange(con, publisher);
                break;

            case 5:
                System.out.println("You selected Option 5");
                String game_name, usern;
                System.out.println("Please enter your username:");
                usern = scanner.next();
                scanner.nextLine();
                System.out.println("Please enter the game name:");
                game_name = scanner.nextLine();

                simpleJDBC instance5 = new simpleJDBC();
                boolean canPlay = instance5.ageRating(con, game_name, usern);
                
                if (!canPlay) {
                  System.out.println("Do you wish to add one to your wishlist for the future? Y/N");
                  String answer = scanner.nextLine();
                  if (answer.equals("Y")) {
                    instance5.addToWishlist(con, usern, game_name);
                  }
                }
                
                break;

            case 6:
                System.out.println("Exiting...");
                con.close();
                return;

            default:
                System.out.println("Invalid option");
                scanner.close();
                break;
          }
        }
        
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
        "SELECT f.main_name, f.friend_name, b.gameID, g.game_name, g.price " +
        "FROM Friend f " +
        "JOIN Buys b ON f.friend_name = b.username " +
        "JOIN Game g ON b.gameID = g.gameID " +
        "WHERE f.main_name = ? ), " +
        "UserGames AS ( " +
        "SELECT username, gameID " +
        "FROM Buys) " +
        "SELECT fg.game_name, fg.price " +
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
                    System.out.println("- " + results.getString("game_name") + " ($" + results.getDouble("price") + ")");
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
        String sql = "SELECT COUNT(*) " +
        "FROM User u " +
        "WHERE u.username = ? ";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
          statement.setString(1, username);

          try (ResultSet results = statement.executeQuery()) {
            if (results.next()) {
                int count = results.getInt(1);

                if (count != 0) {
                  System.out.println("Username already exists, please try again.");
                  return false;
                } else {
                  System.out.println("Username is available.");
                  return true;
                }
            }
            return true;
            } catch (SQLException e) {
                  System.out.println("SQL Error: " + e.getMessage());
                  e.printStackTrace();
                  return false;
            }
          }
        catch (SQLException e) {
          System.out.println("SQL Error: " + e.getMessage());
          e.printStackTrace();
          return false;
        }
      }
    

    private boolean checkPassword(Connection con, String password) {
          if (password.length() < 8) {
            System.out.println("Password needs to be at least 8 characters long. Please try again.");
            return false;
          } else {
            System.out.println("Password is set.");
            return true;
          }
      }
      

    private boolean checkLocation(Connection con, String location) {
        List<String> locations = Arrays.asList("North American", "South America", "Europe", "Asia", "Africa", "Oceania");
        if (locations.contains(location)) {
          System.out.println("Location is set.");
          return true;
        } else {
          System.out.println("Invalid location, please try again.");
          return false;
        }
      }

    private boolean createUser(Connection con, String username, String password, int age, String location) {
        String sql = "INSERT INTO User " +
        "(username, password, avatar, age, location) VALUES (?, ?, NULL, ?, ?) ";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, age);
            statement.setString(4, location);
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                  System.out.println("User successfully created!");
                  return true;
            }
            return false;
        }
        catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
      }

    private void createSteamWallet(Connection con, String username) {
        String sql ="INSERT INTO SteamWallet (walletID, credit, username) " +
                 "VALUES ((SELECT COUNT(*) + 1 FROM SteamWallet), 0, ?)";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Error fetching count: " + e.getMessage());
        }
      }

      private boolean ageRating(Connection con, String game_name, String usern) {
        String sql = "SELECT u.age, g.age_rating " +
        "FROM User u, Game g " +
        "WHERE u.username = ? AND g.game_name = ?";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
          statement.setString(1, usern);
          statement.setString(2, game_name);

          try (ResultSet results = statement.executeQuery()) {
            if (results.next()) {
                int age = results.getInt("age");
                String rating = results.getString("age_rating");

                int minAge = 0;
                if ("T".equals(rating)) minAge = 13;
                else if ("M".equals(rating)) minAge = 18;

                if (age >= minAge) {
                    System.out.println("Welcome to the game.");
                    return true;
                } else {
                    System.out.println("You can't play this game.");
                    return false;
                }
            } else {
                System.out.println("User or game not found.");
                return true;
            }
        }
      } catch (SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
        e.printStackTrace();
        return true;
        }
    }

      private void priceChange(Connection con, String publisher) {
        String updateSql = "UPDATE Game g " +
        "SET price = price + (0.1 * ( " +
        "SELECT (pp.total_profit - ap.avg_profit) " +
        "FROM ( " +
        "SELECT r.publisher_name, " +
        "CASE WHEN EXISTS ( " +
        "SELECT 1 FROM Releases r2 " +
        "JOIN Buys b2 ON b2.gameID = r2.gameID " +
        "WHERE r2.publisher_name = r.publisher_name ) " +
        "THEN SUM(g2.price) ELSE 0 " +
        "END AS total_profit " +
        "FROM Releases r " +
        "JOIN Game g2 ON g2.gameID = r.gameID " +
        "LEFT JOIN Buys b ON b.gameID = g2.gameID " +
        "GROUP BY r.publisher_name ) pp, " +
        "( SELECT AVG(total_profit) AS avg_profit FROM ( " +
        "SELECT CASE WHEN EXISTS ( " +
        "SELECT 1 FROM Releases r2 " +
        "JOIN Buys b2 ON b2.gameID = r2.gameID " +
        "WHERE r2.publisher_name = r.publisher_name ) " +
        "THEN SUM(g2.price) ELSE 0 " +
        "END AS total_profit " +
        "FROM Releases r " +
        "JOIN Game g2 ON g2.gameID = r.gameID " +
        "LEFT JOIN Buys b ON b.gameID = g2.gameID " +
        "GROUP BY r.publisher_name ) all_pp ) ap " +
        "WHERE pp.publisher_name = ? )) " +
        "WHERE gameID IN ( " +
        "SELECT gameID FROM Releases WHERE publisher_name = ?)";

        String selectSql = "SELECT g.game_name, g.price " +
        "FROM Game g " +
        "JOIN Releases r ON r.gameID = g.gameID " +
        "WHERE r.publisher_name = ?";

        try{
          try (PreparedStatement statement = con.prepareStatement(updateSql)) {
              statement.setString(1, publisher);
              statement.setString(2, publisher);
              statement.executeUpdate();
          }

          try (PreparedStatement select = con.prepareStatement(selectSql)) {
            select.setString(1, publisher);
            try (ResultSet results = select.executeQuery()) {
                boolean found = false;
                System.out.println("Updated prices for games from publisher " + publisher + ":");
                while (results.next()) {
                    System.out.println("- " + results.getString("game_name") + " ($" + results.getDouble("price") + ")");
                    found = true;
                }
                if (!found) {
                      System.out.println("No games found for publisher " + publisher);
                }
              }
            }
          } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
          }
        }

        private void addToWishlist(Connection con, String usern, String game_name) {
          String selectSql = "SELECT gameID FROM Game WHERE game_name = ? ";
          String insertSql = "INSERT INTO Wishes " +
          "(gameID, username) VALUES (?, ?)";

          try {
            int gameID;
            try (PreparedStatement statement = con.prepareStatement(selectSql)) {
              statement.setString(1, game_name);
              try (ResultSet results = statement.executeQuery()) {
                if (!results.next()) {
                  System.out.println("Game not found: " + game_name);
                  return;
                }
                gameID = results.getInt("gameID");
              }
            }

            try (PreparedStatement insert = con.prepareStatement(insertSql)) {
              insert.setInt(1, gameID);
              insert.setString(2, usern);
              insert.executeUpdate();
              System.out.println("Added " + game_name + " to wishlist for " + usern);
            }

            } catch (SQLException e) {
              System.out.println("SQL Error: " + e.getMessage());
              e.printStackTrace();
            }
        }
  }
      
