package eu.limereversed.easy_storage_example.owner

import androidx.room.Entity

@Entity(primaryKeys = ["ownerID", "productID"])
data class OwnerProductCrossRef(
    val ownerID: Long,
    val productID: Long
)