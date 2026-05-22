package Views;

import Controllers.BusController;
import Controllers.DriverController;
import Controllers.LineDashboardController;
import Controllers.ManagerController;
import Controllers.ProblemDashboardController;
import Controllers.StudentController;
import Controllers.TripController;
import Controllers.LoginController;
import Models.AllModels;

public class Main {
    public static void main(String[] args) {

        new LoginController(new Login_view());
    }
}