package com.avinnikov.logger.printer

interface BaseLogger {
    fun i(message: String)

    fun d(message: String)

    fun w(message: String, throwable: Throwable? = null)

    fun w(throwable: Throwable)

    fun e(message: String, throwable: Throwable? = null)

    fun e(throwable: Throwable)

    fun c(message: String, throwable: Throwable? = null)

    fun c(throwable: Throwable)
}