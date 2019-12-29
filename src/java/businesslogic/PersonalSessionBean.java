package businesslogic;

import java.sql.Connection;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class PersonalSessionBean {
    
    @Resource(lookup = "jdbc/zeus")
    private DataSource dsc;
    private Connection con;
    
    
}
