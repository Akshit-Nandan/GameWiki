package com.example.gamewiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamewiki.api.RetrofitInstance
import com.example.gamewiki.model.GameNode
import com.example.gamewiki.model.InfoNode
import com.example.gamewiki.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    val apiResponse : MutableLiveData<Response<GameNode>> = MutableLiveData()
    val apiResponseInfo : MutableLiveData<Response<List<InfoNode>>> = MutableLiveData()

    fun getGameNode(game_id : Int){
        viewModelScope.launch {
            apiResponse.value = repository.getGameNode(game_id)
        }

    }
    fun getAllGames( tag : String, sort : String = "relevance",  platform : String = "all"){
        viewModelScope.launch {
            apiResponseInfo.value = repository.getAllGames(tag,sort,platform)
        }

    }
}