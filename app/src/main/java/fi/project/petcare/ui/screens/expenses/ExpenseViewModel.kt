package fi.project.petcare.ui.screens.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.project.petcare.data.repository.ExpenseRepositoryImpl
import fi.project.petcare.domain.model.Expense
import fi.project.petcare.domain.model.ExpenseCategory
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class ExpenseViewModel : ViewModel() {

    private val repository = ExpenseRepositoryImpl()

    private val _state = MutableStateFlow(ExpenseContract.State())
    val state: StateFlow<ExpenseContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ExpenseContract.Effect>()
    val effect: SharedFlow<ExpenseContract.Effect> = _effect.asSharedFlow()

    fun handleIntent(intent: ExpenseContract.Intent) {
        when (intent) {
            is ExpenseContract.Intent.LoadExpenses -> loadExpenses(intent.petId)
            is ExpenseContract.Intent.AddExpense -> addExpense(intent.petId, intent.title, intent.amount, intent.category)
            is ExpenseContract.Intent.DeleteExpense -> deleteExpense(intent.expenseId)
        }
    }

    private fun loadExpenses(petId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.getExpenses(petId).collect { expenses ->
                val total = expenses.sumOf { it.amount }
                val breakdown = expenses.groupBy { it.category }
                    .mapValues { (_, list) -> list.sumOf { it.amount } }
                
                _state.update { 
                    it.copy(
                        isLoading = false,
                        expenses = expenses,
                        totalAmount = total,
                        categoryBreakdown = breakdown
                    ) 
                }
            }
        }
    }

    private fun addExpense(petId: String, title: String, amount: Double, category: ExpenseCategory) {
        viewModelScope.launch {
            val newExpense = Expense(
                id = UUID.randomUUID().toString(),
                petId = petId,
                title = title,
                amount = amount,
                date = Date(),
                category = category
            )
            repository.addExpense(newExpense)
            _effect.emit(ExpenseContract.Effect.ShowSnackbar("Expense added!"))
        }
    }

    private fun deleteExpense(expenseId: String) {
        viewModelScope.launch {
            repository.deleteExpense(expenseId)
            _effect.emit(ExpenseContract.Effect.ShowSnackbar("Expense deleted!"))
        }
    }
}
