import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmGestionEquipos extends JFrame {

    private Usuario usuario;
    private MedaczDAO dao;

    private JTextField txtTipo;
    private JComboBox<Hospital> cmbHospital;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtSerie;
    private JTextField txtUbicacion;
    private JComboBox<String> cmbEstado;

    private JTable tabla;
    private DefaultTableModel modelo;
    private List<EquipoMedico> equipos;

    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnCerrar;

    public FrmGestionEquipos(Usuario usuario) {
        this.usuario = usuario;
        this.dao = new MedaczDAO();

        setTitle("Gestión de Equipos - MEDACZ");
        setSize(1150, 720);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel titulo = Estilos.label("Gestión de equipos", Estilos.SUBTITULO);
        titulo.setBounds(390, 25, 380, 40);
        panel.add(titulo);

        txtTipo = agregarCampo(panel, "Tipo de equipo", 85);
        cmbHospital = new JComboBox<>();
        cmbHospital.setFont(Estilos.TEXTO);
        agregarComboHospital(panel);

        txtMarca = agregarCampo(panel, "Marca", 185);
        txtModelo = agregarCampo(panel, "Modelo", 235);
        txtSerie = agregarCampo(panel, "Núm Serie", 285);
        txtUbicacion = agregarCampo(panel, "Ubicación", 335);

        JLabel lblEstado = Estilos.label("Estado", Estilos.TEXTO);
        lblEstado.setBounds(70, 385, 200, 30);
        panel.add(lblEstado);

        cmbEstado = new JComboBox<>(new String[]{"Funcionando", "Mantenimiento", "En reparación", "Pausado", "Fuera de servicio"});
        cmbEstado.setFont(Estilos.TEXTO);
        cmbEstado.setBounds(260, 385, 300, 35);
        panel.add(cmbEstado);

        Validaciones.soloNumeros(txtSerie);

        modelo = new DefaultTableModel(
                new String[]{"ID", "Tipo", "Hospital", "Marca", "Modelo", "Serie", "Ubicación", "Estado"},
                0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(600, 85, 500, 430);
        panel.add(scroll);

        btnAgregar = Estilos.boton("Agregar");
        btnAgregar.setBounds(100, 535, 160, 40);
        panel.add(btnAgregar);

        btnEditar = Estilos.boton("Editar");
        btnEditar.setBounds(300, 535, 160, 40);
        panel.add(btnEditar);

        btnEliminar = Estilos.boton("Eliminar");
        btnEliminar.setBounds(500, 535, 160, 40);
        panel.add(btnEliminar);

        btnCerrar = Estilos.boton("Cerrar");
        btnCerrar.setBounds(700, 535, 160, 40);
        panel.add(btnCerrar);

        tabla.getSelectionModel().addListSelectionListener(e -> llenarCamposDesdeTabla());
        btnAgregar.addActionListener(e -> agregarEquipo());
        btnEditar.addActionListener(e -> editarEquipo());
        btnEliminar.addActionListener(e -> eliminarEquipo());
        btnCerrar.addActionListener(e -> dispose());

        cargarHospitales();
        cargarEquipos();
    }

    private JTextField agregarCampo(JPanel panel, String texto, int y) {
        JLabel label = Estilos.label(texto, Estilos.TEXTO);
        label.setBounds(70, y, 200, 30);
        panel.add(label);

        JTextField campo = Estilos.campo();
        campo.setBounds(260, y, 300, 35);
        panel.add(campo);

        return campo;
    }

    private void agregarComboHospital(JPanel panel) {
        JLabel label = Estilos.label("Hospital", Estilos.TEXTO);
        label.setBounds(70, 135, 200, 30);
        panel.add(label);

        cmbHospital.setBounds(260, 135, 300, 35);
        panel.add(cmbHospital);
    }

    private void cargarHospitales() {
        try {
            cmbHospital.removeAllItems();

            for (Hospital hospital : dao.listarHospitales()) {
                cmbHospital.addItem(hospital);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar hospitales: " + e.getMessage());
        }
    }

    private void cargarEquipos() {
        try {
            modelo.setRowCount(0);
            equipos = dao.listarEquipos(0);

            for (EquipoMedico e : equipos) {
                modelo.addRow(new Object[]{
                    e.getIdEquipo(),
                    e.getTipoEquipo(),
                    e.getNombreHospital(),
                    e.getMarca(),
                    e.getModelo(),
                    e.getNumeroSerie(),
                    e.getUbicacion(),
                    e.getEstado()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar equipos: " + e.getMessage());
        }
    }

    private EquipoMedico leerFormulario() {
        Hospital hospital = (Hospital) cmbHospital.getSelectedItem();

        if (hospital == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un hospital.");
            return null;
        }

        if (Validaciones.vacio(txtTipo.getText())
                || Validaciones.vacio(txtMarca.getText())
                || Validaciones.vacio(txtModelo.getText())
                || Validaciones.vacio(txtSerie.getText())
                || Validaciones.vacio(txtUbicacion.getText())) {
            JOptionPane.showMessageDialog(this, "Debes llenar todos los campos.");
            return null;
        }

        EquipoMedico equipo = new EquipoMedico();

        equipo.setTipoEquipo(txtTipo.getText().trim());
        equipo.setMarca(txtMarca.getText().trim());
        equipo.setModelo(txtModelo.getText().trim());
        equipo.setNumeroSerie(Integer.parseInt(txtSerie.getText().trim()));
        equipo.setUbicacion(txtUbicacion.getText().trim());
        equipo.setEstado(String.valueOf(cmbEstado.getSelectedItem()));
        equipo.setIdHospital(hospital.getIdHospital());

        return equipo;
    }

    private void agregarEquipo() {
        try {
            EquipoMedico equipo = leerFormulario();

            if (equipo == null) {
                return;
            }

            dao.agregarEquipo(equipo);

            JOptionPane.showMessageDialog(this, "Equipo agregado correctamente.");
            limpiar();
            cargarEquipos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar equipo: " + e.getMessage());
        }
    }

    private void editarEquipo() {
        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un equipo para editar.");
            return;
        }

        try {
            EquipoMedico equipo = leerFormulario();

            if (equipo == null) {
                return;
            }

            equipo.setIdEquipo(equipos.get(fila).getIdEquipo());

            dao.editarEquipo(equipo);

            JOptionPane.showMessageDialog(this, "Equipo editado correctamente.");
            limpiar();
            cargarEquipos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al editar equipo: " + e.getMessage());
        }
    }

    private void eliminarEquipo() {
        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un equipo para eliminar.");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas eliminar este equipo?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            dao.eliminarEquipo(equipos.get(fila).getIdEquipo());

            JOptionPane.showMessageDialog(this, "Equipo eliminado correctamente.");
            limpiar();
            cargarEquipos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar. Puede tener solicitudes relacionadas.\n" + e.getMessage());
        }
    }

    private void llenarCamposDesdeTabla() {
        int fila = tabla.getSelectedRow();

        if (fila == -1 || equipos == null || fila >= equipos.size()) {
            return;
        }

        EquipoMedico e = equipos.get(fila);

        txtTipo.setText(e.getTipoEquipo());
        txtMarca.setText(e.getMarca());
        txtModelo.setText(e.getModelo());
        txtSerie.setText(String.valueOf(e.getNumeroSerie()));
        txtUbicacion.setText(e.getUbicacion());
        cmbEstado.setSelectedItem(e.getEstado());

        for (int i = 0; i < cmbHospital.getItemCount(); i++) {
            Hospital h = cmbHospital.getItemAt(i);

            if (h.getIdHospital() == e.getIdHospital()) {
                cmbHospital.setSelectedIndex(i);
                break;
            }
        }
    }

    private void limpiar() {
        txtTipo.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtSerie.setText("");
        txtUbicacion.setText("");
        cmbEstado.setSelectedIndex(0);
        tabla.clearSelection();
    }
}