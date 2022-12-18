
public class Main {

	public static void main(String[] args) {
		State state = State.readFile("./src/data");
		state.display();
	}
}
