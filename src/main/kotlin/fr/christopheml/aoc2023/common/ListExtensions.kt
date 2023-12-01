package com.github.christopheml.aoc2022.common

fun <T> List<T>.startsWith(other: List<T>): Boolean {
    return other.indices.all { this[it] == other[it] }
}

fun <T> List<T>.endsWith(other: List<T>): Boolean {
    return other.indices.all { this[size - other.size + it] == other[it] }
}

fun <T> List<T>.containsList(other: List<T>): Boolean {
    // This is a naive implementation without skip logic, but it should be sufficient
    return indexOfList(other) > -1
}

fun <T> List<T>.indexOfList(other: List<T>): Int {
    return indices
        .filter { this[it] == other.first() }
        .filter { it + other.size <= size }
        .firstOrNull { offset -> other.indices.all { this[offset + it] == other[it] } }
        ?: -1
}

fun <T> List<T>.split(separator: T): List<List<T>> {
    val cuts = listOf(-1) + indices.filter { this[it] == separator } + listOf(size)
    return cuts.windowed(2)
        .map { subList(it[0] + 1, it[1]) }
        .filterNot { it.isEmpty() }
}
