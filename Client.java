import java.io.IOException;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class Client {
private int [][] desktop;
    public Client(String level){
        try{
            Socket socket = new Socket("localhost", 4445);
            OutputStream out = socket.getOutputStream();
            String text = level;
            out.write(text.getBytes());
            text = "\n";
            out.write(text.getBytes());
            out.flush();


            InputStream in = socket.getInputStream();
            String contentFile = "";
            int unicode;
            while((unicode = in.read()) != -1){
                char symbol = (char) unicode;
//                System.out.print(symbol);
                if(("" + symbol).equals("\n")){
                    break;
                }
                contentFile = contentFile + symbol;
            }

            getDesktopFromText(contentFile);
            socket.close();
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }

    private  int[][] getDesktopFromText(String text) {
        int row = 0;
        for(int i = 0; i < text.length(); i++) {
            char element = text.charAt(i);
            if(element == 'A') {
                row = row + 1;
            }
        }

         desktop = new int[row][];
        int column = 0;
        int index = 0;
        for(int i = 0; i < text.length(); i++) {
            char element = text.charAt(i);
            if(element == 'A') {
                desktop[index] = new int[column];
                column = 0;
                index = index + 1;
            } else {
                column = column + 1;
            }
        }

        int x = 0;
        int y = 0;
        for(int i = 0; i < text.length(); i++) {
            char element = text.charAt(i);
            if(element == 'A') {
                y = 0;
                x = x + 1;
            } else {
                int number = Integer.parseInt("" + element);
                desktop[x][y] = number;
                y = y + 1;
            }
        }
        return desktop;
    }
    public int[][] getDesktop(){
        return desktop;
    }
}
