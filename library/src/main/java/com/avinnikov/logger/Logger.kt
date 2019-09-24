package com.avinnikov.logger

import com.avinnikov.logger.strategy.LogStrategy

class Logger(private val strategy: LogStrategy) {

    enum class LoggingLevel {
        INFO,
        DEBUG,
        WARNING,
        ERROR,
        CRITICAL
    }

    private val printer = LoggerPrinter(strategy)

    fun i(message: String, throwable: Throwable?) {
        printer.i(message, throwable)
    }

    fun d(message: String, throwable: Throwable?) {
        printer.d(message, throwable)
    }

    fun w(message: String, throwable: Throwable?) {
        printer.w(message, throwable)
    }

    fun e(message: String, throwable: Throwable?) {
        printer.e(message, throwable)
    }

    fun c(message: String, throwable: Throwable?) {
        printer.c(message, throwable)
    }
}