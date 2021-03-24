//Shayling Zhao
//NetID: SXZ190015

//imported libraries
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class API_Proj {

    private static HttpURLConnection connection;

    public static void main(String[] args) throws IOException, IrcException {

        // Now start our bot up.
        MyBot bot = new MyBot();

        // Enable debugging output.
        bot.setVerbose(true);

        // Connect to the IRC server.
        bot.connect("irc.freenode.net");

        // Join the #pircbot channel.
        bot.joinChannel("#ShayBot");

    }
    //API for the weather bot
    public static String API(String city, String state, String country) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        //WeatherBot
        try {
            //Passing in as new URL, base URL, method parameters, conversion to imperial units code, API key.
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + state + ","
                    + country + "&units=imperial&appid=" + "5edbec5f242b2f46ecead284e2b4be85");
            //START OF COPY AND PASTED CODE
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return responseContent.toString();
        //END OF COPY AND PASTED CODE
    }

    //API for the weather bot that only takes in 2 parameters
    public static String API2(String city2, String country2) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        //WeatherBot
        try {
            //Passing in as new URL, base URL, method parameters, conversion to imperial units code, API key.
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city2 + ","
                    + country2 + "&units=imperial&appid=" + "5edbec5f242b2f46ecead284e2b4be85");
            //START OF COPY AND PASTED CODE
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return responseContent.toString();
        //END OF COPY AND PASTED CODE
    }

    //API for the dictionary bot
    public static String API3(String word) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        //DictionaryBot
        try {
            String id = "5b29465a";
            String APIKey = "6b3277cfb134cb73b9eeeef264d9d8d9";
            //Passing in as new URL, the base URL, language code, and method parameter
            URL url2 = new URL("https://od-api.oxforddictionaries.com/api/v2/entries/" + "en" + "/"+word);

            //START OF COPY AND PASTED CODE
            connection = (HttpURLConnection) url2.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            //Passing in the json, app id, and app key to get access to URL
            connection.setRequestProperty("Accept","application/json");
            connection.setRequestProperty("app_id", id);
            connection.setRequestProperty("app_key", APIKey);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return responseContent.toString();
        //END OF COPY AND PASTED CODE
    }
}

//Bot class
class MyBot extends PircBot {

    public MyBot() {
        this.setName("MyAPIBot");
    }

    public void onMessage(String channel, String sender,
                          String login, String hostname, String message) {

        //gets current time
        if (message.equalsIgnoreCase("time")) {
                String time = new java.util.Date().toString();
                sendMessage(channel, sender + ": The time is now " + time);
        }
        //Gives user instructions when they need help on the weather bot
        if (message.equalsIgnoreCase("weather help")) {
            sendMessage(channel,"When getting weather information, please type in the word 'iweather' " +
                    "followed by a space, then the city name, state name/code, and country name/code with " +
                    "commas in between. Or you can type in the word 'iweather' followed by a space, then " +
                    "city name, and state name. Or you can type in the word 'iweather' followed by a space, then " +
                    "city name, and country name/code.");
        }
        //prints out weather info into chatbot when they type 'weather' followed by location info.
        if (message.toLowerCase().contains("iweather")) {
            String city2, country2, weather;
            //Setting weather to equal everything up to space
            weather = message.substring(0, message.indexOf(' '));
            //Setting message to everything after "weather "
            message = message.substring(weather.length()+1);
            //Setting city to equal everything up to comma
            city2 = message.substring(0, message.indexOf(','));
            //Setting new message to everything after "weather city, "
            message = message.substring(city2.length()+2);
            //Setting country to equal the rest of the message
            country2 = message;
            //Calling API method
            sendMessage(channel, parseWeat(API_Proj.API2(city2, country2)));
        }
        else if (message.toLowerCase().contains("weather")) {
            String state, city, country, weather;
            //Setting weather to equal everything up to space
            weather = message.substring(0, message.indexOf(' '));
            //Setting message to everything after "weather "
            message = message.substring(weather.length()+1);
            //Setting city to equal everything up to comma
            city = message.substring(0, message.indexOf(','));
            //Setting new message to everything after "weather city, "
            message = message.substring(city.length()+2);
            //Setting state to equal everything up to comma
            state = message.substring(0, message.indexOf(','));
            //Setting new message to everything after "weather city, state, "
            message = message.substring(state.length()+2);
            //Setting country to equal the rest of the string after "weather city, state, "
            country = message;
            //Calling API method
            sendMessage(channel, parseWeat(API_Proj.API(city, state, country)));
        }

        //Gives user instructions when they need help on dictionary bot
        if (message.equalsIgnoreCase("dictionary help")) {
            sendMessage(channel,"Please type in 'search' followed by a space and the word you'd like to look up.");
        }
        //prints out word info into chatbot when user type 'search' followed by the word they want to search.
        if (message.toLowerCase().contains("search")) {
            /*Calls API method and ignores first 7 characters "search " and printing out the statement returned from
            the "parse" method*/
            sendMessage(channel, parseDict(API_Proj.API3(message.substring(7)))); //Ignore the first 7 characters
        }
    }
    //parsing JSON object for weather data
    public static String parseWeat(String URL) {
        JSONObject jObj = new JSONObject(URL);
        double temperature, feelsLike, tempMin, tempMax, humidity, description;
        temperature = jObj.getJSONObject("main").getDouble("temp");
        feelsLike = jObj.getJSONObject("main").getDouble("feels_like");
        tempMin = jObj.getJSONObject("main").getDouble("temp_min");
        tempMax = jObj.getJSONObject("main").getDouble("temp_max");
        humidity = jObj.getJSONObject("main").getDouble("humidity");
        //Returning doubles as strings
        return "The temperature is " + temperature + " degrees F" + " and feels like " + feelsLike + " degrees F." +
                " The minimum temperature is " + tempMin + " degrees F and the maximum temperature is " + tempMax +
                " degrees F. " + "The humidity level is " + humidity + " percent.";
    }
    //parsing JSON object for dictionary data
    public static String parseDict(String URL2) {
        JSONObject jObj = new JSONObject(URL2);
        String definition, etymology, example;

        String results = jObj.getJSONArray("results").getJSONObject(0).toString();
        String lexicalEntries = new JSONObject(results).getJSONArray("lexicalEntries").getJSONObject(0).toString();
        String entries = new JSONObject(lexicalEntries).getJSONArray("entries").getJSONObject(0).toString();
        String senses = new JSONObject(entries).getJSONArray("senses").getJSONObject(0).toString();
        definition = new JSONObject(senses).getJSONArray("definitions").get(0).toString();

        etymology = new JSONObject(entries).getJSONArray("etymologies").get(0).toString();

        String examples = new JSONObject(senses).getJSONArray("examples").get(0).toString();
        example = new JSONObject(examples).getString("text");

        return "DEFINITION: " + definition + ". ETYMOLOGY: " + etymology + ". EXAMPLE: " + example + ".";
    }
}