package businesslogic;

import com.google.gson.Gson;
import dto.LoginDto;
import dto.PersonalDto;
import entity.Personal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public String listadoPersonal(int codRegional) {
        ArrayList<Personal> lista = new ArrayList();
        gson = new Gson();
        try
        {
            //String sqlPersonal = " select p.COD_PERSONAL,(select u.NOMBRE_USUARIO from  USUARIOS_MODULOS u where u.COD_MODULO=4 and u.COD_PERSONAL=p.COD_PERSONAL) NOMBRE_USUARIO,  (select u.CONTRASENA_USUARIO from  USUARIOS_MODULOS u where u.COD_MODULO=4 and u.COD_PERSONAL=p.COD_PERSONAL) CONTRASENA_USUARIO, p.AP_PATERNO_PERSONAL,p.AP_MATERNO_PERSONAL,p.NOMBRES_PERSONAL,p.NOMBRE_PILA,p.COD_AREA_EMPRESA,(select a.NOMBRE_AREA_EMPRESA from AREAS_EMPRESA a where a.COD_AREA_EMPRESA=p.COD_AREA_EMPRESA)  NOMBRE_AREA_EMPRESA  ,p.CODIGO_CARGO  from personal p where p.COD_AREA_EMPRESAVENTAS=" + codregional + " and p.COD_ESTADO_PERSONA=1   and (select u.NOMBRE_USUARIO from USUARIOS_MODULOS u where u.COD_MODULO = 4 and u.COD_PERSONAL = p.COD_PERSONAL)   is not null ";
            String sql = "select p.COD_PERSONAL,(select u.NOMBRE_USUARIO from  USUARIOS_MODULOS u where u.COD_MODULO=4 and u.COD_PERSONAL=p.COD_PERSONAL) NOMBRE_USUARIO,  (select u.CONTRASENA_USUARIO from  USUARIOS_MODULOS u where u.COD_MODULO=4 and u.COD_PERSONAL=p.COD_PERSONAL) CONTRASENA_USUARIO, p.AP_PATERNO_PERSONAL,p.AP_MATERNO_PERSONAL,p.NOMBRES_PERSONAL,p.COD_AREA_EMPRESA ,p.COD_CARGO  from personal p where p.COD_AREA_EMPRESA=" + codRegional + " and p.COD_ESTADO_PERSONA=1   and (select u.NOMBRE_USUARIO from USUARIOS_MODULOS u where u.COD_MODULO = 4 and u.COD_PERSONAL = p.COD_PERSONAL) is not null;";

            System.out.println(sql);
            this.con = DAO.open(this.con, this.dsc);
            PreparedStatement st = this.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next())
            {
              Personal p = new Personal();
              p.setCodPersonal(rs.getInt("COD_PERSONAL"));
              p.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
              p.setContraseniaUsuario(rs.getString("CONTRASENA_USUARIO"));
              p.setApPaternoPersonal(rs.getString("AP_PATERNO_PERSONAL"));
              p.setApMaternoPersonal(rs.getString("AP_MATERNO_PERSONAL"));
              p.setNombresPersonal(rs.getString("NOMBRES_PERSONAL"));
              p.setCodAreaEmpresa(codRegional);
              p.setCodCargo(rs.getInt("COD_CARGO"));

              if (p.getCodCargo() == 1120) {
                p.setCodCargo(1124);
              }

              if (p.getCodCargo() == 1297) {
                p.setCodCargo(1124);
              }


              if (p.getCodCargo() == 1206) {
                p.setCodCargo(1126);
              }

              if (p.getCodCargo() == 1230) {
                p.setCodCargo(1123);
              }

              if (p.getCodPersonal() == 1325)
                p.setCodCargo(1126);
              if (p.getCodPersonal() == 1143) {
                p.setCodCargo(1126);
              }

              lista.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
        System.out.println("RESPUESTA:---" + gson.toJson(lista));
        return gson.toJson(lista);
    }
    
}
