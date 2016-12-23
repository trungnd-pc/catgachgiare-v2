package com.trunnd.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FriendlyUrlUtil {
    public static String LINK_EXTENSION = ".html";

    public static String getLink(String name, Integer id) {
        String deAccentName = deAccent(name);
        return deAccentName.toLowerCase().replaceAll("[^a-zA-Z0-9]+", "-") + "-" + id + LINK_EXTENSION;
    }

    public static Integer getIdFromLink(String link) {
        String strID = link.substring(link.lastIndexOf("-") + 1);
        return Integer.valueOf(strID);
    }


    private static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

}
