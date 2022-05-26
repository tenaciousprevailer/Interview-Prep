package _5_Questions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    interface ISol {
        boolean isValidAnagram(String s1, String s2);
    }

    static class ValidAnagramImpl implements ISol {

        @Override
        public boolean isValidAnagram(String s1, String s2) {
            if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
            Map<Character, Integer> charMap = new HashMap<>();
            for (int i = 0; i < s1.length(); i++) {
                char c = s1.charAt(i);
                Integer count = charMap.get(c);
                if(count == null) {
                    count = 0;
                }
                count++;
                charMap.put(c, count);
            }

            for (int i = 0; i < s2.length(); i++) {
                char c = s2.charAt(i);
                Integer count = charMap.get(c);
                if(count == null) {
                    return false;
                }
                count--;
                if(count < 1) {
                    charMap.remove(c);
                } else {
                    charMap.put(c, count);
                }
            }

            return charMap.size() == 0;
        }
    }

}


class AnagramTest {
    private ValidAnagram.ISol sol;

    @BeforeEach
    public void setup() {
        sol = new ValidAnagram.ValidAnagramImpl();
    }

    /**
     1. when any string is null -> return false
     2. when string are of diff length -> return false
     3. string of same length, but different value -> return false
     4. same string -> return true;
     5. true anagram -> return true
     */

    @Test
    public void isValidAnagram_whenOneStringIsNull_thenReturnFalse() {
        Assertions.assertFalse(sol.isValidAnagram(null, ""));
        Assertions.assertFalse(sol.isValidAnagram("", null));
    }

    @Test
    public void isValidAnagram_whenString2IsNullButNotTheSecond_thenReturnFalse() {
        Assertions.assertFalse(sol.isValidAnagram(null, ""));
    }

}
