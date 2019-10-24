package com.dell.application2.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dell.application2.R;
import com.dell.application2.model.Image;
import com.dell.application2.model.Status;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterViewStatus extends RecyclerView.Adapter<AdapterViewStatus.ViewStatusHolder> {

    Status status;
    Context context;
    LayoutInflater inflater;
    ItemLikeClick itemLikeClick;

    public AdapterViewStatus(Context context,Status status){
        this.context = context;
        this.status = status;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewStatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_item_view_status1, parent, false);
        return new ViewStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewStatusHolder holder, int position) {
        Image image = status.getImageStatus().get(position);
        holder.profileImage.setImageResource(R.drawable.ic_account_circle_black_24dp);
        holder.listTextView1.get(0).setText(status.getUser().getName());
        holder.listTextView1.get(1).setText(status.getTime().toString());
        holder.listTextView1.get(2).setText(status.getTextStatus());
        holder.likeComment.get(0).setText(status.getNumLike()+" Lượt thích");
        holder.likeComment.get(1).setText(status.getNumComment()+" Trả lời");
        holder.likeCommentx.get(0).setText(image.getNumLikeImage()+" Lượt thích");
        holder.likeCommentx.get(1).setText(image.getNumCommentImage()+" Trả lời");
        Glide.with(context).load(image.getImageLink()).into(holder.imageStatus);
        if(position==0){
            holder.layout0.setVisibility(View.VISIBLE);
        }else{
            holder.layout0.setVisibility(View.GONE);
        }
        setUpLikeStatus(holder,status);
        setUplikeImageItem(holder,image,position);

    }

    public void setItemLikeClick(ItemLikeClick itemLikeClick){
        this.itemLikeClick = itemLikeClick;
    }
    @Override
    public int getItemCount() {
        return status.getImageStatus().size();
    }

    public void setUpLikeStatus(ViewStatusHolder holder,Status status){
        if(status.isActiveLike()){
            holder.likeComment.get(2).setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.like.setImageResource(R.drawable.like);
        }else{
            holder.likeComment.get(2).setTextColor(context.getResources().getColor(android.R.color.black));
            holder.like.setImageResource(R.drawable.unlike);
        }
        //like
        holder.layoutLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status.isActiveLike()){
                    itemLikeClick.onLikeClick(-1,false);
                    notifyDataSetChanged();
                }else{
                    itemLikeClick.onLikeClick(1,true);
                    notifyDataSetChanged();
                }
            }
        });
        holder.layoutComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Comment");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemLikeClick.onCommentClick(1);
                        notifyDataSetChanged();
                    }
                });
                AlertDialog alert1 = builder.create();
                alert1.show();
            }
        });
    }
    public void setUplikeImageItem(ViewStatusHolder holder,Image image,int pos){

        if(image.isActiveLike()){
            holder.likeCommentx.get(2).setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.likex.setImageResource(R.drawable.like);
        }else{
            holder.likeCommentx.get(2).setTextColor(context.getResources().getColor(android.R.color.black));
            holder.likex.setImageResource(R.drawable.unlike);
        }
        //like
        holder.layoutLikex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image.isActiveLike()){
                    itemLikeClick.onLikeItemImageClick(-1,pos,false);
                    notifyDataSetChanged();
                }else{
                    itemLikeClick.onLikeItemImageClick(1,pos,true);
                    notifyDataSetChanged();
                }
            }
        });
        holder.layoutCommentx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Comment");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemLikeClick.onCommentItemImageClick(1,pos);
                        notifyDataSetChanged();
                    }
                });
                AlertDialog alert1 = builder.create();
                alert1.show();
            }
        });
    }

    public interface ItemLikeClick{
        void onLikeClick(int countLike,boolean activeLike);
        void onCommentClick(int countComment);
        void onLikeItemImageClick(int countLike,int posItem, boolean activeLikeItem);
        void onCommentItemImageClick(int countComment, int posItem);
    }

    public class ViewStatusHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.profile_image) CircleImageView profileImage;
        @BindViews({R.id.tvUser,R.id.tvTgian,R.id.tvStatus}) List<TextView> listTextView1;
        @BindView(R.id.image11) ImageView imageStatus;
        @BindViews({R.id.tvLuotthich,R.id.tvTraloi,R.id.tvLike,R.id.tvComment}) List<TextView> likeComment;
        @BindView(R.id.like) ImageView like;
        @BindViews({R.id.tvLuotthichx,R.id.tvTraloix,R.id.tvLikex,R.id.tvCommentx}) List<TextView> likeCommentx;
        @BindView(R.id.likex) ImageView likex;
        @BindView(R.id.layoutLike) LinearLayout layoutLike;
        @BindView(R.id.layoutLikex) LinearLayout layoutLikex;
        @BindView(R.id.layoutComment) LinearLayout layoutComment;
        @BindView(R.id.layoutCommentx) LinearLayout layoutCommentx;
        @BindView(R.id.layout0) LinearLayout layout0;

        public ViewStatusHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
