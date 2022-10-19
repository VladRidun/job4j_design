package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        int iInt = 33;
        byte bByte = 8;
        short sShort = 2;
        long lLong = 933332323434343243L;
        double dDouble = 2.5;
        float fFloat = 4.7F;
        char cChar = 23;
        boolean bBool = true;
        LOG.debug("Integer : {}, Byte : {}, Short : {}, Long : {}, Double : {}, Float : {}, Char : {}, Bool : {}", iInt, bByte, sShort, lLong, dDouble, fFloat, cChar, bBool);
    }
}
