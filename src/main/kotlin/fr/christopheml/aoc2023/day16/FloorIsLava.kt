package fr.christopheml.aoc2023.day16

import fr.christopheml.aoc2023.common.Direction
import fr.christopheml.aoc2023.common.Grid
import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.Point
import fr.christopheml.aoc2023.common.runners.Solution

class FloorIsLava : Solution<Int>(16) {

    enum class Elements(val representation: Char) {
        None('.'),
        RightMirror('/'),
        LeftMirror('\\'),
        VerticalSplitter('|'),
        HorizontalSplitter('-');

        companion object {
            fun from(c: Char): Elements {
                val value = Elements.entries.firstOrNull { it.representation == c }
                if (value == null) throw NoSuchElementException("Caractère invalide '$c'")
                return value
            }
        }
    }


    fun next(grid: Grid<Elements>, position: Point, direction: Direction): List<Pair<Point, Direction>> {
        val element = grid[position]
        return if (element == Elements.None
            || (element == Elements.HorizontalSplitter && (direction == Direction.Left || direction == Direction.Right))
            || element == Elements.VerticalSplitter && (direction == Direction.Top || direction == Direction.Bottom)
        ) {
            listOf(Pair(direction.next(position), direction))
        } else if (element == Elements.LeftMirror) {
            listOf(
                when (direction) {
                    Direction.Top -> Pair(position.left(), Direction.Left)
                    Direction.Right -> Pair(position.bottom(), Direction.Bottom)
                    Direction.Bottom -> Pair(position.right(), Direction.Right)
                    Direction.Left -> Pair(position.top(), Direction.Top)
                }
            )
        } else if (element == Elements.RightMirror) {
            listOf(
                when (direction) {
                    Direction.Top -> Pair(position.right(), Direction.Right)
                    Direction.Right -> Pair(position.top(), Direction.Top)
                    Direction.Bottom -> Pair(position.left(), Direction.Left)
                    Direction.Left -> Pair(position.bottom(), Direction.Bottom)
                }
            )
        } else if (element == Elements.HorizontalSplitter) {
            listOf(Pair(position.left(), Direction.Left), Pair(position.right(), Direction.Right))
        } else if (element == Elements.VerticalSplitter) {
            listOf(Pair(position.bottom(), Direction.Bottom), Pair(position.top(), Direction.Top))
        } else throw IllegalStateException()
    }

    private fun trace(grid: Grid<Elements>, initialPosition: Point, initialDirection: Direction): List<Point> {
        val visited = HashSet<Pair<Point, Direction>>()
        val toVisit = ArrayDeque<Pair<Point, Direction>>()
        val start = Pair(initialPosition, initialDirection)
        toVisit.addLast(start)

        while (toVisit.isNotEmpty()) {
            val current = toVisit.removeLast()
            if (visited.contains(current)) continue
            visited.add(current)
            val (position, direction) = current
            val next = next(grid, position, direction)
            next.filter { grid.inRange(it.first) }
                .filterNot { visited.contains(it) }
                .forEach { toVisit.add(it) }
        }

        return visited.map { it.first }
    }

    override fun partOne(input: Input): Int {
        val grid = Grid(input.multi.asList().map { it.map(Elements.Companion::from) })
        return trace(grid, Point(0, 0), Direction.Right).distinct().size
    }

    override fun partTwo(input: Input): Int {
        val grid = Grid(input.multi.asList().map { it.map(Elements.Companion::from) })

        val rays = grid.horizontalIndices.flatMap {
            listOf(Pair(Point(0, it), Direction.Bottom), Pair(Point(grid.verticalIndices.last, it), Direction.Top))
        } + grid.verticalIndices
            .filterNot { it == 0 || it == grid.verticalIndices.last }
            .flatMap {
                listOf(
                    Pair(Point(it, 0), Direction.Right),
                    Pair(Point(it, grid.horizontalIndices.last), Direction.Left)
                )
            }

        return rays.maxOf { trace(grid, it.first, it.second).distinct().size }
    }
}

fun main() {
    FloorIsLava().run()
}