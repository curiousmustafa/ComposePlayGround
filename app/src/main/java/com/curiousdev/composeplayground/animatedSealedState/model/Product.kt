package com.curiousdev.composeplayground.animatedSealedState.model

import com.curiousdev.composeplayground.R

data class Product(
    val name: String = listOf(
        "Iphone X",
        "Iphone 12",
        "Iphone 7+",
        "Iphone 6+",
        "Samsung A32",
        "Samsung S20",
        "Samsung A21",
        "Xiaomi Z2",
        "Samsung Tab3",
        "HUAWEI Y7 2019",
        "HUAWEI Y5 2019",
        "HUAWEI Y6 2019",
        "HUAWEI Y9 2019 ",
        "HUAWEI Y7 2020",
        "HUAWEI Y5 2020",
        "HUAWEI Y6 2020",
        "HUAWEI Y9 2020 ",
    ).shuffled().shuffled().first(),
    val price: Double = listOf(
        400.99,
        250.99,
        100.0,
        299.99
    ).shuffled().first(),
    val description: String = "$name is a smart phone , coming with display 7'15 screen and RAM of 4GB and also with 64 ROM",
    val image: Int = R.drawable.phone,
    val rate: Double = listOf(
        4.6,
        3.9,
        4.4,
        3.5,
        4.1,
        2.4
    ).shuffled().first(),
)