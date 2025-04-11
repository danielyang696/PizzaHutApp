package ntub.imd.pizzahutapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText // 確保導入 EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class StoreListActivity : AppCompatActivity() {

    private lateinit var storeListView: ListView
    private lateinit var btnAddStore: Button
    private val storeList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_list)

        // 初始化 UI 元件
        storeListView = findViewById(R.id.store_list)
        btnAddStore = findViewById(R.id.btn_add_store)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, storeList)
        storeListView.adapter = adapter

        btnAddStore.setOnClickListener {
            showAddStoreDialog()
        }

        storeListView.setOnItemClickListener { _, _, position, _ ->
            val store = storeList[position]
            val phone = store.split(" - ")[1]
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = android.net.Uri.parse("tel:$phone")
            }
            startActivity(intent)
        }

        storeListView.setOnItemLongClickListener { _, _, position, _ ->
            showUpdateStoreDialog(position)
            true
        }
    }

    private fun showAddStoreDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_store, null)
        val editStoreName = dialogView.findViewById<EditText>(R.id.edit_store_name)
        val editStorePhone = dialogView.findViewById<EditText>(R.id.edit_store_phone)

        AlertDialog.Builder(this)
            .setTitle("新增店家")
            .setView(dialogView)
            .setPositiveButton("新增") { _, _ ->
                val name = editStoreName.text.toString()
                val phone = editStorePhone.text.toString()
                storeList.add("$name - $phone")
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("取消", null)
            .show()
    }

    private fun showUpdateStoreDialog(position: Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_store, null)
        val editStoreName = dialogView.findViewById<EditText>(R.id.edit_store_name)
        val editStorePhone = dialogView.findViewById<EditText>(R.id.edit_store_phone)

        val storeData = storeList[position].split(" - ")
        editStoreName.setText(storeData[0])
        editStorePhone.setText(storeData[1])

        AlertDialog.Builder(this)
            .setTitle("更新店家")
            .setView(dialogView)
            .setPositiveButton("更新") { _, _ ->
                val name = editStoreName.text.toString()
                val phone = editStorePhone.text.toString()
                storeList[position] = "$name - $phone"
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("取消", null)
            .show()
    }
}