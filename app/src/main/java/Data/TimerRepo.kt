package Data


/**
 * Repository module for handling data operations.
 */
class TimerRepo private constructor(private val timerDao: TimerItemDAO) {

    fun getTimers() = timerDao.getTimers()

    fun getPlant(timerHour: Int, timerMin: Int) = timerDao.getTimer(timerHour, timerMin)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: TimerRepo? = null

        fun getInstance(timerDao: TimerItemDAO) =
            instance ?: synchronized(this) {
                instance ?: TimerRepo(timerDao).also { instance = it }
            }
    }
}
