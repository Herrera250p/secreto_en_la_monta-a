import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrmSeleccionRegistro extends JFrame {

    private JButton btnHospital;
    private JButton btnIngeniero;
    private JButton btnRegresar;

    public FrmSeleccionRegistro() {
        setTitle("Crear cuenta - MEDACZ");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Estilos.FONDO);
        setContentPane(panel);

        JLabel lblTitulo = Estilos.label("MEDACZ", Estilos.TITULO);
        lblTitulo.setBounds(370, 80, 200, 45);
        panel.add(lblTitulo);

        JLabel lblPregunta1 = Estilos.label("Que usuario desea", Estilos.SUBTITULO);
        lblPregunta1.setBounds(275, 160, 380, 40);
        panel.add(lblPregunta1);

        JLabel lblPregunta2 = Estilos.label("crear", Estilos.SUBTITULO);
        lblPregunta2.setBounds(405, 215, 140, 40);
        panel.add(lblPregunta2);

        btnHospital = Estilos.boton("Hospital");
        btnHospital.setBounds(315, 330, 270, 50);
        panel.add(btnHospital);

        btnIngeniero = Estilos.boton("Ingeniero");
        btnIngeniero.setBounds(315, 420, 270, 50);
        panel.add(btnIngeniero);

        btnRegresar = Estilos.boton("Regresar");
        btnRegresar.setBounds(20, 500, 150, 40);
        panel.add(btnRegresar);
    }

    public JButton getBtnHospital() {
        return btnHospital;
    }

    public JButton getBtnIngeniero() {
        return btnIngeniero;
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }
}