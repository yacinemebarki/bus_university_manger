package Views;

import Controllers.ManagerController;
import Controllers.StudentController;
import Controllers.LoginController;
import Models.AllModels;

public class Main {
    public static void main(String[] args) {
        // new Controllers.LoginController(new Login_view());
        new ManagerController(new AllModels<>(), new Manager_view());
    }
}