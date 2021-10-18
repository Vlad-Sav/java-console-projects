package ru.krsu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;

public class WorkWithText {
    //чтение из файлов в массив texts
    public static void readFiles(ArrayList<String> namesOfFiles, String[] texts){
        for(int i = 0; i < namesOfFiles.size(); i++){
            texts[i] = readFileToString(namesOfFiles.get(i));
        }
    }
    public static String readFileToString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(new File(fileName));
             BufferedReader reader = new BufferedReader(fileReader)) {
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }
    //форматирование текста
    public static void formatTexts(String[] whereFrom, String[] where){
        for(int i = 0; i < whereFrom.length; i++){
            where[i] = formatText(whereFrom[i]);
        }
    }
    public static String formatText(String text){
        text = text.replaceAll("[^0-9\\w ]", "");
        return text.toLowerCase(Locale.ROOT);
    }
    //форматирование текста пользователя
    public static String[] formatUsersText(String text){
        return text.replaceAll("[^0-9\\w ]", "").split(" ");
    }
    public static String createLineFromWords(String[] words){
        StringBuilder res = new StringBuilder();
        for(String s: words){
            res.append(s).append(" ");
        }
        return formatText(res.toString()).strip();
    }
    //Алгоритм Кнутта-Мориса-Пратта

    static int[] prefixFunction(String sample) {
        int [] values = new int[sample.length()];
        for (int i = 1; i < sample.length(); i++) {
            int j = 0;
            while (i + j < sample.length() && sample.charAt(j) == sample.charAt(i + j)) {
                values[i + j] = Math.max(values[i + j], j + 1);
                j++;
            }
        }
        return values;
    }
    public static ArrayList<Integer> KMPSearch(String text, String sample) {
        ArrayList<Integer> found = new ArrayList<>();

        int[] prefixFunc = prefixFunction(sample);

        int i = 0;
        int j = 0;

        while (i < text.length()) {
            if (sample.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == sample.length()) {
                found.add(i - j);
                j = prefixFunc[j - 1];
            } else if (i < text.length() && sample.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = prefixFunc[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        return found;
    }
    public static double antiPlagiarism(int numberOfWords, String[] words, String[] texts){
        double plag = 0;
        for(int i = 0; i < words.length; i+=numberOfWords){
            for(int j = 0; j < texts.length; j++){
                if(i + numberOfWords > words.length){
                    numberOfWords = words.length - i;
                }
                ArrayList<Integer> indexes = KMPSearch(texts[j], createLineFromWords(createTempArrayOfWords(words, i, numberOfWords)));
                texts[j].replaceAll(createLineFromWords(createTempArrayOfWords(words, i, numberOfWords)),"");
                texts[j].trim();
                plag += ((double) numberOfWords * (double)indexes.size())/(double)words.length;
            }
        }
        return plag;
    }
    public static String[] createTempArrayOfWords(String[] words, int start, int lengh){
        String[] res = new String[lengh];
        for(int i = 0; i < lengh; i++){
            if(start + i < words.length)
                res[i] = words[start + i];
        }
        return res;
    }
    public static int findMaxN(String[] words, String[] texts){
       int max = words.length;
       for(int i = max - 1; i > 0; i--){
           double p = antiPlagiarism(i, words, texts);
           if(p > 0){
               return i;
           }
       }
       return 0;
    }
}
