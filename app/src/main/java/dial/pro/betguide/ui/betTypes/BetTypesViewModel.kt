package dial.pro.betguide.ui.betTypes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dial.pro.betguide.data.NetworkModule
import dial.pro.betguide.data.Repository
import dial.pro.betguide.model.BetTypesItem
import dial.pro.betguide.model.DataResult
import kotlinx.coroutines.launch

class BetTypesViewModel : ViewModel() {

    private val _typesMap: MutableLiveData<DataResult<BetTypesItem>> = MutableLiveData()
    val strategyMap: LiveData<DataResult<BetTypesItem>>
        get() = _typesMap

    init {
        getTypes()
    }

    fun getTypes() {
        viewModelScope.launch {
            Repository.performRequest {
                NetworkModule.apiService.getBetTypesList()
            }.collect() {
                _typesMap.value = it
            }
        }
    }
}