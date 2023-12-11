
/**
 * The `Model` class represents the main application model for the I-Fridge application.
 * It manages the data, user interface components, and communication with the Arduino device.
 *
 * @author Group 4
 * @version 1.0
 */

package Model;

import View.Graph;
import View.Temperatures;
import com.fazecast.jSerialComm.SerialPort;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * The `Model` class extends `JFrame` and serves as the main application model.
 */

public class Model extends JFrame {
    // UI Components
    private List<Float> humidityValues = new ArrayList<>();
    private List<Float> temperatureintvalues = new ArrayList<>();
    private List<Float> temperatureextValues = new ArrayList<>();
    private List<Float> pointValues = new ArrayList<>();
    JComboBox<String> portComboBox;
    private JButton refreshButton;
    public Temperatures valuesInstance;
    private JButton connectButton;
    private JTextField temperatureField;
    private JLabel sentValueLabel;
    private JButton Graph;
    private JButton Parameters;
    private SerialPort selectedPort;
    private JLabel humidityLabel;
    private JLabel indoorTemperatureLabel;
    private JLabel outdoorTemperatureLabel;
    private JLabel dewPointLabel;
    public Graph graphFrame;
    private boolean isAlertShown = false;
    private boolean eleve = false;
    private float humidityValue = 0;
    private float temperatureint=0;
    private float teemperatureext = 0;
    private float point=0;

    /**
     * Constructs a new `Model` instance, initializing the UI and setting up event listeners.
     */
    public Model() {
        setTitle("I-Fridge");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.lightGray);
        Image logoImage = new ImageIcon("src/main/java/Model/frigo.png").getImage();
        setIconImage(logoImage);

        //JLabel welcomeLabel = new JLabel("WELCOME ON OUR APPLICATION");
        // welcomeLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        //welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        //welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        //mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.white);

        JPanel portPanel = new JPanel(new GridBagLayout());
        portPanel.setBackground(Color.white);

        JLabel selectPortLabel = new JLabel("Select a port");
        // selectPortLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        portComboBox = new JComboBox<>();
        portComboBox.setPreferredSize(new Dimension(200, 40));
        refreshButton = new JButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(100, 40));
        connectButton = new JButton("Connect");
        connectButton.setPreferredSize(new Dimension(100, 40));
        // Ajouter un label pour afficher les valeurs envoyées
        sentValueLabel = new JLabel("Consigne: ");
        Graph = new JButton("Graph");
        Graph.setPreferredSize(new Dimension(100, 40));
        Parameters = new JButton("Temperatures");
        Parameters.setPreferredSize(new Dimension(100, 40));



        // sentValueLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        // Ajouter un champ de texte pour la température
        temperatureField = new JTextField(10);
        temperatureField.setPreferredSize(new Dimension(100,40));


        GridBagConstraints gbcSelectPortLabel = new GridBagConstraints();
        gbcSelectPortLabel.gridx = 0;
        gbcSelectPortLabel.gridy = 0;
        gbcSelectPortLabel.weighty = 1;
        gbcSelectPortLabel.anchor = GridBagConstraints.EAST;
        gbcSelectPortLabel.insets = new Insets(0, 0, 10, 10);
        portPanel.add(selectPortLabel, gbcSelectPortLabel);

        GridBagConstraints gbcPortComboBox = new GridBagConstraints();
        gbcPortComboBox.gridx = 1;
        gbcPortComboBox.gridy = 0;
        gbcPortComboBox.weighty = 1;
        gbcPortComboBox.anchor = GridBagConstraints.WEST;
        gbcPortComboBox.insets = new Insets(0, 0, 10, 10);
        portPanel.add(portComboBox, gbcPortComboBox);

        GridBagConstraints gbcRefreshButton = new GridBagConstraints();
        gbcRefreshButton.gridx = 2;
        gbcRefreshButton.gridy = 0;
        gbcRefreshButton.anchor = GridBagConstraints.EAST;
        gbcRefreshButton.insets = new Insets(0, 0, 10, 10);
        portPanel.add(refreshButton, gbcRefreshButton);

        GridBagConstraints gbcConnectButton = new GridBagConstraints();
        gbcConnectButton.gridx = 3;
        gbcConnectButton.gridy = 0;
        gbcConnectButton.anchor = GridBagConstraints.WEST;
        gbcConnectButton.insets = new Insets(0, 0, 10, 10);
        portPanel.add(connectButton, gbcConnectButton);

        GridBagConstraints gbcSentValueLabel = new GridBagConstraints();
        gbcSentValueLabel.gridx = 0;
        gbcSentValueLabel.gridy = 1;
        gbcSentValueLabel.anchor = GridBagConstraints.EAST;
        gbcSentValueLabel.insets = new Insets(0, 0, 10, 10);
        portPanel.add(sentValueLabel, gbcSentValueLabel);

        GridBagConstraints gbcTemperatureField = new GridBagConstraints();
        gbcTemperatureField .gridx = 1;
        gbcTemperatureField .gridy = 2;
        gbcTemperatureField .anchor = GridBagConstraints.WEST;
        gbcTemperatureField .insets = new Insets(0, 0, 10, 10);
        portPanel.add(temperatureField, gbcTemperatureField );

        GridBagConstraints gbcPortPanel = new GridBagConstraints();
        gbcPortPanel.gridx = 0;
        gbcPortPanel.gridy = 0;
        gbcPortPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPortPanel.weightx = 1.0;
        gbcPortPanel.insets = new Insets(0, 0, 10, 10);
        contentPanel.add(portPanel, gbcPortPanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        ImageIcon leftImageIcon = new ImageIcon("src/main/java/Model/");
        JLabel leftImageLabel = new JLabel(leftImageIcon);

        ImageIcon leftdownImageIcon = new ImageIcon("src/main/java/Model/");
        JLabel leftdownImageLabel = new JLabel(leftdownImageIcon);

        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setBackground(Color.lightGray);

        GridBagConstraints gbcLeftImageLabel = new GridBagConstraints();
        gbcLeftImageLabel.gridx = 0;
        gbcLeftImageLabel.gridy = 0;
        gbcLeftImageLabel.anchor = GridBagConstraints.NORTHWEST;
        gbcLeftImageLabel.weighty = 1;
        imagePanel.add(leftImageLabel, gbcLeftImageLabel);

        GridBagConstraints gbcLeftdownImageLabel = new GridBagConstraints();
        gbcLeftdownImageLabel.gridx = 0;
        gbcLeftdownImageLabel.gridy = 1;
        gbcLeftdownImageLabel.anchor = GridBagConstraints.NORTHWEST;
        gbcLeftdownImageLabel.weighty = 1;
        imagePanel.add(leftdownImageLabel, gbcLeftdownImageLabel);

        mainPanel.add(imagePanel, BorderLayout.WEST);
        GridBagConstraints gbcParameters = new GridBagConstraints();
        gbcParameters.gridx = 6;
        gbcParameters.gridy = 6;
        gbcParameters.anchor = GridBagConstraints.WEST;
        gbcParameters.insets = new Insets(0, 0, 10, 10);
        portPanel.add(Parameters, gbcParameters);
        GridBagConstraints gbcGraph = new GridBagConstraints();
        gbcGraph.gridx = 6;
        gbcGraph.gridy = 8;
        gbcGraph.anchor = GridBagConstraints.WEST;
        gbcGraph.insets = new Insets(0, 0, 10, 10);
        portPanel.add(Graph, gbcGraph);


        add(mainPanel);


        refreshButton.addActionListener(e -> refreshPortList());


        connectButton.addActionListener(e -> connectToArduino());
        Graph.addActionListener(e -> {
            graphFrame = new Graph(); // Utilisez la variable de classe graphFrame
            graphFrame.updateChartData(humidityValue, temperatureint, teemperatureext, point);
            graphFrame.setVisible(true);

            //ChartPanel chartPanel = new ChartPanel(View.Graph.get);
            //add(chartPanel);

        });
        Parameters.addActionListener(e -> {
            valuesInstance = new Temperatures();
            valuesInstance.updateChartData(humidityValue, temperatureint, teemperatureext, point);
            valuesInstance.setVisible(true);
        });

        Model.SerialReader serialReader = new Model.SerialReader();
        Thread readerThread = new Thread(serialReader);
        readerThread.start();
        humidityLabel = new JLabel("Humidité intérieure: ");
        indoorTemperatureLabel = new JLabel("Température intérieure: ");
        outdoorTemperatureLabel = new JLabel("Température extérieure: ");
        dewPointLabel = new JLabel("Point de rosée: ");

        JPanel dataPanel = new JPanel(new GridLayout(4, 2));
        dataPanel.add(humidityLabel);
        dataPanel.add(indoorTemperatureLabel);
        dataPanel.add(outdoorTemperatureLabel);
        dataPanel.add(dewPointLabel);


    }
    /**
     * Refreshes the list of available serial ports in the portComboBox.
     */

    void refreshPortList() {
        portComboBox.removeAllItems();
        SerialPort[] ports = SerialPort.getCommPorts();
        for (SerialPort port : ports) {
            portComboBox.addItem(port.getSystemPortName());
        }
    }
    /**
     * Establishes a connection to the Arduino device based on the selected port and sends the temperature setpoint.
     */

    private void connectToArduino() {
        String selectedPortName = (String) portComboBox.getSelectedItem();
        selectedPort = SerialPort.getCommPort(selectedPortName);

        if (!temperatureField.getText().isEmpty()) {
            try {
                double temperatureValue = Double.parseDouble(temperatureField.getText());

                selectedPort.setComPortParameters(9600, 8, 1, 0);
                selectedPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

                if (selectedPort.openPort()) {
                    JOptionPane.showMessageDialog(this, "Connexion établie avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    // Send the temperature setpoint to Arduino
                    try (OutputStream outputStream = selectedPort.getOutputStream();
                         OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
                        String temperatureString = String.valueOf(temperatureValue);
                        writer.write(temperatureString);
                        writer.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Impossible de se connecter au port série.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer une valeur numérique valide pour la température.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer une valeur pour la température.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        if (selectedPort.openPort()) {
            JOptionPane.showMessageDialog(this, "Connexion établie avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            Model.SerialReader serialReader = new Model.SerialReader();
            new Thread(serialReader).start();
        } else {
            JOptionPane.showMessageDialog(this, "Impossible de se connecter au port série.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
    }
    /**
     * A private class that implements the `Runnable` interface to read data from the Arduino device.
     */
    private class SerialReader implements Runnable {
        @Override
        public void run() {
            if (selectedPort == null) {
                //System.out.println("Le port sélectionné n'est pas initialisé.");
                return;
            }

            try (InputStream inputStream = selectedPort.getInputStream();
                 Scanner scanner = new Scanner(inputStream)) {

                while (scanner.hasNextLine()) {
                    String receivedData = scanner.nextLine();
                    SwingUtilities.invokeLater(() -> processArduinoData(receivedData));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Displays an alert message to the user.
     *
     * @param message The message to be displayed in the alert.
     */
    void showAlert(String message) {
        SwingUtilities.invokeLater(() -> {
            int result = JOptionPane.showConfirmDialog(this, message, "Alerte", JOptionPane.DEFAULT_OPTION);

            //if OK is pressed the application is closed
            if(result == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });
    }
    /**
     * Displays a high-temperature alert message to the user.
     *
     * @param message The message to be displayed in the high-temperature alert.
     */

    private void high(String message) {
        SwingUtilities.invokeLater(() -> {
            int result = JOptionPane.showConfirmDialog(this, message, "Alerte", JOptionPane.DEFAULT_OPTION);

            //if OK is pressed the application is closed
            if(result == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });
    }
    /**
     * Processes the data received from the Arduino device, updates UI components, and triggers alerts if necessary.
     *
     * @param data The data received from the Arduino device.
     */


    private void processArduinoData(String data) {
        String[] values = data.split(",");

        float temperatureValue = (float) Double.parseDouble(temperatureField.getText());
        if (values.length >= 2) {
            try {
                humidityValue = Float.parseFloat(values[0]);
                temperatureint = Float.parseFloat(values[1]);
                teemperatureext = Float.parseFloat(values[2]);
                point = Float.parseFloat(values[3]);

                humidityValues.add(humidityValue);
                System.out.println(humidityValues);
                temperatureintvalues.add(temperatureint);
                System.out.println(temperatureintvalues);
                temperatureextValues.add(teemperatureext);
                System.out.println(temperatureextValues);
                pointValues.add(point);
                System.out.println(pointValues);

                valuesInstance.updateHumidityLabel(String.valueOf(humidityValue));
                valuesInstance.updateIndoorTemperatureLabel(String.valueOf(temperatureint));
                valuesInstance.updateOutdoorTemperatureLabel(String.valueOf(teemperatureext));
                valuesInstance.updateDewPointLabel(String.valueOf(point));

                graphFrame.updateChartData(humidityValue, temperatureint, teemperatureext, point);

            } catch (NumberFormatException e) {
                System.err.println("Erreur de conversion de la valeur : " + data);
            }
        }

        valuesInstance.updateChartData(humidityValue, temperatureint, teemperatureext, point);

        if (temperatureint <= point && !isAlertShown) {
            showAlert("RISQUE DE CONDENSATION !");
            isAlertShown = true; // l'alerte a été affichée
        } else if (temperatureint > point) {
            isAlertShown = false;
        }

        if(temperatureint > 25 && !eleve) {
            high("REFRIGERATEUR OUVERT !");
            eleve = true; // l'alerte a été affichée
        } else if (temperatureint < 25) {
            eleve = false;
        }
    }}

