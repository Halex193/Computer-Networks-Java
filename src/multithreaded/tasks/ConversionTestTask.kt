package multithreaded.tasks

import multithreaded.ServerTask
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