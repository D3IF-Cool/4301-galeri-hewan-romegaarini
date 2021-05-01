package org.d3if0031.galerihewan.ui.main

import org.d3if0031.galerihewan.model.Hewan
import MainAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if0031.galerihewan.R
import org.d3if0031.galerihewan.databinding.FragmentMainBinding
import org.d3if0031.galerihewan.network.HewanApi

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var binding: FragmentMainBinding
    private lateinit var myAdapter: MainAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        myAdapter = MainAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                    DividerItemDecoration(
                            context,
                            RecyclerView.VERTICAL,
                    ),
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })

    }
    private fun updateProgress(status: HewanApi.ApiStatus) {
        when (status) {
            HewanApi.ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            HewanApi.ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            HewanApi.ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }

    // Biasanya kita mengambil data dari database, atau server.
    // Tapi karena materi belum sampai, kita buat dummy saja.
    private fun getData(): List<Hewan> {
        return listOf(
                Hewan("Angsa", "Cygnus olor", R.drawable.angsa),
                Hewan("Ayam", "Gallus gallus", R.drawable.ayam),
                Hewan("Bebek", "Cairina moschata", R.drawable.bebek),
                Hewan("Domba", "Ovis ammon", R.drawable.domba),
                Hewan("Kalkun", "Meleagris gallopavo", R.drawable.kalkun),
                Hewan("Kambing", "Capricornis sumatrensis", R.drawable.kambing),
                Hewan("Kelinci", "Oryctolagus cuniculus", R.drawable.kelinci),
                Hewan("Kerbau", "Bubalus bubalis", R.drawable.kerbau),
                Hewan("Kuda", "Equus caballus", R.drawable.kuda),
                Hewan("Sapi", "Bos taurus", R.drawable.sapi),
        )
    }
}