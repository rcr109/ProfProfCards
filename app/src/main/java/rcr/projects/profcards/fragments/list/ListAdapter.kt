package rcr.projects.profcards.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import rcr.projects.profcards.databinding.DetalheCartaoCardBinding
import rcr.projects.profcards.model.Cartao

class ListAdapter(): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    var listenerShare : (View) -> Unit = {}
    inner class ViewHolder(val binding: DetalheCartaoCardBinding) : RecyclerView.ViewHolder(binding.root)
    private var cartoesList = emptyList<Cartao>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(cartoesList[position]){
                binding.tvId.text = this.id.toString()
                binding.tvNome.text = this.nome
                binding.tvEmpresa.text = this.empresa
                binding.tvProfissao.text = this.profissao
                binding.tvTelefone.text = this.telefone
                binding.tvEmail.text = this.email
                binding.rowLayout.setOnClickListener {
                    val action = ListFragmentDirections.actionListFragmentToUpdateFragment(this)
                    holder.itemView.findNavController().navigate(action)
                }
                binding.fbShare.setOnClickListener {
                    listenerShare(binding.rowLayout)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DetalheCartaoCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = cartoesList.size

    fun setData(cartao: List<Cartao>){
        this.cartoesList = cartao
        notifyDataSetChanged()
    }

}




