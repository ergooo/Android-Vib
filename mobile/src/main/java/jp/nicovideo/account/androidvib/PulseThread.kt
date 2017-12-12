package jp.nicovideo.account.androidvib


class PulseThread(private val bpm: Int, private val note: Note, private val onPulse: () -> Unit) {
    private var thread: Thread? = null
    private var isRunning: Boolean = false

    fun start() {
        synchronized(isRunning){
            if(isRunning) return
        }
        isRunning = true
        thread = Thread(Runnable {
            val interval = (60 / bpm * note.value * 1000 * 1000 * 1000).toLong()
            val startTime = System.nanoTime()

            var lapTime = startTime

            while (isRunning) {

                onPulse()
                lapTime += interval
                var endTime: Long
                do {
                    endTime = System.nanoTime()
                    try {
                        Thread.sleep(1)
                    } catch (e: InterruptedException) {
                        break
                    }

                } while (lapTime >= endTime)
            }
        })
        thread?.start()
    }

    fun stop() {
        synchronized(isRunning){
            isRunning = false
        }
    }
}
