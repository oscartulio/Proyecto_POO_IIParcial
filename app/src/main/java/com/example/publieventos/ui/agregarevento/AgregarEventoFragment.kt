package com.example.publieventos.ui.agregarevento

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.publieventos.R

class AgregarEventoFragment : Fragment() {

    companion object {
        fun newInstance() = AgregarEventoFragment()
    }

    private lateinit var viewModel: AgregarEventoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.agregar_evento_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AgregarEventoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
