package com.example.newschallenge.news.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.newschallenge.news.data.remote.ArticleImage
import com.google.gson.annotations.SerializedName

/**
 * describes migration related to [NewsNineDatabase]
 */
internal object NewsDatabaseMigration {
    const val latestVersion = 2;

    val allMigrations: Array<Migration>
        get() = arrayOf(
            migration_1_2()
        )

    private fun migration_1_2() = object : Migration(1,2){
        override fun migrate(database: SupportSQLiteDatabase) {
            // Add migration code/SQL here, referencing [V1] and [V2] constants
        }

    }

    object V2{
        object NewsNineArticle{
            const val tableName = "news_nine_article"

            object Colum{

                const val id = "id"
                const val url = "url"
                const val headline = "headline"
                const val theAbstract = "theAbstract"
                const val byLine = "byLine"
                //                const val relatedImages: List<ArticleImage> = emptyList()
                const val timeStamp = "timeStamp"
                const val smallestImage = "smallestImage"
            }
        }
    }

    object V1{
        object NewsNineArticle{
            const val tableName = "news_nine_article"

            object Colum{

                const val id = "id"
                const val url = "url"
                const val headline = "headline"
                const val theAbstract = "theAbstract"
                const val byLine = "byLine"
//                const val relatedImages: List<ArticleImage> = emptyList()
                const val timeStamp = "timeStamp"
                const val smallestImage = "smallestImage"
            }
        }
    }
}