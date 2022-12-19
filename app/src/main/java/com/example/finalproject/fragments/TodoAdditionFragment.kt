package com.example.finalproject.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalproject.room.TodoEntity
import java.util.*
import android.app.*
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.view.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.finalproject.*
import com.example.finalproject.Notification
import com.example.finalproject.databinding.FragmentTodoAdditionBinding
import com.example.finalproject.room.LocalTodoDB
import java.text.SimpleDateFormat

class TodoAdditionFragment : Fragment() {

    private lateinit var binding: FragmentTodoAdditionBinding
    private var imageUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createNotificationChannel()
        setUpSaveButtonClickListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoAdditionBinding.inflate(layoutInflater)
        return binding.root
    }

    fun setUpSaveButtonClickListener() {
        binding.saveButton.setOnClickListener {
            val title = binding.todoTitle.text.toString()
            val steps = binding.todoAdditionSteps.text.toString()
            if (title.isEmpty()) {
                Toast.makeText(
                    this.context,
                    "Please enter TODO Title",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm")
                val dateString = simpleDateFormat.format(getTime())
                LocalTodoDB.getInstance(requireContext()).getTodoDAO().insertTodos(
                    TodoEntity(
                        author = "",
                        title = title,
                        contents = steps,
                        imageName = dateString
                    )
                )
                scheduleNotification()
                findNavController().navigate(R.id.fragment_listView)
            }
        }
    }

    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager = activity?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun scheduleNotification() {
        val intent = Intent(context, Notification::class.java)
        val title = binding.todoTitle.text.toString()
        val message = binding.todoAdditionSteps.text.toString()
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        showAlert(time, title, message)
    }

    private fun showAlert(time: Long, title: String, message: String) {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(context)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(context)

        AlertDialog.Builder(context)
            .setTitle("Notification Scheduled")
            .setMessage(
                "Title: " + title +
                        "\nMessage: " + message +
                        "\nAt: " + dateFormat.format(date) + " " + timeFormat.format(date))
            .setPositiveButton("Okay"){_,_ ->}
            .show()
    }

    private fun getTime(): Long {
        val minute = binding.timePicker.minute
        val hour = binding.timePicker.hour
        val day = binding.datePicker.dayOfMonth
        val month = binding.datePicker.month
        val year = binding.datePicker.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }
}
