package Models;

import Members.Line;

public class LineModel {
    
    public static boolean validateLine(Line line) {
        return !line.getCode().isEmpty()
            && !line.getName().isEmpty()
            && !line.getDestination().isEmpty()
            && line.getDistance() > 0;
    }
}
