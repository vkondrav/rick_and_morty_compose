package com.vkondrav.ram.room

import androidx.room.Room
import com.vkondrav.ram.test.BaseRobolectricTest
import org.junit.After
import org.junit.Before

abstract class BaseDatabaseTest : BaseRobolectricTest() {

    protected lateinit var db: AppDatabase

    @Before
    open fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java,
        ).build()
    }

    @After
    fun tearDown() {
        db.close()
    }
}
