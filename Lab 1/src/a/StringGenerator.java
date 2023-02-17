package a;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringGenerator {
    private static final String[] VN = {"S", "B", "C"};
    private static final String[] VT = {"a", "b", "c"};
    private static final String[][] P = {
            {"S", "aB"},
            {"B", "aC"},
            {"C", "bB"},
            {"C", "c"},
            {"C", "aS"},
            {"B", "bB"},
    };

    private static final Random random = new Random();

    public List<String> generateValidStrings() {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String string = generateValidString();
            result.add(string);
        }

        return result;
    }

    private static String generateValidString() {
        StringBuilder builder = new StringBuilder();
        builder.append("S");
        while (true) {
            int index = builder.indexOf("S");
            if (index < 0) {
                break;
            }
            builder.deleteCharAt(index);
            builder.insert(index, P[0][1]);
        }
        while (true) {
            int index = builder.indexOf("B");
            if (index < 0) {
                break;
            }
            String replacement = P[random.nextInt(3) + 1][1];
            builder.deleteCharAt(index);
            builder.insert(index, replacement);
        }
        while (true) {
            int index = builder.indexOf("C");
            if (index < 0) {
                break;
            }
            String replacement = P[random.nextInt(2) + 4][1];
            builder.deleteCharAt(index);
            builder.insert(index, replacement);
        }
        return builder.toString();
    }
}
