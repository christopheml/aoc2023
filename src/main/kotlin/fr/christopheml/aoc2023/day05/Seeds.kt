package fr.christopheml.aoc2023.day05

import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.asLongs
import fr.christopheml.aoc2023.common.runners.Solution
import fr.christopheml.aoc2023.common.sections
import kotlinx.coroutines.*

data class MappingRange(val start: Long, val end: Long, val offset: Long)

class Mapping(mappingRanges: List<MappingRange>) {

    private val sortedMappingRanges: List<MappingRange> = mappingRanges.sortedBy { it.start }
    private val boundaries = sortedMappingRanges.first().start..sortedMappingRanges.last().end

    fun apply(value: Long): Long {
        if (value !in boundaries) return value
        for (mapping in sortedMappingRanges) {
            if (value >= mapping.start && value <= mapping.end) return value + mapping.offset
        }
        return value
    }

}

class Seeds : Solution<Long>(5) {

    private fun toMapping(lines: List<String>) = Mapping(
        lines.map {
            val (destinationRangeStart, sourceRangeStart, steps) = it.asLongs(' ')
            val offset = destinationRangeStart - sourceRangeStart
            MappingRange(sourceRangeStart, sourceRangeStart + steps - 1, offset)
        }
    )

    private fun mappings(input: Input) = input.multi.asList()
        .filterNot { it.isBlank() }
        .drop(1)
        .sections { it.endsWith(":") }
        .map { toMapping(it.content) }

    private fun seedToLocation(seed: Long, mappings: List<Mapping>): Long {
        var result: Long = seed
        mappings.forEach {
            result = it.apply(result)
        }
        return result
    }

    override fun partOne(input: Input): Long {
        val seeds = input.single.value.substring(7).asLongs(' ')
        val mappings = mappings(input)
        return seeds.map { seedToLocation(it, mappings) }.min()
    }

    override fun partTwo(input: Input): Long {
        val seeds = input.single.value.substring(7).asLongs(' ')
            .windowed(2, 2)
            .map { it[0]..(it[0] + it[1]) }
            .toList()
        val mappings = mappings(input)
        return runBlocking {
            coroutineScope {
                seeds.map { range ->
                    async {
                        var minimum = Long.MAX_VALUE
                        range.forEach { seed ->
                            val location = seedToLocation(seed, mappings)
                            if (location < minimum) minimum = location
                        }
                        minimum
                    }
                }.awaitAll().min()
            }
        }
    }

}

fun main() {
    Seeds().run()
}