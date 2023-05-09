package encriptacion;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import sun.text.normalizer.UTF16;

public class Encriptacion {

    String clave = "eLc1492ñ642";

    public static void main(String[] args) {

        Encriptacion main = new Encriptacion();
        
        String encriptar = main.Encriptar("Texto A Encriptar");
        JOptionPane.showMessageDialog(null, encriptar);
        JOptionPane.showMessageDialog(null, main.Desencriptar(encriptar));
        
    }

        // CLAVE PARA GENERAR CLAVE SECRETA
    public SecretKeySpec crearClave(String llave) {
        try {
            byte[] cadena = clave.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            cadena = md.digest(cadena);
            cadena = Arrays.copyOf(cadena, 16);
            SecretKeySpec skc = new SecretKeySpec(cadena, "AES");
            return skc;
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }
    
        // ENCRIPTAR
    public String Encriptar(String encriptar){
        try{
            SecretKeySpec skc = crearClave(clave);
            Cipher cp = Cipher.getInstance("AES");
            cp.init(Cipher.ENCRYPT_MODE, skc);
            byte[] cadena = encriptar.getBytes("UTF-8");
            byte[] encriptada = cp.doFinal(cadena);
            String cadenaEncriptada = Base64.encode(encriptada);
            return cadenaEncriptada;
        }catch(Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
            return "";
        }
    }
    
        // DESENCRIPTAR
    public String Desencriptar(String desencriptar){
        try{
            SecretKeySpec skc = crearClave(clave);
            Cipher cp = Cipher.getInstance("AES");
            cp.init(Cipher.DECRYPT_MODE, skc);
            byte[] cadena = Base64.decode(desencriptar);
            byte[] desencriptacion = cp.doFinal(cadena);
            String cadenaDesencriptada = new String(desencriptacion);
            return cadenaDesencriptada;
        }catch(Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
            return "";
        }
    }

}
