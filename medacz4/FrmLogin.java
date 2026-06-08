import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrmLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JTextField txtIdentificador;
    private JButton btnIniciarSesion;
    private JButton btnRegresar;

    public FrmLogin() {
        setTitle("Inicio de sesión - MEDACZ");
        setSize(950, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel lblTitulo = Estilos.label("Inicio de sesión", Estilos.SUBTITULO);
        lblTitulo.setBounds(320, 55, 340, 45);
        panel.add(lblTitulo);

        JLabel lblSubtitulo = Estilos.label("MEDACZ", Estilos.TITULO);
        lblSubtitulo.setBounds(380, 120, 220, 45);
        panel.add(lblSubtitulo);

        JLabel lblUsuario = Estilos.label("Usuario", Estilos.TEXTO);
        lblUsuario.setBounds(210, 230, 180, 35);
        panel.add(lblUsuario);

        txtUsuario = Estilos.campo();
        txtUsuario.setBounds(430, 230, 300, 35);
        panel.add(txtUsuario);

        JLabel lblPassword = Estilos.label("Contraseña", Estilos.TEXTO);
        lblPassword.setBounds(210, 290, 180, 35);
        panel.add(lblPassword);

        txtPassword = Estilos.password();
        txtPassword.setBounds(430, 290, 300, 35);
        panel.add(txtPassword);

        JLabel lblIdentificador = Estilos.label("Identificador", Estilos.TEXTO);
        lblIdentificador.setBounds(210, 350, 180, 35);
        panel.add(lblIdentificador);

        txtIdentificador = Estilos.campo();
        txtIdentificador.setBounds(430, 350, 300, 35);
        panel.add(txtIdentificador);

        btnIniciarSesion = Estilos.boton("Iniciar sesión");
        btnIniciarSesion.setBounds(260, 455, 220, 45);
        panel.add(btnIniciarSesion);

        btnRegresar = Estilos.boton("Regresar");
        btnRegresar.setBounds(520, 455, 180, 45);
        panel.add(btnRegresar);
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JTextField getTxtIdentificador() {
        return txtIdentificador;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }
}