package fi.project.petcare.domain.model

data class Skill(
    val id: String,
    val petId: String,
    val title: String,
    val description: String,
    val difficulty: SkillDifficulty,
    val progress: Int, // 0 to 100
    val category: SkillCategory,
    val isCompleted: Boolean = false
)

enum class SkillDifficulty {
    EASY, MEDIUM, HARD
}

enum class SkillCategory {
    OBEDIENCE, TRICKS, AGILITY, HEALTH, SOCIAL
}
