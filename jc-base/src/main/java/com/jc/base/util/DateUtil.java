package com.jc.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    /** 日期格式yyyy-MM-dd字符串常量 */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /** 日期格式yyyy-MM-dd HH:mm:ss字符串常量 */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** 日期格式yyyy-MM字符串常量 */
    public static final String MONTH_FORMAT = "yyyy-MM";

    /** 一个月时间大约的long型数字 */
    public static final long MONTH_LONG = 2651224907l;

    /**
     * @return 当前时间
     */
    public static long now(){
        return System.currentTimeMillis();
    }

    /**
     * 得到当前日期的前/后　beforeDays　天的日期数
     * @param beforeDays
     * @return
     */
    public static String getDate(int beforeDays){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, beforeDays);
        String a = dateToString(c.getTime(),DATE_FORMAT);
        return a;
    }

    /**
     * 得到当前日期的前/后　beforeDays　天的日期数,格式自定
     * @param beforeDays
     * @param dateFormat
     * @return
     */
    public static String getDate(int beforeDays, String dateFormat){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, beforeDays);
        String a = dateToString(c.getTime(),dateFormat);
        return a;
    }

    /**
     * 将日期类型转换为yyyy-MM-dd字符串
     *
     * @param dateValue
     * @return String
     */
    public static String dateToString(Date dateValue) {
        return dateToString(dateValue, DATETIME_FORMAT);
    }

    /**
     * 将日期类型转换为指定格式的字符串
     *
     * @param dateValue
     * @param format
     * @return String
     */
    public static String dateToString(Date dateValue, String format) {
        if (dateValue == null || format == null) {
            return null;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(dateValue);
        }
    }
    /**
     * 将日期yyyy-MM-dd字符串转为日期类型，如果转换失败返回null
     *
     * @param stringValue
     * @return Date
     */
    public static Date stringToDate(String stringValue) {
        return stringToDate(stringValue, DATE_FORMAT);
    }

    /**
     * 将指定日期格式的字符串转为日期类型，如果转换失败返回null
     *
     * @param stringValue
     * @param format
     * @return Date
     */
    public static Date stringToDate(String stringValue, String format) {
        Date dateValue = null;
        if (stringValue != null && format != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                dateValue = dateFormat.parse(stringValue);

            } catch (ParseException ex) {
                dateValue = null;
            }
        }
        return dateValue;
    }
    /**
     * 获得当前年
     *
     * @return string
     */
    public static String getNowYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    /**
     * 获得当前月
     *
     * @return string
     */
    public static String getNowMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            return "0" + month;
        } else {
            return String.valueOf(month);
        }
    }

    /**
     * 获得当前日
     *
     * @return string
     */
    public static String getNowDay() {
        return dateToString(new Date(), "dd");

    }

    /**
     * 昨天
     * @return
     */
    public static String getYestday() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, - 1);
        return dateToString(date.getTime(), "dd");

    }

    /**
     * 返回几个月前的Date类型
     *
     * @param monthCount
     *            几个月
     * @return Date
     */
    public static Date getDateFront(int monthCount) {
        return new Date(Calendar.getInstance().getTimeInMillis() - MONTH_LONG
                * monthCount);
    }

    /**
     * 返回当前小时
     *
     * @return string
     */
    public static String getNowHour() {
        return dateToString(new Date(), "HH");
    }

    /**
     * 返回当前分钟
     *
     * @return string
     */
    public static String getNowMinute() {
        return dateToString(new Date(), "mm");
    }

    /**
     * 设置时间的日期值
     *
     * @param stringDate
     * @param num
     * @return Date
     */
    public static Date setDate(String stringDate, int num) {
        if (stringDate != null) {
            Date date = stringToDate(stringDate, "yyyy-MM-dd");
            return setDate(date, num);
        } else {
            return null;
        }
    }

    /**
     * 设置时间的日期值
     *
     * @param date
     * @param num
     * @return Date
     */
    public static Date setDate(Date date, int num) {
        Date dateValue = null;
        Calendar c = null;
        if (date != null) {
            c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, num);
            dateValue = c.getTime();
        }
        return dateValue;
    }

    /**
     * 取得两个日期的时间间隔,相差的天数
     * @param d1
     * @param d2
     * @return
     */
    public static int getDayBetween(Date d1, Date d2) {
        Calendar before = Calendar.getInstance();
        Calendar after = Calendar.getInstance();
        if (d1.before(d2)) {
            before.setTime(d1);
            after.setTime(d2);
        } else {
            before.setTime(d2);
            after.setTime(d1);
        }
        int days = 0;

        int startDay = before.get(Calendar.DAY_OF_YEAR);
        int endDay = after.get(Calendar.DAY_OF_YEAR);

        int startYear = before.get(Calendar.YEAR);
        int endYear = after.get(Calendar.YEAR);
        before.clear();
        before.set(startYear, 0, 1);

        while (startYear != endYear) {
            before.set(startYear++, Calendar.DECEMBER, 31);
            days += before.get(Calendar.DAY_OF_YEAR);
        }
        return days + endDay - startDay;
    }

    /**
     * 计算两个日期相差的天数 （注意单位是天 忽略时分秒）
     * @param before
     * @param after
     * @return
     * @throws ParseException
     */
    public static int getIntervalDays(Date before, Date after) {

        if (null == before || null == after) {
            throw new NullPointerException();
        }

        long intervalMilli = (after.getTime()/ (24 * 60 * 60 * 1000))
                - (before.getTime()/(24 * 60 * 60 * 1000));

        return (int) intervalMilli;
    }


    public static int getIntervalMonths(Date before,Date after) {
        if (null == before || null == after) {
            throw new NullPointerException();
        }

        Calendar b = Calendar.getInstance();
        b.setTime(before);
        Calendar a = Calendar.getInstance();
        a.setTime(after);

        int monthbefore = b.get(Calendar.MONTH);
        int monthafter = a.get(Calendar.MONTH);

        int ys = a.get(Calendar.YEAR) - b.get(Calendar.YEAR);

        return monthafter + ys * 12 - monthbefore;
    }

    /**
     * 获取年月日
     */
    public static String getDate() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(new Date());
    }

    /**
     * 获取时间
     */
    public static String getTime() {
        SimpleDateFormat sf = new SimpleDateFormat("HHmmss");
        return sf.format(new Date());
    }

    public static String getDateAndTime() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(new Date());
    }

    /**
     * @param startDate yyyyMMdd
     * @param duration  Integer
     * @return
     */
    public static String getDate(String startDate, Integer duration) {

        Calendar cal = Calendar.getInstance();

        Integer year = Integer.parseInt(startDate.substring(0, 4));
        Integer month = Integer.parseInt(startDate.substring(4, 6));
        Integer date = Integer.parseInt(startDate.substring(6, 8));

        cal.set(year, month - 1, date);

        cal.add(Calendar.DATE, duration);
        return String.valueOf(cal.get(Calendar.YEAR)) + format(cal.get(Calendar.MONTH) + 1, 2) + format(cal.get(Calendar.DATE), 2);

    }

    private static String format(int i, int len) {
        StringBuilder sb = new StringBuilder("");
        String perfix = "0";
        String srcStr = String.valueOf(i);
        if (srcStr.length() < len) {
            for (int k = len - srcStr.length(); k < len; k++) {
                sb.append(perfix);
            }
        }
        sb.append(srcStr);
        return sb.toString();
    }

    /**
     * @param date 历史时间
     * @return (long) 距今小时数 。异常-1。
     * @计算历史时间距今小时数
     */
    public static long fromNowHours(String date) {
        String dateStart = date;
        long hours = -1;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = new Date();
        try {
            d1 = format.parse(dateStart);
            // 毫秒ms
            long diff = d2.getTime() - d1.getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            hours = diffDays * 24 + diffHours;
        } catch (Exception e) {
            System.out.println("Date formate error.");
        }
        return hours;
    }

    /**
     * @param date 历史时间
     * @return (long) 距今小时数 。异常-1。
     * @计算历史时间距今分钟数
     */
    public static long fromNowMinutes(String date) {
        String dateStart = date;
        long minutes = -1;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = new Date();
        try {
            d1 = format.parse(dateStart);
            // 毫秒ms
            long diff = d2.getTime() - d1.getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            minutes = diffDays * 24 + diffHours * 60 + diffMinutes;
        } catch (Exception e) {
            System.out.println("Date formate error.");
        }
        return minutes;
    }

    /**
     * 获取延迟后的时间
     *
     * @param date         需要处理的时间
     * @param timeTypeEnum 时间类型
     * @param delta        需要顺延的时间变量
     * @return Date
     */
    public static Date getContinueDate(Date date, TimeTypeEnum timeTypeEnum, int delta) {
        if (null == date) {
            return null;
        }

        // 向后顺延相应的天数
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        if (timeTypeEnum == TimeTypeEnum.DAY) {
            calendar.add(Calendar.DATE, delta); // 把日期往后增加N天， 正数往后推,负数往前移动
        } else if (timeTypeEnum == TimeTypeEnum.HOUR) {
            calendar.add(Calendar.HOUR, delta);// 把日期往后增加N小时， 正数往后推,负数往前移动
        } else if (timeTypeEnum == TimeTypeEnum.MINUTE) {
            calendar.add(Calendar.MINUTE, delta);// 把日期往后增加N分钟， 正数往后推,负数往前移动
        } else if (timeTypeEnum == TimeTypeEnum.MONTH) {
            calendar.add(Calendar.MONTH, delta); // 把日期往后增加N个月， 正数往后推,负数往前移动
        } else if (timeTypeEnum == TimeTypeEnum.YEAR) {
            calendar.add(Calendar.YEAR, delta); // 把日期往后增加N年， 正数往后推,负数往前移动
        }

        return calendar.getTime();
    }


    /**
     * 比较两个时间前后
     */
    public static int compareDate(String date1, String date2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
//				System.out.println("dt1 在dt2后");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
//				System.out.println("dt1在dt2前");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static Date getTime(String txDate, String txTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(txDate.substring(0, 4)).append("-").append(txDate.substring(4, 6)).append("-")
                .append(txDate.substring(6, 8)).append(" ").append(txTime.substring(0, 2))
                .append(":").append(txTime.substring(2, 4)).append(":").append(txTime.substring(4, 6));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间类型枚举类
     */
    public static enum TimeTypeEnum {

        YEAR(1, "年"), MONTH(2, "月"), DAY(3, "日"), HOUR(4, "小时"), MINUTE(5, "分"), SECOND(6, "秒");

        private int timeType;
        private String desc;

        private TimeTypeEnum(int timeType, String desc) {
            this.timeType = timeType;
            this.desc = desc;
        }

        public int getTimeType() {
            return timeType;
        }

        public void setTimeType(int timeType) {
            this.timeType = timeType;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }
    /**
     * 求开始截至日期之间的天数差.
     *
     * @param start
     *            开始日期
     * @param end
     *            截至日期
     * @return 返回相差天数
     * @throws ParseException
     */
    public static int getDaysInterval(Date start, Date end)
            throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse(sdf.format(start));
        Date d2 = sdf.parse(sdf.format(end));
        if (d1 == null || d2 == null)
            return 0;
        Date[] d = new Date[2];
        d[0] = d1;
        d[1] = d2;
        Calendar[] cal = new Calendar[2];
        for (int i = 0; i < cal.length; i++) {
            cal[i] = Calendar.getInstance();
            cal[i].setTime(d[i]);
            cal[i].set(Calendar.HOUR_OF_DAY, 0);
            cal[i].set(Calendar.MINUTE, 0);
            cal[i].set(Calendar.SECOND, 0);
        }
        long m = cal[0].getTime().getTime();
        long n = cal[1].getTime().getTime();

        int ret = (int) Math.abs((m - n) / 1000 / 3600 / 24);
        return ret;
    }

    /**
     * 查看时间是否在6:00-22:00之间
     * @return
     */
    public static boolean checkTimeCanRegist(){
        Calendar now= Calendar.getInstance();
        Calendar begin= Calendar.getInstance();
        begin.set(Calendar.HOUR_OF_DAY,6);
        begin.set(Calendar.MINUTE,0);
        begin.set(Calendar.SECOND,0);
        Calendar end= Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY,22);
        end.set(Calendar.MINUTE,0);
        end.set(Calendar.SECOND,0);
        if (now.after(begin)&&now.before(end)){
            return true;
        }
        return false;
    }
    /**
     * 查看时间是否在2:00-23:00之间
     * @return
     */
    public static boolean checkTimeCanDo(){
        Calendar now= Calendar.getInstance();
        Calendar begin= Calendar.getInstance();
        begin.set(Calendar.HOUR_OF_DAY,2);
        begin.set(Calendar.MINUTE,0);
        begin.set(Calendar.SECOND,0);
        Calendar end= Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY,22);
        end.set(Calendar.MINUTE,59);
        end.set(Calendar.SECOND,59);
        if (now.after(begin)&&now.before(end)){
            return true;
        }
        return false;
    }
    /**
     * 获得当前时间到24点的秒数
     */
    public static Integer getSecondDayInterval(){
        Calendar begin= Calendar.getInstance();
        Calendar end= Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY,23);
        end.set(Calendar.MINUTE,59);
        end.set(Calendar.SECOND,59);
        end.set(Calendar.MILLISECOND,999);
        Long second=(end.getTime().getTime()-begin.getTime().getTime())/1000;
        return second.intValue();
    }

    /**
     * 获得当前时间到月末的秒数
     */
    public static Integer getSecondMonthInterval(){
        Calendar begin= Calendar.getInstance();
        Calendar end= Calendar.getInstance();
        end.set(Calendar.DAY_OF_MONTH,1);
        end.set(Calendar.HOUR_OF_DAY,23);
        end.set(Calendar.MINUTE,59);
        end.set(Calendar.SECOND,59);
        end.set(Calendar.MILLISECOND,999);
        end.add(Calendar.MONTH,1);
        end.add(Calendar.DAY_OF_MONTH,-1);
        //System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(end.getTime()));
        Long second=(end.getTime().getTime()-begin.getTime().getTime())/1000;
        return second.intValue();
    }
    /**
     * Description:指定日期加或减days天
     *
     * @param date1 日期
     * @param days 天数
     * @return
     * @since：2007-12-17 下午03:47:12
     */
    public static Date addDay(Date date1, int days) {
        Calendar date = Calendar.getInstance();
        date.setTime(date1);
        date.add(Calendar.DAY_OF_YEAR, days);
        return date.getTime();
    }

    /**
     * Description:指定日期加或减hours小时
     *
     * @param date   日期
     * @param hours 小时数
     * @return
     */
    public static Date addHour(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    /**
     * Description:指定日期加或减分钟
     *
     * @param date1 日期
     * @param mins 分钟
     * @return
     */
    public static Date addMins(Date date1, int mins) {
        Calendar date = Calendar.getInstance();
        date.setTime(date1);
        date.add(Calendar.MINUTE, mins);
        return date.getTime();
    }
    public static void main(String[] args) {
        System.out.println("==============================");
        System.out.println("距明天秒:"+getSecondDayInterval());
        System.out.println("距月底秒:"+getSecondMonthInterval());
        System.out.println("==============================");
        System.out.println("距今hours:" + DateUtil.fromNowHours("2015-11-27 3:50:00"));
        System.out.println("距今minutes:" + DateUtil.fromNowMinutes("2015-11-27 10:50:00"));

        System.out.println("==============================");
        SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        System.out.println("当前时间:" + sf.format(new Date()));
        System.out.println("10分钟之后时间:" + sf.format(DateUtil.getContinueDate(new Date(), TimeTypeEnum.MINUTE, 10)));

        System.out.println(getTime("20160615", "135538"));
    }
}
