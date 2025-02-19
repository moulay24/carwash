package com.example.carwash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val messages = mutableListOf<String>()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurer le RecyclerView
        adapter = MessageAdapter(messages)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Gérer le bouton d'envoi
        buttonSend.setOnClickListener {
            val message = editMessage.text.toString().trim()
            if (message.isNotEmpty()) {
                messages.add(message)
                adapter.notifyItemInserted(messages.size - 1)
                recyclerView.scrollToPosition(messages.size - 1)
                editMessage.text.clear()
            } else {
                Toast.makeText(this, "Le message est vide !", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

