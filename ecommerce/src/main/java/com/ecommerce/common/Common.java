package com.ecommerce.common;

/**
 * Utility class for common use where the treatment of dates is centralized, 
 * such as the format and the conversion of string to date and date to String.
 * Access to the resource file "application.properties" is implemented, as well as date validation.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class Common {

	@Autowired
	Environment environment;

	public static final String DATE_FORMAT = "date.format";

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
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(environment.getProperty(DATE_FORMAT)));
	}

	public LocalDateTime convertStringToLocalDateTime(String date) {
		return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(environment.getProperty(DATE_FORMAT)));
	}

	public String convertLocalDateTimeToString(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern(environment.getProperty(DATE_FORMAT)));
	}

	public String getMessage(String key) {
		return this.environment.getProperty(key);
	}
}
