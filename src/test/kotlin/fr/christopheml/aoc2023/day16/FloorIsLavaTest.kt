package fr.christopheml.aoc2023.day16

import fr.christopheml.aoc2023.common.Direction
import fr.christopheml.aoc2023.common.Grid
import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FloorIsLavaTest {

    val input = Input(
        listOf(
            ".|...\\....",
            "|.-.\\.....",
            ".....|-...",
            "........|.",
            "..........",
            ".........\\",
            "..../.\\\\..",
            ".-.-/..|..",
            ".|....-|.\\",
            "..//.|...."
        )
    )

    @Test
    fun `Part 1 acceptance test`() {
        assertThat(FloorIsLava().partOne(input)).isEqualTo(46)
    }

}