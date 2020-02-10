package es.udc.redes.tutorial.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Copy {

    public static void main(String[] args) throws IOException {
        if (args.length<2) {
            System.out.print("faltan argumentos");
            System.exit(0);
        }
        FileInputStream orixe = null;
        FileOutputStream destino = null;
        try {
            orixe = new FileInputStream(args[0]);
            destino = new FileOutputStream(args[1]);
            int c;

            while ((c = orixe.read()) != -1) {
                destino.write(c);
            }
            
        }finally {
            
            if (orixe != null) {
                
                orixe.close();
            }
            
            if (destino != null) {
                
                destino.close();
            }
        }

    }
    
}
