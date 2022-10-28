package textjson.model

data class Data(val data:List<Text>)

data class Text(val boundingPoly:BoundingPoly, val description:String, val locale:String)
data class BoundingPoly(val vertices: List<Vertice>){
    fun getCenter():Vertice{
        val maxX = vertices.maxOf { it.x }
        val minX = vertices.minOf { it.x }
        val avgX = (maxX+minX)/2
        val maxY = vertices.maxOf { it.y }
        val minY= vertices.minOf { it.y }
        val avgY = (maxY+minY)/2
        return  Vertice(avgX, avgY)
    }


}
data class Vertice(val x: Int, val y: Int)

