package it.ttlab.piloopapp;

import android.content.Intent;
import android.content.res.Configuration;
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

/**
 * Created by Andrea Ferraro on 23/05/2017.
 */

public class PrimesActivity extends AppCompatActivity {

    ListView listView;
    private int threadsNum = 1;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText(R.string.primes_benchmark_name);
        listView = (ListView) findViewById(R.id.listView);
        Spinner spinner = (Spinner) findViewById(R.id.coresSpinner);
        String[] threads = getResources().getStringArray(R.array.threads);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                threads);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                threadsNum = Integer.parseInt(tv.getText().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                threadsNum = 1;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miPi:
                Log.d("MenuItem", "PI");
                Toast.makeText(this, "PI calculus benchmark", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            //case R.id.miPrimes:
            //    Log.d("MenuItem", "Primes benchmark");
            //    Toast.makeText(this, "Profile",Toast.LENGTH_SHORT).show();
            //    Intent intent2 = new Intent(this, PrimesActivity.class);
            //    startActivity(intent2);
            //    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void calculatePrimes(View view) {

        EditText editText = (EditText) findViewById(R.id.stepsEditText);
        TextView piTextView = (TextView) findViewById(R.id.PiTextView);
        //chronoTimeTextView = (TextView) findViewById(R.id.ChronoTimeTextView);
        TextView cppTimeTextView = (TextView) findViewById(R.id.CppTimeTextView);
        TextView javaTimeTextView = (TextView) findViewById(R.id.JavaTimeTextView);
        String stepsString = editText.getText().toString();
        CheckBox javaCheckBox = (CheckBox) findViewById(R.id.javaCheckBox);
        CheckBox cppCheckBox = (CheckBox) findViewById(R.id.cppCheckBox);


        long tot = Long.parseLong(stepsString);

        if (cppCheckBox.isChecked()) {
            long t1Start = System.currentTimeMillis();
            long pi1 = Primes.getJNIPrimes(tot, threadsNum);
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
            long pi2 = Primes.getPrimes(tot);
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
