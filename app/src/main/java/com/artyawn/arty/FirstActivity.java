package com.artyawn.arty;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.artyawn.arty.ActivityTask.Tasks;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class FirstActivity extends AppCompatActivity {

    CardView tasks, groups, stats;
    ImageView usr_icon;
    TextView date;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        tasks = findViewById(R.id.tasks_main);
        groups = findViewById(R.id.groups_main);
        stats = findViewById(R.id.stats_main);
        usr_icon = findViewById(R.id.usr_ic);
        date = findViewById(R.id.date);

        //date
        Date currentDate = new Date();
        DateFormat dateDay = new SimpleDateFormat("dd", Locale.getDefault());
        String dayText = dateDay.format(currentDate);

        Date currentDate1 = new Date();
        DateFormat dateMonth = new SimpleDateFormat("MM", Locale.getDefault());
        String monthText = dateMonth.format(currentDate1);



            if (monthText.equals("01")) {
                monthText = "января";
            } else if (monthText.equals("02")) {
                monthText = "февряля";
            } else if (monthText.equals("03")) {
                monthText = "марта";
            } else if (monthText.equals("04")) {
                monthText = "апреля";
            } else if (monthText.equals("05")) {
                monthText = "мая";
            } else if (monthText.equals("06")) {
                monthText = "июня";
            } else if (monthText.equals("07")) {
                monthText = "июля";
            } else if (monthText.equals("08")) {
                monthText = "августа";
            } else if (monthText.equals("09")) {
                monthText = "сентября";
            } else if (monthText.equals("10")) {
                monthText = "октября";
            } else if (monthText.equals("11")) {
                monthText = "ноября";
            } else if (monthText.equals("12")) {
                monthText = "декабря";
            }
            date.setText(dayText + " " + monthText);

        tasks.setOnClickListener(view -> {
            Intent intent = new Intent(FirstActivity.this, Tasks.class);
            startActivity(intent);
        });

        usr_icon.setOnClickListener(view -> {
           Intent intent = new Intent (FirstActivity.this, UserActivity.class);
            startActivity(intent);
        });

        groups.setOnClickListener(view -> {
            Intent intent = new Intent(FirstActivity.this, MyGroupsActivity.class);
            startActivity(intent);
        });



    }
}
