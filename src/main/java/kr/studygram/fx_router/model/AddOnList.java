package kr.studygram.fx_router.model;

import java.util.ArrayList;

/**
 * Created by cynos07 on 2017-04-26.
 */
public enum AddOnList {
    INSTANCE;

    private ArrayList<AddOn> addOnList;

    AddOnList() {
        addOnList= new ArrayList<>();
    }

    public void add(String title, String url)
    {
        AddOn addOn = new AddOn(title, url);
        addOnList.add(addOn);
    }

    public ArrayList<AddOn> getAddOnList() {
        return addOnList;
    }
}
