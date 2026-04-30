public class person{
    private String matricule;
    private String first_name;
    private String last_name;    
    private String role;
    public person(String matricule,String last_name,String first_name,String role){
        this.matricule=matricule;
        this.last_name=last_name;
        this.first_name=first_name;
        this.role=role;
    }
    public String getmatricule(){
        return matricule;
    }
    public String getlastname(){
        return last_name;
    }
    public String getfirst_name(){
        return first_name;
    }
    public String role(){
        return role;
    }     
    
}