package com.follow.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wangchunjun
 * @date 2020/8/25
 */
public class LocalDateUtil {

    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";


    public static LocalDate getLocalDateByStr(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM_DD);
        return LocalDate.parse(str, formatter);
    }

    /**
     * 获取指定日期范围内的所有指定星期 包含开始日期和结束日期
     * @param weeks 1,3,7表示周一，周三，周日
     * @return List<LocalDate>
     */
    public static List<LocalDate> getWeekLocalDateListByRange(LocalDate startDay, LocalDate endDay, List<String> weeks) {
        List<LocalDate> localDateList = new ArrayList<>();
        TemporalField fieldISO = WeekFields.of(DayOfWeek.of(1), 1).dayOfWeek();
        LocalDate tempDay;
        for (String week : weeks) {
            tempDay = startDay.with(fieldISO, Long.parseLong(week));
            if (tempDay.isBefore(startDay)) {
                tempDay = tempDay.plusWeeks(1);
            }
            while (tempDay.isBefore(endDay) || tempDay.isEqual(endDay)) {
                localDateList.add(tempDay);
                tempDay = tempDay.plusWeeks(1);
            }
        }
        return localDateList;
    }

    /**
     * 获取指定日期范围内的所有指定dayOfMonth 包含开始日期和结束日期
     * @param months 1,29,31表示每月的1号，29号，31号
     * @return List<LocalDate>
     */
    public static List<LocalDate> getLocalDateListByMonth(LocalDate startDate,LocalDate endDate,List<String> months){
        List<LocalDate> localDateList = new ArrayList<>();

        LocalDate localDate;
        for(String month : months){
            LocalDate tempDate = startDate;
            while (tempDate.isBefore(endDate) || tempDate.getMonthValue() == endDate.getMonthValue()){
                if(tempDate.lengthOfMonth() >= Integer.valueOf(month)){
                    localDate = tempDate.withDayOfMonth(Integer.valueOf(month));
                    if(localDate.isAfter(startDate) || localDate.isEqual(startDate)){
                        localDate = tempDate.withDayOfMonth(Integer.valueOf(month));
                        if(localDate.isEqual(endDate) || localDate.isBefore(endDate)){
                            localDateList.add(localDate);
                        }
                    }
                }
                tempDate = tempDate.plusMonths(1);
            }
        }
        return localDateList;
    }

    /**
     * 获取指定范围内所有日期，包含开始日期和结束日期
     * @return List<LocalDate>
     */
    public static List<LocalDate> getLocalDateByDay(LocalDate startDate, LocalDate endDate){
        List<LocalDate> localDateList = Stream.iterate(startDate, date -> date.plusDays(1)).
                limit(ChronoUnit.DAYS.between(startDate, endDate))
                .collect(Collectors.toList());
        localDateList.add(endDate);
        return localDateList;
    }

    public static void main(String[] args) {
        LocalDate startDate = getLocalDateByStr("2018-10-27");
        LocalDate endDate = getLocalDateByStr("2018-11-05");
        System.out.println(getLocalDateByDay(startDate,endDate));
        List<String> strings = new ArrayList<>();
        strings.add("31");
        List<LocalDate> localDateListByweek = getLocalDateListByMonth(startDate, endDate, strings);
        System.out.println(localDateListByweek);
    }

}
