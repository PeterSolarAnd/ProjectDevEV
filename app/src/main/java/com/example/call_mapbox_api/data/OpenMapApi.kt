package com.example.call_mapbox_api.data

import com.example.call_mapbox_api.data.remote.EvPointsBrakeItem
import com.example.call_mapbox_api.model.EvPointDetails
import retrofit2.http.GET


interface OpenMapApi {
    @GET("testmock")
    suspend fun getMaxResults(): List<EvPointDetails>

    //@GET("v3/poi/?key=22dcf268-f3e1-4cf4-a152-111d68e9502d/output=json&maxresults=10&compact=true&verbose=false&longitude=-1.360406976465403&latitude=51.954015044041914")

//    @GET("v3/poi/?key=22dcf268-f3e1-4cf4-a152-111d68e9502d/output=json&compact=true&verbose=false")
//    suspend fun getDistance(@Query("distance") distance: Number,
//                            @Query("latitude") latitude: Double,
//                            @Query("longitude") longitude: Double
//    ): List<EvPointsBrakeItemX>>>

}