package fr.christopheml.aoc2023.common

fun String.halves(): Pair<String, String> {
    val cutPoint = this.length / 2
    return Pair(this.substring(0, cutPoint), this.substring(cutPoint))
}

fun String.removePrefix(prefix: String) = if (startsWith(prefix)) drop(prefix.length) else this

fun String.removeSuffix(suffix: String) = if (endsWith(suffix)) dropLast(suffix.length) else this

fun String.toSequence(separator: String, prefix: String = "", suffix: String = ""): Sequence<String> =
    removePrefix(prefix).removeSuffix(suffix).splitToSequence(separator)

fun String.toList(separator: String, prefix: String = "", suffix: String = ""): List<String> =
    removePrefix(prefix).removeSuffix(suffix).split(separator)

fun String.toPair(separator: String, prefix: String = "", suffix: String = ""): Pair<String, String> {
    val (left, right) = toList(separator, prefix, suffix)
    return Pair(left, right)
}

fun String.toIntRange(separator: String = "-"): IntRange {
    val (start, endInclusive) = toList(separator).map { it.toInt() }
    return IntRange(start, endInclusive)
}

fun String.toLongRange(separator: String = "-"): LongRange {
    val (start, endInclusive) = toList(separator).map { it.toLong() }
    return LongRange(start, endInclusive)
}

fun String.toIntRange(separator: Char = '-'): IntRange {
    val cutPoint = this.indexOf(separator)
    assert(cutPoint > 0) { "Invalid range representation: $this" }
    return IntRange(this.substring(0, cutPoint).toInt(), this.substring(cutPoint + 1).toInt())
}

fun String.asInts(separator: Char = ','): List<Int> =
    this.split(separator).map { it.trim() }.filter { it.isNotBlank() }.map { it.toInt() }

fun String.asLongs(separator: Char = ','): List<Long> =
    this.split(separator).map { it.trim() }.filter { it.isNotBlank() }.map { it.toLong() }

fun String.split(vararg splitters: StringSplitter.Splitter) = StringSplitter.split(this, *splitters)
