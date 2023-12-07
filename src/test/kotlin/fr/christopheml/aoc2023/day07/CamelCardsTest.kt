package fr.christopheml.aoc2023.day07

import fr.christopheml.aoc2023.common.Input
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CamelCardsTest {

    private val input = Input(listOf("32T3K 765", "T55J5 684", "KK677 28", "KTJJT 220", "QQQJA 483"))

    @Test
    fun `Part 1 acceptance test`() {
        assertThat(CamelCards().partOne(input)).isEqualTo(6440)
    }

    @Test
    fun `Part 2 acceptance test`() {
        assertThat(CamelCards().partTwo(input)).isEqualTo(5905)
    }

}