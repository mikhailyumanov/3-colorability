package com.mikhailyumanov.three_colorability.csp_instance;

public enum Color {
  RED, GREEN, BLUE, BLACK;

  public static Color fromInteger(int i) {
    switch (i) {
      case 0: return RED;
      case 1: return GREEN;
      case 2: return BLUE;
    }

    return BLACK;
  }
}

