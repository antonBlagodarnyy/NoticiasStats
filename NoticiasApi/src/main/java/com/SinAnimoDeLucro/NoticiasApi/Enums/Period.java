package com.SinAnimoDeLucro.NoticiasApi.Enums;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public enum Period {
  TODAY {
    @Override
    public LocalDate getStart(LocalDate today) {
      return today;
    }
  },
  LAST_WEEK {
    @Override
    public LocalDate getStart(LocalDate today) {
      return today.minusDays(7);
    }
  },
  LAST_MONTH {
    @Override
    public LocalDate getStart(LocalDate today) {
      return today.minusMonths(1);
    }
  };

  public abstract LocalDate getStart(LocalDate today);
}
