package utils

import kotlin.math.pow

fun rightTriangle(a: Double, b: Double, c: Double): Boolean
{
    return when
    {
        c > b && c > a -> pythagorasTheorem(c, a, b)
        a > b && a > c -> pythagorasTheorem(a, b, c)
        b > a && b > c -> pythagorasTheorem(b, a, c)
        else -> false
    }
}

private fun pythagorasTheorem(c: Double, b: Double, a: Double): Boolean
{
    return c.pow(2) == a.pow(2) + b.pow(2)
}