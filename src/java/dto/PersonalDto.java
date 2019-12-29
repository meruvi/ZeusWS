package dto;

public class PersonalDto {
    private int codPersonal;
    private int codCargo;
    private String nombresPersonal;
    private String apPaternoPersonal;
    private String apMaternoPersonal;
    private int codAreaEmpresa;

    public PersonalDto() {
        this.codPersonal = 0;
        this.codCargo = 0;
        this.nombresPersonal = "";
        this.apPaternoPersonal = "";
        this.apMaternoPersonal = "";
        this.codAreaEmpresa = 0;
    }

    public int getCodPersonal() {
        return codPersonal;
    }

    public void setCodPersonal(int codPersonal) {
        this.codPersonal = codPersonal;
    }

    public int getCodCargo() {
        return codCargo;
    }

    public void setCodCargo(int codCargo) {
        this.codCargo = codCargo;
    }

    public String getNombresPersonal() {
        return nombresPersonal;
    }

    public void setNombresPersonal(String nombresPersonal) {
        this.nombresPersonal = nombresPersonal;
    }

    public String getApPaternoPersonal() {
        return apPaternoPersonal;
    }

    public void setApPaternoPersonal(String apPaternoPersonal) {
        this.apPaternoPersonal = apPaternoPersonal;
    }

    public String getApMaternoPersonal() {
        return apMaternoPersonal;
    }

    public void setApMaternoPersonal(String apMaternoPersonal) {
        this.apMaternoPersonal = apMaternoPersonal;
    }

    public int getCodAreaEmpresa() {
        return codAreaEmpresa;
    }

    public void setCodAreaEmpresa(int codAreaEmpresa) {
        this.codAreaEmpresa = codAreaEmpresa;
    }
    
    
}
