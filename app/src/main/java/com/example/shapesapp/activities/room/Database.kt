//package com.example.shapesapp.activities.room
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [VideoListApiEntity::class], version = 1)
//abstract class Database : RoomDatabase() {
//    abstract fun videodao(): RoomDao
//
//    companion object {
//        private var INSTANCE: Database? = null
//        fun getDatabase(context: Context): Database {
//            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(
//                    context,
//                    Database::class.java,
//                    "shapesDB"
//                ).build()
//            }
//            return INSTANCE!!
//        }
//    }
//}