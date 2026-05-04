package Members;

public class Line {

    private String code;
    private String name;
    private String destination;
    private double distance;

    public Line() {}

    public Line(String code, String name, String destination, double distance) {
        this.code = code;
        this.name = name;
        this.destination = destination;
        this.distance = distance;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public String getDestination() { return destination; }
    public double getDistance() { return distance; }

    public void setCode(String code) {
        this.code = code;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public void setName(String name) {
        this.name = name;
    }
}