package kr.studygram.fx_router.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import kr.studygram.fx_router.MainApp;

/**
 * Created by cynos07 on 2017-04-18.
 */
public enum GuestNetwork {
    INSTANCE;

    private DoubleProperty frequency;
    private StringProperty ssid;
    private StringProperty password;
    private MainApp application;

    public void init(Double frequency, String ssid, String password)
    {
        this.frequency = new SimpleDoubleProperty(frequency);
        this.ssid = new SimpleStringProperty(ssid);
        this.password = new SimpleStringProperty(password);
    }
    GuestNetwork() {    }

    public static GuestNetwork getInstance() {
        return INSTANCE;
    }

    public double getFrequency() {
        return frequency.get();
    }

    public DoubleProperty frequencyProperty() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency.set(frequency);
    }

    public String getSsid() {
        return ssid.get();
    }

    public StringProperty ssidProperty() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid.set(ssid);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setApp(MainApp application) {
        this.application = application;
    }
}