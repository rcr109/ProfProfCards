package rcr.projects.profcards.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import rcr.projects.profcards.R
import rcr.projects.profcards.model.Cartao
import rcr.projects.profcards.viewmodel.CartaoViewModel
import rcr.projects.profcards.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var mCartaoViewModel: CartaoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater)
        mCartaoViewModel = ViewModelProvider(this).get(CartaoViewModel::class.java)
        binding.btCheck.setOnClickListener {
            saveDataToDatabase()
        }
        return binding.root
    }

    private fun checkData(
        nome: String,
        empresa: String,
        profissao: String,
        telefone: String,
        email: String,
    ): Boolean {
        val verifica = (!nome.isEmpty() && !empresa.isEmpty() && !profissao.isEmpty() && !telefone.isEmpty() and !email.isEmpty())
        return verifica
    }

    private fun saveDataToDatabase() {
        val nome = binding.etNome.text.toString()
        val empresa = binding.etEmpresa.text.toString()
        val profissao = binding.etProfissao.text.toString()
        val telefone = binding.etTelefone.text.toString()
        val email = binding.etEmail.text.toString()

        if (checkData(nome, empresa, profissao, telefone, email)){
            val cartao = Cartao(0,nome, empresa, profissao, telefone, email)
            mCartaoViewModel.addCartao(cartao)
            Toast.makeText(requireContext(), "Incluído com sucesso", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }
}