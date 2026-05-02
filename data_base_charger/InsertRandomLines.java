package data_base_charger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.UUID;
import DBConnections.LinesConnection;

public class InsertRandomLines {

    public static void main(String[] args) {

        String[] villes = {
            "Rouiba","Ain Taya","Dergana","La Perouse",
            "Bab El Oued","Tafourah","Oued Fait","Ain Naadja",
            "Cheraga","Bab Ezzouar","El Harrach",
            "Hussein Dey","Kouba","Draria","Birkhadem"
        };

        String[] statusList = {
            "On Time","Delayed","Departed","Arriving"
        };

        Random rand = new Random();

        String sql =
        "INSERT INTO transport_lines(matricule, status, line, hour, direction) " +
        "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = LinesConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            for(int i = 0; i < 50; i++) {

                String city = villes[rand.nextInt(villes.length)];

                boolean goFromUSTHB = rand.nextBoolean();

                String line;
                String direction;

                if(goFromUSTHB){
                    line = "USTHB";
                    direction = city;
                } else {
                    line = city;
                    direction = "USTHB";
                }

                String hour = randomHour(rand);
                String status = statusList[rand.nextInt(statusList.length)];
                String matricule = "BUS-" + (1000 + i);

                // unique id
                

                
                ps.setString(1, matricule);
                ps.setString(2, status);
                ps.setString(3, line);
                ps.setString(4, hour);
                ps.setString(5, direction);

                ps.executeUpdate();

                System.out.println("Inserted: " + line + " -> " + direction);
            }

            con.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String randomHour(Random rand){

        int start = 6 * 60 + 30;
        int end = 18 * 60;

        int minutes = start + rand.nextInt(end - start);

        int h = minutes / 60;
        int m = minutes % 60;

        return String.format("%02d:%02d", h, m);
    }
}