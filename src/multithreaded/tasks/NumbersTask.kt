package multithreaded.tasks

import multithreaded.ServerTask
import java.net.Socket

/**
 * Reads a short and displays it
 */
class NumbersTask(connection: Socket): ServerTask(connection)
{
    override fun executeTask()
    {
        println(input.readShort())
    }
}