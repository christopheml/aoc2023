package fr.christopheml.aoc2023.day15

import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.runners.Solution

class LensLibrary : Solution<Int>(15) {

    private fun hash(input: String): Int {
        return input.asSequence().fold(0) { acc, char ->
            ((acc + char.code) * 17) % 256
        }
    }

    override fun partOne(input: Input): Int = input.single.split(',').sumOf { hash(it) }

    class Box {
        fun remove(label: String) {
            lenses.removeIf { it.first == label }
        }

        fun put(label: String, focal: Int) {
            val position = lenses.indexOfFirst { it.first == label }
            val newLens = Pair(label, focal)
            if (position >= 0) {
                lenses[position] = newLens
            } else {
                lenses.addLast(newLens)
            }
        }

        val lenses = ArrayDeque<Pair<String, Int>>()
    }

    override fun partTwo(input: Input): Int {
        val boxes = (0..255).map { Box() }
        input.single.split(',').forEach { step ->
            if (step.endsWith('-')) {
                val label = step.dropLast(1)
                val box = boxes[hash(label)]
                box.remove(label)
            } else if (step.contains('=')) {
                val (label, focal) = step.split('=')
                val box = boxes[hash(label)]
                box.put(label, focal.toInt())
            }
        }
        return boxes.mapIndexed { boxNumber, box ->
            box.lenses.mapIndexed { lensSlot, lens ->
                (boxNumber + 1) * (lensSlot + 1) * lens.second
            }.sum()
        }.sum()
    }

}

fun main() {
    LensLibrary().run()
}