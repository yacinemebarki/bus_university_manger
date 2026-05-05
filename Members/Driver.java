package Members;

public class Driver extends Person {
    private String full_name;
    private String code;
    private String password;
    private String busMatricule;

    public Driver() {
    }

    public Driver(String full_name, String code, String password) {
        this.full_name = full_name;
        this.code = code;
        this.password = password;
    }

    public String getfull_name() {
        return full_name;
    }

    public void setfullname(String full_name) {
        this.full_name = full_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getBusMatricule() {
        return busMatricule;
    }
    public void setBusMatricule(String busMatricule) {
        this.busMatricule = busMatricule;
    }
}
