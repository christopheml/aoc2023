package fr.christopheml.aoc2023.day08

import fr.christopheml.aoc2023.common.Input
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HauntedWastelandTest {

    private val input1 = Input(listOf(
        "RL",
        "",
        "AAA = (BBB, CCC)",
        "BBB = (DDD, EEE)",
        "CCC = (ZZZ, GGG)",
        "DDD = (DDD, DDD)",
        "EEE = (EEE, EEE)",
        "GGG = (GGG, GGG)",
        "ZZZ = (ZZZ, ZZZ)",
    ))

    private val input2 = Input(listOf(
        "LR",
        "",
        "11A = (11B, XXX)",
        "11B = (XXX, 11Z)",
        "11Z = (11B, XXX)",
        "22A = (22B, XXX)",
        "22B = (22C, 22C)",
        "22C = (22Z, 22Z)",
        "22Z = (22B, 22B)",
        "XXX = (XXX, XXX)"
    ))

    @Test
    fun `Part 1 acceptance test`() {
        assertThat(HauntedWasteland().partOne(input1)).isEqualTo(2)
    }

    @Test
    fun `Part 2 acceptance test`() {
        assertThat(HauntedWasteland().partTwo(input2)).isEqualTo(6)
    }

}