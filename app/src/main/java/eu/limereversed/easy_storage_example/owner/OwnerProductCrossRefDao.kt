package eu.limereversed.easy_storage_example.owner

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface OwnerProductCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: OwnerProductCrossRef)

    @Delete
    suspend fun deleteCrossRef(crossRef: OwnerProductCrossRef)
}