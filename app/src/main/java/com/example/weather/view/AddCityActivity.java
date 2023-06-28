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

public class AddCityActivity extends AppCompatActivity {

    // создание полей
    private EditText name_edit;
    private Button addCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        // присваивание id полям
        name_edit = findViewById(R.id.name_edit);
        addCity = findViewById(R.id.add_city);

        // обработка нажатия кнопки
        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // если исправленный текст не пустой, то обновление записи в БД
                if (!TextUtils.isEmpty(name_edit.getText().toString())){

                    DatabaseHelper database = new DatabaseHelper(AddCityActivity.this); // создание объекта БД в текущей активности
                    database.addCity(name_edit.getText().toString()); // создание записи в БД

                    // создание намерения переключения активности
                    Intent intent = new Intent(AddCityActivity.this, SecondActivity.class); // переключение обратно в активность демонстрации всех записей
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // установления флага экономии ресурсов
                    startActivity(intent);

                    finish(); // при нажатии на кнопку назад действие уничтожается и проиходит переход в активность SecondActivity

                } else { // иначе просто тост об отсутствии изменений
                    Toast.makeText(AddCityActivity.this, "Необходимо заполнить поле", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}