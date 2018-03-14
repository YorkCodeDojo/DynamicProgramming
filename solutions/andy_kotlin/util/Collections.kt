package util

/**
 * Accumulate Function
 *
 * Basically a Combination of Fold and Map which uses the result of previous element to act as the entry into the next.
 */
inline fun <T, R> Iterable<T>.accumulate(initial: R, operation: (previous: R, T) -> R): List<R> {
    var accumulator = arrayListOf(initial)
    for (element in this) accumulator.add(operation(accumulator.last(), element))
    return accumulator
}
