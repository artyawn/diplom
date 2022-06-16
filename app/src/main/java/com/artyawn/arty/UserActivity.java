package com.artyawn.arty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.artyawn.arty.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {

    private TextView  tv_id;
    private ImageView btn_get_id, back_btn;
    private ImageButton logout_btn;
    private FirebaseAuth mAuth;
    private EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btn_get_id = findViewById(R.id.btn_get_id);
        id = findViewById(R.id.et_id);
        logout_btn = findViewById(R.id.logout_btn);
        back_btn = findViewById(R.id.back_btn);
        mAuth = FirebaseAuth.getInstance();
        tv_id = findViewById(R.id.tv_id);
        String user = mAuth.getUid();



        tv_id.setOnClickListener(view -> {
            id.setText(user);

        });



        back_btn.setOnClickListener(view -> {
            Intent intent = new Intent(UserActivity.this, FirstActivity.class);
            startActivity(intent);
        });


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





        //Выход из аккаунта
        logout_btn.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent (UserActivity.this, LoginActivity.class);
            startActivity(intent);
        });


    }
}