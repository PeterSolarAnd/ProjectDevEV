package com.example.call_mapbox_api.util


import com.example.call_mapbox_api.model.EvPointDetails
import com.example.call_mapbox_api.remote.ConnectionX
import java.io.Serializable


data class ItemDataConverter(
    val AddressLine1: String?,
    val AddressLine2: String?,
    val Latitude: Double?,
    val Longitude: Double?,
    val Postcode: String?,
    val Title: String?,
    val Town: String?,
    val UsageCost: String?,
    val NumberOfPoints: Int?,
    val DateLastStatusUpdate: String?,
    val Connection: List<ConnectionX>?
): Serializable

fun dataConverter(address: EvPointDetails): ItemDataConverter {
    return ItemDataConverter(
        address.AddressInfo?.AddressLine1,
        address.AddressInfo?.AddressLine2,
        address.AddressInfo?.Longitude,
        address.AddressInfo?.Latitude,
        address.AddressInfo?.Title,
        address.AddressInfo?.Postcode,
        address.AddressInfo?.Town,
        address.UsageCost,
        address.NumberOfPoints,
        address.DateLastStatusUpdate,
        address.Connection
    )
}




