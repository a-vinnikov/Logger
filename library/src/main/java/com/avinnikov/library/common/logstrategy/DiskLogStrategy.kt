package com.avinnikov.library.common.logstrategy

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.avinnikov.library.data.model.LoggingLevel
import java.io.File
import java.io.FileWriter
import java.io.IOException


class DiskLogStrategy(private val handler: Handler) :
    LogStrategy {
    override fun log(priority: LoggingLevel, message: String) {
        handler.sendMessage(handler.obtainMessage(priority.ordinal, message))
    }

    override fun getLogFile(): File? {
        return if (handler is WriteHandler) {
            handler.getLogFile()
        } else {
            null
        }
    }

    internal class WriteHandler(
        looper: Looper,
        private val folder: String,
        maxFileSize: Int
    ) :
        Handler(looper) {

        init {
            val logFile = getLogFile()
            if (logFile.exists() && logFile.length() >= maxFileSize) {
                val fileWriter = FileWriter(logFile, false)
                var lines = logFile.readLines()
                lines = lines.subList(lines.size / 2, lines.size)
                lines.forEach {
                    fileWriter.write("$it\n")
                }
                fileWriter.flush()
                fileWriter.close()
            }
        }

        override fun handleMessage(msg: Message) {
            val content = msg.obj as String

            var fileWriter: FileWriter? = null
            val logFile = getLogFile()

            try {
                fileWriter = FileWriter(logFile, true)

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

        fun getLogFile(fileName: String = "backyardLogs"): File {
            val folder = File(folder)
            if (!folder.exists()) {
                folder.mkdirs()
            }

            return File(folder, String.format("%s.csv", fileName))
        }
    }
}