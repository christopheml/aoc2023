package fr.christopheml.aoc2023.common

fun <T, R> Pair<T, T>.map(transform: (T) -> R): Pair<R, R> {
    return Pair(transform.invoke(first), transform.invoke(second))
}
