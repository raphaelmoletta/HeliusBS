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
@WebServlet(urlPatterns = "/status", loadOnStartup = 1)
public class StatusServlet extends HttpServlet {

    private final static long serialVersionUID = 5729648808745937099L;
    private final static Threads threads = new Threads();

    @Override
    public void init(ServletConfig servletConfig) {
        System.out.println("XXXXXXX: " + servletConfig.getServletContext().getContextPath()); 
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        StringBuilder sb = new StringBuilder();
        sb.append("Status: ");
        String parameter = request.getParameter("action");
        System.out.println("Parameter:" + parameter);
        if(parameter != null && parameter.equalsIgnoreCase("start") && threads.getStatus() == Threads.STATUS.Stopped) {
            System.out.println("Starting...");
            threads.start();
            System.out.println("Started");
        } else if(parameter != null && parameter.equalsIgnoreCase("stop") && threads.getStatus() != Threads.STATUS.Stopped) {
            System.out.println("Stopping...");
            threads.stop();
            System.out.println("Stopped");
        }
        if (threads != null) {
            sb.append(threads.getStatus().toString());
        } else {
            sb.append("Not initialized");
        }

        response.getWriter().println(sb.toString());
    }
    
    @Override
    public void destroy() {
        
    }
}