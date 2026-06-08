public class Ingeniero {

    private int idIngeniero;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int idEmpleado;
    private int idUsuario;

    public Ingeniero() {
    }

    public Ingeniero(int idIngeniero, String nombre, String apellidoPaterno,
                    String apellidoMaterno, int idEmpleado, int idUsuario) {
        this.idIngeniero = idIngeniero;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idEmpleado = idEmpleado;
        this.idUsuario = idUsuario;
    }

    public int getIdIngeniero() {
        return idIngeniero;
    }

    public void setIdIngeniero(int idIngeniero) {
        this.idIngeniero = idIngeniero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return getNombreCompleto();
    }
}