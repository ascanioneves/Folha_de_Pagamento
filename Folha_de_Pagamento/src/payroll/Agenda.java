package payroll;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Agenda {
  private int type;
  private Integer dayOfMonth;
  private DayOfWeek dayOfWeek;

  public Agenda(int type, Integer dayOfMonth, DayOfWeek dayOfWeek) {
    this.type = type;
    this.dayOfMonth = dayOfMonth;
    this.dayOfWeek = dayOfWeek;
  }

  public int getType() {
    return type;
  }
  public Integer getDayOfMonth() {
    return dayOfMonth;
  }
  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }
  public void setType(int type) {
    this.type = type;
  }
  public void setDayOfMonth(Integer dayOfMonth) {
    this.dayOfMonth = dayOfMonth;
  }
  public void setDayOfWeek(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }
}
