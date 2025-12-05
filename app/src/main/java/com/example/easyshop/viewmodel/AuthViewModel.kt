package com.example.easyshop.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easyshop.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore


class AuthViewModel : ViewModel(){

    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    fun login(email: String,password: String,onResult :(Boolean, String?) -> Unit){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){onResult(true,null)}

            else{
                    onResult(false,task.exception?.localizedMessage)
            }

            }

    }


    fun signup(email : String,name : String,password : String,onResult : (Boolean, String?) -> Unit){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                task ->
                if (task.isSuccessful){

                    task.result?.user?.uid?.let {
                        userId ->
                        val userModel = UserModel(name,email,userId)
                        firestore.collection("users").document(userId)
                            .set(userModel)
                            .addOnCompleteListener {
                                dbTask ->
                                if (dbTask.isSuccessful){
                                    onResult(true,null)
                                }
                                else{
                                    onResult(false,dbTask.exception?.localizedMessage ?: "Something went wrong")
                                }
                            }
                    }
                } else{

                    onResult(false,task.exception?.localizedMessage)
                }
                }

            }


    }





