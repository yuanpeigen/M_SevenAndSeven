package mark.passwordinput;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.jungly.gridpasswordview.GridPasswordView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.gridPasswordView)
    GridPasswordView gridPasswordView;

    private boolean isExit;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        onPwdChangedTest();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                isExit = false;
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(MainActivity.this, R.string.mark, Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(0, 1500);
                return false;
            } else {
                finish();
            }
        }
        return true;
    }


    void onPwdChangedTest() {
        gridPasswordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                Toast.makeText(MainActivity.this, psw, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, WhorlViewActivity.class));
            }
        });
    }
}
