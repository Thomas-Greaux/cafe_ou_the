package CoT.bfck.Reader;

/**
 * Created by user on 07/12/2016.
 */
public class Commentary {

    public Commentary(){

    }

    public static String delCom(String s){
        if(s.indexOf('#') == 0)
            return "\n";
        else if(s.indexOf('#') == -1)
            return s;
        else return s.substring(0,s.indexOf("#"));
    }
}
