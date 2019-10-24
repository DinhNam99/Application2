package com.dell.application2.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dell.application2.R;
import com.dell.application2.adapter.AdapterViewStatus;
import com.dell.application2.model.EventComment;
import com.dell.application2.model.EventLike;
import com.dell.application2.model.Image;
import com.dell.application2.model.Status;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityViewStatus extends AppCompatActivity {

    //recyclerview
    @BindView(R.id.recyclerViewStatus)
    RecyclerView recyclerView;
    AdapterViewStatus adapterViewStatus;
    Status status = new Status();
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_status);
        ButterKnife.bind(this);
        setUpRecyclerView();
        EventBus eventBus = EventBus.getDefault();
    }
    public void setUpRecyclerView() {
        Intent intent = getIntent();
        status = (Status) intent.getSerializableExtra("STATUS");
        pos = intent.getIntExtra("pos",0);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterViewStatus = new AdapterViewStatus(ActivityViewStatus.this,status);
        recyclerView.setAdapter(adapterViewStatus);

        adapterViewStatus.setItemLikeClick(new AdapterViewStatus.ItemLikeClick() {
            @Override
            public void onLikeClick(int countLike,boolean activeLike) {
                EventBus.getDefault().post(new EventLike(countLike,pos,activeLike));
                adapterViewStatus.notifyDataSetChanged();
            }

            @Override
            public void onCommentClick(int countComment) {
                EventBus.getDefault().post(new EventComment(countComment,pos));
            }

            @Override
            public void onLikeItemImageClick(int countLike, int posItem, boolean activeLikeItem) {
                EventBus.getDefault().post(new EventLike.EventLikeItemImage(countLike,pos,posItem,activeLikeItem));
            }

            @Override
            public void onCommentItemImageClick(int countComment, int posItem) {
                EventBus.getDefault().post(new EventComment.EventCommentItemImage(countComment,pos,posItem));
            }
        });
    }

    @Subscribe
    public void onEventLike(EventLike eventLike){
        int likeCount = status.getNumLike()+eventLike.getCountLike();
        status.setNumLike(likeCount);
        status.setActiveLike(eventLike.isActiveLike());
        adapterViewStatus.notifyDataSetChanged();
    }
    @Subscribe
    public void onEventComment(EventComment eventComment){
        int countComment = status.getNumComment()+eventComment.getCountComment();
        status.setNumComment(countComment);
        adapterViewStatus.notifyDataSetChanged();
    }
    @Subscribe
    public void onEventLikeItemImage(EventLike.EventLikeItemImage eventLikeItemImage){
        Image image = status.getImageStatus().get(eventLikeItemImage.getPosItem());
        int likeCount = image.getNumLikeImage()+eventLikeItemImage.getCountLike();
        image.setNumLikeImage(likeCount);
        image.setActiveLike(eventLikeItemImage.isActiveLike());
        adapterViewStatus.notifyDataSetChanged();
    }
    @Subscribe
    public void onEventCommetItemImage(EventComment.EventCommentItemImage eventCommentItemImage){
        Image image = status.getImageStatus().get(eventCommentItemImage.getPosItem());
        int commentCount = image.getNumCommentImage()+eventCommentItemImage.getCountComment();
        image.setNumCommentImage(commentCount);
        adapterViewStatus.notifyDataSetChanged();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
