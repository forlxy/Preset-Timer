package Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * The Data Access Object for the Plant class.
 */
@Dao
interface TimerItemDAO {
    @Query("SELECT * FROM timer ORDER BY timer_id")
    fun getTimers(): LiveData<List<TimerItem>>

    @Query("SELECT * FROM timer WHERE timer_hour = :timerHour AND timer_minute = :timerMin")
    fun getTimer(timerHour: Int, timerMin: Int): LiveData<TimerItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(plants: List<TimerItem>)
}
