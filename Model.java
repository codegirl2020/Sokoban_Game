public class Model {
	private Viewer viewer;
	private int[][] desktop;
	private int indexX;
	private int indexY;
	private boolean isOk;
	private int[][] arrayOfFourIndexies;
	private Levels levels;

	public Model(Viewer viewer) {
		this.viewer = viewer;
		isOk = true;
		levels = new Levels();
		initialization();
	}

	private void initialization() {
		desktop = levels.nextLevel();
		int countOne = 0;
		int countThree = 0;
		int countFour = 0;
		for(int i = 0; i < desktop.length; i++) {
			for(int j = 0; j < desktop[i].length; j++) {
				if(desktop[i][j] == 1) {
					indexX = i;
					indexY = j;
					countOne = countOne + 1;
				} else if(desktop[i][j] == 3) {
					countThree = countThree + 1;
				} else if(desktop[i][j] == 4) {
					countFour = countFour + 1;
				}
			}
		}

		if(countOne != 1 || countThree == 0 || countFour == 0 || countThree != countFour) {
			isOk = false;
			return;
		}

		arrayOfFourIndexies = new int[2][countFour];
		int t = 0;
		for(int i = 0; i < desktop.length; i++) {
			for(int j = 0; j < desktop[i].length; j++) {
				if(desktop[i][j] == 4) {
					arrayOfFourIndexies[0][t] = i;
					arrayOfFourIndexies[1][t] = j;
					t = t + 1;
				}
			}
		}
	}

	public void doAction(String direction) {
		if(direction.equals("Left")) {
			moveLeft();
		} else if(direction.equals("Up")) {
			moveUp();
		} else if(direction.equals("Right")) {
			moveRight();
		} else if(direction.equals("Down")) {
			moveDown();
		}
		checkGoal();
		viewer.update();
		won();
		//printDesktop();
	}


	private void moveLeft() {
		if(desktop[indexX][indexY - 1] == 3) {
			if(desktop[indexX][indexY - 2] == 0 || desktop[indexX][indexY - 2] == 4) {
				desktop[indexX][indexY - 1] = 0;
				desktop[indexX][indexY - 2] = 3;
			}
		}
		if(desktop[indexX][indexY - 1] == 0 || desktop[indexX][indexY - 1] == 4) {
			desktop[indexX][indexY] = 0;
			indexY = indexY - 1;
			desktop[indexX][indexY] = 1;
		}
	}

	private void moveUp() {
		if(desktop[indexX - 1][indexY] == 3) {
			if(desktop[indexX - 2][indexY] == 0 || desktop[indexX - 2][indexY] == 4) {
				desktop[indexX - 1][indexY] = 0;
				desktop[indexX - 2][indexY] = 3;
			}
		}
		if(desktop[indexX - 1][indexY] == 0 || desktop[indexX - 1][indexY] == 4) {
			desktop[indexX][indexY] = 0;
			indexX = indexX - 1;
			desktop[indexX][indexY] = 1;
		}
	}

	private void moveRight() {
		if(desktop[indexX][indexY + 1] == 3) {
			if(desktop[indexX][indexY + 2] == 0 || desktop[indexX][indexY + 2] == 4) {
				desktop[indexX][indexY + 1] = 0;
				desktop[indexX][indexY + 2] = 3;
			}
		}
		if(desktop[indexX][indexY + 1] == 0 || desktop[indexX][indexY + 1] == 4) {
			desktop[indexX][indexY] = 0;
			indexY = indexY + 1;
			desktop[indexX][indexY] = 1;
		}
	}

	private void moveDown() {
		if(desktop[indexX + 1][indexY] == 3) {
			if(desktop[indexX + 2][indexY] == 0 || desktop[indexX + 2][indexY] == 4) {
				desktop[indexX + 1][indexY] = 0;
				desktop[indexX + 2][indexY] = 3;
			}
		}
		if(desktop[indexX + 1][indexY] == 0 || desktop[indexX + 1][indexY] == 4) {
			desktop[indexX][indexY] = 0;
			indexX = indexX + 1;
			desktop[indexX][indexY] = 1;
		}
	}


	private void checkGoal() {
		for(int a = 0; a < arrayOfFourIndexies[0].length; a++) {
			int x = arrayOfFourIndexies[0][a];
			int y = arrayOfFourIndexies[1][a];
			if(desktop[x][y] == 0) {
				desktop[x][y] = 4;
			}
		}
	}

	private void won() {
		boolean flag = true;
		for(int a = 0; a < arrayOfFourIndexies[0].length; a++) {
			int x = arrayOfFourIndexies[0][a];
			int y = arrayOfFourIndexies[1][a];
			if(desktop[x][y] != 3) {
				flag = false;
			}
		}

		if(flag) {
			javax.swing.JOptionPane.showMessageDialog(
			new javax.swing.JFrame(),
			"You are won!!!");
			initialization();
			viewer.update();
		}
	}
	public boolean getStateModel() {
		return isOk;
	}

	public int[][] getDesktop() {
		return desktop;
	}

	private void printDesktop() {
		for(int i = 0; i < desktop.length; i++) {
			for(int j = 0; j < desktop[i].length; j++) {
				System.out.print(desktop[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("-----------------------------");
	}

	public void loadNextLevel(){
		initialization();
		viewer.update();
	}

}
