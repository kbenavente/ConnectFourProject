import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ConnectFourDisc extends Circle {
	
	private final static double DISK_RADIUS = 28;
	private static Color currentColor = Color.RED;
	
	private int x;
	private int y;
	private boolean isEmpty = true;
	private Color color;
	private static int turnID = 0;
	
	public ConnectFourDisc() {
		
		super(DISK_RADIUS, Color.WHITE);
		this.isEmpty = true;
		this.color = Color.WHITE;
		
	}
	
	public ConnectFourDisc(Color color) {
		
		super(DISK_RADIUS, color);
		
		this.isEmpty = false;
		this.color = color;
		
	}
	
	public ConnectFourDisc(Color color, double radius) {
		
		super(radius, color);
		
		this.isEmpty = false;
		
	}
	
	public Color getColor() {
		
		return this.color;
		
	}

	public void setCurrentColor(Color currentColor) {
		
		ConnectFourDisc.currentColor = currentColor;
		
	}
	
	public static Color getCurrentColor() {
		
		if(turnID % 2 == 0)
			currentColor = Color.RED;
		else
			currentColor = Color.GOLD;
		
		return currentColor;
		
	}
	
	public static void resetTurnID() {
		
		turnID = 0;
		
	}
	
	public boolean isEmpty() {
		
		return this.isEmpty;
		
	}
	
	public void setIsEmpty(boolean isEmpty) {
		
		this.isEmpty = isEmpty;
		
	}
	
	public static int getTurnID() {
		
		return turnID;
		
	}
	
	public static void incrementTurnID() {
		
		turnID++;
		
	}
	
	public void setX(int x) {
		
		this.x = x;
		
	}
	
	public void setY(int y) {
		
		this.y = y;
		
	}
	
	public int getX() {
		
		return this.x;
		
	}

	public int getY() {
		
		return this.x;
		
	}
	
}
