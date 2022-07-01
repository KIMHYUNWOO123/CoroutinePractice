package com.example.coroutine_practice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MainActivity : AppCompatActivity() {
    val TAG = "Coroutine"
    private var job = Job()
    private var myScope = CoroutineScope(Dispatchers.IO + job)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myScope.launch {
            Log.d(TAG, "onCreate: first ")
            myFlow().collect { value -> Log.d(TAG, "onCreate: $value") }
        }
    }
    fun myFlow() : Flow<Int> = flow {
        for(i in 1..10){
            delay(1000)
            emit(i)
        }
    }

    override fun onDestroy() {
        myScope.cancel()
        super.onDestroy()
    }
}