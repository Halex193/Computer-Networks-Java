import java.io.DataInputStream
import java.io.InputStreamReader
import java.net.DatagramPacket
import java.net.DatagramSocket

fun main()
{
    val socket = DatagramSocket(1921)
    val buffer = ByteArray(256)
    val packet = DatagramPacket(buffer, buffer.size)
    socket.receive(packet)
    val message = String(packet.data.copyOf(packet.length))
    println("Message: '$message' from ${packet.address} - ${packet.port}")
}