package Controller;

import  Model.Model;
import View.Temperatures;
import View.Graph;

import javax.swing.*;
/**
 * The `Controller` class initializes and shows the I-Fridge application.
 * It creates an instance of the `Model` class and sets up the UI components.
 *
 * @author Group 4
 * @version 1.0
 */

public class Controller {
    /**
     * Initializes and shows the I-Fridge application.
     */

    public void initializeAndShowApp() {
        SwingUtilities.invokeLater(() -> {
            Model app = new Model();
            app.valuesInstance = new Temperatures(); // Initialiser valuesInstance ici
            app.graphFrame = new Graph();
            app.setVisible(true);
        });
    }}
