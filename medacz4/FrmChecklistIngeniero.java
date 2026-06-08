import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FrmChecklistIngeniero extends JFrame {

    private Usuario usuario;
    private MedaczDAO dao;

    private JComboBox<EquipoMedico> cmbEquipo;
    private JComboBox<String>[] cmbResultados;
    private javax.swing.JTextField[] txtObservaciones;
    private JButton btnEnviar;
    private JButton btnCerrar;

    private String[] verificaciones = {
        "Estado fisico general",
        "Encendido y Funcionamiento",
        "Pantalla / Indicadores",
        "Alarmas funcionales",
        "Cables en buen estado",
        "Parametros dentro del rango",
        "Limpieza General Realizada",
        "Equipo sin fallas aparentes"
    };

    public FrmChecklistIngeniero(Usuario usuario) {
        this.usuario = usuario;
        this.dao = new MedaczDAO();

        setTitle("Checklist Ingeniero - MEDACZ");
        setSize(1100, 720);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel titulo = Estilos.label("Checklist", Estilos.SUBTITULO);
        titulo.setBounds(440, 25, 220, 40);
        panel.add(titulo);

        JLabel lblEquipo = Estilos.label("Equipo medico", Estilos.TEXTO);
        lblEquipo.setBounds(220, 90, 220, 35);
        panel.add(lblEquipo);

        cmbEquipo = new JComboBox<>();
        cmbEquipo.setFont(Estilos.TEXTO);
        cmbEquipo.setBounds(430, 90, 430, 35);
        panel.add(cmbEquipo);

        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new GridLayout(9, 3, 5, 5));
        panelTabla.setBackground(Estilos.FONDO);
        panelTabla.setBounds(120, 150, 850, 390);
        panel.add(panelTabla);

        panelTabla.add(Estilos.label("Verificación", Estilos.TEXTO));
        panelTabla.add(Estilos.label("Resultado", Estilos.TEXTO));
        panelTabla.add(Estilos.label("Observación", Estilos.TEXTO));

        cmbResultados = new JComboBox[verificaciones.length];
        txtObservaciones = new javax.swing.JTextField[verificaciones.length];

        for (int i = 0; i < verificaciones.length; i++) {
            panelTabla.add(Estilos.label(verificaciones[i], Estilos.TEXTO));

            cmbResultados[i] = new JComboBox<>(new String[]{"Ok", "Observación", "No Conforme"});
            cmbResultados[i].setFont(Estilos.TEXTO);
            panelTabla.add(cmbResultados[i]);

            txtObservaciones[i] = Estilos.campo();
            panelTabla.add(txtObservaciones[i]);
        }

        btnEnviar = Estilos.boton("Enviar");
        btnEnviar.setBounds(360, 585, 180, 45);
        panel.add(btnEnviar);

        btnCerrar = Estilos.boton("Cerrar");
        btnCerrar.setBounds(590, 585, 180, 45);
        panel.add(btnCerrar);

        btnEnviar.addActionListener(e -> guardarChecklist());
        btnCerrar.addActionListener(e -> dispose());

        cargarEquipos();
    }

    private void cargarEquipos() {
        try {
            List<EquipoMedico> equipos = dao.listarEquipos(0);

            cmbEquipo.removeAllItems();

            for (EquipoMedico equipo : equipos) {
                cmbEquipo.addItem(equipo);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar equipos: " + e.getMessage());
        }
    }

    private void guardarChecklist() {
        EquipoMedico equipo = (EquipoMedico) cmbEquipo.getSelectedItem();

        if (equipo == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un equipo.");
            return;
        }

        StringBuilder verificacionFinal = new StringBuilder();
        StringBuilder observacionFinal = new StringBuilder();
        StringBuilder anomaliasFinal = new StringBuilder();

        for (int i = 0; i < verificaciones.length; i++) {
            String resultado = String.valueOf(cmbResultados[i].getSelectedItem());
            String observacion = txtObservaciones[i].getText().trim();

            verificacionFinal.append(verificaciones[i])
                    .append(": ")
                    .append(resultado)
                    .append("\n");

            if (!observacion.isEmpty()) {
                observacionFinal.append(verificaciones[i])
                        .append(": ")
                        .append(observacion)
                        .append("\n");
            }

            if (resultado.equals("No Conforme")) {
                anomaliasFinal.append(verificaciones[i])
                        .append(" presenta anomalía.\n");
            }
        }

        try {
            dao.guardarChecklist(
                    equipo.getIdEquipo(),
                    verificacionFinal.toString(),
                    observacionFinal.toString(),
                    anomaliasFinal.toString()
            );

            JOptionPane.showMessageDialog(this, "Checklist enviado correctamente.");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar checklist: " + e.getMessage());
        }
    }
}