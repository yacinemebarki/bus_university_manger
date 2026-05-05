package Models;

import Members.Manager;
import Members.Person;
import Members.Student;
import Members.Driver;
import Members.Line;

import java.sql.*;

public class AllModels<T extends Person> {

    public boolean validateMember(T member) {

        if (member instanceof Student s) {
            return !s.getfull_name().isEmpty()
                && !s.getmatricule().isEmpty()
                && !s.getpassword().isEmpty();
        }

        if (member instanceof Manager m) {
            return !m.getfull_name().isEmpty()
                && !m.getCode().isEmpty()
                && !m.getpassword().isEmpty();
        }

        if (member instanceof Driver d) {
            return !d.getfull_name().isEmpty()
                && !d.getCode().isEmpty()
                && !d.getpassword().isEmpty()
                && !d.getBusMatricule().isEmpty();
        }

        return false;
    }
}