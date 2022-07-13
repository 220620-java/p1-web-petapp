package com.revature.petapp.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

// singleton?
/**
 * Write logs to a file using a specific format.
 * @author SierraNicholes
 *
 */
public class Logger {
	private static Logger logger;
	private static LoggingLevel level = LoggingLevel.FATAL;
	
	private Logger() {
		
	}
	
	public static synchronized Logger getLogger() {
		if (logger == null) {
			logger = new Logger();
		}
		return logger;
	}
	
	public void log(String message, LoggingLevel level) {
		message = LocalDateTime.now().toString() + " --- " + level + ": " + message;
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/log.log", true))) {
			writer.write(message);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setLoggingLevel(LoggingLevel level) {
		Logger.level = level;
	}
}
