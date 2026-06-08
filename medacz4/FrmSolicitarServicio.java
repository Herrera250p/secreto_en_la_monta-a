import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrmSolicitarServicio extends JFrame {

    private Usuario usuario;
    private MedaczDAO dao;

    private JTextField txtFecha;
    private JComboBox<String> cmbTipoServicio;
    private JComboBox<EquipoMedico> cmbEquipo;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtSerie;
    private JTextField txtUbicacion;
    private JTextArea txtFalla;

    private JButton btnFecha;
    private JButton btnEnviar;
    private JButton btnCancelar;

    public FrmSolicitarServicio(Usuario usuario) {
        this.usuario = usuario;
        this.dao = new MedaczDAO();

        setTitle("Solicitar Servicio - MEDACZ");
        setSize(1050, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel titulo = Estilos.label("Menú Hospital - Solicitudes de servicio", Estilos.TEXTO);
        titulo.setBounds(280, 25, 550, 35);
        panel.add(titulo);

        JLabel lblFecha = Estilos.label("Fecha", Estilos.TEXTO);
        lblFecha.setBounds(250, 100, 220, 30);
        panel.add(lblFecha);

        txtFecha = Estilos.campo();
        txtFecha.setBounds(500, 100, 220, 35);
        txtFecha.setEditable(false);
        panel.add(txtFecha);

        btnFecha = Estilos.boton("Elegir");
        btnFecha.setBounds(740, 100, 120, 35);
        panel.add(btnFecha);

        JLabel lblTipoServicio = Estilos.label("<html>Tipo de<br>servicio</html>", Estilos.TEXTO);
        lblTipoServicio.setBounds(250, 155, 220, 50);
        panel.add(lblTipoServicio);

        cmbTipoServicio = new JComboBox<>(new String[]{"Preventivo", "Correctivo"});
        cmbTipoServicio.setFont(Estilos.TEXTO);
        cmbTipoServicio.setBounds(500, 165, 360, 35);
        panel.add(cmbTipoServicio);

        JLabel lblEquipo = Estilos.label("<html>Tipo de equipo<br>medico</html>", Estilos.TEXTO);
        lblEquipo.setBounds(250, 220, 230, 50);
        panel.add(lblEquipo);

        cmbEquipo = new JComboBox<>();
        cmbEquipo.setFont(Estilos.TEXTO);
        cmbEquipo.setBounds(500, 230, 360, 35);
        panel.add(cmbEquipo);

        JLabel lblMarca = Estilos.label("Marca", Estilos.TEXTO);
        lblMarca.setBounds(250, 290, 220, 30);
        panel.add(lblMarca);

        txtMarca = Estilos.campo();
        txtMarca.setBounds(500, 290, 360, 35);
        panel.add(txtMarca);

        JLabel lblModelo = Estilos.label("Modelo", Estilos.TEXTO);
        lblModelo.setBounds(250, 345, 220, 30);
        panel.add(lblModelo);

        txtModelo = Estilos.campo();
        txtModelo.setBounds(500, 345, 360, 35);
        panel.add(txtModelo);

        JLabel lblSerie = Estilos.label("Num.Serie", Estilos.TEXTO);
        lblSerie.setBounds(250, 400, 220, 30);
        panel.add(lblSerie);

        txtSerie = Estilos.campo();
        txtSerie.setBounds(500, 400, 360, 35);
        txtSerie.setEditable(false);
        panel.add(txtSerie);

        JLabel lblUbicacion = Estilos.label("Ubicación", Estilos.TEXTO);
        lblUbicacion.setBounds(250, 455, 220, 30);
        panel.add(lblUbicacion);

        txtUbicacion = Estilos.campo();
        txtUbicacion.setBounds(500, 455, 360, 35);
        panel.add(txtUbicacion);

        JLabel lblFalla = Estilos.label("<html>Descripción<br>Falla</html>", Estilos.TEXTO);
        lblFalla.setBounds(250, 510, 220, 50);
        panel.add(lblFalla);

        txtFalla = new JTextArea();
        txtFalla.setFont(Estilos.TEXTO);
        JScrollPane scroll = new JScrollPane(txtFalla);
        scroll.setBounds(500, 510, 360, 80);
        panel.add(scroll);

        btnEnviar = Estilos.boton("Enviar Solicitud");
        btnEnviar.setBounds(300, 610, 240, 40);
        panel.add(btnEnviar);

        btnCancelar = Estilos.boton("Cancelar");
        btnCancelar.setBounds(590, 610, 180, 40);
        panel.add(btnCancelar);

        btnFecha.addActionListener(e -> elegirFecha());
        btnEnviar.addActionListener(e -> enviarSolicitud());
        btnCancelar.addActionListener(e -> dispose());

        cmbEquipo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                llenarDatosEquipo();
            }
        });

        cargarEquipos();
    }

    private void elegirFecha() {
        String fecha = SelectorFecha.elegirFecha(this);
        if (fecha != null) {
            txtFecha.setText(fecha);
        }
    }

    private void cargarEquipos() {
        try {
            List<EquipoMedico> equipos = dao.listarEquipos(usuario.getIdHospital());

            cmbEquipo.removeAllItems();

            for (EquipoMedico equipo : equipos) {
                cmbEquipo.addItem(equipo);
            }

            llenarDatosEquipo();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar equipos: " + e.getMessage());
        }
    }

    private void llenarDatosEquipo() {
        EquipoMedico equipo = (EquipoMedico) cmbEquipo.getSelectedItem();

        if (equipo == null) {
            return;
        }

        txtMarca.setText(equipo.getMarca());
        txtModelo.setText(equipo.getModelo());
        txtSerie.setText(String.valueOf(equipo.getNumeroSerie()));
        txtUbicacion.setText(equipo.getUbicacion());
    }

    private void enviarSolicitud() {
        EquipoMedico equipo = (EquipoMedico) cmbEquipo.getSelectedItem();

        if (equipo == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un equipo médico.");
            return;
        }

        if (Validaciones.vacio(txtFecha.getText())
                || Validaciones.vacio(txtMarca.getText())
                || Validaciones.vacio(txtModelo.getText())
                || Validaciones.vacio(txtUbicacion.getText())
                || Validaciones.vacio(txtFalla.getText())) {
            JOptionPane.showMessageDialog(this, "Debes llenar todos los campos.");
            return;
        }

        try {
            SolicitudServicio solicitud = new SolicitudServicio();

            solicitud.setFecha(txtFecha.getText());
            solicitud.setTipoServicio(String.valueOf(cmbTipoServicio.getSelectedItem()));
            solicitud.setFalla(txtFalla.getText().trim());
            solicitud.setIdHospital(usuario.getIdHospital());
            solicitud.setIdEquipo(equipo.getIdEquipo());

            dao.registrarSolicitud(solicitud);

            JOptionPane.showMessageDialog(this, "Solicitud enviada correctamente.");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo enviar la solicitud: " + e.getMessage());
        }
    }
}