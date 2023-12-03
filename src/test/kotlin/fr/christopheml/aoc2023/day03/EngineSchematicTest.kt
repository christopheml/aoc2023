package fr.christopheml.aoc2023.day03

import fr.christopheml.aoc2023.common.Input
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EngineSchematicTest {

    private val input = listOf(
        "467..114..",
        "...*......",
        "..35..633.",
        "......#...",
        "617*......",
        ".....+.58.",
        "..592.....",
        "......755.",
        "...$.*....",
        ".664.598.."
    )

    @Test
    fun `Acceptance test part 1`() {
        val schematic = EngineSchematic(input)
        assertThat(schematic.partNumbers()).containsExactly(467, 35, 633, 617, 592, 755, 664, 598)
    }

    @Test
    fun `Acceptance test part 2`() {
        val solution = GearRatios().partTwo(Input(input))
        assertThat(solution).isEqualTo(467835)
    }

}