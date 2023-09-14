package com.example.imagecouroutinemvvmrecycler.Coroutine

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.imagecouroutinemvvmrecycler.R
import kotlinx.coroutines.*

class Coroutine : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coroutine)

        GlobalScope.launch(Dispatchers.Main) {
            execute()
        }


        Log.d(ContentValues.TAG, "${Thread.currentThread().name}")
    }

    private suspend fun execute(){


        val parentJob =  CoroutineScope(Dispatchers.Main).launch{
            for(i in 1..100){
                longRunning()
                Log.d(ContentValues.TAG, i.toString())
            }
        }

        delay(100)
        Log.d(ContentValues.TAG, "cancel job")
        parentJob.cancel()
        parentJob.join()
        Log.d(ContentValues.TAG, "job complete")

    }

    private fun longRunning(){
        for (i in 1..10000000000L){

        }
    }


}