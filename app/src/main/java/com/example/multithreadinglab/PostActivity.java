package com.example.multithreadinglab;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PostActivity extends AppCompatActivity {
    private ProgressBar pbWaiting;
    private TextView tvTopCaption;
    private EditText etInput;
    private Button btnExecute;
    private int globalValue, accum;
    private long startTime;
    private final String PATIENCE = "Some important data is being collected now.\nPlease be patient...wait...";
    private Handler handler;
    private Runnable fgRunnable, bgRunnable;
    private Thread testThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        findViewByIds();
        initVariables();
        btnExecute.setOnClickListener(v -> {
            String data = etInput.getText().toString();
            Toast.makeText(PostActivity.this, data, Toast.LENGTH_SHORT).show();
        });
        testThread.start();
    }
    private void findViewByIds() {
        tvTopCaption = findViewById(R.id.tv_top_caption);
        pbWaiting = findViewById(R.id.pb_waiting);
        etInput = findViewById(R.id.et_input);
        btnExecute = findViewById(R.id.btn_execute);
    }
    private void initVariables() {
        globalValue = 0;
        accum = 0;
        startTime = System.currentTimeMillis();
        handler = new Handler();
        fgRunnable = () -> {
            globalValue += 100;
            tvTopCaption.setText("Global value: " + globalValue + "\n" + PATIENCE);
            pbWaiting.setProgress(globalValue % 100);
        };
        bgRunnable = () -> {
            while (globalValue < 1000) {
                try {
                    Thread.sleep(1000);
                    globalValue++;
                    handler.post(fgRunnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            handler.post(() -> tvTopCaption.setText(getString(R.string.bg_work_is_over)));
        };
        testThread = new Thread(bgRunnable);
    }
}
