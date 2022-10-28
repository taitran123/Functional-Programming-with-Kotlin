package textjson

import com.google.gson.Gson
import com.google.gson.JsonArray
import netscape.javascript.JSObject
import textjson.model.Data
import textjson.model.Text
import textjson.model.Vertice
import java.io.BufferedReader
import java.io.File


val fileName="/home/thtai/IdeaProjects/Functional-Programming-with-Kotlin/FPwithKotlin/src/main/kotlin/textjson/sample.json"
val mark1="ミス"
val spacingBottom = 20

fun main() {
    val bufferedReader: BufferedReader = File(fileName).bufferedReader()
    val inputString = bufferedReader.use { it.readText() }
    var gson= Gson()
    var json = gson.fromJson(inputString, Data::class.java)
    var belowY = json.data.filter { it.description==mark1 }.map { it.boundingPoly.vertices.map {it2-> it2.y }.max() }.max() + spacingBottom
    var selections = json.data.filter { it.boundingPoly.vertices.map {it2-> it2.y }.min() > belowY }
    var column3 = selections.filter { it.description.contains(":") }
    val remains = selections.minus(column3)

    column3.forEach{it->
        val center = it.boundingPoly.getCenter()
        val result = remains.filter { it.boundingPoly.vertices.maxOf {it3-> it3.y }>center.y && it.boundingPoly.vertices.minOf {it2-> it2.y }<center.y}.sortedBy { it.boundingPoly.vertices.minOf {it4-> it4.x } }
        println(result.map { it.description }+it.description)
    }
}