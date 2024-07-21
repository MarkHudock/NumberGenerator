import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PickRandomNumber {

	private String randomNumber;
	private int length;

	private ArrayList<String> numbersGenerated = new ArrayList<String>();

	private PickRandomNumber() {
		Random random = new Random();
		length = random.nextInt(10);
		try {
			getAlreadyGeneratedNumbers(length + " Digits");
		} catch (IOException e) {
			e.printStackTrace();
		}
		randomNumber = numbersGenerated.get(random.nextInt(numbersGenerated.size()));
		System.out.println("Random number: " + randomNumber);
	}

	public static void main(String[] args) {
		new PickRandomNumber();
	}

	private void getAlreadyGeneratedNumbers(String fileName) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("./Results/" + fileName + ".txt"));
		String line;
		StringBuilder result = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) {
			result.append(line + "~");
		}
		String[] f = result.toString().split("~");
		for (int i = 0; i < f.length; i++) {
			numbersGenerated.add(f[i]);
		}
		bufferedReader.close();
	}

}
