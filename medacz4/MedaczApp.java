import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MedaczApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                InicializadorBD.prepararBase();

                FrmInicio vista = new FrmInicio();
                ControladorInicio controlador = new ControladorInicio(vista);
                vista.setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "No se pudo iniciar Medacz.\n"
                        + "Revisa que MySQL esté encendido y que exista la base medacz.\n\n"
                        + "Detalle: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}