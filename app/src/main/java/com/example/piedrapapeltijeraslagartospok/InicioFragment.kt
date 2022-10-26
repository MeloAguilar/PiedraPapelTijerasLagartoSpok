package com.example.piedrapapeltijeraslagartospok

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class InicioFragment : Fragment() {

    private var listener:Comunicador? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Comunicador){
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var viewroot = inflater.inflate(R.layout.fragment_inicio, container, false)
        viewroot.findViewById<Button>(R.id.btnMejorDeTres).setOnClickListener{ listener?.onClickInicioTres() }
        viewroot.findViewById<Button>(R.id.btnMejorDeCinco).setOnClickListener { listener?.onClickInicioCinco() }
        viewroot.findViewById<Button>(R.id.btnMejorDeSiete).setOnClickListener { listener?.onClickInicioSiete() }


        return viewroot
    }

}