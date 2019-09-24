package com.avinnikov.library.common.printer

import android.content.Context
import com.avinnikov.library.common.adapter.DiskLogAdapter
import com.avinnikov.library.data.model.LoggingLevel
import java.io.File


class LoggerPrinter(context: Context) : Printer {

    private var logAdapter = DiskLogAdapter(context)

    @Synchronized
    override fun log(priority: LoggingLevel, message: String, throwable: Throwable?) {
        logAdapter.log(priority, message)
    }

    override fun i(message: String, throwable: Throwable?) {
        log(LoggingLevel.INFO, message, throwable)
    }

    override fun d(message: String, throwable: Throwable?) {
        log(LoggingLevel.DEBUG, message, throwable)
    }

    override fun w(message: String, throwable: Throwable?) {
        log(LoggingLevel.WARNING, message, throwable)
    }

    override fun e(message: String, throwable: Throwable?) {
        log(LoggingLevel.ERROR, message, throwable)
    }

    override fun c(message: String, throwable: Throwable?) {
        log(LoggingLevel.CRITICAL, message, throwable)
    }

    override fun getLogFile(): File? {
        return logAdapter.getLogFile()
    }
}