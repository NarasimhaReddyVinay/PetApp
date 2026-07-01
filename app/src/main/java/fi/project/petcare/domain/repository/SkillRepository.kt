package fi.project.petcare.domain.repository

import fi.project.petcare.domain.model.Skill
import kotlinx.coroutines.flow.Flow

interface SkillRepository {
    fun getSkills(petId: String): Flow<List<Skill>>
    suspend fun addSkill(skill: Skill)
    suspend fun updateSkillProgress(skillId: String, progress: Int)
    suspend fun deleteSkill(skillId: String)
}
