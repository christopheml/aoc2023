package fr.christopheml.aoc2023.day11

import fr.christopheml.aoc2023.common.Grid
import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.LongPoint
import fr.christopheml.aoc2023.common.runners.Solution


class CosmicExpansion(private val customExpansionFactor: Long = 1000000) : Solution<Long>(11) {

    enum class Space { Void, Galaxy }

    private fun LongPoint.expand(expandingLines: List<Int>, expandingColumns: List<Int>, factor: Long) =
        LongPoint(x + expandingLines.count { it < x } * (factor - 1),
            y + expandingColumns.count { it < y } * (factor - 1))


    private fun grid(input: Input) =
        Grid(input.multi.asList().map { it.map { c -> if (c == '#') Space.Galaxy else Space.Void } })

    private fun expandingSpace(grid: Grid<Space>): Pair<List<Int>, List<Int>> =
        Pair(grid.verticalIndices.filter { grid.line(it).none { p -> p == Space.Galaxy } },
            grid.horizontalIndices.filter { grid.column(it).none { p -> p == Space.Galaxy } })

    private fun distances(galaxies: List<LongPoint>): Long {
        return sequence {
            for (a in galaxies.indices) {
                for (b in (a + 1)..galaxies.indices.last) {
                    yield(galaxies[a].distanceFrom(galaxies[b]))
                }
            }
        }.sum()
    }

    private fun galaxies(
        grid: Grid<Space>, expandingLines: List<Int>, expandingColumns: List<Int>, expansionFactor: Long
    ) = grid.points()
        .filter { grid[it] == Space.Galaxy }
        .map { it.toLongPoint() }
        .map { it.expand(expandingLines, expandingColumns, expansionFactor) }.toList()

    private fun solve(input: Input, expansionFactor: Long): Long {
        val grid = grid(input)
        val (expandingLines, expandingColumns) = expandingSpace(grid)
        val galaxies = galaxies(grid, expandingLines, expandingColumns, expansionFactor)

        return distances(galaxies)
    }

    override fun partOne(input: Input): Long {
        return solve(input, 2)
    }

    override fun partTwo(input: Input): Long {
        return solve(input, customExpansionFactor)
    }

}

fun main() {
    CosmicExpansion().run()
}