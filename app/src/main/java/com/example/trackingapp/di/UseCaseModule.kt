package com.example.trackingapp.di

import com.example.trackingapp.data.remote.repository.FirebaseAuthImpl
import com.example.trackingapp.domain.usecase.CurrentUserUseCase
import com.example.trackingapp.domain.usecase.SignInWithEmailAndPasswordUseCase
import com.example.trackingapp.domain.usecase.SignOutUseCase
import com.example.trackingapp.domain.usecase.SignUpWithEmailAndPasswordUseCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideSignInWithEmailAndPasswordUseCase(repository: FirebaseAuthImpl) : SignInWithEmailAndPasswordUseCase {
        return SignInWithEmailAndPasswordUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSignUpWithEmailAndPasswordUseCase(repository: FirebaseAuthImpl) : SignUpWithEmailAndPasswordUseCase {
        return SignUpWithEmailAndPasswordUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSignOutUseCase(repository: FirebaseAuthImpl) : SignOutUseCase {
        return SignOutUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideCurrentUserUseCase(repository: FirebaseAuthImpl) : CurrentUserUseCase {
        return CurrentUserUseCase(repository)
    }


}