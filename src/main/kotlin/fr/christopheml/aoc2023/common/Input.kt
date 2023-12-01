package fr.christopheml.aoc2023.common

class Input {

    constructor(day: Int) {
        elements = run {
            val lines = object {}.javaClass.getResource("/inputs/day%02d.txt".format(day))!!.readText().lines()
            if (lines.last().isEmpty()) lines.dropLast(1) else lines
        }
        single = SingleLineInput()
        multi = MultiLineInput()
    }

    constructor(input: List<String>) {
        elements = input
        single = SingleLineInput()
        multi = MultiLineInput()
    }

    inner class SingleLineInput {

        val value: String = elements.first()

        fun asInts(separator: Char = ',') = elements.first().asInts(separator)

        fun asLongs(separator: Char = ',') = elements.first().asLongs(separator)

        fun split(separator: Char = ',') = elements.first().split(separator)

    }

    inner class MultiLineInput {

        fun asList(): List<String> = elements

        fun asSequence(): Sequence<String> = elements.asSequence()

        fun split(separator: String = ""): List<List<String>> = elements.split(separator)

        fun first(): String = elements.first()

        fun last(): String = elements.last()

    }

    private val elements: List<String>

    val single: SingleLineInput

    val multi: MultiLineInput

}
