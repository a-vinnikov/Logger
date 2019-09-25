package com.avinnikov.logger

import com.avinnikov.logger.printer.BaseLogger
import com.avinnikov.logger.printer.LoggerPrinter
import com.avinnikov.logger.strategy.LogStrategy
import java.io.File

class Logger(private val strategy: LogStrategy) : BaseLogger {

    private val printer = LoggerPrinter(strategy)

    override fun i(message: String) {
        printer.i(message)
    }

    override fun d(message: String) {
        printer.d(message)
    }

    override fun w(message: String, throwable: Throwable?) {
        printer.w(message, throwable)
    }

    override fun w(throwable: Throwable) {
        printer.w(throwable.message ?: "", throwable)
    }

    override fun e(message: String, throwable: Throwable?) {
        printer.e(message, throwable)
    }

    override fun e(throwable: Throwable) {
        printer.e(throwable.message ?: "", throwable)
    }

    override fun c(message: String, throwable: Throwable?) {
        printer.c(message, throwable)
    }

    override fun c(throwable: Throwable) {
        printer.c(throwable.message ?: "", throwable)
    }

    fun getLogFile(): File {
        return strategy.getLogFile()
    }
}