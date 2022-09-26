package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btShow = findViewById<Button>(R.id.btShow)

        btShow.setOnClickListener {
            loadJoke()
        }
    }

    private fun loadJoke(){

        val tvJoke = findViewById<TextView>(R.id.tvJoke)
        //1. Creo una instancia de retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://icanhazdadjoke.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Log.e("RETROFIT","variable retrofit: ${retrofit}")


        //2. Creo una instancia de la interfaz
        var jokeService: JokeService
        jokeService = retrofit.create(JokeService::class.java)

        Log.e("SERVICE","variable service: ${jokeService}")

        //3. Creo una variable y a traves de la interfaz le asigno la funcion
        val request = jokeService.getJoke("json")

        Log.e("REQUEST","variable request: ${request}")

        //Implemento los metodos a traves de request
        request.enqueue(object :Callback<Joke>{
            //si funciona
            override fun onResponse(call: Call<Joke>,response: Response<Joke>){
                Log.e("RESPONSE","variable response: ${response}")
                if(response.isSuccessful){
                    tvJoke.text = response.body()!!.joke
                }
            }

            //si no funciona
            override fun onFailure(call: Call<Joke>, t:Throwable){
                TODO("Not yet implemented")
            }
        })
    }
}