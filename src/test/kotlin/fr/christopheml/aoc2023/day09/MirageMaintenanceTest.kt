package fr.christopheml.aoc2023.day09

import fr.christopheml.aoc2023.common.Input
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MirageMaintenanceTest {

    private val input = Input(listOf(
        "0 3 6 9 12 15",
        "1 3 6 10 15 21",
        "10 13 16 21 30 45"
    ))

    @Test
    fun `Part 1 acceptance test`() {
        assertThat(MirageMaintenance().partOne(input)).isEqualTo(114)
    }
}