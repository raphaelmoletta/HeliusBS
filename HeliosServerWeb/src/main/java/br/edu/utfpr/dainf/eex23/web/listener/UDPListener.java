package br.edu.utfpr.dainf.eex23.web.listener;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Raphael Zagonel Moletta <raphael.moletta@gmail.com>
 */
@WebServlet(urlPatterns="/udp", loadOnStartup=1)
public class UDPListener extends HttpServlet {
    private static final long serialVersionUID = 5729648808745937094L;
    private final static UDPThread uDPThread = new UDPThread();
    private static Thread thread = null;
    
    @Override
    public void init(ServletConfig sc) {
        if (thread == null) {
            thread = new Thread(uDPThread);
            thread.start();
        }
        System.out.println("CocoMeçou");
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        StringBuilder sb = new StringBuilder();
        sb.append("Status: ");
        sb.append(uDPThread.getStatus().toString());
        sb.append("\nState: ");
        sb.append(thread.getState().toString());
        
        response.getWriter().println(sb.toString());
    }

    @Override
    public void destroy() {
        //uDPThread.stop();
        System.out.println("Terminou");
    }
}