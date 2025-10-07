package com.example.multithreadinglab;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AsyncActivity extends AppCompatActivity {
    private Button btnQuickJob, btnSlowJob;
    private TextView tvStatus;
    private SlowTask slowTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        findViewByIds();
        slowTask = new SlowTask(AsyncActivity.this, tvStatus);
        btnQuickJob.setOnClickListener(v -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            tvStatus.setText(sdf.format(new Date()));
        });
        btnSlowJob.setOnClickListener(v -> slowTask.execute());
    }
    private void findViewByIds() {
        btnQuickJob = findViewById(R.id.btn_quick_job);
        btnSlowJob = findViewById(R.id.btn_slow_job);
        tvStatus = findViewById(R.id.tv_status);
    }
}
