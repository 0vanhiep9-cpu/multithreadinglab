package com.example.multithreadinglab;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MessageActivity extends AppCompatActivity {
    private ProgressBar pbFirst, pbSecond;
    private TextView tvMsgWorking, tvMsgReturned;
    private boolean isRunning;
    private int MAX_SEC = 10;
    private int intTest;
    private Thread bgThread;
    private Handler handler;
    private Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        findViewByIds();
        initVariables();
    }
    private void findViewByIds() {
        pbFirst = findViewById(R.id.pb_first);
        pbSecond = findViewById(R.id.pb_second);
        tvMsgWorking = findViewById(R.id.tv_working);
        tvMsgReturned = findViewById(R.id.tv_return);
        btnStart = findViewById(R.id.btn_start);
    }
    private void initVariables() {
        handler = new Handler(msg -> {
            String returned = msg.getData().getString("returnedData");
            tvMsgReturned.setText(returned);
            pbFirst.setProgress(msg.what);
            pbSecond.setProgress(msg.what);
            return true;
        });

        btnStart.setOnClickListener(v -> initBgThread());
    }
    private void initBgThread() {
        isRunning = true;
        bgThread = new Thread(() -> {
            for (int i = 0; i <= MAX_SEC && isRunning; i++) {
                try {
                    Thread.sleep(1000);
                    intTest++;
                    int random = new Random().nextInt(100);
                    Message msg = handler.obtainMessage();
                    Bundle b = new Bundle();
                    b.putString("returnedData", "Returned by background thread:\n\nRandom: " + random +
                            "\nGlobal value seen by all thread: " + intTest);
                    msg.setData(b);
                    msg.what = i * 10;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            runOnUiThread(() ->
                    tvMsgWorking.setText(getString(R.string.done_background_thread_has_been_stopped)));
        });
        bgThread.start();
        tvMsgWorking.setText(getString(R.string.working));
    }
    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }
}
