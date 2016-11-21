package CoT.bfck.Command;

import CoT.bfck.Memory;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Created by Thoma on 11/21/2016.
 */
public class In {
    protected Color colorRGB = new Color(255, 255, 255);
    protected String colorHexa = new String("ffff00");
    protected String name = new String("IN");
    protected String nameShort = new String(",");

    public void execute(Memory m) throws Exception {
        m.in();
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
