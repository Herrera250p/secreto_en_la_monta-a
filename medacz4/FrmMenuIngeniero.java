import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrmMenuIngeniero extends JFrame {

    private Usuario usuario;
    private JButton btnSolicitudes;
    private JButton btnChecklist;
    private JButton btnCerrarSesion;

    public FrmMenuIngeniero(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Menú Ingeniero - MEDACZ");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel lblTitulo = Estilos.label("Menú Ingeniero", Estilos.SUBTITULO);
        lblTitulo.setBounds(410, 40, 320, 40);
        panel.add(lblTitulo);

        JLabel lblUsuario = Estilos.label("Usuario: " + usuario.getNombreUsuario(), Estilos.TEXTO);
        lblUsuario.setBounds(40, 100, 500, 35);
        panel.add(lblUsuario);

        btnSolicitudes = Estilos.boton("<html>Solicitudes<br>de servicio</html>");
        btnSolicitudes.setBounds(60, 190, 220, 80);
        panel.add(btnSolicitudes);

        btnChecklist = Estilos.boton("Checklist");
        btnChecklist.setBounds(60, 320, 220, 70);
        panel.add(btnChecklist);

        btnCerrarSesion = Estilos.boton("Cerrar Sesion");
        btnCerrarSesion.setBounds(60, 530, 220, 55);
        panel.add(btnCerrarSesion);

        JLabel lblInfo = Estilos.label("<html>En este menú el ingeniero puede consultar solicitudes,<br>"
                + "dar seguimiento, registrar mantenimiento y llenar checklist.</html>", Estilos.TEXTO);
        lblInfo.setBounds(360, 230, 650, 120);
        panel.add(lblInfo);

        btnSolicitudes.addActionListener(e -> {
            FrmSolicitudesIngeniero frm = new FrmSolicitudesIngeniero(usuario);
            frm.setVisible(true);
        });

        btnChecklist.addActionListener(e -> {
            FrmChecklistIngeniero frm = new FrmChecklistIngeniero(usuario);
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