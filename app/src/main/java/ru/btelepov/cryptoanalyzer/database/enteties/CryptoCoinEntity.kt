package ru.btelepov.cryptoanalyzer.database.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.btelepov.cryptoanalyzer.models.CryptoResponse

@Entity(tableName = "coins_table")
class CryptoCoinEntity(var cryptoResponse: CryptoResponse) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}