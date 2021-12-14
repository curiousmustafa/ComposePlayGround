package com.curiousdev.composeplayground.animatedSealedState.repo

import com.curiousdev.composeplayground.animatedSealedState.model.Product

object DataRepo {
    val products = mutableListOf<Product>().apply {
        for (i in 0..100) add(Product())
    }
}