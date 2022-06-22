

package chapter14.tools
import chapter14.tools.models.ScoredShow
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

object TvShowParser {

  fun parse(json: String): List<ScoredShow> = Json {
    ignoreUnknownKeys = true
  }.decodeFromString<List<ScoredShow>>(ListSerializer(ScoredShow.serializer()), json)
}