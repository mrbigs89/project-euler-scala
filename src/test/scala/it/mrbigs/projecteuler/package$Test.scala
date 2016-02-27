package it.mrbigs.projecteuler

import org.scalatest.FunSuite

/**
  * Created by simone on 2/13/16.
  */
class package$Test extends FunSuite {
  test("isPrime") {
    assert(3.isPrime)
    assert(! 81.isPrime)
    assert(982451653.isPrime)

    assert(! (32452867 * 982451653).isPrime)
  }

  test("IterableToLong") {
    assert(10L == List(0, 1).toLong)
  }

  test("LongToList") {
    assert(List(1, 2, 3) == 321.toList)
    assert(321 == 321.toList.toLong)
  }

  test("nextPrime") {
    assert(5 == nextPrime(4))
    assert(5 == nextPrime(3))
  }

  test("toPrimeFactors") {
    assert(Map(3L -> 1, 11L -> 2) == (11*11*3).toPrimeFactors)
    assert(Map(19L -> 1) == 19.toPrimeFactors)
    assert(Map(3L -> 1, 11L -> 2, 982451653L -> 1) == (11*11*3*982451653L).toPrimeFactors)
  }

  test("primesBetween") {
    assert(List(2,3,5,7,11,13) == primesBetween(2, 14).toList)
  }
}
