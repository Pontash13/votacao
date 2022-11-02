package Controle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author alyss
 */
public class ImageUtil {

    /**
     * Função que transformar um blob em uma imagem
     */
    public byte[] obtem_imagem_blob(InputStream in) throws IOException {
        if(in == null)
        {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = in.read(buffer);
        while (bytesRead > -1) {
            out.write(buffer, 0, bytesRead);
            bytesRead = in.read(buffer);
        }
        byte[] picture = out.toByteArray();
        in.close();

        return picture;
    }

    
    public byte[] imagem_para_blob(String caminho) throws IOException
    {
        if (caminho.trim()== "")
        {
            return null;
        }
         ByteArrayOutputStream bos = null;
            try {
            File f = new File(caminho);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        byte[] teste;
        return teste = bos != null ? bos.toByteArray() : null;
        
    }
}
