package data_base_charger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import DBConnections.LinesConnection;

public class InsertRandomLines {

    public static void main(String[] args) {

        String[] villes = {
            "Rouiba",
            "Ain Taya",
            "Dergana",
            "La Perouse",
            "Bab El Oued",
            "Tafourah",
            "Oued Fait",
            "Ain Naadja",
            "Cheraga",
            "Bab Ezzouar",
            "El Harrach",
            "Hussein Dey",
            "Kouba",
            "Draria",
            "Birkhadem"
        };

        String[] statusList = {
            "On Time",
            "Delayed",
            "Departed",
            "Arriving"
        };

        Random rand = new Random();

        String sql =
        "INSERT INTO transport_lines(line, matricule, hour, direction, status) " +
        "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = LinesConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            for(int i = 0; i < 20; i++) {

                String line = villes[rand.nextInt(villes.length)];
                String direction = villes[rand.nextInt(villes.length)];
                String hour = randomHour(rand);
                String status = statusList[rand.nextInt(statusList.length)];
                String matricule = "BUS-" + (1000 + i);

                ps.setString(1, line);
                ps.setString(2, matricule);
                ps.setString(3, hour);
                ps.setString(4, direction);
                ps.setString(5, status);

                ps.executeUpdate();

                System.out.println("Inserted: " + matricule);
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // generate random hour
    public static String randomHour(Random rand) {

        int h = rand.nextInt(24);
        int m = rand.nextInt(60);

        return String.format("%02d:%02d", h, m);
    }
}
