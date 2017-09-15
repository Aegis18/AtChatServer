import java.sql.*;
import java.util.UUID;

public class DataBaseManager {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String tempPassword;
    private String tempEmailAddress;
    private String userInputedEmailAddress;
    private String getUserInputedPassword;
    private User user;
    ChatProtocol chat = new ChatProtocol();

    public DataBaseManager(){
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

    //returns String array, [0] email, [1] password, [3] UUID
    //If there is no user empty strings are returned
    private String[] collectData(String emailAddress){
        String[] userInfo = new String[2];
        try {
            String query = "SELECT * FROM Users WHERE email_address IN ('" + emailAddress +"')";
            resultSet = statement.executeQuery(query);
            resultSet.next();
            userInfo[0] = resultSet.getString("email_address");
            userInfo[1] = resultSet.getString("password");
        }catch (SQLException e) {
            //e.printStackTrace();
            userInfo[0] = "";
            userInfo[1] = "";
        }
        if(userInfo[0] == null){
            //System.out.println("No user");
        }
        return userInfo;
    }

    public User login(String tryEmail, String tryPassword){
        User user = null;
        if(checkForUser(tryEmail)) {
            if (verifyPassword(tryEmail, tryPassword)) {
                try {
                    user.setFirst_name(resultSet.getString("first_name"));
                    user.setLast_name(resultSet.getString("last_name"));
                    user.setGender(Integer.parseInt(resultSet.getString("gender")));
                    user.setID(UUID.fromString(resultSet.getString("UUID")));
                    user.setEmail_Address(resultSet.getString("email_address"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user.clone();
    }
    private boolean verifyPassword(String tryEmail, String tryPassword){
        String resultPassword = null;
        String[] userInfo = collectData(tryEmail);
        if(userInfo[1].equals(tryPassword))
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

    public String getTempPassword() {
        return tempPassword;
    }

    public String getTempEmailAddress() {
        return tempEmailAddress;
    }
}
