// ClosedHashing.java
// Handles hashing parts of PA5

import java.util.*;
import java.io.*;

public class ClosedHashing {
    static final int C = 123;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();

        System.out.print("Enter table size: ");
        int tableSize = scanner.nextInt();
        scanner.nextLine(); // eat newline

        String[] table = new String[tableSize];
        Arrays.fill(table, null);

        HashSet<String> seenWords = new HashSet<>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("[^a-zA-Z'-]+");
            for (String word : words) {
                if (word.isEmpty()) continue;
                if (seenWords.contains(word)) continue;

                int hashValue = computeHash(word, tableSize);
                int address = hashValue;

                // Linear probing
                while (table[address] != null) {
                    address = (address + 1) % tableSize;
                }

                table[address] = word;
                seenWords.add(word);
            }
        }

        reader.close();

        // Output the hash table
        for (int i = 0; i < tableSize; i++) {
            if (table[i] != null) {
                int hashVal = computeHash(table[i], tableSize);
                System.out.println(i + ", " + table[i] + ", " + hashVal);
            }
        }

        analyzeTable(table);
    }

    static int computeHash(String word, int m) {
        int h = 0;
        for (int i = 0; i < word.length(); i++) {
            h = (h * C + word.charAt(i)) % m;
        }
        return h;
    }

    static void analyzeTable(String[] table) {
        int tableSize = table.length;

        // Part a: Load factor
        int nonEmpty = 0;
        for (String word : table) {
            if (word != null) nonEmpty++;
        }
        double loadFactor = (double) nonEmpty / tableSize;
        System.out.println("\nNon-empty addresses: " + nonEmpty);
        System.out.println("Load Factor: " + loadFactor);

        // Part b: Longest empty area
        int maxEmpty = 0, currentEmpty = 0;
        int startEmpty = -1, endEmpty = -1, tempStart = -1;
        for (int i = 0; i < 2 * tableSize; i++) {
            int idx = i % tableSize;
            if (table[idx] == null) {
                if (currentEmpty == 0) tempStart = idx;
                currentEmpty++;
                if (currentEmpty > maxEmpty) {
                    maxEmpty = currentEmpty;
                    startEmpty = tempStart;
                    endEmpty = idx;
                }
            } else {
                currentEmpty = 0;
            }
        }
        System.out.println("Longest empty area: " + maxEmpty + " slots (from " + startEmpty + " to " + endEmpty + ")");

        // Part c: Longest cluster
        int maxCluster = 0, currentCluster = 0;
        int startCluster = -1, endCluster = -1, tempCStart = -1;
        for (int i = 0; i < 2 * tableSize; i++) {
            int idx = i % tableSize;
            if (table[idx] != null) {
                if (currentCluster == 0) tempCStart = idx;
                currentCluster++;
                if (currentCluster > maxCluster) {
                    maxCluster = currentCluster;
                    startCluster = tempCStart;
                    endCluster = idx;
                }
            } else {
                currentCluster = 0;
            }
        }
        System.out.println("Longest cluster: " + maxCluster + " slots (from " + startCluster + " to " + endCluster + ")");

        // Part d: Hash value with most distinct words
        HashMap<Integer, Integer> hashCount = new HashMap<>();
        for (String word : table) {
            if (word != null) {
                int h = computeHash(word, tableSize);
                hashCount.put(h, hashCount.getOrDefault(h, 0) + 1);
            }
        }
        int maxHashFreq = 0, maxHash = -1;
        for (var entry : hashCount.entrySet()) {
            if (entry.getValue() > maxHashFreq) {
                maxHashFreq = entry.getValue();
                maxHash = entry.getKey();
            }
        }
        System.out.println("Most common hash value: " + maxHash + " (" + maxHashFreq + " words)");

        // Part e: Word with maximum drift
        int maxDrift = 0;
        String worstWord = "";
        for (int i = 0; i < tableSize; i++) {
            if (table[i] != null) {
                int originalHash = computeHash(table[i], tableSize);
                int drift = (i - originalHash + tableSize) % tableSize;
                if (drift > maxDrift) {
                    maxDrift = drift;
                    worstWord = table[i];
                }
            }
        }
        System.out.println("Word with max drift: '" + worstWord + "' drifted " + maxDrift + " slots.");
    }
}
