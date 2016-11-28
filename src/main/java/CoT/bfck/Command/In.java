package CoT.bfck.Command;

import CoT.bfck.Memory;
import CoT.bfck.Metrics;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Created by Thoma on 11/21/2016.
 */
public class In implements Command {

    public Color colorRGB = new Color(255, 255, 0);
    public String colorHexa = new String("ffff00");
    public String name = new String("IN");
    public String nameShort = new String(",");

    public void execute(Memory m) throws Exception {
        m.in();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<String> getProperties(){
        ArrayList<String> l = new ArrayList<String>();
        l.add(colorHexa);
        l.add(name);
        l.add(nameShort);
        return l;
    }

    public String getNameShort(){
        return this.nameShort;
    }

    public Color getRGBColor(){
        return this.colorRGB;
    }
}
