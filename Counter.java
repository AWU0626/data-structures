import java.io.*;
import java.util.*;

public class Counter {

	/*
	 * This method reads the text from a file and returns a List of the
	 * distinct strings. The strings are converted to lowercase and any
	 * whitespace is removed, but punctuation is not removed.
	 */
	public static Set<String> getWords(String filename) {
		HashSet<String> allWords = new HashSet<String>();

		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = in.readLine()) != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					word = word.trim().toLowerCase();
					if (word.length() > 0) {
						allWords.add(word);
					}
				}
			}

			return allWords;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * This method reads the text from a file and returns an array of two Lists:
	 * the first List holds the individual bigrams (pairs of letters) that occur
	 * in the file; the second holds the corresponding number of occurrences of
	 * each bigram.
	 */
	public static Map<String, Integer> countBigrams(String filename) {
		Map<String, Integer> countBigrams = new HashMap<>();

		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = in.readLine()) != null) {
				String[] words = line.split(" ");

				for (String word : words) {
					for (int i = 0; i < word.length() - 1; i++) {
						char c0 = word.charAt(i), c1 = word.charAt(i + 1);

						if (c0 >= 'a' && c0 <= 'z' && c1 >= 'a' && c1 <= 'z') {
							String bigram = Character.toString(c0) + Character.toString(c1);

							if (countBigrams.containsKey(bigram)) {
								countBigrams.put(bigram, countBigrams.get(bigram) + 1);

							} else {
								countBigrams.put(bigram, 1);
							}
						}
					}
				}
			}

			return countBigrams;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * This method reads the text from a file and returns a Map of each
	 * letter ('a' through 'z') and the number of occurrences of that letter.
	 * This is case-insensitive so uppercase letters are converted to
	 * lowercase.
	 */
	public static int[] countLetters(String filename) {
		int[] countOccurrence = new int[26];

		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = in.readLine()) != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					for (char c : word.toCharArray()) {
						if (c >= 'a' && c <= 'z') {
							countOccurrence[c - 'a'] += 1;
						}
					}
				}
			}
			return countOccurrence;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/****************************************
	 * Tests
	 ****************************************/
	public static void main(String[] args) {
		String filename = "alice.txt";
		if (args.length > 0) {
			filename = args[0];
		}

		/******************************
		 * Tests getWords method
		 ******************************/
		Set<String> words = getWords(filename);

		if (words == null) {
			System.out.println("ERROR! getWords return null");
			System.exit(1);
		}

		int numWords = words.size();
		if (numWords != 5022) {
			System.out.println("ERROR! Number of words should be 5022 but is " + numWords);
			System.exit(1);
		} else {
			System.out.println("getWords test passed");
		}

		/******************************
		 * Tests countBigrams method
		 ******************************/
		Map<String, Integer> bigramCount = countBigrams(filename);
		int numBigrams = bigramCount.size(),
				countAL = bigramCount.get("al");

		if (numBigrams != 375) {
			System.out.println("ERROR! Number of bigrams should be 375 but is " + numBigrams);
			System.exit(1);
		} else if (countAL != 521) {
			System.out.println("ERROR! Bigram count for 'al' should be 521 but is " + countAL);
			System.exit(1);
		} else {
			System.out.println("countBigrams tests passed");
		}

		/******************************
		 * Tests countLetters method
		 ******************************/
		int[] count = countLetters(filename);
		int countE = count[4];

		if (countE != 13518) {
			System.out.println("ERROR! Count for 'e' should be 13518 but is " + countE);
			System.exit(1);
		} else {
			System.out.println("countLetters test passed");
		}

		System.out.println("ALL TESTS PASSED!");

	}

}
