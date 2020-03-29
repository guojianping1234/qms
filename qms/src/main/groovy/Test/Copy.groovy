package Test


import java.io.*

class Copy {

    def copy() {
        def outFile = new File(args[1])
        if (outFile.exists()) {
            def ans
            System.in.withReader {
                print "File ${args[1]} is already there, you wanna to overwrite it?(y/n) "
                ans = it.readLine()
                println "You choose >> $ans <<"
                if (it =~ /n|N/) {
                    println "Exit without changing anything..."
                    return
                }
            }

        }

        def printWriter = outFile.newPrintWriter()

        new File(args[0]).eachLine { line ->
            printWriter.println(line)
        }

        printWriter.flush()
        printWriter.close()
    }
}
