package com.example.piedrapapeltijeraslagartospok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentContainerView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), Comunicador {
    var marcadorCpu = 0
    var marcadorUser = 0
    var imgUserRes: Int = 0
    var imgCpuRes: Int = 0
    var maxPuntuacion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Genero la transaccion para cambiar el Fragment que se mostrará
        val transaction = supportFragmentManager.beginTransaction()


        //Se mostrará el Fragment Inicio
        val inicio = InicioFragment()
        transaction.add(R.id.containerPrincipal, inicio)

        transaction.commit()

    }

    override fun onCLickReiniciar() {
        val inicio = InicioFragment()
        val transaccion = supportFragmentManager.beginTransaction()
        transaccion.replace(R.id.containerPrincipal, inicio)
        marcadorCpu = 0
         marcadorUser = 0
         imgUserRes = 0
         imgCpuRes = 0
        transaccion.commit()
    }


    override fun onClickInicioCinco() {
       maxPuntuacion = 5
        onClickInicio()
    }

    override fun onClickInicioSiete() {
        maxPuntuacion = 7
        onClickInicio()
    }

    override fun onClickInicioTres(){
        maxPuntuacion = 3
        onClickInicio()
    }
    override fun onClickInicio() {
        val juego = GameFragment()
        //Genero la transaccion para cambiar el Fragment que se mostrará
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerPrincipal, juego)

        transaction.commit()
    }

    private fun marcador() {
        when (imgUserRes) {
            Armas.PIEDRA.resourceID -> {
                when (imgCpuRes) {
                    Armas.PAPEL.resourceID -> {
                        marcadorCpu += 1
                    }
                    Armas.TIJERAS.resourceID -> {
                        marcadorUser += 1
                    }
                    Armas.LAGARTO.resourceID -> {
                        marcadorUser += 1
                    }
                    Armas.SPOK.resourceID -> {
                        marcadorCpu += 1
                    }
                }
            }
            Armas.PAPEL.resourceID -> {
                when (imgCpuRes) {
                    Armas.PIEDRA.resourceID -> {
                        marcadorUser += 1
                    }
                    Armas.TIJERAS.resourceID -> {
                        marcadorCpu += 1
                    }
                    Armas.LAGARTO.resourceID -> {
                        marcadorCpu += 1
                    }
                    Armas.SPOK.resourceID -> {
                        marcadorUser += 1
                    }
                }
            }
            Armas.TIJERAS.resourceID -> {
                when (imgCpuRes) {
                    Armas.PAPEL.resourceID -> {
                        marcadorUser += 1
                    }
                    Armas.PIEDRA.resourceID -> {
                        marcadorCpu += 1
                    }
                    Armas.LAGARTO.resourceID -> {
                        marcadorUser += 1
                    }
                    Armas.SPOK.resourceID -> {
                        marcadorCpu += 1
                    }
                }
            }
            Armas.LAGARTO.resourceID -> {
                when (imgCpuRes) {
                    Armas.PAPEL.resourceID -> {
                        marcadorUser += 1
                    }
                    Armas.TIJERAS.resourceID -> {
                        marcadorCpu += 1
                    }
                    Armas.PIEDRA.resourceID -> {
                        marcadorCpu += 1
                    }
                    Armas.SPOK.resourceID -> {
                        marcadorUser += 1
                    }
                }
            }
            Armas.SPOK.resourceID -> {
                when (imgCpuRes) {
                    Armas.PAPEL.resourceID -> {
                        marcadorCpu += 1
                    }
                    Armas.TIJERAS.resourceID -> {
                        marcadorUser += 1
                    }
                    Armas.LAGARTO.resourceID -> {
                        marcadorCpu += 1
                    }
                    Armas.PIEDRA.resourceID -> {
                        marcadorUser += 1
                    }
                }
            }
        }
    }

    fun cambioImagenCpu() {
        //Recojo las variables de los fragmentos que voy a utilizar
        var txtMarcadorCpu: TextView = findViewById(R.id.txtMarcCPU)
        var txtMarcadorUser: TextView = findViewById(R.id.txtMarcUser)
        var random = Random.nextInt(Armas.values().size)
        var arma: Armas = Armas.values().get(random)
        var imgCpu: ImageView = findViewById(R.id.imgCPU)
        var imgUser : ImageView = findViewById(R.id.imgUser)
        var winner = findViewById<ImageView>(R.id.imgWinner)
        var txtWinner = findViewById<TextView>(R.id.txtWinner)

        //Establezco cuando ganará uno de los dos
        if(marcadorCpu == maxPuntuacion || marcadorUser == maxPuntuacion){


            if(marcadorCpu > marcadorUser) {
                txtWinner.text = "CPU"
            }else{
                txtWinner.text = "USUARIO"
            }

            //Quito de la pantalla tódo lo que no se debe ver en ese momento
            //Y pongo la imagen de la copa ademas del nombre del ganador
            winner.visibility = VISIBLE
            txtMarcadorCpu.visibility = GONE
            txtMarcadorUser.visibility = GONE
            imgCpu.visibility = GONE
            imgUser.visibility = GONE
            txtWinner.visibility = VISIBLE
            findViewById<Button>(R.id.btnLagarto).isVisible= false
            findViewById<Button>(R.id.btnSpok).isVisible = false
            findViewById<Button>(R.id.btnTijeras).isVisible = false
            findViewById<Button>(R.id.btnPapel).isVisible = false
            findViewById<Button>(R.id.btnPiedra).isVisible = false
            findViewById<Button>(R.id.txtVs).isVisible = false
        }else {
            //Cambiamos la imagen del cpu que tenemos guardada
            imgCpuRes = arma.resourceID
            //Establecemos la imamgen en el ImageView del CPU
            imgCpu.setImageResource(imgCpuRes)
            //Actualizamos el marcador
            marcador()
            //Establecemos el marcador para el cpu y el usuario
            txtMarcadorCpu.text = marcadorCpu.toString()
            txtMarcadorUser.text = marcadorUser.toString()

        }

    }

    override fun onClickPiedra() {
        var imgUser = findViewById<ImageView>(R.id.imgUser)
        imgUser.setImageResource(Armas.PIEDRA.resourceID)
        imgUserRes = Armas.PIEDRA.resourceID
        cambioImagenCpu()

    }

    override fun onClickPapel() {
        var imgUser = findViewById<ImageView>(R.id.imgUser)
        imgUser.setImageResource(Armas.PAPEL.resourceID)
        imgUserRes = Armas.PAPEL.resourceID
        cambioImagenCpu()
    }

    override fun onClickTijeras() {
        var imgUser = findViewById<ImageView>(R.id.imgUser)
        imgUser.setImageResource(Armas.TIJERAS.resourceID)
        imgUserRes = Armas.TIJERAS.resourceID
        cambioImagenCpu()
    }

    override fun onClickLagarto() {
        var imgUser = findViewById<ImageView>(R.id.imgUser)
        imgUser.setImageResource(Armas.LAGARTO.resourceID)
        imgUserRes = Armas.LAGARTO.resourceID
        cambioImagenCpu()
    }

    override fun onClickSpok() {
        var imgUser = findViewById<ImageView>(R.id.imgUser)
        imgUser.setImageResource(Armas.SPOK.resourceID)
        imgUserRes = Armas.SPOK.resourceID
        cambioImagenCpu()
    }


}