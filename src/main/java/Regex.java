import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Regex {

    public static void main(String[] args) throws IOException {
//        extractContacts();
//        readContacts();
        removeDuplicates();
    }

    private static void removeDuplicates() throws IOException {
        File file = new ClassPathResource("finalcontacts").getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> lines = bufferedReader.lines().collect(Collectors.toList());
        HashSet<String> strings = new HashSet<>(lines);
        System.out.println(lines.size());
        System.out.println(strings.size());
        System.out.println(strings);
    }

    private static void readContacts() throws IOException {
        File file = new ClassPathResource("contacts").getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        List<String> lines = bufferedReader.lines().collect(Collectors.toList());

        HashSet<String> strings = new LinkedHashSet<>();
        for (String data : lines) {
            String[] split = data.split(",");
            for (String s : split) {
                String trim = s.trim()
                        .replace("[", "")
                        .replace("]", "");
                if (trim.length() >= 10)
                strings.add(trim);
            }
        }
        System.out.println(strings);
    }

    private static void extractContacts() throws IOException {
        File file = new ClassPathResource("doc1").getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String data = bufferedReader.readLine();
        List<String> allMatches = new ArrayList<>();
        Matcher m = Pattern.compile("91[0-9 ]*")
                .matcher(data);
        while (m.find()) {
            allMatches.add(m.group());
        }
        System.out.println(allMatches);
    }
}
