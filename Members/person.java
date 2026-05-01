package Members;

public class  Person {
    private String full_name;
    private String role;
    private String password;

    public Person() {}

    public Person(String full_name,String role,String password){
        this.full_name=full_name;
        this.role=role;
        this.password = password;
    }
    
    public String getfull_name(){
        return full_name;
    }
    public void setfullname(String fname){
        this.full_name=fname;
    }

    public String getrole(){
        return role;
    }
    public void setrole(String role){
        this.role=role;
    }

    public String getpassword(){
        return password;
    }
    public void setpassword(String password){
        this.password=password;
    }
    
}