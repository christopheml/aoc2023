package fr.christopheml.aoc2023.day01

import fr.christopheml.aoc2023.common.Input
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalibrationTest {
    @Test
    fun `Part Two official example`() {
        val input = Input(listOf("two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen"))
        assertThat(Calibration().partTwo(input)).isEqualTo(281)

        //2, 1
        //2
        //1, 2
        //2, 1, 3
        //4, 2
        //1, 2, 3, 4
        //7, 6
    }

}