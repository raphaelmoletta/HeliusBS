package br.edu.utfpr.dainf.eex23.web.listener;

import br.edu.utfpr.dainf.eex23.web.Model;
import br.edu.utfpr.dainf.eex23.web.bean.Data;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raphael Zagonel Moletta <raphael.moletta@gmail.com>
 */
public class UDPThread implements Runnable {

    private boolean running = true;

    public enum STATUS {
        Listening, Reciving, Stopped
    };
    private STATUS status = STATUS.Stopped;

    @Override
    public void run() {
        try {
            DatagramPacket packet;
            JsonReader reader;
            byte data[] = new byte[1024];
            Gson gson = new Gson();
            while (running) {
                packet = new DatagramPacket(data, data.length);
                status = STATUS.Listening;
                Socket.get().receive(packet);
                status = STATUS.Reciving;
                System.out.println(new String(data));
                reader = new JsonReader(new StringReader(new String(data)));
                reader.setLenient(true);
                Model.getInstance().add((Data)gson.fromJson(reader, Data.class));
            }
        } catch (SocketException | UnknownHostException e) {
            Socket.close();
            Logger.getLogger(UDPThread.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Socket.close();
            Logger.getLogger(UDPThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Socket.close();
        }

    }

    public void stop() {
        running = false;
        Socket.close();
    }

    public STATUS getStatus() {
        return status;
    }
}
