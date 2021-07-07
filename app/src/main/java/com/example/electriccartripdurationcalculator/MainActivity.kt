package com.example.electriccartripdurationcalculator

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.os.LocaleList
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var calculate: Button
    var inputTestOne = false
    var inputTestTwo = false
    var inputTestThree = false
    var inputTestFalse = false
    lateinit var auto: String
    lateinit var tot: String
    lateinit var speed: String
    lateinit var charge: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadLocale()
        val autonomy = findViewById<EditText>(R.id.autonomy)
        val total_distance = findViewById<EditText>(R.id.totaldistance)
        val average_spped = findViewById<EditText>(R.id.speedaverege)
        var chargetime = findViewById<EditText>(R.id.car_charge_time)
        val changeLangButton: Button = findViewById(R.id.buttonChangeLang)
        val copyriths: Button = findViewById(R.id.copyrithes)
        val mytest =
            "Share with us your Feedback, so we can make the In Time application better.\n" +
                    "Send your feedback to this E-mail:\n" +
                    "Suppot@DDEBIT.com\n" +
                    "Or call us simply on this number: +49 178 30100506\n" +
                    "Monday: 08h00 to 16h00\n" +
                    "Tuesday: 08h00 to 16h00\n" +
                    "Wednesday: 08h00 to 16h00\n" +
                    "Thursday: 08h00 to 16h00\n" +
                    "Friday: 08h00 to 16h00\n" +
                    "Saturday: 08h00 to 16h00\n"
        val mytest2 =
            "Teilen Sie uns Ihr Feedback mit, damit wir die In Time App für Sie verbessern können.\n" +
                    "Ihr Feedback an diese E-mail schicken:\n" +
                    "Suppot@DDEBIT.com\n" +
                    "Oder rufen Sie uns einfach unter der Telefonnummer: +49 178 30100506\n" +
                    "In den folgenden Sprechzeiten:\n" +
                    "Montag: 08h00 to 16h00\n" +
                    "Dienstag: 08h00 to 16h00\n" +
                    "Mittwoch: 08h00 to 16h00\n" +
                    "Donnerstag: 08h00 to 16h00\n" +
                    "Freitag: 08h00 to 16h00\n" +
                    "Samstag: 08h00 to 16h00\n"



            copyriths.setOnClickListener {
                if (Locale.getDefault().language.equals("de")){
                    withCustomStyle1(it, mytest2)

                }
                else{
                    withCustomStyle1(it, mytest)
                }


            }






        changeLangButton.setOnClickListener {
            val languages = arrayOf("English", "Deutsch")
            val langSelectorBuilder = AlertDialog.Builder(this@MainActivity)
            langSelectorBuilder.setTitle("Select your language  / Wähle deine Sprache : ")
            langSelectorBuilder.setSingleChoiceItems(languages, -1) { dialog, selection ->
                when (selection) {
                    0 -> {
                        setLocale("en")

                    }
                    1 -> {
                        setLocale("de")

                    }

                }
                recreate()
                dialog.dismiss()
            }
            langSelectorBuilder.create().show()
        }
        calculate = findViewById(R.id.angry_btn)
        autonomy.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                (s.toString().isNotEmpty()).also { inputTestOne = it }
                if (s.toString().isEmpty()) {
                    autonomy.setError(getString(R.string.validationerror))
                } else {
                    auto = autonomy.text.toString()
                    autonomy.setTextColor(Color.parseColor("#32CD32"))
                }

            }
        })

        total_distance.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


            override fun afterTextChanged(s: Editable?) {
                (s.toString().isNotEmpty()).also { inputTestTwo = it }
                if (s.toString().isEmpty()) {
                    total_distance.setError(getString(R.string.validationerror))
                } else {
                    tot = total_distance.text.toString()
                    total_distance.setTextColor(Color.parseColor("#32CD32"))
                }

            }
        })

        average_spped.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


            @SuppressLint("ResourceAsColor")
            override fun afterTextChanged(s: Editable?) {
                (s.toString().isNotEmpty()).also { inputTestThree = it }
                if (s.toString().isEmpty()) {
                    average_spped.setError(getString(R.string.validationerror))
                } else {
                    speed = average_spped.text.toString()
                    average_spped.setTextColor(Color.parseColor("#32CD32"))
                }


            }
        })

        chargetime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


            @SuppressLint("ResourceAsColor")
            override fun afterTextChanged(s: Editable?) {
                (s.toString().isNotEmpty()).also { inputTestFalse = it }
                if (s.toString().isEmpty()) {
                    chargetime.setError(getString(R.string.validationerror))
                } else {
                    charge = chargetime.text.toString()
                    chargetime.setTextColor(Color.parseColor("#32CD32"))
                }


            }
        })


        calculate.setOnClickListener {
            if (inputTestThree && inputTestTwo && inputTestOne && inputTestFalse) {
                var AutonomyValue: Float = autonomy.text.toString().toFloat()
                var TotalDistance: Float = total_distance.text.toString().toFloat()
                var Speed: Float = average_spped.text.toString().toFloat()
                var TimeToCharging: Float = chargetime.text.toString().toFloat()
                var Result1: String = " "
                var Result2: String = " "
                var KmPerMinute: Float = Speed / 60
                var Counter: Int = 0
                if (TotalDistance <= AutonomyValue) {
                    Result1 =
                        BigDecimal(((TotalDistance / KmPerMinute).toDouble()) / 60).setScale(2, RoundingMode.HALF_EVEN).toString()

                    Result2 =
                        BigDecimal(((TotalDistance / KmPerMinute).toDouble()) / 60).setScale(2, RoundingMode.HALF_EVEN).toString()

                    if(Locale.getDefault().language.equals("de")){
                        withCustomStyle(it, " $Result2 stunden und  $Counter pause !")
                    } else {
                        withCustomStyle(it, " $Result1 Hours includes  $Counter Breaks !")
                    }

                } else {
                    var r1 = TotalDistance / Speed.toDouble()
                    var r2 = ceil( (TotalDistance / AutonomyValue.toDouble()) - 1)
                    Result1 = BigDecimal(r1 + r2 * TimeToCharging).setScale(2, RoundingMode.HALF_EVEN).toString()

                    Result2 = r2.toInt().toString()
                    if(Locale.getDefault().language.equals("de")){
                        withCustomStyle(it, " $Result1 stunden und  $Result2 pause !")
                    } else {
                        withCustomStyle(it, " $Result1 Hours includes  $Result2 Breaks !")
                    }

                }
            } else {
                Toast(this).showCustomToast(
                    "Please Check the inputs !  ",
                    this
                )
            }
        }

    }


    private fun setLocale(localeToSet: String) {
        val localeListToSet = LocaleList(Locale(localeToSet))
        LocaleList.setDefault(localeListToSet)
        resources.configuration.setLocales(localeListToSet)
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
        val sharedPref = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        sharedPref.putString("locale_to_set", localeToSet)
        sharedPref.apply()
    }

    private fun loadLocale() {
        val sharedPref = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val localeToSet: String = sharedPref.getString("locale_to_set", "")!!
        setLocale(localeToSet)
    }

    private fun withCustomStyle(view: View, message: String) {
        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
        with(builder)
        {
            setTitle(getString(R.string.alert_title))
            setMessage(message)
            show()
        }
    }

    private fun withCustomStyle1(view: View, message: String) {
        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
        with(builder)
        {
            setMessage(message)
            show()
        }
    }
}

fun Toast.showCustomToast(message: String, activity: Activity) {
    val layout = activity.layoutInflater.inflate(
        R.layout.toast,
        activity.findViewById(R.id.toast_container)
    )
    // set the text of the TextView of the message
    val textView = layout.findViewById<TextView>(R.id.toast_text)
    textView.text = message
    // use the application extension function
    this.apply {
        setGravity(Gravity.BOTTOM, 0, 40)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}




