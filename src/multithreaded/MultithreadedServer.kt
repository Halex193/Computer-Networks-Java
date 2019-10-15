package multithreaded

import multithreaded.tasks.CommandTask
import multithreaded.tasks.NumbersTask
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors

fun main(args: Array<String>)
{
    startServer(1921) { connection -> CommandTask(connection) }
}

@Suppress("SameParameterValue")
private fun startServer(port: Int, newTask: (Socket) -> ServerTask)
{
    try
    {
        ServerSocket(port).use {
            println("Server started...")
            val executor = Executors.newFixedThreadPool(5)
            while (true)
            {
                val connection = it.accept()
                println("Client connected: ${connection.inetAddress.hostAddress} - ${connection.port}")
                executor.execute(newTask(connection))
            }
        }
    } catch (e: IOException)
    {
        System.err.println("Port unavailable")
    }
}