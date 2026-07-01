package fi.project.petcare.ui.screens.skills

import fi.project.petcare.domain.model.Skill

/**
 * MVI Contract for Skill feature.
 * UDF (Unidirectional Data Flow) for better state management.
 */
class SkillContract {

    data class State(
        val skills: List<Skill> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )

    sealed class Intent {
        data class LoadSkills(val petId: String) : Intent()
        data class UpdateProgress(val skillId: String, val progress: Int) : Intent()
        data class AddSkill(val petId: String, val title: String, val description: String) : Intent()
    }

    sealed class Effect {
        data class ShowSnackbar(val message: String) : Effect()
    }
}
