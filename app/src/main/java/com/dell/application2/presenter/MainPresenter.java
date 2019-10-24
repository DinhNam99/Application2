package com.dell.application2.presenter;

import com.dell.application2.model.Image;
import com.dell.application2.model.Status;
import com.dell.application2.model.User;
import com.dell.application2.view.ViewInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainPresenter implements PresenterInteface {

    ViewInterface.MainView mainView;
    public MainPresenter(ViewInterface.MainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void displayData() {
        List<Image> list1 = new ArrayList<>();
        list1.add(new Image("https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg",2,1));
        List<Image> list2 = new ArrayList<>();
        list2.add(new Image("https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg",4,2));
        list2.add(new Image("https://interactive-examples.mdn.mozilla.net/media/examples/grapefruit-slice-332-332.jpg",1,3));
        List<Image> list3 = new ArrayList<>();
        list3.add(new Image("https://www.belightsoft.com/products/imagetricks/img/intro-video-poster@2x.jpg",10,9));
        list3.add(new Image("https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg",7,2));
        list3.add(new Image("https://interactive-examples.mdn.mozilla.net/media/examples/grapefruit-slice-332-332.jpg",3,5));
        List<Image> list4 = new ArrayList<>();
        list4.add(new Image("https://interactive-examples.mdn.mozilla.net/media/examples/grapefruit-slice-332-332.jpg",3,2));
        list4.add(new Image("https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg",4,3));
        list4.add(new Image("https://www.belightsoft.com/products/imagetricks/img/intro-video-poster@2x.jpg",2,3));
        list4.add(new Image("https://znews-photo.zadn.vn/w660/Uploaded/cqxrcajwp/2013_10_07/6ec45d099de507e8b51024a2e6f20a691.jpg",5,4));
        List<Image> list5 = new ArrayList<>();
        list5.add(new Image("https://interactive-examples.mdn.mozilla.net/media/examples/grapefruit-slice-332-332.jpg",7,3));
        list5.add(new Image("https://www.belightsoft.com/products/imagetricks/img/intro-video-poster@2x.jpg",2,4));
        list5.add(new Image("https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg",2,6));
        list5.add(new Image("https://znews-photo.zadn.vn/w660/Uploaded/cqxrcajwp/2013_10_07/6ec45d099de507e8b51024a2e6f20a691.jpg",4,5));
        list5.add(new Image("https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg",2,1));
        list5.add(new Image("https://znews-photo.zadn.vn/w660/Uploaded/cqxrcajwp/2013_10_07/6ec45d099de507e8b51024a2e6f20a691.jpg",2,3));

        ArrayList<Status>statusList = new ArrayList<>();
        statusList.add(new Status(new User(null,"A"),"Toi la A",list1,22,29,true, new Date()));
        statusList.add(new Status(new User(null,"B"),"Toi la B", list2,24,36,false,new Date()));
        statusList.add(new Status(new User(null,"C"),"Toi la C", list3,26,35,true,new Date()));
        statusList.add(new Status(new User(null,"D"),"Toi la D",list4,21,53,true,new Date()));
        statusList.add(new Status(new User(null,"E"),"Toi la E", list5,42,43,false,new Date()));

        mainView.loadData(statusList);
    }
}
