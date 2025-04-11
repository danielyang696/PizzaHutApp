package ntub.imd.pizzahutapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainDishText: TextView
    private lateinit var sideDishText: TextView
    private lateinit var btnMainDish: Button
    private lateinit var btnSideDish: Button
    private lateinit var btnStoreInfo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化 UI 元件
        mainDishText = findViewById(R.id.main_dish_text)
        sideDishText = findViewById(R.id.side_dish_text)
        btnMainDish = findViewById(R.id.btn_main_dish)
        btnSideDish = findViewById(R.id.btn_side_dish)
        btnStoreInfo = findViewById(R.id.btn_store_info)

        // 點主餐按鈕
        btnMainDish.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            startActivityForResult(intent, 1)
        }

        // 點副餐按鈕
        btnSideDish.setOnClickListener {
            val intent = Intent(this, SideDishActivity::class.java)
            startActivityForResult(intent, 2)
        }

        // 店家訊息按鈕
        btnStoreInfo.setOnClickListener {
            val intent = Intent(this, StoreListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                1 -> {
                    // 更新主餐
                    val mainDish = data.getStringExtra("mainDish")
                    mainDishText.text = "主餐: $mainDish"
                }
                2 -> {
                    // 更新副餐
                    val sideDish = data.getStringExtra("sideDish")
                    sideDishText.text = "副餐: $sideDish"
                }
            }
        }
    }
}