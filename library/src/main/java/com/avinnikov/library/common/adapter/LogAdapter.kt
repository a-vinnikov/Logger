package com.avinnikov.library.common.adapter

import com.avinnikov.library.data.model.LoggingLevel
import java.io.File


interface LogAdapter {
    fun log(priority: LoggingLevel, message: String)

    fun getLogFile(): File?
}