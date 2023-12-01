package com.github.christopheml.aoc2022.common

fun IntRange.fullyOverlaps(other: IntRange): Boolean =
    this.contains(other.first) && this.contains(other.last)

fun IntRange.overlaps(other: IntRange): Boolean =
    this.first in other.first .. other.last || this.last in other.first .. other.last ||
            other.first in this.first .. this.last || other.last in this.first .. this.last
