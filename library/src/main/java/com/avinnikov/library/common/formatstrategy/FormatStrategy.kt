package com.avinnikov.library.common.formatstrategy

import com.avinnikov.library.data.model.LoggingLevel
import java.io.File

interface FormatStrategy {
    fun log(priority: LoggingLevel, message: String)

    fun getLogFile(): File?
}