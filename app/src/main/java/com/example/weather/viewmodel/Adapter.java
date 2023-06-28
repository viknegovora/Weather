package com.example.weather.viewmodel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.model.City;
import com.example.weather.view.Third_Activity;
import com.example.weather.view.UpdateActivity;
import com.example.weather.view.SecondActivity;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    // поля адаптера
    private Context context; // поле для контекста
    private Activity activity; // поле для активности

    private List<City> cityList; // поле для всех городов
    private List<City> newList; // поле для нового города



    // конструктор
    public Adapter(Context context, Activity activity, List<City> cityList) {
        this.context = context;
        this.activity = activity;
        this.cityList = cityList;
        newList = new ArrayList<>(cityList);
    }

    // метод onCreateViewHolder() возвращает объект ViewHolder(), который будет хранить данные по одному объекту City
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // трансформация layout-файла во View-элемент
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        holder.title.setText(cityList.get(position).getTitle());

        // обработаем нажатие на контейнер city_recycler_view
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // задание переключения на новый экран
                Intent intent = new Intent(context, Third_Activity.class);
                // передача данных в новую активити
                intent.putExtra("title", cityList.get(position).getTitle());
                // старт перехода
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }


    // созданный статический класс ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // поля представления
        TextView title;
        ConstraintLayout mLayout;

        // конструктор класса ViewHolder с помощью которого мы связываем поля и представление city_recycler_view.xml
        ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title);
            mLayout = view.findViewById(R.id.mLayout);
        }
    }
}


