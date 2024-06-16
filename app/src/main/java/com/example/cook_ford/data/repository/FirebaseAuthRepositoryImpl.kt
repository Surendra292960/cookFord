package com.example.cook_ford.data.repository

import android.app.Activity
import android.util.Log
import com.example.cook_ford.domain.repository.FirebaseAuthRepository
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.ResultState
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(private val firebaseAuth:FirebaseAuth) :
    FirebaseAuthRepository {

    private lateinit var omVerificationCode:String

    override fun createUserWithPhone(phone: String,activity:Activity): Flow<ResultState<String>> =  callbackFlow{
        trySend(ResultState.Loading(isLoading = true))

        val onVerificationCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                super.onCodeAutoRetrievalTimeOut(p0)
                Log.d("createUserWithPhone", "onCodeAutoRetrievalTimeOut: $p0")
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                Log.d("createUserWithPhone", "onVerificationCompleted: ${phoneAuthCredential.smsCode}")
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                trySend(ResultState.Failure(msg = exception))
                Log.d("createUserWithPhone", "onVerificationFailed: $exception")
            }

            override fun onCodeSent(verificationCode: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verificationCode, p1)
                trySend(ResultState.Success(otp = verificationCode, data = "OTP Sent Successfully"))
                omVerificationCode = verificationCode
                Log.d("createUserWithPhone", "onCodeSent: $p1  $verificationCode")
            }

        }

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber("+91$phone")
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(onVerificationCallback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        awaitClose{
            close()
        }
    }

    override fun signWithCredential(otp: String): Flow<ResultState<String>>  = callbackFlow{
       try {
           trySend(ResultState.Loading(isLoading = true))
           val credential = PhoneAuthProvider.getCredential(omVerificationCode,otp)
           firebaseAuth.signInWithCredential(credential)
               .addOnCompleteListener {
                   Log.d("TAG", "signWithCredential: ${it.isSuccessful}")
                   if(it.isSuccessful)
                       trySend(ResultState.Success(otp = otp, data = "otp verified"))
               }.addOnFailureListener {
                   trySend(ResultState.Failure(it))
               }
           awaitClose {
               close()
           }
       }catch (e:Exception){
           e.printStackTrace()
       }
    }
}