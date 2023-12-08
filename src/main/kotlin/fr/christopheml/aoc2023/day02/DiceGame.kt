package fr.christopheml.aoc2023.day02

import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.runners.Solution
import fr.christopheml.aoc2023.common.toPair
import fr.christopheml.aoc2023.common.toSequence
import kotlin.math.max

class DiceGame : Solution<Int>(2) {
    override fun partOne(input: Input): Int {
        return input.multi.asSequence()
            .map { parseGame(it) }
            .filter { game -> game.draws.none { it.red > 12 || it.green > 13 || it.blue > 14 } }
            .sumOf { it.number }
    }

    override fun partTwo(input: Input): Int {
        return input.multi.asSequence()
            .map { parseGame(it) }
            .sumOf { it.power() }
    }

}

data class Game(val number: Int, val draws: List<Draw>) {

    fun power(): Int {
        val maxCubes = draws.reduce { draw1, draw2 -> Draw(
            max(draw1.red, draw2.red),
            max(draw1.green, draw2.green),
            max(draw1.blue, draw2.blue)
        ) }
        return maxCubes.red * maxCubes.green * maxCubes.blue
    }

}
data class Draw(val red: Int, val green: Int, val blue: Int)

fun parseGame(input: String) : Game {
    val (gameNumber, gameLog) = input.toPair(": ", prefix = "Game ")
    val draws = gameLog.splitToSequence("; ").map { parseDraw(it) }.toList()
    return Game(gameNumber.toInt(), draws)
}

fun parseDraw(draw: String) : Draw {
    val values = draw.splitToSequence(", ").map { it.split(" ") }.associate { it[1] to it[0].toInt() }
    return Draw(
        values.getOrDefault("red", 0),
        values.getOrDefault("green", 0),
        values.getOrDefault("blue", 0)
    )
}

fun main() {
    DiceGame().run()
}
