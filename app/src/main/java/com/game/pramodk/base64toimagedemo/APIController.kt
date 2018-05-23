package com.game.pramodk.base64toimagedemo

import org.json.JSONObject

/**
 * Created by siddheshn on 1/3/18.
 */
class APIController constructor(serviceInjection: ServiceInterface): ServiceInterface {
    private val service: ServiceInterface = serviceInjection

    override fun getImageBase64(path: String, params: JSONObject, completionHandler: (response: String?) -> Unit) {
        service.getImageBase64(path, params, completionHandler)
    }
}