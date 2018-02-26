package ir.maktab.model;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by Hamed-Abbaszadeh on 2/24/2018.
 */
public class MyLogger {
    public static final  Logger logger = Logger.getRootLogger();

    {
        PropertyConfigurator.configure("log4jConsole.properties");
        PropertyConfigurator.configure("log4jDB.properties");
    }
}
