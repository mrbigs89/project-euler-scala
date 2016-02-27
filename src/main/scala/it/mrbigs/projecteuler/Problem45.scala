package it.mrbigs.projecteuler

/**
  * Created by simone on 2/13/16.
  */
object Problem45 extends App{
  var maxT = 40755L
  var tIndex = 285L
  var maxP = 40755L
  var pIndex = 165L
  var maxH = 40755L
  var hIndex = 143L

  tIndex += 1
  maxT = triangular(tIndex)

  while (maxH != maxP || maxP != maxT && maxH > 0 && maxP > 0 && maxT > 0) {
    val p: Long = maxP
    val h: Long = maxH
    val t: Long = maxT
    val letter = Math.min(Math.min(p, h), t) match {
      case `p` =>
        pIndex += 1
        maxP = pentagonal(pIndex)
        "P"
      case `h` =>
        hIndex += 1
        maxH = hexagonal(hIndex)
        "H"
      case `t` =>
        tIndex += 1
        maxT = triangular(tIndex)
        "T"
    }
    println(List(maxH, maxP, maxT).mkString(", ") + "\t\t" +letter)
  }

  println(maxT)

}
