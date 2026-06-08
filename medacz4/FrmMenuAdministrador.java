import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrmMenuAdministrador extends JFrame {

    private Usuario usuario;
    private JButton btnEquiposMedicos;
    private JButton btnPlantillasChecklist;
    private JButton btnCerrarSesion;

    public FrmMenuAdministrador(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Menú Administrador - MEDACZ");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel lblTitulo = Estilos.label("Menú Administrador", Estilos.SUBTITULO);
        lblTitulo.setBounds(370, 40, 400, 40);
        panel.add(lblTitulo);

        JLabel lblUsuario = Estilos.label("Usuario: " + usuario.getNombreUsuario(), Estilos.TEXTO);
        lblUsuario.setBounds(40, 100, 500, 35);
        panel.add(lblUsuario);

        btnEquiposMedicos = Estilos.boton("<html>Equipos<br>medicos</html>");
        btnEquiposMedicos.setBounds(60, 190, 220, 80);
        panel.add(btnEquiposMedicos);

        btnPlantillasChecklist = Estilos.boton("<html>Plantillas<br>Checklist</html>");
        btnPlantillasChecklist.setBounds(60, 320, 220, 80);
        panel.add(btnPlantillasChecklist);

        btnCerrarSesion = Estilos.boton("Cerrar Sesion");
        btnCerrarSesion.setBounds(60, 530, 220, 55);
        panel.add(btnCerrarSesion);

        JLabel lblInfo = Estilos.label("<html>El administrador puede registrar equipos médicos<br>"
                + "y crear o editar plantillas de checklist.</html>", Estilos.TEXTO);
        lblInfo.setBounds(370, 240, 650, 120);
        panel.add(lblInfo);

        btnEquiposMedicos.addActionListener(e -> {
            FrmGestionEquipos frm = new FrmGestionEquipos(usuario);
            frm.setVisible(true);
        });

        btnPlantillasChecklist.addActionListener(e -> {
            FrmPlantillasChecklist frm = new FrmPlantillasChecklist(usuario);
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