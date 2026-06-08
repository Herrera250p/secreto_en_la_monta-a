import javax.swing.JOptionPane;

public class ControladorRegistro {

    private FrmSeleccionRegistro vistaSeleccion;
    private FrmRegistroHospital vistaHospital;
    private FrmRegistroIngeniero vistaIngeniero;
    private RegistroDAO registroDAO;

    public ControladorRegistro(FrmSeleccionRegistro vistaSeleccion) {
        this.vistaSeleccion = vistaSeleccion;
        this.registroDAO = new RegistroDAO();

        this.vistaSeleccion.getBtnHospital().addActionListener(e -> abrirRegistroHospital());
        this.vistaSeleccion.getBtnIngeniero().addActionListener(e -> abrirRegistroIngeniero());
        this.vistaSeleccion.getBtnRegresar().addActionListener(e -> regresarInicioDesdeSeleccion());
    }

    public ControladorRegistro(FrmRegistroHospital vistaHospital) {
        this.vistaHospital = vistaHospital;
        this.registroDAO = new RegistroDAO();

        this.vistaHospital.getBtnRegistrar().addActionListener(e -> registrarHospital());
        this.vistaHospital.getBtnRegresar().addActionListener(e -> regresarSeleccionDesdeHospital());
    }

    public ControladorRegistro(FrmRegistroIngeniero vistaIngeniero) {
        this.vistaIngeniero = vistaIngeniero;
        this.registroDAO = new RegistroDAO();

        this.vistaIngeniero.getBtnRegistrar().addActionListener(e -> registrarIngeniero());
        this.vistaIngeniero.getBtnRegresar().addActionListener(e -> regresarSeleccionDesdeIngeniero());
    }

    private void abrirRegistroHospital() {
        FrmRegistroHospital registroHospital = new FrmRegistroHospital();
        new ControladorRegistro(registroHospital);
        registroHospital.setVisible(true);
        vistaSeleccion.dispose();
    }

    private void abrirRegistroIngeniero() {
        FrmRegistroIngeniero registroIngeniero = new FrmRegistroIngeniero();
        new ControladorRegistro(registroIngeniero);
        registroIngeniero.setVisible(true);
        vistaSeleccion.dispose();
    }

    private void registrarHospital() {
        String nombre = vistaHospital.getTxtNombreHospital().getText().trim();
        String direccion = vistaHospital.getTxtDireccion().getText().trim();
        String correo = vistaHospital.getTxtCorreo().getText().trim();
        String password = String.valueOf(vistaHospital.getTxtPassword().getPassword()).trim();
        String confirmar = String.valueOf(vistaHospital.getTxtConfirmarPassword().getPassword()).trim();
        String identificador = vistaHospital.getTxtIdentificador().getText().trim();

        if (Validaciones.vacio(nombre)
                || Validaciones.vacio(direccion)
                || Validaciones.vacio(correo)
                || Validaciones.vacio(password)
                || Validaciones.vacio(confirmar)
                || Validaciones.vacio(identificador)) {
            JOptionPane.showMessageDialog(vistaHospital, "Debes llenar todos los campos.");
            return;
        }

        if (!Validaciones.correoValido(correo)) {
            JOptionPane.showMessageDialog(vistaHospital, "El correo debe tener @ y punto.");
            return;
        }

        if (!password.equals(confirmar)) {
            JOptionPane.showMessageDialog(vistaHospital, "Las contraseñas no coinciden.");
            return;
        }

        try {
            registroDAO.registrarHospital(nombre, correo, password, identificador);
            JOptionPane.showMessageDialog(vistaHospital, "Hospital registrado correctamente.");

            FrmLogin login = new FrmLogin();
            new ControladorLogin(login);
            login.setVisible(true);
            vistaHospital.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    vistaHospital,
                    "No se pudo registrar el hospital.\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void registrarIngeniero() {
        String nombre = vistaIngeniero.getTxtNombreCompleto().getText().trim();
        String password = String.valueOf(vistaIngeniero.getTxtPassword().getPassword()).trim();
        String confirmar = String.valueOf(vistaIngeniero.getTxtConfirmarPassword().getPassword()).trim();
        String identificador = vistaIngeniero.getTxtIdentificador().getText().trim();

        if (Validaciones.vacio(nombre)
                || Validaciones.vacio(password)
                || Validaciones.vacio(confirmar)
                || Validaciones.vacio(identificador)) {
            JOptionPane.showMessageDialog(vistaIngeniero, "Debes llenar todos los campos.");
            return;
        }

        if (!password.equals(confirmar)) {
            JOptionPane.showMessageDialog(vistaIngeniero, "Las contraseñas no coinciden.");
            return;
        }

        try {
            registroDAO.registrarIngeniero(nombre, password, identificador);
            JOptionPane.showMessageDialog(vistaIngeniero, "Ingeniero registrado correctamente.");

            FrmLogin login = new FrmLogin();
            new ControladorLogin(login);
            login.setVisible(true);
            vistaIngeniero.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    vistaIngeniero,
                    "No se pudo registrar el ingeniero.\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void regresarInicioDesdeSeleccion() {
        FrmInicio inicio = new FrmInicio();
        new ControladorInicio(inicio);
        inicio.setVisible(true);
        vistaSeleccion.dispose();
    }

    private void regresarSeleccionDesdeHospital() {
        FrmSeleccionRegistro seleccion = new FrmSeleccionRegistro();
        new ControladorRegistro(seleccion);
        seleccion.setVisible(true);
        vistaHospital.dispose();
    }

    private void regresarSeleccionDesdeIngeniero() {
        FrmSeleccionRegistro seleccion = new FrmSeleccionRegistro();
        new ControladorRegistro(seleccion);
        seleccion.setVisible(true);
        vistaIngeniero.dispose();
    }
}