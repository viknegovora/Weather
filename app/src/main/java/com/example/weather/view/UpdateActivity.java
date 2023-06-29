package com.example.weather.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weather.R;
import com.example.weather.viewmodel.DatabaseHelper;

public class UpdateActivity extends AppCompatActivity {

    // создание полей
    private EditText title;
    private Button updateCity, deleteCity;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_update);

        // присваивание id полям
        title = findViewById(R.id.title);
        updateCity = findViewById(R.id.update_city);
        deleteCity= findViewById(R.id.delete_city);

        // считывание данных из переданного намерения Intent
        Intent intent = getIntent();
        // запись этих данных на экран активности
        title.setText(intent.getStringExtra("title"));
        id = (intent.getStringExtra("id"));

        // обработка нажатия кнопки
        updateCity.setOnClickListener(listener);
        deleteCity.setOnClickListener(listener);
    }

    // обработка нажатия кнопки
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // если исправленный текст не пустой, то обновление записи в БД
            if(!TextUtils.isEmpty(title.getText().toString())) {
                DatabaseHelper database = new DatabaseHelper(UpdateActivity.this); // создание объекта БД в текущей активности

                // обработка кнопки
                switch (view.getId()) {
                    case R.id.update_city:
                        // обновление города
                        database.updateCity(title.getText().toString(), id); // обновление записи в БД по id
                        break;
                    case R.id.delete_city:
                        // удаление города
                        database.deleteSingleItem(id); // удаление записи в БД по id
                        break;
                }

                startActivity(new Intent(UpdateActivity.this, SecondActivity.class)); // переключение обратно в активность демонстрации всех записей
            } else { // иначе просто тост об отсутствии изменений
                Toast.makeText(UpdateActivity.this,"Изменений не внесено", Toast.LENGTH_SHORT).show();
            }
        }
    };

}