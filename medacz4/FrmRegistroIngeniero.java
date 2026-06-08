import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrmRegistroIngeniero extends JFrame {

    private JTextField txtNombreCompleto;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmarPassword;
    private JTextField txtIdentificador;
    private JButton btnRegistrar;
    private JButton btnRegresar;

    public FrmRegistroIngeniero() {
        setTitle("Registro Ingeniero - MEDACZ");
        setSize(950, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel lblTitulo = Estilos.label("Registro Ingeniero", Estilos.SUBTITULO);
        lblTitulo.setBounds(320, 70, 340, 40);
        panel.add(lblTitulo);

        JLabel lblNombre = Estilos.label("<html>Nombre<br>completo</html>", Estilos.TEXTO);
        lblNombre.setBounds(170, 170, 220, 60);
        panel.add(lblNombre);

        txtNombreCompleto = Estilos.campo();
        txtNombreCompleto.setBounds(440, 185, 320, 35);
        panel.add(txtNombreCompleto);

        JLabel lblPassword = Estilos.label("Contraseña", Estilos.TEXTO);
        lblPassword.setBounds(170, 255, 220, 35);
        panel.add(lblPassword);

        txtPassword = Estilos.password();
        txtPassword.setBounds(440, 255, 320, 35);
        panel.add(txtPassword);

        JLabel lblConfirmar = Estilos.label("<html>Confirmar<br>contraseña</html>", Estilos.TEXTO);
        lblConfirmar.setBounds(170, 320, 220, 60);
        panel.add(lblConfirmar);

        txtConfirmarPassword = Estilos.password();
        txtConfirmarPassword.setBounds(440, 335, 320, 35);
        panel.add(txtConfirmarPassword);

        JLabel lblIdentificador = Estilos.label("Identificador", Estilos.TEXTO);
        lblIdentificador.setBounds(170, 420, 220, 35);
        panel.add(lblIdentificador);

        txtIdentificador = Estilos.campo();
        txtIdentificador.setBounds(440, 420, 320, 35);
        panel.add(txtIdentificador);

        btnRegistrar = Estilos.boton("Registrar");
        btnRegistrar.setBounds(265, 520, 180, 45);
        panel.add(btnRegistrar);

        btnRegresar = Estilos.boton("Regresar");
        btnRegresar.setBounds(505, 520, 180, 45);
        panel.add(btnRegresar);
    }

    public JTextField getTxtNombreCompleto() {
        return txtNombreCompleto;
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