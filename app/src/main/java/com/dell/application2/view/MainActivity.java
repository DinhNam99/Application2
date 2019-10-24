package com.dell.application2.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dell.application2.R;
import com.dell.application2.adapter.AdapterStatus;
import com.dell.application2.model.EventComment;
import com.dell.application2.model.EventLike;
import com.dell.application2.model.Image;
import com.dell.application2.model.Status;
import com.dell.application2.presenter.MainPresenter;
import com.google.android.material.navigation.NavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewInterface.MainView {

    //toolbar and navigation
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawermain)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationViewMain)
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    //recyclerview
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    AdapterStatus adapterStatus;
    ArrayList<Status> statusList;
    MainPresenter mainPresenter;

    @BindView(R.id.tvThoigian)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this);
        setUpDrawerlayout();
        setUpRecyclerView();
    }

    public void setUpRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainPresenter.displayData();


    }

    private void setUpDrawerlayout(){
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("");
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public void loadData(ArrayList<Status> statusList) {
        this.statusList = statusList;
        adapterStatus = new AdapterStatus(MainActivity.this,statusList);
        recyclerView.setAdapter(adapterStatus);
    }
    @Subscribe
    public void onEventComment(EventComment eventComment){
        int countComment = statusList.get(eventComment.getPos()).getNumComment()+eventComment.getCountComment();
        statusList.get(eventComment.getPos()).setNumComment(countComment);
        adapterStatus.notifyDataSetChanged();
        Log.e("EVENT",eventComment.getPos()+"");
    }
    @Subscribe
    public void onEventLike(EventLike eventLike){
        int likeCount = statusList.get(eventLike.getPos()).getNumLike()+eventLike.getCountLike();
        statusList.get(eventLike.getPos()).setNumLike(likeCount);
        statusList.get(eventLike.getPos()).setActiveLike(eventLike.isActiveLike());
        adapterStatus.notifyDataSetChanged();
    }
    @Subscribe
    public void onEventLikeItemImage(EventLike.EventLikeItemImage eventLikeItemImage){
        Image image = statusList.get(eventLikeItemImage.getPos()).getImageStatus().get(eventLikeItemImage.getPosItem());
        int likeCount = image.getNumLikeImage()+eventLikeItemImage.getCountLike();
        image.setNumLikeImage(likeCount);
        image.setActiveLike(eventLikeItemImage.isActiveLike());
    }
    @Subscribe
    public void onEventCommetItemImage(EventComment.EventCommentItemImage eventCommentItemImage){
        Image image = statusList.get(eventCommentItemImage.getPos()).getImageStatus().get(eventCommentItemImage.getPosItem());
        int commentCount = image.getNumCommentImage()+eventCommentItemImage.getCountComment();
        image.setNumCommentImage(commentCount);
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

    //helo
}
