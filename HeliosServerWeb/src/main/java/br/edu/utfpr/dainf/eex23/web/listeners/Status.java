package br.edu.utfpr.dainf.eex23.web.listeners;

import br.edu.utfpr.dainf.eex23.heliusbeans.StatusBean.STATUS;

/**
 *
 * @author Raphael Zagonel Moletta <raphael.moletta@gmail.com>
 */
public class Status {
    private boolean running = true;
    private STATUS actualStatus = STATUS.Stopped;
    
    
    private static Status status = null;
    
    
    private Status() {
        //leaved blank
    }
    
    public synchronized static Status getInstance() {
        if(status == null) {
            status = new Status();
        }
        return status;
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void setRunning(boolean running) {
        this.running = running;
    }

    public synchronized STATUS getActualStatus() {
        return actualStatus;
    }

    public synchronized void setActualStatus(STATUS actualStatus) {
        this.actualStatus = actualStatus;
    }
    
    
    
    
}
