package com.avinnikov.library.common.logger

import java.io.File


interface LoggerInterface {
    fun i(message: String, throwable: Throwable? = null)

    fun d(message: String, throwable: Throwable? = null)

    fun w(message: String, throwable: Throwable? = null)

    fun e(message: String, throwable: Throwable? = null)

    fun c(message: String, throwable: Throwable? = null)

    fun getLogFile(): File?
}