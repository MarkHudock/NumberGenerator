import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

public class NumberGenerator {

	/**
	 * The main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			// Update the timer if provided by args.
			if (args.length > 0 && args.length < 2) {
				timer = Integer.parseInt(args[0]);
			}

			// Update the length if provided by args.
			if (args.length > 1 && args.length < 3) {
				length = Integer.parseInt(args[1]);
				predefinedLength = args[1];
			}
			new NumberGenerator();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates a randomly lengthened, randomized word.
	 * Gets already generated words from all files.
	 * Checks to see if the generated word was already generated.
	 * Adds the generated word to the list.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	private NumberGenerator() throws InterruptedException, IOException {
		getAlreadyGeneratedNumbers();
		System.out.println();
		System.out.println("Generating numbers...");
		System.out.println();
		random = new Random();
		while (true) {// Loops until termination.
			if (getArrayList().size() >= Math.pow(10, length)) {
				System.out.println("Word limit reached for " + length + " digit numbers.");
				return;
			}
			String number = generateRandomNumber();
			while (getArrayList().contains(number)) {
				System.out.println("Number already generated: " + number + ". ("
						+ NumberFormat.getInstance().format(getArrayList().size()) + ")");
				number = generateRandomNumber();
			}
			getArrayList().add(number);
			System.out.println(number);
			System.out.println(length + " Digits: " + NumberFormat.getInstance().format(getArrayList().size()) + "/"
					+ NumberFormat.getInstance().format(((int) Math.pow(10, length))) + "\n");
			BufferedWriter bufferedWriter = new BufferedWriter(
					new FileWriter(".\\Results\\" + length + " Digits.txt", true));
			bufferedWriter.write(number);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();

			Thread.sleep(timer); // Timer between generations.
		}
	}

	/**
	 * Generates a random number by selecting a different character
	 * each time if the word is not as long as the length.
	 * 
	 * @return The generated number.
	 */
	private String generateRandomNumber() {
		final String[] digits = {
				"0", "1", "2", "3", "4",
				"5", "6", "7", "8", "9"
		};
		length = predefinedLength.equalsIgnoreCase("all") ? 7 + random.nextInt(4) : Integer.parseInt(predefinedLength);
		String generatedNumber = "";
		for (int i = 1; i <= length; i++) {
			generatedNumber += digits[random.nextInt(digits.length)];
		}
		return generatedNumber;
	}

	/**
	 * Returns the proper ArrayList for the value of length.
	 * 
	 * @return The correct ArrayList for length.
	 */
	private ArrayList<String> getArrayList() {
		switch (length) {
			case 1:
				return oneDigitNumbers;
			case 2:
				return twoDigitNumbers;
			case 3:
				return threeDigitNumbers;
			case 4:
				return fourDigitNumbers;
			case 5:
				return fiveDigitNumbers;
			case 6:
				return sixDigitNumbers;
			case 7:
				return sevenDigitNumbers;
			case 8:
				return eightDigitNumbers;
			case 9:
				return nineDigitNumbers;
		}
		return null;
	}

	/**
	 * Collaborates all numbers previously generated from all files
	 * and converts them to separate ArrayLists.
	 * 
	 * @throws IOException
	 */
	private void getAlreadyGeneratedNumbers() throws IOException {
		int finish = 10;
		if (!predefinedLength.equalsIgnoreCase("all")) {
			length = Integer.parseInt(predefinedLength);
			finish = Integer.parseInt(predefinedLength);
		}
		for (int i = length; i <= finish; i++) {// Loop through all unfinished lists.
			BufferedReader bufferedReader = null;
			String filePath = ".\\Results\\" + length + " Digits.txt";
			try {
				bufferedReader = new BufferedReader(new FileReader(
						filePath));
			} catch (FileNotFoundException e) {
				FileWriter writer = new FileWriter(filePath);
				writer.close();
				bufferedReader = new BufferedReader(new FileReader(
						filePath));
			}
			String line;
			StringBuilder result = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line + "~");
			}
			String[] f = result.toString().split("~");
			System.out.println("Collecting data list of " + length + " Digits...");
			for (int j = 0; j < f.length; j++) {
				getArrayList().add(f[j]);
			}
			System.out.println(
					"Finished making list. (Generated numbers: "
							+ NumberFormat.getInstance().format(getArrayList().size()) + ")");
			// length++;
			bufferedReader.close();
		}
	}

	private ArrayList<String> oneDigitNumbers = new ArrayList<String>();
	private ArrayList<String> twoDigitNumbers = new ArrayList<String>();
	private ArrayList<String> threeDigitNumbers = new ArrayList<String>();
	private ArrayList<String> fourDigitNumbers = new ArrayList<String>();
	private ArrayList<String> fiveDigitNumbers = new ArrayList<String>();
	private ArrayList<String> sixDigitNumbers = new ArrayList<String>();
	private ArrayList<String> sevenDigitNumbers = new ArrayList<String>();
	private ArrayList<String> eightDigitNumbers = new ArrayList<String>();
	private ArrayList<String> nineDigitNumbers = new ArrayList<String>();

	private static int length = 7;
	private static String predefinedLength = "7";

	private static int timer = 500;

	private Random random;

}