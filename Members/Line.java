package Members;

public class Line {
    public String status;
    public String line;
    public String hour;
    public String direction;
    public String matricule;
    
    public Line(String matricule,String status,String line,String hour,String direction){
        this.line=line;
        this.hour=hour;
        this.status=status;
        this.matricule=matricule;
        this.direction=direction;
    }


}
