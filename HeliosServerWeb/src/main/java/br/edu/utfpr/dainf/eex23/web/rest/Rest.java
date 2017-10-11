package br.edu.utfpr.dainf.eex23.web.rest;

import br.edu.utfpr.dainf.eex23.heliusbeans.InstantBean;
import br.edu.utfpr.dainf.eex23.web.Model;
import java.time.LocalDateTime;
import java.util.Map;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author rapha
 */
@Path("rest")
public class Rest {
    @Context
    UriInfo uriInfo;
    
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
    @Path("instant")
    @Produces(MediaType.APPLICATION_JSON)
    public InstantBean getInstant() {
        return Model.getInstance().getInstant();
        
    }
    
    @GET
    @Path("graphic")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<LocalDateTime, Double> getGraphic() {
        return Model.getInstance().getGraphic();
        
    }
    

    /**
     * PUT method for updating or creating an instance of Rest
     * @param content representation for the resource
     
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        System.out.println("Content: " + content);
    }*/
}
