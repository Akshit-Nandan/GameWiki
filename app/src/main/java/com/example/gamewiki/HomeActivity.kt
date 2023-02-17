package com.example.gamewiki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamewiki.adapter.GameCardClicked
import com.example.gamewiki.adapter.GameListAdapter
import com.example.gamewiki.databinding.ActivityHomeBinding

import com.example.gamewiki.repository.Repository
import com.example.gamewiki.uitility.Constants.Companion.GAME_ID
import com.example.gamewiki.viewmodel.MainViewModel
import com.example.gamewiki.viewmodel.MainViewModelFactory


class HomeActivity : AppCompatActivity(), GameCardClicked {
    private lateinit var viewModel : MainViewModel
    private lateinit var binding : ActivityHomeBinding
    private val recyclerView by lazy{
        binding.mainRecyclerView
    }
    private val myAdapter by lazy {
        GameListAdapter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository  = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter

        updateList()

        binding.Browser.setOnClickListener{
            resetBtn()
            it.setBackgroundResource(R.drawable.btn_background)
            updateList(platform = "browser")
        }
        binding.Pc.setOnClickListener{
            resetBtn()
            it.setBackgroundResource(R.drawable.btn_background)
            updateList(platform = "pc")
        }
        binding.All.setOnClickListener{
            resetBtn()
            it.setBackgroundResource(R.drawable.btn_background)
            updateList(platform = "all")
        }

    }

    override fun onItemClicked(id: Int) {
        var intent = Intent(this,GameIdActivity::class.java)
        Log.d("Response",id.toString())
        intent.putExtra(GAME_ID,id)
        startActivity(intent)
    }
    fun resetBtn(){
        binding.Browser.setBackgroundResource(R.drawable.back_ground)
        binding.Pc.setBackgroundResource(R.drawable.back_ground)
        binding.All.setBackgroundResource(R.drawable.back_ground)

    }
    fun updateList( tag : String = "", sort : String = "relevance",  platform : String = "all"){
        viewModel.getAllGames(tag,sort,platform)
        viewModel.apiResponseInfo.observe(this, Observer{ response ->
            if(response.isSuccessful != null){
                response.body()?.let { myAdapter.updateList(it) }
            }
        })
    }
}