package com.yk.training.javaapp.utils;

import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimeUtils {

  private static final Logger LOGGER = LogManager.getLogger(TimeUtils.class);

  private TimeUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static <T> T measureTime(String label, Callable<T> callable) {
    long start = System.currentTimeMillis();

    try {
      return callable.call();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      long end = System.currentTimeMillis();
      long duration = end - start;
      LOGGER.info("Measure time. Label: {}, duration: {} ms.", label, duration);
    }
  }
}
