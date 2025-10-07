package com.example.multithreadinglab;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {
    private Button btnMessage, btnPost, btnAsync;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        // Ánh xạ view
        btnMessage = findViewById(R.id.btn_message);
        btnPost = findViewById(R.id.btn_post);
        btnAsync = findViewById(R.id.btn_async);
        // Bắt sự kiện chuyển Activity
        btnMessage.setOnClickListener(v ->
                startActivity(new Intent(MainMenuActivity.this, MessageActivity.class)));

        btnPost.setOnClickListener(v ->
                startActivity(new Intent(MainMenuActivity.this, PostActivity.class)));

        btnAsync.setOnClickListener(v ->
                startActivity(new Intent(MainMenuActivity.this, AsyncActivity.class)));
    }
}
