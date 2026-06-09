import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class FrmSolicitudesIngeniero extends JFrame {

    private Usuario usuario;
    private MedaczDAO dao;

    private JTable tabla;
    private DefaultTableModel modelo;
    private List<SolicitudServicio> solicitudes;

    private JButton btnActualizar;
    private JButton btnSeguimiento;
    private JButton btnMantenimiento;
    private JButton btnCerrar;

    public FrmSolicitudesIngeniero(Usuario usuario) {
        this.usuario = usuario;
        this.dao = new MedaczDAO();

        setTitle("Solicitudes Ingeniero - MEDACZ");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel titulo = Estilos.label("Menú Ingeniero - Solicitudes de servicio", Estilos.TEXTO);
        titulo.setBounds(270, 25, 600, 35);
        panel.add(titulo);

        modelo = new DefaultTableModel(
                new String[]{"Equipo", "Serie", "Fecha", "Tipo Servicio", "Estado", "Fecha Visita"},
                0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(50, 90, 980, 410);
        panel.add(scroll);

        btnActualizar = Estilos.boton("Actualizar");
        btnActualizar.setBounds(120, 540, 180, 40);
        panel.add(btnActualizar);

        btnSeguimiento = Estilos.boton("Dar seguimiento");
        btnSeguimiento.setBounds(340, 540, 220, 40);
        panel.add(btnSeguimiento);

        btnMantenimiento = Estilos.boton("Mantenimiento");
        btnMantenimiento.setBounds(600, 540, 200, 40);
        panel.add(btnMantenimiento);

        btnCerrar = Estilos.boton("Cerrar");
        btnCerrar.setBounds(840, 540, 150, 40);
        panel.add(btnCerrar);

        btnActualizar.addActionListener(e -> cargarSolicitudes());
        btnSeguimiento.addActionListener(e -> darSeguimiento());
        btnMantenimiento.addActionListener(e -> registrarMantenimiento());
        btnCerrar.addActionListener(e -> dispose());

        cargarSolicitudes();
    }

    private void cargarSolicitudes() {
        try {
            modelo.setRowCount(0);
            solicitudes = dao.listarSolicitudes(0);

            for (SolicitudServicio s : solicitudes) {
                modelo.addRow(new Object[]{
                    s.getNomEquipo() != null ? s.getNomEquipo() : "Sin equipo",
                    s.getNumSerie(),
                    s.getFecha(),
                    s.getTipoServicio(),
                    s.getEstado(),
                    s.getFechaVisita()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar solicitudes: " + e.getMessage());
        }
    }

    private SolicitudServicio obtenerSolicitudSeleccionada() {
        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una solicitud.");
            return null;
        }

        return solicitudes.get(fila);
    }

    private void darSeguimiento() {
        SolicitudServicio solicitud = obtenerSolicitudSeleccionada();

        if (solicitud == null) {
            return;
        }

        try {
            JComboBox<Ingeniero> cmbIngeniero = new JComboBox<>();

            for (Ingeniero ingeniero : dao.listarIngenieros()) {
                cmbIngeniero.addItem(ingeniero);
            }

            String fecha = SelectorFecha.elegirFecha(this);

            if (fecha == null) {
                return;
            }

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    cmbIngeniero,
                    "Seleccionar ingeniero asignado",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (opcion != JOptionPane.OK_OPTION) {
                return;
            }

            Ingeniero ingeniero = (Ingeniero) cmbIngeniero.getSelectedItem();

            if (ingeniero == null) {
                JOptionPane.showMessageDialog(this, "No hay ingenieros registrados.");
                return;
            }

            dao.asignarSeguimiento(solicitud.getIdSolicitud(), ingeniero.getIdIngeniero(), fecha);

            JOptionPane.showMessageDialog(this, "Seguimiento asignado correctamente.");
            cargarSolicitudes();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al asignar seguimiento: " + e.getMessage());
        }
    }

    private void registrarMantenimiento() {
        SolicitudServicio solicitud = obtenerSolicitudSeleccionada();

        if (solicitud == null) {
            return;
        }

        try {
            JComboBox<String> cmbEstado = new JComboBox<>(
                    new String[]{"En reparación", "Mantenimiento", "Pausado", "Funcionando", "Fuera de servicio"}
            );

            JTextArea txtObservaciones = new JTextArea(5, 25);
            txtObservaciones.setFont(Estilos.TEXTO);

            String fechaProxima = SelectorFecha.elegirFecha(this);

            if (fechaProxima == null) {
                return;
            }

            Object[] contenido = {
                "Estado del equipo:", cmbEstado,
                "Observaciones:", new JScrollPane(txtObservaciones),
                "Fecha próximo mantenimiento: " + fechaProxima
            };

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    contenido,
                    "Registrar mantenimiento",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (opcion != JOptionPane.OK_OPTION) {
                return;
            }

            if (Validaciones.vacio(txtObservaciones.getText())) {
                JOptionPane.showMessageDialog(this, "Debes escribir observaciones.");
                return;
            }

            dao.guardarMantenimiento(
                    solicitud,
                    String.valueOf(cmbEstado.getSelectedItem()),
                    txtObservaciones.getText().trim(),
                    fechaProxima
            );

            JOptionPane.showMessageDialog(this, "Mantenimiento registrado correctamente.");
            cargarSolicitudes();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar mantenimiento: " + e.getMessage());
        }
    }
}