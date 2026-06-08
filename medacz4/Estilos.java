import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Estilos {

    public static final Color FONDO = new Color(238, 238, 238);
    public static final Color BOTON = new Color(220, 228, 242);
    public static final Color BORDE = new Color(130, 145, 165);

    public static final Font TITULO = new Font("Courier New", Font.PLAIN, 34);
    public static final Font SUBTITULO = new Font("Courier New", Font.PLAIN, 28);
    public static final Font TEXTO = new Font("Courier New", Font.PLAIN, 18);
    public static final Font BOTON_FUENTE = new Font("Courier New", Font.PLAIN, 18);

    private Estilos() {
    }

    public static JLabel label(String texto, Font fuente) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(Color.BLACK);
        return label;
    }

    public static JButton boton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(BOTON_FUENTE);
        boton.setBackground(BOTON);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        return boton;
    }

    public static JTextField campo() {
        JTextField campo = new JTextField();
        campo.setFont(TEXTO);
        return campo;
    }

    public static JPasswordField password() {
        JPasswordField campo = new JPasswordField();
        campo.setFont(TEXTO);
        return campo;
    }
}