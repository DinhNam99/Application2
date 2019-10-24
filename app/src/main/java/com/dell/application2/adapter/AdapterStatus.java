package com.dell.application2.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dell.application2.R;
import com.dell.application2.model.EventComment;
import com.dell.application2.model.EventLike;
import com.dell.application2.model.Image;
import com.dell.application2.model.Status;
import com.dell.application2.view.ActivityViewStatus;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterStatus extends RecyclerView.Adapter<AdapterStatus.StatusHolder> {

    ArrayList<Status> statusList;
    Context context;
    LayoutInflater inflater;
    public AdapterStatus(Context context, ArrayList<Status> statusList){
        this.context = context;
        this.statusList = statusList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_item, parent, false);
        return new StatusHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull StatusHolder holder, int position) {
        Status status = statusList.get(position);
        holder.profileImage.setImageResource(R.drawable.ic_account_circle_black_24dp);
        holder.listTextView1.get(0).setText(status.getUser().getName());
        holder.listTextView1.get(1).setText(status.getTime().toString());
        holder.listTextView1.get(2).setText(status.getTextStatus());
        setImage(holder,status);
        holder.likeComment.get(0).setText(status.getNumLike()+" Lượt thích");
        holder.likeComment.get(1).setText(status.getNumComment()+" Trả lời");

        if(status.isActiveLike()){
            holder.likeComment.get(2).setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.like.setImageResource(R.drawable.like);
        }else{
            holder.likeComment.get(2).setTextColor(context.getResources().getColor(android.R.color.black));
            holder.like.setImageResource(R.drawable.unlike);
        }

        //like
        holder.layoutLikeCommment.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status.isActiveLike()){
                    EventBus.getDefault().post(new EventLike(-1,position,false));
                    notifyDataSetChanged();

                }else{
                    EventBus.getDefault().post(new EventLike(1,position,true));
                    notifyDataSetChanged();
                }
            }
        });
        holder.layoutLikeCommment.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Comment");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EventBus.getDefault().post(new EventComment(1,position));
                    }
                });
                AlertDialog alert1 = builder.create();
                alert1.show();
            }
        });

        setClickLayoutImage(holder,status,position);
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }


    public void setImage(StatusHolder holder, Status status){
        List<Image> image = status.getImageStatus();
        List<LinearLayout> listLayoutImage = holder.listLayoutImage;
        if(image.size()==0){
            holder.layoutImage.setVisibility(View.GONE);
        }else if(image.size()==1){
            setUpLayoutImage(holder.layoutImage,listLayoutImage.get(0),listLayoutImage.get(1),listLayoutImage.get(2),listLayoutImage.get(3));
            Glide.with(context).load(image.get(0).getImageLink()).into(holder.imageStatus);
        }else if(image.size()==2){
            setUpLayoutImage(holder.layoutImage,listLayoutImage.get(1),listLayoutImage.get(0),listLayoutImage.get(2),listLayoutImage.get(3));
            for (int i = 0; i<holder.listIV2.size(); i++){
                Glide.with(context).load(image.get(i).getImageLink()).into(holder.listIV2.get(i));
            }
        }else if(image.size()==3){
            setUpLayoutImage(holder.layoutImage,listLayoutImage.get(2),listLayoutImage.get(1),listLayoutImage.get(0),listLayoutImage.get(3));
            for (int i = 0; i<holder.listIV3.size(); i++){
                Glide.with(context).load(image.get(i).getImageLink()).into(holder.listIV3.get(i));
            }
        }else if(image.size()>=4){
            setUpLayoutImage(holder.layoutImage,listLayoutImage.get(3),listLayoutImage.get(1),listLayoutImage.get(2),listLayoutImage.get(0));
            for (int i = 0; i<holder.listIV4.size(); i++){
                Glide.with(context).load(image.get(i).getImageLink()).into(holder.listIV4.get(i));
            }
            if(image.size() == 4){
                holder.layoutImages.setVisibility(View.GONE);
            }else{
                holder.layoutImages.setVisibility(View.VISIBLE);
                holder.tvNumImage.setText(status.getImageStatus().size()-4+"");
            }
        }
    }
    public void setClickLayoutImage(StatusHolder holder,Status status,int pos){
        if(status.getImageStatus().size()>1) {
            holder.layoutImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ActivityViewStatus.class);
                    intent.putExtra("STATUS", status);
                    intent.putExtra("pos",pos);
                    context.startActivity(intent);
                }
            });
        }

    }

    public void setUpLayoutImage(LinearLayout layoutMain, LinearLayout layout1, LinearLayout layout2, LinearLayout layout3, LinearLayout layout4){
        layoutMain.setVisibility(View.VISIBLE);
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        layout4.setVisibility(View.GONE);
    }

    public class StatusHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile_image) CircleImageView profileImage;
        @BindView(R.id.image11) ImageView imageStatus;
        @BindViews({R.id.image21,R.id.image22}) List<ImageView> listIV2;
        @BindViews({R.id.image31,R.id.image32,R.id.image33}) List<ImageView> listIV3;
        @BindViews({R.id.image41,R.id.image42,R.id.image43,R.id.image44}) List<ImageView> listIV4;
        @BindViews({R.id.tvUser,R.id.tvTgian,R.id.tvStatus}) List<TextView> listTextView1;
        @BindView(R.id.layoutImage) LinearLayout layoutImage;
        @BindViews({R.id.layout_image1,R.id.layout_image2,R.id.layout_image3,R.id.layout_image4}) List<LinearLayout> listLayoutImage;
        @BindViews({R.id.layoutLike,R.id.layoutComment}) List<LinearLayout> layoutLikeCommment;
        @BindView(R.id.manyImage) RelativeLayout layoutImages;
        @BindViews({R.id.tvLuotthich,R.id.tvTraloi,R.id.tvLike,R.id.tvComment}) List<TextView> likeComment;
        @BindView(R.id.like) ImageView like;
        @BindView(R.id.tvNumImage) TextView tvNumImage;
        public StatusHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
