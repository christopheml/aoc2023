package fr.christopheml.aoc2023.day12

import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.asInts
import fr.christopheml.aoc2023.common.runners.Solution
import kotlin.math.min

class HotSprings : Solution<Long>(12) {

    private fun canEat(records: String, size: Int): Boolean {
        if (records.length < size) return false
        if (records.length == size) return records.none { it == '.' }
        return records.substring(0, size).none { it == '.' } && records[size] != '#'
    }

    private fun eat(records: String, size: Int): String {
        return if (records.length == size) "" else records.drop(size + 1)
    }

    private fun minimumSizeOfRecords(groups: List<Int>) = groups.sum() + groups.size - 1

    private val cache = HashMap<Pair<String, List<Int>>, Long>()

    private fun cacheAndReturn(records: String, groups: List<Int>, result: Long): Long {
        cache[Pair(records, groups)] = result
        return result
    }

    fun arrangements(records: String, groups: List<Int>): Long {
        val cacheKey = Pair(records, groups)
        if (cache[cacheKey] != null) return cache[cacheKey]!!

        if (records.count { it == '?' || it == '#' } < groups.sum() || records.length < minimumSizeOfRecords(groups))
            return 0L

        if (groups.isEmpty()) return if (records.count { it == '#' } == 0) 1L else 0

        return cacheAndReturn(
            records, groups, when (records[0]) {
                '.' -> {
                    val skipTo = min(records.indexOfFirst { it != '.' }, records.length - 1)
                    arrangements(records.drop(skipTo), groups)
                }
                '#' -> {
                    if (canEat(records, groups[0])) arrangements(eat(records, groups[0]), groups.drop(1)) else 0L
                }
                else -> {
                    arrangements(records.drop(1), groups) +
                            if (canEat(records, groups[0])) arrangements(
                                eat(records, groups[0]),
                                groups.drop(1)
                            ) else 0L
                }
            }
        )
    }

    override fun partOne(input: Input): Long {
        return input.multi.asSequence().map {
            val (records, groups) = it.split(" ")
            arrangements(records, groups.asInts())
        }.sum()
    }

    override fun partTwo(input: Input): Long {
        return input.multi.asSequence().map {
            val (records, groups) = it.split(" ")
            val unfoldedRecords = (1..5).joinToString("?") { records }
            val unfoldedGroups = (1..5).joinToString(",") { groups }
            arrangements(unfoldedRecords, unfoldedGroups.asInts())
        }.sum()
    }
}

fun main() {
    HotSprings().run()
}