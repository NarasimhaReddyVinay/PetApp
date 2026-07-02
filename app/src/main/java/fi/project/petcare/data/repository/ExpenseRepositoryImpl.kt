package fi.project.petcare.data.repository

import fi.project.petcare.domain.model.Expense
import fi.project.petcare.domain.model.ExpenseCategory
import fi.project.petcare.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*

class ExpenseRepositoryImpl : ExpenseRepository {

    private val _expenses = MutableStateFlow<List<Expense>>(
        listOf(
            Expense(UUID.randomUUID().toString(), "pet-1", "Monthly Food", 45.0, Date(), ExpenseCategory.FOOD),
            Expense(UUID.randomUUID().toString(), "pet-1", "Vaccination", 120.0, Date(), ExpenseCategory.VET),
            Expense(UUID.randomUUID().toString(), "pet-1", "New Leash", 25.0, Date(), ExpenseCategory.TOYS)
        )
    )

    override fun getExpenses(petId: String): Flow<List<Expense>> {
        return _expenses.asStateFlow()
    }

    override suspend fun addExpense(expense: Expense) {
        _expenses.update { it + expense }
    }

    override suspend fun deleteExpense(expenseId: String) {
        _expenses.update { current -> current.filter { it.id != expenseId } }
    }
}
