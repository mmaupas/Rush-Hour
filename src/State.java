import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files



public class State {
	private int size;
	private int num_vehicles;
	private Vehicle[] vehicles;
	
	
	State(String data) {
		//Constructor that takes data from a string
		
	    int j = 0;
	    
	    int[] r = getInt(data, j);
	    j = r[1] + 1;
	    size = r[0];
	    
	    r = getInt(data, j);
	    j = r[1] + 1;
	    num_vehicles = r[0];
	    vehicles = new Vehicle[r[0]];
	    
	    //System.out.println(size);
	    
	    for(int i=0 ; i<num_vehicles ; i++) {
	    	r = getInt(data, j);
		    j = r[1] + 1;
		    int id = r[0];
		    
		    boolean o = data.charAt(j) == 'h';;
		    j = j + 2;

		    r = getInt(data, j);
		    j = r[1] + 1;
		    int l = r[0];
		    
		    r = getInt(data, j);
		    j = r[1] + 1;
		    int x = r[0];
		    
		    r = getInt(data, j);
		    j = r[1] + 1;
		    int y = r[0];
		    
		    //System.out.println(id);
		    
		    vehicles[i] = new Vehicle(id, o, l, x, y);
	    }
	}
	
	public static State readFile(String filename) {
		//reads a file and outputs a State that corresponds
		String data = new String();
	    try {
	    	File myObj = new File(filename);
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	        	data += myReader.nextLine() + " ";
	        }
	        myReader.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	    //System.out.println(data);
	    return new State(data);
	}
	
	
	
	public boolean isValid() {
		//checks if the state is valid
		boolean[][] t = new boolean[size][size];
		for(Vehicle v : vehicles) {
			if(v.getOrientation()) {
				for(int i=0 ; i<v.getLength() ; i++) {
					if(t[v.getX()+i][v.getY()])return false;
					else t[v.getX()+i][v.getY()] = true;
				}
			}
			if(!v.getOrientation()) {
				for(int i=0 ; i<v.getLength() ; i++) {
					if(t[v.getX()][v.getY()+i])return false;
					else t[v.getX()][v.getY()+i] = true;
				}
			}
		}
		return true;
	}
	
	
	public static boolean isValid(String filename) {
		//checks if the state in the file is valid
		return readFile(filename).isValid();
	}
	public static int[] getInt(String s, int i) {
		//Get the value of an integer in a string at location i
		int j = i;
		int n = 0;
		while(j < s.length() && s.charAt(j) != ' ') {
			n = 10*n + (int)s.charAt(j)-48;
			j++;
		}
		int[] a = {n, j};
		return a;
	}
	
	public void display() {
		//displays the state (doesn't work for more than 10 vehicles)
		int[][] t = new int[size][size];
		for(Vehicle v : vehicles) {
			if(v.getOrientation()) {
				for(int i=0 ; i<v.getLength() ; i++) {
					t[v.getX()+i-1][v.getY()-1] = v.getId(); 
				}
			}
			if(!v.getOrientation()) {
				for(int i=0 ; i<v.getLength() ; i++) {
					t[v.getX()-1][v.getY()+i-1] = v.getId();
				}
			}
		}
		for(int i=0 ; i<size ; i++) {
			for(int j=0 ; j<size ; j++) {
				System.out.print(t[i][j]);
			}
			System.out.println();
		}
	}

	public int getNum_V() {return num_vehicles;}
	public int getSize() {return size;}
	public Vehicle[] getVehicules() {return vehicles;}
}
