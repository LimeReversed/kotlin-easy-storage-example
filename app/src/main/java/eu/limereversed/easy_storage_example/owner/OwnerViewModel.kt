package eu.limereversed.easy_storage_example.owner

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.limereversed.easy_storage_example.Graph
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow

// ViewModel is the holder of states and business logic.
class OwnerViewModel(private val ownerRepository: OwnerRepository = Graph.ownerRepository) : ViewModel() {

    var ownerNameState by mutableStateOf("")
    var ownerProductIDsState by mutableStateOf<List<Int>>(listOf<Int>())

    fun onOwnerNameChange(newName:String){
        ownerNameState = newName
    }

    fun onOwnerProductIDsChange(newList:List<Int>){
        ownerProductIDsState = newList
    }

    lateinit var getOwners: Flow<List<Owner>>

    init {
        // This has to be launched on the main thread, so we don't add the IO dispatchers here.
        viewModelScope.launch {
            getOwners = ownerRepository.getOwners()
        }
    }

    fun getOwnerById(id:Long): Flow<Owner> {
        return ownerRepository.getOwnerById(id)
    }

    fun getOwnerWithProducts(id:Long): Flow<OwnerWithProducts> {
        return ownerRepository.getOwnerWithProducts(id)
    }

    fun addOwner(ownerEntity: Owner) {
        viewModelScope.launch(Dispatchers.IO) {
            ownerRepository.addOwner(ownerEntity)
        }
    }

    fun deleteOwner(ownerEntity: Owner) {
        viewModelScope.launch(Dispatchers.IO) {
            ownerRepository.deleteOwner(ownerEntity)
        }
    }

    fun updateOwner(ownerEntity: Owner) {
        viewModelScope.launch(Dispatchers.IO) {
            ownerRepository.updateOwner(ownerEntity)
        }
    }

    fun assignProductToOwner(ownerId: Long, productId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            ownerRepository.assignProductToOwner(ownerId, productId)
        }
    }

    fun unassignProductFromOwner(ownerId: Long?, productId: Long?) {
        if (ownerId == null || productId == null) return

        viewModelScope.launch(Dispatchers.IO) {
            ownerRepository.unassignProductFromOwner(ownerId, productId)
        }
    }
}