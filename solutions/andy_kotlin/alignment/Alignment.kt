package alignment

import util.accumulate

enum class Direction {
    HOME, RIGHT, DOWN, DIAGONAL
}

typealias Score = Pair<Int, Direction>

fun align(seq1: String, seq2: String): Triple<Int, String, String> {

    fun cost(c1: Char, c2: Char): Int = if (c1 == c2) 0 else 1

    fun allowedMoves(x: Int, y: Int, previous: Score, previousRow: List<Score>): List<Score> {
        return listOf(Pair(previous.first + 1, Direction.RIGHT),
                Pair(previousRow[x + 1].first + 1, Direction.DOWN),
                Pair(previousRow[x].first + cost(seq1[x], seq2[y]), Direction.DIAGONAL)
        )
    }

    val firstRow = (0 until seq1.length).accumulate(Pair(0, Direction.HOME)) { prev, it -> Pair(it+1, Direction.RIGHT) }

    val scores = (0 until seq2.length).accumulate(firstRow) { previousRow, rowId ->
        (0 until seq1.length).accumulate(Pair(previousRow[0].first + 1, Direction.DOWN)) { prev, it ->
            allowedMoves(it, rowId, prev, previousRow).minBy { it.first }!!
        }
    }

    return scores.backtrack(seq1, seq2)
}

private fun List<List<Score>>.cost() = this.last().last().first

private fun List<List<Score>>.backtrack(seq1: String, seq2: String): Triple<Int, String, String> {

    class Step(val dx:Int, val dy: Int, val c1: Char, val c2: Char)
    tailrec fun backtrackInner(seq1: String, seq2: String, x: Int, y: Int, align1: List<Char>, align2: List<Char>) : Pair<String, String>{
        val step = when (this[y][x].second){
            Direction.DIAGONAL -> Step(-1, -1, seq1[x - 1], seq2[y-1])
            Direction.RIGHT -> Step(-1, 0, seq1[x - 1], '-')
            Direction.DOWN -> Step(0, -1, '-', seq2[y - 1])
            Direction.HOME -> {
                return Pair(align1.reversed().joinToString(""), align2.reversed().joinToString(""))
            }
        }
        return backtrackInner(seq1, seq2, x+ step.dx, y + step.dy, align1 + step.c1, align2 + step.c2)
    }

    val (alignment1, alignment2) = backtrackInner(seq1, seq2, seq1.length, seq2.length, listOf(), listOf())
    return Triple(this.cost(), alignment1, alignment2)
}



