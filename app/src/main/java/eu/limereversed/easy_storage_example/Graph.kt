package eu.limereversed.easy_storage_example

import android.content.Context
import androidx.room.Room

// Defines the database builder
object Graph {
    lateinit var database: AppDatabase

    val productRepository by lazy {
        ProductRepository(productDao = database.productDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, AppDatabase::class.java, "appDatabase.db").build()
    }
}