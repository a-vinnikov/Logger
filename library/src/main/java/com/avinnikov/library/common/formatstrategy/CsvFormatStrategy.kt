package com.avinnikov.library.common.formatstrategy

import android.content.Context
import android.os.HandlerThread
import com.avinnikov.library.common.logstrategy.DiskLogStrategy
import com.avinnikov.library.common.logstrategy.LogStrategy
import com.avinnikov.library.data.model.LoggingLevel
import java.io.File
import java.io.File.separatorChar
import java.text.SimpleDateFormat
import java.util.*


const val SEPARATOR = ";"
const val NEW_LINE = "\n"

class CsvFormatStrategy(
    private val dateFormat: SimpleDateFormat,
    private val logStrategy: LogStrategy
) : FormatStrategy {

    private val date: Date = Date()

    data class Builder(
        var dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS", Locale.US),
        var logStrategy: LogStrategy? = null
    ) {
        private val maxBites = 5 * 1024 * 1024
        fun dateFormat(dateFormat: SimpleDateFormat) = apply { this.dateFormat = dateFormat }
        fun logStrategy(logStrategy: LogStrategy) = apply { this.logStrategy = logStrategy }
        fun build(context: Context): CsvFormatStrategy {
            if (logStrategy == null) {
                val diskPath = context.filesDir
                val folder = "${diskPath}${separatorChar}logger"

                val ht = HandlerThread("AndroidFileLogger.$folder")
                ht.start()
                val handler = DiskLogStrategy.WriteHandler(ht.looper, folder, maxBites)
                logStrategy = DiskLogStrategy(handler)
            }

            return CsvFormatStrategy(
                dateFormat,
                logStrategy!!
            )
        }
    }

    override fun log(priority: LoggingLevel, message: String) {
        date.time = System.currentTimeMillis()

        val builder = StringBuilder()
        builder.append(dateFormat.format(date))
        builder.append(SEPARATOR)
        builder.append(priority)
        builder.append(SEPARATOR)
        builder.append(message)
        builder.append(NEW_LINE)
        logStrategy.log(priority, builder.toString())
    }

    override fun getLogFile(): File? {
        return logStrategy.getLogFile()
    }
}