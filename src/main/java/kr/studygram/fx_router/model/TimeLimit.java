package kr.studygram.fx_router.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by cynos07 on 2017-04-24.
 */
public enum TimeLimit {
    INSTANCE;
    private StringProperty timeStart;
    private StringProperty timeEnd;

    TimeLimit(){ }

    public void init(String timeStart, String timeEnd) {
        this.timeStart = new SimpleStringProperty(timeStart);
        this.timeEnd = new SimpleStringProperty(timeEnd);
    }

    public String getTimeStart() {
        return timeStart.get();
    }

    public StringProperty timeStartProperty() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart.set(timeStart);
    }

    public String getTimeEnd() {
        return timeEnd.get();
    }

    public StringProperty timeEndProperty() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd.set(timeEnd);
    }

    public static TimeLimit getInstance() {
        return INSTANCE;
    }
}
