package udp.multithreaded

import java.io.IOException
import java.net.DatagramPacket
import java.net.SocketException
import java.nio.ByteBuffer

abstract class ServerTask(capacity: Int) : Runnable
{
    val packet: DatagramPacket

    init
    {
        val buffer = ByteBuffer.allocate(capacity).array()
        packet = DatagramPacket(buffer, buffer.size)
    }

    override fun run()
    {
        try
        {
            executeTask()
        } catch (e: SocketException)
        {
            System.err.println("An error occurred with the socket")
        } catch (e: IOException)
        {
            System.err.println("Input/Output error")
        }
    }

    abstract fun executeTask()
}