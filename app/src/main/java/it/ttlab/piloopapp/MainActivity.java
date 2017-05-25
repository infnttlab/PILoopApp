package it.ttlab.piloopapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

import static it.ttlab.piloopapp.R.id.cppCheckBox;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public static final String LISTVIEW_FIRST_COLUMN = "First";
    public static final String LISTVIEW_SECOND_COLUMN = "Second";
    private ArrayList<HashMap<String,String>> list;
    ListView listView;
    Activity mActivity;
    private static final String KEY_PI_TEXT = "key_pi_text";
    private static final String KEY_CPP_TIME_TEXT = "key_cpp_time_text";
    private static final String KEY_JAVA_TIME_TEXT = "key_java_time_text";
    //private TextView chronoTimeTextView;

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native double piFromJNI(long steps, int threads);
    //public native double timeFromJNI(long steps);

    private int threadsNum = 1;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //setContentView(R.layout.activity_main);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putCharSequence(KEY_PI_TEXT,piTextView.getText());
        //outState.putCharSequence(KEY_CPP_TIME_TEXT,cppTimeTextView.getText());
        //outState.putCharSequence(KEY_JAVA_TIME_TEXT,javaTimeTextView.getText());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText(R.string.pi_benchmark_name);
        listView = (ListView) findViewById(R.id.listView);
        //initListView();

        //asciiArrayAdapter = new ArrayAdapter<AsciiCode>(this,
        //        android.R.layout.simple_list_item_1, android.R.id.text1, Asciis);
        //listAscii.setAdapter(asciiArrayAdapter);

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
                threadsNum = Integer.parseInt(tv.getText().toString());
                //m_tv.setText(tv.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                threadsNum = 1;
            }
        });
        ListView lview = (ListView) findViewById(R.id.listView);
        populateList();
        ListViewAdapter adapter2 = new ListViewAdapter(this, list);
        lview.setAdapter(adapter2);
        //if (savedInstanceState != null) {
        // piTextView.setText(savedInstanceState.getCharSequence(KEY_PI_TEXT));
        // cppTimeTextView.setText(savedInstanceState.getCharSequence(KEY_CPP_TIME_TEXT));
        // javaTimeTextView.setText(savedInstanceState.getCharSequence(KEY_JAVA_TIME_TEXT));
        //}
    }

    private void populateList() {

        list = new ArrayList<HashMap<String,String>>();

        HashMap<String,String> temp = new HashMap<String,String>();
        temp.put(LISTVIEW_FIRST_COLUMN, "Brand");
        temp.put(LISTVIEW_SECOND_COLUMN, Build.BRAND);
        list.add(temp);

        HashMap<String,String> temp1 = new HashMap<String,String>();
        temp1.put(LISTVIEW_FIRST_COLUMN, "Model");
        temp1.put(LISTVIEW_SECOND_COLUMN, Build.MODEL);
        list.add(temp1);

        HashMap<String,String> temp2 = new HashMap<String,String>();
        temp2.put(LISTVIEW_FIRST_COLUMN, "CPU");
        temp2.put(LISTVIEW_SECOND_COLUMN, Build.BOARD);
        list.add(temp2);

        HashMap<String,String> temp3 = new HashMap<String,String>();
        temp3.put(LISTVIEW_FIRST_COLUMN, "Hardware");
        temp3.put(LISTVIEW_SECOND_COLUMN, Build.HARDWARE);
        list.add(temp3);

        HashMap<String,String> temp4 = new HashMap<String,String>();
        temp4.put(LISTVIEW_FIRST_COLUMN, "Manufacturer");
        temp4.put(LISTVIEW_SECOND_COLUMN, Build.MANUFACTURER);
        list.add(temp4);

    }

    //private void initListView() {
    //    Asciis = new AsciiCode[128];
    //    for (int i = 0; i < 128; i++) {
    //        Asciis[i] = new AsciiCode(i);
    //    }
    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            //case R.id.miPi:
            //    Log.d("MenuItem", "PI");
            //    Toast.makeText(this, "PI benchmark",Toast.LENGTH_SHORT).show();
            //    Intent intent = new Intent(this, PrimesActivity.class);
            //    startActivity(intent);
            //    return true;
            case R.id.miPrimes:
                Log.d("MenuItem", "Primes benchmark");
                Toast.makeText(this, "Primes calculus benchmark", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, PrimesActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void calculatePI(View view) {

        EditText editText = (EditText) findViewById(R.id.stepsEditText);
        TextView piTextView = (TextView) findViewById(R.id.PiTextView);
        //chronoTimeTextView = (TextView) findViewById(R.id.ChronoTimeTextView);
        TextView cppTimeTextView = (TextView) findViewById(R.id.CppTimeTextView);
        TextView javaTimeTextView = (TextView) findViewById(R.id.JavaTimeTextView);
        String stepsString = editText.getText().toString();
        CheckBox javaCheckBox = (CheckBox) findViewById(R.id.javaCheckBox);
        CheckBox cppCheckBox = (CheckBox) findViewById(R.id.cppCheckBox);


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
        } else {
            cppTimeTextView.setText(R.string.not_available);
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
        } else {
            javaTimeTextView.setText(R.string.not_available);
        }
    }


}
