package ru.otus.exchangeservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author: URUNOV Khamdamboy
 * @date 10.06.2026
 * @Project: transaction-service
 * @description  PRODUCT
 */

@Component
public class ProjectStartingPoint implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectStartingPoint.class);

    @Override
    public void run(String... args) {
        final Date date = new Date();
        final String strDateFormat = "dd-MM-yyyy :> hh:mm:ss a";
        final DateFormat dateFormat = new SimpleDateFormat(strDateFormat, Locale.US);
        final TimeZone timeZone = TimeZone.getTimeZone("Asia/Tashkent");
        dateFormat.setTimeZone(timeZone);
        final String formattedDate = dateFormat.format(date);
        LOG.info("EXCHANGE SERVICE ( BANK  ) is started!  {}", formattedDate);
    }

    public static void loggingApplicationParams(ConfigurableEnvironment environment) {
        var host = getHostAddress();
        LOG.info("""


                        ________EXCHANGE SERVICE ( BANK  )  PROJECT ________
                        EXCHANGE SERVICE PROJECT STARTED SUCCESSFULLY!
                        authors: OTUS team [Urunov Khamdamboy]
                        application: ..........: {}
                        active profiles: ......: {}
                        address: ..............: {}
                        _____________________________________________________""",
                environment.getProperty("spring.application.name"),
                environment.getActiveProfiles(),
                "http://" + host + ":" + environment.getProperty("server.port")
        );
    }

    private static String getHostAddress() {
        String hostAddress;

        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostAddress = "localhost";
            LOG.error("Can't determine host address");
        }
        return hostAddress;
    }
}
