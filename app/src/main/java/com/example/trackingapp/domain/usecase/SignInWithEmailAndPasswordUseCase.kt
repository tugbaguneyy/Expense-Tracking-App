package com.example.trackingapp.domain.usecase

import com.example.trackingapp.data.remote.repository.FirebaseAuthImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val repo: FirebaseAuthImpl
) {

    operator fun invoke(email: String, password: String) : Flow<Boolean> = flow {
        repo.signInWithEmailAndPassword(email, password).collect {
            emit(it)
        }
    }
}