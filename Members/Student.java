package Members;
public class Student extends Person{
    private String matricule;

    public Student() {};
    
    public Student(String full_name,String password,String role,String matricule){
        super(full_name, role, password);
        this.matricule = matricule;
    }

    public String getmatricule(){
        return matricule;
    }
    public void setmatricule(String matricule){
        this.matricule=matricule;
    }

}
