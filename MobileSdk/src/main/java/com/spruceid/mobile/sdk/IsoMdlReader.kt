package com.spruceid.mobile.sdk

import android.bluetooth.BluetoothManager
import android.content.Context
import android.util.Log
import com.spruceid.mobile.sdk.rs.MDocItem
import com.spruceid.mobile.sdk.rs.MdlReaderResponseData
import com.spruceid.mobile.sdk.rs.MdlReaderResponseException
import com.spruceid.mobile.sdk.rs.MdlSessionManager
import com.spruceid.mobile.sdk.rs.VerificationResponse
import com.spruceid.mobile.sdk.rs.establishSession
import java.util.UUID

class IsoMdlReader(
    val callback: BLESessionStateDelegate,
    uri: String,
    docType: String,
    format: String,
    requestedItems: Map<String, Map<String, Boolean>>,
    trustAnchorRegistry: List<String>?,
    platformBluetooth: BluetoothManager,
    context: Context
) {
    private lateinit var session: MdlSessionManager
    private lateinit var bleManager: Transport

    init {
        try {
            val sessionData = establishSession(uri, docType, format, requestedItems, trustAnchorRegistry)

            session = sessionData.state
            bleManager = Transport(platformBluetooth)
            bleManager.initialize(
                "Reader",
                UUID.fromString(sessionData.uuid),
                "BLE",
                "Central",
                sessionData.bleIdent,
                null,
                context,
                callback,
                sessionData.request
            )

        } catch (e: Error) {
            Log.e("BleSessionManager.constructor", e.toString())
        }
    }

    suspend fun handleResponse(response: ByteArray): Map<String, Map<String, MDocItem>> {
        try {
            val responseData = com.spruceid.mobile.sdk.rs.handleResponse(session, response, emptyMap(), true)
            if(responseData.responses.size > 0) {
                return responseData.responses.get(0).verifiedResponse
            }

            return emptyMap()
        } catch (e: MdlReaderResponseException) {
            throw e
        }
    }

    suspend fun handleMdlReaderResponseData(response: ByteArray, dids: Map<String, String>, resolveDids: Boolean): VerificationResponse {
        try {
            return com.spruceid.mobile.sdk.rs.handleResponse(session, response, dids, resolveDids)
        } catch (e: MdlReaderResponseException) {
            throw e
        }
    }
}