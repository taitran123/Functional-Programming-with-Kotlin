package chapter14.tools.models
import kotlinx.serialization.Serializable



@Serializable
data class ScoredShow(
    val score: Double,
    val show: Show
)

@Serializable
data class Show(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val url: String,
    val image: ShowImage?,
    val summary: String?,
    val language: String
)

@Serializable
data class ShowImage(
    val original: String,
    val medium: String
)