package it.mrbigs

import scala.collection.Searching._
import scala.collection.immutable.Queue
import scala.collection.mutable

/**
  * Created by simone on 2/13/16.
  */
package object projecteuler {

  val innerPrimes = mutable.ArrayBuffer(2L)

  def innerPrimesSize = innerPrimes.length

  def initPrimes(limit: Long): Unit = nextPrime(limit)

  def nextPrime(n: Long) =
    innerPrimes.search(n) match {
      case Found(i) if innerPrimes.last > n =>
        innerPrimes(i + 1)
      case InsertionPoint(i) if innerPrimes.last > n =>
        innerPrimes(i)
      case _ =>
        Stream.range(innerPrimes.last + 1, Long.MaxValue)
          .filter(isPrimeNotOptimized)
          .map(p => {
            innerPrimes.append(p)
            p
          })
          .dropWhile(_ <= n)
          .head
    }

  private def isPrimeNotOptimized(n: Long) = !(2L to Math.sqrt(n).toLong).toStream.exists(n % _ == 0)

  def triangular(n: Int): Long = n * (n + 1) / 2

  def triangular(n: Long): Long = n * (n + 1) / 2

  def pentagonal(n: Int): Long = n * (3 * n - 1) / 2

  def pentagonal(n: Long): Long = n * (3 * n - 1) / 2

  def hexagonal(n: Int): Long = n * (2 * n - 1)

  def hexagonal(n: Long): Long = n * (2 * n - 1)

  def primesStream = primesFrom(2)

  def primesFrom(n: Long) = primesBetween(n, Long.MaxValue)

  def primesBetween(start: Long, end: Long): Stream[Long] = innerPrimes.dropWhile(_ < start).takeWhile(_ < end) ++: Stream.range(innerPrimes.last + 1, end).filter(_.isPrime)

  private def decomposeRec(factors: Map[Long, Int], n: Long, lastPrime: Long): Map[Long, Int] = {
    n match {
      case 1 => factors
      case _ =>
        primesFrom(lastPrime).dropWhile(p => n % p != 0 && p <= Math.sqrt(n)).head match {
          case p if n % p == 0 => decomposeRec(factors.updated(p, factors.getOrElse(p, 0) + 1), n / p, p)
          case _ => factors.updated(n, 1)
        }
    }
  }

  implicit class IntIsPrime(n: Int) {
    def isPrime: Boolean = n.toLong.isPrime
  }

  implicit class LongIsPrime(n: Long) {
    val sqrt = Math.sqrt(n)

    def isPrime: Boolean =
      if (n < innerPrimes.last) {
        innerPrimes.search(n) match {
          case Found(_) => true
          case _ => false
        }
      } else {
        nextPrime(n)
        isPrime
      }

  }

  implicit class IterableToLong(coll: Iterable[Int]) {
    def toLong: Long = coll.zipWithIndex
      .map { case (num, exp) => num * Math.pow(10, exp).toLong }
      .sum
  }

  implicit class LongToList(n: Long) {
    def toList: List[Int] = recToList(Queue.empty[Int], n).toList

    private def recToList(q: Queue[Int], num: Long): Queue[Int] = {
      if (num < 10) {
        q :+ num.toInt
      }
      else {
        recToList(q :+ (num % 10).toInt, num / 10)
      }
    }
  }

  implicit class IntToList(n: Int) {
    def toList: List[Int] = recToList(Queue.empty[Int], n).toList

    private def recToList(q: Queue[Int], num: Int): Queue[Int] = {
      if (num < 10) {
        q :+ num
      }
      else {
        recToList(q :+ (num % 10), num / 10)
      }
    }
  }

  implicit class LongToPrimeFactors(n: Long) {
    def toPrimeFactors: Map[Long, Int] = if (n.isPrime) Map(n -> 1) else decomposeRec(Map.empty[Long, Int], n, 2L)
  }

  implicit class IntToPrimeFactors(n: Int) {
    def toPrimeFactors: Map[Long, Int] = if (n.isPrime) Map(n.toLong -> 1) else decomposeRec(Map.empty[Long, Int], n, 2L)
  }

  implicit class LongNthDigit(n: Long) {
    def nThDigit(i: Int) = n % Math.pow(10, i)
  }
}
