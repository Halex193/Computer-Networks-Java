package udp

import utils.rightTriangle
import java.io.ByteArrayInputStream
import java.io.DataInputStream
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketException

fun main()
{
    var socket: DatagramSocket? = null
    try
    {
        socket = DatagramSocket(1921)
        println("Server started...")
        while (true)
        {
            val buffer = ByteArray(12)
            val packet = DatagramPacket(buffer, buffer.size)
            socket.receive(packet)
            val address = packet.address.hostAddress
            val port = packet.port

            val input = DataInputStream(ByteArrayInputStream(packet.data))
            val a = input.readInt()
            val b = input.readInt()
            val c = input.readInt()
            input.close()

            val isTriangle = rightTriangle(a.toDouble(), b.toDouble(), c.toDouble())
            println("Processed values: $a, $b, $c -> $isTriangle | $address - $port")

            val newBuffer = ByteArray(1)
            newBuffer[0] = if (isTriangle) 1 else 0
            packet.data = newBuffer
            socket.send(packet)
        }
    } catch (e: SocketException)
    {
        println("Socket cannot be bound on specified port")
    } catch (e: IOException)
    {
        println("Input/Output error")
    } finally
    {
        socket?.close()
    }


}