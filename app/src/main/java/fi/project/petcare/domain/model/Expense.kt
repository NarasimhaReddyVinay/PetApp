package fi.project.petcare.domain.model

import java.util.Date

data class Expense(
    val id: String,
    val petId: String,
    val title: String,
    val amount: Double,
    val date: Date,
    val category: ExpenseCategory,
    val notes: String? = null
)

enum class ExpenseCategory {
    FOOD, VET, TOYS, GROOMING, INSURANCE, OTHER
}
