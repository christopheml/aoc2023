package fr.christopheml.aoc2023.common

fun String.halves(): Pair<String, String> {
    val cutPoint = this.length / 2
    return Pair(this.substring(0, cutPoint), this.substring(cutPoint))
}

fun String.toPair(separator: Char): Pair<String, String> {
    return when (val cutPoint = this.indexOf(separator)) {
        -1 -> Pair(this, "")
        else -> Pair(this.substring(0, cutPoint), this.substring(cutPoint + 1))
    }
}
fun String.toRange(separator: Char = '-'): IntRange {
    val cutPoint = this.indexOf(separator)
    assert(cutPoint > 0) { "Invalid range representation: $this" }
    return IntRange(this.substring(0, cutPoint).toInt(), this.substring(cutPoint + 1).toInt())
}

fun String.asInts(separator: Char = ','): List<Int> = this.split(separator).map { it.trim() }.filter { it.isNotBlank() }.map { it.toInt() }

fun String.asLongs(separator: Char = ','): List<Long> = this.split(separator).map { it.trim() }.filter { it.isNotBlank() }.map { it.toLong() }

fun String.split(vararg splitters: StringSplitter.Splitter) = StringSplitter.split(this, *splitters)
