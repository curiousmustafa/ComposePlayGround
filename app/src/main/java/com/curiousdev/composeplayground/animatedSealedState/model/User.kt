package com.curiousdev.composeplayground.animatedSealedState.model

data class User(
    val name: String,
    val gender: Gender = Gender.Male,
    val email: String,
    val profile: Int
){
    sealed class Gender(){
        object Male: Gender()
        object Female: Gender()
    }
}