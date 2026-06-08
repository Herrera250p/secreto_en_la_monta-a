import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrmCatalogoEquipos extends JFrame {

    private Usuario usuario;
    private MedaczDAO dao;

    private JComboBox<EquipoMedico> cmbEquipo;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtSerie;
    private JTextField txtUbicacion;
    private JTextField txtEstado;
    private JTextField txtUltimo;
    private JTextField txtProximo;
    private JButton btnHistorial;
    private JButton btnChecklist;
    private JButton btnCerrar;

    public FrmCatalogoEquipos(Usuario usuario) {
        this.usuario = usuario;
        this.dao = new MedaczDAO();

        setTitle("Equipos Médicos - MEDACZ");
        setSize(1050, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel titulo = Estilos.label("Equipos Medicos", Estilos.SUBTITULO);
        titulo.setBounds(390, 30, 350, 40);
        panel.add(titulo);

        JLabel lblEquipo = Estilos.label("<html>Tipo de equipo<br>medico</html>", Estilos.TEXTO);
        lblEquipo.setBounds(230, 100, 230, 50);
        panel.add(lblEquipo);

        cmbEquipo = new JComboBox<>();
        cmbEquipo.setFont(Estilos.TEXTO);
        cmbEquipo.setBounds(500, 110, 360, 35);
        panel.add(cmbEquipo);

        txtMarca = agregarCampo(panel, "Marca", 170);
        txtModelo = agregarCampo(panel, "Modelo", 220);
        txtSerie = agregarCampo(panel, "Num.Serie", 270);
        txtUbicacion = agregarCampo(panel, "Ubicación", 320);
        txtEstado = agregarCampo(panel, "Estado Actual", 370);
        txtUltimo = agregarCampo(panel, "Ultimo Mantenimiento", 420);
        txtProximo = agregarCampo(panel, "Prox. Mantenimiento", 470);

        btnHistorial = Estilos.boton("Historial");
        btnHistorial.setBounds(300, 560, 180, 45);
        panel.add(btnHistorial);

        btnChecklist = Estilos.boton("Checklist");
        btnChecklist.setBounds(530, 560, 180, 45);
        panel.add(btnChecklist);

        btnCerrar = Estilos.boton("Cerrar");
        btnCerrar.setBounds(760, 560, 150, 45);
        panel.add(btnCerrar);

        cmbEquipo.addActionListener(e -> mostrarEquipo());
        btnHistorial.addActionListener(e -> mostrarHistorial());
        btnChecklist.addActionListener(e -> mostrarChecklist());
        btnCerrar.addActionListener(e -> dispose());

        cargarEquipos();
    }

    private JTextField agregarCampo(JPanel panel, String texto, int y) {
        JLabel label = Estilos.label(texto, Estilos.TEXTO);
        label.setBounds(230, y, 260, 30);
        panel.add(label);

        JTextField campo = Estilos.campo();
        campo.setBounds(500, y, 360, 35);
        campo.setEditable(false);
        panel.add(campo);

        return campo;
    }

    private void cargarEquipos() {
        try {
            List<EquipoMedico> equipos = dao.listarEquipos(usuario.getIdHospital());

            cmbEquipo.removeAllItems();

            for (EquipoMedico equipo : equipos) {
                cmbEquipo.addItem(equipo);
            }

            mostrarEquipo();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar equipos: " + e.getMessage());
        }
    }

    private void mostrarEquipo() {
        EquipoMedico equipo = (EquipoMedico) cmbEquipo.getSelectedItem();

        if (equipo == null) {
            return;
        }

        txtMarca.setText(equipo.getMarca());
        txtModelo.setText(equipo.getModelo());
        txtSerie.setText(String.valueOf(equipo.getNumeroSerie()));
        txtUbicacion.setText(equipo.getUbicacion());
        txtEstado.setText(equipo.getEstado());
        txtUltimo.setText(equipo.getUltimoMantenimiento());
        txtProximo.setText(equipo.getProximoMantenimiento());
    }

    private void mostrarHistorial() {
        try {
            EquipoMedico equipo = (EquipoMedico) cmbEquipo.getSelectedItem();

            if (equipo == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un equipo.");
                return;
            }

            StringBuilder historial = new StringBuilder();

            for (SolicitudServicio s : dao.listarSolicitudes(usuario.getIdHospital())) {
                if (s.getIdEquipo() == equipo.getIdEquipo()) {
                    historial.append("Fecha: ")
                            .append(s.getFecha())
                            .append(" | Tipo: ")
                            .append(s.getTipoServicio())
                            .append(" | Estado: ")
                            .append(s.getEstado())
                            .append("\n");
                }
            }

            if (historial.length() == 0) {
                historial.append("Este equipo todavía no tiene historial.");
            }

            JTextArea area = new JTextArea(historial.toString());
            area.setEditable(false);
            area.setFont(Estilos.TEXTO);

            JOptionPane.showMessageDialog(this, area, "Historial", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al consultar historial: " + e.getMessage());
        }
    }

    private void mostrarChecklist() {
        try {
            EquipoMedico equipo = (EquipoMedico) cmbEquipo.getSelectedItem();

            if (equipo == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un equipo.");
                return;
            }

            JTextArea area = new JTextArea(dao.ultimoChecklist(equipo.getIdEquipo()));
            area.setEditable(false);
            area.setFont(Estilos.TEXTO);

            JOptionPane.showMessageDialog(this, area, "Checklist", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al consultar checklist: " + e.getMessage());
        }
    }
}