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

public class ViewQuestActivity extends AppCompatActivity {

    TextView level, mission, count, time, point, test, test1, test2;
    ImageButton green, red;

    DatabaseReference reference,reference1,referenceRef;
    FirebaseUser firebaseUser;

    int Qpoint,Upoint,Sum;
    int Hlevel;
    String Hmission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quest);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("View-Quest");
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

        Bundle bundle = getIntent().getExtras();
        final String uid = bundle.getString("publisherid");


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Quest").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Quest quest = dataSnapshot.getValue(Quest.class);
                level.setText(String.valueOf(quest.getLevel()));
                point.setText(String.valueOf(quest.getPoint()));
                mission.setText(quest.getMission());
                count.setText(quest.getCount());
                time.setText(String.valueOf(quest.getTime()));

                Hlevel = quest.getLevel();
                Hmission = quest.getMission();
                Qpoint = quest.getPoint();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Upoint = (user.getPoints());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hlevel!=0) {
                    final AlertDialog.Builder adb = new AlertDialog.Builder(ViewQuestActivity.this);
                    final View mview = getLayoutInflater().inflate(R.layout.dialog_pass, null);
                    //final EditText pass = (EditText) mview.findViewById(R.id.pass);
                    Button confirm = (Button) mview.findViewById(R.id.confirm);

                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //if (pass.getText().toString().equals("")) {

                            //getPoints(uid);
                            //getSum(uid,Qpoint);
                            //Push(uid,Sum);
                            getSum(uid, Qpoint, Upoint);
                            History(uid, Hlevel);
                            Setquest(uid);
                            Toast.makeText(getApplication(), "Confirm Quest Success!", Toast.LENGTH_LONG).show();
                            finish();
                            //} else {
                            // Toast.makeText(getApplication(), "Trainer only!", Toast.LENGTH_LONG).show();
                            //}
                        }
                    });
                    adb.setView(mview);
                    AlertDialog dialog = adb.create();
                    dialog.show();
                }
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hlevel!=0) {
                final AlertDialog.Builder adb = new AlertDialog.Builder(ViewQuestActivity.this);
                final View mview = getLayoutInflater().inflate(R.layout.cancel_quest, null);
                //final EditText cpass = (EditText) mview.findViewById(R.id.cpass);
                Button cconfirm = (Button) mview.findViewById(R.id.cconfirm);

                cconfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //if (cpass.getText().toString().equals("")) {

                            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            reference = FirebaseDatabase.getInstance().getReference("Quest").child(uid);
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("level", 0);
                            map.put("mission", "ไม่มีภารกิจ");
                            map.put("count", "0");
                            map.put("time", 0);
                            map.put("point", 0);
                            reference.updateChildren(map);
                            Toast.makeText(getApplication(), "Cancel Quest Success!", Toast.LENGTH_LONG).show();
                            finish();
                        //} else {
                            //Toast.makeText(getApplication(), "Trainer only!", Toast.LENGTH_LONG).show();
                        //}
                    }
                });
                adb.setView(mview);
                AlertDialog dialog = adb.create();
                dialog.show();
            }}
        });


    }



//    public void getPoints(final String uid) {
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Quest").child(uid);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Quest quest1 = dataSnapshot.getValue(Quest.class);
//                Qpoint = (quest1.getPoint());
//                //Qpoint = Qpoint/2;
//                //test.setText(String.valueOf(Qpoint));
//                getSum(uid);
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }

    public void getSum(String uid,int Qpoint, int Upoint){

                Sum = Qpoint+Upoint;

                Push(uid,Sum);

    }

    public void Push(String uid,int Sum){
        //test2.setText(String.valueOf(Sum));
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference1 = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("points",Sum);
        reference1.updateChildren(map);
        //test1.setText(String.valueOf(Sum));
    }

    public  void History(String uid,int Hlevel){
        if(Hlevel != 0) {

            referenceRef = FirebaseDatabase.getInstance().getReference("History").child(uid).child(String.valueOf(Hlevel));
            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("mission", Hmission);
            referenceRef.push().setValue(map3);
            //reference.setValue(map3);
        }

    }

    public void Setquest(String uid){
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Quest").child(uid);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("level", 0);
        map1.put("mission", "ไม่มีภารกิจ");
        map1.put("count", "0");
        map1.put("time", 0);
        map1.put("point", 0);
        reference.updateChildren(map1);
    }
}