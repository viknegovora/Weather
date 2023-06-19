package com.example.weather.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.weather.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SecondActivity extends AppCompatActivity {

    // создание полей
    private ImageView imageView;
    private RecyclerView recyclerView;
    private FloatingActionButton fabadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // присваивание id полям
        imageView = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.recycler_List);
        fabadd = findViewById(R.id.fabadd);

        // обработка нажатия кнопки
        fabadd.setOnClickListener(listener);
    }
    // создание слушателя
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (recyclerView.equals(SecondActivity.this)) {// переключение на новую активность
                Intent intent = new Intent(getApplicationContext(), Third_Activity.class);
                startActivity(intent);
            } else if (fabadd.equals(SecondActivity.this)) {// переключение на новую активность
                Intent intent1 = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(intent1);
            }
        }
    };
}