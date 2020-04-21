package uniq;


import java.nio.file.Path;
import java.util.*;


public class Uniq {

    boolean ignore;
    boolean unique;
    boolean replaced;
    int num;
    ArrayList<String> inputStrings;
    ArrayList<String> strings;
    ArrayList<Integer> numbers;


    public Uniq(Boolean ignore, Boolean unique, Boolean replaced, Integer num,
                ArrayList<String> inputStrings, ArrayList<String> strings, ArrayList<Integer> numbers) {
        this.ignore = ignore;
        this.unique = unique;
        this.replaced = replaced;
        this.num = num;
        this.inputStrings = inputStrings;
        this.strings = strings;
        this.numbers = numbers;
    }

    public void ret(){
        if (inputStrings.size() != 0)
        stringsList();
    }

    //Список строк и кол-ва их повторений подряд
    private void stringsList() {
        strings.add(inputStrings.get(0));

        numbers.add(1);

        int k = 0;
        int num = 1;

        for (int i = 0; i < inputStrings.size() - 1; i++) {
            if (ignoreCase(inputStrings.get(i), inputStrings.get(i + 1)) == 0) {
                num++;
                numbers.set(k, num);

            } else {

                strings.add(inputStrings.get(i + 1));
                num = 1;
                numbers.add(1);
                k++;

            }

        }
    }

    //-i & -s
    private int ignoreCase(String str1, String str2) {
        if (num > 0) {
            str1 = str1.length() <= num ? "" : str1.substring(num);

            str2 = str2.length() <= num ? "" : str2.substring(num);
        }

        if (ignore)
            return str1.compareToIgnoreCase(str2);
        else
            return str1.compareTo(str2);
    }

    //-u
    private ArrayList<String> uniqueStrings() {
        ArrayList<String> uniq = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {

            /*
            equals(strings, strings.get(i)) < 2 проверяется, потому что в строке вида:
             A
             B
             A
             C
             C

             "A" - не является уникальной, потому что написана два раза.
             Но они не идут подряд и списки numbers "-" strings выглядит так:
             1 - A
             1 - B
             1 - A
             2 - C

             Поэтому я и ввел equals()
             */

            if (numbers.get(i) == 1 && equals(strings.get(i)) < 2) {
                uniq.add(strings.get(i));
            }
        }
        return uniq;
    }

    //Проверяет, входит ли строка больше одного раза НЕ подряд
    private int equals(String str) {
        int p = 0;

        for (String s : strings)

            if (s.equals(str))
                p++;

        return p;
    }
}
