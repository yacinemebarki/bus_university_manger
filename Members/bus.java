package Members;

public class bus {

    String driverId;
    String matricule;
    String work_status;
    String problem_status;
    public bus(){

    }  
    public bus(String driverId,String matricule,String work_status,String problem_status){
        
        this.driverId=driverId;
        this.matricule=matricule;
        this.work_status=work_status;
        this.problem_status=problem_status;
    }
}
