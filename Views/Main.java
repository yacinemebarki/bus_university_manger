package Views;

import Controllers.DriverController;
import Controllers.LoginController;
import Controllers.ManagerController;
import Controllers.StudentController;
import Models.AllModels;

public class Main {
    public static void main(String[] args) {
        new LoginController(new Login_view());
    }
}