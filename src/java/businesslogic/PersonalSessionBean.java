package businesslogic;

import com.google.gson.Gson;
import dto.LoginDto;
import dto.PersonalDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import util.DAO;

@Stateless
public class PersonalSessionBean {
    
    @Resource(lookup = "jdbc/zeus")
    private DataSource dsc;
    private Connection con;
    
    private Gson gson;
    private LoginDto loginDto;

    public String authentication(String strJson) {
        System.out.println("JSON" + strJson);
        gson = new Gson();
        loginDto = gson.fromJson(strJson, LoginDto.class);
        PersonalDto p = new PersonalDto();
        
        try{
            String sql = "select COD_PERSONAL from USUARIOS_MODULOS WHERE NOMBRE_USUARIO = '" + loginDto.getUserName() + "' AND CONTRASENA_USUARIO = '" + loginDto.getPassword() + "' AND COD_MODULO = 4";
            System.out.println(sql);
            
            this.con = DAO.open(this.con, this.dsc);
            PreparedStatement st = this.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                int codPersonal = rs.getInt("COD_PERSONAL");
                sql = "select COD_PERSONAL, COD_CARGO, NOMBRES_PERSONAL, AP_PATERNO_PERSONAL, AP_MATERNO_PERSONAL, COD_AREA_EMPRESA FROM PERSONAL WHERE COD_PERSONAL = " + codPersonal;
                System.out.println(sql);

                st = this.con.prepareStatement(sql);
                rs = st.executeQuery();

                if (rs.next()) {
                    p.setCodPersonal(rs.getInt("COD_PERSONAL"));
                    p.setCodCargo(rs.getInt("COD_CARGO"));
                    p.setNombresPersonal(rs.getString("NOMBRES_PERSONAL"));
                    p.setApPaternoPersonal(rs.getString("AP_PATERNO_PERSONAL"));
                    p.setApMaternoPersonal(rs.getString("AP_MATERNO_PERSONAL"));
                    p.setCodAreaEmpresa(rs.getInt("COD_AREA_EMPRESA"));
                }
            }
            rs.close();
            st.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return gson.toJson(p);
    }
    
}
