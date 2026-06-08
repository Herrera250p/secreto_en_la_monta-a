import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SelectorFecha {

    private SelectorFecha() {
    }

    public static String elegirFecha(java.awt.Component padre) {
        LocalDate actual = LocalDate.now();

        JComboBox<Integer> cmbDia = new JComboBox<>();
        JComboBox<Integer> cmbMes = new JComboBox<>();
        JComboBox<Integer> cmbAnio = new JComboBox<>();

        for (int i = 1; i <= 31; i++) {
            cmbDia.addItem(i);
        }

        for (int i = 1; i <= 12; i++) {
            cmbMes.addItem(i);
        }

        for (int i = actual.getYear(); i <= actual.getYear() + 5; i++) {
            cmbAnio.addItem(i);
        }

        cmbDia.setSelectedItem(actual.getDayOfMonth());
        cmbMes.setSelectedItem(actual.getMonthValue());
        cmbAnio.setSelectedItem(actual.getYear());

        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.add(new JLabel("Día:"));
        panel.add(cmbDia);
        panel.add(new JLabel("Mes:"));
        panel.add(cmbMes);
        panel.add(new JLabel("Año:"));
        panel.add(cmbAnio);

        int opcion = JOptionPane.showConfirmDialog(
                padre,
                panel,
                "Seleccionar fecha",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcion != JOptionPane.OK_OPTION) {
            return null;
        }

        int dia = (Integer) cmbDia.getSelectedItem();
        int mes = (Integer) cmbMes.getSelectedItem();
        int anio = (Integer) cmbAnio.getSelectedItem();

        try {
            LocalDate fecha = LocalDate.of(anio, mes, dia);
            return fecha.toString();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(padre, "Fecha no válida.");
            return null;
        }
    }
}