package businesslogic;

import com.google.gson.Gson;
import entity.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import util.DAO;

@Stateless
public class EmployeeSessionBean {
    
    @Resource(lookup="jdbc/zeus")
    private DataSource dsc;
    private Connection con;
    
    public String listadoEmployees(){
        List<Employee> lista = new ArrayList();
        Gson gson = new Gson();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            //String sqlEmployee = "SELECT EmployeeID, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Photo, Notes, ReportsTo, PhotoPath FROM Employees";
            String sqlEmployee = "SELECT EmployeeID, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Photo, Notes, ReportsTo, PhotoPath FROM Employees where EmployeeID = 0";

            System.out.println(sqlEmployee);
            this.con = DAO.open(this.con, this.dsc);
            PreparedStatement stPersonal = this.con.prepareStatement(sqlEmployee);
            ResultSet rs = stPersonal.executeQuery();

            while (rs.next()) {

                Employee p = new Employee();
                p.setEmployeeId(rs.getInt("EmployeeID"));
                p.setLastName(rs.getString("LastName"));
                p.setFirstName(rs.getString("FirstName"));
                p.setTitle(rs.getString("Title"));
                p.setTitleOfCourtesy(rs.getString("TitleOfCourtesy"));
                p.setBirthDate(f.parse(rs.getString("BirthDate")));
                p.setHireDate(f.parse(rs.getString("HireDate")));
                p.setAddress(rs.getString("Address"));
                p.setCity(rs.getString("City"));
                p.setRegion(rs.getString("Region"));
                p.setPostalCode(rs.getString("PostalCode"));
                p.setCountry(rs.getString("Country"));
                p.setHomePhone(rs.getString("HomePhone"));
                p.setExtension(rs.getString("Extension"));
                //p.setPhoto(rs.getInt("Photo"));
                p.setNotes(rs.getString("Notes"));
                p.setReportsTo(rs.getInt("ReportsTo"));
                p.setPhotoPath(rs.getString("PhotoPath"));

                lista.add(p);
            }
            rs.close();
            stPersonal.close();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return gson.toJson(lista);
    }
}
