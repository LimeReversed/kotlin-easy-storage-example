package eu.limereversed.easy_storage_example.owner

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import eu.limereversed.easy_storage_example.product.Product

data class OwnerWithProducts (
    @Embedded val owner: Owner,
    @Relation(
        parentColumn = "id",    // Owner's PK
        entityColumn = "id",     // Product's PK
        associateBy = Junction(
            value = OwnerProductCrossRef::class,
            parentColumn = "ownerID",
            entityColumn = "productID"
        )
    )
    val products: List<Product>
)