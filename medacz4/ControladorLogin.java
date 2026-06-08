import javax.swing.JOptionPane;

public class ControladorLogin {

    private FrmLogin vista;
    private UsuarioDAO usuarioDAO;

    public ControladorLogin(FrmLogin vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();

        this.vista.getBtnIniciarSesion().addActionListener(e -> iniciarSesion());
        this.vista.getBtnRegresar().addActionListener(e -> regresarInicio());
    }

    private void iniciarSesion() {
        String nombre = vista.getTxtUsuario().getText().trim();
        String password = String.valueOf(vista.getTxtPassword().getPassword()).trim();
        String identificador = vista.getTxtIdentificador().getText().trim();

        if (Validaciones.vacio(nombre) || Validaciones.vacio(password)) {
            JOptionPane.showMessageDialog(vista, "Debes escribir usuario y contraseña.");
            return;
        }

        Usuario usuario = usuarioDAO.login(nombre, password, identificador);

        if (usuario == null) {
            JOptionPane.showMessageDialog(vista, "Usuario, contraseña o identificador incorrecto.");
            return;
        }

        if (usuario.getRol() == null || usuario.getRol().equals("Sin rol")) {
            JOptionPane.showMessageDialog(vista, "El usuario existe, pero no tiene rol asignado.");
            return;
        }

        abrirMenu(usuario);
    }

    private void abrirMenu(Usuario usuario) {
        switch (usuario.getRol()) {
            case "Administrador":
                FrmMenuAdministrador menuAdmin = new FrmMenuAdministrador(usuario);
                menuAdmin.setVisible(true);
                vista.dispose();
                break;

            case "Hospital":
                FrmMenuHospital menuHospital = new FrmMenuHospital(usuario);
                menuHospital.setVisible(true);
                vista.dispose();
                break;

            case "Ingeniero":
                FrmMenuIngeniero menuIngeniero = new FrmMenuIngeniero(usuario);
                menuIngeniero.setVisible(true);
                vista.dispose();
                break;

            default:
                JOptionPane.showMessageDialog(vista, "Rol no reconocido: " + usuario.getRol());
                break;
        }
    }

    private void regresarInicio() {
        FrmInicio inicio = new FrmInicio();
        new ControladorInicio(inicio);
        inicio.setVisible(true);
        vista.dispose();
    }
}