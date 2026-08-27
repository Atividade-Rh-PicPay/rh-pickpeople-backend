package com.example.rhpicpaybackend.shared.helpers;

import java.util.Set;

public class NormalizeOutput {
  public static String phone(String phone) {
    if (phone == null) {
      return null;
    }

    return phone.replaceFirst(
        "(\\d{2})(\\d{4})(\\d{4})",
        "($1) $2-$3"
    );
  }

  public static String email(String email){
    if (email == null || email.isBlank()) return email;

    return email.toLowerCase();
  }

  public static String name(String name){
    if (name == null || name.isBlank()) return name;

    String[] words = name.trim()
        .replaceAll("\\s+", " ")
        .toLowerCase()
        .split(" ");

    StringBuilder normalized = new StringBuilder();

    for (int i = 0; i < words.length; i++) {
      String word = words[i];

      if (i > 0 && LOWERCASE_WORDS.contains(word)) {
        normalized.append(word);
      } else {
        normalized.append(
            Character.toUpperCase(word.charAt(0))
        );

        if (word.length() > 1) {
          normalized.append(word.substring(1));
        }
      }

      if (i < words.length - 1) {
        normalized.append(' ');
      }
    }

    return normalized.toString();
  }

  private static final Set<String> LOWERCASE_WORDS = Set.of(
      "de", "da", "do", "das", "dos", "e", "o", "a", "em", "na", "no"
  );
}
