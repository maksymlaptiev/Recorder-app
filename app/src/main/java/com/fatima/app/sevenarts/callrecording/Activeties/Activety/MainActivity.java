package com.fatima.app.sevenarts.callrecording.Activeties.Activety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.fatima.app.sevenarts.callrecording.Activeties.Fragment.Incoming;
import com.fatima.app.sevenarts.callrecording.Activeties.Fragment.More;
import com.fatima.app.sevenarts.callrecording.Activeties.Fragment.Outgoing;
import com.fatima.app.sevenarts.callrecording.Activeties.Fragment.Saved;
import com.fatima.app.sevenarts.callrecording.R;
import com.fatima.app.sevenarts.callrecording.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.INCOMING:

                        Toast.makeText(MainActivity.this, "INCOMING", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(MainActivity.this,Incoming.class);
//                        startActivity(i);
//                        startActivity(new Intent(MainActivity.this, Incoming.class));
                        break;
                    case R.id.OUTGOING:
                        Toast.makeText(MainActivity.this, "OUTGOING", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(MainActivity.this, Outgoing.class));

                        break;
                    case R.id.SAVED:
                        Toast.makeText(MainActivity.this, "SAVED", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(MainActivity.this, Saved.class));

                        break;
                    case R.id.MORE:
                        Toast.makeText(MainActivity.this, "MORE", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(MainActivity.this, More.class));
                        break;
                }
                return true;
            }
        });
    }
    }
