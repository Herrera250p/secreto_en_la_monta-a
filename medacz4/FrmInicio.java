import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrmInicio extends JFrame {

    private JButton btnIniciarSesion;
    private JButton btnRegistrarCuenta;

    public FrmInicio() {
        setTitle("MEDACZ");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel lblTitulo = Estilos.label("MEDACZ", Estilos.TITULO);
        lblTitulo.setBounds(370, 80, 200, 45);
        panel.add(lblTitulo);

        JLabel lblLinea1 = Estilos.label("Mantenimiento a", Estilos.SUBTITULO);
        lblLinea1.setBounds(315, 145, 310, 40);
        panel.add(lblLinea1);

        JLabel lblLinea2 = Estilos.label("equipo medico", Estilos.SUBTITULO);
        lblLinea2.setBounds(320, 195, 310, 40);
        panel.add(lblLinea2);

        btnIniciarSesion = Estilos.boton("Iniciar Sesión");
        btnIniciarSesion.setBounds(315, 300, 270, 50);
        panel.add(btnIniciarSesion);

        btnRegistrarCuenta = Estilos.boton("Registrar Cuenta");
        btnRegistrarCuenta.setBounds(315, 390, 270, 50);
        panel.add(btnRegistrarCuenta);
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public JButton getBtnRegistrarCuenta() {
        return btnRegistrarCuenta;
    }
}