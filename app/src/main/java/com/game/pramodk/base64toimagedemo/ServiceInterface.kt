package com.game.pramodk.base64toimagedemo

import org.json.JSONObject

/**
 * Created by siddheshn on 1/3/18.
 */
interface ServiceInterface {
    fun getImageBase64(path: String, params: JSONObject, completionHandler: (response: String?) -> Unit)
}