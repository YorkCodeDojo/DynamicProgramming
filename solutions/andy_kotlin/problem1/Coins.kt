package problem1

private fun pickCoin(coins : List<Int>, previous : List<Pair<Int,Int>>) : Pair<Int,Int> {
    return coins.filter { coin -> coin <= previous.size }
            .map { previous[previous.size-it].first + 1 to it }
            .minBy { it.first }!!
}

fun List<Int>.generateChange(target: Int) : List<Int>{
    val steps = mutableListOf(0 to 0)
    (1..target).forEach {
        steps.add(pickCoin(this, steps))
    }
    return backtrack(target, steps)
}

private fun backtrack(target: Int, steps: MutableList<Pair<Int, Int>>): MutableList<Int> {
    val change = mutableListOf<Int>()
    var ptr = target
    while (ptr > 0) {
        change.add(steps[ptr].second)
        ptr -= change.last()
    }
    return change
}
