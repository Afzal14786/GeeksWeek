package CricketScoreBoard.Main;
import java.sql.*;
import java.util.Scanner;

public class jdbcConnection {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        // 1. Load and register the main driver
            // name of sql driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. Establish the connection with the database
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket", "root", "Afzal#14777");
        // 3. Create the statement object
        Statement statement = con.createStatement();

        boolean flag = true;
        while (flag) {
            System.out.println("1. View the score board");
            System.out.println("2. Insert the new record");
            System.out.println("3. Update the record");
            System.out.println("4. Delete the record");
            System.out.println("5. Exit");
            System.out.println("-----------------------------------------------------------");

            System.out.println("Enter the choice : ");
            int choice = scn.nextInt();

            switch (choice) {
                // view the scoreboard
                case 1 :
                    // view the score
                    viewTable(statement);
                    break;

                case 2 :
                    // insert new records in score table
                    insertTable(statement, scn);
                    break;
                case 3 :
                    // update on score table
                    updateTable(statement, scn);
                    break;
                // delete from the database
                case 4 :
                    deleteScoreTable(statement, scn);
                    break;
                default :
                    flag = false;
                    break;
            }
        }

    }
    public static void viewTable(Statement statement) throws Exception{
        String sql = "select * from SCORETABLE;";
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("ID\t| NAME\t| RUNS\t| BALLS\t");
        while (resultSet.next())
            System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3) + "\t" + resultSet.getInt(4));
        System.out.println("-----------------------------------------------------------");
    }

    public static void insertTable(Statement statement, Scanner scn) throws Exception {
        // Insert data into score table
        System.out.println("Enter the ID : ");
        int id = scn.nextInt();
        System.out.println("Enter the name of the Player : ");
        String playerName = scn.next();
        System.out.println("Enter the Run Scored : ");
        int runs = scn.nextInt();
        System.out.println("In how many balls ??? ");
        int balls = scn.nextInt();

        String insert = "INSERT INTO SCORETABLE VALUES('"+id+"', '"+playerName+"', '"+runs+"', '"+balls+"');";
        int rows = statement.executeUpdate(insert);
        System.out.println(rows + " rows inserted");
        System.out.println("-----------------------------------------------------------");
    }

    public static void updateTable(Statement statement, Scanner scn) throws Exception {
        // Update the runs and balls where id = 1
        System.out.println("Enter the ID of the : ");
        int updatedId = scn.nextInt();
        System.out.println("Enter the new runs : ");
        int updatedRuns = scn.nextInt();
        System.out.println("In how many balls ??? ");
        int updatedBalls = scn.nextInt();

        String update = "UPDATE SCORETABLE set runs = '"+updatedRuns+"', balls='"+updatedBalls+"' where id = '"+updatedId+"';";
        int updatedRows = statement.executeUpdate(update);
        System.out.println(updatedRows + " Rows Update");
        System.out.println("-----------------------------------------------------------");
    }

    public static void deleteScoreTable(Statement statement, Scanner scn) throws Exception{
        // delete scoretable where id = 1
        System.out.println("Enter the ID : ");
        int delId = scn.nextInt();

        String delQuery = "DELETE FROM SCORETABLE WHERE ID = '"+delId+"';";
        int delRows = statement.executeUpdate(delQuery);
        System.out.println(delRows + " Row Deleted");
        System.out.println("-----------------------------------------------------------");
    }
}
