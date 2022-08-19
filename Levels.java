import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Levels {
  private int level;
  public Levels() {
    level = 1;
  }

  public int[][] nextLevel() {
    int[][] desktop = null;
    switch (level) {
      case 1:
        desktop = getFirstLevel();
      break;
      case 2:
        desktop = getSecondLevel();
      break;
      case 3:
        desktop = getThirdLevel();
      break;
      case 4:
        desktop = readLevelFromFile("levels/level4.sok");
      break;
      case 5:
        desktop = readLevelFromFile("levels/level5.sok");;
      break;
      case 6:
        desktop = readLevelFromFile("levels/level6.sok");;
      break;
      case 7:
        Client client7 = new Client("7");
        desktop = client7.getDesktop();
        break;
      case 8:
        Client client8 = new Client("8");
        desktop = client8.getDesktop();
        break;
      case 9:
        Client client9 = new Client("9");
        desktop = client9.getDesktop();
        break;
      default:
      desktop = getFirstLevel();
      level = 1;
    }
    level = level + 1;
    return desktop;
  }

  private int[][] getFirstLevel() {
    int[][] array = new int[][] {
      {0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
      {0, 0, 2, 0, 0, 0, 0, 0, 0, 2},
      {0, 0, 2, 0, 0, 0, 0, 0, 0, 2},
      {2, 2, 2, 0, 0, 0, 0, 0, 0, 2},
      {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
      {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
      {2, 2, 2, 0, 0, 0, 0, 0, 0, 2},
      {0, 0, 2, 0, 0, 0, 0, 0, 0, 2},
      {0, 0, 2, 1, 0, 3, 0, 4, 0, 2},
      {0, 0, 2, 2, 2, 2, 2, 2, 2, 2}
    };
    return array;
  }

  private int[][] getSecondLevel() {
    int[][] array = new int[][]{
      {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
      {2, 0, 0, 0, 1, 0, 0, 0, 0, 2},
      {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
      {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
      {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
      {2, 2, 2, 2, 0, 2, 2, 2, 2, 2},
      {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
      {2, 0, 3, 4, 0, 0, 4, 3, 0, 2},
      {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
      {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
    };
    return array;
  }

  private int[][] getThirdLevel() {
    int[][] array = new int[][]{
      {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
      {2, 1, 0, 3, 0, 4, 2, 2, 2, 2},
      {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
    };
    return array;
  }

  private int[][] readLevelFromFile(String fileName) {
    String contentFile = getContentFile(fileName);
    int[][] desktop = getDesktop(contentFile);
    return desktop;
  }

  private static String getContentFile(String fileName) {
    String textFromFile = "";
    File file = new File(fileName);
    try {
      FileInputStream in = new FileInputStream(file);
      int unicode;
      while ((unicode = in.read()) != -1) {
        char symbol = (char)unicode;
        if(('0' <= symbol && symbol <= '4') || symbol == '\n') {
          textFromFile = textFromFile + symbol;
        }
      }
      in.close();
    } catch(IOException ioe) {
      System.out.print(ioe);
    }
    return textFromFile;
  }

  private static int[][] getDesktop(String text) {
    int row = 0;
    for(int i = 0; i < text.length(); i++) {
      char element = text.charAt(i);
      if(element == '\n') {
        row = row + 1;
      }
    }

    int[][] desktop = new int[row][];
    int column = 0;
    int index = 0;
    for(int i = 0; i < text.length(); i++) {
      char element = text.charAt(i);
      if(element == '\n') {
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
      if(element == '\n') {
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
}
