package com.example.ash.smartamravati.activity.admin.dashboard.Notification;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ash.smartamravati.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.List;



public class NotificationRecyclerAdapter extends RecyclerView.Adapter<NotificationRecyclerAdapter.ViewHolder> {

    public List<Notification> notification_list;
    public Context context;

    private FirebaseFirestore firebaseFirestore;

    public NotificationRecyclerAdapter(List<Notification> notification_list){

        this.notification_list = notification_list;

    }

    @NonNull
    @Override
    public NotificationRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list_item, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationRecyclerAdapter.ViewHolder holder, int position) {

        String desc_data = notification_list.get(position).getDesc();
        holder.setDescText(desc_data);

        String image_url = notification_list.get(position).getImage_url();
        holder.setBlogImage(image_url);


        long millisecond = notification_list.get(position).getTimestamp().getTime();
        String dateString = DateFormat.format("dd/MM/yyyy", new Date(millisecond)).toString();
        holder.setTime(dateString);

    }

    @Override
    public int getItemCount() {
        return notification_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;

        private TextView descView;
        private ImageView blogImageView;
        private TextView blogDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDescText(String descText) {

            descView = mView.findViewById(R.id.complaint_desc);
            descView.setText(descText);

        }

        public void setBlogImage(String downloadUri) {

            blogImageView = mView.findViewById(R.id.complaint_image);

            Glide.with(context).load(downloadUri).into(blogImageView);

        }


        public void setTime(String date) {

            blogDate = mView.findViewById(R.id.blog_date);
            blogDate.setText(date);

        }


    }
}
