
public class Vehicle {
	private int id;
	private boolean orientation; //true : horizontal ; false : vertical
	private int length;
	private int x, y;
	
	
	Vehicle(int i, boolean o, int l, int x, int y){
		id = i;
		orientation = o;
		length = l;
		this.x = x;
		this.y = y;
	}
	
	public int getLength(){return length;}
	public boolean getOrientation(){return orientation;}
	public int getId(){return id;}
	public int getX(){return x;}
	public int getY(){return y;}
	
}
