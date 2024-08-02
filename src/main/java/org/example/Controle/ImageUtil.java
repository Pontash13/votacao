package org.example.Controle;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {

    public byte[] obtem_imagem_blob(InputStream in) throws IOException {
        if (in == null) {
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


    public byte[] imagem_para_blob(String caminho) {
        if (caminho.trim().isEmpty()) {
            return null;
        }
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(caminho);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1; ) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }
}
