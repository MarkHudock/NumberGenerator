import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

public class SortGeneratedNumbers {

	private BufferedWriter bw = null;
	private ArrayList<String> numbersGenerated = new ArrayList<String>();

	public static void main(String[] arguments) {
		new SortGeneratedNumbers();
	}

	private SortGeneratedNumbers() {
		for (int i = 7; i <= 10; i++) {
			sortGeneratedNumbers(i + " Digits");
		}
	}

	/**
	 * Sorts generated words by allocating all words from a file,
	 * Deleting the file, recreating the file, and filling it with
	 * the sorted words.
	 * 
	 * @param file The name of the file that will be sorted.
	 */
	public void sortGeneratedNumbers(String file) {
		String fileName = file;
		try {
			getAlreadyGeneratedNumbers(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Sorting numbers for " + fileName + ". ("
				+ NumberFormat.getInstance().format(numbersGenerated.size()) + " numbers found)");
		String[] data = new String[numbersGenerated.size()];
		for (int i = 0; i < numbersGenerated.size(); i++)
			data[i] = numbersGenerated.get(i);
		Arrays.sort(data);
		try {
			clearList(fileName);
			bw = new BufferedWriter(new FileWriter(
					".\\Results\\" + fileName + ".txt", true));
			for (int i = 1; i < data.length; i++) {
				bw.write("" + data[i]);
				bw.newLine();
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finished sorting for " + fileName + ". ("
				+ NumberFormat.getInstance().format(numbersGenerated.size()) + ")\n");
		numbersGenerated.clear();
	}

	/**
	 * Reads all of the words from file and adds them to an ArrayList.
	 * 
	 * @param fileName The name of the file to get the words from.
	 * @throws IOException
	 */
	private void getAlreadyGeneratedNumbers(String fileName) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				".\\Results\\" + fileName + ".txt"));
		String line;
		StringBuilder result = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) {
			result.append(line + ":");
		}
		String[] f = result.toString().split(":");
		for (int i = 0; i < f.length; i++)
			numbersGenerated.add(f[i]);
		bufferedReader.close();
	}

	/**
	 * Deletes the initial file in the path and recreates it.
	 * 
	 * @param fileName The name of the file that will be deleted and recreated.
	 */
	private void clearList(String fileName) throws IOException {
		File fileToDelete = new File(
				".\\Results\\" + fileName + ".txt");
		boolean deleted = fileToDelete.delete();
		if (!deleted) {
			System.out.println("Deletion failed for " + fileName + ". Destructing in 2 minutes.");
			System.exit(120);
		}
		File reCreatedFile = new File(
				".\\Results\\" + fileName + ".txt");
		reCreatedFile.createNewFile();
	}

}