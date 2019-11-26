package com.example.projectfitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TrainActivity extends AppCompatActivity {

    EditText pass;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Trainer-Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pass=(EditText)findViewById(R.id.pass);
        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pass.getText().toString().equals("123456789")) {
                    Intent T1=new Intent(TrainActivity.this, Trainer1Activity.class);
                    startActivity(T1);
                    Toast.makeText(getApplication(),"Trainer Success",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplication(),"Trainer only",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
