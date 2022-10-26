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





    /**
     * Método que se encarga de reemplazar el Fragment que se encuentra
     * dentro de el FragmentContainerView containerPrincipal. Donde se encontraba el Fragment InicioFragment
     * ahora se encontrará el Fragment JuegoFragment
     */
    override fun onClickInicio(rondas:Int) {
        maxPuntuacion = rondas
        val juego = GameFragment()
        //Genero la transaccion para cambiar el Fragment que se mostrará
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerPrincipal, juego)

        transaction.commit()
    }


    /**
     * Método que aumenta marcadorUser o marcadorCPU segun el resultado de un número generado aleatoriamente
     * de entre las posibles elecciones del enum Armas.
     *
     */
    private fun marcador() {
        when (imgUserRes) {
            //Si el usuario elige Piedra
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
            //Si el usuario elige Papel
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
            //Si el usuario elige Tijeras
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
            //Si el usuario elige Lagarto
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
            //Si el usuario elige Spok
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
        //Si ha finalizado el juego
        if (marcadorCpu == maxPuntuacion || marcadorUser == maxPuntuacion) {
            mostrarGanador()
        }
    }


    /**
     * Método que se encarga de quitar la visibilidad a lo que se está mostrando en pantalla
     * y muestra una imagen que indica el ganador sobre la imagen de la ultima opcion que eligió
     */
    fun mostrarGanador() {
        var winner = findViewById<ImageView>(R.id.imgWinner)
        var winner2 = findViewById<ImageView>(R.id.imgWinner2)
        var txtWinner = findViewById<TextView>(R.id.txtWinner)
        //Establezco cuando ganará uno de los dos

        if (marcadorCpu > marcadorUser) {
            txtWinner.text = "CPU"
            winner.visibility = VISIBLE
        } else {
            txtWinner.text = "USUARIO"

            winner2.visibility = VISIBLE
        }

        //Quito de la pantalla tódo lo que no se debe ver en ese momento
        //Y pongo la imagen de la copa ademas del nombre del ganador
        findViewById<Button>(R.id.btnLagarto).isVisible = false
        findViewById<Button>(R.id.btnSpok).isVisible = false
        findViewById<Button>(R.id.btnTijeras).isVisible = false
        findViewById<Button>(R.id.btnPapel).isVisible = false
        findViewById<Button>(R.id.btnPiedra).isVisible = false
        findViewById<Button>(R.id.txtVs).isVisible = false
    }

    /**
     * Método que se encarga de mostrar una imagen para el ImageView imgCPU
     * gracias a un número aleatorio entre 0 y la longitud de la clase enum Armas
     * casteada a Array. Además llama al método que actualiza el marcador del juego
     */
    fun cambioImagenCpu() {
        //Recojo las variables de los fragmentos que voy a utilizar
        var txtMarcadorCpu: TextView = findViewById(R.id.txtMarcCPU)
        var txtMarcadorUser: TextView = findViewById(R.id.txtMarcUser)
        var random = Random.nextInt(Armas.values().size)
        var arma: Armas = Armas.values().get(random)
        var imgCpu: ImageView = findViewById(R.id.imgCPU)

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

    /**
     * Método que se encarga de modificar el src del ImageView correspondiente a la eleccion del usuario
     *
     * <values>ResID:Int</values>
     * <pre>ResID debe ser uno de los valores del atributo ResourceID de la clase enum Armas</pre>
     */
    private fun cambioImagenUsuario(ResID: Int) {
        var imgUser = findViewById<ImageView>(R.id.imgUser)
        imgUser.setImageResource(ResID)
        imgUserRes = ResID
    }

    /**
     * Método que se encarga de llamar a los métodos que cambian la imagen
     * del usuario por el de el enum Armas.PIEDRA y el CPU por uno aleatorio entre los posibles de la clase enum Armas
     * <values>ResID:Int</values>
     * <pre>ResID debe ser uno de los valores del atributo ResourceID de la clase enum Armas</pre>
     */
    override fun onClickArma(ResID: Int) {
        cambioImagenUsuario(ResID)
        cambioImagenCpu()

    }




}