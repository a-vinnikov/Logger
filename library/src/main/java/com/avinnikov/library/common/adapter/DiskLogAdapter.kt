package com.avinnikov.library.common.adapter

import android.content.Context
import com.avinnikov.library.common.formatstrategy.CsvFormatStrategy
import com.avinnikov.library.common.formatstrategy.FormatStrategy
import com.avinnikov.library.data.model.LoggingLevel
import java.io.File

class DiskLogAdapter(context: Context) : LogAdapter {

    private val formatStrategy: FormatStrategy

    init {
        formatStrategy = CsvFormatStrategy.Builder().build(context)
    }

    override fun log(priority: LoggingLevel, message: String) {
        formatStrategy.log(priority, message)
    }

    override fun getLogFile(): File? {
        return formatStrategy.getLogFile()
    }
}