package com.example.piedrapapeltijeraslagartospok

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton


class UserFragment : Fragment() {

private var listener:Comunicador? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Comunicador){
            listener = context
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var viewRoot = inflater.inflate(R.layout.fragment_user, container, false)
        viewRoot.findViewById<ImageButton>(R.id.btnPiedra).setOnClickListener {listener?.onClickPiedra()}
        viewRoot.findViewById<ImageButton>(R.id.btnPapel).setOnClickListener {listener?.onClickPapel()}
        viewRoot.findViewById<ImageButton>(R.id.btnLagarto).setOnClickListener {listener?.onClickLagarto()}
        viewRoot.findViewById<ImageButton>(R.id.btnTijeras).setOnClickListener {listener?.onClickTijeras()}
        viewRoot.findViewById<ImageButton>(R.id.btnSpok).setOnClickListener {listener?.onClickSpok()}

        return viewRoot
    }

}