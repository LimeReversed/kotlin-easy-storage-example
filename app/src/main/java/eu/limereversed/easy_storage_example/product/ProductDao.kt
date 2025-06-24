package eu.limereversed.easy_storage_example.product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(productEntity: Product): Long

    @Query("Select * from `product-table`")
    fun getAllProducts(): Flow<List<Product>>

    @Update
    suspend fun updateProduct(productEntity: Product)

    @Delete
    suspend fun deleteProduct(productEntity: Product)

    @Query("Select * from `product-table` where id=:id")
    fun getProductById(id: Long): Flow<Product>
}