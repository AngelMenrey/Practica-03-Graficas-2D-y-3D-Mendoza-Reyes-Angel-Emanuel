import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.TexturePaint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Practica03MendozaReyesAngelEmanuel {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Práctica 03 - Dibujo con degradados y texturizados");
        DegradadosTexturizados panel = new DegradadosTexturizados();
        ventana.add(panel);
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
    }
}

class DegradadosTexturizados extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Color colorAzulOscuro = new Color(0, 0, 51);
        Color colorNegro = new Color(0, 0, 0);

        int ancho = getWidth();
        int altura = getHeight();

        GradientPaint degradado = new GradientPaint(0, 0, colorAzulOscuro, 0, altura, colorNegro);
        g2d.setPaint(degradado);
        g2d.fillRect(0, 0, ancho, altura);

        g2d.setColor(Color.WHITE);
        Random aleatorio = new Random();
        for (int i = 0; i < 100; i++) {
            int x = aleatorio.nextInt(ancho);
            int y = aleatorio.nextInt(altura);
            int tamañoEstrella = aleatorio.nextInt(3) + 1;
            g2d.fillOval(x, y, tamañoEstrella, tamañoEstrella);
        }

        int alturaPasto = 100;
        BufferedImage buffer = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
        Graphics2D gBuffer = buffer.createGraphics();

        gBuffer.setColor(new Color(34, 139, 34));
        gBuffer.fillRect(0, 0, 20, 20);
        gBuffer.setColor(new Color(0, 100, 0));
        gBuffer.drawLine(0, 10, 20, 10);
        gBuffer.drawLine(10, 0, 10, 20);
        gBuffer.drawLine(5, 5, 15, 15);
        gBuffer.drawLine(15, 5, 5, 15);

        TexturePaint texturaPasto = new TexturePaint(buffer, new Rectangle(20, 20));
        g2d.setPaint(texturaPasto);
        g2d.fillRect(0, altura - alturaPasto, ancho, alturaPasto);

        g2d.setColor(Color.YELLOW);
        int[] xPuntos1 = {120, 130, 110};
        int[] yPuntos1 = {altura - alturaPasto - 20, altura - alturaPasto, altura - alturaPasto};
        g2d.fillPolygon(xPuntos1, yPuntos1, 3);

        int[] xPuntos2 = {140, 150, 130};
        int[] yPuntos2 = {altura - alturaPasto - 20, altura - alturaPasto, altura - alturaPasto};
        g2d.fillPolygon(xPuntos2, yPuntos2, 3);

        int[] xPuntos3 = {160, 170, 150};
        int[] yPuntos3 = {altura - alturaPasto - 20, altura - alturaPasto, altura - alturaPasto};
        g2d.fillPolygon(xPuntos3, yPuntos3, 3);

        BufferedImage texturaCaja = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
        Graphics2D gCaja = texturaCaja.createGraphics();

        gCaja.setColor(new Color(139, 69, 19));
        gCaja.fillRect(0, 0, 20, 20);
        gCaja.setColor(new Color(160, 82, 45));
        gCaja.drawLine(0, 0, 20, 20);
        gCaja.drawLine(0, 20, 20, 0);
        gCaja.drawLine(0, 10, 20, 10);
        gCaja.drawLine(10, 0, 10, 20);

        TexturePaint texturaCajaMario = new TexturePaint(texturaCaja, new Rectangle(20, 20));

        g2d.setPaint(texturaCajaMario);
        int tamañoCuadrado = 20;
        int baseX = 200;
        int baseY = altura - alturaPasto;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <= i; j++) {
                int x = baseX + j * tamañoCuadrado;
                int y = baseY - (i + 1) * tamañoCuadrado;
                g2d.fillRect(x, y, tamañoCuadrado, tamañoCuadrado);
            }
        }

        int x = baseX;
        int y = baseY - 3 * tamañoCuadrado;
        g2d.fillRect(x, y, tamañoCuadrado, tamañoCuadrado);

        g2d.fillRect(baseX + tamañoCuadrado, baseY - tamañoCuadrado, tamañoCuadrado, tamañoCuadrado);
        g2d.fillRect(baseX + 2 * tamañoCuadrado, baseY - tamañoCuadrado, tamañoCuadrado, tamañoCuadrado);

        g2d.setColor(Color.GREEN);
        int lineaX = 50;
        int lineaYInicio = altura - alturaPasto;
        int lineaYFin = 120;
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(lineaX, lineaYInicio, lineaX, lineaYFin);

        g2d.setColor(Color.WHITE);
        int desplazamientoTriangulo = 8;
        int[] xPuntosBandera = {lineaX, lineaX + 20, lineaX};
        int[] yPuntosBandera = {lineaYFin - 10 + desplazamientoTriangulo, lineaYFin + desplazamientoTriangulo, lineaYFin + 10 + desplazamientoTriangulo};
        g2d.fillPolygon(xPuntosBandera, yPuntosBandera, 3);

        ImageIcon iconoImagen = new ImageIcon(getClass().getResource("/yoshi.png"));
        int anchoImagen = 100;
        int alturaImagen = 100;
        x = (ancho - anchoImagen) / 2;
        y = altura - alturaPasto - alturaImagen;
        g2d.drawImage(iconoImagen.getImage(), x, y, anchoImagen, alturaImagen, this);

        int diametroLuna = 100;
        Point2D centro = new Point2D.Float(ancho - 150, 100);
        float radio = 50;
        float[] dist = {0.0f, 1.0f};
        Color[] colores = {Color.WHITE, Color.GRAY};
        RadialGradientPaint degradadoLuna = new RadialGradientPaint(centro, radio, dist, colores);
        g2d.setPaint(degradadoLuna);
        g2d.fillOval(ancho - 150 - diametroLuna / 2, 50, diametroLuna, diametroLuna);

        int lunaX = ancho - 150;
        int lunaY = 100;
        int radioLuna = 50;

        g2d.setColor(Color.GRAY);
        float[] patronGuiones = {10, 10};
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, patronGuiones, 0));

        int longitudRayo = 60;
        for (int i = 0; i < 360; i += 30) {
            double angulo = Math.toRadians(i);
            int x1 = (int) (lunaX + radioLuna * Math.cos(angulo));
            int y1 = (int) (lunaY + radioLuna * Math.sin(angulo));
            int x2 = (int) (lunaX + (radioLuna + longitudRayo) * Math.cos(angulo));
            int y2 = (int) (lunaY + (radioLuna + longitudRayo) * Math.sin(angulo));
            g2d.drawLine(x1, y1, x2, y2);
        }

        int anchoArco = 60;
        int alturaArco = 100;
        int arcoX = ancho - 200;
        int arcoY = altura - alturaPasto - alturaArco + 50;

        g2d.setStroke(new BasicStroke());

        g2d.setColor(Color.GREEN);
        for (int i = 0; i < 3; i++) {
            g2d.drawArc(arcoX, arcoY, anchoArco, alturaArco, 0, 180);
            arcoX += anchoArco;
        }

        g2d.setColor(Color.WHITE);
        g2d.fillOval(100, 50, 60, 40);
        g2d.fillOval(130, 30, 80, 60);
        g2d.fillOval(170, 50, 60, 40);
        g2d.fillOval(140, 60, 80, 50);

        g2d.fillOval(230, 50, 60, 40);
        g2d.fillOval(260, 30, 80, 60);
        g2d.fillOval(300, 50, 60, 40);
        g2d.fillOval(270, 60, 80, 50);
    }
}