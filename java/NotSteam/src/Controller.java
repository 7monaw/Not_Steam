package NotSteam;

import java.sql.*;

public class Controller {
    @FXML private TextArea resultArea;
    @FXML private TextField searchArea_dev;
    @FXML private TextField searchArea_location;
    private Connection dbConnection;

    public void setConnection(Connection connection) {
        this.dbConnection = connection;
    }

    // Query 3 in PD2 with Developer and Location as Input
    /*@FXML
    private void targetClient() {
        String dev = searchArea_dev.getText().trim();
        String location = searchArea_location.getText().trim();
        
        if (dev.isEmpty() || location.isEmpty()) {
            resultArea.setText("Please first enter the developer name and then enter the desired location:");
            return;
        }

        String sql = """
            SELECT DISTINCT u.username
            FROM User u
            JOIN InterestedIn ii ON u.username = ii.username
            JOIN Associates a   ON ii.genre_name = a.genre_name
            JOIN Releases r     ON a.gameID = r.gameID
            JOIN Game g         ON g.gameID = r.gameID
            JOIN Available_In ai ON ai.gameID = g.gameID
            WHERE r.dev_name = ?
            AND ai.loc_name = ?
            AND u.age >= 
                CASE g.age_rating
                    WHEN 'T' THEN 13
                    WHEN 'M' THEN 17
                    ELSE 0
                END
            """;

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

    // Quit the application
    @FXML
    private void quit() {
        Platform.exit();
        System.exit(0);
        
    }*/
}
