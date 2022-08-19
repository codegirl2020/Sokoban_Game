import javax.swing.JFrame;

public class Viewer {
	private Canvas canvas;
	public Viewer() {
		Controller controller = new Controller(this);
		Model model = controller.getModel();
		canvas = new Canvas(model);

		JFrame frame = new JFrame("Sokoban Game MVC Pattern");
		frame.setSize(900, 700);
		frame.setLocation(500, 10);
		frame.add(canvas);
		frame.setVisible(true);
		frame.addKeyListener(controller);
	}

	public void update() {
		canvas.repaint();
	}
}
