package com.dell.application2.view;

import com.dell.application2.model.Status;

import java.util.ArrayList;

public class ViewInterface {
    public interface MainView{
        void loadData(ArrayList<Status> statusList);
    }
}
