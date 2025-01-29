Weather App - Java
A simple Java-based Weather App that fetches weather details like temperature, humidity, description, and feels like temperature for a given city using the OpenWeatherMap API.

Features
Displays current temperature in Celsius.
Displays weather description.
Shows "feels like" temperature.
Shows humidity percentage.
Simple, intuitive user interface built with Java Swing.
Requirements
Java Development Kit (JDK) 8 or above.
Active internet connection to fetch data from the OpenWeatherMap API.
Setup
Clone the repository:

bash
Copy
git clone https://github.com/RajputAshwanii/Weather-app-Java.git
Navigate into the project folder:

bash
Copy
cd Weather-app-Java
Make sure you have JDK 8+ installed on your machine. If not, download it from the official Java website.

Compile and run the application:

bash
Copy
javac WeatherApp.java
java WeatherApp
API Key
This app uses the OpenWeatherMap API to get weather data. You need to get an API key to use it.

Sign up for an OpenWeatherMap account (if you don’t have one) here.

Once signed in, go to the API keys section and copy your API key.

Replace the appid parameter in the following URL in the WeatherApp.java file with your actual API key:

java
Copy
String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=YOUR_API_KEY";
Save the file and recompile the program.

Usage
Launch the application.
Enter a city name in the provided text field and click "Get Details."
The weather details including temperature, description, humidity, and feels like temperature will be displayed.
