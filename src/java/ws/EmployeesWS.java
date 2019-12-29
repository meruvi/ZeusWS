package ws;

import businesslogic.EmployeeSessionBean;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/serviceEmployee")
public class EmployeesWS {
    
    @EJB
    EmployeeSessionBean employeeSessionBean;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listadoEmployee")
    public String listadoEmployee()
    {
      if (this.employeeSessionBean != null) {
        return this.employeeSessionBean.listadoEmployees();
      }

      return "";
    }
}
