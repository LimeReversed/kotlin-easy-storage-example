package eu.limereversed.easy_storage_example

import androidx.room.Database
import androidx.room.RoomDatabase

// Tells the database what tables and entities to use. In this case "Product"
@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false,
)

// Tells the database what DAOs to use
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}