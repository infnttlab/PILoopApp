package it.ttlab.piloopapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());
        //tv.setText(String.valueOf(doubleFromJNI()));
    }

    public void calculatePI(View view) {
        EditText editText = (EditText) findViewById(R.id.stepsEditText);
        TextView piTextView = (TextView) findViewById(R.id.PiTextView);
        TextView timeTextView = (TextView) findViewById(R.id.timeTextView);
        TextView time2TextView = (TextView) findViewById(R.id.time2TextView);
        String stepsString = editText.getText().toString();
        CheckBox javaCheckBox = (CheckBox) findViewById(R.id.checkBox);


        long steps = Long.parseLong(stepsString);
        long t1Start = System.currentTimeMillis();
        double pi1 = piFromJNI(steps);
        long t1End = System.currentTimeMillis();
        long t1Delta = t1End - t1Start;
        double elapsed1Seconds = t1Delta / 1000.0;
        piTextView.setText(String.valueOf(pi1));
        timeTextView.setText(String.valueOf(timeFromJNI(steps)));
        time2TextView.setText(String.valueOf(elapsed1Seconds));
        //((Activity) context).runOnUiThread(new Runnable() {
        //    @Override
        //    public void run() {
        //        piTextView.setText(String.valueOf(pi1));
        //        timeTextView.setText(String.valueOf(timeFromJNI(steps)));
        //        time2TextView.setText(String.valueOf(elapsed1Seconds));
        //    }
        //});
        //piTextView.postInvalidate();
        //timeTextView.postInvalidate();
        //time2TextView.postInvalidate();
        TextView time3TextView = (TextView) findViewById(R.id.time3TextView);
        if (javaCheckBox.isChecked()) {
            long t2Start = System.currentTimeMillis();
            double pi2 = Pi.getPI(steps);
            long t2End = System.currentTimeMillis();
            long t2Delta = t2End - t2Start;
            double elapsed2Seconds = t2Delta / 1000.0;
            time3TextView.setText(String.valueOf(elapsed2Seconds));
            //time3TextView.postInvalidate();
        }else{
            time3TextView.setText("N/A");
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native double piFromJNI(long steps);
    public native double timeFromJNI(long steps);
}
