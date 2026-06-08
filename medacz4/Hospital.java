public class Hospital {

    private int idHospital;
    private String nombreHospital;
    private String correo;
    private int idUsuario;

    public Hospital() {
    }

    public Hospital(int idHospital, String nombreHospital, String correo, int idUsuario) {
        this.idHospital = idHospital;
        this.nombreHospital = nombreHospital;
        this.correo = correo;
        this.idUsuario = idUsuario;
    }

    public int getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return nombreHospital;
    }
}