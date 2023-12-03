package fr.christopheml.aoc2023.common

import java.lang.IllegalStateException

class StringSplitter {

    abstract class Splitter(val separator: String)

    private class Single(separator: String) : Splitter(separator)

    private class Greedy(separator: String) : Splitter(separator)




    companion object {
        fun split(input: String, vararg splitters: Splitter) : List<String> {
            return iterator {
                var position = 0
                for (splitter in splitters) {
                    when (splitter) {
                        is Single -> {
                            val next = input.indexOf(splitter.separator, position)
                            if (next == -1) throw IllegalStateException()
                            yield(input.substring(position, next))
                            position = next + splitter.separator.length
                        }
                        is Greedy -> {
                            var next = input.indexOf(splitter.separator, position)
                            while (next != -1) {
                                yield(input.substring(position, next))
                                position = next + splitter.separator.length
                                next = input.indexOf(splitter.separator, position)
                            }
                        }
                    }
                }
                yield(input.substring(position))
            }.asSequence().toList()
        }

        fun one(separator: String) : Splitter = Single(separator)

        fun many(separator: String) : Splitter = Greedy(separator)

    }




}