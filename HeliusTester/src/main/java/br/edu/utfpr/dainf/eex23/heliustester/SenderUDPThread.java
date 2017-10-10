/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.dainf.eex23.heliustester;

import br.edu.utfpr.dainf.eex23.heliusbeans.Data;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rapha
 */
public class SenderUDPThread implements Runnable {
    private boolean running = true;
    private DatagramSocket ds = null;
    private DatagramPacket dp = null;
    private byte pack[] = null;
    
    @Override
    public void run() {
        try {
            Data data;
            Gson gson = new Gson();        
            ds = new DatagramSocket(11001);
            ds.setBroadcast(true);
            ds.connect(InetAddress.getByName("255.255.255.255"), 11000);
            while(running) {
                data = fill();
                pack = gson.toJson(data, Data.class).getBytes();
                System.out.println(new String(pack));
                dp = new DatagramPacket(pack, pack.length);
                ds.send(dp);
                Thread.sleep(1000);
            }
        } catch (UnknownHostException | SocketException ex) {
            Logger.getLogger(Execute.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SenderUDPThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SenderUDPThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            stop();
        }
    }
    
    public void stop() {
        running = false;
        if(ds != null && !ds.isClosed()) {
            ds.close();
        }
    }
    
    private Data fill() {
        Data data = new Data();
        data.setCurrent((Math.random() * 100));
        data.setPyranometer((Math.random() * 100));
        data.setStatus(Data.STATUS.ok);
        data.setThermometer((Math.random() * 100));
        data.setVoltage((Math.random() * 100));
        return data;
    }
}
