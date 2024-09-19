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
import org.springframework.stereotype.Component;

@Component
public class Common {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String MSG_DATE_FORMAT_INCORRECT = "Format of Date incorrect, correct format yyyy-MM-dd HH:mm:ss";
    public static final String MSG_PRODUCT_ID_INVALID = "Product ID invalid";
    public static final String MSG_RESOURCE_NOT_FOUND = "Resource not found";
    public static final String MSG_BRAND_ID_INVALID =  "Brand ID invalid";

    public static boolean isDateValid(String date) {

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

    public static String getCurrentDay() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Objects.requireNonNull(DATE_FORMAT)));
    }

    public static LocalDateTime convertStringToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Objects.requireNonNull(DATE_FORMAT));

        return LocalDateTime.parse(date, formatter);
    }

    public static String convertLocalDateTimeToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(Objects.requireNonNull(DATE_FORMAT)));
    }
}
