package fr.christopheml.aoc2023.day13

import fr.christopheml.aoc2023.common.Input
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointOfIncidenceTest {

    val input = Input(listOf(
        "#.##..##.",
        "..#.##.#.",
        "##......#",
        "##......#",
        "..#.##.#.",
        "..##..##.",
        "#.#.##.#.",
        "",
        "#...##..#",
        "#....#..#",
        "..##..###",
        "#####.##.",
        "#####.##.",
        "..##..###",
        "#....#..#"
    ))

    @Test
    fun `Part 1 acceptance test`() {
        assertThat(PointOfIncidence().partOne(input)).isEqualTo(405)
    }

    @Test
    fun `Part 2 acceptance test`() {
        assertThat(PointOfIncidence().partTwo(input)).isEqualTo(400)
    }

}