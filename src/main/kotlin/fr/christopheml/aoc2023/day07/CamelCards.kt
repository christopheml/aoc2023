package fr.christopheml.aoc2023.day07

import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.runners.Solution


enum class HandType { HighCard, OnePair, TwoPairs, ThreeOfAKind, FullHouse, FourOfAKind, FiveOfAKind }

class Hand(val cards: String, val bid: Int) {

    private fun cardCounts(cards: String) =
        cards.groupingBy { it }.eachCount().values.sortedDescending().joinToString("")

    val score = when (cardCounts(cards)) {
        "5" -> HandType.FiveOfAKind
        "41" -> HandType.FourOfAKind
        "32" -> HandType.FullHouse
        "311" -> HandType.ThreeOfAKind
        "221" -> HandType.TwoPairs
        "2111" -> HandType.OnePair
        else -> HandType.HighCard
    }

    val scoreWithJokers = run {
        if (cards.count { it == 'J' } == 0) score else {
            when (cardCounts(cards.filterNot { it == 'J' })) {
                "4", "3", "2", "1", "" -> HandType.FiveOfAKind
                "31", "21", "11" -> HandType.FourOfAKind
                "22" -> HandType.FullHouse
                "211", "111" -> HandType.ThreeOfAKind
                "1111" -> HandType.OnePair
                else -> throw IllegalStateException()
            }
        }
    }

}

class CamelCards : Solution<Int>(7) {
    private fun cardComparator(cardOrder: String, score: (hand: Hand) -> HandType): Comparator<Hand> =
        compareBy<Hand> { score.invoke(it) }
            .thenComparingInt { cardOrder.indexOf(it.cards[0]) }
            .thenComparingInt { cardOrder.indexOf(it.cards[1]) }
            .thenComparingInt { cardOrder.indexOf(it.cards[2]) }
            .thenComparingInt { cardOrder.indexOf(it.cards[3]) }
            .thenComparingInt { cardOrder.indexOf(it.cards[4]) }

    private fun totalScore(input: Input, comparator: Comparator<Hand>) =
        input.multi.asSequence()
            .map { it.split(' ') }
            .map { Hand(it[0], it[1].toInt()) }
            .sortedWith(comparator)
            .mapIndexed { index, hand -> (index + 1) * hand.bid }
            .sum()

    override fun partOne(input: Input) = totalScore(input, cardComparator("23456789TJQKA") { it.score })

    override fun partTwo(input: Input) = totalScore(input, cardComparator("J23456789TQKA") { it.scoreWithJokers })
}

fun main() {
    CamelCards().run()
}