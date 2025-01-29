import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class WeatherApp extends JFrame implements ActionListener {

    private JLabel cityLabel, tempLabel ,descriptionLabel, feelsLikeLabel, HumidityLabel, nameLabel;
    private JTextField cityField;
    private JButton getTempButton;

    public WeatherApp() {
        setTitle("Weather App");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6,1,2,2));


        JPanel HeadingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,30));
        nameLabel = new JLabel(("Weather App"));
        nameLabel.setFont(new Font("Century Gothic",Font.BOLD, 60));
        HeadingPanel.add(nameLabel);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        cityLabel = new JLabel("Enter city/country name:");
        cityLabel.setFont(new Font("Century Gothic", Font.BOLD,30));
        cityField = new JTextField(10);
        cityField.setFont(new Font("Century Gothic", Font.PLAIN, 30));
        getTempButton = new JButton("Get Details");
        getTempButton.setFont(new Font("Century Gothic", Font.BOLD, 30));

        
        inputPanel.add(cityLabel);
        inputPanel.add(cityField);
        inputPanel.add(getTempButton);

        JPanel outputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        tempLabel = new JLabel();
        tempLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
        outputPanel.add(tempLabel);

        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10 ));
        descriptionLabel = new JLabel();
        descriptionLabel.setFont(new Font("Century Gothic", Font.BOLD,25));
        descriptionPanel.add(descriptionLabel);

        JPanel feelsLikePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40,10));
        feelsLikeLabel = new JLabel();
        feelsLikeLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
        feelsLikePanel.add(feelsLikeLabel);

        JPanel HumidityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
        HumidityLabel = new JLabel();
        HumidityLabel.setFont(new Font("Century Gothic", Font.BOLD,25));
        HumidityPanel.add(HumidityLabel);

        
        add(HeadingPanel);
        add(inputPanel);
        add(outputPanel);
        add(feelsLikePanel);
        add(HumidityPanel);
        add(descriptionPanel);

        
        getTempButton.addActionListener(this);

        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    public void actionPerformed(ActionEvent e) {
        
        String city = cityField.getText();
        
        try {
            
            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=dc41c702477dd1a8c5d5c918cabec124";

            
            URI uri = URI.create(apiUrl);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            
            double temperature = parseResponse(response.toString());
            String descriptoin = getdescription(response.toString());
            String Humidity = getHumidity(response.toString());
            Humidity = Humidity.replaceAll("}"," ");
            double feelsLike = feelsLike(response.toString());


            
            System.out.println("\n\n"+response.toString()+"\n\n");
            System.out.println("\n"+response.getClass()+"\n");


            
            tempLabel.setText(String.format("The temperature in %s is %.1f\t° Celsius.", city, temperature));
            descriptionLabel.setText(String.format("Description: %s",descriptoin));
            feelsLikeLabel.setText(String.format("Feels Like: %.1f\t° C",feelsLike));
            HumidityLabel.setText(("Humidity: "+ Humidity +"%"));



        } catch (Exception ex) {
            
            if(city.equals("")||city.equals(" "))
            {
                tempLabel.setText("City name can't be empty.");
                descriptionLabel.setText(" ");
                HumidityLabel.setText(" ");
                feelsLikeLabel.setText(" ");
            }
            
            else {
                tempLabel.setText("Please enter a valid city name.");
                descriptionLabel.setText(" ");
                HumidityLabel.setText(" ");
                feelsLikeLabel.setText(" ");
            }
        }
    }

    private double parseResponse(String response) {
        
        int index = response.indexOf("\"temp\":");
        int end = response.indexOf(",", index);
        String tempString = response.substring(index + 7, end);
        double temperature = Double.parseDouble(tempString) - 273.15;
        return temperature;
    }

    private double feelsLike(String response){
        int index = response.indexOf("\"feels_like\":");
        int end = response.indexOf(",",index);
        String tempString = response.substring(index + 13, end);
        double feelslike = Double.parseDouble(tempString) - 273.15;
        return feelslike;
    }

    private String getdescription(String response){
        int index = response.indexOf("\"description\":");
        int end = response.indexOf(",",index);
        String descriptionString = response.substring(index + 15,end-1);
        return descriptionString;
    }

    private String getHumidity(String response){
        int index = response.indexOf("\"humidity\":");
        int end = response.indexOf(",",index);
        String humidity = response.substring(index + 11,end);
        return humidity;
    }

    
    public static void main(String[] args) {
        
        new WeatherApp().setVisible(true);
}
}