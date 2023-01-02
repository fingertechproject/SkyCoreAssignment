package com.example.skycoreassignment

data class Businesse(
    val alias: String,
    val categories: List<Category>,
    val coordinates: Coordinates,
    val display_phone: String,
    val distance: Double,
    val id: String,
    val image_url: String,
    val is_closed: Boolean,
    val location: Location,
    val name: String,
    val phone: String,
    val price: String,
    val rating: Int,
    val review_count: Int,
    val transactions: List<String>,
    val url: String
)