package br.com.aavol.aula4_mvvm_exemplo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.aavol.aula4_mvvm_exemplo.databinding.FragmentItensBinding

class ItensFragment : Fragment() {
    private lateinit var binding: FragmentItensBinding

    /* ViewModel em escopo de Activity - Pode ser compartilhado entre fragmentos */
    private val myViewModel: ComprasViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_itens, container, false)

        /* vinculação de dados (databinding) no código xml do layout para se comunicar diretamente com o ViewModel. */
        binding.itensViewModel = myViewModel

        /* Usando LiveData para atualizar automaticamente seu layout por meio de vinculação de dados (databinding) */
        binding.setLifecycleOwner(activity)

        binding.btnCheckout.setOnClickListener {
            findNavController().navigate(br.com.aavol.aula4_mvvm_exemplo.ItensFragmentDirections.actionItensFragmentToCheckoutFragment())
        }
        return binding.root
    }
}