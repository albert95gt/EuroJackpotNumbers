package com.example.eurojackpotnumbergenerator.utils

fun generateMainNumbers(): List<Int>{
    return (1..50).shuffled().take(5).sorted()
}

fun generateEuroNumbers(): List<Int>{
    return (1..12).shuffled().take(2).sorted()
}

fun generateSportkaNumbers(): List<Int>{
    return (1 .. 49).shuffled().take(6).sorted()
}