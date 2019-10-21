package multithreaded

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InterruptedIOException
import java.net.Socket

abstract class ServerTask(val connection: Socket) : Runnable
{
    protected val input = DataInputStream(connection.getInputStream())
    protected val output = DataOutputStream(connection.getOutputStream())
    protected val peerAddress get() = "${connection.inetAddress.hostAddress} - ${connection.port}"

    override fun run()
    {
        try
        {
            executeTask()
        }
        catch (e: InterruptedIOException)
        {
            System.err.println("Connection interrupted")
        }
        finally
        {
            input.close()
            output.close()
            connection.close()
        }
    }

    abstract fun executeTask()
}