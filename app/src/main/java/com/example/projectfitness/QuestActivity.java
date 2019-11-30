package com.example.projectfitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectfitness.Model.Quest;
import com.example.projectfitness.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuestActivity extends AppCompatActivity {

    TextView level,mission,count,time,point;

    DatabaseReference reference;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quests");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        level = findViewById(R.id.level);
        point = findViewById(R.id.point);
        mission = findViewById(R.id.mission);
        count = findViewById(R.id.count);
        time = findViewById(R.id.time);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Quest").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Quest quest = dataSnapshot.getValue(Quest.class);
                level.setText(quest.getLevel());
                point.setText(quest.getPoint());
                mission.setText(quest.getMission());
                count.setText(quest.getCount());
                time.setText(quest.getTime());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
