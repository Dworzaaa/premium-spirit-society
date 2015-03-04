package com.premium.spirit.society.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LoggingService {
    private Logger logger;

    public void setLogger(Class<?> classOfOrigin) {
        this.logger = LoggerFactory.getLogger(classOfOrigin);
    }

    public Logger getLogger() {
        return logger;
    }


    public void logInfo(String message) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        this.logger.info(reportDate + " : " + message);
    }

    public void logError(String message, Exception e) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));

        this.logger.error(reportDate + ": " + message + " \n" + sw.toString());
    }
}
