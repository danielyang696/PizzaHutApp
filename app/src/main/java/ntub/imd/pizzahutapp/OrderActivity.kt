package ntub.imd.pizzahutapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // 初始化 UI 元件
        radioGroup = findViewById(R.id.radio_group_main_dish)
        btnSubmit = findViewById(R.id.btn_submit_order)

        btnSubmit.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val mainDish = selectedRadioButton.text.toString()
                val resultIntent = Intent()
                resultIntent.putExtra("mainDish", mainDish)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}