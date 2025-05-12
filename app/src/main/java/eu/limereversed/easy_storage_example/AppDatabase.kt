package eu.limereversed.easy_storage_example

import androidx.room.Database
import androidx.room.RoomDatabase
import eu.limereversed.easy_storage_example.owner.Owner
import eu.limereversed.easy_storage_example.owner.OwnerDao
import eu.limereversed.easy_storage_example.owner.OwnerProductCrossRef
import eu.limereversed.easy_storage_example.owner.OwnerProductCrossRefDao
import eu.limereversed.easy_storage_example.product.Product
import eu.limereversed.easy_storage_example.product.ProductDao

// Tells the database what tables and entities to use. In this case "Product"
@Database(
    entities = [Product::class, Owner::class, OwnerProductCrossRef::class],
    version = 1,
    exportSchema = false,
)

// Tells the database what DAOs to use
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun ownerDao(): OwnerDao
    abstract fun ownerProductCrossRefDao(): OwnerProductCrossRefDao

}