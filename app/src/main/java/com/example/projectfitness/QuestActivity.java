package com.example.projectfitness;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectfitness.Model.Quest;
import com.example.projectfitness.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class QuestActivity extends AppCompatActivity {

    TextView level, mission, count, time, point, test, test1, test2;
    ImageButton green, red;

    DatabaseReference reference,reference1;
    FirebaseUser firebaseUser;

    int Qpoint=0,Upoint=0,Sum=0;

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
        green = findViewById(R.id.green);
        red = findViewById(R.id.red);
        test = findViewById(R.id.test);
        test1 = findViewById(R.id.test1);
        test2 = findViewById(R.id.test2);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Quest").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Quest quest = dataSnapshot.getValue(Quest.class);
                level.setText(quest.getLevel());
                point.setText(String.valueOf(quest.getPoint()));
                mission.setText(quest.getMission());
                count.setText(quest.getCount());
                time.setText(quest.getTime());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        green.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final AlertDialog.Builder adb = new AlertDialog.Builder(QuestActivity.this);
//                final View mview = getLayoutInflater().inflate(R.layout.dialog_pass, null);
//                final EditText pass = (EditText) mview.findViewById(R.id.pass);
//                Button confirm = (Button) mview.findViewById(R.id.confirm);
//
//                confirm.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (pass.getText().toString().equals("")) {
//                            Qpoint=0;
//                            getPoints();
//                            getSum();
//                            Toast.makeText(getApplication(), "Confirm Success!", Toast.LENGTH_LONG).show();
//                            finish();
//                        } else {
//                            Toast.makeText(getApplication(), "Trainer only!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//                adb.setView(mview);
//                AlertDialog dialog = adb.create();
//                dialog.show();
//            }
//        });

//        red.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final AlertDialog.Builder adb = new AlertDialog.Builder(QuestActivity.this);
//                final View mview = getLayoutInflater().inflate(R.layout.cancel_quest, null);
//                final EditText cpass = (EditText) mview.findViewById(R.id.cpass);
//                Button cconfirm = (Button) mview.findViewById(R.id.cconfirm);
//
//                cconfirm.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (cpass.getText().toString().equals("")) {
//
//                            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//                            reference = FirebaseDatabase.getInstance().getReference("Quest").child(firebaseUser.getUid());
//                            HashMap<String, Object> map = new HashMap<>();
//                            map.put("level", "");
//                            map.put("mission", "");
//                            map.put("count", "");
//                            map.put("time", "");
//                            map.put("point", 0);
//                            reference.updateChildren(map);
//                            Toast.makeText(getApplication(), "Cancel Success!", Toast.LENGTH_LONG).show();
//                            finish();
//                        } else {
//                            Toast.makeText(getApplication(), "Trainer only!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//                adb.setView(mview);
//                AlertDialog dialog = adb.create();
//                dialog.show();
//            }
//        });

    }



//    public void getPoints() {
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Quest").child(firebaseUser.getUid());
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Quest quest1 = dataSnapshot.getValue(Quest.class);
//                Qpoint = (quest1.getPoint());
//                Qpoint = Qpoint/2;
//                //test.setText(String.valueOf(Qpoint));
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    public void getSum(){
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                Upoint = (user.getPoints());
//                //test1.setText(String.valueOf(Upoint));
//                Sum = Qpoint+Upoint;
//                Push();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });
//
//    }
//
//    public void Push(){
//          //test2.setText(String.valueOf(Sum));
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        reference1 = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("points",Sum);
//        reference1.updateChildren(map);
//        Sum=0;
//
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Quest").child(firebaseUser.getUid());
//        HashMap<String, Object> map1 = new HashMap<>();
//        map1.put("level", "");
//        map1.put("mission", "");
//        map1.put("count", "");
//        map1.put("time", "");
//        map1.put("point", 0);
//        reference.updateChildren(map1);
//
//    }
}



