package CoT.bfck.Command;

import CoT.bfck.Memory;
import CoT.bfck.Metrics;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The IN instruction
 * @author cafe_ou_the
 */
public class In implements Command {

    public Color colorRGB = new Color(255, 255, 0);
    public String colorHexa = new String("ffff00");
    public String name = new String("IN");
    public String nameShort = new String(",");

    /**
     * Exécution of the command
     * @param m memory
     * @throws Exception
     */
    public void execute(Memory m) throws Exception {
        m.in();
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
