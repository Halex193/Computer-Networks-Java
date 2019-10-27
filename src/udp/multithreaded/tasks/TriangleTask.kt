package udp.multithreaded.tasks

import udp.multithreaded.ServerTask
import utils.rightTriangle
import java.net.DatagramSocket
import java.nio.ByteBuffer

class TriangleTask : ServerTask(capacity)
{
    companion object
    {
        const val capacity = 12
    }
    override fun executeTask()
    {
        val buffer = ByteBuffer.wrap(packet.data)
        val a = buffer.int
        val b = buffer.int
        val c = buffer.int

        val isTriangle = rightTriangle(a.toDouble(), b.toDouble(), c.toDouble())
        println("Processed values: $a, $b, $c -> $isTriangle | ${packet.address.hostAddress} - ${packet.port}")

        val byte = if (isTriangle) 1 else 0
        val newBuffer = ByteBuffer.allocate(1).put(byte.toByte())
        packet.data = newBuffer.array()
        DatagramSocket(0).send(packet)
    }


}