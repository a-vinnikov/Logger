package com.avinnikov.library.common.logstrategy

import com.avinnikov.library.data.model.LoggingLevel
import java.io.File


interface LogStrategy {
    fun log(priority: LoggingLevel, message: String)
    
    fun getLogFile(): File?
}