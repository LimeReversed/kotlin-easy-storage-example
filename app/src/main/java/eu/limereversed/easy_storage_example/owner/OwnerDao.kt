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
abstract class OwnerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addOwner(ownerEntity: Owner)

    @Query("Select * from `owner-table`")
    abstract fun getAllOwners(): Flow<List<Owner>>

    @Update
    abstract suspend fun updateOwner(ownerEntity: Owner)

    @Delete
    abstract suspend fun deleteOwner(ownerEntity: Owner)

    @Query("Select * from `owner-table` where id=:id")
    abstract fun getOwnerById(id: Long): Flow<Owner>

    @Transaction
    @Query("Select * from `owner-table` where id= :ownerID")
    abstract fun getOwnerWithProducts(ownerID: Long): Flow<OwnerWithProducts>
}