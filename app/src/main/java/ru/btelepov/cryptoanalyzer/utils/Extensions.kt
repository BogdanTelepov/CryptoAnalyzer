package ru.btelepov.cryptoanalyzer.utils

fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()

fun Double.format(digits: Int) = "%.${digits}f".format(this)