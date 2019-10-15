package multithreaded

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InterruptedIOException
import java.net.Socket

abstract class ServerTask(val connection: Socket) : Runnable
{
    protected val input = DataInputStream(connection.getInputStream())
    protected val output = DataOutputStream(connection.getOutputStream())

    override fun run()
    {
        try
        {
            executeTask()
        }
        catch (e: InterruptedIOException)
        {
            error("Connection interrupted")
        }
        finally
        {
            connection.close()
        }
    }

    abstract fun executeTask()
}