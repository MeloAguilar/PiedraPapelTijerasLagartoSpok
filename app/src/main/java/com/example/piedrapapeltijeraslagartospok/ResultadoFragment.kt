package com.example.piedrapapeltijeraslagartospok

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView


class ResultadoFragment : Fragment() {


    private var listener:Comunicador?=null

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
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var viewroot = inflater.inflate(R.layout.fragment_resultado, container, false)
        var btnAtras = viewroot.findViewById<ImageButton>(R.id.btnReiniciar).setOnClickListener { listener?.onCLickReiniciar() }
        return viewroot
    }


}