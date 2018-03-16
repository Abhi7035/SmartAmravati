package com.example.ash.smartamravati.activity.user.dashboard.Complaints;


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

public class ComplaintRecyclerAdapter extends RecyclerView.Adapter<ComplaintRecyclerAdapter.ViewHolder>{

    public List<Complaint> complaint_list;
    public Context context;

    private FirebaseFirestore firebaseFirestore;

    public ComplaintRecyclerAdapter(List<Complaint> complaint_list){

        this.complaint_list = complaint_list;

    }

    @NonNull
    @Override
    public ComplaintRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_list_item, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintRecyclerAdapter.ViewHolder holder, int position) {

        String tittle_data = complaint_list.get(position).getSubject();
        holder.setTittleView(tittle_data);

        String name_data = complaint_list.get(position).getName();
        holder.setNameView(name_data);

        String mobile_data = complaint_list.get(position).getMobile();
        holder.setMobileView(mobile_data);

        String desc_data = complaint_list.get(position).getComplaint_Details();
        holder.setDescText(desc_data);

        String image_url = complaint_list.get(position).getImage_thumb();
        holder.setImageView(image_url);

        long millisecond = complaint_list.get(position).getTimestamp().getTime();
        String dateString = DateFormat.format("dd/MM/yyyy", new Date(millisecond)).toString();
        holder.setTime(dateString);
    }

    @Override
    public int getItemCount() {
       return complaint_list.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;

        private TextView tittleView,nameView,mobileView,descView;
        private ImageView imageView;
        private TextView ComplaintDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }


        public void setTittleView(String descText1) {

            tittleView = mView.findViewById(R.id.complaint_tittle);
            tittleView.setText(descText1);

        }

        public void setNameView(String descText2) {

            nameView = mView.findViewById(R.id.complaint_name);
            nameView.setText(descText2);

        }

        public void setMobileView(String descText3) {

            mobileView = mView.findViewById(R.id.complaint_mobile);
            mobileView.setText(descText3);

        }

        public void setDescText(String descText4) {

            descView = mView.findViewById(R.id.complaint_desc);
            descView.setText(descText4);

        }

        public void setImageView(String downloadUri) {

            imageView = mView.findViewById(R.id.complaint_image);

            Glide.with(context).load(downloadUri).into(imageView);

        }

        public void setTime(String date) {

            ComplaintDate = mView.findViewById(R.id.complaint_date);
            ComplaintDate.setText(date);

        }


    }
}
