package fr.christopheml.aoc2023.day14

import fr.christopheml.aoc2023.common.Input
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParabolicReflectorDishTest {

    val input = Input(
        listOf(
            "O....#....",
            "O.OO#....#",
            ".....##...",
            "OO.#O....O",
            ".O.....O#.",
            "O.#..O.#.#",
            "..O..#O..O",
            ".......O..",
            "#....###..",
            "#OO..#...."
        )
    )

    @Test
    fun `Part 1 acceptance test`() {
        assertThat(ParabolicReflectorDish().partOne(input)).isEqualTo(136)
    }
}