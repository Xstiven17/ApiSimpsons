package com.example.apithesimpsons.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apithesimpsons.R
import com.example.apithesimpsons.databinding.ActivityMainBinding
import com.example.apithesimpsons.viewmodels.MainViewModel
import com.example.apithesimpsons.views.adapters.PersonajeAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PersonajeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider (this)[MainViewModel::class.java]

        setupRecyclerView()

        viewModel.obtenerPersonajes()

        viewModel.listaPersonajes.observe(this) {
            adapter.listaPersonajes = it
            adapter.notifyDataSetChanged()
        }

        binding.tilBuscar.setEndIconOnClickListener {
            if (binding.tietBuscar.text.toString() == "") {
                viewModel.obtenerPersonajes()
            } else {
                viewModel.obtenerPersonaje(binding.tietBuscar.text.toString().trim())
            }
        }
    }

    fun setupRecyclerView() {
        binding.rvPersonajes.layoutManager = GridLayoutManager(this, 3)
        adapter = PersonajeAdapter(this, arrayListOf())
        binding.rvPersonajes.adapter = adapter
    }
}