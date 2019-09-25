package com.avinnikov.logger.strategy

import com.avinnikov.logger.Logger.LoggingLevel

interface LogStrategy {
    fun log(priority: LoggingLevel, message: String)
}