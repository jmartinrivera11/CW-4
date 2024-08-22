
package progra2_q3.archivo;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestMiFile {
    
    static MiFile mf = new MiFile();
    static Scanner lea = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        int opcion;
        do {
            System.out.println("\nMENU");
            System.out.println("1) Set el archivo/folder");
            System.out.println("2) Ver informacion");
            System.out.println("3) Crear un archivo");
            System.out.println("4) Crear un folder");
            System.out.println("5) Borrar");
            System.out.println("6) CMD - DIR");
            System.out.println("7) Tree");
            System.out.println("8) Escribir");
            System.out.println("9) Sobrescribir");
            System.out.println("10) Leer");
            System.out.println("11) Salir");
            System.out.print("Escoger una opcion: ");
            opcion = lea.nextInt();
            lea.nextLine();  // Consumir la nueva línea
            
            try {
                switch (opcion) {
                    case 1:
                        set();
                        break;
                    case 2:
                        mf.Info();
                        break;
                    case 3:
                        mf.crearFile();
                        break;
                    case 4:
                        mf.crearFolder();
                        break;
                    case 5:
                        mf.borrar();
                        break;
                    case 6:
                        mf.dir();
                        break;
                    case 7:
                        mf.tree();
                        break;
                    case 8:
                        escribir();
                        break;
                    case 9:
                        sobrescribir();
                        break;
                    case 10:
                        mf.leer();
                        break;
                }
            } catch (InputMismatchException e) {
                lea.nextLine();
                System.out.println("Por favor ingresar una opción correcta");
            } catch (NullPointerException e) {
                System.out.println("Debes seleccionar la opción 1 al menos una vez");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 11);
    }
    
    private static void set() {
        System.out.print("Direccion: ");
        mf.setFile(lea.next());
    }
    
    private static void escribir() throws IOException {
        System.out.print("Ingresar texto: ");
        String txt = lea.nextLine();
        mf.escribir(txt, true);
    }
    
    private static void sobrescribir() throws IOException {
        System.out.print("Ingresar texto nuevo: ");
        String txt = lea.nextLine();
        mf.escribir(txt, false);
    }
}
