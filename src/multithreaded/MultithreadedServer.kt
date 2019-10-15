package multithreaded

import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors

val PORT = 1921

fun main()
{
    fun newTask(connection: Socket): ServerTask = NumbersTask(connection)

    try
    {
        ServerSocket(PORT).use {
            println("Server started...")
            val executor = Executors.newFixedThreadPool(5)
            while (true)
            {
                val connection = it.accept()
                executor.execute(newTask(connection))
            }
        }
    } catch (e: IOException)
    {
        error("Port unavailable")
    }

}