package com.ult3mate.photoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.ult3mate.photoapp.adapter.CustomListAdapter
import com.ult3mate.photoapp.databinding.ActivityMainBinding
import com.ult3mate.photoapp.modes.JsonResult
import com.ult3mate.photoapp.services.APIClient
import com.ult3mate.photoapp.services.APIServies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var customAdapter: CustomListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        customAdapter = CustomListAdapter(null)

        setupWidget()
        feedNetwork()

    }

    private fun feedNetwork() {
        binding.swipeRefresh.isRefreshing = true

        APIClient.getClient().create(APIServies::class.java).getEvents().let { call ->
            Log.d("net_work", call.request().url().toString())
            call.enqueue(object : Callback<List<JsonResult>> {
                override fun onFailure(call: Call<List<JsonResult>>, t: Throwable) {
                    showToast(t.message.toString())
                    binding.swipeRefresh.isRefreshing = false
                }

                override fun onResponse(
                    call: Call<List<JsonResult>>,
                    response: Response<List<JsonResult>>
                ) {
                    if (response.isSuccessful) {
                        binding.photoRecyclerview.adapter = CustomListAdapter(response.body())
                    } else {
                        showToast(response.message())
                    }
                    binding.swipeRefresh.isRefreshing = false
                }
            })
        }
    }

    private fun setupWidget() {
        setContentView(binding.root)
        binding.photoRecyclerview.apply {
            adapter = customAdapter
            layoutManager = GridLayoutManager(context, 2)
        }.also {
            it.addItemDecoration(GridSpacingItemDecoration(2, 20, true))
            it.setHasFixedSize(true)
        }
        binding.swipeRefresh.setOnRefreshListener {
            feedNetwork()
        }
    }

}