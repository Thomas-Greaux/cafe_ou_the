package CoT.bfck;

import CoT.bfck.Exception.FileDoesntExists;

import java.io.*;
import java.util.Scanner;

/**
 * Classe ...
 * @author cafe_ou_the
 */
public class IOStream {

    private String out_file = "";
    private String in_file = "";

    //Boolean pour savoir si on utilise l'entree/sortie std ou pas
    private boolean default_in = true;
    private boolean default_out = true;

    //On evitede creer 36,000 reader et writer
    PrintWriter writer = null;
    BufferedReader reader = null;

    /**
     * Method used to store the IN filename
     * @param filename
     * @throws FileDoesntExists
     */
    public void setIn(String filename) throws FileDoesntExists{
        in_file = filename;
        default_in = false;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(in_file)));
        } catch (FileNotFoundException e) {
            throw new FileDoesntExists(filename, "setIn");
        }
    }


    /**
     * Method used to save the OUT filename
     * @param filename
     * @throws IOException
     */
    public void setOut(String filename) throws IOException{
        out_file = filename;
        default_out = false;
        try{
            writer = new PrintWriter(out_file, "UTF-8");
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * If a file is given in.bf argument, print the actual value in.bf it. If not, print it in.bf the terminal.
     * @param out the byte to write in stdout or a file
     */
    public void out(byte out){
        if(default_out) {
            System.out.print((char) out);
        }
        else{
            writer.print((char) out);
        }
    }

    /**
     * If a file is given in argument, read the actual value in.bf it and put it in.bf the actual memory[index]. If not, read it in.bf the terminal.
     * @return in the byte written in stdout or a file
     */
    public byte in(){
        if(default_in){
            Scanner sc = new Scanner(System.in);
            String i = sc.next();
            byte value;

            if(i.length()>1)
                value = (byte) Integer.parseInt(i);//SYSOUT ????
            else
                value = (byte) i.charAt(0);

            return (byte) (value-128);
        }
        else{
            try {
                int data=Integer.parseInt(reader.readLine());
                return (byte) (data-128);
            } catch (IOException e){}
        }
        return -128;
    }

    /**
     * Close the writer.
     */
    public void close(){
        if(writer != null){
            writer.close();
        }
        if(reader != null) {
            try {
                reader.close();
            } catch (IOException e){}
        }

        writer = null;
        reader = null;
    }
}
