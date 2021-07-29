package ru.btelepov.cryptoanalyzer.repository

import ru.btelepov.cryptoanalyzer.database.MainDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mainDao:MainDao) {
}