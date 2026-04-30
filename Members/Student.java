package Members;
public class Student extends person{
    String matricule;
    public Student(){
        super();
        this.matricule=null;
    }
    public Student(String matricule,String full_name,String password){
        super(full_name, "Student", password);
        this.matricule=matricule;
    } 
    public String getmatricule(){
        return matricule;
    }
    public void setmatricule(String matricule){
        this.matricule=matricule;
    }

}
