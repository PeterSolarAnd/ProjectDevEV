package com.example.call_mapbox_api.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.call_mapbox_api.data.remote.AddressInfo
import com.example.call_mapbox_api.data.remote.Connection
import com.example.call_mapbox_api.data.remote.EvPointsBrakeItem
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

// This class has not been used. Room using EvPointsBreakItems

@Serializable
data class EvPointDetails(
    val ID: Int?,
    val DateLastVerified: String,
    val DataProviderID: Int?,
    val DataQualityLevel: Int?,
    val DateCreated: String?,
    val DateLastStatusUpdate: String?,
    val NumberOfPoints: Int?,
    val IsRecentlyVerified: Boolean?,
    val OperatorID: Int?,
    val StatusTypeID: Int?,
    val SubmissionStatusTypeID: Int?,
    val UUID: String?,
    val UsageCost: String?,
    val UsageTypeID: Int?,
    val AddressInfo: AddressInfo,
    val Connection: List<Connection>?
)
