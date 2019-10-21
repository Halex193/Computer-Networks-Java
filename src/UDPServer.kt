import multithreaded.tasks.TriangleTask.Companion.triangle
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
        while (true)
        {
            val receivedPack = receiveInt(socket)
            val address = receivedPack.address.hostAddress
            val port = receivedPack.port
            val a = receivedPack.value
            val b = receiveInt(socket).value
            val c = receiveInt(socket).value

            val isTriangle = triangle(a.toDouble(), b.toDouble(), c.toDouble())
            println("Received values: $a, $b, $c -> $isTriangle - $address, $port")
            sendBoolean(socket, address, port, isTriangle)
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

fun sendBoolean(socket: DatagramSocket, address: String?, port: Int, bool: Boolean)
{
    val buffer = ByteArray(1)
    buffer[0] = if (bool) 1 else 0
    val packet = DatagramPacket(buffer, buffer.size)
    packet.address = InetAddress.getByName(address)
    packet.port = port
    socket.send(packet)
}

data class Pack(val value: Int, val address: InetAddress, val port: Int)

fun receiveInt(socket: DatagramSocket): Pack
{
    val buffer = ByteArray(256)
    val packet = DatagramPacket(buffer, buffer.size)
    socket.receive(packet)
    val input = DataInputStream(packet.data.inputStream())
    val value = input.readInt()
    input.close()
    return Pack(value, packet.address, packet.port)
}