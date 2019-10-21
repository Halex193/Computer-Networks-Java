package multithreaded.tasks

import multithreaded.ServerTask
import java.net.Socket
import kotlin.math.pow

class TriangleTask(connection: Socket) :  ServerTask(connection)
{
    override fun executeTask()
    {
        val a = input.readInt()
        val b = input.readInt()
        val c = input.readInt()
        val isTriangle = triangle(a.toDouble(), b.toDouble(), c.toDouble())
        println("Received values: $a, $b, $c -> $isTriangle - $peerAddress")
        output.writeBoolean(isTriangle)
    }

    companion object
    {
        fun triangle(a: Double, b: Double, c: Double): Boolean
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
    }
}