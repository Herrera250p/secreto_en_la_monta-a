import javax.swing.JOptionPane;

public class ControladorInicio {

    private FrmInicio vista;

    public ControladorInicio(FrmInicio vista) {
        this.vista = vista;

        this.vista.getBtnIniciarSesion().addActionListener(e -> abrirLogin());
        this.vista.getBtnRegistrarCuenta().addActionListener(e -> abrirSeleccionRegistro());
    }

    private void abrirLogin() {
        FrmLogin login = new FrmLogin();
        new ControladorLogin(login);
        login.setVisible(true);
        vista.dispose();
    }

    private void abrirSeleccionRegistro() {
        FrmSeleccionRegistro seleccion = new FrmSeleccionRegistro();
        new ControladorRegistro(seleccion);
        seleccion.setVisible(true);
        vista.dispose();
    }
}