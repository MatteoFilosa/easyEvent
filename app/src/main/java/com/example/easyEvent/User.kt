package com.example.easyEvent

data class User(val name: String, val surname: String, val age: Int, val gender: String) {
    constructor() : this("", "", 0, "")
}
