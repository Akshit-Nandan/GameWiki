package com.example.gamewiki.repository

import com.example.gamewiki.api.RetrofitInstance
import com.example.gamewiki.model.GameNode
import com.example.gamewiki.model.InfoNode
import retrofit2.Response
import retrofit2.http.Query

class Repository {
    suspend fun getGameNode(game_id:Int) : Response<GameNode> {
        return RetrofitInstance.api.getGameNode(game_id)
    }
    suspend fun getAllGames( tag : String, sort : String = "relevance",  platform : String = "all")
    : Response<List<InfoNode>> {
        return RetrofitInstance.api.getAllGames(tag,sort,platform)
    }
}