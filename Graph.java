package View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
/**
 * The `Graph` class represents the graphical display of real-time data using JFreeChart.
 * It extends `JFrame` and includes methods to update the chart with new data.
 *
 * @author Group 4
 * @version 1.0
 */

public class Graph extends JFrame {

    private XYSeries humiditySeries = new XYSeries("Humidité");
    private XYSeries indoorTemperatureSeries = new XYSeries("Température interne");
    private XYSeries outdoorTemperatureSeries = new XYSeries("Température externe");
    private XYSeries dewPointSeries = new XYSeries("Point de rosée");
    private XYSeries consigne = new XYSeries("Consigne");
    private JFreeChart chart;
    /**
     * Gets the JFreeChart instance used in the graph.
     *
     * @return The JFreeChart instance.
     */

    public JFreeChart getChart() {
        return chart;
    }
    /**
     * Sets the JFreeChart instance used in the graph.
     *
     * @param chart The JFreeChart instance to set.
     */

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }
    /**
     * Constructs a new `Graph` instance, setting up the chart and UI components.
     */

    public Graph() {
        setTitle("Graph Frame");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image logoImage = new ImageIcon("src/main/java/View/frigo.png").getImage();
        setIconImage(logoImage);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(humiditySeries);
        dataset.addSeries(indoorTemperatureSeries);
        dataset.addSeries(outdoorTemperatureSeries);
        dataset.addSeries(dewPointSeries);
        dataset.addSeries(consigne);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Temperature Variation Over Time",
                "Time",
                "Temperatures",
                dataset,  // Utilisez XYSeriesCollection ici
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        // Créez un panneau de graphique et ajoutez-le à l'interface utilisateur
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);

    }



    /**
     * Updates the chart data with the provided values for humidity, indoor temperature, outdoor temperature, and dew point.
     *
     * @param humidity            The humidity value to update.
     * @param indoorTemperature   The indoor temperature value to update.
     * @param outdoorTemperature  The outdoor temperature value to update.
     * @param dewPoint            The dew point value to update.
     */
    public void updateChartData(float humidity, float indoorTemperature, float outdoorTemperature, float dewPoint) {
        humiditySeries.addOrUpdate(System.currentTimeMillis(), humidity);
        indoorTemperatureSeries.addOrUpdate(System.currentTimeMillis(), indoorTemperature);
        outdoorTemperatureSeries.addOrUpdate(System.currentTimeMillis(), outdoorTemperature);
        dewPointSeries.addOrUpdate(System.currentTimeMillis(), dewPoint);
        //consigne.addOrUpdate(System.currentTimeMillis(), temperatureValue);


    }}

