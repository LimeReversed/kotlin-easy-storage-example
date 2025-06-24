package eu.limereversed.easy_storage_example.owner

import androidx.room.Entity

@Entity(tableName = "owner-product-cross-ref-table", primaryKeys = ["ownerID", "productID"])
data class OwnerProductCrossRef(
    val ownerID: Long,
    val productID: Long
)