package br.edu.utfpr.dainf.eex23.web.rest;

import br.edu.utfpr.dainf.eex23.web.Model;
import br.edu.utfpr.dainf.eex23.web.bean.Data;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author rapha
 */
@Path("rest")
public class Rest {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Rest
     */
    public Rest() {
    }

    /**
     * Retrieves representation of an instance of br.edu.utfpr.dainf.eex23.web.rest.Rest
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        Gson gson = new Gson();
        Data d = Model.getInstance().getLast();
        if(d != null)
            return gson.toJson(d);
        return gson.toJson(new Data());
        
    }

    /**
     * PUT method for updating or creating an instance of Rest
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        System.out.println("Content: " + content);
    }
}
