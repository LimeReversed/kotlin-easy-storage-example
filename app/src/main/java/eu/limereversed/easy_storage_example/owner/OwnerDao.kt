package eu.limereversed.easy_storage_example.owner

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface OwnerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addOwner(ownerEntity: Owner)

    @Query("Select * from `owner-table`")
    fun getAllOwners(): Flow<List<Owner>>

    @Update
    suspend fun updateOwner(ownerEntity: Owner)

    @Delete
    suspend fun deleteOwner(ownerEntity: Owner)

    @Query("Select * from `owner-table` where id=:id")
    fun getOwnerById(id: Long): Flow<Owner>

    @Transaction
    @Query("Select * from `owner-table` where id= :ownerID")
    fun getOwnerWithProducts(ownerID: Long): Flow<OwnerWithProducts>
}