package fr.christopheml.aoc2023.day10

import fr.christopheml.aoc2023.common.Input
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PipeMazeTest {

    val input = Input(
        listOf(
            "..F7.",
            ".FJ|.",
            "SJ.L7",
            "|F--J",
            "LJ..."
        )
    )

    val input2 = Input(
        listOf(
            "FF7FSF7F7F7F7F7F---7",
            "L|LJ||||||||||||F--J",
            "FL-7LJLJ||||||LJL-77",
            "F--JF--7||LJLJ7F7FJ-",
            "L---JF-JLJ.||-FJLJJ7",
            "|F|F-JF---7F7-L7L|7|",
            "|FFJF7L7F-JF7|JL---7",
            "7-L-JL7||F7|L7F-7F7|",
            "L.L7LFJ|||||FJL7||LJ",
            "L7JLJL-JLJLJL--JLJ.L"
        )
    )


    @Test
    fun `Part 1 acceptance test`() {
        assertThat(PipeMaze().partOne(input)).isEqualTo(8)
    }

    @Test
    fun `Part 2 acceptance test`() {
        PipeMaze().partTwo(input2)
    }

}