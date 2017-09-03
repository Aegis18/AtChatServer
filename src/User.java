import java.util.UUID;

public class User implements Cloneable{
    private String first_name;
    private String last_name;
    private String email_Address;
    private int gender;
    private UUID ID;


    public User(){
    }


    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    public void setLast_name(String lasst_name){
        this.last_name = last_name;
    }

    public void setEmail_Address(String email_Address){
        this.email_Address = email_Address;
    }

    public void setGender(int gender){
        this.gender = gender;
    }

    public void setID(UUID UUID){
        ID = UUID;
    }

    @Override
    public User clone(){
        User user = new User();
        user.setEmail_Address(this.email_Address);
        user.setFirst_name(this.first_name);
        user.setLast_name(this.last_name);
        user.setGender(this.gender);
        user.setID(this.ID);
        return user;
    }

}
