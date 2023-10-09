package com.zuzob00l.to_do.di

import android.content.Context
import androidx.room.Room
import com.zuzob00l.to_do.data.ToDoDatabase
import com.zuzob00l.to_do.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//provideRoomDatabase:
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule
{
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ToDoDatabase::class.java,
        DATABASE_NAME)
        .build()
}

//provide dao:
@Provides
@Singleton
fun provideDao(database: ToDoDatabase) = database.ToDoDao()