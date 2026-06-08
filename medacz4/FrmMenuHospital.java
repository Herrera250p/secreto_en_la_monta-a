import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrmMenuHospital extends JFrame {

    private Usuario usuario;
    private JButton btnSolicitudes;
    private JButton btnEstadoSolicitud;
    private JButton btnEquiposMedicos;
    private JButton btnCerrarSesion;

    public FrmMenuHospital(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Menú Hospital - MEDACZ");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel lblTitulo = Estilos.label("Menú Hospital", Estilos.SUBTITULO);
        lblTitulo.setBounds(430, 40, 300, 40);
        panel.add(lblTitulo);

        JLabel lblUsuario = Estilos.label("Usuario: " + usuario.getNombreUsuario(), Estilos.TEXTO);
        lblUsuario.setBounds(40, 100, 500, 35);
        panel.add(lblUsuario);

        btnSolicitudes = Estilos.boton("<html>Solicitudes<br>de servicio</html>");
        btnSolicitudes.setBounds(60, 180, 220, 80);
        panel.add(btnSolicitudes);

        btnEstadoSolicitud = Estilos.boton("<html>Estado<br>Solicitud</html>");
        btnEstadoSolicitud.setBounds(60, 290, 220, 80);
        panel.add(btnEstadoSolicitud);

        btnEquiposMedicos = Estilos.boton("<html>Equipos<br>Medicos</html>");
        btnEquiposMedicos.setBounds(60, 400, 220, 80);
        panel.add(btnEquiposMedicos);

        btnCerrarSesion = Estilos.boton("Cerrar Sesion");
        btnCerrarSesion.setBounds(60, 530, 220, 55);
        panel.add(btnCerrarSesion);

        JLabel lblInfo = Estilos.label("<html>Bienvenido al sistema Medacz.<br>"
                + "Aquí podrás solicitar servicio, consultar estado y revisar tus equipos.</html>", Estilos.TEXTO);
        lblInfo.setBounds(360, 220, 650, 120);
        panel.add(lblInfo);

        btnSolicitudes.addActionListener(e -> {
            FrmSolicitarServicio frm = new FrmSolicitarServicio(usuario);
            frm.setVisible(true);
        });

        btnEstadoSolicitud.addActionListener(e -> {
            FrmEstadoSolicitud frm = new FrmEstadoSolicitud(usuario);
            frm.setVisible(true);
        });

        btnEquiposMedicos.addActionListener(e -> {
            FrmCatalogoEquipos frm = new FrmCatalogoEquipos(usuario);
            frm.setVisible(true);
        });

        btnCerrarSesion.addActionListener(e -> cerrarSesion());
    }

    private void cerrarSesion() {
        int opcion = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "¿Estas seguro de querer cerrar sesión?",
                "Advertencia",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (opcion == javax.swing.JOptionPane.YES_OPTION) {
            FrmInicio inicio = new FrmInicio();
            new ControladorInicio(inicio);
            inicio.setVisible(true);
            dispose();
        }
    }
}