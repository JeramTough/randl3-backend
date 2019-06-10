package com.jeramtough.randl3.apicommon.component;

import com.jeramtough.jtlog.with.WithLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Created on 2019-03-14 09:47
 * by @author JeramTough
 */
@Component
public class ApiFailedMessager implements WithLogger {

    private static final String RESPONSE_ERROR_CODES_PROPERTIES_PATH = "response_error_codes.properties";

    private Properties properties;
    private String applicationName;

    @Autowired
    public ApiFailedMessager(Environment environment) {
        applicationName = environment.getProperty("spring.application.name");
        properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(
                    RESPONSE_ERROR_CODES_PROPERTIES_PATH);
            if (inputStream == null) {
                throw new FileNotFoundException(RESPONSE_ERROR_CODES_PROPERTIES_PATH + " is " +
                        "not found");
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            InputStreamReader inputStreamReader =
                    new InputStreamReader(bufferedInputStream,
                            Charset.forName("UTF-8"));
            properties.load(inputStreamReader);
            getLogger().verbose(
                    "Loading the error codes of (" + applicationName + ") is successful");

            inputStreamReader.close();
            bufferedInputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String getApiErrorMessage(int errorCode) {
        return getApiErrorMessage(String.valueOf(errorCode));
    }


    public String getApiErrorMessage(String errorCode) {
        String errorMessage = properties.getProperty(errorCode);

        if (errorMessage == null) {
            throw new NullPointerException("This error code don't exist");
        }

        return errorMessage;
    }

    public String getApiErrorMessage(String errorCode, Object... holders) {
        String errorMessage = getApiErrorMessage(errorCode);
        errorMessage = String.format(errorMessage, holders);
        return errorMessage;
    }

    public String getApiErrorMessage(int errorCode, Object... holders) {
        return getApiErrorMessage(String.valueOf(errorCode), holders);
    }


}

