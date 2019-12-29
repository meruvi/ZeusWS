package ws;

import businesslogic.PersonalSessionBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("servicePersonal")
public class PersonalWS {

    @EJB
    PersonalSessionBean personalSessionBean;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public String login(String strJson) {
        System.out.println("JSON" + strJson);
        return personalSessionBean.authentication(strJson);
    }
    
}
