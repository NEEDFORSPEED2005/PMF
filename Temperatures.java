package View;

import javax.swing.*;
import java.awt.*;
/**
 * The `Temperatures` class represents the temperature data display frame.
 * It extends `JFrame` and provides methods to update temperature labels with new data.
 *
 * @author Group 4
 * @version 1.0
 */

public class Temperatures extends JFrame {

    private JTextField humidityField;
    private JTextField indoorTemperatureField;
    private JTextField outdoorTemperatureField;
    private JTextField dewPointField;
    /**
     * Constructs a new `Temperatures` instance, setting up the UI components for temperature data display.
     */

    public Temperatures() {
        setTitle("Lecteur de Températures");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Définir la couleur de fond du frame
        getContentPane().setBackground(new Color(44, 62, 80)); // Bleu foncé

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(44, 62, 80)); // Bleu foncé

        JLabel welcomeLabel = new JLabel("LECTEUR DE TEMPERATURES");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.WHITE); // Texte en blanc
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel dataPanel = new JPanel(new GridLayout(4, 2));
        dataPanel.setBackground(new Color(236, 240, 241)); // Nuance de blanc

        JLabel humidityLabel = createLabel("Pourcentage d'humidité : ");
        humidityField = createTextField();
        dataPanel.add(humidityLabel);
        dataPanel.add(humidityField);

        JLabel indoorTemperatureLabel = createLabel("Température intérieure : ");
        indoorTemperatureField = createTextField();
        dataPanel.add(indoorTemperatureLabel);
        dataPanel.add(indoorTemperatureField);

        JLabel outdoorTemperatureLabel = createLabel("Température extérieure: ");
        outdoorTemperatureField = createTextField();
        dataPanel.add(outdoorTemperatureLabel);
        dataPanel.add(outdoorTemperatureField);

        JLabel dewPointLabel = createLabel("Point de rosée: ");
        dewPointField = createTextField();
        dataPanel.add(dewPointLabel);
        dataPanel.add(dewPointField);

        mainPanel.add(dataPanel, BorderLayout.CENTER);

        add(mainPanel);
        //setVisible(true);
    }

    private JLabel createLabel(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 20)); // Texte en gras
        label.setForeground(new Color(52, 73, 94)); // Texte en bleu foncé
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textField.setEditable(false); // Les champs de texte ne sont pas éditables par défaut
        return textField;
    }
    /**
     * Updates the humidity label with the provided value.
     *
     * @param value The humidity value to update.
     */

    public void updateHumidityLabel(String value) {
        humidityField.setText(value);
    }
    /**
     * Updates the indoor temperature label with the provided value.
     *
     * @param value The indoor temperature value to update.
     */

    public void updateIndoorTemperatureLabel(String value) {
        indoorTemperatureField.setText(value);
    }
    /**
     * Updates the outdoor temperature label with the provided value.
     *
     * @param value The outdoor temperature value to update.
     */


    public void updateOutdoorTemperatureLabel(String value) {
        outdoorTemperatureField.setText(value);
    }
    /**
     * Updates the dew point label with the provided value.
     *
     * @param value The dew point value to update.
     */

    public void updateDewPointLabel(String value) {
        dewPointField.setText(value);
    }

    //public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> {
            //Temperatures app = new Temperatures();
            //app.setVisible(true);
        //});
    //}

    public void updateChartData(float humidityValue, float temperatureint, float teemperatureext, float point) {
    }
}
