public class SolicitudServicio {

    private int idSolicitud;
    private String fecha;
    private String tipoServicio;
    private String falla;
    private String estado;
    private int idHospital;
    private int idEquipo;
    private int idIngeniero;
    private String fechaVisita;

    public SolicitudServicio() {
    }

    public SolicitudServicio(int idSolicitud, String fecha, String tipoServicio,
                             String falla, String estado, int idHospital, int idEquipo) {
        this.idSolicitud = idSolicitud;
        this.fecha = fecha;
        this.tipoServicio = tipoServicio;
        this.falla = falla;
        this.estado = estado;
        this.idHospital = idHospital;
        this.idEquipo = idEquipo;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdIngeniero() {
        return idIngeniero;
    }

    public void setIdIngeniero(int idIngeniero) {
        this.idIngeniero = idIngeniero;
    }

    public String getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(String fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    @Override
    public String toString() {
        return "Solicitud #" + idSolicitud + " - " + tipoServicio + " - " + estado;
    }
}