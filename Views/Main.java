package Views;

import Controllers.BusController;
import Controllers.LineDashboardController;
import Controllers.ManagerController;
import Controllers.ProblemDashboardController;
import Controllers.StudentController;
import Controllers.TripController;
import Controllers.LoginController;
import Models.AllModels;

public class Main {
    public static void main(String[] args) {

        new LineDashboardController(new Trip_view());
    }
}