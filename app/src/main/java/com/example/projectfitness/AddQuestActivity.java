package com.example.projectfitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projectfitness.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.CallableStatement;
import java.util.HashMap;

public class AddQuestActivity extends AppCompatActivity {

    ImageView image_profile;
    TextView username;
    EditText level, point, mission, count, time;
    Button comfirm;

    DatabaseReference reference;
    FirebaseUser firebaseUser;
    String profileid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quest);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add-Quest");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        final String uid = bundle.getString("publisherid");

        level = findViewById(R.id.level);
        point = findViewById(R.id.point);
        mission = findViewById(R.id.mission);
        count = findViewById(R.id.count);
        time = findViewById(R.id.time);
        comfirm = findViewById(R.id.comfirm);



        SharedPreferences prefs = getApplicationContext().getSharedPreferences("PREFS", MODE_PRIVATE);
        profileid = prefs.getString("profileid", "none");

        image_profile = findViewById(R.id.image_profile);
        username = findViewById(R.id.username);


        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuest(uid);
            }


        });


        userInfo(uid);

    }

    private void addQuest(String uid){
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Quest").child(uid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("level",level);
        map.put("point",point);
        map.put("count",count);
        map.put("time",time);
        Toast.makeText(getApplication(),"AddQuest-Success",Toast.LENGTH_LONG).show();
    }

    private void userInfo(String uid){


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(profileid=uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (getApplicationContext() == null){
                    return;
                }
                User user = dataSnapshot.getValue(User.class);

                Glide.with(getApplicationContext()).load(user.getImageurl()).into(image_profile);
                username.setText(user.getUsername());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
