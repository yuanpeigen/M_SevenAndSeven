package mark.passwordinput;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tt.whorlviewlibrary.WhorlView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mark on 2016/7/6.
 */
public class WhorlViewActivity extends AppCompatActivity {

    @InjectView(R.id.whorl)
    WhorlView whorlView;
    @InjectView(R.id.whorl2)
    WhorlView whorlView2;
    @InjectView(R.id.whorl3)
    WhorlView whorlView3;
    @InjectView(R.id.button)
    Button button;

    private boolean isRunning = true, isExit;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whorlview);
        ButterKnife.inject(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    stopWhorlview();
                    button.setText("START");
                    isRunning = false;
                } else {
                    startWhorlview();
                    button.setText("STOP");
                    isRunning = true;
                }
            }
        });
        startWhorlview();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                isExit = false;
            }
        };
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(WhorlViewActivity.this, R.string.toastString, Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(0, 1500);
                return false;
            } else {
                finish();
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    void startWhorlview() {
        whorlView.start();
        whorlView2.start();
        whorlView3.start();
    }

    void stopWhorlview() {
        whorlView.stop();
        whorlView2.stop();
        whorlView3.stop();
    }

}
