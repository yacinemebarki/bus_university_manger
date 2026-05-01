package Views;

import Controllers.ManagerController;
import Controllers.StudentController;
import Models.AllModels;

public class Main {
    public static void main(String[] args) {

        new StudentController(new Student_view());
    }
}