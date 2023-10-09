package com.zuzob00l.to_do.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zuzob00l.to_do.data.models.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase()
{
    abstract fun ToDoDao(): ToDoDao
}