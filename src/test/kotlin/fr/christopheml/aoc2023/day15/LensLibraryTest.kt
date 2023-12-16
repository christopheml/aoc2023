package fr.christopheml.aoc2023.day15

import fr.christopheml.aoc2023.common.Input
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LensLibraryTest {

    val input = Input(listOf("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"))

    @Test
    fun `Part 2 acceptance test`() {
        assertThat(LensLibrary().partTwo(input)).isEqualTo(145)
    }
}