package fr.christopheml.aoc2023.day09

import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.asLongs
import fr.christopheml.aoc2023.common.runners.Solution


private fun List<Long>.differences() = sequence {
    for (i in 0..<(size - 1)) {
        yield(get(i + 1) - get(i))
    }
}.toList()

class MirageMaintenance : Solution<Long>(9) {

    private fun extrapolate(sequence: List<Long>): Long {
        var current = sequence
        return sequence {
            while (!current.all { it == 0L }) {
                yield(current.last())
                current = current.differences()
            }
        }.sum()
    }

    override fun partOne(input: Input): Long {
        return input.multi.asSequence().map { extrapolate(it.asLongs(' ')) }.sum()
    }

    override fun partTwo(input: Input): Long {
        return input.multi.asSequence().map { extrapolate(it.asLongs(' ').reversed()) }.sum()
    }
}

fun main() {
    MirageMaintenance().run()
}