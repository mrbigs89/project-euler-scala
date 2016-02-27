package it.mrbigs.projecteuler

import scala.collection.mutable

/**
  * Created by simone on 2/13/16.
  */
object Problem44 extends App {

  val pentagonals = mutable.Set.empty[Long]
  var of = Long.MaxValue

  val (pibest, pjbest) = Stream.from(1)
    .flatMap(i => (1 to i).toStream.map(j => {
      (pentagonal(i), pentagonal(j))
    })).filter { case (pi, pj) => (pi - pj).isPentagonal && (pi + pj).isPentagonal }
    .takeWhile { case (pi, pj) =>
      val oldOf = of
      of = pi - pj
      println(s"$pi, $pj -> $of")

      of < oldOf
    }.last
  var maxPentagonal = 0L
  var maxIndex = 0

  implicit class LongIsPentagonal(n: Long) {
    def isPentagonal: Boolean = if (pentagonals.contains(n)) {
      true
    } else {
      while (maxPentagonal < n) {
        maxIndex += 1
        val pent = pentagonal(maxIndex)
        pentagonals.add(pent)
        maxPentagonal = pent
      }
      maxPentagonal == n
    }
  }

  println(pibest - pjbest)
}
