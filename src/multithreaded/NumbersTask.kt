package multithreaded

import java.net.Socket

class NumbersTask(connection: Socket): ServerTask(connection)
{
    override fun executeTask()
    {
        print(input.readShort())
    }
}