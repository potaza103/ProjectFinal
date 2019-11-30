package com.example.projectfitness;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projectfitness.Adapter.TrainerAdapter;
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
    TextView username,test;
    EditText level, point, mission, count, time;
    Button comfirm;

    DatabaseReference reference;
    FirebaseUser firebaseUser;
    String profileid;
    ProgressDialog pd;


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
        test = findViewById(R.id.test);


        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String str_level = level.getText().toString();
                final String str_point = point.getText().toString();
                final String str_mission = mission.getText().toString();
                final String str_count = count.getText().toString();
                final String str_time = time.getText().toString();

                if (TextUtils.isEmpty(str_level) || TextUtils.isEmpty(str_point) || TextUtils.isEmpty(str_mission) || TextUtils.isEmpty(str_count)
                        || TextUtils.isEmpty(str_time)){
                    Toast.makeText(getApplication(),"All fields are required!",Toast.LENGTH_LONG).show();
                }else {
                    addQuest(uid, str_level, str_mission, str_count, str_time, str_point);
//                    addPoint(uid, str_point);
                    Toast.makeText(getApplication(),"AddQuest-Success",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        userInfo(uid);
    }

    private void addQuest(String uid, String level, String mission, String count, String time, String point){
        //test.setText(uid+level+point+mission+count+time);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Quest").child(uid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("level",level);
        map.put("mission",mission);
        map.put("count",count);
        map.put("time",time);
        map.put("point",point);
        reference.updateChildren(map);

    }

//    private void addPoint(String uid, String point){
//
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
////        reference.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                    User user = dataSnapshot.getValue(User.class);
////                Glide.with(getApplicationContext()).load(user.getPoint());
////                test.setText(user.getPoint());
////
////          }
////
////          @Override
////          public void onCancelled(@NonNull DatabaseError databaseError) {
////
////          }
////        });
//        HashMap<String, Object> map1 = new HashMap<>();
//        map1.put("point",point);
//        reference.updateChildren(map1);
//
//    }


    private void userInfo(String uid){

        reference = FirebaseDatabase.getInstance().getReference("Users").child(profileid=uid);
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
