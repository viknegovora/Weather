package com.example.weather.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.weather.R;

public class MainActivity extends AppCompatActivity {

    //создание полей

    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_main);

        // присваивание id полям
        imageButton = findViewById(R.id.SQDate);



        // обработка нажатия кнопки
        imageButton.setOnClickListener(listener);
    }

    //создание слушателя
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // переключение на новую активность
            Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
            startActivity(intent);
        }
    };
}