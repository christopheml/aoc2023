package fr.christopheml.aoc2023.common

import fr.christopheml.aoc2023.common.StringSplitter.Companion.many
import fr.christopheml.aoc2023.common.StringSplitter.Companion.one
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StringSplitterTest {

    @Test
    fun `One single separator`() {
        assertThat("Type: O Negative".split(one(": ")))
            .containsExactly("Type", "O Negative")
    }

    @Test
    fun `Multiple single separators`() {
        assertThat("Coordinates = 18, 9".split(one(" = "), one(", ")))
            .containsExactly("Coordinates", "18", "9")
    }

    @Test
    fun `One many separator`() {
        assertThat("bim|bam|boum".split(many("|"))).containsExactly("bim", "bam", "boum")
    }

    @Test
    fun `Combined one and many`() {
        assertThat("Numbers: 233, 774, 23, 0, 99".split(one(": "), many(", ")))
            .containsExactly("Numbers", "233", "774", "23", "0", "99")
    }

}