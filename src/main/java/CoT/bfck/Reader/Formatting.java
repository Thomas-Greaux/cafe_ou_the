package CoT.bfck.Reader;

import CoT.bfck.Factory.CommandFactory;

/**
 * This class is used to erase syntax settings. For now, it
 *
 * @author cafe_ou_the
 */
public class Formatting {

    public Formatting(){

    }

    /**
     * Delete the commentary on the line.
     * @param s contains the line
     * @return the new line, without comments. If the comments are all-line : return \n
     */
    private String delCom(String s){
        if(s.indexOf('#') == -1)
            return s;
        else return s.substring(0,s.indexOf("#"));
    }

    /**
     * Delete the syntax on the line.
     * @param s contains the line
     * @return the new line, without any syntax ( \t, ' ' or \n for now).
     */
    private String delSyntax(String s){
        String returned = "";

        for(int i=0;i<s.length();i++) {
            /*if (s.charAt(i) == ' ') {
                //DO NOTHING : SYNTAX
            } else */if (s.charAt(i) == '\\') {
                if (s.charAt(i + 1) == 't') {
                    i++;
                    //DO NOTHING : SYNTAX
                }
                else if (s.charAt(i + 1) == 'n') {
                    return returned;
                }
            }else{
                returned += s.charAt(i);
            }

        }

        return returned;
    }

    /**
     * Delete the line if the color of the area is black (it's the color used to fill an instruction picture).
     * @param s contains the line
     * @return the line, or an empty line if it is equals to the fill color
     */
    public String delNullColor(String s){
        if(s.equals("000000")){
            return "";
        }else{
            return s;
        }
    }
    /**
     * Delete everything that does't need to be deleted in order to run the execution.
     * @param s contains the line
     * @return new line, without comments and everything, ready to be executed.
     */
    public String deleteSyntaxAndComments(String s){
        s = delCom(s);
        s = delSyntax(s);
        s = delNullColor(s);
        return s;
    }
}
