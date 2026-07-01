package fi.project.petcare.data.repository

import fi.project.petcare.domain.model.Skill
import fi.project.petcare.domain.model.SkillCategory
import fi.project.petcare.domain.model.SkillDifficulty
import fi.project.petcare.domain.repository.SkillRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class SkillRepositoryImpl : SkillRepository {

    private val _skills = MutableStateFlow<List<Skill>>(
        listOf(
            Skill(UUID.randomUUID().toString(), "pet-1", "Sit", "Basic sit command", SkillDifficulty.EASY, 100, SkillCategory.OBEDIENCE, true),
            Skill(UUID.randomUUID().toString(), "pet-1", "Stay", "Stay in place", SkillDifficulty.MEDIUM, 45, SkillCategory.OBEDIENCE),
            Skill(UUID.randomUUID().toString(), "pet-1", "Roll Over", "Complete roll over", SkillDifficulty.HARD, 10, SkillCategory.TRICKS)
        )
    )

    override fun getSkills(petId: String): Flow<List<Skill>> {
        return _skills.asStateFlow()
    }

    override suspend fun addSkill(skill: Skill) {
        _skills.update { current -> current + skill }
    }

    override suspend fun updateSkillProgress(skillId: String, progress: Int) {
        _skills.update { current ->
            current.map {
                if (it.id == skillId) it.copy(progress = progress, isCompleted = progress >= 100)
                else it
            }
        }
    }

    override suspend fun deleteSkill(skillId: String) {
        _skills.update { current -> current.filter { it.id != skillId } }
    }
}
