package fi.project.petcare.domain.usecase

import fi.project.petcare.domain.model.Skill
import fi.project.petcare.domain.repository.SkillRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Use case to get skills for a pet, sorted by progress.
 * Demonstrates Single Responsibility Principle.
 */
class GetSkillsUseCase(private val repository: SkillRepository) {
    operator fun invoke(petId: String): Flow<List<Skill>> {
        return repository.getSkills(petId).map { skills ->
            skills.sortedByDescending { it.progress }
        }
    }
}
