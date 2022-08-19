import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
	private Model model;
	private Image imageWall;
	private Image imageBox;
	private Image imageGoal;
	private Image imageGamer;
	private Font fontError;
	private Font fontButton;
	private Color backgroundColor;
	private Color noEnteredColor;
	private Color enteredColor;
	private boolean flagButton;

	public Canvas(Model model) {
		this.model = model;
		// backgroundColor = new Color(209, 247, 202);
		// setBackground(backgroundColor);
		setBackground(Color.BLACK);
		noEnteredColor = new Color(72,72,72);
		enteredColor = new Color(121,121, 121);
		flagButton = true;

		setOpaque(true);

		// fontError = new Font("SHOWCARD GOTHIC", Font.PLAIN, 50);
		fontError = new Font("Bernard MT Condensed", Font.PLAIN, 50);
		fontButton = new Font("Impact", Font.PLAIN, 25);

		File wallFile = new File("images/wall.png");
		File boxFile = new File("images/box.png");
		File goalFile = new File("images/goal.png");
		File gamerFile = new File("images/gamer.png");
		try {
			imageWall = ImageIO.read(wallFile);
			imageBox = ImageIO.read(boxFile);
			imageGoal = ImageIO.read(goalFile);
			imageGamer = ImageIO.read(gamerFile);
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paint(Graphics g) {
		super.paint(g);

		if(model.getStateModel()) {
			drawDesktop(g);
		} else {
			drawError(g);
		}
	}

	private void drawError(Graphics g) {
		g.setFont(fontError);
		g.setColor(Color.RED);
		g.drawString("Error Initialization!", 100, 100);
	}

	private void drawDesktop(Graphics g) {
		int x = 50;
		int y = 50;
		int width = 50;
		int height = 50;
		int offset = 0;

		int[][] desktop = model.getDesktop();

		for(int i = 0; i < desktop.length; i++) {
			for(int j = 0; j < desktop[i].length; j++) {
				if(desktop[i][j] == 1) {
					g.drawImage(imageGamer, x, y, null);
				} else if(desktop[i][j] == 2) {
					g.drawImage(imageWall, x, y, null);
				} else if(desktop[i][j] == 3) {
					g.drawImage(imageBox, x, y, null);
				} else if(desktop[i][j] == 4) {
					g.drawImage(imageGoal, x, y, null);
				} else if(desktop[i][j] == 0) {
					// g.setColor(Color.WHITE);
					// g.fillRect(x, y , width, height);
				}
				x = x + width + offset;
			}
			x = 50;
			y = y + height + offset;
		}

		if(flagButton){
			g.setColor(noEnteredColor);
			g.fillRect(600, 50, 200, 50);
			g.setColor(Color.YELLOW);
			g.drawRect(600, 50, 200, 50);
		} else{
			g.setColor(enteredColor);
			g.fillRect(600, 50, 200, 50);
			g.setColor(Color.MAGENTA);
			g.drawRect(600, 50, 200, 50);
		}
		g.setColor(Color.WHITE);
		g.setFont(fontButton);
		g.drawString("Next Level", 650, 85);

	}

	@Override
	public void mouseClicked(MouseEvent event) {

	}

	@Override
	public void mousePressed(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		if( (600 <= x && x <= 800 ) && ( 50 <= y && y <=100 )){
//			System.out.println(x + " " + y);
			model.loadNextLevel();
		}


	}

	@Override
	public void mouseReleased(MouseEvent event) {

	}

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent event) {

	}

	@Override
	public void mouseDragged(MouseEvent event) {

	}

	@Override
	public void mouseMoved(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
//		System.out.println(x + " " + y);
		if( (600 <= x && x <= 800 ) && ( 50 <= y && y <=100 )){
			flagButton = false;
			repaint();
		} else{
			flagButton = true;
			repaint();
		}

	}
}
