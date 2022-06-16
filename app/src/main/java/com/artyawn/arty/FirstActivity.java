package com.artyawn.arty;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.artyawn.arty.ActivityMyGroups.MyGroupsActivity;
import com.artyawn.arty.ActivityTask.Tasks;
//import com.artyawn.arty.CreateGroup.NewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FirstActivity extends AppCompatActivity {

    CardView tasks, groups, stats;
    ImageView usr_icon;
    TextView date;


    DatabaseReference myRef;
    ListView listView;
    ArrayList<String> arList;
    ArrayAdapter<String> arrayAdapter;
    String mAuth;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        tasks = findViewById(R.id.tasks_main);
        groups = findViewById(R.id.groups_main);
        stats = findViewById(R.id.stats_main);
        usr_icon = findViewById(R.id.usr_ic);
        date = findViewById(R.id.date);
        listView =(ListView) findViewById(R.id.listViewCard);
        arList = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arList);

        mAuth = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference("users").child(mAuth).child("tasks");
        listView.setAdapter(arrayAdapter);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(CreateTaskClass.class).getTask_name();
                arList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



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
