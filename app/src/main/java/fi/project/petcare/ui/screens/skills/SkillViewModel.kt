package fi.project.petcare.ui.screens.skills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.project.petcare.data.repository.SkillRepositoryImpl
import fi.project.petcare.domain.model.Skill
import fi.project.petcare.domain.model.SkillCategory
import fi.project.petcare.domain.model.SkillDifficulty
import fi.project.petcare.domain.usecase.GetSkillsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class SkillViewModel : ViewModel() {

    // Ideally, these would be injected via Hilt
    private val repository = SkillRepositoryImpl()
    private val getSkillsUseCase = GetSkillsUseCase(repository)

    private val _state = MutableStateFlow(SkillContract.State())
    val state: StateFlow<SkillContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SkillContract.Effect>()
    val effect: SharedFlow<SkillContract.Effect> = _effect.asSharedFlow()

    fun handleIntent(intent: SkillContract.Intent) {
        when (intent) {
            is SkillContract.Intent.LoadSkills -> loadSkills(intent.petId)
            is SkillContract.Intent.UpdateProgress -> updateProgress(intent.skillId, intent.progress)
            is SkillContract.Intent.AddSkill -> addSkill(intent.petId, intent.title, intent.description)
        }
    }

    private fun loadSkills(petId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getSkillsUseCase(petId)
                .catch { e ->
                    _state.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { skills ->
                    _state.update { it.copy(isLoading = false, skills = skills) }
                }
        }
    }

    private fun updateProgress(skillId: String, progress: Int) {
        viewModelScope.launch {
            repository.updateSkillProgress(skillId, progress)
            _effect.emit(SkillContract.Effect.ShowSnackbar("Progress updated!"))
        }
    }

    private fun addSkill(petId: String, title: String, description: String) {
        viewModelScope.launch {
            val newSkill = Skill(
                id = UUID.randomUUID().toString(),
                petId = petId,
                title = title,
                description = description,
                difficulty = SkillDifficulty.EASY,
                progress = 0,
                category = SkillCategory.TRICKS
            )
            repository.addSkill(newSkill)
            _effect.emit(SkillContract.Effect.ShowSnackbar("New skill added!"))
        }
    }
}
