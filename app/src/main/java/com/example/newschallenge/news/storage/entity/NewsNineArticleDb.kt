package com.example.newschallenge.news.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newschallenge.news.storage.entity.NewsNineArticleDb.NewsNineArticles.Column
import com.example.newschallenge.news.storage.entity.NewsNineArticleDb.NewsNineArticles.tableName
import com.google.gson.annotations.SerializedName

@Entity(tableName = tableName)
data class NewsNineArticleDb(
    /**
     * Primary key for Room.
     */
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    /**
     * news_id of the article
     */
    @ColumnInfo(name = Column.news_id)
    var news_id: Int? = null,

    /**
     * url of the article
     */
    @SerializedName("url")
    var url: String? = null,

    /**
     * headline of the article
     */
    @ColumnInfo(name = Column.headline)
    var headline: String? = null,

    /**
     * theAbstract of the article
     */
    @ColumnInfo(name = Column.theAbstract)
    var theAbstract: String? = null,

    /**
     * byLine of the article
     */
    @ColumnInfo(name = Column.byLine)
    var byLine: String? = null,

    /**
     * smallestImage of the article
     */
    @ColumnInfo(name = Column.smallestImage)
    var smallestImage: String? = null,

    /**
     * smallestImage of the article
     */
    @ColumnInfo(name = Column.timeStamp)
    var timeStamp: Long? = null

    ) {

    object NewsNineArticles {
        const val tableName = "news_nine_article"

        object Column {
            const val news_id = "news_id"
            const val headline = "headline"
            const val theAbstract = "theAbstract"
            const val byLine = "byLine"
            const val smallestImage = "smallestImage"
            const val timeStamp = "timeStamp"
        }
    }
}
