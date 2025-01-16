package word_ladder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class WordLadderSolver {
	
	public static void main(String[] args) {
		HashMap<String, String[]> wordConnections = Parser.getWordConnections();

		Scanner in = new Scanner(System.in);

		boolean running = true;
		while (running) {
			System.out.print("Enter your initial word and your target word: ");
			String initial = in.next().toUpperCase();

			if (initial.equals("-1"))
				break;

			String target = in.next().toUpperCase();

			List<String> solution = findSolution(initial, target, wordConnections);

			for (String word : solution) {
				System.out.println(word);
			}

			System.out.println(target);

		}

		in.close();
	}

	public static List<String> findSolution(String initial, String target, HashMap<String, String[]> wordConnections) {
		Queue<String> queue = new LinkedList<>();
		HashMap<String, String> ancestorMap = new HashMap<>();

		queue.add(initial);
		ancestorMap.put(initial, null);

		boolean found = false;
		while (!found && !queue.isEmpty()) {
			String cur = queue.poll();

			String[] adjacent = wordConnections.get(cur);

			for (int i = 0; i < adjacent.length; i++) {
				String next = adjacent[i];

				if (ancestorMap.containsKey(next))
					continue;

				queue.add(next);
				ancestorMap.put(next, cur);

				if (next.equals(target)) {
					found = true;
					break;
				}
			}
		}

		List<String> list = new LinkedList<>();

		if (found) {
			String cur = target;
			while (!cur.equals(initial)) {
				cur = ancestorMap.get(cur);
				list.add(0, cur);
			}
		}

		return list;
	}
}
