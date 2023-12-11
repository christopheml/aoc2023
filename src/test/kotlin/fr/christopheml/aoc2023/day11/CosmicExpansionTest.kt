package fr.christopheml.aoc2023.day11

import fr.christopheml.aoc2023.common.Input
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CosmicExpansionTest {

    private val input = Input(
        listOf(
            "...#......",
            ".......#..",
            "#.........",
            "..........",
            "......#...",
            ".#........",
            ".........#",
            "..........",
            ".......#..",
            "#...#....."
        )
    )

    @Test
    fun `Part 1 acceptance test`() {
        assertThat(CosmicExpansion().partOne(input)).isEqualTo(374)
    }

    @Test
    fun `Part 2 acceptance test`() {
        assertThat(CosmicExpansion(10).partTwo(input)).isEqualTo(1030)
        assertThat(CosmicExpansion(100).partTwo(input)).isEqualTo(8410)
    }

}