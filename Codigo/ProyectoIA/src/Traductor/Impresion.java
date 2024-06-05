package Traductor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Impresion {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 100;

    public static BufferedImage generarImagen(String texto) {
        BufferedImage imagen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagen.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        g2d.setColor(Color.BLACK);
        Font font = new Font("Braille", Font.PLAIN, 48);
        g2d.setFont(font);
        g2d.drawString(texto, 10, 50);
        g2d.dispose();
        return imagen;
    }
    
    public static BufferedImage generarImagenEspejo(BufferedImage imagen) {
        int width = imagen.getWidth();
        int height = imagen.getHeight();
        BufferedImage imagenEspejo = new BufferedImage(width, height, imagen.getType());
        Graphics2D g = imagenEspejo.createGraphics();
        g.drawImage(imagen, width, 0, -width, height, null);
        g.dispose();
        return imagenEspejo;
    }

    public static void guardarImagen(BufferedImage imagen, String nombreBase) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Imagen");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File carpeta = fileChooser.getSelectedFile();
            try {
                String timestamp = Long.toString(System.currentTimeMillis());
                File archivo = new File(carpeta, nombreBase + "_" + timestamp + ".png");
                ImageIO.write(imagen, "png", archivo);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al guardar la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
