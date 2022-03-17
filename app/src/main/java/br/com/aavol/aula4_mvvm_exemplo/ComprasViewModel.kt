package br.com.aavol.aula4_mvvm_exemplo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComprasViewModel : ViewModel() {
    
    // Encapsulamento das variáveis
    
    private var _moedas = MutableLiveData<Int>()
    val moedas : LiveData<Int>
        get() = _moedas

    private var _pontos = MutableLiveData<Int>()
    val pontos : LiveData<Int>
        get() = _pontos

    private var _valorCompra = MutableLiveData<Double>()
    val valorCompra : LiveData<Double>
        get() = _valorCompra


    fun addMoedas() {
        _moedas.value = (_moedas.value)!!.plus(1) // Operações utilizadas com métodos
        calcularValorCompra()
    }

    fun removerMoedas() {
        /* Modo aply recomendado pelo google */
        _moedas.apply {
            if (value!! <= 0) {
                value = 0
            } else{
                value = value!!.minus(1)
            }
        }
        calcularValorCompra()
    }

    fun addPontos() {
        _pontos.value = (_pontos.value)!!.plus(1) // Operações utilizadas com métodos
        calcularValorCompra()
    }

    fun removerPontos() {

        // Modo aply recomendado pelo google
        _pontos.apply {
            if (value!! <= 0) {
                value = 0
            } else{
                value = value!!.minus(1)
            }
        }
        calcularValorCompra()
    }

    fun calcularValorCompra() {
        _valorCompra.value = ((_pontos.value)!!.times(2)!!.plus(_moedas.value!!)).toDouble()
    }

    fun resetCompra() {
        _moedas.value = 0
        _pontos.value = 0
        _valorCompra.value = 00.0
    }

    init {
        resetCompra()
        Log.i("ComprasViewModel", "ComprasViewModel criado!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ComprasViewModel", "ComprasViewModel destruído!")
    }

    fun getCodigoCompra() : String {
        var lenght = 10 // Informar quantos caracteres haverão
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..lenght)
            .map { charset.random() }
            .joinToString ( " ")
    }
}
