import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Estilos {

    // Light blue theme
    public static final Color FONDO = new Color(225, 240, 255);
    public static final Color BOTON = new Color(180, 215, 245);
    public static final Color BORDE = new Color(100, 140, 180);

    public static final Font TITULO = new Font("Courier New", Font.PLAIN, 34);
    public static final Font SUBTITULO = new Font("Courier New", Font.PLAIN, 28);
    public static final Font TEXTO = new Font("Courier New", Font.PLAIN, 18);
    public static final Font BOTON_FUENTE = new Font("Courier New", Font.PLAIN, 18);

    private Estilos() {
    }

    public static JLabel label(String texto, Font fuente) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(new Color(10, 45, 90));
        return label;
    }

    public static JButton boton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(BOTON_FUENTE);
        boton.setBackground(BOTON);
        boton.setForeground(new Color(10, 45, 90));
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