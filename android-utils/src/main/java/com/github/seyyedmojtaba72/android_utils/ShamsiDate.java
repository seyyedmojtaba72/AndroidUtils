package com.github.seyyedmojtaba72.android_utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ShamsiDate {
    private static final String TAG = ShamsiDate.class.getSimpleName();

    public static String getCurrentShamsidate() {
        Locale loc = new Locale("en_US");
        ShamsiDate pDate = new ShamsiDate();
        SolarCalendar sc = pDate.new SolarCalendar();
        return String.format(loc, "%02d", sc.year) + "/" + String.format(loc, "%02d", sc.month) + "/" + String.format(loc, "%02d", sc.date);
    }

    public static String getShamsidate(Locale locale, Date miladiDate) {
        ShamsiDate pDate = new ShamsiDate();
        SolarCalendar sc = pDate.new SolarCalendar(miladiDate);
        return String.format(locale, "%02d", sc.year) + "/" + String.format(locale, "%02d", sc.month) + "/" + String.format(locale, "%02d", sc.date);
    }

    public static String toShamsi(String date) {
        int Y = Integer.parseInt(date.split("/")[0]);
        int M = Integer.parseInt(date.split("/")[1]);
        int D = Integer.parseInt(date.split("/")[2]);
        if (Y == 0) {
            Y = 2000;
        }
        if (Y < 100) {
            Y = Y + 1900;
        }
        if (Y == 0x7d0) {
            if (M > 0x2) {
                SimpleDateFormat temp = new SimpleDateFormat("yyyyMMdd");
                String curentDateandTime = temp.format(new Date());
                String year = curentDateandTime.substring(0x0, 4);
                String month = curentDateandTime.substring(0x4, 6);
                String day = curentDateandTime.substring(0x6, 8);
                Y = Integer.valueOf(year);
                M = Integer.valueOf(month);
                D = Integer.valueOf(day);
            }
        }

        if ((M < 3) || (M == 3) && (D < 21)) {
            Y = Y - 0x26e;
        } else {
            Y = Y - 0x26d;
        }
        switch (M) {
            case 1: {
                if (D < 0x15) {
                    M = 0xa;
                    D = D + 0xa;
                    break;
                } else {
                    M = 0xb;
                    D = D - 0x14;
                    break;
                }
            }
            case 2: {
                if (D < 0x14) {
                    M = 0xb;
                    D = D + 0xb;
                    break;
                } else {
                    M = 0xc;
                    D = D - 0x13;
                    break;
                }
            }
            case 3: {
                if (D < 0x15) {
                    M = 0xc;
                    D = D + 0x9;
                    break;
                } else {
                    M = 0x1;
                    D = D - 0x14;
                    break;
                }
            }
            case 4: {
                if (D < 0x15) {
                    M = 0x1;
                    D = D + 0xb;
                    break;
                } else {
                    M = 0x2;
                    D = D - 0x14;
                    break;
                }
            }
            case 5: {
                if (D < 0x16) {
                    M = M - 0x3;
                    D = D + 0xa;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x15;
                    break;
                }
            }
            case 6: {
                if (D < 0x16) {
                    M = M - 0x3;
                    D = D + 0xa;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x15;
                    break;
                }
            }
            case 7: {
                if (D < 0x17) {
                    M = M - 0x3;
                    D = D + 0x9;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x16;
                    break;
                }
            }
            case 8: {
                if (D < 0x17) {
                    M = M - 0x3;
                    D = D + 0x9;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x16;
                    break;
                }
            }
            case 9: {
                if (D < 0x17) {
                    M = M - 0x3;
                    D = D + 0x9;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x16;
                    break;
                }
            }
            case 10: {
                if (D < 0x17) {
                    M = 0x7;
                    D = D + 0x8;
                    break;
                } else {
                    M = 0x8;
                    D = D - 0x16;
                    break;
                }
            }
            case 11: {
                if (D < 0x16) {
                    M = M - 0x3;
                    D = D + 0x9;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x15;
                    break;
                }
            }
            case 12: {
                if (D < 0x16) {
                    M = M - 0x3;
                    D = D + 0x9;
                    break;
                } else {
                    M = M - 0x2;
                    D = D - 0x15;
                    break;
                }
            }

        }

        String year = null, month, day;
        year = String.valueOf(Y);
        if (M < 10) {
            month = String.valueOf(M);
        } else {
            month = "0" + String.valueOf(M);
        }
        if (D < 0xa) {
            day = String.valueOf(D);
        } else {
            day = "0" + String.valueOf(D);
        }
        return year + "/" + month + "/" + day;
    }

    public static String getPersianTitle(String date) {
        String str1 = toShamsi(date);
        String str2 = str1.split("/")[0];
        String str3 = str1.split("/")[1];
        String str4 = str1.split("/")[2];
        String str5 = "";
        if (str3.equals("01")) {
            str5 = "فروردین";
        } else if (str3.equals("02")) {
            str5 = "اردیبهشت";
        } else if (str3.equals("03")) {
            str5 = "خرداد";
        } else if (str3.equals("04")) {
            str5 = "تیر";
        } else if (str3.equals("05")) {
            str5 = "مرداد";
        } else if (str3.equals("06")) {
            str5 = "شهریور";
        } else if (str3.equals("07")) {
            str5 = "مهر";
        } else if (str3.equals("08")) {
            str5 = "آبان";
        } else if (str3.equals("09")) {
            str5 = "آذر";
        } else if (str3.equals("10")) {
            str5 = "دی";
        } else if (str3.equals("11")) {
            str5 = "بهمن";
        } else if (str3.equals("12")) {
            str5 = "اسفند";
        }

        return DateManager.getWeekDayPersianName(date) + " " + str4 + " " + str5 + " " + str2;

    }

    private class SolarCalendar {

        public String strWeekDay = "";
        public String strMonth = "";

        int date;
        int month;
        int year;

        public SolarCalendar() {
            Date MiladiDate = new Date();
            calcSolarCalendar(MiladiDate);
        }

        public SolarCalendar(Date MiladiDate) {
            calcSolarCalendar(MiladiDate);
        }

        @SuppressWarnings("deprecation")
        public void calcSolarCalendar(Date MiladiDate) {

            int ld;

            int miladiYear = MiladiDate.getYear() + 1900;
            int miladiMonth = MiladiDate.getMonth() + 1;
            int miladiDate = MiladiDate.getDate();
            int WeekDay = MiladiDate.getDay();

            int[] buf1 = new int[12];
            int[] buf2 = new int[12];

            buf1[0] = 0;
            buf1[1] = 31;
            buf1[2] = 59;
            buf1[3] = 90;
            buf1[4] = 120;
            buf1[5] = 151;
            buf1[6] = 181;
            buf1[7] = 212;
            buf1[8] = 243;
            buf1[9] = 273;
            buf1[10] = 304;
            buf1[11] = 334;

            buf2[0] = 0;
            buf2[1] = 31;
            buf2[2] = 60;
            buf2[3] = 91;
            buf2[4] = 121;
            buf2[5] = 152;
            buf2[6] = 182;
            buf2[7] = 213;
            buf2[8] = 244;
            buf2[9] = 274;
            buf2[10] = 305;
            buf2[11] = 335;

            if ((miladiYear % 4) != 0) {
                date = buf1[miladiMonth - 1] + miladiDate;

                if (date > 79) {
                    date = date - 79;
                    if (date <= 186) {
                        switch (date % 31) {
                            case 0:
                                month = date / 31;
                                date = 31;
                                break;
                            default:
                                month = (date / 31) + 1;
                                date = (date % 31);
                                break;
                        }
                        year = miladiYear - 621;
                    } else {
                        date = date - 186;

                        switch (date % 30) {
                            case 0:
                                month = (date / 30) + 6;
                                date = 30;
                                break;
                            default:
                                month = (date / 30) + 7;
                                date = (date % 30);
                                break;
                        }
                        year = miladiYear - 621;
                    }
                } else {
                    if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
                        ld = 11;
                    } else {
                        ld = 10;
                    }
                    date = date + ld;

                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 9;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 10;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 622;
                }
            } else {
                date = buf2[miladiMonth - 1] + miladiDate;

                if (miladiYear >= 1996) {
                    ld = 79;
                } else {
                    ld = 80;
                }
                if (date > ld) {
                    date = date - ld;

                    if (date <= 186) {
                        switch (date % 31) {
                            case 0:
                                month = (date / 31);
                                date = 31;
                                break;
                            default:
                                month = (date / 31) + 1;
                                date = (date % 31);
                                break;
                        }
                        year = miladiYear - 621;
                    } else {
                        date = date - 186;

                        switch (date % 30) {
                            case 0:
                                month = (date / 30) + 6;
                                date = 30;
                                break;
                            default:
                                month = (date / 30) + 7;
                                date = (date % 30);
                                break;
                        }
                        year = miladiYear - 621;
                    }
                } else {
                    date = date + 10;

                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 9;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 10;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 622;
                }

            }

            switch (month) {
                case 1:
                    strMonth = "فروردين";
                    break;
                case 2:
                    strMonth = "ارديبهشت";
                    break;
                case 3:
                    strMonth = "خرداد";
                    break;
                case 4:
                    strMonth = "تير";
                    break;
                case 5:
                    strMonth = "مرداد";
                    break;
                case 6:
                    strMonth = "شهريور";
                    break;
                case 7:
                    strMonth = "مهر";
                    break;
                case 8:
                    strMonth = "آبان";
                    break;
                case 9:
                    strMonth = "آذر";
                    break;
                case 10:
                    strMonth = "دي";
                    break;
                case 11:
                    strMonth = "بهمن";
                    break;
                case 12:
                    strMonth = "اسفند";
                    break;
            }

            switch (WeekDay) {

                case 0:
                    strWeekDay = "يکشنبه";
                    break;
                case 1:
                    strWeekDay = "دوشنبه";
                    break;
                case 2:
                    strWeekDay = "سه شنبه";
                    break;
                case 3:
                    strWeekDay = "چهارشنبه";
                    break;
                case 4:
                    strWeekDay = "پنج شنبه";
                    break;
                case 5:
                    strWeekDay = "جمعه";
                    break;
                case 6:
                    strWeekDay = "شنبه";
                    break;
            }

        }
    }

}
