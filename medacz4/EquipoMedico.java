public class EquipoMedico {

    private int idEquipo;
    private String tipoEquipo;
    private String modelo;
    private String marca;
    private int numeroSerie;
    private String estado;
    private String ubicacion;
    private String ultimoMantenimiento;
    private String proximoMantenimiento;
    private int idHospital;
    private String nombreHospital;

    public EquipoMedico() {
    }

    public EquipoMedico(int idEquipo, String tipoEquipo, String modelo, String marca,
                        int numeroSerie, String estado, String ubicacion, int idHospital) {
        this.idEquipo = idEquipo;
        this.tipoEquipo = tipoEquipo;
        this.modelo = modelo;
        this.marca = marca;
        this.numeroSerie = numeroSerie;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.idHospital = idHospital;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUltimoMantenimiento() {
        return ultimoMantenimiento;
    }

    public void setUltimoMantenimiento(String ultimoMantenimiento) {
        this.ultimoMantenimiento = ultimoMantenimiento;
    }

    public String getProximoMantenimiento() {
        return proximoMantenimiento;
    }

    public void setProximoMantenimiento(String proximoMantenimiento) {
        this.proximoMantenimiento = proximoMantenimiento;
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

    @Override
    public String toString() {
        return tipoEquipo + " - Serie: " + numeroSerie;
    }
}