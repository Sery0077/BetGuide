package dial.pro.betguide.ui.betTypes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dial.pro.betguide.data.repository.BetTypesRepository
import dial.pro.betguide.model.BetTypesItem
import dial.pro.betguide.model.DataResult
import kotlinx.coroutines.launch

class BetTypesViewModel : ViewModel() {

    private val _typesMap: MutableLiveData<DataResult<BetTypesItem>> = MutableLiveData()
    val strategyMap: LiveData<DataResult<BetTypesItem>>
        get() = _typesMap

    private val repository = BetTypesRepository()

    init {
        getTypes()
    }

    fun getTypes() {
        viewModelScope.launch {
            repository.getBetTypes().collect() {
                _typesMap.value = it
            }
        }
    }
}