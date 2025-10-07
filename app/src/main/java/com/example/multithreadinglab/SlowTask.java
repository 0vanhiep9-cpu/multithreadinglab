package com.example.multithreadinglab;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class SlowTask extends AsyncTask<Void, Integer, Void> {

    private ProgressDialog dialog;
    private Context context;
    private TextView tvStatus;

    public SlowTask(Context context, TextView tvStatus) {
        this.context = context;
        this.tvStatus = tvStatus;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.please_wait));
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(2000);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        tvStatus.setText("Progress: " + values[0]);
    }

    @Override
    protected void onPostExecute(Void unused) {
        dialog.dismiss();
        tvStatus.setText("Done");
    }
}
