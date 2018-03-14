package problem3

operator fun Pair<Int, List<Int>>.plus( x: Pair<Int,Int>) : Pair<Int,List<Int>>
        = Pair(this.first + x.first, this.second + x.second)

/**
 * Use Dynamic Programming to optimally place billboards.
 */
fun placeBillboards(distance: Int, separation: Int, billboards: Map<Int, Int>): Pair<Int, List<Int>> {

    var placements = mutableListOf(0 to listOf<Int>())
    (1..distance).forEach {
        val possibles = mutableListOf(placements.last())
        if (billboards.containsKey(it)){
            val prevPlacement = placements.getOrElse(it-(separation +1), {0 to listOf()})
            possibles.add( prevPlacement +  Pair(billboards[it]!!, it))
        }
        placements.add( possibles.maxBy { it.first }!!)
    }
    return placements.last()
}
