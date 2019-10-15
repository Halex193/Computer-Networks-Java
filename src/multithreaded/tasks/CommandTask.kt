package multithreaded.tasks

import multithreaded.ServerTask
import java.io.IOException
import java.net.Socket
import java.io.InputStreamReader


class CommandTask(connection: Socket) : ServerTask(connection)
{
    override fun executeTask()
    {
        val size = input.readShort()
        val command = input.readNBytes(size.toInt())
        val stringCommand = String(command)

        try
        {
            val process = Runtime.getRuntime().exec(stringCommand)

            InputStreamReader(process.inputStream).apply {
                forEachLine { line ->
                    output.writeShort(line.length)
                    output.writeBytes(line)
                }
            }
            output.writeShort(-1)


            val exitCode = process.waitFor()
            output.writeShort(exitCode)
            println("Client task executed: ${connection.inetAddress.hostAddress} - ${connection.port} - $stringCommand")
        } catch (exception: IOException)
        {
            System.err.println("Command failed for client: ${connection.inetAddress.hostAddress} - ${connection.port} - $stringCommand")
            output.writeShort(-1)
            output.writeShort(0)
        }


    }
}