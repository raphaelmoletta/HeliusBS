package br.edu.utfpr.dainf.eex23.heliusbeans;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author Raphael Zagonel Moletta <raphael.moletta@gmail.com>
 */
public class StatusBean implements Serializable {
    private static final long serialVersionUID = 7670517157482636603L;
    public enum STATUS {Listening, Reciving, Stopped, Error, Initilizing, Stopping, NotInitialized};
    
    @SerializedName("udprunning")
    private boolean running = false;
    @SerializedName("udpstatus")
    private STATUS udpstatus = STATUS.NotInitialized;

    public STATUS getUdpstatus() {
        return udpstatus;
    }

    public void setUdpstatus(STATUS udpstatus) {
        this.udpstatus = udpstatus;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
