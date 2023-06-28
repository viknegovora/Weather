package com.example.weather.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.weather.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Third_Activity extends AppCompatActivity implements Runnable {
// https://api.openweathermap.org/data/2.5/weather?q=Krasnodar&appid=40603b445c6f4f66747d2e19e34136c6&units=metric&lang=ru
    // создание полей
    private TextView infoCity; // поле информации о населённом пункте
    private TextView infoTemperature; // поле информации о температуре
    private TextView infoDay; // поле информации о температуре утром и вечером
    private TextView Wind;

    private String weather;

    private SharedPreferences settings; // поле настроек приложения
    private final String APP_WEATHER = "Weather"; // константа названия настроек
    private final String CITY = "City"; // константа названия переменной города
    // дополнительные поля интернет соединения
    private final String URL_SERVER = "https://api.openweathermap.org/data/2.5/weather?q="; // url сервера
    private final String KEY = "&appid=40603b445c6f4f66747d2e19e34136c6"; // ключ доступа к сервисам сервера (получается при регистрации на https://openweathermap.org)
    private final String EXTRA_OPTIONS = "&units=metric&lang=ru"; // настройки поиска на русском языке
    private String title; // поле названия населённого пункта
    private String request; // url для запросов на сервер
    private String response; // ответ с сервера в виде JSON
    private HttpsURLConnection connection; // поле интернет соединения
    private Handler handler; // создание обработчика событий
    private JSONObject jsonObject; // создание поля JSON объекта
    private Intent intent; // поле намерения переключения активностей

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        // присваивание id полям
        infoCity = findViewById(R.id.infoCity);
        infoTemperature = findViewById(R.id.infoTemperature);
        infoDay = findViewById(R.id.infoDay);
        Wind = findViewById(R.id.Wind);

        // считывание данных из переданного намерения Intent
        Intent intent = getIntent();
        // запись этих данных на экран активности
        title = intent.getStringExtra("title");

//        // создание объекта работы с настройками приложения
//        settings = getSharedPreferences(APP_WEATHER, MODE_PRIVATE);
//        // считывание настроек выбранного города, данной переменной назначается NoCity если данной настройки нет
//        title = settings.getString(CITY, "NoCity");

        // вывод на экран информации о городе
        infoCity.setText(title);

        infoTemperature.setText("Данные обновляются ..."); // вывод данных до получения данных с сервера

        handler = new Handler(); // создание объекта обработчика сообщений
        new Thread(this).start(); // запуск фонового потока

    }

    // метод дополнительного потока для интернет соединения и определения погоды в заданном населённом пункте
    @Override
    public void run() {
        // определение url для запросов на сервер
        request = URL_SERVER + title + KEY + EXTRA_OPTIONS;
        // запрос на сервер
        try {
            URL url = new URL(request); // создание url ссылки для запроса на сервер
            connection = (HttpsURLConnection) url.openConnection(); // открытие соединения с сервером
            connection.connect(); // соединение с сервером

            InputStream stream = connection.getInputStream(); // Считывание данных из потока ответа сервера
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream)); // Запись данных и выделение памяти

            StringBuffer buffer = new StringBuffer(); // Запись данных и выделение памяти
            String line = ""; // По умолчанию пустая строка

            while ((line = reader.readLine()) != null) // Постройное считывание текста
                buffer.append(line).append("\n");
            response = buffer.toString(); // текстовый ответ с сервера

            jsonObject = new JSONObject(response); // создание JSON объекта по ответу с сервера

            // задание на обработчик сообщений обновление TextView с температурой
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // вывод данных с JSON файла
                    try {
                        weather = "";
                        weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                        weather = weather.substring(0, 1).toUpperCase() + weather.substring(1);
                        infoTemperature.setText(Math.round(jsonObject.getJSONObject("main").getDouble("temp")) + "");
                        infoDay.setText(Math.round(jsonObject.getJSONObject("main").getDouble("temp_max")) + " C / " + Math.round(jsonObject.getJSONObject("main").getDouble("temp_min")) + " C");
                        /// давление * 0.750064

                        Wind.setText(weather);
                    } catch (JSONException e) { // исключение отсутствия JSON объекта
                        e.printStackTrace();
                    }
                }
            });

        } catch (MalformedURLException e) { // исключение на случай отсутствия ссылки request
            e.printStackTrace();
        } catch (IOException e) { // исключение на случай отсутствия соединения
            e.printStackTrace();
        } catch (JSONException e) { // исключение отсутствия JSON объекта
            e.printStackTrace();
        }
    }
}