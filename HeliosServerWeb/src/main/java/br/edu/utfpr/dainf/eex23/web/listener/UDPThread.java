package br.edu.utfpr.dainf.eex23.web.listener;

import br.edu.utfpr.dainf.eex23.heliusbeans.Data;
import br.edu.utfpr.dainf.eex23.web.Model;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader; 
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;

/**
 *
 * @author rapha
 */
@Singleton
public class UDPThread {

    private DatagramSocket socket = null;
    private volatile boolean running = true;
    public enum STATUS {Listening, Reciving, Stopped, Error};

    private STATUS status = STATUS.Stopped;

    @Asynchronous
    public void start() {
        if (socket == null) {
            try {
                socket = new DatagramSocket(11000, InetAddress.getByName("0.0.0.0"));
                DatagramPacket packet;
                JsonReader reader;
                byte data[] = new byte[1024];
                Gson gson = new Gson();
                while (running) {
                    packet = new DatagramPacket(data, data.length);
                    status = STATUS.Listening;
                    socket.receive(packet);
                    status = STATUS.Reciving;
                    System.out.println(new String(data));
                    reader = new JsonReader(new StringReader(new String(data)));
                    reader.setLenient(true);
                    Model.getInstance().add((Data) gson.fromJson(reader, Data.class));
                }
            } catch (UnknownHostException ex) {
                status = STATUS.Error;
                Logger.getLogger(UDPThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException se) {
                status = STATUS.Stopped;
            } catch (IOException ex) {
                status = STATUS.Error;
                Logger.getLogger(UDPThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stop() {
        running = false;
        status = STATUS.Stopped;
        if(!socket.isClosed()) {
            socket.close();
        }
    }

    public STATUS getStatus() {
        return status;
    }
}