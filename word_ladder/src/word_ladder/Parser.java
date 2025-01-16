package word_ladder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
	
public class Parser {
	public static void main(String[] args) throws IOException {
		BufferedReader in = Files.newBufferedReader(Paths.get("src/word_ladder/dictionary.txt"));
		BufferedWriter out = Files.newBufferedWriter(Paths.get("src/word_ladder/connections.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

		int numWords = Integer.parseInt(in.readLine());
		String[] words = new String[numWords];

		for (int i = 0; i < numWords; i++) {
			words[i] = in.readLine();
		}

		in.close();

		HashMap<String, ArrayList<String>> wordConnections = new HashMap<>();

		for (int i = 0; i < numWords; i++) {
			wordConnections.put(words[i], new ArrayList<>());
		}

		for (int i = 0; i < numWords; i++) {
			String cur = words[i];

			char[] charArr = cur.toCharArray();
			for (int j = 0; j < cur.length(); j++) {
				char old = charArr[j];

				for (char k = 'A'; k <= 'Z'; k++) {
					if (k == old)
						continue;

					charArr[j] = k;

					String next = new String(charArr);

					if (wordConnections.containsKey(next)) {
						wordConnections.get(cur).add(next);
					}
				}

				charArr[j] = old;
			}
		}

		out.append(numWords + "\n");
		
		for (int i = 0; i < numWords; i++) {
			ArrayList<String> adjacent = wordConnections.get(words[i]);
			adjacent.sort(String::compareTo);

			out.append(words[i] + " " + adjacent.size());

			for (int j = 0; j < adjacent.size(); j++) {
				out.append(' ').append(adjacent.get(j));
			}

			out.append('\n');
		}

		out.flush();
		out.close();

	}

	public static HashMap<String, String[]> getWordConnections() {
		Scanner in = null;
		
		try {
			in = new Scanner(Paths.get("src/word_ladder/connections.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		HashMap<String, String[]> wordConnections = new HashMap<>();

		int numWords = in.nextInt();

		for (int i = 0; i < numWords; i++) {
			String word = in.next();
			String[] adjacent = new String[in.nextInt()];

			for (int j = 0; j < adjacent.length; j++) {
				adjacent[j] = in.next();
			}
			
			wordConnections.put(word, adjacent);
		}

		in.close();

		return wordConnections;

	}
}
