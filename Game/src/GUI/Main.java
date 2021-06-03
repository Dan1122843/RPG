package GUI;

import static GUI.GameSetup.setCharacter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import rpgCharacters.*;

/**
 *
 * @author Liam O'Connor ID: 18048495
 */
public class Main {

    public static boolean bossDefeated = false;
    public static boolean inBattle = false;
    public static boolean isWarrior = false;
    public static boolean isDwarf = false;
    public static boolean isElf = false;
    public static Player p1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            GameSetup gui = new GameSetup();

            gui.getWarriorButton().addActionListener((ActionEvent e) -> {
                gui.getStartPageCharacterButtonsPanel().setVisible(false);
                gui.getStartPageNamePanel().setVisible(true);
                GameSetup.setCharacter(1);
                isWarrior = true;
            });

            gui.getElfButton().addActionListener((ActionEvent e) -> {
                gui.getStartPageCharacterButtonsPanel().setVisible(false);
                gui.getStartPageNamePanel().setVisible(true);
                GameSetup.setCharacter(2);
                isElf = true;
            });

            gui.getDwarfButton().addActionListener((ActionEvent e) -> {
                gui.getStartPageCharacterButtonsPanel().setVisible(false);
                gui.getStartPageNamePanel().setVisible(true);
                GameSetup.setCharacter(1);
                isDwarf = true;
            });

            gui.getEnterButton().addActionListener((ActionEvent e) -> {
                String PlayerName = gui.getNameTextBox().getText();
                
                if(PlayerName.equals("")){
                    gui.getNameLabel().setText("You must enter a name!");
                    gui.getNameLabel().setForeground(Color.red);
                } else{
                    if(isWarrior){
                    p1 = new Warrior(PlayerName);
                } if(isElf){
                    p1 = new Elf(PlayerName);
                } if(isDwarf){
                    p1 = new Dwarf(PlayerName);
                }
                
                gui.getStatsLabel().setText("<html><b>Player Name: </b>" + p1.getName() + "<b>&emsp;Health: </b>"
                        + p1.getHealth() + " HP<b>&emsp;Attack: </b>" + p1.getAttack() + " DMG</html>");
                gui.getObjectiveLabel().setText("<html>Find your way out of the dungeon. Defeat all the enemies: <br/><br/>Good Luck " + PlayerName + "<html/>");
                gui.getStartPageFrame().setVisible(false);
                gui.getGameplayFrame().setVisible(true);
                }
                
            });

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
