package lt.arnas.androidtopics.first_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lt.arnas.androidtopics.repository.reqres.ReqresServiceClient
import lt.arnas.androidtopics.repository.reqres.UsersResponse
import lt.arnas.androidtopics.repository.reqres.news_api.NewsApiServiceClient
import lt.arnas.androidtopics.repository.reqres.news_api.TopHeadlinesResponse

class FirstFragmentViewModel : ViewModel() {
    private val _itemsStateFlow: MutableStateFlow<UsersResponse?> =
        MutableStateFlow(UsersResponse())
    val itemsStateFlow = _itemsStateFlow.asStateFlow()

    private val _topNewsStateFlow: MutableStateFlow<TopHeadlinesResponse?> =
        MutableStateFlow(TopHeadlinesResponse())
    val topNewsStateFlow = _topNewsStateFlow.asStateFlow()
    fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = ReqresServiceClient.providesApiService().getUsers()
            _itemsStateFlow.value = resp.body()
        }
    }

    fun fetchTopNews(){
        viewModelScope.launch(Dispatchers.IO) {
            val resp = NewsApiServiceClient.providesApiService().getTopNews("us")
            _topNewsStateFlow.value = resp.body()
        }
    }
}