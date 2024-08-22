
package progra2_q3.archivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class MiFile {
    private File mf = null;
    
    public void setFile(String dir) {
        mf = new File(dir);
    }
    
    public void Info() {
        if (mf.exists()) {
            System.out.println("Si existe:");
            System.out.println("Nombre: " + mf.getName() 
                    + "\nPath: " + mf.getPath()
                    + "\nAbsoluta: " + mf.getAbsolutePath()
                    + "\nPadre: " + mf.getAbsoluteFile().getParentFile().getName()
                    + "\nBytes: " + mf.length());
            
            if (mf.isFile()) {
                System.out.println("Es un archivo");
            } else if (mf.isDirectory()) {
                System.out.println("Es un folder");
            }
            
            System.out.println("Última modificación: " + new Date(mf.lastModified()));
            
        } else {
            System.out.println("El archivo no existe");
        }
    }
    
    public void crearFile() throws IOException {
        if (mf.createNewFile())
            System.out.println("Creado exitosamente!");
        else
            System.out.println("No se pudo crear");
    }
    
    public void crearFolder() {
        if (mf.mkdir())
            System.out.println("Creado exitosamente!");
        else
            System.out.println("No se pudo crear");
    }
    
    private boolean antidoto(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles())
                antidoto(child);
        }
        return file.delete();
    }
    
    public void borrar() {
        if (antidoto(mf)) 
            System.out.println("Se ha borrado");
        else
            System.out.println("No se ha borrado");
    }
    
    public void dir() {
        if (mf.isDirectory()) {
            System.out.println("Directorio de: " + mf.getAbsolutePath());
            System.out.println("");
            int cfiles = 0, cdirs = 0, tbytes = 0;
            
            for (File child : mf.listFiles()) {
                if (!child.isHidden()) {
                    //Fecha última modificación
                    Date ultima = new Date(child.lastModified());
                    System.out.print(ultima + "\t");
                    
                    //Si es File o Folder
                    if (child.isDirectory()) {
                        cdirs++;
                        System.out.print("<DIR>\t\t");
                    } else {
                        cfiles++;
                        tbytes+=child.length();
                        System.out.print("     \t" + child.length() + "\t");
                    }
                    System.out.println(child.getName());
                }
            }
            System.out.println(cfiles + " archivos\t" + tbytes + " bytes" + "\n" + cdirs + " dirs");
        }
    }
    
    public void tree() {
        tree(mf, "-");
    }
    
    private void tree(File dir, String tab) {
        if (dir.isDirectory()) {
            System.out.println(tab + dir.getName());
            for (File child : dir.listFiles())
                if (!child.isHidden())
                    tree(child, tab + "--");
        }
    }
    
    public void escribir(String txt, boolean unir) throws IOException {
        try (FileWriter writer = new FileWriter(mf, unir)) {
            writer.write(txt + "\n");
            System.out.println("Agregado exitosamente");
        }
    }
    
    public void leer() throws IOException {
        if (mf.exists() && mf.isFile()) {
            try (Scanner lea = new Scanner(mf)) {
                System.out.println("Contenido:");
                while (lea.hasNextLine()) {
                    System.out.println(lea.nextLine());
                }
            }
        } else {
            System.out.println("Archivo no existente");
        }
    }
}
