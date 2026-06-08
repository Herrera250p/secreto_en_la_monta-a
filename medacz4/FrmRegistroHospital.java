import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrmRegistroHospital extends JFrame {

    private JTextField txtNombreHospital;
    private JTextField txtDireccion;
    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmarPassword;
    private JTextField txtIdentificador;
    private JButton btnRegistrar;
    private JButton btnRegresar;

    public FrmRegistroHospital() {
        setTitle("Registro Hospital - MEDACZ");
        setSize(1050, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel lblTitulo = Estilos.label("Registro del cliente (Hospital)", Estilos.TEXTO);
        lblTitulo.setBounds(340, 35, 430, 35);
        panel.add(lblTitulo);

        JLabel lblRegistro = Estilos.label("Registro", Estilos.SUBTITULO);
        lblRegistro.setBounds(445, 90, 180, 40);
        panel.add(lblRegistro);

        JLabel lblNombre = Estilos.label("<html>Nombre del<br>hospital</html>", Estilos.TEXTO);
        lblNombre.setBounds(180, 165, 230, 60);
        panel.add(lblNombre);

        txtNombreHospital = Estilos.campo();
        txtNombreHospital.setBounds(470, 175, 340, 35);
        panel.add(txtNombreHospital);

        JLabel lblDireccion = Estilos.label("Dirección", Estilos.TEXTO);
        lblDireccion.setBounds(180, 230, 230, 35);
        panel.add(lblDireccion);

        txtDireccion = Estilos.campo();
        txtDireccion.setBounds(470, 230, 340, 35);
        panel.add(txtDireccion);

        JLabel lblCorreo = Estilos.label("<html>Correo<br>electronico</html>", Estilos.TEXTO);
        lblCorreo.setBounds(180, 285, 230, 60);
        panel.add(lblCorreo);

        txtCorreo = Estilos.campo();
        txtCorreo.setBounds(470, 300, 340, 35);
        panel.add(txtCorreo);

        JLabel lblPassword = Estilos.label("Contraseña", Estilos.TEXTO);
        lblPassword.setBounds(180, 365, 230, 35);
        panel.add(lblPassword);

        txtPassword = Estilos.password();
        txtPassword.setBounds(470, 365, 340, 35);
        panel.add(txtPassword);

        JLabel lblConfirmar = Estilos.label("<html>Confirmar<br>contraseña</html>", Estilos.TEXTO);
        lblConfirmar.setBounds(180, 420, 230, 60);
        panel.add(lblConfirmar);

        txtConfirmarPassword = Estilos.password();
        txtConfirmarPassword.setBounds(470, 435, 340, 35);
        panel.add(txtConfirmarPassword);

        JLabel lblIdentificador = Estilos.label("<html>Identificador de<br>seguridad</html>", Estilos.TEXTO);
        lblIdentificador.setBounds(180, 495, 260, 60);
        panel.add(lblIdentificador);

        txtIdentificador = Estilos.campo();
        txtIdentificador.setBounds(470, 510, 340, 35);
        panel.add(txtIdentificador);

        btnRegistrar = Estilos.boton("Registrar");
        btnRegistrar.setBounds(310, 590, 180, 45);
        panel.add(btnRegistrar);

        btnRegresar = Estilos.boton("Regresar");
        btnRegresar.setBounds(560, 590, 180, 45);
        panel.add(btnRegresar);
    }

    public JTextField getTxtNombreHospital() {
        return txtNombreHospital;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JPasswordField getTxtConfirmarPassword() {
        return txtConfirmarPassword;
    }

    public JTextField getTxtIdentificador() {
        return txtIdentificador;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }
}