package com.example.trackingapp.domain.usecase

import com.example.trackingapp.data.repository.FirebaseAuthImpl
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val repo : FirebaseAuthImpl
) {
    suspend operator fun invoke(){
        repo.signOut()
    }
}