package jp.nicovideo.account.androidvib


enum class Note(val value: Double) {
    Whole(4.0),
    Half(2.0),
    Quarter(1.0),
    Eighth(0.5),
    Sixteenth(0.25)
}

class PulseController() {
    fun start(bpm: Int, note: Note, onPulse: () -> Unit) {
        Thread(Runnable {
            val interval = (60 / bpm * note.value * 1000 * 1000 * 1000).toLong()
            val startTime = System.nanoTime()

            var lapTime = startTime

            for (i in 0..16) {
                onPulse()
                lapTime += interval
                var endTime: Long
                do {
                    endTime = System.nanoTime()
                    try {
                        Thread.sleep(10)
                    } catch (e: InterruptedException) {
                        break
                    }

                } while (lapTime >= endTime)
            }
        }).start()
    }

    fun stop() {

    }

}
