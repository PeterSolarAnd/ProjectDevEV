package com.example.call_mapbox_api.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.call_mapbox_api.data.remote.AddressInfo
import com.example.call_mapbox_api.data.remote.Connection
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "evPointsDb")
data class EvPointDetails(
    @PrimaryKey(autoGenerate = false)
    val ID: Int?,

    @ColumnInfo(name = "data_provider_id")
    val DataProviderID: Int?,

    @ColumnInfo(name = "data_quality_level")
    val DataQualityLevel: Int?,

    @ColumnInfo(name = "data_created")
    val DateCreated: String?,

    @ColumnInfo(name = "data_last_status_update")
    val DateLastStatusUpdate: String?,

    @ColumnInfo(name = "data_last_verified")
    val DateLastVerified: String?,

    @ColumnInfo(name = "number_of_points")
    val NumberOfPoints: Int?,

    @ColumnInfo(name = "is_recently_verified")
    val IsRecentlyVerified: Boolean?,

    @ColumnInfo(name = "operator_id")
    val OperatorID: Int?,

    @ColumnInfo(name = "status_type_id")
    val StatusTypeID: Int?,

    @ColumnInfo(name = "submission_status_type_id")
    val SubmissionStatusTypeID: Int?,

    @ColumnInfo(name = "uuid")
    val UUID: String?,

    @ColumnInfo(name = "usage_cost")
    val UsageCost: String?,

    @ColumnInfo(name = "usage_type_id")
    val UsageTypeID: Int?,

     @ColumnInfo(name = "connection")
     val Connection: List<Connection>?,

    @ColumnInfo(name = "address_info")
    val AddressInfo: AddressInfo?
)