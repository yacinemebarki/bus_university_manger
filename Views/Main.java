package Views;

import Controllers.ManagerController;
import Models.AllModels;

public class Main {
    public static void main(String[] args) {

        new ManagerController(new AllModels<>(), new Manager_view());
    }
}