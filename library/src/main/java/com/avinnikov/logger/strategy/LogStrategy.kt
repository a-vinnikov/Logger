package com.avinnikov.logger.strategy

import com.avinnikov.logger.data.LoggingLevel
import java.io.File


interface LogStrategy {
    fun log(priority: LoggingLevel, message: String, throwable: Throwable? = null)

    fun getLogFile(): File
}