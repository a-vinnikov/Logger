package com.avinnikov.logger

import com.avinnikov.logger.Logger.LoggingLevel
import com.avinnikov.logger.strategy.LogStrategy

class LoggerPrinter(private val strategy: LogStrategy) {

    @Synchronized
    fun log(priority: LoggingLevel, message: String, throwable: Throwable?) {
        strategy.log(priority, message)
    }

    fun i(message: String, throwable: Throwable?) {
        log(LoggingLevel.INFO, message, throwable)
    }

    fun d(message: String, throwable: Throwable?) {
        log(LoggingLevel.DEBUG, message, throwable)
    }

    fun w(message: String, throwable: Throwable?) {
        log(LoggingLevel.WARNING, message, throwable)
    }

    fun e(message: String, throwable: Throwable?) {
        log(LoggingLevel.ERROR, message, throwable)
    }

    fun c(message: String, throwable: Throwable?) {
        log(LoggingLevel.CRITICAL, message, throwable)
    }
}