package Members;

public class Manager extends Person{

    private String code;

    public Manager() {}

    public Manager(String first_name, String last_name, String password, String code) {
        super(first_name, last_name, password);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}