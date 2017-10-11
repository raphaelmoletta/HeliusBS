package br.edu.utfpr.dainf.eex23.web.listeners;

import br.edu.utfpr.dainf.eex23.heliusbeans.StatusBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
@WebServlet(urlPatterns = "/status.json", loadOnStartup = 1, asyncSupported = true)
public class StatusServlet extends HttpServlet {
    private final static long serialVersionUID = 5729648808745937099L;
    @EJB
    private UDPThread thread = new UDPThread();

    @Override
    public void init(ServletConfig servletConfig) {
        
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger.getLogger(StatusServlet.class.getName()).info("Thread ID".concat(thread.toString()));
        response.setContentType("application/json");
        StatusBean statusBean = new StatusBean();
        statusBean.setRunning(Status.getInstance().isRunning());
        String parameter = request.getParameter("action");
        if (parameter != null && parameter.equalsIgnoreCase("start")) {
            Logger.getLogger(StatusServlet.class.getName()).info("Starting...");
            Status.getInstance().setActualStatus(StatusBean.STATUS.Initilizing);
            statusBean.setRunning(true);
            Status.getInstance().setRunning(true);
            thread.start();
            Logger.getLogger(StatusServlet.class.getName()).info("Started");
        } else if (parameter != null && parameter.equalsIgnoreCase("stop")) {
            Logger.getLogger(StatusServlet.class.getName()).info("Stopping...");
            Status.getInstance().setActualStatus(StatusBean.STATUS.Stopping);
            Status.getInstance().setRunning(false);
            Logger.getLogger(StatusServlet.class.getName()).info("Stopped");
        }
        if (thread != null) {
            statusBean.setUdpstatus(Status.getInstance().getActualStatus());
        } else {
            statusBean.setUdpstatus(StatusBean.STATUS.NotInitialized);
        }
        
        Gson gson = new Gson();

        response.getWriter().println(gson.toJson(statusBean, StatusBean.class));
    }

    @Override
    public void destroy() {

    }
/*
    private void executor() {
        try {
            InitialContext ctx = new InitialContext();
            ManagedExecutorService mes = (ManagedExecutorService) ctx.lookup("java:comp/env/concurrent/ThreadPool");
            mes.invokeAll(tasks)
        } catch (NamingException ex) {
            Logger.getLogger(StatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
}
