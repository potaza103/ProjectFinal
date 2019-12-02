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
    int sum,reward;
    int upoints,i;
    DatabaseReference reference,reference1,reference2;
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

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Rpoint.setText(String.valueOf(user.getPoints()));
                sum = user.getPoints();
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
                            reward=100;
                            setReward1(sum);
                            //test.setText(String.valueOf(sum));
                        } else {
                            Toast.makeText(getApplication(), "Fitnees only!", Toast.LENGTH_LONG).show();
                        }
                    }

                });
                adb.setView(mview);
                AlertDialog dialog = adb.create();
                dialog.dismiss();
                dialog.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
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
                            reward=150;
                            setReward1(sum);
                            //test.setText(String.valueOf(sum));
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

        button3.setOnClickListener(new View.OnClickListener() {
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
                            reward=200;
                            setReward1(sum);
                            //test.setText(String.valueOf(sum));
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

        button4.setOnClickListener(new View.OnClickListener() {
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
                            reward=250;
                            setReward1(sum);
                            //test.setText(String.valueOf(sum));
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


    public void setReward1(int sum){

        if(sum >= reward){
            sum = sum-reward;
            //test.setText(String.valueOf(sum));
            Push(sum);
            Toast.makeText(RewardActivity.this, "Reward redemption is complete!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplication(), "Your points is not enough!", Toast.LENGTH_LONG).show();
        }

//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        reference1 = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
//        reference1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(sum >= reword1) {
//                    test.setText(String.valueOf(upoints-reword1));
//                    sum = sum-reword1;
//                    Toast.makeText(RewardActivity.this, "Reward redemption is complete!", Toast.LENGTH_LONG).show();
//
//                    //Intent intent = new Intent(RewardActivity.this, FitnessActivity.class);
//                    //startActivity(intent);
//                    //System.exit(0);
//                   }else {
//                        Toast.makeText(getApplication(), "Your points is not enough!", Toast.LENGTH_LONG).show();
//                    }
//
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


    }

    public void Push(int sum){
                //test.setText(String.valueOf(sum));
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                reference2 = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
                HashMap<String, Object> map = new HashMap<>();
                map.put("points", sum);
                reference2.updateChildren(map);
        }

}
