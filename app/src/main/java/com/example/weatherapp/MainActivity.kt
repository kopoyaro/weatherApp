package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val CITY: String = "tokyo,jp"
    val API: String = "47b03d2131c1aef3bb392260c52d860b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WeatherTasks()
    }
    inner class WeatherTasks() {
         fun fetchData(vararg p0: String?): String? {
            var response:String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").toString()
            }
            catch (e:Exception){
                response = null
            }
            return response
        }
         fun toJson(result: String?) {
            try {
                val jsonObj =JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val weather = jsonObj.getJSONObject("weather")
                val updateAt:Long = jsonObj.getLong("dt")
                val updateAtText = "mis à jour le: "+SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.FRANCE).format(Date(updateAt*1000))
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min temp: "+main.getString("temp_min")+"°C"
                val tempMax = "Max temp: "+main.getString("temp_max")+"°C"
                val weatherDescription = weather.getString("description")
                val address = jsonObj.getString("name")+", "+sys.getString("country")

                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updatedAt).text = updateAtText
                findViewById<TextView>(R.id.status).text = weatherDescription
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
            }
            catch (e: Exception){

            }
        }




    }
}