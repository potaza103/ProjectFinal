package com.example.projectfitness;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RewardActivity extends AppCompatActivity {

    TextView Rpoint,test;
    Button button1 ,button2, button3, button4;
    int sum,reword1=100;
    int upoints,i;

    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reward");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        Rpoint = findViewById(R.id.Rpoint);
        test = findViewById(R.id.test);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Rpoint.setText(String.valueOf(user.getPoints()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder adb = new AlertDialog.Builder(RewardActivity.this);
                final View mview = getLayoutInflater().inflate(R.layout.comfirm_reward, null);
                final EditText passreward = (EditText) mview.findViewById(R.id.passreward);
                Button confirm = (Button) mview.findViewById(R.id.confirm);

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (passreward.getText().toString().equals("")) {
                            setReward1();
                        } else {
                            Toast.makeText(getApplication(), "Fitnees only!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                adb.setView(mview);
                AlertDialog dialog = adb.create();
                dialog.show();
            }
        });
    }


//        button1.setOnClickListener(new View.OnClickListener() {
//
//            final AlertDialog.Builder adb = new AlertDialog.Builder(RewardActivity.this);
//
//            public void onClick(View v) {
//                setReward1();
//                adb.setTitle("Reward");
//                adb.setMessage("Do you want to redeem the reward?");
//                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch(which) {
//
//                            case DialogInterface.BUTTON_POSITIVE:
//
//                                break;
//
//                            case DialogInterface.BUTTON_NEUTRAL:
//                                // Neutral/Cancel button clicked
//                                break;
////                        break;
//                            //test.setText(String.valueOf(sum));
//
//                        }
//                    }
//                };
//                adb.setPositiveButton("Yes", dialogClickListener);
//                adb.setNegativeButton("Cancel", dialogClickListener);
//                AlertDialog dialog = adb.create();
//                dialog.show();
//            }
//        });
//    }
//


    public void setReward1(){

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                upoints = (user.getPoints());
                if(upoints >= reword1) {
                    test.setText(String.valueOf(upoints-reword1));
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("points", upoints-reword1);
                    reference.updateChildren(map);
                    //push();
                    Toast.makeText(RewardActivity.this, "Reward redemption is complete!", Toast.LENGTH_LONG).show();
                    //Intent intent = new Intent(RewardActivity.this, FitnessActivity.class);
                    //startActivity(intent);
                    //System.exit(0);
                    }else {
                        Toast.makeText(getApplication(), "Your points is not enough!", Toast.LENGTH_LONG).show();
                    }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

//    public void push(){
//                sum = upoints - reword1;
//                test.setText(String.valueOf(sum));
//                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
//                HashMap<String, Object> map = new HashMap<>();
//                map.put("points", sum);
//                reference.updateChildren(map);
//
//
//        }

}
