package Members;
public class person{
    private String full_name;
        
    private String role;
    private String passwrod;
    public person(){
        this.full_name=null;
        this.passwrod=null;
        this.role=null;
    }

    public person(String full_name,String role,String password){
        this.passwrod=password;
        
        this.full_name=full_name;
        this.role=role;
    }
    
    public String getfull_name(){
        return full_name;
    }
    public String role(){
        return role;
    }   
    public void setfullname(String fname){
        this.full_name=fname;
    }  
    
    public void setrole(String role){
        this.role=role;
    }
    public String getpassword(){
        return passwrod;
    }
    public void setpassword(String password){
        this.passwrod=password;
    }
    
}