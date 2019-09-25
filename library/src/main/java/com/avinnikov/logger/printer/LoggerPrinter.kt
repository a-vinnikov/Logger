package com.avinnikov.logger.printer

import com.avinnikov.logger.data.LoggingLevel
import com.avinnikov.logger.strategy.LogStrategy

class LoggerPrinter(private val strategy: LogStrategy) : BaseLogger {

    @Synchronized
    fun log(priority: LoggingLevel, message: String, throwable: Throwable? = null) {
        strategy.log(priority, message, throwable)
    }

    override fun i(message: String) {
        log(LoggingLevel.INFO, message)
    }

    override fun d(message: String) {
        log(LoggingLevel.DEBUG, message)
    }

    override fun w(message: String, throwable: Throwable?) {
        log(LoggingLevel.WARNING, message, throwable)
    }

    override fun w(throwable: Throwable) {
        log(LoggingLevel.WARNING, throwable.message ?: "", throwable)
    }

    override fun e(message: String, throwable: Throwable?) {
        log(LoggingLevel.ERROR, message, throwable)
    }

    override fun e(throwable: Throwable) {
        log(LoggingLevel.ERROR, throwable.message ?: "", throwable)
    }

    override fun c(message: String, throwable: Throwable?) {
        log(LoggingLevel.CRITICAL, message, throwable)
    }

    override fun c(throwable: Throwable) {
        log(LoggingLevel.CRITICAL, throwable.message ?: "", throwable)
    }
}