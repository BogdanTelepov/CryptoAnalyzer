package ru.btelepov.cryptoanalyzer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.btelepov.cryptoanalyzer.data.database.MainDao
import ru.btelepov.cryptoanalyzer.data.database.MainDatabase
import ru.btelepov.cryptoanalyzer.models.CryptoCoinItem
import ru.btelepov.cryptoanalyzer.models.Quote
import ru.btelepov.cryptoanalyzer.models.Usd

@RunWith(AndroidJUnit4::class)
@SmallTest
class DaoTest {

    @get:Rule
    var instantTasExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MainDatabase
    private lateinit var dao: MainDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MainDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.mainDao()
    }


    @After
    fun tearDown(){
            database.close()
    }
    @Test
    fun insertCoinItem() = runBlockingTest {
        val item = CryptoCoinItem(48,"2013-04-28T00:00:00.000Z",1,"2021-08-01T07:00:41.000Z","Bitcoin",
            Quote(Usd(18.35467316909869)),"Btc"
        )
        val item2 = CryptoCoinItem(48,"2013-04-28T00:00:00.000Z",1,"2021-08-01T07:00:41.000Z","Bitcoin",
            Quote(Usd(18.35467316909869)),"Btc"
        )
        val item3 = CryptoCoinItem(48,"2013-04-28T00:00:00.000Z",1,"2021-08-01T07:00:41.000Z","Bitcoin",
            Quote(Usd(18.35467316909869)),"Btc"
        )

        val list:List<CryptoCoinItem> = listOf(
            item,
            item2,
            item3

        )
        dao.insertCoinEntity(list)
        val listItem = dao.readAllCoins().getOrAwaitValue()
        assertThat(listItem).contains(item)
        assertThat(listItem).contains(item2)
        assertThat(listItem).contains(item3)

    }


}