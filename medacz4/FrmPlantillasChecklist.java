import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmPlantillasChecklist extends JFrame {

    private Usuario usuario;
    private MedaczDAO dao;

    private JTextField txtNombre;
    private JTextArea txtElementos;
    private JTable tabla;
    private DefaultTableModel modelo;
    private List<String[]> plantillas;

    private JButton btnCrear;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnCerrar;

    public FrmPlantillasChecklist(Usuario usuario) {
        this.usuario = usuario;
        this.dao = new MedaczDAO();

        setTitle("Plantillas Checklist - MEDACZ");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel titulo = Estilos.label("Plantillas Checklist", Estilos.SUBTITULO);
        titulo.setBounds(360, 25, 420, 40);
        panel.add(titulo);

        JLabel lblNombre = Estilos.label("<html>Nombre<br>Plantilla</html>", Estilos.TEXTO);
        lblNombre.setBounds(80, 100, 180, 55);
        panel.add(lblNombre);

        txtNombre = Estilos.campo();
        txtNombre.setBounds(270, 110, 300, 35);
        panel.add(txtNombre);

        JLabel lblElementos = Estilos.label("Elementos:", Estilos.TEXTO);
        lblElementos.setBounds(80, 180, 180, 35);
        panel.add(lblElementos);

        txtElementos = new JTextArea();
        txtElementos.setFont(Estilos.TEXTO);
        JScrollPane scrollArea = new JScrollPane(txtElementos);
        scrollArea.setBounds(270, 180, 300, 220);
        panel.add(scrollArea);

        modelo = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Elementos", "Fecha"},
                0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBounds(620, 100, 420, 390);
        panel.add(scrollTabla);

        btnCrear = Estilos.boton("Crear Plantilla");
        btnCrear.setBounds(80, 530, 220, 40);
        panel.add(btnCrear);

        btnEditar = Estilos.boton("Editar Plantilla");
        btnEditar.setBounds(330, 530, 220, 40);
        panel.add(btnEditar);

        btnEliminar = Estilos.boton("Eliminar Plantilla");
        btnEliminar.setBounds(580, 530, 250, 40);
        panel.add(btnEliminar);

        btnCerrar = Estilos.boton("Cerrar");
        btnCerrar.setBounds(860, 530, 150, 40);
        panel.add(btnCerrar);

        tabla.getSelectionModel().addListSelectionListener(e -> llenarCamposDesdeTabla());
        btnCrear.addActionListener(e -> crearPlantilla());
        btnEditar.addActionListener(e -> editarPlantilla());
        btnEliminar.addActionListener(e -> eliminarPlantilla());
        btnCerrar.addActionListener(e -> dispose());

        cargarPlantillas();
    }

    private void cargarPlantillas() {
        try {
            modelo.setRowCount(0);
            plantillas = dao.listarPlantillas();

            for (String[] p : plantillas) {
                modelo.addRow(p);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar plantillas: " + e.getMessage());
        }
    }

    private void crearPlantilla() {
        if (Validaciones.vacio(txtNombre.getText()) || Validaciones.vacio(txtElementos.getText())) {
            JOptionPane.showMessageDialog(this, "Debes escribir nombre y elementos.");
            return;
        }

        try {
            dao.crearPlantilla(txtNombre.getText().trim(), txtElementos.getText().trim());

            JOptionPane.showMessageDialog(this, "Plantilla creada correctamente.");
            limpiar();
            cargarPlantillas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al crear plantilla: " + e.getMessage());
        }
    }

    private void editarPlantilla() {
        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una plantilla.");
            return;
        }

        if (Validaciones.vacio(txtNombre.getText()) || Validaciones.vacio(txtElementos.getText())) {
            JOptionPane.showMessageDialog(this, "Debes escribir nombre y elementos.");
            return;
        }

        try {
            int idPlantilla = Integer.parseInt(plantillas.get(fila)[0]);

            dao.editarPlantilla(idPlantilla, txtNombre.getText().trim(), txtElementos.getText().trim());

            JOptionPane.showMessageDialog(this, "Plantilla editada correctamente.");
            limpiar();
            cargarPlantillas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al editar plantilla: " + e.getMessage());
        }
    }

    private void eliminarPlantilla() {
        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una plantilla.");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas eliminar esta plantilla?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            int idPlantilla = Integer.parseInt(plantillas.get(fila)[0]);

            dao.eliminarPlantilla(idPlantilla);

            JOptionPane.showMessageDialog(this, "Plantilla eliminada correctamente.");
            limpiar();
            cargarPlantillas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar plantilla: " + e.getMessage());
        }
    }

    private void llenarCamposDesdeTabla() {
        int fila = tabla.getSelectedRow();

        if (fila == -1 || plantillas == null || fila >= plantillas.size()) {
            return;
        }

        String[] plantilla = plantillas.get(fila);

        txtNombre.setText(plantilla[1]);
        txtElementos.setText(plantilla[2]);
    }

    private void limpiar() {
        txtNombre.setText("");
        txtElementos.setText("");
        tabla.clearSelection();
    }
}