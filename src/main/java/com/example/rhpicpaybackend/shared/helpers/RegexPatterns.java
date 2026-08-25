package com.example.rhpicpaybackend.shared.helpers;

public class RegexPatterns {
  public static final String EMAIL = "[a-z]+\\.[a-z]+@(picpay)\\.com";
  public static final String NAME = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:\\s+[A-Za-zÀ-ÖØ-öø-ÿ]+)*$";
  public static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$";
  public static final String PHONE = "^(\\(\\d{2}\\)|\\d{2})\\s?\\d{4}-?\\d{4}$";
}