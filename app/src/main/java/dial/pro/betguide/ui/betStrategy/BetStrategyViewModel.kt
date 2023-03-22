package dial.pro.betguide.ui.betStrategy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dial.pro.betguide.data.NetworkModule
import dial.pro.betguide.data.Repository
import dial.pro.betguide.model.BetStrategyItem
import dial.pro.betguide.model.DataResult
import kotlinx.coroutines.launch

class BetStrategyViewModel : ViewModel() {

    private val _strategyMap: MutableLiveData<DataResult<BetStrategyItem>> = MutableLiveData()
    val strategyMap: LiveData<DataResult<BetStrategyItem>>
        get() = _strategyMap

    init {
        getStrategy()
    }

    fun getStrategy() {
        viewModelScope.launch {
            Repository.performRequest {
                NetworkModule.apiService.getStrategyList()
            }.collect {
                _strategyMap.value = it
            }
        }
    }
}