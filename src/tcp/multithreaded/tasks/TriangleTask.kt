package tcp.multithreaded.tasks

import tcp.multithreaded.ServerTask
import utils.rightTriangle
import java.net.Socket

class TriangleTask(connection: Socket) :  ServerTask(connection)
{
    override fun executeTask()
    {
        val a = input.readInt()
        val b = input.readInt()
        val c = input.readInt()
        val isTriangle = rightTriangle(a.toDouble(), b.toDouble(), c.toDouble())
        println("Processed values: $a, $b, $c -> $isTriangle | $peerAddress")
        output.writeBoolean(isTriangle)
    }
}