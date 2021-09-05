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

    private fun checkData(titulo : String, descricao : String, data : String, status : String, dono : String): Boolean {
        val verifica = (!titulo.isEmpty() && !descricao.isEmpty() && !data.isEmpty() and !status.isEmpty() and !dono.isEmpty())
        return verifica
    }

    private fun saveDataToDatabase() {
        val titulo = binding.etTitulo.text.toString()
        val descricao = binding.etDescricao.text.toString()
        val data = binding.etData.text.toString()
        val status = binding.etStatus.text.toString()
        val dono = binding.etDono.text.toString()
        if (checkData(titulo, descricao, data, status, dono)){
            val cartao = Cartao(0,titulo, descricao, data, status, dono)
            mCartaoViewModel.addCartao(cartao)
            Toast.makeText(requireContext(), "Incluído com sucesso", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }
}