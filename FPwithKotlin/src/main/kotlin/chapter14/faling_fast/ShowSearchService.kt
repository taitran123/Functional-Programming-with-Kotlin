package chapter14.faling_fast

import chapter14.tools.TvShowFetcher
import chapter14.tools.TvShowParser
import chapter14.tools.flatMap
import chapter14.tools.models.ScoredShow
import chapter8.pipe
import chapter9.*
import kotlinx.serialization.SerializationException
import java.io.IOException

fun fetchTvShowOptional(query:String) :Either<IOException, String> = try{
    Either.right(TvShowFetcher.fetch(query))
} catch (ioe: IOException){
    Either.left(ioe)
}

fun parseTvShowString(json:String):Either<SerializationException,List<ScoredShow>> = try {
    Either.right(TvShowParser.parse(json))
}catch (e:SerializationException){
    Either.left(e)
}

fun fetchAndParseTvShow(query: String) = fetchTvShowOptional(query).flatMap(::parseTvShowString)

fun main(){
    fetchAndParseTvShow("Big Bang").leftMap { println("Error : $it") }.rightMap { println("Result : $it") }
}