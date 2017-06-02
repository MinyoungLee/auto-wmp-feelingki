package kr.yerina.wmp.autonomousRegistration.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
public class DateUtility {

    public static final String SZ_NULL_STRING = "";
    public static final SimpleDateFormat SDF_YYYYMM = new SimpleDateFormat("yyyyMM");
    public static final SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat SDF_YYYYMMDD_DASH = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat SDF_YYYYMMDD_DOT = new SimpleDateFormat("yyyy.MM.dd");
    public static final SimpleDateFormat SDF_YYYYMMDD_SLASH = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat SDF_YYYYMMDDHHMM = new SimpleDateFormat("yyyyMMddhhmm");
    public static final SimpleDateFormat SDF_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat SDF_YYYYMMDDHHMMM_DASH_DOT = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
    public static final SimpleDateFormat SDF_YYYYMMDDHHMMSSM_DASH_DOT = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");

    /**
     * 지정된 형식으로 현재날자 반환 - CjUtil.java
     */
    public static String getDate(String format) {
        Date today = new Date();
        SimpleDateFormat day = new SimpleDateFormat(format);
        return day.format(today);
    }

    /**
     * 현재 일자를 String형으로 반환
     *
     **/
    public static String getToday(SimpleDateFormat sdf) {
        return convertDate(new Date(), sdf);
    }


    /**
     * sdf형식으로 되어있는 날짜String을 Date Type으로 변경
     */
    public static Date convertString(String szString, SimpleDateFormat sdf) {
        if (szString == null || szString.length() <= 0) {
            return null;
        }

        try {
            return sdf.parse(szString);
        } catch (Exception e) {
             log.error("Exception", e);
            return null;
        }
    }

    /**
     * 주어진 Date를 sdf Type으로 되어있는 날짜 String으로 변환
     */
    public static String convertDate(Date dt, SimpleDateFormat sdf) {
        if (dt == null) {
            return SZ_NULL_STRING;
        }

        try {
            return sdf.format(dt);
        } catch (Exception e) {
            log.error("Exception", e);
            return SZ_NULL_STRING;
        }
    }


}
