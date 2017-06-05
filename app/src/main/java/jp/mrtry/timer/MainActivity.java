package jp.mrtry.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button startButton, stopButton;
    private TextView timerText;

    private Timer timer;
    private Handler handler = new Handler();
    private long count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.start_button);
        stopButton = (Button)findViewById(R.id.stop_button);

        timerText = (TextView)findViewById(R.id.timer);
        timerText.setText("0:10");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != timer) {
                    timer.cancel();
                    timer = null;
                }

                // Timer インスタンスを生成
                timer = new Timer();

                // カウンター
                count = 10;
                timerText.setText("0:10");

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // handlerdを使って処理をキューイングする
                        handler.post(new Runnable() {
                            public void run() {
                                if (count > 0) {
                                    count--;
                                    long mm = count / 60;
                                    long ss = count % 60;
                                    timerText.setText(String.format("%d:%2$02d", mm, ss));
                                } else {
                                    cancel();
                                    timer = null;
                                    timerText.setText("0:10");
                                }
                            }
                        });
                    }
                }, 0, 1000);
            }
        });


        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != timer) {
                    // Cancel
                    timer.cancel();
                    timer = null;
                    timerText.setText("0:10");

                }
            }
        });

    }
}
