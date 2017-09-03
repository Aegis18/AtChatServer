import java.sql.*;
import java.util.UUID;

public class Manager{
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String tempPassword;
    private String tempEmailAddress;
    private String userInputedEmailAddress;
    private String getUserInputedPassword;
    private User user;

    public Manager(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://kmphomes.com:3306/avi_AtChat","avi_Rem", "Remisbestwaifu18");
        statement = connection.createStatement();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void collectData(){
        try {
            String query = "SELECT * FROM Users WHERE email_address IN ('" + userInputedEmailAddress +"')";
            resultSet = statement.executeQuery(query);
            resultSet.next();
            tempEmailAddress = resultSet.getString("email_address");
            tempPassword = resultSet.getString("password");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User login(){
        User user = null;
        collectData();
        if(verifyPassword(tempPassword)){
            try {
                user.setFirst_name(resultSet.getString("first_name"));
                user.setLast_name(resultSet.getString("last_name"));
                user.setGender(Integer.parseInt(resultSet.getString("gender")));
                user.setID(UUID.fromString(resultSet.getString("UUID")));
                user.setEmail_Address(resultSet.getString("email_address"));
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return user.clone();
    }
    private boolean verifyPassword(String password){
        String resultPassword = null;
            resultPassword = tempPassword;
        if(resultPassword.equals(password))
            return true;
        else
            return false;
    }

    public boolean checkForUser(String emailAddress){
        boolean check = true;
        String query = "SELECT * FROM Users WHERE email_address IN ('" + emailAddress +"')";
        try{
            resultSet = statement.executeQuery(query);
            resultSet.next();
            resultSet.getString("email_address");
        }catch(SQLException e){
            check = false;
        }
        return check;
    }

    public User getUser(){
        return user.clone();
    }
}
