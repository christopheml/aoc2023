package fr.christopheml.aoc2023.common

import kotlin.math.abs

data class Point(val x: Int, val y: Int) {

    fun top() = Point(x - 1, y)
    fun bottom() = Point(x + 1, y)

    fun left() = Point(x, y - 1)

    fun right() = Point(x, y + 1)

    fun topLeft() = Point(x - 1, y - 1)

    fun topRight() = Point(x - 1, y + 1)

    fun bottomLeft() = Point(x + 1, y - 1)

    fun bottomRight() = Point(x + 1, y + 1)

    fun directNeighbors() = listOf(top(), right(), bottom(), left())

    fun allNeighbors() = listOf(top(), topRight(), right(), bottomRight(), bottom(), bottomLeft(), left(), topLeft())

    fun distanceFrom(other: Point) = abs(x - other.x) + abs(y - other.y)

    fun toLongPoint() = LongPoint(x.toLong(), y.toLong())

}

data class LongPoint(val x: Long, val y: Long) {

    fun top() = LongPoint(x - 1, y)
    fun bottom() = LongPoint(x + 1, y)

    fun left() = LongPoint(x, y - 1)

    fun right() = LongPoint(x, y + 1)

    fun topLeft() = LongPoint(x - 1, y - 1)

    fun topRight() = LongPoint(x - 1, y + 1)

    fun bottomLeft() = LongPoint(x + 1, y - 1)

    fun bottomRight() = LongPoint(x + 1, y + 1)

    fun directNeighbors() = listOf(top(), right(), bottom(), left())

    fun allNeighbors() = listOf(top(), topRight(), right(), bottomRight(), bottom(), bottomLeft(), left(), topLeft())

    fun distanceFrom(other: LongPoint) = abs(x - other.x) + abs(y - other.y)

}


class Grid<T>(val lines: List<List<T>>) {

    val width = lines[0].size
    val height = lines.size
    val horizontalIndices = 0..<width
    val verticalIndices = 0..<height

    val size = width * height

    private fun inRange(line: Int, column: Int) = line in verticalIndices && column in horizontalIndices

    private fun inRange(point: Point) = inRange(point.x, point.y)

    fun points() = sequence {
        for (x in verticalIndices) {
            for (y in horizontalIndices) {
                yield(Point(x, y))
            }
        }
    }

    fun <R> map(transform: (element: T) -> R): Grid<R> {
        return Grid(
            lines.map { line ->
                line.map { transform(it) }
            }.toList()
        )
    }

    fun <R> mapIndexed(transform: (element: T, point: Point) -> R): Grid<R> {
        return Grid(
            lines.mapIndexed { x, line ->
                line.mapIndexed { y, element -> transform(element, Point(x, y)) }
            }.toList()
        )
    }

    /**
     * Returns a [Sequence] of [points][Point] matching the given predicate.
     *
     * This is more efficient than calling [points] then [Sequence.filter].
     */
    fun filter(predicate: (T) -> Boolean) = sequence {
        for (x in verticalIndices) {
            for (y in horizontalIndices) {
                if (predicate(get(x, y))) yield(Point(x, y))
            }
        }
    }

    fun toList() = lines

    fun line(index: Int) = lines[index].asSequence()

    fun column(index: Int) = sequence {
        for (x in verticalIndices) {
            yield(lines[x][index])
        }
    }

    fun transpose() = Grid(
        horizontalIndices.map { column(it).toList() }.toList()
    )

    fun directNeighbors(point: Point) = point.directNeighbors().filter { inRange(it) }

    fun allNeighbors(point: Point) = point.allNeighbors().filter { inRange(it) }

    operator fun get(line: Int, column: Int): T = lines[line][column]

    operator fun get(point: Point): T = lines[point.x][point.y]

    /**
     * Returns the coordinates of the first occurrence of the given element
     */
    fun first(element: T): Point {
        for (line in verticalIndices) {
            for (column in horizontalIndices) {
                if (lines[line][column] == element) return Point(line, column)
            }
        }
        throw NoSuchElementException("Element '$element' not found in the grid")
    }

}