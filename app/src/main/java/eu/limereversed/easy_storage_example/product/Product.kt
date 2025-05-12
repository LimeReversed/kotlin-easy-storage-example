package eu.limereversed.easy_storage_example.product

// Product.kt

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="product-table")
data class Product(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name="serial-number")
    var serialNumber : Int = -1,

    @ColumnInfo(name="price")
    var price : Int = -1,
)