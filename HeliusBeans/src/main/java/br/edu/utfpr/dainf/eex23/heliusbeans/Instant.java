package br.edu.utfpr.dainf.eex23.heliusbeans;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author Raphael Zagonel Moletta <raphael.moletta@gmail.com>
 */
public class Instant implements Serializable {
    private static final long serialVersionUID = -6946324531882801415L;
    
    @SerializedName("temperature")
    private double temperature;
    @SerializedName("efficiency")
    private double efficiency;
    @SerializedName("solarIncidence")
    private double solarIncidence;
    @SerializedName("economy")
    private double economy;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public double getSolarIncidence() {
        return solarIncidence;
    }

    public void setSolarIncidence(double solarIncidence) {
        this.solarIncidence = solarIncidence;
    }

    public double getEconomy() {
        return economy;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }   
}