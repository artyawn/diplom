package com.artyawn.arty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.artyawn.arty.ActivityTask.Tasks;
import com.artyawn.arty.ActivityTask.CreateTask;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button logout_btn;
    private FirebaseAuth mAuth;
    private TextView  textViewDate, tasks, create_tasks, tasks_for_mates;
    private String email;
    private ImageView usr_icon;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout_btn = findViewById(R.id.btn_logout);
        mAuth = FirebaseAuth.getInstance();
        usr_icon = findViewById(R.id.user_icon);
        textViewDate=findViewById(R.id.textViewDate);
        tasks = findViewById(R.id.tasks);
        create_tasks = findViewById(R.id.create_tasks);
        tasks_for_mates = findViewById(R.id.tasks_for_mates);

        // Дата
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        textViewDate.setText(dateText);

        create_tasks.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateTask.class);
            startActivity(intent);
        });

        //Переход на задачи
        tasks.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Tasks.class);
            startActivity(intent);
        });


//Переход на страницу юзера
        usr_icon.setOnClickListener(view -> {
            Intent intent = new Intent (MainActivity.this, UserActivity.class);
            startActivity(intent);

        });

        tasks_for_mates.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MatesTasksActivity.class);
            startActivity(intent);
        });


//Выход из аккаунта
        logout_btn.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent (MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}