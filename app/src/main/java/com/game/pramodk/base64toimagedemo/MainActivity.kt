package com.game.pramodk.base64toimagedemo

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*


class MainActivity : AppCompatActivity() {

    var encodedBase64List =  ArrayList<String>()
    val responseList = ArrayList<Map<String, String>>()
    lateinit var mapObj: HashMap<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getApiCall()
    }

    private fun getApiCall() {
        val service = ServiceVolley()
        val apiController = APIController(service)

        val path = "readBase64Image.php"
        val params = JSONObject()
        Log.e("api_call", path)
        apiController.getImageBase64(path, params) { response ->
            if (null != response) {
                Log.e("api_response", response)
                // Parse the result
                var jsonObject = JSONObject(response)
                var jsonArray = jsonObject.getJSONArray("data")
                mapObj = HashMap<String, String>()
                for (i in 0..jsonArray.length() - 1) {
                    var jsonObject = jsonArray.getJSONObject(i)

                    mapObj.put(jsonObject.getString("id"), jsonObject.getString("imageBase64"))
//                    responseList.add(mapObj)
                }
                showResponseList()
            }
        }
    }

    fun showResponseList() {
        for (obj in mapObj) {
            Log.e("obj", obj.toString())
            encodedBase64List.add(obj.value)
//            val decodedBase64 = Base64.decode(obj.value, Base64.NO_WRAP)
//            val decodedBitmap = BitmapFactory.decodeByteArray(decodedBase64, 0, decodedBase64.size)

//            ivDisplayImage.setImageBitmap(decodedBitmap)
        }
        Log.e("List size", encodedBase64List.size.toString())
        vpImageSlider.adapter = CustomPagerAdapter(this, encodedBase64List)
    }

    /*fun base64Decode(text: String): ByteArray {
        return Base64.decode(text, Base64.NO_WRAP)
    }

    fun base64Encode(data: ByteArray): String {
        return Base64.encodeToString(data, Base64.NO_WRAP)
    }*/
}
