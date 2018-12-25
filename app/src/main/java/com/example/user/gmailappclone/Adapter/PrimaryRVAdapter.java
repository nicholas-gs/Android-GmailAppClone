package com.example.user.gmailappclone.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.gmailappclone.Model.Email;
import com.example.user.gmailappclone.R;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class PrimaryRVAdapter extends RecyclerView.Adapter<PrimaryRVAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Email> emails;

    int[] colors;
    int noOfEmails;

    //Constructor
    public PrimaryRVAdapter(Context context, ArrayList<Email> emails) {
        this.context = context;
        this.emails = emails;

        colors = context.getResources().getIntArray(R.array.mdcolor_400);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;
        private CircleImageView circularImageView;
        private TextView from, subject, message, timeStamp;
        private ImageView star;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.emailrow_linearlayout);
            circularImageView = itemView.findViewById(R.id.emailrow_imageview);
            from = itemView.findViewById(R.id.emailrow_from);
            subject = itemView.findViewById(R.id.emailrow_subject);
            message = itemView.findViewById(R.id.emailrow_message);
            timeStamp = itemView.findViewById(R.id.emailrow_timestamp);
            star = itemView.findViewById(R.id.emailrow_star);

            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.isSelected()){
                        v.setSelected(false);
                    }
                    else{
                        v.setSelected(true);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.email_row_layout, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Email currentEmail = emails.get(i);

        int emailId = currentEmail.getEmailId();
        boolean isRead = currentEmail.getIsRead();
        boolean isImportant = currentEmail.getIsImportant();
        String imageUrl = currentEmail.getImageUrl();
        String from = currentEmail.getFrom();
        String subject = currentEmail.getSubject();
        String message = currentEmail.getMessage();
        String timeStamp = currentEmail.getTimeStamp();

        // If isRead is true
        if (isRead) {
            myViewHolder.subject.setTypeface(Typeface.create(myViewHolder.subject.getTypeface()
                    , Typeface.NORMAL), Typeface.NORMAL);
            myViewHolder.subject.setText(subject);

            myViewHolder.from.setTypeface(Typeface.create(myViewHolder.from.getTypeface()
                    , Typeface.NORMAL), Typeface.NORMAL);
            myViewHolder.from.setText(from);

            myViewHolder.timeStamp.setTypeface(Typeface.create(myViewHolder.timeStamp.getTypeface()
                    , Typeface.NORMAL), Typeface.NORMAL);
            myViewHolder.timeStamp.setTextColor(ContextCompat.getColor(context, R.color.colorMessage));
            myViewHolder.timeStamp.setText(timeStamp);
        }
        // If isRead is false - bold & change color
        else {
            myViewHolder.subject.setTypeface(myViewHolder.subject.getTypeface(), Typeface.BOLD);
            myViewHolder.subject.setText(subject);

            myViewHolder.from.setTypeface(myViewHolder.from.getTypeface(), Typeface.BOLD);
            myViewHolder.from.setText(from);

            myViewHolder.timeStamp.setTypeface(myViewHolder.timeStamp.getTypeface(), Typeface.BOLD);
            myViewHolder.timeStamp.setTextColor(ContextCompat.getColor(context, R.color.colorTimestamp));
            myViewHolder.timeStamp.setText(timeStamp);
        }

        myViewHolder.message.setText(message);
        loadImage(imageUrl, myViewHolder);
        loadStar(isImportant, myViewHolder);
    }

    @Override
    public int getItemCount() {
        noOfEmails = emails.size();
        return noOfEmails;
    }

    private void loadImage(String imageUrl, MyViewHolder myViewHolder) {
        if (imageUrl == null) {
        } else {
            Glide.with(context).load(imageUrl)
                    .apply(new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background))
                    .into(myViewHolder.circularImageView);
        }
    }

    private void loadStar(boolean isImportant, MyViewHolder myViewHolder) {
        if (isImportant) {
            //myViewHolder.star.setImageResource(R.drawable.ic_star_yellow_24dp);
            myViewHolder.star.setSelected(true);
        } else {
            //myViewHolder.star.setImageResource(R.drawable.ic_star_border_grey_24dp);
            myViewHolder.star.setSelected(false);
        }
    }

}
