package com.example.wallpaperapi

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaperapi.Adapter.WallpaperAdapter
import com.example.wallpaperapi.Api.ApiClient
import com.example.wallpaperapi.Api.ApiInterface
import com.example.wallpaperapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    var data = ArrayList<PhotosItem>()
    lateinit var search: String
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: WallpaperAdapter
    var page = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()


    }

    fun initView() {
        binding.imgBtn.setOnClickListener {
            search = binding.edtSearch.text.toString()

            loadWallpaper()
        }

        binding.nestedScr.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->


            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                page++
                binding.progress.setVisibility(View.VISIBLE)
                loadWallpaper()
            }
        })
    }

    fun loadWallpaper() {
        var apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        apiInterface.getData(
            search,
            page,
            "AiE8mEHipmRX28hFBB1WfEyGAsQiQxNTovdKHZCVEf7pmeZSzHsq4PvM"
        ).enqueue(object : Callback<WallpaperModel> {
            override fun onResponse(
                call: Call<WallpaperModel>,
                response: Response<WallpaperModel>
            ) {
//                   data = response.body() as ArrayList<Src>
                data.addAll(response.body()?.photos as ArrayList<PhotosItem>)
                adapter = WallpaperAdapter(data)
//                    Log.e(TAG, "onResponse: ====" + data )

                binding.recycler.layoutManager = GridLayoutManager(this@MainActivity, 2)
                binding.recycler.adapter = adapter

            }

            override fun onFailure(call: Call<WallpaperModel>, t: Throwable) {

            }

        })
    }
}