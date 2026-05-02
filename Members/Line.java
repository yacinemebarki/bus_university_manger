package Members;

public class Line {

    private String code;
    private String name;
    private String startTime;
    private String startPoint;
    private String endPoint;
    private double distance;
    private int busCount;

    public Line() {}

    public Line(String code, String name, String startTime,
                String startPoint, String endPoint,
                double distance, int busCount) {

        this.code = code;
        this.name = name;
        this.startTime = startTime;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
        this.busCount = busCount;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public String getStartTime() { return startTime; }
    public String getStartPoint() { return startPoint; }
    public String getEndPoint() { return endPoint; }
    public double getDistance() { return distance; }
    public int getBusCount() { return busCount; }
}