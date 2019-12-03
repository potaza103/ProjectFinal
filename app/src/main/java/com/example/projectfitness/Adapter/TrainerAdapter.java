package com.example.projectfitness.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectfitness.AddQuestActivity;
import com.example.projectfitness.CommentsActivity;
import com.example.projectfitness.FitnessActivity;
import com.example.projectfitness.Fragments.ProfileFragment;
import com.example.projectfitness.HistoryQuestActivity;
import com.example.projectfitness.MainActivity;
import com.example.projectfitness.MenuActivity;
import com.example.projectfitness.Model.User;
import com.example.projectfitness.QuestActivity;
import com.example.projectfitness.R;
import com.example.projectfitness.StartActivity;
import com.example.projectfitness.Trainer1Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.AccessControlContext;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.ImageViewHolder>  {

    private Context mContext;
    private List<User> mUsers;
    private boolean isFragment;


    private FirebaseUser firebaseUser;
    private User user;

    public TrainerAdapter(Context mContext, List<User> mUsers, boolean isFragment) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.isFragment = isFragment;
    }

    @NonNull
    public TrainerAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new TrainerAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrainerAdapter.ImageViewHolder holder, final int position) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final User user = mUsers.get(position);

        holder.btn_follow.setVisibility(View.VISIBLE);
        isFollowing(user.getId(), holder.btn_follow);

        holder.btn_history.setVisibility(View.VISIBLE);
        isHistory(user.getId(), holder.btn_history);

        holder.username.setText(user.getUsername());
        holder.fullname.setText(user.getFullname());
        Glide.with(mContext).load(user.getImageurl()).into(holder.image_profile);

        if (user.getId().equals(firebaseUser.getUid())){
            holder.btn_follow.setVisibility(View.GONE);
            holder.btn_history.setVisibility(View.GONE);
        }
//        if (user.getId().equals(firebaseUser.getUid())){
//            holder.btn_history.setVisibility(View.GONE);
//        }

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isFragment) {
//                    SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS", MODE_PRIVATE).edit();
//                    editor.putString("profileid", user.getId());
//                    editor.apply();
//
//                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                            new ProfileFragment()).commit();
//                } else {
//                    Intent intent = new Intent(mContext,Trainer1Activity.class);
//                    intent.putExtra("publisherid", user.getId());
//                    mContext.startActivity(intent);
//                }
//            }
//        });

        holder.btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.btn_follow.getText().toString().equals("QUESTS")) {
                    Intent intent = new Intent(mContext, AddQuestActivity.class);
                    intent.putExtra("publisherid", user.getId());
                    mContext.startActivity(intent);

                    //addNotification(user.getId());
                } else {
                    Intent intent = new Intent(mContext, AddQuestActivity.class);
                    intent.putExtra("publisherid", user.getId());
                    mContext.startActivity(intent);
                }
            }

        });

        holder.btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.btn_history.getText().toString().equals("QUESTS")) {
                    Intent intent = new Intent(mContext, HistoryQuestActivity.class);
                    intent.putExtra("publisherid", user.getId());
                    mContext.startActivity(intent);

                    //addNotification(user.getId());
                } else {
                    Intent intent = new Intent(mContext, HistoryQuestActivity.class);
                    intent.putExtra("publisherid", user.getId());
                    mContext.startActivity(intent);
                }
            }

        });
    }



    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView username;
        public TextView fullname;
        public CircleImageView image_profile;
        public Button btn_follow;
        public Button btn_history;

        public ImageViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            fullname = itemView.findViewById(R.id.fullname);
            image_profile = itemView.findViewById(R.id.image_profile);
            btn_follow = itemView.findViewById(R.id.btn_follow);
            btn_history = itemView.findViewById(R.id.btn_history);
        }
    }

    private void isFollowing(final String userid, final Button button){

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("Follow").child(firebaseUser.getUid()).child("following");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(userid).exists()){
                    button.setText("QUESTS");
                } else{
                    button.setText("QUESTS");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void isHistory(final String userid, final Button button){

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference()
                .child("Follow").child(firebaseUser.getUid()).child("following");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(userid).exists()){
                    button.setText("HISTORY");
                } else{
                    button.setText("HISTORY");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

    

