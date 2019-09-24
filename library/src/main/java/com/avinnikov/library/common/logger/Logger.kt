package com.avinnikov.library.common.logger

import android.content.Context
import com.avinnikov.library.common.printer.LoggerPrinter
import java.io.File


class Logger(context: Context) : LoggerInterface {

    private val printer = LoggerPrinter(context)

    override fun i(message: String, throwable: Throwable?) {
        printer.i(message, throwable)
    }

    override fun d(message: String, throwable: Throwable?) {
        printer.d(message, throwable)
    }

    override fun w(message: String, throwable: Throwable?) {
        printer.w(message, throwable)
    }

    override fun e(message: String, throwable: Throwable?) {
        printer.e(message, throwable)
    }

    override fun c(message: String, throwable: Throwable?) {
        printer.c(message, throwable)
    }

    override fun getLogFile(): File? {
        return printer.getLogFile()
    }
}