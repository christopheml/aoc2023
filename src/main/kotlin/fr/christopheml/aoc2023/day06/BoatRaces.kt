package fr.christopheml.aoc2023.day06

import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.asLongs
import fr.christopheml.aoc2023.common.runners.Solution


fun distance(pressDuration: Long, time: Long): Long = (time - pressDuration) * pressDuration

class BoatRaces : Solution<Int>(6) {

    data class Race(val duration: Long, val bestDistance: Long) {

        fun isBetter(pressDuration: Long) = distance(pressDuration, duration) > bestDistance

    }

    private fun toRaces(input: Input): List<Race> {
        val times = input.multi.asList()[0].substring(12).asLongs(' ')
        val distances = input.multi.asList()[1].substring(12).asLongs(' ')
        return times.mapIndexed { i, time -> Race(time, distances[i]) }
    }

    private fun pressTimesToBeat(race: Race, lowerBoundary: Long = 1, upperBoundary: Long = race.duration): Int {
        var validSolutions = 0
        for (duration in lowerBoundary..upperBoundary) {
            val distance = distance(duration, race.duration)
            if (distance > race.bestDistance) validSolutions++
            if (distance < race.bestDistance && validSolutions > 0) break
        }
        return validSolutions
    }

    override fun partOne(input: Input): Int {
        val races = toRaces(input)
        return races.map { pressTimesToBeat(it) }.reduce { a, b -> a * b }
    }

    override fun partTwo(input: Input): Int {
        val race = Race(47986698, 400121310111540)
        val rangeCutoff = 100 // Condition d'arrêt pour la dichotomie, pas besoin d'être précis.

        fun findLeftBoundary(range: LongRange): Long {
            val rangeSize = range.last - range.first

            val cut = rangeSize / 2
            return if (race.isBetter(range.last)) {
                findLeftBoundary(range.first..cut)
            } else {
                if (rangeSize < rangeCutoff) return range.first
                findLeftBoundary((range.first + cut)..range.last)
            }
        }

        fun findRightBoundary(range: LongRange): Long {
            val rangeSize = range.last - range.first

            val cut = rangeSize / 2
            return if (race.isBetter(range.first)) {
                findRightBoundary((range.first + cut)..range.last)
            } else {
                if (rangeSize < rangeCutoff) return range.last
                findRightBoundary(range.first..(range.last - cut))
            }
        }

        return pressTimesToBeat(
            race,
            findLeftBoundary(1..(race.duration / 2)),
            findRightBoundary((race.duration / 2)..race.duration)
        )


    }

}

fun main() {
    BoatRaces().run()
}