package task2.task23;

import java.util.Objects;

public class Task23 {

    public static void main(String[] args) {
        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel"));  // true
        System.out.println(fuzzySearch("cwhl", "cartwheel")); // true
        System.out.println(fuzzySearch("cwhee", "cartwheel")); // true
        System.out.println(fuzzySearch("cartwheel", "cartwheel")); // true
        System.out.println(fuzzySearch("cwheeel", "cartwheel")); // false
        System.out.println(fuzzySearch("lw", "cartwheel")); // false
        System.out.println(fuzzySearch("carw", "ca")); // false
        System.out.println(fuzzySearch("", "cartwheel")); // false
        System.out.println(fuzzySearch("car", "")); // false
        System.out.println(fuzzySearch(null, "")); // npe
        System.out.println(fuzzySearch("car", null)); // npe
        System.out.println(fuzzySearch(null, null)); // npe

    }

    private static boolean fuzzySearch(String a, String b) {

        if (Objects.isNull(a) || Objects.isNull(b)) {
            System.out.println("Ошибка! Оба аргумента должны являться действительными строками");
            return false;
        } else {

            int testStrIdx = 0;
            for (int checkStrIdx = 0; checkStrIdx < a.length(); checkStrIdx++) {
                char chekingSymbol = a.charAt(checkStrIdx);
                for (; testStrIdx < b.length(); testStrIdx++) {
                    char testingSymbol = b.charAt(testStrIdx);
                    if (testingSymbol == chekingSymbol) {
                        if (checkStrIdx == a.length() - 1) { //end of string A (checking) is reached
                            return true;
                        } else {
                            testStrIdx++;
                            break;
                        }
                    } else {
                        if (testStrIdx == b.length() - 1) { //end of string B (testing) is reached
                            return false;
                        }
                    }
                }
            }
            return false;
        }
    }
}
