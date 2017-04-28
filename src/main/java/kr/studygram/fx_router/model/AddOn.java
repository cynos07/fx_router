package kr.studygram.fx_router.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by cynos07 on 2017-04-26.
 */
public class AddOn extends RecursiveTreeObject<AddOn> {
    private IntegerProperty index;
    private StringProperty title;
    private StringProperty url;

    public AddOn(String title, String url) {
        this.title = new SimpleStringProperty(title);
        this.url = new SimpleStringProperty(url);
    }

    public int getIndex() {
        return index.get();
    }

    public IntegerProperty indexProperty() {
        return index;
    }

    public void setIndex(int index) {
        this.index.set(index);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getUrl() {
        return url.get();
    }

    public StringProperty urlProperty() {
        return url;
    }

    public void setUrl(String url) {
        this.url.set(url);
    }
}
