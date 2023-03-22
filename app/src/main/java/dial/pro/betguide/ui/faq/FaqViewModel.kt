package dial.pro.betguide.ui.faq

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dial.pro.betguide.data.NetworkModule
import dial.pro.betguide.data.Repository
import dial.pro.betguide.model.DataResult
import dial.pro.betguide.model.FAQItem
import kotlinx.coroutines.launch

class FaqViewModel : ViewModel() {
    private val _faqText: MutableLiveData<DataResult<FAQItem>> = MutableLiveData()
    val faqText: LiveData<DataResult<FAQItem>>
        get() = _faqText

    init {
        getFAQ()
    }

    fun getFAQ() {
        viewModelScope.launch {
            Repository.performRequest {
                NetworkModule.apiService.getFAQ()
            }.collect {
                _faqText.value = it
            }
        }
    }
}