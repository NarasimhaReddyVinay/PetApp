package fi.project.petcare.ui.screens.expenses

import fi.project.petcare.domain.model.Expense
import fi.project.petcare.domain.model.ExpenseCategory

class ExpenseContract {

    data class State(
        val expenses: List<Expense> = emptyList(),
        val isLoading: Boolean = false,
        val totalAmount: Double = 0.0,
        val categoryBreakdown: Map<ExpenseCategory, Double> = emptyMap()
    )

    sealed class Intent {
        data class LoadExpenses(val petId: String) : Intent()
        data class AddExpense(val petId: String, val title: String, val amount: Double, val category: ExpenseCategory) : Intent()
        data class DeleteExpense(val expenseId: String) : Intent()
    }

    sealed class Effect {
        data class ShowSnackbar(val message: String) : Effect()
    }
}
