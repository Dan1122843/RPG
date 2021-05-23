package GUI;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Liam O'Connor ID: 18048495
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws IOException {
        GameSetup setup = new GameSetup();
        

    public static void main(String[] args) {
        try {
            GameSetup setup = new GameSetup();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
