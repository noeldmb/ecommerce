package com.ecommerce.common;

/*
  Utility class for common use where the treatment of dates is centralized,
  such as the format and the conversion of string to date and date to String.
  Access to the resource file "application.properties" is implemented, as well as date validation.
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Common {

    @Autowired
    private final Environment environment;

    public Common(Environment environment) {
        this.environment = environment;
    }

    public boolean isDateValid(String date) {

        if (StringUtils.isBlank(date))
            return false;

        try {
            @SuppressWarnings("unused")
            LocalDateTime localDateTime = convertStringToLocalDateTime(date);
        } catch (IllegalArgumentException e) {
            return false;
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public String getCurrentDay() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Objects.requireNonNull(environment.getProperty("date.format"))));
    }

    public LocalDateTime convertStringToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Objects.requireNonNull(environment.getProperty("date.format")));

        return LocalDateTime.parse(date, formatter);
    }

    public String convertLocalDateTimeToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(Objects.requireNonNull(environment.getProperty("date.format"))));
    }

    public String getMessage(String keyOfMessage) {
        return environment.getProperty(keyOfMessage);
    }
}
