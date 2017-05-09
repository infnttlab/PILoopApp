package it.ttlab.piloopapp;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static it.ttlab.piloopapp.R.id.cppCheckBox;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    Activity mActivity;
    private EditText editText;
    private TextView piTextView;
    private static final String KEY_PI_TEXT = "key_pi_text";
    //private TextView chronoTimeTextView;
    private TextView cppTimeTextView;
    private static final String KEY_CPP_TIME_TEXT = "key_cpp_time_text";
    private TextView javaTimeTextView;
    private static final String KEY_JAVA_TIME_TEXT = "key_java_time_text";
    private CheckBox javaCheckBox;
    private CheckBox cppCheckBox;
    private int threadsNum = 1;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //setContentView(R.layout.activity_main);

    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(KEY_PI_TEXT,piTextView.getText());
        outState.putCharSequence(KEY_CPP_TIME_TEXT,cppTimeTextView.getText());
        outState.putCharSequence(KEY_JAVA_TIME_TEXT,javaTimeTextView.getText());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.coresSpinner);
        String[] threads = getResources().getStringArray(R.array.threads);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        threads);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //                        android.R.layout.simple_spinner_item,
        //                        threads);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                threadsNum=Integer.parseInt(tv.getText().toString());
                //m_tv.setText(tv.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                threadsNum = 1 ;
            }
        });
        if (savedInstanceState != null) {
            piTextView.setText(savedInstanceState.getCharSequence(KEY_PI_TEXT));
            cppTimeTextView.setText(savedInstanceState.getCharSequence(KEY_CPP_TIME_TEXT));
            javaTimeTextView.setText(savedInstanceState.getCharSequence(KEY_JAVA_TIME_TEXT));
        }
    }

    public void calculatePI(View view) {
        editText = (EditText) findViewById(R.id.stepsEditText);
        piTextView = (TextView) findViewById(R.id.PiTextView);
        //chronoTimeTextView = (TextView) findViewById(R.id.ChronoTimeTextView);
        cppTimeTextView = (TextView) findViewById(R.id.CppTimeTextView);
        javaTimeTextView = (TextView) findViewById(R.id.JavaTimeTextView);
        String stepsString = editText.getText().toString();
        javaCheckBox = (CheckBox) findViewById(R.id.javaCheckBox);
        cppCheckBox = (CheckBox) findViewById(R.id.cppCheckBox);


        long steps = Long.parseLong(stepsString);
        
        if (cppCheckBox.isChecked()) {
            long t1Start = System.currentTimeMillis();
            double pi1 = piFromJNI(steps, threadsNum);
            long t1End = System.currentTimeMillis();
            long t1Delta = t1End - t1Start;
            double elapsed1Seconds = t1Delta / 1000.0;
            piTextView.setText(String.valueOf(pi1));
            //chronoTimeTextView.setText(String.valueOf(timeFromJNI(steps)));
            cppTimeTextView.setText(String.valueOf(elapsed1Seconds));
        }else{
            cppTimeTextView.setText("N/A");
        }
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
        if (javaCheckBox.isChecked()) {
            long t2Start = System.currentTimeMillis();
            double pi2 = Pi.getPI(steps);
            long t2End = System.currentTimeMillis();
            long t2Delta = t2End - t2Start;
            double elapsed2Seconds = t2Delta / 1000.0;
            javaTimeTextView.setText(String.valueOf(elapsed2Seconds));
            //time3TextView.postInvalidate();
        }else{
            javaTimeTextView.setText("N/A");
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native double piFromJNI(long steps, int threads);
    public native double timeFromJNI(long steps);
}
