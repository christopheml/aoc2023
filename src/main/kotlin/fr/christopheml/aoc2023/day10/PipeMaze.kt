package fr.christopheml.aoc2023.day10

import fr.christopheml.aoc2023.common.Grid
import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.Point
import fr.christopheml.aoc2023.common.runners.Solution
import fr.christopheml.aoc2023.day10.Pipe.*
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

enum class Pipe(val representation: Char) {

    None('.') {
        override fun connects(position: Point): List<Point> = emptyList()
    },
    Vertical('|') {
        override fun connects(position: Point): List<Point> = listOf(position.top(), position.bottom())
    },
    Horizontal('-') {
        override fun connects(position: Point): List<Point> = listOf(position.left(), position.right())
    },
    StartingPosition('S') {
        override fun connects(position: Point): List<Point> = position.allNeighbors()
    },
    NorthToEastBend('L') {
        override fun connects(position: Point): List<Point> = listOf(position.top(), position.right())
    },
    NorthToWestBend('J') {
        override fun connects(position: Point): List<Point>  = listOf(position.top(), position.left())
    },
    SouthToWestBend('7') {
        override fun connects(position: Point): List<Point> = listOf(position.bottom(), position.left())
    },
    SouthToEastBend('F') {
        override fun connects(position: Point): List<Point> = listOf(position.bottom(), position.right())
    };

    abstract fun connects(position: Point): List<Point>

    override fun toString(): String {
        return "Pipe(representation=$representation)"
    }


    companion object {
        fun from(c: Char): Pipe {
            val value = entries.firstOrNull { it.representation == c }
            if (value == null) throw NoSuchElementException("Caractère invalide '$c'")
            return value
        }
    }

}

fun Grid<Pipe>.next(origin: Point, pipe: Point) = this[pipe].connects(pipe).filterNot { it == origin }.first()

class PipeMaze : Solution<Int>(10) {
    private fun findLoop(
        start: Point,
        grid: Grid<Pipe>
    ): List<Point> {
        val loop = ArrayList<Point>()
        loop.add(start)
        var current: Point? =
            grid.directNeighbors(start).filter { grid[it].connects(it).contains(start) }.first { it != start }
        do {
            val previous = loop.last()
            loop.add(current!!)
            current = grid.next(previous, current)
        } while (current != start)
        return loop
    }

    override fun partOne(input: Input): Int {
        val grid = Grid(input.multi.asList().map { it.map { c -> Pipe.from(c) } })
        val start = grid.first(StartingPosition)

        val loop = findLoop(start, grid)

        return loop.size / 2
    }

    override fun partTwo(input: Input): Int {
        val grid = Grid(input.multi.asList().map { it.map { c -> Pipe.from(c) } })
        val start = grid.first(StartingPosition)
        val loop = findLoop(start, grid).toSet()

        val maze = grid.mapIndexed { element, point ->
            if (point in loop) when (element) {
                None -> '.'
                Vertical -> '║'
                Horizontal -> '═'
                StartingPosition -> '╗'
                NorthToEastBend -> '╚'
                NorthToWestBend -> '╝'
                SouthToWestBend -> '╗'
                SouthToEastBend -> '╔'
            } else 'A'
        }.toList()

        val bufferedImage = BufferedImage(grid.width * 12, grid.height * 30, BufferedImage.TYPE_INT_RGB)
        val g = bufferedImage.createGraphics()!!
        g.paint = Color.WHITE
        g.font = Font("JetBrains Mono Medium", Font.PLAIN, 20)


        var y = 20
        maze.map { it.joinToString("") }.forEach {
            g.drawString(it, 0, y)
            y += 30
        }
        g.dispose()

        val outputfile = File("image.png")
        ImageIO.write(bufferedImage, "png", outputfile)

        println(maze.joinToString("\n") { it.joinToString("") })
        return -1
    }

}

fun main() {
    PipeMaze().run()
}