package com.toolcat.application;

import org.joda.time.*;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * joda-time 常用的类
 * <p>
 * DateTime - 详细时间类
 * LocalDateTime - 本地的日期 + 时间
 * LocalDate - 本地日期
 * LocalTime - 本地时间
 * <p>
 * Instant - 瞬间时刻
 * <p>
 * Interval - 保存一段时间 从开始时刻到结束时刻
 * Period - 保存一段时间
 * Duration - 保存一段时间精确到毫秒
 * <p>
 * DateTimeZone - 时区
 */

class JodaTimeTest {

    // 定义格式化规则
    final String format_date = "yyyy年MM月dd日";
    final String format_time = "HH:mm:ss";
    final String format_datetime = format_date.concat(" ").concat(format_time);

    /**
     * DateTime,LocalDateTime,LocalDate,LocalTime
     */
    @Test
    void test0() {

        // 创建实例 系统当前时间
        DateTime dateTime0 = new DateTime();
        // 系统当前时间. LocalDateTime,LocalDate,LocalTime该方法
        DateTime dateTime1 = DateTime.now();
        // String -> DateTime if(月,日,时,分,秒,毫秒)<10 前面可以补0或者不补 LocalDateTime,LocalDate,LocalTime该方法
        DateTime dateTime2 = DateTime.parse("2020-02-2T02:2:2");
        // String -> DateTime
        DateTime dateTime3 = new DateTime("2020-2-02T2:02:2");
        // 毫秒级时间
        DateTime dateTime4 = new DateTime(1580580122L);
        // 年,月,日,时,分,秒,毫秒
        DateTime dateTime5 = new DateTime(2020, 2, 2, 2, 2, 2);

        System.out.println("系统当前时间: " + dateTime0);
        System.out.println("DateTime -> LocalDateTime: " + dateTime1.toLocalDateTime());
        System.out.println("DateTime -> LocalDate: " + dateTime2.toLocalDate());
        System.out.println("DateTime -> LocalTime: " + dateTime3.toLocalTime());
        System.out.println("DateTime -> DateTimeISO: " + dateTime4.toDateTimeISO());
        System.out.println("DateTime -> String: " + dateTime5.toString(format_datetime, Locale.CHINESE));// 按照指定格式表示为中国本地时间
        System.out.println("DateTime -> Date: " + dateTime0.toDate());
        System.out.println("Date -> DateTime: " + new DateTime(new Date()));
        System.out.println("DateTime -> Calendar: " + dateTime0.toCalendar(Locale.getDefault()));
        System.out.println("Calender -> DateTime: " + new DateTime(Calendar.getInstance()));

        System.out.println("本地'年'表示的文本格式: " + dateTime0.year().getAsText(Locale.getDefault()));
        System.out.println("本地'月'表示的文本格式: " + dateTime0.monthOfYear().getAsText(Locale.getDefault()));
        System.out.println("本地'日'表示的文本格式: " + dateTime0.dayOfMonth().getAsText(Locale.getDefault()));
        System.out.println("本地'周'表示的文本格式: " + dateTime0.dayOfWeek().getAsText(Locale.getDefault()));

        System.out.println("dateTime0在dateTime1之后: " + dateTime0.isAfter(dateTime2)); // isAfter() 之后,isBefore() 之前,isEqual() 相等
        System.out.println("是否闰年:" + dateTime0.year().isLeap());
        System.out.println("是否闰月:" + dateTime0.monthOfYear().isLeap());

        System.out.println("当前时间设置为: 2008年8月8日 8时8分8秒: " + dateTime0.withYear(2008).withMonthOfYear(8).withDayOfMonth(8).withHourOfDay(8).withMinuteOfHour(8).withSecondOfMinute(8));
        System.out.println("当前时间的年月日时分秒都+1: " + dateTime0.plusYears(1).plusMonths(1).plusDays(1).plusHours(1).plusMillis(1).plusSeconds(1)); // minusXXX(1) -1

        System.out.println("今天的开始时间: " + dateTime0.withTimeAtStartOfDay());
        System.out.println("今天的结束时间: " + dateTime0.millisOfDay().withMaximumValue());
        System.out.println("本年的第一天: " + dateTime0.dayOfYear().withMinimumValue().toLocalDate()); // withMaximumValue() -> 最后一天
        System.out.println("本月的第一天: " + dateTime0.dayOfMonth().withMinimumValue().toLocalDate()); // withMaximumValue() -> 最后一天
        System.out.println("本周的第一天: " + dateTime0.dayOfWeek().withMinimumValue().toLocalDate()); // withMaximumValue() -> 最后一天
        System.out.println("上周的周一: " + dateTime0.minusWeeks(1).dayOfWeek().withMinimumValue().toLocalDate()); // withMaximumValue() -> 最后一天
        System.out.println("下周的周一: " + dateTime0.plusWeeks(1).dayOfWeek().withMaximumValue().toLocalDate()); // withMaximumValue() -> 最后一天
        System.out.println("99天后那周的周一是: " + dateTime0.plusDays(99).dayOfWeek().withMinimumValue().toString(format_datetime)); // withMaximumValue() -> 最后一天

        System.out.println("距离今天结束还有多久时间: " + (dateTime0.millisOfDay().withMaximumValue().getMillis() - dateTime0.getMillis()));

    }

    /**
     * Instant: 瞬间
     */
    @Test
    void test1() {

        Instant instant = Instant.now();
        System.out.println("瞬间时刻: " + instant.getMillis());
        System.out.println("瞬间时刻+2-1: " + instant.plus(2).minus(1).getMillis());
        System.out.println("设置为系统当前时间: " + Instant.ofEpochMilli(System.currentTimeMillis()));

    }

    /**
     * Interval：保存一段时间 从开始时刻到结束时刻
     * Period：保存一段时间
     * Duration：保存一段时间精确到毫秒
     * <p>
     * 先判断参数1和参数2
     * dateTime2.isBefore(dateTime0)
     */
    @Test
    void test2() {

        DateTime dateTime0 = DateTime.now();
        DateTime dateTime1 = DateTime.parse("2020-02-2T02:2:2");

        Interval interval = new Interval(dateTime1, dateTime0);
        System.out.println("2050-1-1是否在 " + dateTime1 + " 与 " + dateTime0 + " 之间: " + interval.contains(new DateTime("2050-1-1")));

        Period period = new Period(dateTime1, dateTime0, PeriodType.days());
        System.out.println(dateTime1 + " 与 " + dateTime0 + " 相隔的天数: " + period.getDays());

        Duration duration = new Duration(dateTime1, dateTime0);
        System.out.println(dateTime1 + " 与 " + dateTime0 + " 相隔的天数: " + duration.getStandardDays());
        System.out.println(dateTime1 + " 与 " + dateTime0 + " 相隔的小时数: " + duration.getStandardHours());
        System.out.println(dateTime1 + " 与 " + dateTime0 + " 相隔的分钟数: " + duration.getStandardMinutes());
        System.out.println(dateTime1 + " 与 " + dateTime0 + " 相隔的秒数: " + duration.getStandardSeconds());

        System.out.println(dateTime1 + " 与 " + dateTime0 + " 相隔的年数: " + Years.yearsBetween(dateTime1, dateTime0));
        System.out.println(dateTime1 + " 与 " + dateTime0 + " 相隔的月数: " + Months.monthsBetween(dateTime1, dateTime0));
        System.out.println(dateTime1 + " 与 " + dateTime0 + " 相隔的周数: " + Weeks.weeksBetween(dateTime1, dateTime0));
        System.out.println(dateTime1 + " 与 " + dateTime0 + " 相隔的天数: " + Days.daysBetween(dateTime1, dateTime0));
        System.out.println(dateTime1 + " 与 " + dateTime0 + " 相隔的小时数: " + Hours.hoursBetween(dateTime1, dateTime0));
        System.out.println(dateTime1 + " 与 " + dateTime0 + " 相隔的分钟数: " + Seconds.secondsBetween(dateTime1, dateTime0));
        System.out.println(dateTime1 + " 与 " + dateTime0 + " 相隔的秒数: " + Minutes.minutesBetween(dateTime1, dateTime0));

    }

    /**
     * DateTimeZone: 时区
     */
    @Test
    void test3() {

        DateTimeZone.setDefault(DateTimeZone.forID("Asia/Tokyo"));  // 把日本时间设置为默认

        System.out.println("日本时间: " + new DateTime().toString(format_datetime));

        System.out.println("伦敦时间: " + new DateTime(DateTimeZone.forID("Europe/London")).toString(format_date));

    }

}