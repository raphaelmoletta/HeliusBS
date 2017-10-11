package br.edu.utfpr.dainf.eex23.web.listeners;

import br.edu.utfpr.dainf.eex23.heliusbeans.DataBean;
import br.edu.utfpr.dainf.eex23.heliusbeans.StatusBean.STATUS;
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

    @Asynchronous
    public void start() {
        if (socket == null) {
            try {
                socket = new DatagramSocket(11000, InetAddress.getByName("0.0.0.0"));
                DatagramPacket packet;
                JsonReader reader;
                byte data[] = new byte[1024];
                Gson gson = new Gson();
                while (Status.getInstance().isRunning()) {
                    packet = new DatagramPacket(data, data.length);
                    Status.getInstance().setActualStatus(STATUS.Listening);
                    socket.receive(packet);
                    Status.getInstance().setActualStatus(STATUS.Reciving);
                    System.out.println(new String(data));
                    reader = new JsonReader(new StringReader(new String(data)));
                    reader.setLenient(true);
                    Model.getInstance().add((DataBean) gson.fromJson(reader, DataBean.class));
                }
            } catch (UnknownHostException ex) {
                Status.getInstance().setActualStatus(STATUS.Error);
                Logger.getLogger(UDPThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException se) {
                Status.getInstance().setActualStatus(STATUS.Stopped);
            } catch (IOException ex) {
                Status.getInstance().setActualStatus(STATUS.Error);
                Logger.getLogger(UDPThread.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (!socket.isClosed()) {
                    Status.getInstance().setActualStatus(STATUS.Stopped);
                    socket.close();
                }
            }
        }
    }
}
