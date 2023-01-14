package dev.vladimir.mobinttestproject.domain.models

data class Company(
    val id: String,
    val companyName: String,
    val logoUrl: String,
    val backgroundColor: Int,
    val mainColor: Int,
    val cardBackgroundColor: Int,
    val textColor: Int,
    val highlightTextColor: Int,
    val accentColor: Int,
    val mark: Int,
    val cashback: Int,
    val levelName: String
)