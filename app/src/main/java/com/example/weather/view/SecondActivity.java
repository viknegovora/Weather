package com.example.weather.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weather.R;
import com.example.weather.model.City;
import com.example.weather.viewmodel.Adapter;
import com.example.weather.viewmodel.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    // создание полей
    private ImageView imageView;
    private RecyclerView recyclerView;
    private FloatingActionButton fabadd;

    private FloatingActionButton fabdelete; // поле для кнопки удалить все города

    public DatabaseHelper database; // поле работы с БД
    public Adapter adapter; // поле для адаптера
    private List<City> cityList; // поле для всех городов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_second);

        // присваивание id полям
        imageView = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.recycler_List);
        fabadd = findViewById(R.id.fabadd);
        fabdelete = findViewById(R.id.fabdelete);

        cityList = new  ArrayList<>(); // выделение памяти и задание типа контейнера для списка городов
        database = new DatabaseHelper(this); // выделение памяти и задание текущего контекста работы с БД


        // считывание данных из БД и запись их в коллекцию cityList
        fetchAllCity();


        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // задание структуры вывода данных в recyclerView
        adapter = new Adapter(this, SecondActivity.this, cityList) ;// инициализация адаптера и передача в рего данных из БД
        recyclerView.setAdapter(adapter); // передача в recyclerView адаптера

        // обработка нажатия кнопки создания нового города
        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // переключение на новую активность
                startActivity(new Intent(SecondActivity.this, AddCityActivity.class));
            }
        });
        fabdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllCity();
            }
        });
    }

    public void fetchAllCity() {
        // чтение БД и запись данных в курсор
        Cursor cursor = database.readCity();

        if (cursor.getCount() == 0) { // если данные отсутствую, то вывод на экран об этом тоста
            Toast.makeText(this, "Заметок нет", Toast.LENGTH_SHORT).show();
        } else { // иначе помещение их в контейнер данных notesList
            while (cursor.moveToNext()){
                // помещение в контейнер notesList из курсора данных
                cityList.add(new City(cursor.getString(1)));
            }
        }
    }
    public void deleteAllCity() {
        // чтение БД и запись данных в курсор
        database.deleteAllCity();
        cityList.clear();
        adapter.notifyDataSetChanged();
        Cursor cursor = database.readCity();

        if (cursor.getCount() == 0) { // если данные отсутствую, то вывод на экран об этом тоста
            Toast.makeText(this, "Города удалены", Toast.LENGTH_SHORT).show();
        } else { // иначе помещение их в контейнер данных notesList
            while (cursor.moveToNext()) {
                // помещение в контейнер notesList из курсора данных
                cityList.add(new City(cursor.getString(1)));
            }
            Toast.makeText(this, "Города не удалены. Что-то пошло не так!", Toast.LENGTH_SHORT).show();
        }
    }
}

