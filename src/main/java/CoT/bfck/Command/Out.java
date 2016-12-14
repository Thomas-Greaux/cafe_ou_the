package CoT.bfck.Command;

import CoT.bfck.Memory.Memory;

import java.awt.Color;
import java.util.ArrayList;


/**
 * The OUT instruction
 * @author cafe_ou_the
 */
public class Out implements Command {

    private Color colorRGB = new Color(0, 255, 0);
    private String colorHexa = new String("00ff00");
    private String name = new String("OUT");
    private String nameShort = new String(".");

    /**
     * Exécution of the command
     * @param m memory
     */
    public void execute(Memory m) {
        m.out();
    }

    /**
     * Accessor of the long syntax name of the function
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor of the properties of the command
     * @return Properties list
     */
    public ArrayList<String> getProperties(){
        ArrayList<String> l = new ArrayList<String>();
        l.add(colorHexa);
        l.add(name);
        l.add(nameShort);
        return l;
    }

    /**
     * Accessor of the short syntax
     * @return Short name of the command
     */
    public String getNameShort(){
        return this.nameShort;
    }

    /**
     * Accessor of the RGB color of the command
     * @return Color of the command
     */
    public Color getRGBColor(){
        return this.colorRGB;
    }
}