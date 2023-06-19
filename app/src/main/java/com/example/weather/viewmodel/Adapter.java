package com.example.weather.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.model.City;

import java.util.List;

public class Adapter {

    // поля адаптера
    private Context context; // поле для контекста
    private Activity activity; // поле для активности
    private List<City> cityList; // поле для всех записей
    private List<City> newList; // поле для новой записи

}

