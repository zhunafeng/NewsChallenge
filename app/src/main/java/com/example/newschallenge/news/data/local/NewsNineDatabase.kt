package com.example.newschallenge.news.data.local

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [NewsNineArticleDb::class],
    version = NewsDatabaseMigration.latestVersion

)
abstract class NewsNineDatabase : RoomDatabase() {

    /**
     * Get news article DAO
     */
    abstract fun newsArticlesDao(): NewsNineArticlesDao

    companion object {

        private const val databaseName = "news-nine-db"

        fun buildDefault(context: Context) =
            Room.databaseBuilder(context, NewsNineDatabase::class.java, databaseName)
                .addMigrations(*NewsDatabaseMigration.allMigrations)
                .build()

        @VisibleForTesting
        fun buildTest(context: Context) =
            Room.inMemoryDatabaseBuilder(context, NewsNineDatabase::class.java)
                .build()
    }
}