package Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Calendar

/**
 * [GardenPlanting] represents when a user adds a [Plant] to their garden, with useful metadata.
 * Properties such as [lastWateringDate] are used for notifications (such as when to water the
 * plant).
 *
 * Declaring the column info allows for the renaming of variables without implementing a
 * database migration, as the column name would not change.
 */
@Entity(
    tableName = "timer",
    indices = [Index("timer_id")]
)
data class TimerItem(

    /**
     * Indicates when the [Plant] was planted. Used for showing notification when it's time
     * to harvest the plant.
     */
    @ColumnInfo(name = "timer_hour") val timerHour: Int,

    /**
     * Indicates when the [Plant] was last watered. Used for showing notification when it's
     * time to water the plant.
     */
    @ColumnInfo(name = "timer_minute") val timerMin: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "timer_id")
    var timerItenId: Long = 0
}