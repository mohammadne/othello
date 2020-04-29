import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class InputOutPut {
    static int getInput() {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        int num1 = ((int) str.charAt(0)) - 1 - 48;
        char character = str.charAt(1);
        int num2 = 0;
        if (character == 'A' || character == 'a') num2 = 0;
        else if (character == 'B' || character == 'b') num2 = 1;
        else if (character == 'C' || character == 'c') num2 = 2;
        else if (character == 'D' || character == 'd') num2 = 3;
        else if (character == 'E' || character == 'e') num2 = 4;
        else if (character == 'F' || character == 'f') num2 = 5;
        else if (character == 'G' || character == 'g') num2 = 6;
        else if (character == 'H' || character == 'h') num2 = 7;

        return (num1 * 10 + num2);
    }

    static void printOutPut(Map<Integer, Map<String, Integer>> available) {
        Map<String, Map<String, Integer>> newAvailable = new HashMap<>();
        for (Map.Entry<Integer, Map<String, Integer>> entry : available.entrySet()) {
            StringBuilder str = new StringBuilder();
            int key = entry.getKey();
            Map<String, Integer> value = entry.getValue();
            int a = key / 10 + 1;
            str.append(a);
            char b = 'A';

            if (key / 10 >= 1) {
                if ((key % 10) == 0) b = 'A';
                else if ((key % 10) == 1) b = 'B';
                else if ((key % 10) == 2) b = 'C';
                else if ((key % 10) == 3) b = 'D';
                else if ((key % 10) == 4) b = 'E';
                else if ((key % 10) == 5) b = 'F';
                else if ((key % 10) == 6) b = 'G';
                else if ((key % 10) == 7) b = 'H';
            }
            str.append(b);
            newAvailable.put(str.toString(), value);
        }
        System.out.print(newAvailable.toString());
    }
}
