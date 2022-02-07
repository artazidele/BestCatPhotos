package com.example.bestcatphotos.model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.snapshots.Snapshot
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.example.bestcatphotos.viewmodel.MyVoteViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import okhttp3.internal.wait

class FirebaseMessage {
//    val db = FirebaseFirestore.getInstance()
//
////    suspend fun getAllMessages(): Task<QuerySnapshot> {
////        return db.collection("Message").get()//.wait()
////    }
//
//    suspend fun getQuerySnap(): Task<QuerySnapshot> {
//        return db.collection("Message").get()
//    }
//    suspend fun getAllMessages(): ArrayList<MessageF> {
//
//        var itemList: ArrayList<MessageF> = ArrayList()
//        db.collection("Message")
//            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    val message = document.toObject<MessageF>()
//                    itemList.add(message)
//                }
//            }
//            .wait()
////        Log.d(TAG, "TRY TRY TRY TRY TRY")
////        val itemListToReturn: List<MessageF> = itemList
//        for (item in itemList) {
//            Log.d(TAG, item.message)
//        }
//        return itemList//ToReturn
//    }
//
//    suspend fun updateMessages() {
//        var itemList: ArrayList<MessageF> = ArrayList()
//        db.collection("Message")
//            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    val message = document.toObject<MessageF>()
//                    itemList.add(message)
//                }
//                val itemListToReturn: List<MessageF> = itemList
//                MyVoteViewModel().updateMessagesTrue(itemListToReturn)
//            }
//            .addOnFailureListener {
//                MyVoteViewModel().notUpdateMessages()
//            }
//    }
//

}