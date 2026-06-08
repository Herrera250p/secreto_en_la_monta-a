import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmEstadoSolicitud extends JFrame {

    private Usuario usuario;
    private MedaczDAO dao;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnActualizar;
    private JButton btnCerrar;

    public FrmEstadoSolicitud(Usuario usuario) {
        this.usuario = usuario;
        this.dao = new MedaczDAO();

        setTitle("Estado de Solicitud - MEDACZ");
        setSize(1050, 650);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel titulo = Estilos.label("Estado Solicitud", Estilos.SUBTITULO);
        titulo.setBounds(390, 30, 300, 40);
        panel.add(titulo);

        modelo = new DefaultTableModel(
                new String[]{"ID", "Fecha", "Tipo Servicio", "Estado", "Fecha Visita", "Falla"},
                0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(60, 110, 920, 390);
        panel.add(scroll);

        btnActualizar = Estilos.boton("Actualizar");
        btnActualizar.setBounds(330, 535, 180, 40);
        panel.add(btnActualizar);

        btnCerrar = Estilos.boton("Cerrar");
        btnCerrar.setBounds(560, 535, 160, 40);
        panel.add(btnCerrar);

        btnActualizar.addActionListener(e -> cargarSolicitudes());
        btnCerrar.addActionListener(e -> dispose());

        cargarSolicitudes();
    }

    private void cargarSolicitudes() {
        try {
            modelo.setRowCount(0);

            List<SolicitudServicio> solicitudes = dao.listarSolicitudes(usuario.getIdHospital());

            for (SolicitudServicio s : solicitudes) {
                modelo.addRow(new Object[]{
                    s.getIdSolicitud(),
                    s.getFecha(),
                    s.getTipoServicio(),
                    s.getEstado(),
                    s.getFechaVisita(),
                    s.getFalla()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar solicitudes: " + e.getMessage());
        }
    }
}