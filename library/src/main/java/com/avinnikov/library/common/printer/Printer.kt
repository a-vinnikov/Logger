package com.avinnikov.library.common.printer

import com.avinnikov.library.data.model.LoggingLevel
import java.io.File


interface Printer {
    fun log(priority: LoggingLevel, message: String, throwable: Throwable? = null)

    fun i(message: String, throwable: Throwable? = null)

    fun d(message: String, throwable: Throwable? = null)

    fun w(message: String, throwable: Throwable? = null)

    fun e(message: String, throwable: Throwable? = null)

    fun c(message: String, throwable: Throwable? = null)

    fun getLogFile(): File?
}