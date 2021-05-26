import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Words {


    public static void main(String[] args) {

        //Φέρε το κείμενο ως ένα Array απο String
        String[] words = getWords();

        //Τύπωσε ποιο είναι το μήκος της κάθε λέξης του κειμένου.
        countWordsLength(words);

        System.out.println();
        System.out.println("-----------");
        System.out.println();

        //Τύπωσε πόσες φορές εμφανίζεται η κάθε λέξη στο κείμενο.
        countWordsOccurrences(words);

        System.out.println();
        System.out.println("-----------");
        System.out.println();

        //Τύπωσε τη μεγαλυτερη σε μήκος λέξη και το μήκος της.
        findLongestWord(words);

        System.out.println();
        System.out.println("-----------");
        System.out.println();

        //Τύπωσε τη λέξη που εμφανίζεται περισσότερες φορές στο κείμενο και πόσες είναι.
        findMostOccurredWord(words);

        System.out.println();
        System.out.println("-----------");
        System.out.println();

        //Τύπωσε τις διακριτές λέξεις που εμφανίζονται στο κείμενο
        // καθώς και τις διπλότυπες με τη σειρά εμφάνισης τους
        countDistinctAndPrintNonDistinctWords(words);

        System.out.println();
        System.out.println("-----------");
        System.out.println();

        //Τύπωσε τις διακριτές λέξεις που εμφανίζονται στο κείμενο με λεξικογραφική (φυσική) σειρά
        printDistinctWordsInLexicographicOrder(words);

        System.out.println();
        System.out.println("-----------");
        System.out.println();

        //Τύπωσε τις διακριτές λέξεις με τη σειρά που εμφανίζονται στο κείμενο
        // αφου αφαιρεσεις (φιλτραρεις) οσες εμφανίζονται ακριβώς 3 φορές
        removeWordsThatOccurExactlyThreeTimes(words);

        System.out.println();
        System.out.println("-----------");
        System.out.println();

        //Τύπωσε με λεξικογραφική (φυσική) σειρά τις διακριτές λέξεις που εμφανίζονται στο κείμενο
        // αφου αφαιρεσεις (φιλτραρεις) οσες εχουν μήκος μεγαλύτερο απο 3 γράμματα
        removeWordsLongerThanThreeLetters(words);

    }

    private static String[] getWords() {
        String jfk = "We choose to go to the moon. " +
                "We choose to go to the moon in this decade and do the other things, " +
                "not because they are easy, " +
                "but because they are hard, " +
                "because that goal will serve to organize and measure the best of our energies and skills, " +
                "because that challenge is one that we are willing to accept, " +
                "one we are unwilling to postpone, " +
                "and one which we intend to win, " +
                "and the others, too.";

        return jfk
                .toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .split(" ");
    }

    private static void countWordsLength(String[] words) {
        Arrays.stream(words).
                forEach(word -> System.out.println("Length of word '" + word + "' is: " + word.length()));
    }

    private static void countWordsOccurrences(String[] words) {
        Arrays.stream(words)
                .distinct()
                .forEach(word -> System.out.println("Total occurrences of word '" + word + "' are: " + Arrays.stream(words).filter(w -> w.equals(word)).count()));
    }

    private static void findLongestWord(String[] words) {
        Arrays.stream(words)
                .filter(word -> word.length() == (Arrays.stream(words).max(Comparator.comparingInt(String::length))).get().length())
                .forEach(word -> System.out.println("Longest word: " + word));
    }

    private static void findMostOccurredWord(String[] words) {
        System.out.println("Most occurred word is: " +
                Arrays.stream(words)
                        .distinct()
                        .max((w1, w2) -> (int) (Arrays.stream(words).filter(word1 -> word1.equals(w1)).count() - Arrays.stream(words).filter(word2 -> word2.equals(w2)).count()))
                        .get());
    }

    private static void countDistinctAndPrintNonDistinctWords(String[] words) {
        System.out.println("Distinct words appearing in text:");
        System.out.println();

        Arrays.stream(words)
                .filter(w -> Collections.frequency(Arrays.asList(words), w) == 1)
                .forEach(System.out::println);

        System.out.println();

        System.out.println("Non distinct words in text insertion order: ");
        Arrays.stream(words)
                .filter(w -> Collections.frequency(Arrays.asList(words), w) > 1)
                .distinct()
                .forEach(System.out::println);
    }

    private static void printDistinctWordsInLexicographicOrder(String[] words) {
        System.out.println("Distinct words in lexicographic order: \n");
        Arrays.stream(words)
                .filter(w -> Collections.frequency(Arrays.asList(words), w) == 1)
                .sorted()
                .forEach(System.out::println);
    }

    private static void removeWordsThatOccurExactlyThreeTimes(String[] words) {
        System.out.println("Distinct words in natural order (words with 3 occurrences are removed):\n");
        Arrays.stream(words)
                .filter(w -> Collections.frequency(Arrays.asList(words), w) != 3)
                .filter(w -> Collections.frequency(Arrays.asList(words), w) == 1)
                .forEach(System.out::println);
    }

    private static void removeWordsLongerThanThreeLetters(String[] words) {
        System.out.println("Distinct words in lexicographic order (words with length bigger than 3 are removed):\n");
        Arrays.stream(words)
                .filter(word -> word.length() < 4)
                .filter(w -> Collections.frequency(Arrays.asList(words), w) == 1)
                .sorted()
                .forEach(System.out::println);
    }
}
