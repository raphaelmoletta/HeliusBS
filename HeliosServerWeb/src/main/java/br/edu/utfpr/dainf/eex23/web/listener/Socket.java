package br.edu.utfpr.dainf.eex23.web.listener;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;

/**
 *
 * @author Raphael Zagonel Moletta <raphael.moletta@gmail.com>
 */
@Singleton
public class Socket {

    private static DatagramSocket socket = null;

    public static DatagramSocket get() {
        if (socket == null) {
            try {
                socket = new DatagramSocket(11000, InetAddress.getByName("0.0.0.0"));
            } catch (UnknownHostException | SocketException ex) {
                Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return socket;
    }

    public static void close() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
