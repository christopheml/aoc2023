package fr.christopheml.aoc2023.day05

import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.asLongs
import fr.christopheml.aoc2023.common.runners.Solution
import fr.christopheml.aoc2023.common.sections

class Seeds : Solution<Long>(5) {

    private fun toMapping(lines: List<String>) = lines.map {
        val (destinationRangeStart, sourceRangeStart, steps) = it.asLongs(' ')
        val offset = destinationRangeStart - sourceRangeStart
        sourceRangeStart..<(sourceRangeStart + steps) to offset
    }.sortedBy { it.first.first }

    private fun mappings(input: Input) = input.multi.asList()
        .filterNot { it.isBlank() }
        .drop(1)
        .sections { it.endsWith(":") }
        .map { toMapping(it.content) }

    private fun seedToLocation(seed: Long, mappings: List<List<Pair<LongRange, Long>>>): Long {
        var result: Long = seed
        mappings.forEach {
            if (result >= it.first().first.first && result <= it.last().first.last ) {
                val transformation = it.firstOrNull { t -> result in t.first }
                result = if (transformation == null) result else result + transformation.second
            }
        }
        return result
    }

    override fun partOne(input: Input): Long {
        val seeds = input.single.value.substring(7).asLongs(' ')
        val mappings = mappings(input)
        return seeds.map {seedToLocation(it, mappings) }.min()
    }

    override fun partTwo(input: Input): Long {
        val seeds = input.single.value.substring(7).asLongs(' ')
            .windowed(2, 2)
            .map { it[0]..(it[0] + it[1]) }
            .toList().take(2)
        val mappings = mappings(input)
        TODO("Not implemented")
    }
}

fun main() {
    Seeds().run()
}