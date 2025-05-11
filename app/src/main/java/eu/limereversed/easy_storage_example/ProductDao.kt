package eu.limereversed.easy_storage_example

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addProduct(productEntity: Product)

    @Query("Select * from `product-table`")
    abstract fun getAllProducts(): Flow<List<Product>>

    @Update
    abstract suspend fun updateProduct(productEntity: Product)

    @Delete
    abstract suspend fun deleteProduct(productEntity: Product)

    @Query("Select * from `product-table` where id=:id")
    abstract fun getProductById(id: Long): Flow<Product>
}