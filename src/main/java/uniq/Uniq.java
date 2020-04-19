package uniq;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Uniq {

    boolean ignore;
    boolean unique;
    boolean replaced;
    int num;
    String outputName;
    ArrayList<String> strings = new ArrayList<>();
    ArrayList<String> numbers = new ArrayList<>();


    public Uniq(Boolean ignore, Boolean unique, Boolean replaced, Integer num, String outputName) {
        this.ignore = ignore;
        this.unique = unique;
        this.replaced = replaced;
        this.num = num;
        this.outputName = outputName;
    }

    public void stringList(String inputName) throws IOException {
        BufferedReader br;
        ArrayList<String> stringList = new ArrayList<>();

        if (inputName == null) {
            System.out.println("Введите строки");
            br = new BufferedReader(new InputStreamReader(System.in));
        } else
            br = new BufferedReader(new FileReader(inputName));

        try {
            String str;
            while ((str = br.readLine()) != null) {
                stringList.add(str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedWriter writer = outputName != null ? Files.newBufferedWriter(Paths.get(outputName)) :
                new BufferedWriter(new OutputStreamWriter(System.out));
        stringsMap(stringList);

        if (unique) {
            uniqueStrings(strings, numbers);

            for (String s : uniqueStrings(strings, numbers))
                writer.write(s + "\n");

        } else for (int i = 0; i < strings.size(); i++) {

            if (replaced) {
                replacedStrings();
                writer.write(numbers.get(i) + strings.get(i) + "\n");

            } else
                writer.write(strings.get(i) + "\n");

        }

        writer.close();
    }

    //Список строк и кол-ва их повторений подряд
    private void stringsMap(ArrayList<String> str) {
        strings.add(str.get(0));
        numbers.add("1 ");
        int k = 0;
        int num = 1;
        for (int i = 0; i < str.size() - 1; i++) {
            if (ignoreCase(str.get(i), str.get(i + 1)) == 0) {
                num++;
                numbers.set(k, num + " ");
            } else {

                strings.add(str.get(i + 1));
                num = 1;
                numbers.add("1 ");
                k++;
            }
        }
    }


    //-i & -s
    private int ignoreCase(String str1, String str2) {

        if (num > 0) {
            str1 = str1.length() < num ? "" : str1.substring(num);
            str2 = str2.length() < num ? "" : str2.substring(num);
        }

        if (ignore)
            return str1.compareToIgnoreCase(str2);
        else
            return str1.compareTo(str2);
    }


    //-r
    private void replacedStrings() {
        for (String i : numbers)

            if (i.equals("1 "))
                numbers.set(numbers.indexOf(i), "");
    }

    //-u
    private ArrayList<String> uniqueStrings(ArrayList<String> str, ArrayList<String> n) {
        ArrayList<String> uniq = new ArrayList<>();

        for (int i = 0; i < n.size(); i++) {

            if (n.get(i).equals("1 ") && equals(str, str.get(i)) < 2) {
                uniq.add(str.get(i));

            }

        }

        return uniq;
    }

    //Проверяет, входит ли строка больше одного раза НЕ подряд
    private int equals(ArrayList<String> strList, String str) {
        int p = 0;

        for (String s : strList)

            if (s.equals(str))
                p++;

        return p;
    }
}
