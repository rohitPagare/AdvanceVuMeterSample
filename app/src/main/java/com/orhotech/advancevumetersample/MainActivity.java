package com.orhotech.advancevumetersample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.orhotech.advancevumeter.VuMeterView;

public class MainActivity extends AppCompatActivity {

    private VuMeterView mVuMeterView;

    private boolean mEnableAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setUI();
    }

    private void setUI() {
        //init
        mVuMeterView = (VuMeterView) findViewById(R.id.vumeter);
        SeekBar mBarNumberSeekBar = (SeekBar) findViewById(R.id.numberSeekBar);
        SwitchMaterial mAnimationSwitch = (SwitchMaterial) findViewById(R.id.barNumberSwitch);

        //set seekbar
        mBarNumberSeekBar.incrementProgressBy(1);
        mBarNumberSeekBar.setMax(10);

        mBarNumberSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mVuMeterView.setBlockNumber(progress + 1);
            }
        });

        mAnimationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> mEnableAnimation = isChecked);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (super.onOptionsItemSelected(item)) {
            return true;
        }
        int itemId = item.getItemId();

        if (itemId == R.id.action_stop) {
            mVuMeterView.stop(mEnableAnimation);
            return true;
        } else if (itemId == R.id.action_resume) {
            mVuMeterView.resume(mEnableAnimation);
            return true;
        } else if (itemId == R.id.action_pause) {
            mVuMeterView.pause();
            return true;
        } else {
            return false;
        }
    }
}