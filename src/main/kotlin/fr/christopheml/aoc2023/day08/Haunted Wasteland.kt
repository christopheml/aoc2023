package fr.christopheml.aoc2023.day08

import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.runners.Solution
import java.lang.IllegalStateException

// Pompé sans vergogne sur Baeldung @ https://www.baeldung.com/kotlin/lcm
private fun lowestCommonMultiple(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}

class HauntedWasteland : Solution<Long>(8) {

    class Network(private val path: String, nodes: List<Triple<String, String, String>>) {

        private val nodes = nodes.associateBy({ it.first }, { Pair(it.second, it.third) })

        private fun left(node: String) = nodes[node]!!.first

        private fun right(node: String) = nodes[node]!!.second

        private fun step(index: Int) = path[index % path.length]

        private fun navigateTo(start: String, endCondition: (String) -> Boolean): Int {
            var current = start
            for (steps in 0..<Int.MAX_VALUE) {
                if (endCondition(current)) return steps
                current = when (step(steps)) {
                    'L' -> left(current)
                    else -> right(current)
                }
            }
            throw IllegalStateException()
        }

        fun navigateTo(start: String, end: String) = navigateTo(start) { it == end }

        fun detectCycle(start: String) = navigateTo(start) { it.endsWith("Z") }.toLong()

        fun startingNodes() = nodes.keys.filter { it.endsWith("A") }

    }

    private fun read(input: Input) =
        Network(
            input.single.value, input.multi.asSequence().drop(2).map {
                Triple(it.substring(0, 3), it.substring(7, 10), it.substring(12, 15))
            }.toList()
        )

    override fun partOne(input: Input): Long {
        val network = read(input)
        return network.navigateTo("AAA", "ZZZ").toLong()
    }

    override fun partTwo(input: Input): Long {
        val network = read(input)
        return network.startingNodes().map {
            network.detectCycle(it)
        }.fold(1L) { a, b -> lowestCommonMultiple(a, b) }
    }

}

fun main() {
    HauntedWasteland().run()
}