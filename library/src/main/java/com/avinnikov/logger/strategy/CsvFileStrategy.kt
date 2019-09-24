package com.avinnikov.logger.strategy

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import com.avinnikov.logger.Logger.LoggingLevel
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

class CsvFileStrategy(
    private val handler: Handler,
    private val dateFormat: SimpleDateFormat,
    private val fieldSeparator: String
) : LogStrategy {
    private val date = Date()

    override fun log(priority: LoggingLevel, message: String) {
        date.time = System.currentTimeMillis()

        val builder = StringBuilder()
        builder.append(dateFormat.format(date))
        builder.append(fieldSeparator)
        builder.append(priority)
        builder.append(fieldSeparator)
        builder.append(message)
        builder.append(LINE_SEPARATOR)

        handler.sendMessage(handler.obtainMessage(priority.ordinal, builder.toString()))
    }

    companion object Builder {
        private const val MAX_BYTES = 5 * 1024 * 1024
        const val DEFAULT_FIELD_SEPARATOR = ";"
        const val LINE_SEPARATOR = "\n"
        val DEFAULT_DATE_FORMAT = SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS", Locale.US)

        @JvmStatic
        fun build(
            weakContext: WeakReference<Context>,
            fileName: String,
            dateFormat: SimpleDateFormat?,
            fieldSeparator: String?
        ): CsvFileStrategy {
            val diskPath = weakContext.get()?.filesDir
            val folder = "${diskPath?.absolutePath ?: ""}${File.separatorChar}logger"
            val filePath = "${folder}${File.separatorChar}${fileName}.csv"

            val ht = HandlerThread("AndroidFileLogger.$folder")
            ht.start()
            val handler = WriteHandler(ht.looper, filePath, MAX_BYTES)
            return CsvFileStrategy(
                handler,
                dateFormat ?: DEFAULT_DATE_FORMAT,
                fieldSeparator ?: DEFAULT_FIELD_SEPARATOR
            )
        }
    }

    private class WriteHandler(
        looper: Looper,
        private val loggerFilePath: String,
        maxFileSize: Int
    ) : Handler(looper) {

        init {
            val logFile = getLoggerFile()
            if (logFile.exists() && logFile.length() >= maxFileSize) {
                val fileWriter = FileWriter(logFile, false)
                // TODO: may be strong operation, refactor this
                var lines = logFile.readLines()
                lines = lines.subList(lines.size / 2, lines.size)
                lines.forEach {
                    fileWriter.write("$it\n")
                }
                fileWriter.flush()
                fileWriter.close()
            }
        }

        private fun getLoggerFile(): File {
            val logFile = File(loggerFilePath)
            if (!logFile.exists()) {
                logFile.parentFile.mkdirs()
            }
            return logFile
        }

        override fun handleMessage(msg: Message) {
            val content = msg.obj as String

            var fileWriter: FileWriter? = null

            try {
                fileWriter = FileWriter(getLoggerFile(), true)

                fileWriter.append(content)

                fileWriter.flush()
                fileWriter.close()
            } catch (e: IOException) {
                if (fileWriter != null) {
                    try {
                        fileWriter.flush()
                        fileWriter.close()
                    } catch (e: IOException) {
                    }
                }
            }
        }
    }
}