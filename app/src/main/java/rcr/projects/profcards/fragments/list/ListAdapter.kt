package rcr.projects.profcards.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import rcr.projects.profcards.model.Cartao
import rcr.projects.profcards.databinding.DetalheCartaoBinding
import rcr.projects.profcards.viewmodel.CartaoViewModel

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: DetalheCartaoBinding) : RecyclerView.ViewHolder(binding.root)

    private var cartoesList = emptyList<Cartao>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(cartoesList[position]){
                binding.tvId.text = this.id.toString()
                binding.tvTitulo.text = this.titulo
                binding.tvDescricao.text = this.mensagem
                binding.tvData.text = this.data
                binding.tvStatus.text = this.status
                binding.rowLayout.setOnClickListener {
                    val action = ListFragmentDirections.actionListFragmentToUpdateFragment(this)
                    holder.itemView.findNavController().navigate(action)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DetalheCartaoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = cartoesList.size

    fun setData(cartao: List<Cartao>){
        this.cartoesList = cartao
        notifyDataSetChanged()
    }

}




