package eu.limereversed.easy_storage_example

import android.content.Context
import androidx.room.Room
import eu.limereversed.easy_storage_example.product.ProductRepository
import eu.limereversed.easy_storage_example.owner.OwnerRepository

// Defines the database builder
object Graph {
    lateinit var database: AppDatabase

    val productRepository by lazy {
        ProductRepository(productDao = database.productDao())
    }

    val ownerRepository by lazy {
        OwnerRepository(ownerDao = database.ownerDao(), crossRefDao = database.ownerProductCrossRefDao())

    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, AppDatabase::class.java, "appDatabase.db").build()
    }
}