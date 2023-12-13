package fr.christopheml.aoc2023.day12

import fr.christopheml.aoc2023.common.Input
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HotSpringsTest {

    val input = Input(
        listOf(
            "???.### 1,1,3",
            ".??..??...?##. 1,1,3",
            "?#?#?#?#?#?#?#? 1,3,1,6",
            "????.#...#... 4,1,1",
            "????.######..#####. 1,6,5",
            "?###???????? 3,2,1"
        )
    )

    @Test
    fun `Part 1 acceptance test`() {
        assertThat(HotSprings().partOne(input)).isEqualTo(21)
    }

    @Test
    fun `Part 2 acceptance test`() {
        assertThat(HotSprings().partTwo(input)).isEqualTo(525152)
    }

    @Test
    fun `Part 2-1`() {
        assertThat(HotSprings().arrangements("???.###????.###????.###????.###????.###", listOf(1,1,3,1,1,3,1,1,3,1,1,3,1,1,3))).isEqualTo(1)
    }

    @Test
    fun `Part 2-2`() {
        assertThat(HotSprings().arrangements(".??..??...?##.?.??..??...?##.?.??..??...?##.?.??..??...?##.?.??..??...?##.", listOf(1,1,3,1,1,3,1,1,3,1,1,3,1,1,3))).isEqualTo(16384)
    }

    @Test
    fun `Part 1-1`() {
        assertThat(HotSprings().arrangements("???.###", groups = listOf(1, 1, 3))).isEqualTo(1)
    }

    @Test
    fun `Part 1-2`() {
        assertThat(HotSprings().arrangements(".??..??...?##.", groups = listOf(1, 1, 3))).isEqualTo(4)
    }

    @Test
    fun `Part 1-3`() {
        assertThat(HotSprings().arrangements("?#?#?#?#?#?#?#?", groups = listOf(1, 3, 1, 6))).isEqualTo(1)
    }

    @Test
    fun `Single with empty prefix`() {
        assertThat(HotSprings().arrangements("...#", groups = listOf(1))).isEqualTo(1)
    }

    @Test
    fun `jjj to 1 and 1`() {
        assertThat(HotSprings().arrangements("???", groups = listOf(1, 1))).isEqualTo(1)
    }



}