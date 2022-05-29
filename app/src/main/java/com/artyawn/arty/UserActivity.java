package com.artyawn.arty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.artyawn.arty.CreateGroup.NewGroup;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {

    private TextView  id;
    private ImageView btn_get_id;
    private ImageButton logout_btn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btn_get_id = findViewById(R.id.btn_get_id);
        id = findViewById(R.id.et_id);
        logout_btn = findViewById(R.id.logout_btn);
        mAuth = FirebaseAuth.getInstance();
        String user = mAuth.getUid();




//        create_new_group.setOnClickListener(view -> {
//            Intent intent = new Intent(UserActivity.this, NewGroup.class);
//            startActivity(intent);
//        });

        btn_get_id.setOnClickListener(view -> {
                id.setText(user);
            //скопировать id
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", id.getText().toString());
            clipboard.setPrimaryClip(clip);
            if (user.isEmpty()){
                    Toast.makeText(this, "Возникла ошибка", Toast.LENGTH_SHORT).show();
                }
            else{
                Toast.makeText(this, "Скопированно в буфер обмена", Toast.LENGTH_SHORT).show();

            }
        });

//        my_groups.setOnClickListener(view -> {
//            Intent intent=new Intent(UserActivity.this, MyGroupsActivity.class);
//            startActivity(intent);
//        });



        //Выход из аккаунта
        logout_btn.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent (UserActivity.this, LoginActivity.class);
            startActivity(intent);
        });


    }
}