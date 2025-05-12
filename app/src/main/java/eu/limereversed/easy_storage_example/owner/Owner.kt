package eu.limereversed.easy_storage_example.owner

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="owner-table")
data class Owner(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name="name")
    var name : String = "",
)