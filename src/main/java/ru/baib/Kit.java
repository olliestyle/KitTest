package ru.baib;

import java.util.*;
import java.util.regex.Pattern;

public class Kit {

    public int[][] toIntArray(String[] indexes) {
        int[][] res = new int[indexes.length][];
        for (int i = 0; i < indexes.length; i++) {
            String[] temp = indexes[i].split(",");
            StringBuilder sb = extract(temp);
            String[] numbers = sb.toString().split(",");
            res[i] = new int[numbers.length];
            for (int j = 0; j < numbers.length; j++) {
                res[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        return res;
    }

    public List<List<Integer>> combine(int[][] array) {
        Map<Integer, List<Integer>>  source = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            source.put(i, new ArrayList<>());
            for (int j = 0; j < array[i].length; j++) {
                source.get(i).add(array[i][j]);
            }
        }
        Combinator<Integer, Integer> combinator = new Combinator<>(source);
        return combinator.makeCombinations();
    }

    private StringBuilder extract(String[] str) {
        Pattern pattern = Pattern.compile("^[\\d,-]*$");
        StringBuilder sb = new StringBuilder();
        for(String s: str) {
            if (!pattern.matcher(s).matches()) {
                throw new IllegalArgumentException("\"indexes\" must contain only digits, commas and dashes");
            } else {
                if (s.contains("-")) {
                    sb.append(dashConvert(s));
                } else {
                    sb.append(s).append(",");
                }
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb;
    }

    private StringBuilder dashConvert(String str) {
        String[] s = str.split("-");
        int first = Integer.parseInt(s[0]);
        int second = Integer.parseInt(s[1]);
        StringBuilder temp = new StringBuilder();
        for (int i = first; i <= second; i++) {
            temp.append(i).append(",");
        }
        return temp;
    }
}
