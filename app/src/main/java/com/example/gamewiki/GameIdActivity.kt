package com.example.gamewiki

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

import com.example.gamewiki.databinding.ActivityGameIdBinding
import com.example.gamewiki.repository.Repository
import com.example.gamewiki.uitility.Constants.Companion.GAME_ID
import com.example.gamewiki.viewmodel.MainViewModel
import com.example.gamewiki.viewmodel.MainViewModelFactory


class GameIdActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGameIdBinding
    private lateinit var viewModel : MainViewModel
    private  var gameLink : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameIdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gameId = intent.getIntExtra(GAME_ID,-1)
        if(gameId==-1){
            Toast.makeText(this,"Failed to fetch game id",Toast.LENGTH_SHORT)
            onBackPressedDispatcher.onBackPressed()
        }


        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository) // getting view model factory
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java) // getting view model


        binding.gameURL.setOnClickListener {
            if(gameLink == null){
                Toast.makeText(this,"No link availale ",Toast.LENGTH_SHORT)
            }else {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(gameLink))
                startActivity(intent)
            }
        }

        viewModel.getGameNode(gameId)
        viewModel.apiResponse.observe(this, Observer { response ->
            Log.d("Response",response.code().toString())
            if(response.isSuccessful){
                Log.d("Response",response.body().toString())
                binding.title.text = response.body()?.title
                if(response.body()?.thumbnail != null ){
                    Glide.with(this)
                        .load(response.body()?.thumbnail)
                        .into(binding.thumbnail)
                }

                response.body()?.screenshots?.forEach {
                    var imgview = ImageView(this)
                    imgview.layoutParams = binding.sampleScreenShot.layoutParams
                    Log.d("Response","I am here")
                    Glide.with(this)
                        .load(it.image.toString())
                        .into(imgview)
                    binding.screenShots.addView(imgview)

                }
                val body = response.body()
                binding.developerAns.text = if (body != null) body.developer else "Unknown"
                val body1 = response.body()
                binding.publisherAns.text = if (body1 != null) body1.publisher else "Unknown"
                val body2 = response.body()
                binding.releaseDateAns.text = if (body2 != null) body2.releaseDate else "Unknown"

                binding.description.text = response.body()?.shortDescription.toString()

                gameLink = response.body()?.gameUrl

                binding.genre.text = response.body()?.genre

                binding.platform.text = response.body()?.platform


            }else{
                Toast.makeText(this,"Failed to fetch game details",Toast.LENGTH_SHORT)
            }

        })


    }

}