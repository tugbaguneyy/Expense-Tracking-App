package com.example.trackingapp.di

import com.example.trackingapp.data.remote.repository.FirebaseAuthImpl
import com.google.firebase.auth.FirebaseAuth

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthImpl(auth: FirebaseAuth) : FirebaseAuthImpl {
        return FirebaseAuthImpl(auth)
    }

}