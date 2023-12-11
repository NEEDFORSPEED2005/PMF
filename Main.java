package GROUPE4;

import Controller.Controller;
/**
 * The `Main` class contains the main method to start the I-Fridge application.
 * It creates an instance of the `Controller` class and initializes the application.
 *
 * @author Group 4
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.initializeAndShowApp();
    }
}
