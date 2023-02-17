package com.example.gamewiki.api

import com.example.gamewiki.model.GameNode
import com.example.gamewiki.model.InfoNode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {
    @GET("game")
    suspend fun getGameNode(@Query("id") game_id:Int ) : Response<GameNode>

    @GET("games")
    suspend fun getAllGames(
        @Query("tag") tag : String,
        @Query("sort-by") sort : String = "relevance",
        @Query("platform") platform : String = "all"
    ) : Response<List<InfoNode>>
}