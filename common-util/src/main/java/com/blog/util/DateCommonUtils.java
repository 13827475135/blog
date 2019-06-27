package com.blog.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期处理类
 *
 * @author Nicholas
 * @since 2019-04-04
 */
public class DateCommonUtils {

    private static final Logger LOG = LoggerFactory.getLogger(DateCommonUtils.class);

    public static final String UTC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    public final static String DATE_PATTERN_2 = "dd/MM/yyyy";

    public final static String DATE_PATTERN_3 = "dd/MM/yyyy HH:mm:ss";

    public final static String DATE_PATTERN_4 = "dd-MM-yyyy";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public final static String MONTH_PATTERN = "yyyy-MM";

    public final static String WHOLE_PATTERN = "yyyyMMddHHmmss";

    public final static String SHORT_PATTERN = "yyyyMMdd";

    public static Date formatUTC(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(UTC_DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            LOG.error("Format UTC error {}", date);
        }
        return null;
    }

    /**
     * 返回此刻加上一定秒数后的Date
     *
     * @param seconds 一定秒数
     */
    public static Date nowPlusSeconds(int seconds) {
        return DateTime.now().plusSeconds(seconds).toDate();
    }

    /**
     * 获取两个日期之间相隔多少秒
     *
     * @return 相隔多少秒
     */
    public static int getSeconds(Date validStartTime, Date validEndTime) {
        return Seconds.secondsBetween(new DateTime(validStartTime), new DateTime(validEndTime)).getSeconds();
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     *
     * @param week 周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return 返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date    日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date    日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date  日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date  日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date   日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date  日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * 将LocalDateTime转换成Date
     */
    public static Date convertToDate(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将Date转换成LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime convertToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        } else {
            Instant instant = date.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            return LocalDateTime.ofInstant(instant, zone);
        }
    }

    /**
     * 计算2个日期时间相隔多少天， 第二个日期 - 第一个日期
     * 如果第一参数在第二个参数之前，返回值为正数，否则为负数
     *
     * @param date1 日期一
     * @param date2 日期二
     * @return 天数
     */
    public static int getDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }

        Date truncateDate1 = truncateDay(date1);
        Date truncateDate2 = truncateDay(date2);
        return Days.daysBetween(new DateTime(truncateDate1), new DateTime(truncateDate2)).getDays();
    }

    /**
     * 获取当天00:00:00
     *
     * @param date 日期
     * @return 日期
     */
    public static Date truncateDay(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Null date!");
        }
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.dayOfMonth().roundFloorCopy();
        return dateTime.toDate();
    }

    /**
     * 获得指定时间当天的开始时间，为空则获取当天的
     * @param date 指定的时间
     * @return 开始时间（去掉时分秒）
     */
    public static Date beginOfDay(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取日期的年份
     * @param date 指定的时间
     * @return 整数年份
     */
    public static Integer getYear(Date date) {
        if (date == null) {
            return null;
        }
        return Integer.valueOf(format(date, "yyyy"));
    }

    /**
     * 返回指定时间的零点
     */
    public static Date beginOfDate(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.withMillisOfDay(0).toDate();
    }

    /**
     * Returns a 00:00 date plus the specified number of days.
     * 返回指定时间加上指定天数后的零点
     */
    public static Date beginOfDate(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).withMillisOfDay(0).toDate();
    }

    /**
     * Returns a 00:00 date plus the specified number of days.
     * 返回指定时间加上指定天数后的零点按指定模式转换的字符串
     */
    public static String beginOfDate(Date date, int days, String pattern) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).withMillisOfDay(0).toString(pattern);
    }

    /**
     * 创建一个用时间表示的某个随意的时刻。Constructs an instance from datetime field values using <code>ISOChronology</code> in the default time zone.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year, from 1 to 12
     * @param dayOfMonth  the day of the month, from 1 to 31
     * @param hourOfDay  the hour of the day, from 0 to 23
     * @param minuteOfHour  the minute of the hour, from 0 to 59
     * @param secondOfMinute  the second of the minute, from 0 to 59
     */
    public static Date someRandomMoment(int year,int monthOfYear,int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute) {
        DateTime dateTime = new DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute);
        return dateTime.toDate();
    }

    /**
     * 返回date的英文简写月份
     * Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
     */
    public static String getShortMonthOfDate(Date date) {
        return date.toString().substring(4,7);
    }

    /**
     * 返回date的印尼文简写月份
     * JAN, FEB, MAR, APR, MEI, JUN, JUL, AGS, SEP, OKT, NOV, DES
     */
    private static final String[] MONTHS = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};

    public static String getShortIdMonthOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return MONTHS[calendar.get(Calendar.MONTH)];
    }

    /**
     * Formats local date using the specified formatter
     * @param localDate A date without a time-zone in the ISO-8601 calendar system,such as 2007-12-03.
     * @param pattern   the pattern to use, not null
     * @return  the formatter based on the pattern, not null
     */
    public static String formatLocalDate(java.time.LocalDate localDate, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            return localDate.toString();
        }
        return localDate.format(java.time.format.DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * Convert to UTC date
     * @param localDate
     * @return
     */
    public static Date getUTCDate(Date localDate) {
        long localTimeInMillis = localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate = new Date(calendar.getTimeInMillis());
        return utcDate;
    }
}
