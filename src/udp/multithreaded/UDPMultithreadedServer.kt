package udp.multithreaded

import udp.multithreaded.tasks.TriangleTask
import java.io.IOException
import java.net.DatagramSocket
import java.net.SocketException
import java.util.concurrent.Executors

fun main()
{
    var socket: DatagramSocket? = null
    try
    {
        socket = DatagramSocket(1921)
        println("Server started...")
        val executor = Executors.newFixedThreadPool(5)
        while (true)
        {
            val task: ServerTask = TriangleTask()
            socket.receive(task.packet)
//            println("Client send packet: ${task.packet.address.hostAddress} - ${task.packet.port}")
            executor.execute(task)
        }
    } catch (e: SocketException)
    {
        System.err.println("Socket cannot be bound on specified port")
    } catch (e: IOException)
    {
        System.err.println("Input/Output error")
    } finally
    {
        socket?.close()
    }


}