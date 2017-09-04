package br.edu.utfpr.dainf.eex23.web.bean;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author rapha
 */
public class Data {
    public enum STATUS {ok,error,empty};
    @SerializedName("s")
    private STATUS status = STATUS.empty;
    @SerializedName("v")
    private double voltage = 0;
    @SerializedName("c")
    private double current = 0;
    @SerializedName("p")
    private double pyranometer = 0;
    @SerializedName("t")
    private double thermometer = 0;

    public Data() {
        
    }
    
    public Data(STATUS status, double voltage, double current, double pyranometer, double thermometer) {
        this.status = status;
        this.voltage = voltage;
        this.current = current;
        this.pyranometer = pyranometer;
        this.thermometer = thermometer;
    }

    
    
    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getPyranometer() {
        return pyranometer;
    }

    public void setPyranometer(double pyranometer) {
        this.pyranometer = pyranometer;
    }

    public double getThermometer() {
        return thermometer;
    }

    public void setThermometer(double thermometer) {
        this.thermometer = thermometer;
    }
    
    
}
