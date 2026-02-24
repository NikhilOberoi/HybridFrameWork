package org.System;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util
{
    //Generate Random Numbers
    // Randome Names
    //Date Converters
    //Data Tokenization

    //Check field values - RestAPIs
    // Check field calues - Web Applications.
    //Screenshots and attachments
    // Excel Reader - might be done



    static String getStringDateTime(String format , Calendar cal) {
        cal = cal== null ? Calendar.getInstance() : cal;
        SimpleDateFormat sdf = new SimpleDateFormat(format); //MM_dd_YYYY_hh_mm_ss
        return sdf.format(cal.getTime());
    }
}
