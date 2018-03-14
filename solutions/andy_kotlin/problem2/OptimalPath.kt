package problem2

import util.accumulate

enum class Direction {
    RIGHT, DOWN, HOME
}

fun findOptimalPath(matrix: List<List<Int>>): Pair<Int, List<Int>> {

    val topline = matrix[0].drop(1).accumulate(Pair(matrix[0][0], Direction.HOME)) {
                                    previous, it -> Pair(previous.first + it, Direction.RIGHT) }

    val scores = matrix.drop(1).accumulate(topline){
        prevRow, row -> (1 until row.size)
                    .accumulate(Pair(prevRow[0].first + row[0], Direction.DOWN)){
                        prev, it -> allowedMoves(it, prev, row, prevRow).minBy{it.first}!!
                    }
    }

    val cost = scores.last().last().first
    return Pair(cost, scores.backtrack(matrix))
}

private fun allowedMoves(it: Int, previousElement: Pair<Int, Direction>, row: List<Int>, prevRow: List<Pair<Int, Direction>>) =
     listOf( Pair(prevRow[it].first + row[it], Direction.DOWN), Pair(previousElement.first + row[it], Direction.RIGHT))


fun List<List<Pair<Int,Direction>>>.backtrack(matrix: List<List<Int>>): List<Int> {
    var y = this.size - 1
    var x = this[0].size - 1
    val path = mutableListOf(matrix[y][x])

    while (this[y][x].second != Direction.HOME) {
        when (this[y][x].second){
            Direction.RIGHT -> x -= 1
            Direction.DOWN -> y -= 1
            Direction.HOME -> {}
        }
        path.add(matrix[y][x])
    }
    return path.reversed()
}
