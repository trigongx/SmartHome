package com.example.smart_home.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smart_home.core.network.RemoteDataSource
import com.example.smart_home.core.network.RetrofitClient
import com.example.smart_home.databinding.ActivityMainBinding
import com.example.smart_home.domain.repository.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object{
        private val retrofitClient = RetrofitClient().createApiService()
        private val remoteDataSource = RemoteDataSource(retrofitClient)
        val repository = Repository(remoteDataSource)
    }
}