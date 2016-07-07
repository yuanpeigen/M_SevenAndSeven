package mark.passwordinput;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.romainpiel.titanic.library.Titanic;
import com.romainpiel.titanic.library.TitanicTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mark on 2016/7/7.
 */
public class WelcomeActivity extends AppCompatActivity {

    @InjectView(R.id.my_text_view)
    TitanicTextView titanicTextView;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);
        titanicTextView.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));
        new Titanic().start(titanicTextView);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, WhorlViewActivity.class));
                finish();
            }
        }, 10000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @Override
    public void onBackPressed() {
    }
}
