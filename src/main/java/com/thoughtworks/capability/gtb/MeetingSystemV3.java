package com.thoughtworks.capability.gtb;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 脑洞会议系统v3.0
 * 1.当前会议时间"2020-04-01 14:30:00"表示伦敦的本地时间，而输出的新会议时间是芝加哥的本地时间
 *   场景：
 *   a:上个会议是伦敦的同事定的，他在界面上输入的时间是"2020-04-01 14:30:00"，所以我们要解析的字符串是伦敦的本地时间
 *   b:而我们在当前时区(北京时区)使用系统
 *   c:我们设置好新会议时间后，要发给芝加哥的同事查看，所以格式化后的新会议时间要求是芝加哥的本地时间
 * 2.用Period来实现下个会议时间的计算
 *
 * @author itutry
 * @create 2020-05-19_18:43
 */
public class MeetingSystemV3 {

  private static final ZoneId LONDON_ZONE = ZoneId.of("Europe/London");
  private static final ZoneId BEIJING_ZONE = ZoneId.of("Asia/Shanghai");
  private static final ZoneId CHICAGO_ZONE = ZoneId.of("America/Chicago");

  public static void main(String[] args) {
   // String timeStr = "2020-04-01 14:30:00";
   String timeStr = "2020-10-01 14:30:00";

    // 根据格式创建格式化类
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 从字符串解析得到会议时间
    LocalDateTime meetingTime = LocalDateTime.parse(timeStr, formatter);

    ZonedDateTime meetingTimeInLondon = ZonedDateTime.of(meetingTime, LONDON_ZONE);
    ZonedDateTime meetingTimeInBeijing = meetingTimeInLondon.withZoneSameInstant(BEIJING_ZONE);


    LocalDateTime now = LocalDateTime.now(BEIJING_ZONE);
    if (now.isAfter(meetingTime)) {
      LocalDateTime tomorrow = now.plusDays(1);
      int newDayOfYear = tomorrow.getDayOfYear();
      LocalDateTime meetingTimeInChicago = meetingTimeInBeijing.withDayOfYear(newDayOfYear)
              .withZoneSameInstant(CHICAGO_ZONE).toLocalDateTime();

      // 格式化新会议时间
      String showTimeStr = formatter.format(meetingTimeInChicago);
      System.out.println("Meeting start time :" + showTimeStr + "(Chicago Time)");
    } else {
      LocalDateTime meetingTimeInChicago = meetingTimeInLondon.withZoneSameInstant(CHICAGO_ZONE).toLocalDateTime();
      String showTimeStr = formatter.format(meetingTimeInChicago);
      Period period = Period.between(now.toLocalDate(), meetingTimeInBeijing.toLocalDate());
      System.out.println("The meeting has not begun!");
      System.out.println("There are  " + period.getDays() + "  days before the meeting begins.");
    }
  }
}
