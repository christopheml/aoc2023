package fr.christopheml.aoc2023.common

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

}

class Grid<T>(private val lines: List<List<T>>) {

    val width = lines[0].size
    val height = lines.size
    private val horizontalRange = 0..<width
    private val verticalRange = 0..<height

    val size = width * height

    private fun inRange(line: Int, column: Int) = line in verticalRange && column in horizontalRange

    private fun inRange(point: Point) = inRange(point.x, point.y)

    fun points() = sequence {
        for (x in verticalRange) {
            for (y in horizontalRange) {
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

    fun toList() = lines

    fun directNeighbors(point: Point) = point.directNeighbors().filter { inRange(it) }

    fun allNeighbors(point: Point) = point.allNeighbors().filter { inRange(it) }

    operator fun get(line: Int, column: Int): T = lines[line][column]

    operator fun get(point: Point): T = lines[point.x][point.y]

    /**
     * Returns the coordinates of the first occurrence of the given element
     */
    fun first(element: T): Point {
        for (line in verticalRange) {
            for (column in horizontalRange) {
                if (lines[line][column] == element) return Point(line, column)
            }
        }
        throw NoSuchElementException("Element '$element' not found in the grid")
    }

}