import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class Level1 {
    static String original = "";
    static ArrayList<String> changes = new ArrayList<>(Arrays.asList(original));

    static int iteration = 0;
    static int undoIteration = 0;
    static int redoIteration = 0;
    static int sms = 0;
    public static String BastShoe(String command) {
        String[] s = command.split(" ");
        int N = Integer.valueOf(s[0]);

        if (N == 1) addString(command);
        if (N == 2) deleteString(Integer.valueOf(s[1]));
        if (N == 3) return getString(Integer.valueOf(s[1]));
        if (N == 4) {
            sms = 1;
            redoIteration = 0;
            iteration--;
            undoIteration++;
            if (iteration <= 0) {
                iteration = 0;
            }
            original = changes.get(iteration);
        }
        if (N == 5 && redoIteration >= 0) {
            redoIteration = iteration + 1;
            iteration++;
            if (redoIteration >= changes.size()) {
                redoIteration = changes.size() - 1;
                iteration--;
            }
            original = changes.get(redoIteration);
        }

        return original;
    }
    public static ArrayList<String> stringRemove (int undoIteration, ArrayList <String> changes) {
        int f = changes.size();
        for (int y = 0; y < f; y++) {
            changes.remove(0);
        }
        changes.add(original);
        iteration = 0;
        return changes;
    }
    public static String addString (String command) {
        if (sms == 1) {
            stringRemove(undoIteration, changes);
            }

        sms = 0;
        int start = 2;
        int end = command.length();
        char[] buf = new char[end - start];
        command.getChars(start,command.length(), buf, 0);
        original += new String(buf);
        changes.add(original);
        iteration++;
        redoIteration = -1;
        return original;
    }
    public static String deleteString (int x) {
        if (sms == 1) {
            stringRemove(undoIteration, changes);
        }
        sms = 0;
        if (x > original.length()) {
            original = original.substring(0, 0);
            changes.add(original);
            sms = 0;
            iteration++;
            return original;
        }

        original = original.substring(0, original.length() - x);
        changes.add(original);
        iteration++;
        return original;
    }
    public static String getString (int x) {
    if (x >= original.length()) return "";

    char c = original.charAt(x);
    String s = new String(new char[]{c});
    return s;
    }
}

