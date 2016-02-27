package it.mrbigs.projecteuler

/**
  * Created by simone on 2/13/16.
  */
object Problem49 extends App {
  println(primesBetween(1000, 9999)
    .map(_.toList.permutations.toStream.map(_.toLong).filter(_ > 1000).filter(_.isPrime))
    .filter(l => l.size >= 3)
    .map(_.toSet)
    .distinct
    .map(set => {
      set.map(base => {
        val possibleDiffs = set.map(base - _).filter(_ != 0)
        base -> possibleDiffs.find(diff => set.contains(base - 2 * diff))
      }).filter(_._2.isDefined)
    })
    .filter(_.nonEmpty).toList
  )
}
