package eu.limereversed.easy_storage_example.owner

import kotlinx.coroutines.flow.Flow

// A repository is responsible for communicating with the database.
class OwnerRepository(private val ownerDao: OwnerDao, private val crossRefDao: OwnerProductCrossRefDao) {

    fun getOwners(): Flow<List<Owner>> = ownerDao.getAllOwners()

    fun getOwnerById(id: Long): Flow<Owner> = ownerDao.getOwnerById(id)

    suspend fun addOwner(owner: Owner) {
        ownerDao.addOwner(owner)
    }

    suspend fun updateOwner(owner: Owner) {
        ownerDao.updateOwner(owner)
    }

    suspend fun deleteOwner(owner: Owner) {
        ownerDao.deleteOwner(owner)
    }

    fun getOwnerWithProducts(ownerId: Long): Flow<OwnerWithProducts> =
        ownerDao.getOwnerWithProducts(ownerId)

    suspend fun assignProductToOwner(ownerId: Long, productId: Long) {
        crossRefDao.insertCrossRef(OwnerProductCrossRef(ownerId, productId))
    }

    suspend fun unassignProductFromOwner(ownerId: Long, productId: Long) {
        crossRefDao.deleteCrossRef(OwnerProductCrossRef(ownerId, productId))
    }
}