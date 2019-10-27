package tcp.multithreaded.tasks

import tcp.multithreaded.ServerTask
import java.net.Socket

class ConversionTestTask(connection: Socket) : ServerTask(connection)
{
    override fun executeTask()
    {
        input.readUnsignedShort().let {
            println(it)
            println(peerAddress)
        }
    }
}