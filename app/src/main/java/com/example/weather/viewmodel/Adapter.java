package com.example.weather.viewmodel;

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
import com.example.weather.view.UpdateActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // поля адаптера
    private Context context; // поле для контекста
    private Activity activity; // поле для активности
    private List<City> cityList; // поле для всех записей
    private List<City> newList; // поле для новой записи

    public Adapter(Context context, Activity activity, List<City> cityList, List<City> newList) {
        this.context = context;
        this.activity = activity;
        this.cityList = cityList;
        this.newList = newList;
    }

    // метод onCreateViewHolder() возвращает объект ViewHolder(), который будет хранить данные по одному объекту City
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // трансформация layout-файла во View-элемент
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    // метод onBindViewHolder() выполняет привязку объекта ViewHolder к объекту City по определенной позиции
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.title.setText(cityList.get(position).getTitle());

        // обработаем нажатие на контейнер city_recycler_view
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // задание переключения на новый экран
                Intent intent = new Intent(context, UpdateActivity.class);
                // передача данных в новую активити
                intent.putExtra("title", cityList.get(position).getTitle());
                intent.putExtra("id", cityList.get(position).getId());
                // старт перехода
                activity.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 0;
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

